package com.budgettrack.api.service;


import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.budgettrack.api.DTO.TransactionRequestDTO;
import com.budgettrack.api.DTO.TransactionUpdateDTO;
import com.budgettrack.api.Entity.TransactionEntity;
import com.budgettrack.api.Entity.UserEntity;
import com.budgettrack.api.common.APIResponse;
import com.budgettrack.api.constants.CommonConstants;
import com.budgettrack.api.repository.TransactionRepository;



@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	LoginServiceImpl loginServiceImpl;

	/*
	 * API Name : saveincometransaction Request Param : TransactionRequestDTO Return
	 * : APIResponse
	 * 
	 * Description : In this api user can save the income data. Note : Based on
	 * endpoint URI the Transaction type manually selected in this api. Transaction
	 * type is enum in java as well as Mysql.
	 * 
	 */

	public APIResponse saveIncomeTransaction(TransactionRequestDTO transactionRequestDTO) {

		APIResponse apiResponse = new APIResponse();
		HashMap<String, Object> resultDataMap = new HashMap<>();
		TransactionEntity transactionEntity = new TransactionEntity();
		try {
			UserEntity user = loginServiceImpl.getUserById(transactionRequestDTO.getUserId());
			if (user == null) {
				// Handle the case whether the user is not found, return an appropriate error
				// response
				apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
				apiResponse.setError("User not found");
				return apiResponse;
			}
			transactionEntity.setUserEntity(user);

			transactionEntity.setTransactionDate(transactionRequestDTO.getTransactionDate());
			transactionEntity.setDescription(transactionRequestDTO.getDescription());
			transactionEntity.setAmount(transactionRequestDTO.getAmount());
			transactionEntity.setTransactionType(TransactionEntity.TransactionType.INCOME);
			transactionEntity.setCategory(transactionRequestDTO.getCategory());
			transactionEntity.setPaymentMethod(transactionRequestDTO.getPaymentMethod());
			transactionEntity.setReceiptUrl(transactionRequestDTO.getReceiptUrl());
			transactionEntity.setIsActive(true);
			transactionEntity = transactionRepository.save(transactionEntity);
			
			resultDataMap.put(CommonConstants.RESULT_STATUS, "Success");
			apiResponse.setData(resultDataMap);		

			return apiResponse;
			
		} catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return apiResponse;
		}
	}

	
	
	public APIResponse updateIncomeTransaction(TransactionUpdateDTO transactionUpdateDTO) {

		APIResponse apiResponse = new APIResponse();
		HashMap<String, Object> resultDataMap = new HashMap<>();
		try {
			UserEntity user = loginServiceImpl.getUserById(transactionUpdateDTO.getUserId());
			if (user == null) {
				// Handle the case where the user is not found, return an appropriate error
				// response
				apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
				apiResponse.setError("User not found");
				return apiResponse;
			}
			
			// check the transaction already available or not 
			Optional<TransactionEntity> checkTransactionEntity = transactionRepository.findById(transactionUpdateDTO.getTransactionId());
			
			if(checkTransactionEntity.isPresent())
			{
				TransactionEntity transactionEntity = checkTransactionEntity.get();
				transactionEntity.setUserEntity(user);
				transactionEntity.setTransactionDate(transactionUpdateDTO.getTransactionDate());
				transactionEntity.setDescription(transactionUpdateDTO.getDescription());
				transactionEntity.setAmount(transactionUpdateDTO.getAmount());
				transactionEntity.setTransactionType(TransactionEntity.TransactionType.INCOME);
				transactionEntity.setCategory(transactionUpdateDTO.getCategory());
				transactionEntity.setPaymentMethod(transactionUpdateDTO.getPaymentMethod());
				transactionEntity.setReceiptUrl(transactionUpdateDTO.getReceiptUrl());
				transactionEntity.setIsActive(true);
				transactionEntity = transactionRepository.save(transactionEntity);				
				resultDataMap.put(CommonConstants.RESULT_STATUS, "Success");
				apiResponse.setData(resultDataMap);		

			}
			else {
	            apiResponse.setError("Transaction not found with ID: " + transactionUpdateDTO.getTransactionId());
	        }
			
			return apiResponse;
			
		} catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return apiResponse;
		}
	}
	
	
	
	
	
	
	/*
	 * API Name : saveexpensetransaction
	 * Request Param : TransactionRequestDTO 
	 * Return : APIResponse
	 * 
	 * Description : In this api user can save the income data.
	 * Note : Based on endpoint URI the Transaction type manually selected in this api. 
	 * Transaction type is enum in java as well as Mysql.
	 * 
	 */

	public APIResponse saveExpenseTransaction(TransactionRequestDTO transactionRequestDTO) {

		APIResponse apiResponse = new APIResponse();
		HashMap<String, Object> resultDataMap = new HashMap<>();
		TransactionEntity transactionEntity = new TransactionEntity();
		try {
			UserEntity user = loginServiceImpl.getUserById(transactionRequestDTO.getUserId());
			if (user == null) {
				// Handle the case where the user is not found, return an appropriate error
				// response
				apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
				apiResponse.setError("User not found");
				return apiResponse;
			}
			transactionEntity.setUserEntity(user);

			transactionEntity.setTransactionDate(transactionRequestDTO.getTransactionDate());
			transactionEntity.setDescription(transactionRequestDTO.getDescription());
			transactionEntity.setAmount(transactionRequestDTO.getAmount());
			transactionEntity.setTransactionType(TransactionEntity.TransactionType.EXPENSE);
			transactionEntity.setCategory(transactionRequestDTO.getCategory());
			transactionEntity.setPaymentMethod(transactionRequestDTO.getPaymentMethod());
			transactionEntity.setReceiptUrl(transactionRequestDTO.getReceiptUrl());
			transactionEntity.setIsActive(true);
			transactionEntity = transactionRepository.save(transactionEntity);
			resultDataMap.put(CommonConstants.RESULT_STATUS, "Success");

			apiResponse.setData(resultDataMap);		

			return apiResponse;

		} catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return apiResponse;
		}
	}
	
	
	public APIResponse updateExpenseTransaction(TransactionUpdateDTO transactionUpdateDTO) {

		APIResponse apiResponse = new APIResponse();
		HashMap<String, Object> resultDataMap = new HashMap<>();
		try {
			UserEntity user = loginServiceImpl.getUserById(transactionUpdateDTO.getUserId());
			if (user == null) {
				// Handle the case where the user is not found, return an appropriate error
				// response
				apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
				apiResponse.setError("User not found");
				return apiResponse;
			}
			
			// check the transaction already available or not 
			Optional<TransactionEntity> checkTransactionEntity = transactionRepository.findById(transactionUpdateDTO.getTransactionId());
			
			if(checkTransactionEntity.isPresent())
			{
				TransactionEntity transactionEntity = checkTransactionEntity.get();
				transactionEntity.setUserEntity(user);
				transactionEntity.setTransactionDate(transactionUpdateDTO.getTransactionDate());
				transactionEntity.setDescription(transactionUpdateDTO.getDescription());
				transactionEntity.setAmount(transactionUpdateDTO.getAmount());
				transactionEntity.setTransactionType(TransactionEntity.TransactionType.EXPENSE);
				transactionEntity.setCategory(transactionUpdateDTO.getCategory());
				transactionEntity.setPaymentMethod(transactionUpdateDTO.getPaymentMethod());
				transactionEntity.setReceiptUrl(transactionUpdateDTO.getReceiptUrl());
				transactionEntity.setIsActive(true);
				transactionEntity = transactionRepository.save(transactionEntity);				
				resultDataMap.put(CommonConstants.RESULT_STATUS, "Success");
				apiResponse.setData(resultDataMap);		

			}
			else {
	            apiResponse.setError("Transaction not found with ID: " + transactionUpdateDTO.getTransactionId());
	        }
			
			return apiResponse;
			
		} catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return apiResponse;
		}
	}
}