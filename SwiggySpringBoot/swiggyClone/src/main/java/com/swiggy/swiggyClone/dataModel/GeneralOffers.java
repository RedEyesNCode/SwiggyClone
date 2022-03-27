package com.swiggy.swiggyClone.dataModel;

import javax.persistence.*;


@Entity
@Table
public class GeneralOffers {


    @Id
    @SequenceGenerator(name = "popular_brand_sequence", sequenceName = "popular_brand_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "popular_brand_sequence")
    private Long offerId;


    private Double discount;
    private String couponCode;

    public GeneralOffers(Double discount, String couponCode) {
        this.discount = discount;
        this.couponCode = couponCode;
    }

    public GeneralOffers(Long offerId, Double discount, String couponCode) {
        this.offerId = offerId;
        this.discount = discount;
        this.couponCode = couponCode;
    }

    public GeneralOffers() {
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
