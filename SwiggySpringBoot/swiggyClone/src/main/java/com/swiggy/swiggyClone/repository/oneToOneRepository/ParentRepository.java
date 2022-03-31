package com.swiggy.swiggyClone.repository.oneToOneRepository;


import com.swiggy.swiggyClone.dataModel.oneToOneRelation.ParentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<ParentTable,Long> {



}
