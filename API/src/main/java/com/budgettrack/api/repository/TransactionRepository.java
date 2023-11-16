package com.budgettrack.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.budgettrack.api.Entity.TransactionEntity;


public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	
	
}