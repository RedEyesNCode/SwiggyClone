package com.swiggy.swiggyClone.controllers;


import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.SignupResponse;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.dataModel.UsersJson;
import com.swiggy.swiggyClone.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
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






}
