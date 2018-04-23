package com.smart.construction.painting.service.impl;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.constant.MaterialQuality;
import com.smart.construction.painting.entity.ProjectTypeEntity;
import com.smart.construction.painting.entity.setup.FinancialSetupEntity;
import com.smart.construction.painting.entity.setup.PaintingMaterialEntity;
import com.smart.construction.painting.model.PaintingMaterial;
import com.smart.construction.painting.model.ProjectType;
import com.smart.construction.painting.repo.FinancialSetupRepository;
import com.smart.construction.painting.repo.PaintingMaterialRepository;
import com.smart.construction.painting.repo.ProjectTypeRepository;
import com.smart.construction.painting.service.ProjectSetupService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProjectSetupServiceImpl implements ProjectSetupService {

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @Autowired
    private FinancialSetupRepository financialSetupRepository;

    @Autowired
    private PaintingMaterialRepository paintingMaterialRepository;

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

    @Override
    public FinancialSetupEntity getFinancialSetup() throws ServiceException {
        FinancialSetupEntity entity = null;
        Iterator<FinancialSetupEntity> iterator = financialSetupRepository.findAll().iterator();
        if (iterator.hasNext()) {
            entity = iterator.next();
        }
        return entity;
    }

    @Override
    public void saveFinancialSetup(FinancialSetupEntity financialSetupEntity) throws ServiceException {
        financialSetupRepository.save(financialSetupEntity);
    }


    @Override
    public List<PaintingMaterial> getPaintingMaterialList() throws ServiceException {
        List<PaintingMaterial> resultList = new ArrayList<>();
        List<PaintingMaterialEntity> paintingMaterialList = (List<PaintingMaterialEntity>) paintingMaterialRepository.findAll();
        if (CollectionUtils.isNotEmpty(paintingMaterialList)) {
            paintingMaterialList.forEach(entity -> {
                resultList.add(convertPaintingMaterialEntity(entity));
            });
        }
        return resultList;
    }

    @Override
    public PaintingMaterial getPaintingMaterialById(Long id) throws ServiceException {
        PaintingMaterialEntity entity =  paintingMaterialRepository.findById(id).get();
        return convertPaintingMaterialEntity(entity);
    }

    @Override
    public void savePaintingMaterial(PaintingMaterial paintingMaterial) throws ServiceException {
        paintingMaterialRepository.save(convertPaintingMaterial(paintingMaterial));
    }

    @Override
    public void deletePaintingMaterial(Long id) throws ServiceException {
        paintingMaterialRepository.deleteById(id);
    }

    private ProjectType convertEntity(ProjectTypeEntity entity) {
        ProjectType projectType = new ProjectType();
        projectType.setId(entity.getId());
        projectType.setType(entity.getType());
        return projectType;
    }

    private PaintingMaterial convertPaintingMaterialEntity(PaintingMaterialEntity entity) {
        PaintingMaterial paintingMaterial = new PaintingMaterial();
        paintingMaterial.setId(entity.getId());
        paintingMaterial.setName(entity.getName());
        paintingMaterial.setCode(entity.getCode());
        paintingMaterial.setOriginalPrice(entity.getOriginalPrice());
        paintingMaterial.setQuotePrice(entity.getQuotePrice());
        String qualityCode = entity.getQuality();
        String qualityDisplay = MaterialQuality.getByCode(qualityCode).getDisplay();
        paintingMaterial.setQualityCode(qualityCode);
        paintingMaterial.setQuality(qualityDisplay);
        return paintingMaterial;
    }

    private PaintingMaterialEntity convertPaintingMaterial(PaintingMaterial paintingMaterial) {
        PaintingMaterialEntity entity = null;
        Long id = paintingMaterial.getId();
        if (id != null) {
            entity = paintingMaterialRepository.findById(id).get();
        }
        if (entity == null) {
            entity = new PaintingMaterialEntity();
        }
        entity.setName(paintingMaterial.getName());
        entity.setCode(paintingMaterial.getCode());
        entity.setOriginalPrice(paintingMaterial.getOriginalPrice());
        entity.setQuotePrice(paintingMaterial.getQuotePrice());
        entity.setQuality(paintingMaterial.getQualityCode());
        return entity;
    }
}
