package com.smart.construction.painting.controller;

import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

	@Autowired
	RoomService roomService;

	@GetMapping(value="/rooms/projectId/{projectId}",  produces=MediaType.APPLICATION_JSON_VALUE)
	public List<RoomEntity> getRoomByProject(@PathVariable long projectId) {
		List<RoomEntity> list = roomService.getRoomListByProjectId(projectId);
		return list;
	}
	
	@PostMapping(value="/room/save")
	public RoomEntity saveRoom(@RequestBody RoomEntity room) {
		roomService.updateRoom(room);
		return room;
	}

	@PutMapping(value="/room/update")
	public RoomEntity updateRoom(@RequestBody RoomEntity room) {
		roomService.updateRoom(room);
		return room;
	}
	
	@DeleteMapping(value="/room/delete/{id}")
	public void deleteCustomer(@PathVariable long id){
		roomService.removeRoom(id);
	}
}
