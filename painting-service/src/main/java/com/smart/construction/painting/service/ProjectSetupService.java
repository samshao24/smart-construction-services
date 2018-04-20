package com.smart.construction.painting.service;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.entity.setup.FinancialSetupEntity;
import com.smart.construction.painting.entity.setup.PaintingMaterialEntity;
import com.smart.construction.painting.model.PaintingMaterial;
import com.smart.construction.painting.model.ProjectType;

import java.util.List;

public interface ProjectSetupService {

    /**
     * Service to get all project type
     *
     * @return
     * @throws ServiceException
     */
    List<ProjectType> getAllProjectType() throws ServiceException;

    /**
     * Temp service to get financial setup
     *
     * @throws ServiceException
     */
    FinancialSetupEntity getFinancialSetup() throws ServiceException;

    /**
     * Temp service to save financial setup information
     *
     * @param financialSetupEntity
     * @throws ServiceException
     */
    void saveFinancialSetup(FinancialSetupEntity financialSetupEntity) throws ServiceException;

    /**
     * Service to get all painting material
     *
     * @return
     * @throws ServiceException
     */
    List<PaintingMaterial> getPaintingMaterialList() throws ServiceException;

    /**
     * Service to save painting material
     *
     * @param paintingMaterial
     * @throws ServiceException
     */
    void savePaintingMaterial(PaintingMaterial paintingMaterial) throws ServiceException;

}
