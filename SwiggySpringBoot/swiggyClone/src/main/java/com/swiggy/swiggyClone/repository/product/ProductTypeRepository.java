package com.swiggy.swiggyClone.repository.product;


import com.swiggy.swiggyClone.dataModel.product.ProductTypeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeTable,Long> {



}
