package com.smart.construction.painting.service.impl;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.entity.ProjectEntity;
import com.smart.construction.painting.entity.ProjectTypeEntity;
import com.smart.construction.painting.model.Project;
import com.smart.construction.painting.model.ProjectType;
import com.smart.construction.painting.repo.ProjectTypeRepository;
import com.smart.construction.painting.service.ProjectSetupService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectSetupServiceImpl implements ProjectSetupService {

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @Override
    public List<ProjectType> getAllProjectType() throws ServiceException {
        List<ProjectType> resultList = new ArrayList<>();
        List<ProjectTypeEntity> typeList = (List<ProjectTypeEntity>) projectTypeRepository.findAll();
        if (CollectionUtils.isNotEmpty(typeList)) {
            typeList.forEach(projectType -> {
                resultList.add(convertEntity(projectType));
            });
        }
        return resultList;
    }

    private ProjectType convertEntity(ProjectTypeEntity entity) {
        ProjectType projectType = new ProjectType();
        projectType.setId(entity.getId());
        projectType.setType(entity.getType());
        return projectType;
    }
}
