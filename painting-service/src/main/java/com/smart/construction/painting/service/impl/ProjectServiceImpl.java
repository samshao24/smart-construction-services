package com.smart.construction.painting.service.impl;

import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.repo.ProjectRepository;
import com.smart.construction.painting.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectEntity getProjectById(long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        return (List<ProjectEntity>) projectRepository.findAll();

    }
}
