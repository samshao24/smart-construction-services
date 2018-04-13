package com.smart.construction.painting.service.impl;

import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.mapper.PaintingMapper;
import com.smart.construction.painting.model.Room;
import com.smart.construction.painting.repo.RoomRepository;
import com.smart.construction.painting.service.RoomService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PaintingMapper paintingMapper;

    @Override
    public Room getRoomById(long id) {
        RoomEntity roomEntity = roomRepository.findById(id).get();
        return paintingMapper.map(roomEntity, Room.class);
    }

    @Override
    public List<Room> getRoomListByProjectId(long projectId) {
        List<Room> roomList = new ArrayList<>();
        List<RoomEntity> roomEntityList = roomRepository.findByProject_Id(projectId);
        if (CollectionUtils.isNotEmpty(roomEntityList)) {
            roomList = roomEntityList
                    .stream()
                    .map(roomEntity -> paintingMapper.map(roomEntity, Room.class))
                    .distinct()
                    .collect(Collectors.toList());

        }
        return roomList;
    }

    @Override
    public void removeRoom(long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public void saveAndUpdateRoom(Room room) {
        RoomEntity roomEntity = paintingMapper.map(room, RoomEntity.class);
        roomRepository.save(roomEntity);
    }

    @Override
    public Room calculate(Room room) {
        // TODO: Here need to implement the actual logic to get the cost of room painting.
        return new Room();
    }
}
