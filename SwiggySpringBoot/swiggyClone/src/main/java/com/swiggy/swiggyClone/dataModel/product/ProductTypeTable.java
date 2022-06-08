package com.swiggy.swiggyClone.dataModel.product;


import javax.persistence.*;

@Entity
@Table
public class ProductTypeTable {

    @Id
    @SequenceGenerator(name = "product_type_sequence", sequenceName = "product_type_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_sequence")
    private Long productTypeId;

    private String productType;

    public ProductTypeTable() {
    }

    public ProductTypeTable(Long productTypeId, String productType) {
        this.productTypeId = productTypeId;
        this.productType = productType;
    }

    public ProductTypeTable(String productType) {
        this.productType = productType;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
