package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.RestaurantsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantsTable,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE RestaurantsTable s SET s.restaurantImage = :completeFileName WHERE s.restaurantId = :id")
    int updateFoodImage(String completeFileName,Long id);


}
