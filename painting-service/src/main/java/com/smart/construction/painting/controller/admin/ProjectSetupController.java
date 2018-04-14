package com.smart.construction.painting.controller.admin;

import com.smart.construction.painting.model.Customer;
import com.smart.construction.painting.model.ProjectType;
import com.smart.construction.painting.service.ProjectSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
