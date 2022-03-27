package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.GeneralOffers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffersRespository extends JpaRepository<GeneralOffers,Long> {
}
