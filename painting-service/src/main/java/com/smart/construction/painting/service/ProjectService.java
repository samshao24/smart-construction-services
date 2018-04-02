package com.smart.construction.painting.service;

import com.smart.construction.painting.entity.ProjectEntity;

import java.util.List;

public interface ProjectService {

    /**
     * Find project information by Id
     * @param id
     * @return
     */
    ProjectEntity getProjectById(long id);

    /**
     * Find all existing projects
     * @return
     */
    List<ProjectEntity> getAllProjects();


}
