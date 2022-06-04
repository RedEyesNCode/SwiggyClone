package com.swiggy.swiggyClone.repository.orderRepository;


import com.swiggy.swiggyClone.dataModel.cart.OrderDetailTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailTable,Long> {
    @Query("SELECT s FROM OrderDetailTable s WHERE s.orderId = :id")
    OrderDetailTable findOrderDetailsByOrderId(Long id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM OrderDetailTable s WHERE s.orderId = :inputOrderId")
    void deleteByOrderID(Long inputOrderId);



}
