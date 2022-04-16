package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel,Long> {

    @Query("SELECT s FROM WishListModel s WHERE s.userId = :id")
    List<WishListModel> findByID(int id);
}
