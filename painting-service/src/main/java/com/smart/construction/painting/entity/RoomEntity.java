package com.smart.construction.painting.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "room_detail")
public class RoomEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="room_detail_seq")
    private long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="project_id")
    private ProjectEntity project;

    @Column(name = "height")
    private Double height;

    @Column(name = "width")
    private Double width;

    @Column(name = "length")
    private Double length;

    @Column(name = "closet_count")
    private Integer closetCount;

    @Column(name = "door_count")
    private Integer doorCount;

    @Column(name = "mantle_count")
    private Integer mantleCount;

    @Column(name = "column_count")
    private Integer columnCount;

    @Column(name = "wall_size")
    private Double wallSize;

    @Column(name = "ceiling_size")
    private Double ceilingSize;

    @Column(name = "trim_size")
    private Double trimSize;

    @Column(name = "trim_with_crown_size")
    private Double trimWithCrownSize;

    public long getId() {
        return id;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getClosetCount() {
        return closetCount;
    }

    public void setClosetCount(Integer closetCount) {
        this.closetCount = closetCount;
    }

    public Integer getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(Integer doorCount) {
        this.doorCount = doorCount;
    }

    public Integer getMantleCount() {
        return mantleCount;
    }

    public void setMantleCount(Integer mantleCount) {
        this.mantleCount = mantleCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public Double getWallSize() {
        return wallSize;
    }

    public void setWallSize(Double wallSize) {
        this.wallSize = wallSize;
    }

    public Double getCeilingSize() {
        return ceilingSize;
    }

    public void setCeilingSize(Double ceilingSize) {
        this.ceilingSize = ceilingSize;
    }

    public Double getTrimSize() {
        return trimSize;
    }

    public void setTrimSize(Double trimSize) {
        this.trimSize = trimSize;
    }

    public Double getTrimWithCrownSize() {
        return trimWithCrownSize;
    }

    public void setTrimWithCrownSize(Double trimWithCrownSize) {
        this.trimWithCrownSize = trimWithCrownSize;
    }
}
