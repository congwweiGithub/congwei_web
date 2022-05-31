package com.example.web;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.BlogUserInfo;
import com.example.repository.BlogUserInfoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogUserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BlogUserInfoRepository blogUserInfoRepository;
	
	@Test
	public void testGetLoginView_Succcess() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/login")//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("login"));
	}
	
	@Test
	public void testGetRegisterView_Succcess() throws Exception{		
		
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/register")//
				.accept(MediaType.APPLICATION_JSON);		
		mockMvc.perform(request).andExpect(view().name("register"));	
	}
	
	@Test
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
	
	@Test
	public void testRegister_Failed_UsernameLessTan2() throws Exception{		

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username","熊")//
				.param("pw", "xd1234")//
				.param("rpw", "xd1234")//
				.accept(MediaType.APPLICATION_JSON);			
//		when(blogUserInfoRepository.save(any(BlogUserInfo.class))).then(i -> i.getArgument(0));  
		
		mockMvc.perform(request).andExpect(view().name("Failed"));
	
	}
	
	@Test
	public void testRegister_Failed_PassWordLessTan6() throws Exception{		

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username","熊大")//
				.param("pw", "xd123")//
				.param("rpw", "xd123")//
				.accept(MediaType.APPLICATION_JSON);			
//		when(blogUserInfoRepository.save(any(BlogUserInfo.class))).then(i -> i.getArgument(0));  
		
		mockMvc.perform(request).andExpect(view().name("Failed"));
	
	}		
	
	@Test
	public void testRegister_Failed_RepassWordUnequalToPassWord() throws Exception{		

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username","熊大")//
				.param("pw", "xd1234")//
				.param("rpw", "xd1235")//
				.accept(MediaType.APPLICATION_JSON);			
		when(blogUserInfoRepository.save(any(BlogUserInfo.class))).then(i -> i.getArgument(0));  
		
		mockMvc.perform(request).andExpect(view().name("Failed"));
	
	}
	
	@Test
	public void testLogin_Succcess() throws Exception{
		
		String username = "熊大";
		String password = "xd1234";
//		BlogUserInfo blogUserInfo = new BlogUserInfo(1l,password,username);
		BlogUserInfo blogUserInfo = BlogUserInfo.builder()//
				.username(username)//
				.password(password)//
				.build();
		
		when(blogUserInfoRepository.findByUsername(username)).thenReturn(blogUserInfo);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username",username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("redirect:/blog"))
		 .andExpect(model().attribute("username",username));
	}
	
	@Test
	public void testLogin_Failed_UsernameMiss() throws Exception{
		
		String username = "熊大";
		String password = "xd1234";
		BlogUserInfo blogUserInfo = BlogUserInfo.builder()//
				.username(username)//
				.password(password)//
				.build();
		
		when(blogUserInfoRepository.findByUsername(username)).thenReturn(blogUserInfo);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username","熊")//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("Failed"));
	}
	
	@Test
	public void  testLogin_Failed_PassWordMiss() throws Exception{
		
		String username = "熊大";
		String password = "xd1234";
		BlogUserInfo blogUserInfo = BlogUserInfo.builder()//
				.username(username)//
				.password(password)//
				.build();
		
		when(blogUserInfoRepository.findByUsername(username)).thenReturn(blogUserInfo);
		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username",username)//
				.param("password","xd12345")//
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)//
		.andExpect(view().name("Failed"));
	}
			
}


