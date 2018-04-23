package com.smart.construction.painting.service.impl;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.constant.RoomType;
import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.entity.RoomExpenseEntity;
import com.smart.construction.painting.entity.setup.FinancialSetupEntity;
import com.smart.construction.painting.entity.setup.PaintingMaterialEntity;
import com.smart.construction.painting.mapper.PaintingMapper;
import com.smart.construction.painting.model.Room;
import com.smart.construction.painting.model.RoomExpense;
import com.smart.construction.painting.repo.ProjectRepository;
import com.smart.construction.painting.repo.RoomRepository;
import com.smart.construction.painting.service.ProjectSetupService;
import com.smart.construction.painting.service.RoomService;
import org.apache.commons.collections4.CollectionUtils;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ProjectSetupService projectSetupService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PaintingMapper paintingMapper;

    @Override
    public Room getRoomById(long id) {
        RoomEntity roomEntity = roomRepository.findById(id).get();
        // paintingMapper.map(roomEntity, Room.class);
        Room room = convertEntity(roomEntity);
        return room;
    }

    @Override
    public List<Room> getRoomListByProjectId(long projectId) {
        List<Room> roomList = new ArrayList<>();
        List<RoomEntity> roomEntityList = roomRepository.findByProject_Id(projectId);
        if (CollectionUtils.isNotEmpty(roomEntityList)) {
            /*roomList = roomEntityList
                    .stream()
                    .map(roomEntity -> paintingMapper.map(roomEntity, Room.class))
                    .distinct()
                    .collect(Collectors.toList());*/
            roomList = roomEntityList.stream().map(roomEntity -> convertEntity(roomEntity))
                    .collect(Collectors.toList());

        }
        return roomList;
    }

    @Override
    public void removeRoom(long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public void saveRoom(Room room) {
        // RoomEntity roomEntity = paintingMapper.map(room, RoomEntity.class);
        RoomEntity roomEntity = convertModel(room);
        roomRepository.save(roomEntity);
    }

    @Override
    public Room calculate(Room room) {
        // TODO: Need to re implement a trim paint cost field somehow
        Integer trimPaintGallonCost = 30;

        FinancialSetupEntity financialSetup = projectSetupService.getFinancialSetup();
        Integer wallLaborPerHour = financialSetup.getWallLaborPerHour();
        Integer columnLaborPerHour = financialSetup.getColumnLaborPerHour();
        Integer mantleLaborPerHour = financialSetup.getMantleLaborPerHour();
        Integer closetLaborPerHour = financialSetup.getClosetLaborPerHour();

        RoomExpense roomExpense = room.getExpense();
        if (roomExpense == null) {
            roomExpense = new RoomExpense();
        }

        Double height = room.getHeight();
        Double width = room.getWidth();
        Double length = room.getLength();
        Integer closetCount = room.getClosetCount();
        Integer doorCount = room.getDoorCount();
        Integer mantleCount = room.getMantleCount();
        Integer columnCount = room.getColumnCount();

        Double wallSize = (width + length) * height;
        room.setWallSize(wallSize);
        Double ceilingSize = width * length;
        room.setCeilingSize(ceilingSize);

        Double trimSize = (ceilingSize * 2 + closetCount * 2) * (room.getTrimWithCrown() ? 2 : 1);

        // Labor Part
        Integer wallHours = ((Double) Math.ceil(wallSize / 150)).intValue();
        Integer wallLabor = wallLaborPerHour * wallHours;

        Integer ceilingHour = ((Double) Math.ceil(ceilingSize / 200)).intValue();
        Integer ceilingLabor = wallLaborPerHour * ceilingHour;

        Integer trimHour = ((Double) Math.ceil(trimSize / 40)).intValue();
        Integer trimLabor = wallLaborPerHour * trimHour;

        Integer doorLabor = doorCount * wallLaborPerHour;
        Integer mantleLabor = mantleCount * mantleLaborPerHour;
        Integer columnLabor = columnCount * columnLaborPerHour;
        Integer closetLabor = closetCount * closetLaborPerHour;

        // Paint Material Part
        Long projectId = room.getProjectId();
        ProjectEntity projectEntity = projectRepository.findById(projectId).get();
        if (projectEntity == null) {
            throw new ServiceException("Unable to find project");
        }
        PaintingMaterialEntity paintingMaterialEntity = projectEntity.getPaintingMaterial();
        if (paintingMaterialEntity == null) {
            throw new ServiceException("Unable to find material setup information");
        }
        Double paintingPrice = paintingMaterialEntity.getQuotePrice();

        Double wallPaintCost = Math.ceil(wallSize / 250) * paintingPrice;
        roomExpense.setWallExpense(wallPaintCost + wallLabor);

        Double ceilingPaintCost = Math.ceil(ceilingSize / 250) * paintingPrice;
        roomExpense.setCeilingExpense(ceilingPaintCost + wallLabor);

        Double trimPaintCost = Math.ceil(trimSize / 300) * trimPaintGallonCost;
        roomExpense.setTrimExpense(trimPaintCost + trimLabor);

        Double doorPaintCost = Math.ceil(doorCount / 4) * trimPaintGallonCost;
        roomExpense.setDoorExpense(doorPaintCost + doorLabor);

        Double mantlePaintCost = Math.ceil(mantleCount / 4) * trimPaintGallonCost;
        roomExpense.setMantleExpense(mantlePaintCost + mantleLabor);

        Double columnPaintCost = Math.ceil(columnCount / 4) * trimPaintGallonCost;
        roomExpense.setColumnExpense(columnPaintCost + columnLabor);

        Double closetPaintCost = Math.ceil(closetCount / 2) * paintingPrice;
        roomExpense.setClosetExpense(closetPaintCost + closetLabor);

        Double totalMaterial = wallPaintCost + ceilingPaintCost + trimPaintCost + doorPaintCost + mantlePaintCost + columnPaintCost + closetPaintCost;
        roomExpense.setMaterialExpense(totalMaterial);
        Double totalLabor = Math.ceil(wallLabor + ceilingLabor + trimLabor + doorLabor + mantleLabor + columnLabor + closetLabor);
        roomExpense.setLaborExpense(totalLabor);
        roomExpense.setTotalExpense(totalMaterial + totalLabor);
        room.setExpense(roomExpense);
        return room;
    }

    private RoomEntity convertModel(Room room) {
        RoomEntity roomEntity = null;
        if (room != null) {
            LocalDate now = LocalDate.now();
            Long id = room.getId();
            if (id != null) {
                roomEntity = roomRepository.findById(id).get();
            }
            if (roomEntity == null) {
                roomEntity = new RoomEntity();
                roomEntity.setCreatedDate(now);
            } else {
                roomEntity.setUpdatedDate(now);
            }
            Long projectId = room.getProjectId();
            ProjectEntity projectEntity = projectRepository.findById(projectId).get();
            roomEntity.setProject(projectEntity);
            roomEntity.setType(room.getType());
            roomEntity.setHeight(room.getHeight());
            roomEntity.setWidth(room.getWidth());
            roomEntity.setLength(room.getLength());
            roomEntity.setDoorCount(room.getDoorCount());
            roomEntity.setClosetCount(room.getClosetCount());
            roomEntity.setMantleCount(room.getMantleCount());
            roomEntity.setColumnCount(room.getColumnCount());
            roomEntity.setTrimWithCrown(room.getTrimWithCrown());
            roomEntity.setWallSize(room.getWallSize());
            roomEntity.setCeilingSize(room.getCeilingSize());
            roomEntity.setTrimSize(room.getTrimSize());

            // Expense
            RoomExpense expense = room.getExpense();
            if (expense != null) {
                RoomExpenseEntity expenseEntity = roomEntity.getRoomExpense();
                if (expenseEntity == null) {
                    expenseEntity = new RoomExpenseEntity();
                }
                expenseEntity.setRoom(roomEntity);
                expenseEntity.setTrimExpense(expense.getTrimExpense());
                expenseEntity.setColumnExpense(expense.getColumnExpense());
                expenseEntity.setDoorExpense(expense.getDoorExpense());
                expenseEntity.setMantleExpense(expense.getMantleExpense());
                expenseEntity.setClosetExpense(expense.getClosetExpense());
                expenseEntity.setWallExpense(expense.getWallExpense());
                expenseEntity.setCeilingExpense(expense.getCeilingExpense());
                expenseEntity.setMaterialExpense(expense.getMaterialExpense());
                expenseEntity.setLaborExpense(expense.getLaborExpense());
                expenseEntity.setTotalExpense(expense.getTotalExpense());
                roomEntity.setRoomExpense(expenseEntity);
            }
        }
        return roomEntity;
    }

    private Room convertEntity(RoomEntity roomEntity) {
        // Detail
        Room room = new Room();
        room.setId(roomEntity.getId());
        room.setProjectId(roomEntity.getProject().getId());
        String type = roomEntity.getType();
        room.setType(type);
        room.setTypeDisplay(RoomType.getByValue(type).getDisplay());
        room.setWidth(roomEntity.getWidth());
        room.setHeight(roomEntity.getHeight());
        room.setLength(roomEntity.getLength());
        room.setDoorCount(roomEntity.getDoorCount());
        room.setClosetCount(roomEntity.getClosetCount());
        room.setMantleCount(roomEntity.getMantleCount());
        room.setColumnCount(roomEntity.getColumnCount());
        room.setTrimWithCrown(roomEntity.isTrimWithCrown());
        room.setWallSize(roomEntity.getWallSize());
        room.setCeilingSize(roomEntity.getCeilingSize());
        room.setTrimSize(roomEntity.getTrimSize());
        // Expense
        RoomExpenseEntity expenseEntity = roomEntity.getRoomExpense();
        RoomExpense expense = new RoomExpense();
        if (expenseEntity != null) {
            expense.setClosetExpense(expenseEntity.getClosetExpense());
            expense.setDoorExpense(expenseEntity.getDoorExpense());
            expense.setMantleExpense(expenseEntity.getMantleExpense());
            expense.setColumnExpense(expenseEntity.getColumnExpense());
            expense.setWallExpense(expenseEntity.getWallExpense());
            expense.setCeilingExpense(expenseEntity.getCeilingExpense());
            expense.setMaterialExpense(expenseEntity.getMaterialExpense());
            expense.setLaborExpense(expenseEntity.getLaborExpense());
            expense.setTotalExpense(expenseEntity.getTotalExpense());
        }
        room.setExpense(expense);
        return room;
    }
}
