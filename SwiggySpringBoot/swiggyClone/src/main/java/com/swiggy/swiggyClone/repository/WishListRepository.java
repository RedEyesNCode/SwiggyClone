package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.WishListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;

@Repository
public interface WishListRepository extends JpaRepository<WishListModel,Long> {

    @Query("SELECT s FROM WishListModel s WHERE s.userId = :id")
    List<WishListModel> findByID(Long id);



    @Transactional
    @Modifying
    @Query(value = "DELETE FROM WishListModel s WHERE s.wishlistId = :inputWishlistId")
    void deleteWishlistById(Long inputWishlistId);

}
