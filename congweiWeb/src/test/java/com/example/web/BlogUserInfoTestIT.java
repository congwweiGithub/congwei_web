package com.example.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogUserInfoTestIT {
	@Autowired
	private MockMvc mockMvc;
		
//	@Test
//	public void login_succcess() throws Exception{
//		
//		String username = "熊大";
//		String password = "xd1234";
//								
//		RequestBuilder request = MockMvcRequestBuilders//
//				.post("/login")//
//				.param("username",username)//
//				.param("password", password)//
//				.accept(MediaType.APPLICATION_JSON);
//		
//		mockMvc.perform(request)//
//		.andExpect(view().name("redirect:/blog"));
//	}
	
	@Test
	public void register_succcess() throws Exception{
		String username = null;
		String password = null;		
		String repassword = null;
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username",username)//
				.param("password", password)//
				.param("password", repassword)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("login"));
	
	}
}