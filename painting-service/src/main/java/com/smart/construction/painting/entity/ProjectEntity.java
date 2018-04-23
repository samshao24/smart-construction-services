package com.smart.construction.painting.entity;

import com.smart.construction.painting.entity.setup.PaintingMaterialEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
public class ProjectEntity extends Auditable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_seq")
    @SequenceGenerator(
            name="project_seq",
            sequenceName="project_seq",
            initialValue = 1000)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "type_id")
    private ProjectTypeEntity type;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "status_id")
    private ProjectStatusEntity status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "painting_material_id")
    private PaintingMaterialEntity paintingMaterial;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RoomEntity> roomList;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public ProjectTypeEntity getType() {
        return type;
    }

    public void setType(ProjectTypeEntity type) {
        this.type = type;
    }

    public ProjectStatusEntity getStatus() {
        return status;
    }

    public void setStatus(ProjectStatusEntity status) {
        this.status = status;
    }

    public PaintingMaterialEntity getPaintingMaterial() {
        return paintingMaterial;
    }

    public void setPaintingMaterial(PaintingMaterialEntity paintingMaterial) {
        this.paintingMaterial = paintingMaterial;
    }

    public List<RoomEntity> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomEntity> roomList) {
        this.roomList = roomList;
    }
}
