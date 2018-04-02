package com.smart.construction.painting.service.impl;

import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.repo.RoomRepository;
import com.smart.construction.painting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomEntity getRoomById(long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public List<RoomEntity> getRoomListByProjectId(long projectId) {
        return roomRepository.findByProject_Id(projectId);
    }

    @Override
    public void removeRoom(long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public RoomEntity updateRoom(RoomEntity room) {
        return roomRepository.save(room);
    }
}
