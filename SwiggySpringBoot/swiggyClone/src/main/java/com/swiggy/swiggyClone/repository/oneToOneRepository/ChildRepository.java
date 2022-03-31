package com.swiggy.swiggyClone.repository.oneToOneRepository;


import com.swiggy.swiggyClone.dataModel.oneToOneRelation.ChildTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<ChildTable,Long> {
}
