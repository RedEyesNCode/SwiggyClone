package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.offers.OfferTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepostiory extends JpaRepository<OfferTable,Long> {

}
