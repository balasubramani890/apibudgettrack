package com.budgettrack.api.Entity;

import java.time.LocalDate;

import com.budgettrack.api.constants.CommonConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table (name="USERS")
public class UserEntity {
	
	@Id	
	@Column(name = "user_id", length = 50)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name = "name", length = 50)
	private String userName;
	
	@Column(name = "mobile_no", length = 50)
	private String mobileNo;
	
	@Column(name = "password", length = 50)
	private String password;
	
	@Column(name = "user_type", length = 50)
	private String userType = CommonConstants.USER_TYPE;
	
	@Column(name = "is_active", length = 50)
	private boolean isActive;
	
	@Column(name = "sso_type", length = 50)
	private String ssoType;
	
	@Column(name = "login_at", length = 50)
	private LocalDate  loginAt;
	
	@Column(name = "login_count", length = 50)
	private Integer loginCount = 0;
	
	@Column(name = "created_at", length = 50)
	private LocalDate createdAt;
	
	@Column(name = "updated_at", length = 50)
	private LocalDate updatedAt;

	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getSsoType() {
		return ssoType;
	}

	public void setSsoType(String ssoType) {
		this.ssoType = ssoType;
	}

	public LocalDate getLoginAt() {
		return loginAt;
	}

	public void setLoginAt(LocalDate loginAt) {
		this.loginAt = loginAt;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
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