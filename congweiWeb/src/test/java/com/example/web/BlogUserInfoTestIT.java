package com.example.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
				.param("username","毛毛")//
				.param("pw", "mmjj1234")//
				.param("rpw", "mmjj1234")//
				.accept(MediaType.APPLICATION_JSON);			
		
		mockMvc.perform(request).andExpect(view().name("login"));
	
	}
	
	@Test // 待修改
	public void testRegister_Failed_UsernameLessTan2() throws Exception{		

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username","吉")//
				.param("pw", "jj1234")//
				.param("rpw", "jj1234")//
				.accept(MediaType.APPLICATION_JSON);			
		
		mockMvc.perform(request).andExpect(view().name("Failed"));
	
	}
	
	@Test // 待修改
	public void testRegister_Failed_PassWordLessTan6() throws Exception{		

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username","吉吉")//
				.param("pw", "jj123")//
				.param("rpw", "jj123")//
				.accept(MediaType.APPLICATION_JSON);			
		
		mockMvc.perform(request).andExpect(view().name("Failed"));
	
	}
	
	@Test // 待修改
	public void testRegister_Failed_RepassWordUnequalToPassWord() throws Exception{		

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username","吉吉")//
				.param("pw", "jj1234")//
				.param("rpw", "jj1235")//
				.accept(MediaType.APPLICATION_JSON);			
		
		mockMvc.perform(request).andExpect(view().name("Failed"));
	
	}
	
	
	@Test // 待修改
	public void testLogin_Succcess() throws Exception{
		
		String username = "吉吉";
		String password = "jj1234";
								
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username",username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("redirect:/blog"))//
		.andExpect(model().attribute("username",username));
	}
	
	@Test // 待修改
	public void testLogin_Failed_UsernameMiss() throws Exception{
		
		String username = "吉吉吉";
		String password = "jj1234";
								
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username",username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("Failed"));
	}

	@Test // 待修改
	public void testLogin_Failed_PassWordMiss() throws Exception{
		
		String username = "吉吉";
		String password = "jj1235";
								
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username",username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("Failed"));
	}
	
}