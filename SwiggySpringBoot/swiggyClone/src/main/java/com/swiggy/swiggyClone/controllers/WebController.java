package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.RestaurantsTable;
import com.swiggy.swiggyClone.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;


@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    AdminService service;


    @RequestMapping("/")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    @RequestMapping("/restaurantlist")
    public ModelAndView restaurantList(){

        ModelAndView model =new ModelAndView();
        List<RestaurantsTable> courseList = service.getAllRestaurants();
        model.addObject("restaurantDBList",courseList);
        model.setViewName("restaurantlist");
        return model;
    }
    @RequestMapping("/addRestaurantForm")
    public ModelAndView addRestaurantForm(){
        RestaurantsTable restaurantsTable = new RestaurantsTable();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("restaurantDetail",restaurantsTable);
        modelAndView.setViewName("addRestaurantForm");
        return modelAndView;
    }
    @PostMapping("/addRestaurant")
    public ModelAndView addRestaurant(@ModelAttribute("restaurantDetail") RestaurantsTable restaurantsTable){

        service.addRestaurantWeb(restaurantsTable);

        return new ModelAndView("redirect:/web/restaurantlist");
    }
    @PostMapping("uploadRestaurantImage")
    public ModelAndView uploadRestaurantImage(@RequestParam("restaurant_image") MultipartFile file,@RequestParam("restaurantid") Long restaurantid){
        if(file==null){
            throw new RuntimeException();
        }else {
            service.uploadRestaurantImage(file, restaurantid);
            return new ModelAndView("redirect:/web/restaurantlist");

        }





    }

}
