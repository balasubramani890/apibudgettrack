package com.budgettrack.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgettrack.api.DTO.ForgetPasswordReqDTO;
import com.budgettrack.api.DTO.LoginRequestDTO;
import com.budgettrack.api.DTO.SignUpRequestDTO;
import com.budgettrack.api.common.APIResponse;
import com.budgettrack.api.service.LoginServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("api")
public class LoginController {

	@Autowired
	private LoginServiceImpl loginServiceImpl;

	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		try {

			apiResponse = loginServiceImpl.signUp(signUpRequestDTO);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

		catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		try {

			apiResponse = loginServiceImpl.login(loginRequestDTO);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		
		catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
	}

	@PostMapping("/forgetpassword")
	public ResponseEntity<APIResponse> forgetpassword(@RequestBody ForgetPasswordReqDTO forgetPasswordReqDTO) {
		APIResponse apiResponse = new APIResponse();
		try {

			apiResponse = loginServiceImpl.forgetPasswordService(forgetPasswordReqDTO);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		
		catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

	}

}