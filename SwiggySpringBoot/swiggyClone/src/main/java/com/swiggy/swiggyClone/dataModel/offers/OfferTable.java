package com.swiggy.swiggyClone.dataModel.offers;


import javax.persistence.*;

@Entity
@Table
public class OfferTable {
    @Id
    @SequenceGenerator(name = "offers_data_sequence", sequenceName = "offers_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offers_data_sequence")
    private Long id;

    private String name;

    private Double discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public OfferTable(Long id, String name, Double discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    public OfferTable(String name, Double discount) {
        this.name = name;
        this.discount = discount;
    }

    public OfferTable() {
    }
}
