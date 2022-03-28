package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.DessertMenuItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DessertMenuRepository extends JpaRepository<DessertMenuItemModel,Long> {


}
