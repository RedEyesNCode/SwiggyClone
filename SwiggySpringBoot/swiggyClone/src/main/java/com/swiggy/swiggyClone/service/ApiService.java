package com.swiggy.swiggyClone.service;


import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.SignupResponse;
import com.swiggy.swiggyClone.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class ApiService {

    private UserDataRepository userDataRepository;

    @Autowired
    public ApiService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
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

    //Fetching all the stored users.
    public List<SignupModel> getAllUsers(){

        return userDataRepository.findAll();


    }




}
