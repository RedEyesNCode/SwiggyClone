package com.swiggy.swiggyClone.dataModel;


import javax.persistence.*;

@Entity
@Table
public class SwiggiGenieTable {

    @Id
    @SequenceGenerator(name = "genie_sequence_table", sequenceName = "genie_sequence_table",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genie_sequence_table")
    private Long genieId;

    private String shopName;
    private String shopType;
    private String distance;

    public SwiggiGenieTable() {
    }

    public SwiggiGenieTable(Long genieId, String shopName, String shopType, String distance) {
        this.genieId = genieId;
        this.shopName = shopName;
        this.shopType = shopType;
        this.distance = distance;
    }

    public SwiggiGenieTable(String shopName, String shopType, String distance) {
        this.shopName = shopName;
        this.shopType = shopType;
        this.distance = distance;
    }

    public Long getGenieId() {
        return genieId;
    }

    public void setGenieId(Long genieId) {
        this.genieId = genieId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
