package com.smart.construction.painting.service.impl;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.ProjectTypeEntity;
import com.smart.construction.painting.mapper.PaintingMapper;
import com.smart.construction.painting.model.Project;
import com.smart.construction.painting.model.ProjectType;
import com.smart.construction.painting.repo.ProjectRepository;
import com.smart.construction.painting.service.ProjectService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PaintingMapper paintingMapper;

    @Override
    public Project getProjectById(long id) throws ServiceException {
        ProjectEntity project = projectRepository.findById(id).get();
        return paintingMapper.map(project, Project.class);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> resultList = new ArrayList<>();
        List<ProjectEntity> projectList = (List<ProjectEntity>) projectRepository.findAll();
        if (CollectionUtils.isNotEmpty(projectList)) {
            projectList.forEach(projectEntity -> {
                resultList.add(convertEntity(projectEntity));
            });
        }
        return resultList;
    }

    @Override
    public void saveProject(Project project) throws ServiceException {
        ProjectEntity projectEntity = paintingMapper.map(project, ProjectEntity.class);
        projectRepository.save(projectEntity);
    }

    private Project convertEntity(ProjectEntity entity) {
        Project project = new Project();
        project.setId(entity.getId());
        project.setStatus(entity.getStatus().getStatus());
        ProjectTypeEntity typeEntity = entity.getType();
        ProjectType type = new ProjectType();
        type.setId(typeEntity.getId());
        type.setType(typeEntity.getType());
        project.setType(type);
        return project;
    }
}
