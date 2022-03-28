package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.SnacksMenuItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnacksMenuRepository extends JpaRepository<SnacksMenuItemModel,Long> {

}
