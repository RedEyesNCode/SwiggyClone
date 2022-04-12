package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.address.AddressTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressTable,Long> {


    @Query("SELECT s FROM AddressTable s WHERE s.userId = :userId")
    List<AddressTable> findByUserId(int userId);
}
