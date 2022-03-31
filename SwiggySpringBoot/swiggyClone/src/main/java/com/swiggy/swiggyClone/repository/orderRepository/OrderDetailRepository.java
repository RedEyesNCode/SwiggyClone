package com.swiggy.swiggyClone.repository.orderRepository;


import com.swiggy.swiggyClone.dataModel.cart.OrderDetailTable;
import com.swiggy.swiggyClone.dataModel.cart.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailTable,Long> {
    @Query("SELECT s FROM OrderDetailTable s WHERE s.orderId = :id")
    OrderDetailTable findOrderDetailsByOrderId(Long id);


}
