package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.service.ApiService;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public LoginResponse loginUser(@Param("number") String number, @Param("password") String password){

        return new LoginResponse("success",200,"Login Successfully",apiService.loginUser(number, password).getId(),apiService.loginUser(number,password));
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

    //Api to get Home Feed Screen Response
    @GetMapping("/getFeed")
    public HomeFeedResponse getHomeFeed(){


        return apiService.getHomeFeed();
    }

    //Api to get past order of a user by UserId
    @PostMapping("/getUserPastOrders")
    public PastOrdersResponse getPastOrderOfUser(@Param("userId") int userId){

        List<PastOrders> pastOrders = apiService.getUserPastOrder(userId);

        if(pastOrders.size()==0){
            return new PastOrdersResponse("fail",200,"Record Not  Found",apiService.getUserPastOrder(userId));

        }else {
            return new PastOrdersResponse("success",200,"Record Found",apiService.getUserPastOrder(userId));

        }



    }
    //Getting the restaurant menu by Restaurant Id;
    @PostMapping("/getRestaurantMenu")
    public RestaurantMenuResponse getRestaurantMenuById(){



        return apiService.getRestaurantMenuByid();

    }

    @PostMapping("/getPastOrderDetail")
    public PastOrderDetailResponse getOrderDEtailsPAst(@Param("id") Long id){


        return  new PastOrderDetailResponse("success",200,"Record Found !",apiService.getPastOrderDetail(id));

    }

    @GetMapping("/getUserAddressById")
    public AddressUserResponse getUserAddressById(@Param("userId") Long id){

        if(apiService.getUserAddressByUserID(id).size()==0){


            return new AddressUserResponse("success",200,"Record Found !", apiService.getUserAddressByUserID(id));
        }else {


            return new AddressUserResponse("fail",200,"Record Not Found",new ArrayList<>());
        }

    }

    @PostMapping("/saveUserAddress")
    public StatusCodeModel saveUserAddressByID(@RequestBody AddressBody addressBody){



        return apiService.saveUserAddress(addressBody);

    }




    @ExceptionHandler(SignatureException.class)
    public StatusCodeModel HandlerException(){
        return new StatusCodeModel("fail",400,"Invalid TOken Signature");


    }




}
