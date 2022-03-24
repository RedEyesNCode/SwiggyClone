package com.swiggy.swiggyClone.dataModel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;


//Using the Json Ignore Properties beacuse we need to update this table upon insert in another table
@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class WishListModel {

    @Id
    @SequenceGenerator(name = "wishlist_data_sequence", sequenceName = "wishlist_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_data_sequence")
    private Long id;



    private boolean isThereWishList;

    public WishListModel(Long id, boolean isThereWishList) {
        this.id = id;
        this.isThereWishList = isThereWishList;
    }

    public WishListModel(boolean isThereWishList) {
        this.isThereWishList = isThereWishList;
    }

    public WishListModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isThereWishList() {
        return isThereWishList;
    }

    public void setThereWishList(boolean thereWishList) {
        isThereWishList = thereWishList;
    }
}
