package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.MenuItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemModel,Long> {
}
