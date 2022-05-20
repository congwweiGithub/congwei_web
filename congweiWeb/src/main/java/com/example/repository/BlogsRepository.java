package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.BlogsInfo;

public interface BlogsRepository extends JpaRepository<BlogsInfo,Long> {

	
}
