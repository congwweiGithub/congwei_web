package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.BlogsInfo;
import com.example.repository.BlogsInfoRepository;

public class Test {
	@Autowired	
	public static BlogsInfoRepository blogsInfoRepository;
	public static void main(String[] args) {
		List<BlogsInfo> blogs = blogsInfoRepository.findAll();
		System.out.println(blogs);
		}	
		}

