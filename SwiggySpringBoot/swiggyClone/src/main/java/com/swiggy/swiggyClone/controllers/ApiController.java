package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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






}
