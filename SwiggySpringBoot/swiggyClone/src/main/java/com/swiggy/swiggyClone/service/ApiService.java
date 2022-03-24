package com.swiggy.swiggyClone.service;


import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.SignupResponse;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.dataModel.WishListModel;
import com.swiggy.swiggyClone.repository.UserDataRepository;
import com.swiggy.swiggyClone.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class ApiService {

    private UserDataRepository userDataRepository;
    private WishListRepository wishListRepository;


    @Autowired
    public ApiService(UserDataRepository userDataRepository, WishListRepository wishListRepository) {
        this.userDataRepository = userDataRepository;
        this.wishListRepository = wishListRepository;

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
    public WishListModel getUserWishList(Long id){
        if (wishListRepository.existsById(id)) {

            return wishListRepository.getById(id);
        }else {
            throw new IllegalArgumentException();
        }


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






}
