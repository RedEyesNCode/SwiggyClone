package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.commonProduct.AllProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllProductsRepository extends JpaRepository<AllProductTable,Long> {


}
