package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetailTable,Long> {

    @Query("SELECT s FROM PaymentDetailTable s WHERE s.userId = :userId AND s.orderId = :inputOrderId")
    Optional<PaymentDetailTable> findByUserId(Long userId,Long inputOrderId);
    @Transactional
    @Modifying
    @Query("UPDATE PaymentDetailTable s SET s.amount = :newAmount WHERE s.orderId = :inputOrderId")
    int applyPromocode(Long inputOrderId,Double newAmount);

    @Query("SELECT s FROM PaymentDetailTable s WHERE s.orderId = :inputOrderId")
    Optional<PaymentDetailTable> findByOrderId(Long inputOrderId);

}
