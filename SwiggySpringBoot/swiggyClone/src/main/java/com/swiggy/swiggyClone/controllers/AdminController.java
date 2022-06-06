package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.adminModels.FoodItemBody;
import com.swiggy.swiggyClone.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

}
