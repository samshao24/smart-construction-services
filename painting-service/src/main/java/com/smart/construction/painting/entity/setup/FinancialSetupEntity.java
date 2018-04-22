package com.smart.construction.painting.entity.setup;

import javax.persistence.*;

@Entity
@Table(name = "financial_setup")
public class FinancialSetupEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="financial_setup_seq")
    @SequenceGenerator(
            name="financial_setup_seq",
            sequenceName="financial_setup_seq",
            initialValue = 1000)
    private long id;

    @Column(name = "wall_labor_per_hour")
    private Integer wallLaborPerHour;

    @Column(name = "closet_labor_per_hour")
    private Integer closetLaborPerHour;

    @Column(name = "mantle_labor_per_hour")
    private Integer mantleLaborPerHour;

    @Column(name = "column_labor_per_hour")
    private Integer columnLaborPerHour;

    @Column(name = "profit_ratio")
    private Double profitRatio;

    public long getId() {
        return id;
    }

    public Integer getWallLaborPerHour() {
        return wallLaborPerHour;
    }

    public void setWallLaborPerHour(Integer wallLaborPerHour) {
        this.wallLaborPerHour = wallLaborPerHour;
    }

    public Integer getClosetLaborPerHour() {
        return closetLaborPerHour;
    }

    public void setClosetLaborPerHour(Integer closetLaborPerHour) {
        this.closetLaborPerHour = closetLaborPerHour;
    }

    public Integer getMantleLaborPerHour() {
        return mantleLaborPerHour;
    }

    public void setMantleLaborPerHour(Integer mantleLaborPerHour) {
        this.mantleLaborPerHour = mantleLaborPerHour;
    }

    public Integer getColumnLaborPerHour() {
        return columnLaborPerHour;
    }

    public void setColumnLaborPerHour(Integer columnLaborPerHour) {
        this.columnLaborPerHour = columnLaborPerHour;
    }

    public Double getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(Double profitRatio) {
        this.profitRatio = profitRatio;
    }
}
