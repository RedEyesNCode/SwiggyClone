package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel,Long> {

}
