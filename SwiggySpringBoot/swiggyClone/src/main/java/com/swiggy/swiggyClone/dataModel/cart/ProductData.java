package com.swiggy.swiggyClone.dataModel.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.swiggyClone.dataModel.commonProduct.AllProductTable;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductData implements Serializable {

    private Long productId;
    private AllProductTable productData;

    public ProductData(Long productId, AllProductTable productData) {
        this.productId = productId;
        this.productData = productData;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public AllProductTable getProductData() {
        return productData;
    }

    public void setProductData(AllProductTable productData) {
        this.productData = productData;
    }
}
