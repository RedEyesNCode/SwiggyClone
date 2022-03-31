package com.swiggy.swiggyClone.repository.orderRepository;


import com.swiggy.swiggyClone.dataModel.WishListModel;
import com.swiggy.swiggyClone.dataModel.cart.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderTable,Long> {
    @Query("SELECT s FROM OrderTable s WHERE s.userId = :id")
    List<OrderTable> findAllOrderForUserId(Long id);

}
