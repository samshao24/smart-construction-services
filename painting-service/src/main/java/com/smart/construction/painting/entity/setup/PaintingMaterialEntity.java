package com.smart.construction.painting.entity.setup;

import com.smart.construction.painting.entity.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "painting_material")
public class PaintingMaterialEntity extends Auditable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="painting_material_seq")
    @SequenceGenerator(
            name="painting_material_seq",
            sequenceName="painting_material_seq",
            initialValue = 1000)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "quality")
    private String quality;

    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "quotePrice")
    private Double quotePrice;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getQuotePrice() {
        return quotePrice;
    }

    public void setQuotePrice(Double quotePrice) {
        this.quotePrice = quotePrice;
    }
}
