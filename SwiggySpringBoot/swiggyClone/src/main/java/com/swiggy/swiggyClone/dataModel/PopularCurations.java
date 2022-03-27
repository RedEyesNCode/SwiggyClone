package com.swiggy.swiggyClone.dataModel;

import javax.persistence.*;

@Entity
@Table
public class PopularCurations {

    @Id
    @SequenceGenerator(name = "popular_brand_sequence", sequenceName = "popular_brand_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "popular_brand_sequence")
    private Long curationId;

    private String cuisineName;


    public PopularCurations() {
    }

    public PopularCurations(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public Long getCurationId() {
        return curationId;
    }

    public void setCurationId(Long curationId) {
        this.curationId = curationId;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }
}
