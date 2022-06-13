package com.swiggy.swiggyClone.repository.orderRepository;

import com.swiggy.swiggyClone.dataModel.placeOrder.RealtimeOrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RealtimeOrderRepository extends JpaRepository<RealtimeOrderTable,Long> {


    // Query for updating order status.

    @Transactional
    @Modifying
    @Query("UPDATE RealtimeOrderTable s SET s.orderStatus = :orderStatusInput WHERE s.placeOrderid = :id")
    int updateOrderStatus(String orderStatusInput,Long id);


}
