package com.smart.construction.painting.controller;

import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.RoomEntity;
import com.smart.construction.painting.model.Project;
import com.smart.construction.painting.model.Room;
import com.smart.construction.painting.service.ProjectService;
import com.smart.construction.painting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping(value="detail/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Project getProjectById(@PathVariable long id) {
		Project project = projectService.getProjectById(id);
		return project;
	}

	@GetMapping(value="list", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping(value = "{id}/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Room> getRoomsByProjectId(@PathVariable(value = "id") long projectId) {
		return null;
	}

	@DeleteMapping(value = "delete/{id}")
	public void deleteProject(@PathVariable(value = "id") long id) {
		projectService.deleteProject(id);
	}

	@PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveProject(@RequestBody Project project) {
		projectService.saveProject(project);
	}
}
