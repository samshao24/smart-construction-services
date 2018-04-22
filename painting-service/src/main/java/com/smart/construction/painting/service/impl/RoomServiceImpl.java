package com.smart.construction.painting.service.impl;

import com.smart.construction.common.constant.RoomType;
import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.entity.RoomExpenseEntity;
import com.smart.construction.painting.mapper.PaintingMapper;
import com.smart.construction.painting.model.Room;
import com.smart.construction.painting.model.RoomExpense;
import com.smart.construction.painting.repo.ProjectRepository;
import com.smart.construction.painting.repo.RoomRepository;
import com.smart.construction.painting.service.RoomService;
import org.apache.commons.collections4.CollectionUtils;
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
        // TODO: Here need to implement the actual logic to get the cost of room painting.

        RoomExpense roomExpense = room.getRoomExpense();
        if (roomExpense == null) {
            roomExpense = new RoomExpense();
        }
        roomExpense.setMaterialExpense(1500.36);
        roomExpense.setLaborExpense(13000.00);
        roomExpense.setTotalExpense(14500.36);
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
            RoomExpense expense = room.getRoomExpense();
            if (expense != null) {
                RoomExpenseEntity expenseEntity = roomEntity.getRoomExpense();
                if (expenseEntity == null) {
                    expenseEntity = new RoomExpenseEntity();
                }
                expenseEntity.setRoom(roomEntity);
                expenseEntity.setColumnExpense(expense.getColumnExpense());
                expenseEntity.setDoorExpense(expense.getDoorExpense());
                expenseEntity.setMantleExpense(expense.getMantleExpense());
                expenseEntity.setColumnExpense(expense.getColumnExpense());
                expenseEntity.setWallExpense(expense.getWallExpense());
                expenseEntity.setCeilingExpense(expense.getCeilingExpense());
                expenseEntity.setMaterialExpense(expense.getMaterialExpense());
                expenseEntity.setLaborExpense(expense.getLaborExpense());
                expenseEntity.setTotalExpense(expense.getTotalExpense());
            }
        }
        return roomEntity;
    }

    private Room convertEntity(RoomEntity roomEntity) {
        // Detail
        Room room = new Room();
        room.setId(roomEntity.getId());
        room.setProjectId(roomEntity.getProject().getId());
        room.setType(roomEntity.getType());
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
        if (expenseEntity != null) {
            RoomExpense expense = new RoomExpense();
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
        return room;
    }
}
