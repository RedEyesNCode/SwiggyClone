package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetailTable,Long> {

    @Query("SELECT s FROM PaymentDetailTable s WHERE s.userId = :userId AND s.orderId = :inputOrderId")
    Optional<PaymentDetailTable> findByUserId(Long userId,Long inputOrderId);

}
