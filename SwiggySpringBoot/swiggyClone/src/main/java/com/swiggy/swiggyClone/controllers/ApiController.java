package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.dataModel.address.AddressBody;
import com.swiggy.swiggyClone.dataModel.address.AddressUserResponse;
import com.swiggy.swiggyClone.dataModel.cart.CartResponse;
import com.swiggy.swiggyClone.dataModel.cart.OrderInsertBody;
import com.swiggy.swiggyClone.dataModel.oneToOneRelation.ChildTable;
import com.swiggy.swiggyClone.dataModel.oneToOneRelation.ParentTable;
import com.swiggy.swiggyClone.exceptionHandling.ForbiddenException;
import com.swiggy.swiggyClone.service.ApiService;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/swiggy")
public class ApiController {


    private ApiService apiService;
    //YOU CAN ONLY HANDLE CONTROLLER EXCEPTIONS BY A COMMON EXCEPTION HANDLER CLASS

    //PLEASE USE THE REQUEST PARAM IN INTEGER AND ALSO KEEP THE key and Request Parameter same name
    // ex - @Param("userId) int userId


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

        SignupModel signupModel = apiService.loginUser(number, password);
        if(signupModel.getUserName().isEmpty()){
            //YOU CAN ONLY HANDLE CONTROLLER EXCEPTIONS BY A COMMON EXCEPTION HANDLER CLASS

            throw new ForbiddenException("User Not Found");
        }else {
            return new LoginResponse("success",200,"Login Successfully",apiService.loginUser(number, password).getId(),apiService.loginUser(number,password));

        }



    }



    //Api to store user data at the sign up screen
    @PostMapping("/signup")
    public SignupResponse postSignupUser(@RequestBody SignupModel signupModel){
        return  apiService.signupUser(signupModel);
    }

    @GetMapping("/getAllUsers")
    public UsersJson getAllUsers() throws ForbiddenException{
        UsersJson usersJson =  new UsersJson();
        usersJson.setCode(200);
        usersJson.setStatus("success");
        try{
            usersJson.setUsers(apiService.getAllUsers());
            return usersJson;
        }catch (Exception e){
            throw new ForbiddenException("Invalid Token");

        }


    }

    //Api to fetch the user data on behalf of the id.
    @GetMapping("/getUser")
    public UserDataResponse getUser(@Param("userId") int id){
        SignupModel userDataResponseService = apiService.getUser(id);
        if(userDataResponseService.getUserName().isEmpty()){
            throw new ForbiddenException("No User Found !");
        }else {
            UserDataResponse userDataResponse = new UserDataResponse("success",200,"Found user",apiService.getUser(id));
            return userDataResponse;
        }



    }
    //Update the user data according to the id
    @PostMapping("/updateUser")
    public StatusCodeModel updateUser(@Param("userId") Long id, @RequestBody SignupModel signupModel){

        return apiService.updateUser(id,signupModel.getUserName());

    }

    @GetMapping("/getWishList")
    public UserWishlistResponse getUserWishlist(@Param("userId") int id){

        return new UserWishlistResponse("success",200,"Found Wishlist",apiService.getUserWishList(id));

    }
    //Api to fetch all the restaurant List in the DB.
    @GetMapping("/getRestaurantlist")
    public RestaurantListResponse getRestaurants(){
        return new RestaurantListResponse("success",200,"Restarants Found !",apiService.getRestaurants());
    }


    //Api to add a Restaurant to Wishlist by Id
    @PostMapping("/addtoWislist")
    public StatusCodeModel addToWislist(@Param("restaurantId") int restaurantId, @Param("userId") int userId){

        return apiService.addRestaurantToWishList(restaurantId, userId);


    }

    //Api to get Restaurant detail by Id.
    @PostMapping("/getRestaurantDetail")
    public RestaurantDetailResponse getRestaurantDetail(@Param("restaurantId") int restaurantId){


        return new RestaurantDetailResponse("success",200,"Found Restaurant Detail",apiService.getRestaurantDetails(restaurantId));

    }

    //Api to get Home Feed Screen Response
    @GetMapping("/getFeed")
    public HomeFeedResponse getHomeFeed()throws SignatureException{


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
    public PastOrderDetailResponse getOrderDEtailsPAst(@Param("id") int id){


        return  new PastOrderDetailResponse("success",200,"Record Found !",apiService.getPastOrderDetail(id));

    }

    @GetMapping("/getUserAddressById")
    public AddressUserResponse getUserAddressById(@Param("userId") int userId){


        if(apiService.getUserAddressByUserID(userId).size()==0){


            return new AddressUserResponse("fail",200,"Record Not Found !", new ArrayList<>());
        }else {


            return new AddressUserResponse("success",200,"Record Found",apiService.getUserAddressByUserID(userId));
        }

    }
    @PostMapping("/saveUserAddress")
    public StatusCodeModel saveUserAddressByID(@RequestBody AddressBody addressBody){
        return apiService.saveUserAddress(addressBody);

    }

    @GetMapping("/403")
    public String error(){
        return "Error 403";
    }


    @GetMapping("/getParentTable")
    public List<ParentTable> getAllParentTables(){
        return apiService.getParentTables();
    }
    @GetMapping("/getChildTables")
    public List<ChildTable> getAllChildTables(){

        return apiService.getChildTables();
    }

    @PostMapping("/addtoCart")
    public StatusCodeModel addToCart(@RequestBody OrderInsertBody orderInsertBody){


        return apiService.addtoCart(orderInsertBody);


    }

    @PostMapping("/deleteCart")
    public StatusCodeModel deleteCartItem(@Param("orderId") Long orderId,@Param("userId") Long userId){


        return apiService.deleteCartItem(orderId,userId);

    }


    //Api to get cart information with the id of the user.
    @GetMapping("/getCart")
    public ResponseEntity<?> getCart(@Param("userId") Long userId){
        return apiService.getUserCart(userId);
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProduct(){


        return apiService.getAllProducts();

    }

    //APi to get basic swiggy genie service.
    @GetMapping("/genie")
    public ResponseEntity<?> getBasicSwiggyGenieProducts(){

        return apiService.getBasicGenieResponse();

    }

    // API TO UPLOAD USER PROFILE IMAGE
    @PostMapping("/uploadProfilePic")
    public ResponseEntity<StatusCodeModel> uploadProfileImage(@RequestParam(value = "avatar") MultipartFile file,@Param("userId") Long userId){
        return apiService.uploadFile(file, userId);
    }


    @PostMapping("/deleteWishlist")
    public StatusCodeModel deleteUserWishlist(@Param("wishListId") Long wishlistId){


        return  apiService.deleteWishlist(wishlistId);

    }














}
