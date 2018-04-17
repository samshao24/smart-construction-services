package com.smart.construction.painting.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_type")
public class ProjectTypeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_type_seq")
    @SequenceGenerator(
            name="project_type_seq",
            sequenceName="project_type_seq",
            initialValue = 1000)
    private long id;

    @Column(name = "type")
    private String type;

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
