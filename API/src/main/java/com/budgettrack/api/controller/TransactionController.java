package com.budgettrack.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgettrack.api.DTO.RequestMeta;
import com.budgettrack.api.DTO.TransactionRequestDTO;
import com.budgettrack.api.DTO.TransactionUpdateDTO;
import com.budgettrack.api.common.APIResponse;
import com.budgettrack.api.service.TransactionService;


@RestController
@CrossOrigin
@RequestMapping("api")
public class TransactionController {

	private final RequestMeta requestMeta;

	@Autowired
	TransactionService transactionService;

	public TransactionController(RequestMeta requestMeta) {
		this.requestMeta = requestMeta;
	}

	@PostMapping("/saveincometransaction")
	public ResponseEntity<APIResponse> saveIncomeTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
		System.out.println("TransactionController Class : saveIncomeTransaction Method : Started");
		APIResponse apiResponse = new APIResponse();
		transactionRequestDTO.setUserId(requestMeta.getUserId());
		try {
			apiResponse = transactionService.saveIncomeTransaction(transactionRequestDTO);

			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

		catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

	}
	
	
	

	@PostMapping("/updateincometransaction")
	public ResponseEntity<APIResponse> updateIncomeTransaction(@RequestBody TransactionUpdateDTO transactionUpdateDTO) {
		APIResponse apiResponse = new APIResponse();
		transactionUpdateDTO.setUserId(requestMeta.getUserId());
		try {
			apiResponse = transactionService.updateIncomeTransaction(transactionUpdateDTO);

			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

		catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

	}
	
		
	
	@PostMapping("/saveexpensetransaction")
	public ResponseEntity<APIResponse> saveExpenseTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
		APIResponse apiResponse = new APIResponse();
		transactionRequestDTO.setUserId(requestMeta.getUserId());
		try {
			apiResponse = transactionService.saveExpenseTransaction(transactionRequestDTO);

			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

		catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

	}
	
	
	@PostMapping("/updateexpensetransaction")
	public ResponseEntity<APIResponse> updateExpenseTransaction(@RequestBody TransactionUpdateDTO transactionUpdateDTO) {
		APIResponse apiResponse = new APIResponse();
		transactionUpdateDTO.setUserId(requestMeta.getUserId());
		try {
			apiResponse = transactionService.updateExpenseTransaction(transactionUpdateDTO);

			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

		catch (Exception e) {
			apiResponse.setError(e.getMessage());
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
	}
	


}