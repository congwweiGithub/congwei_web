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
		
	@Test // 待修改
	public void testGetLoginView_Succcess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/login")//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("login"));
	}
	
	@Test // 待修改
	public void testGetRegisterView_Succcess() throws Exception{		
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/register")//
				.accept(MediaType.APPLICATION_JSON);		
		mockMvc.perform(request).andExpect(view().name("register"));	
	}
	
	@Test // 待修改
	public void testRegister_Succcess() throws Exception{		

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username","熊大")//
				.param("pw", "xd1234")//
				.param("rpw", "xd1234")//
				.accept(MediaType.APPLICATION_JSON);			
//		when(blogUserInfoRepository.save(any(BlogUserInfo.class))).then(i -> i.getArgument(0));  
		
		mockMvc.perform(request).andExpect(view().name("login"));
	
	}
	
	@Test // 待修改
	public void testLogin_Succcess() throws Exception{
		
		String username = "熊大";
		String password = "xd1234";
								
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username",username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("redirect:/blog"));
	}
	
	

}