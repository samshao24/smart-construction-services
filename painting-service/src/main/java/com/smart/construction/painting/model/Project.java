package com.smart.construction.painting.model;

import java.time.LocalDate;
import java.util.List;

public class Project {

    private Long Id;
    private ProjectType type;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String status;
    private Long totalCost;
    private Customer customer;

    private List<Room> roomList;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}
