package com.smart.construction.painting.controller.admin;

import com.smart.construction.painting.entity.setup.FinancialSetupEntity;
import com.smart.construction.painting.model.Customer;
import com.smart.construction.painting.model.PaintingMaterial;
import com.smart.construction.painting.model.ProjectType;
import com.smart.construction.painting.service.ProjectSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project/admin")
public class ProjectSetupController {

    @Autowired
    private ProjectSetupService projectSetupService;

    @GetMapping(value="/type",  produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectType> getAll() {
        return projectSetupService.getAllProjectType();
    }

    @GetMapping(value="/painting/material/all",  produces= MediaType.APPLICATION_JSON_VALUE)
    public List<PaintingMaterial> getAllPaintingMaterial() {
        return projectSetupService.getPaintingMaterialList();
    }

    @PostMapping(value="/painting/material/save",  produces= MediaType.APPLICATION_JSON_VALUE)
    public void savePaintingMaterial(@RequestBody PaintingMaterial paintingMaterial) {
        projectSetupService.savePaintingMaterial(paintingMaterial);
    }

    @GetMapping(value="/painting/financial/setup",  produces= MediaType.APPLICATION_JSON_VALUE)
    public FinancialSetupEntity getFinancialSetup() {
        return projectSetupService.getFinancialSetup();
    }

    @PostMapping(value="/painting/financial/save",  produces= MediaType.APPLICATION_JSON_VALUE)
    public void saveFinancialSetup(@RequestBody FinancialSetupEntity entity) {
        projectSetupService.saveFinancialSetup(entity);
    }
}
