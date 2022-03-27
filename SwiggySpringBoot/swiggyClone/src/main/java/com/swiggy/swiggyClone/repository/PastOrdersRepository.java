package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.PastOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastOrdersRepository extends JpaRepository<PastOrders,Long> {



    @Query("SELECT s FROM PastOrders s WHERE s.userID = :userID")
    List<PastOrders> getUserPastOrders(int userID);


}
