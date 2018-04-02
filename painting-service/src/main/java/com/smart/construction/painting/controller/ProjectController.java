package com.smart.construction.painting.controller;

import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.service.ProjectService;
import com.smart.construction.painting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping(value="/project/{id}",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ProjectEntity getRoomByProject(@PathVariable long id) {
		ProjectEntity project = projectService.getProjectById(id);
		return project;
	}

	@GetMapping(value="/project/all",  produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectEntity> getAllProjects() {
		return projectService.getAllProjects();
	}
}