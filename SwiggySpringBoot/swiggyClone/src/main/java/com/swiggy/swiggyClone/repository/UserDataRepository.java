package com.swiggy.swiggyClone.repository;


import com.swiggy.swiggyClone.dataModel.SignupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<SignupModel, Long> {


    @Query("SELECT s FROM SignupModel s WHERE s.userEmail = ?1")
    Optional<SignupModel> findbyEmail(String email);
    //?1 implies not equal to in JPQL QUery
    @Query("SELECT s FROM SignupModel s WHERE s.number = ?1")
    Optional<SignupModel> findbyNumber(String Number);
    //USE PARAM TO MATCH WITH THE DATABASE or :ID
    //Below is the query to search by id in the db by sending param in the request.
    @Query("SELECT s FROM SignupModel s WHERE s.id = :id")
    Optional<SignupModel> findByID( Long id);
    //Update the user details on behalf of the id.


    //For the update query 3 annotations are nesscary
    @Transactional
    @Modifying
    @Query("UPDATE SignupModel s SET s.userName = :userNameQuery WHERE s.id = :id")
    int updateUserNamebyId(String userNameQuery, Long id);

    @Transactional
    @Modifying
    @Query("UPDATE SignupModel s SET s.profileUrl = :completeFileName WHERE s.id = :id")
    int updateUserProfileImage(String completeFileName,Long id);


    //Query to check if the User Exists in the database or not.
    @Query("SELECT s FROM SignupModel s WHERE s.number =:number AND s.password=:password")
    Optional<SignupModel> loginUser(String number, String password);



    @Query("SELECT s FROM SignupModel s WHERE s.number =:number AND s.password=:password")
    Optional<SignupModel> loginUserData(String number, String password);


}
