package com.smart.construction.painting.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="project_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private ProjectTypeEntity type;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private ProjectStatusEntity status;

    @OneToMany(mappedBy = "project")
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

    public List<RoomEntity> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RoomEntity> roomList) {
        this.roomList = roomList;
    }
}
