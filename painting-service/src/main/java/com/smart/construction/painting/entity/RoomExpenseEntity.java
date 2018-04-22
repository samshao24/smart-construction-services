package com.smart.construction.painting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "room_expense")
public class RoomExpenseEntity extends Auditable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="room_expense_seq")
    @SequenceGenerator(
            name="room_expense_seq",
            sequenceName="room_expense_seq",
            initialValue = 1000,
            allocationSize = 1)
    private long id;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="room_detail_id")
    private RoomEntity room;

    @Column(name = "closet_expense")
    private Double closetExpense;

    @Column(name = "door_expense")
    private Double doorExpense;

    @Column(name = "mantle_expense")
    private Double mantleExpense;

    @Column(name = "column_expense")
    private Double columnExpense;

    @Column(name = "wall_expense")
    private Double wallExpense;

    @Column(name = "ceiling_expense")
    private Double ceilingExpense;

    @Column(name = "material_expense")
    private Double materialExpense;

    @Column(name = "labor_expense")
    private Double laborExpense;

    @Column(name = "total_expense")
    private Double totalExpense;

    public long getId() {
        return id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public Double getClosetExpense() {
        return closetExpense;
    }

    public void setClosetExpense(Double closetExpense) {
        this.closetExpense = closetExpense;
    }

    public Double getDoorExpense() {
        return doorExpense;
    }

    public void setDoorExpense(Double doorExpense) {
        this.doorExpense = doorExpense;
    }

    public Double getMantleExpense() {
        return mantleExpense;
    }

    public void setMantleExpense(Double mantleExpense) {
        this.mantleExpense = mantleExpense;
    }

    public Double getColumnExpense() {
        return columnExpense;
    }

    public void setColumnExpense(Double columnExpense) {
        this.columnExpense = columnExpense;
    }

    public Double getWallExpense() {
        return wallExpense;
    }

    public void setWallExpense(Double wallExpense) {
        this.wallExpense = wallExpense;
    }

    public Double getCeilingExpense() {
        return ceilingExpense;
    }

    public void setCeilingExpense(Double ceilingExpense) {
        this.ceilingExpense = ceilingExpense;
    }

    public Double getMaterialExpense() {
        return materialExpense;
    }

    public void setMaterialExpense(Double materialExpense) {
        this.materialExpense = materialExpense;
    }

    public Double getLaborExpense() {
        return laborExpense;
    }

    public void setLaborExpense(Double laborExpense) {
        this.laborExpense = laborExpense;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
