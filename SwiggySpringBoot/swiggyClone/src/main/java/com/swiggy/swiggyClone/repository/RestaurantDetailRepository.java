package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.RestaurantDetailTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantDetailRepository extends JpaRepository<RestaurantDetailTable,Long> {

}
