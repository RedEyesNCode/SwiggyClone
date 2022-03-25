package com.swiggy.swiggyClone.service;


import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.repository.RestaurantDetailRepository;
import com.swiggy.swiggyClone.repository.RestaurantRepository;
import com.swiggy.swiggyClone.repository.UserDataRepository;
import com.swiggy.swiggyClone.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class ApiService {

    private UserDataRepository userDataRepository;
    private WishListRepository wishListRepository;
    private RestaurantRepository restaurantRepository;
    private RestaurantDetailRepository restaurantDetailRepository;




    @Autowired
    public ApiService(UserDataRepository userDataRepository, WishListRepository wishListRepository, RestaurantRepository restaurantRepository,
                      RestaurantDetailRepository restaurantDetailRepository) {
        this.userDataRepository = userDataRepository;
        this.wishListRepository = wishListRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantDetailRepository = restaurantDetailRepository;

    }





    //Get User Details by ID
    public SignupModel getUserDetails(int id){
        return userDataRepository.getById(Long.parseLong(String.valueOf(id)));
    }


    //Store the user data.
    public SignupResponse signupUser(SignupModel signupModel){

        Optional<SignupModel> signupModelOptional = userDataRepository.findbyEmail(signupModel.getUserEmail());
        Optional<SignupModel> signupModelOptionalPassword = userDataRepository.findbyPassword(signupModel.getNumber());


        if(signupModelOptional.isPresent() || signupModelOptionalPassword.isPresent()){
            return new SignupResponse(400,"fail","Already Registered user",0);
        }else {
            userDataRepository.save(signupModel);
            wishListRepository.save(new WishListModel(false));
            List<SignupModel> users = userDataRepository.findAll();
            Long id = 0L;
            for (int i=0;i<users.size();i++){
                if(users.get(i).getUserEmail().contains(signupModel.getUserEmail()) && users.get(i).getNumber().contains(signupModel.getNumber())){
                    id = users.get(i).getId();
                }
            }
            return new SignupResponse(200,"success","Registered Successfully",Integer.parseInt(String.valueOf(id)));

        }
    }

    //Get the user Wishlist.
    public List<WishListModel> getUserWishList(Long id){

        return wishListRepository.findByID(id);

    }



    //Fetching all the stored users.
    public List<SignupModel> getAllUsers(){

        return userDataRepository.findAll();
    }
    //Fetching the user by ID
    public SignupModel getUser(Long id){
        Optional<SignupModel> signupModelOptional= userDataRepository.findByID(id);
        if(signupModelOptional.isPresent()){

            return userDataRepository.getById(id);

        }else {
            throw new IllegalArgumentException();
        }

    }
    //Update the user according to id
    public StatusCodeModel updateUser(Long id,String userName){
        boolean isFound = userDataRepository.existsById(id);

        if(isFound){
            userDataRepository.updateUserNamebyId(userName,id);
            return new StatusCodeModel("success",200,"Updated the username successfully");
        }else {
            return new StatusCodeModel("fail",400,"No user exists by such id");
        }
    }

    //Fetching all the restaurant list from the database.
    public List<RestaurantsTable> getRestaurants()
    {
        return restaurantRepository.findAll();

    }
    //Add a restuarant to wishlist by id of restaurant
    public StatusCodeModel addRestaurantToWishList(Long restaurantID, Long userID){


        //1. Fetch the restaurant data from the r-table. and insert it into w-table.
        RestaurantsTable restaurantsTable = restaurantRepository.getById(restaurantID);

        WishListModel wishListModel = new WishListModel(userID,restaurantID,true,restaurantsTable.getRestaurantName(),restaurantsTable.getRating(),restaurantsTable.getDeliveryTime(),restaurantsTable.getDiscount(),restaurantsTable.isSwiggyOne());

        wishListRepository.save(wishListModel);
        return new StatusCodeModel("success",200,"Successfully added to Wishlist !");
    }

    //Getting the restaurant detail by id.
    public RestaurantDetailTable getRestaurantDetails(Long id){

        return restaurantDetailRepository.getById(id);

    }

    //Login the user
    public SignupModel loginUser(String number, String password){

        Optional<SignupModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if(signupModelOptional.isPresent()){

            return userDataRepository.loginUserData(number,password);
        }else {
            throw new IllegalArgumentException();
        }
    }







}
