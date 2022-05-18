package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.BlogUserInfo;


public interface BlogUserInfoRepository extends JpaRepository<BlogUserInfo,Long> {
	
	BlogUserInfo findByUsername(String username);

}