package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swiggy")
public class ApiController {


    private ApiService apiService;


    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }


    @GetMapping("/testapi")
    public StatusCodeModel testApi(){
        return new StatusCodeModel("Success",200,"Api working !");

    }

    //Api to get the login response from the db.
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginBody loginBody){

        return new LoginResponse("success",200,"Login Successfully !",apiService.loginUser(loginBody.getNumber(), loginBody.getPassword()));
    }



    //Api to store user data at the sign up screen
    @PostMapping("/signup")
    public SignupResponse postSignupUser(@RequestBody SignupModel signupModel){
        return  apiService.signupUser(signupModel);
    }

    @GetMapping("/getAllUsers")
    public UsersJson getAllUsers(){
        UsersJson usersJson =  new UsersJson();
        usersJson.setCode(200);
        usersJson.setStatus("success");
        usersJson.setUsers(apiService.getAllUsers());
        return usersJson;

    }

    //Api to fetch the user data on behalf of the id.
    @GetMapping("/getUser")
    public UserDataResponse getUser(@Param("userId") Long id){
        UserDataResponse userDataResponse = new UserDataResponse("success",200,"Found user",apiService.getUser(id));
        return userDataResponse;
    }
    //Update the user data according to the id
    @PostMapping("/updateUser")
    public StatusCodeModel updateUser(@Param("userId") Long id, @RequestBody SignupModel signupModel){

        return apiService.updateUser(id,signupModel.getUserName());

    }

    @GetMapping("/getWishList")
    public UserWishlistResponse getUserWishlist(@Param("userId") Long id){

        return new UserWishlistResponse("success",200,"Found Wishlist",apiService.getUserWishList(id));

    }
    //Api to fetch all the restaurant List in the DB.
    @GetMapping("/getRestaurantlist")
    public RestaurantListResponse getRestaurants(){
        return new RestaurantListResponse("success",200,"Restarants Found !",apiService.getRestaurants());
    }


    //Api to add a Restaurant to Wishlist by Id
    @PostMapping("/addtoWislist")
    public StatusCodeModel addToWislist(@Param("restaurantId") Long restaurantId, @Param("userId") Long userId){

        return apiService.addRestaurantToWishList(restaurantId, userId);


    }

    //Api to get Restaurant detail by Id.
    @PostMapping("/getRestaurantDetail")
    public RestaurantDetailResponse getRestaurantDetail(@Param("restaurantId") Long restaurantId){


        return new RestaurantDetailResponse("success",200,"Found Restaurant Detail",apiService.getRestaurantDetails(restaurantId));

    }












}
