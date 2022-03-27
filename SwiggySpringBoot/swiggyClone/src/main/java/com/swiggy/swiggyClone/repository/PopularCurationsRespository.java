package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.PopularCurations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularCurationsRespository extends JpaRepository<PopularCurations,Long> {



}
