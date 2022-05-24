package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.BlogsInfo;

public interface BlogsInfoRepository extends JpaRepository<BlogsInfo,Long> {

	BlogsInfo findByBlogId(String blogId);
}
