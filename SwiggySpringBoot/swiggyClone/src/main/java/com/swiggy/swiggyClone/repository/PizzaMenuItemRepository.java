package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.PizzaMenuItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaMenuItemRepository extends JpaRepository<PizzaMenuItemModel,Long> {
}
