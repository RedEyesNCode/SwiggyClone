package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.Allergens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergensRepo extends JpaRepository<Allergens,Long> {
}
