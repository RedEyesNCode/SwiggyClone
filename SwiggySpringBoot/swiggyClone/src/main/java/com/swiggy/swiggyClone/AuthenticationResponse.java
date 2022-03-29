package com.swiggy.swiggyClone;

import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;

public class AuthenticationResponse extends StatusCodeModel {
	
	private String _token;
	private SignupModel data;

	public AuthenticationResponse(String status, int code, String message, String jwt, SignupModel signupModel) {
		super(status, code, message);
		this._token = jwt;
		this.data = signupModel;
	}

	public AuthenticationResponse(String status, int code, String jwt, SignupModel signupModel) {
		super(status, code);
		this._token = jwt;
		this.data = signupModel;
	}

	public SignupModel getData() {
		return data;
	}

	public void setData(SignupModel data) {
		this.data = data;
	}


	public String get_token() {
		return _token;
	}

	public void set_token(String _token) {
		this._token = _token;
	}

}
