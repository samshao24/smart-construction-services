package com.smart.construction.painting.controller;

import com.smart.construction.painting.model.Room;
import com.smart.construction.painting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping(value="/rooms/projectId/{projectId}",  produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Room> getRoomByProject(@PathVariable long projectId) {
		List<Room> list = roomService.getRoomListByProjectId(projectId);
		return list;
	}
	
	@PostMapping(value="/room/saveOrUpdate")
	public Room saveRoom(@RequestBody Room room) {
		roomService.saveAndUpdateRoom(room);
		return room;
	}
	
	@DeleteMapping(value="/room/delete/{id}")
	public void deleteRoom(@PathVariable long id){
		roomService.removeRoom(id);
	}

	@PostMapping(value = "/room/calculate")
	public Room calculateRoomCost(@RequestBody Room room) {
	    return roomService.calculate(room);
    }
}
