package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.PopularBrands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularBrandsRepository extends JpaRepository<PopularBrands,Long> {
}
