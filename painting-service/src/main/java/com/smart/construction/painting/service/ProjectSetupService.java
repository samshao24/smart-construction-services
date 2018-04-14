package com.smart.construction.painting.service;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.model.ProjectType;

import java.util.List;

public interface ProjectSetupService {

    /**
     * Service to get all project type
     * @return
     * @throws ServiceException
     */
    List<ProjectType> getAllProjectType() throws ServiceException;

}
