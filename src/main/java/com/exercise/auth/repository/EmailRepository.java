package com.exercise.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exercise.auth.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {
	
	 @Query("select u from Email u where u.name = ?1")
	  List<Email> findByUserName(String username);

}