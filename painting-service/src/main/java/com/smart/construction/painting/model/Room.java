package com.smart.construction.painting.model;

public class Room {

    private Long id;
    private Long projectId;
    private String type;
    private String typeDisplay;
    private Double height;
    private Double width;
    private Double length;
    private Integer doorCount;
    private Integer closetCount;
    private Integer mantleCount;
    private Integer columnCount;
    private Boolean trimWithCrown;
    private Double wallSize;
    private Double ceilingSize;
    private Double trimSize;
    private RoomExpense expense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
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

    public Integer getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(Integer doorCount) {
        this.doorCount = doorCount;
    }

    public Integer getClosetCount() {
        return closetCount;
    }

    public void setClosetCount(Integer closetCount) {
        this.closetCount = closetCount;
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

    public Boolean getTrimWithCrown() {
        return trimWithCrown;
    }

    public void setTrimWithCrown(Boolean trimWithCrown) {
        this.trimWithCrown = trimWithCrown;
    }

    public Double getTrimSize() {
        return trimSize;
    }

    public void setTrimSize(Double trimSize) {
        this.trimSize = trimSize;
    }

    public RoomExpense getExpense() {
        return expense;
    }

    public void setExpense(RoomExpense expense) {
        this.expense = expense;
    }
}
