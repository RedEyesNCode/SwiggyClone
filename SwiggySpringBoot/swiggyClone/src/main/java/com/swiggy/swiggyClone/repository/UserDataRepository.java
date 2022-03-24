package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.SignupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<SignupModel, Long> {


    @Query("SELECT s FROM SignupModel s WHERE s.userEmail = ?1")
    Optional<SignupModel> findbyEmail(String email);

    @Query("SELECT s FROM SignupModel s WHERE s.number = ?1")
    Optional<SignupModel> findbyPassword(String Number);




}
