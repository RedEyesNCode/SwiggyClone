package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.commonProduct.AllProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AllProductsRepository extends JpaRepository<AllProductTable,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AllProductTable s SET s.productImage = :completeFileName WHERE s.menuId = :id")
    int updateFoodImage(String completeFileName,Long id);


}
