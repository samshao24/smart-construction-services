package com.smart.construction.painting.service;

import com.smart.construction.painting.entity.RoomEntity;

import java.util.List;

public interface RoomService {

    /**
     * Find room information by Id
     * @param id
     * @return
     */
    RoomEntity getRoomById(long id);

    /**
     * Find list of Rooms information by project Id
     * @param projectId
     * @return
     */
    List<RoomEntity> getRoomListByProjectId(long projectId);

    /**
     * Remove one room based on Id
     * @param roomId
     */
    void removeRoom(long roomId);

    /**
     * Update room information
     * @return
     */
    RoomEntity updateRoom(RoomEntity room);


}
