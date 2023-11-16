package com.budgettrack.api.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

	public enum TransactionType {
		INCOME, EXPENSE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id") // Specify the referenced column in the User table
	private UserEntity userEntity;

	@Column(name = "user_id",insertable=false, updatable=false)
	private Long userId;

	@Column(name = "transaction_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private String transactionDate;

	@Column(name = "description")
	private String description;

	@Column(name = "amount")
	private BigDecimal amount;

	
//	Income or Expense mention this column	
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type")
	private TransactionType transactionType;

//	if category is income --> salary, interest, etc..
//	if category is expense --> food, dress, etc..	
	@Column(name = "category")
	private String category;

//	payment method is cash, card, upi etc..
	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "receipt_url")
	private String receiptUrl;
	
	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "created_at")
	private LocalDate createdAt;

	@Column(name = "updated_at")
	private LocalDate updatedAt;
	

	// Getters and setters, constructors, and other methods
	

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getReceiptUrl() {
		return receiptUrl;
	}

	public void setReceiptUrl(String receiptUrl) {
		this.receiptUrl = receiptUrl;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
	//	pre and post persist
	@PrePersist
	public void onSave()
	{
		LocalDate currentDate = LocalDate.now();
		this.createdAt = currentDate;
		this.updatedAt = currentDate;
	}
	

	@PostPersist
	public void onUpdate()
	{
		LocalDate currentDate = LocalDate.now();
		this.updatedAt = currentDate;	
	}
}