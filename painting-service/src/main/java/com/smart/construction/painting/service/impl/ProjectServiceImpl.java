package com.smart.construction.painting.service.impl;

import com.smart.construction.common.exception.ServiceException;
import com.smart.construction.painting.entity.*;
import com.smart.construction.painting.mapper.PaintingMapper;
import com.smart.construction.painting.model.Address;
import com.smart.construction.painting.model.Customer;
import com.smart.construction.painting.model.Project;
import com.smart.construction.painting.model.ProjectType;
import com.smart.construction.painting.model.Room;
import com.smart.construction.painting.repo.ProjectRepository;
import com.smart.construction.painting.service.ProjectService;
import com.smart.construction.painting.service.RoomService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PaintingMapper paintingMapper;

    @Override
    public Project getProjectById(long id) throws ServiceException {
        ProjectEntity projectEntity = projectRepository.findById(id).get();
        Project project = convertEntity(projectEntity);
        List<Room> roomList = roomService.getRoomListByProjectId(id);
        project.setRoomList(roomList);
        return project;
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
        ProjectEntity projectEntity = convertModel(project);
        projectRepository.save(projectEntity);
    }

    @Override
    public void deleteProject(long id) throws ServiceException {
        projectRepository.deleteById(id);
    }

    private ProjectEntity convertModel(Project project) {
        ProjectEntity projectEntity = null;
        LocalDate now = LocalDate.now();
        if (project != null) {
            Long projectId = project.getId();
            if (projectId != null) {
                projectEntity = projectRepository.findById(projectId).get();
            }
            if (projectEntity == null) {
                projectEntity = new ProjectEntity();
                projectEntity.setCreatedDate(now);
            } else {
                projectEntity.setUpdatedDate(now);
            }
            projectEntity.setBeginDate(project.getBeginDate());
            projectEntity.setEndDate(project.getEndDate());
            projectEntity.setCreatedDate(LocalDate.now());

            Customer customer = project.getCustomer();
            if (customer != null) {
                CustomerEntity customerEntity = projectEntity.getCustomer();
                if (customerEntity == null) {
                    customerEntity = new CustomerEntity();
                    customerEntity.setCreatedDate(now);
                } else {
                    customerEntity.setUpdatedDate(now);
                }
                PersonEntity personEntity = customerEntity.getPerson();
                if (personEntity == null) {
                    personEntity = new PersonEntity();
                    personEntity.setCreatedDate(now);
                } else {
                    personEntity.setUpdatedDate(now);
                }
                personEntity.setGivenName(customer.getGivenName());
                personEntity.setMiddleName(customer.getMiddleName());
                personEntity.setFamilyName(customer.getFamilyName());
                personEntity.setHomePhone(customer.getHomePhone());
                personEntity.setCellPhone(customer.getCellPhone());
                personEntity.setEmailAddress(customer.getEmail());
                customerEntity.setPerson(personEntity);

                Address address = customer.getAddress();
                if (address != null) {
                    AddressEntity addressEntity = customerEntity.getAddress();
                    if (addressEntity == null) {
                        addressEntity = new AddressEntity();
                    } else {
                        addressEntity.setUpdatedDate(now);
                    }
                    addressEntity.setStreetAddress1(address.getStreetAddr1());
                    addressEntity.setStreetAddress2(address.getStreetAddr2());
                    addressEntity.setCity(address.getCity());
                    addressEntity.setState(address.getState());
                    addressEntity.setZipcode(address.getZipcode());
                    customerEntity.setAddress(addressEntity);
                }
                projectEntity.setCustomer(customerEntity);
            }
        }
        return projectEntity;
    }

    private Project convertEntity(ProjectEntity entity) {
        Project project = new Project();
        if (entity != null) {
            project.setId(entity.getId());
            project.setBeginDate(entity.getBeginDate());
            LocalDate endDate = entity.getEndDate();
            if (endDate != null && endDate.isAfter(LocalDate.now())) {
                project.setStatus("Active");
            } else {
                project.setStatus("Expired");
            }
            project.setEndDate(entity.getEndDate());
            ProjectStatusEntity statusEntity = entity.getStatus();
            if (statusEntity != null) {
                project.setId(statusEntity.getId());
                project.setStatus(statusEntity.getStatus());
            }
            ProjectTypeEntity typeEntity = entity.getType();
            if (typeEntity != null) {
                ProjectType type = new ProjectType();
                type.setId(typeEntity.getId());
                type.setType(typeEntity.getType());
                project.setType(type);
            }
            CustomerEntity customerEntity = entity.getCustomer();
            if (customerEntity != null) {
                Customer customer = new Customer();
                PersonEntity personEntity = customerEntity.getPerson();
                if (personEntity != null) {
                    customer.setGivenName(personEntity.getGivenName());
                    customer.setMiddleName(personEntity.getMiddleName());
                    customer.setFamilyName(personEntity.getFamilyName());
                    customer.setHomePhone(personEntity.getHomePhone());
                    customer.setCellPhone(personEntity.getCellPhone());
                    customer.setEmail(personEntity.getEmailAddress());
                }
                AddressEntity addressEntity = customerEntity.getAddress();
                if (addressEntity != null) {
                    Address address = new Address();
                    address.setId(addressEntity.getId());
                    address.setStreetAddr1(addressEntity.getStreetAddress1());
                    address.setStreetAddr2(addressEntity.getStreetAddress2());
                    address.setCity(addressEntity.getCity());
                    address.setState(addressEntity.getState());
                    address.setZipcode(addressEntity.getZipcode());
                    customer.setAddress(address);
                }
                project.setCustomer(customer);
            }
        }
        return project;
    }
}
