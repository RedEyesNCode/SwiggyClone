package com.swiggy.swiggyClone.repository;

import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetailTable,Long> {
}
