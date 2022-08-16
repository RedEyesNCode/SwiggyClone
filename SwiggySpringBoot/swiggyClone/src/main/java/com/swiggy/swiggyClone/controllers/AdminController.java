package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.dataModel.adminModels.FoodItemBody;
import com.swiggy.swiggyClone.dataModel.adminModels.RestaurantBody;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;
import com.swiggy.swiggyClone.dataModel.placeOrder.RealtimeOrderTable;
import com.swiggy.swiggyClone.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("addFoodItem")
    public ResponseEntity<?> addFoodItem(@RequestBody FoodItemBody foodItemBody){
        return  adminService.insertMenuItem(foodItemBody);

    }
    @PostMapping("uploadFoodItemImage")
    public ResponseEntity<?> uploadFoodImage(@RequestParam("food_image") MultipartFile file,@RequestParam("menuId") Long menuId){
        return  adminService.uploadFoodImage(file,menuId);
    }

    @PostMapping("addRestaurant")
    public ResponseEntity<?> addRestaurant(@RequestBody RestaurantBody restaurantBody){
        return adminService.addRestaurant(restaurantBody);
    }

    @PostMapping("uploadRestaurantImage")
    public ResponseEntity<?> uploadRestaurantImage(@RequestParam("restaurant_image") MultipartFile file, @RequestParam("restaurantId") Long restaurantId){

        return adminService.uploadRestaurantImage(file, restaurantId);

    }

    @GetMapping("/getAllOrders")
    public List<RealtimeOrderTable> getAllOrders(){
        return  adminService.getAllOrders();
    }

    @PostMapping("/updateOrderStatus")
    public StatusCodeModel updateOrderStatus(@RequestBody HashMap<String,String> orderUpdateBody){
        return adminService.updateOrderStatus(orderUpdateBody.get("orderStatus"), orderUpdateBody.get("placeOrderid"));
    }



}
