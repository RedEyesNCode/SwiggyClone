package com.swiggy.swiggyClone.service;

import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {


	private UserDataRepository userDataRepository;


	@Autowired
	public MyUserDetails(UserDataRepository userDataRepository) {
		this.userDataRepository = userDataRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<SignupModel> signupModelOptional = userDataRepository.findbyNumber(username);
		if(signupModelOptional.isPresent()){
			SignupModel signupModel = signupModelOptional.get();
			return new User(signupModel.getNumber(), signupModel.getPassword(), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User Name Not Found");
		}


	}

	public UserDetailsDatabase loadByNumberAndPassword(String number, String password)throws UsernameNotFoundException{
		Optional<SignupModel> signupModelOptional = userDataRepository.loginUser(number, password);
		if(signupModelOptional.isPresent()){

			Optional<SignupModel> optionalSignupModel = userDataRepository.loginUserData(number,password);
			return new UserDetailsDatabase(optionalSignupModel.get().getNumber(),optionalSignupModel.get().getPassword(),optionalSignupModel.get());
		}else {
			throw new IllegalArgumentException();
		}



	}



}
