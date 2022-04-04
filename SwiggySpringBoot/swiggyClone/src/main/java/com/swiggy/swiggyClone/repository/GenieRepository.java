package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.SwiggiGenieTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenieRepository extends JpaRepository<SwiggiGenieTable,Long> {


}
