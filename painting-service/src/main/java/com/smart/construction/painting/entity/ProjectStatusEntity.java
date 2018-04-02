package com.smart.construction.painting.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_status")
public class ProjectStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="project_status_seq")
    private long id;

    @Column(name = "status")
    private String status;

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}