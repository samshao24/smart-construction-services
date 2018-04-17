package com.smart.construction.painting.service;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.model.Project;
import com.smart.construction.painting.model.Room;

import java.util.List;

public interface ProjectService {

    /**
     * Find project information by Id
     * @param id
     * @return
     */
    Project getProjectById(long id) throws ServiceException;

    /**
     * Find all existing projects
     * @return
     */
    List<Project> getAllProjects() throws ServiceException;

    /**
     * Method to save or update new or existing project.
     * @param project
     * @throws ServiceException
     */
    void saveProject(Project project) throws ServiceException;

    /**
     * Delete project by id
     * @param id
     * @throws ServiceException
     */
    void deleteProject(long id) throws ServiceException;

}
