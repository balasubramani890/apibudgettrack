package com.budgettrack.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.budgettrack.api.Entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findById(Long userId);
	
	@Query(value ="SELECT CASE WHEN COUNT(e.mobileNo) > 0 THEN TRUE ELSE FALSE END FROM UserEntity e WHERE e.mobileNo = :mobileNo")
	boolean findByMobileNumber(@Param("mobileNo") String mobileNo);
	
	@Query("SELECT e FROM UserEntity e WHERE e.mobileNo = :mobileNo and e.password = :password")
	UserEntity validLogin(@Param("mobileNo") String mobileNo, @Param("password") String password);

//	@Query(value ="SELECT e FROM user e WHERE e.mobileNo = ?1 and e.password = ?2", nativeQuery = true)
//	UserEntity validLogin(String mobileNo, String password);
	
	@Query(value ="SELECT e.userId FROM UserEntity e WHERE e.mobileNo = :mobileNo and e.password = :password")
	Long loginAccept(@Param("mobileNo")String mobileNo, @Param("password") String password);
	
	@Query(value ="SELECT e FROM UserEntity e WHERE e.mobileNo = :mobileNo")
	UserEntity forgetPasswordRepo(@Param("mobileNo") String mobileNo);

}