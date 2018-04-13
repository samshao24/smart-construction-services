package com.smart.construction.painting.service;

import com.smart.construction.painting.model.Room;

import java.util.List;

public interface RoomService {

    /**
     * Find room information by Id
     *
     * @param id
     * @return
     */
    Room getRoomById(long id);

    /**
     * Find list of Rooms information by project Id
     *
     * @param projectId
     * @return
     */
    List<Room> getRoomListByProjectId(long projectId);

    /**
     * Remove one room based on Id
     *
     * @param roomId
     */
    void removeRoom(long roomId);

    /**
     * Update room information
     *
     * @return
     */
    void saveAndUpdateRoom(Room room);

    /**
     *
     * @param room
     * @return
     */
    Room calculate(Room room);

}
