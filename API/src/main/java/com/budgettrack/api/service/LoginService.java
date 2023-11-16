package com.budgettrack.api.service;

import com.budgettrack.api.DTO.ForgetPasswordReqDTO;
import com.budgettrack.api.DTO.LoginRequestDTO;
import com.budgettrack.api.DTO.SignUpRequestDTO;
import com.budgettrack.api.common.APIResponse;

public interface LoginService {
	
	APIResponse signUp(SignUpRequestDTO signUpRequestDTO);
	APIResponse login(LoginRequestDTO loginRequestDTO);
	APIResponse forgetPasswordService(ForgetPasswordReqDTO forgetPasswordReqDTO);

}