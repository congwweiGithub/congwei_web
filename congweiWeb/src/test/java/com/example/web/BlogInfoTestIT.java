package com.example.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.BlogInfo;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogInfoTestIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Test // 覆盖率75.9%，Controller里没标出未覆盖的代码
	public void testGetBlogView_Succcess() throws Exception {

		String username = "熊大";					
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/blog")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("blog"))//
				.andExpect(model().attribute("blogs",
						Matchers.everyItem(Matchers.hasProperty("username", Matchers.is(username)))))
						.andExpect(model().attribute("username", username));
	}
	
	@Test // 通过
	public void testGetEditView_Succcess() throws Exception {

		String username = "熊大";
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/edit")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("edit"))
				.andExpect(model().attribute("username", username));
	}
	
	@Test // 通过
	public void testGetLastPage_Succcess() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/lastPage")//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("lastPage"));
	}
	
	@Test // 找不到"blogId"
	public void testGetUpdateView_Succcess() throws Exception {

		String username = "熊大";
		Long blogId = 1l;
//		BlogInfo blogInfo = BlogInfo.builder()//
//				.username(username)//
//				.blogId(blogId)//
//				.build();
//		when(blogInfoRepository.findByBlogId(blogId)).thenReturn(blogInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/update")//
				.param("username", username)//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("update"))//
				.andExpect(model().attribute("username", username));//
//				.andExpect(model().attribute("blog", blogInfo));
	}
	
	@Test // 通过 但不确定对错不对
	public void testDeleteBlog_Succcess() throws Exception {

		String username = "熊大";
		Long blogId = 1l;
//		BlogInfo blogInfo = BlogInfo.builder()//
//				.username(username)//
//				.blogId(blogId)//
//				.build();
//		when(blogInfoRepository.findByBlogId(blogId)).thenReturn(blogInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/delete")//
				.param("username", "熊大")//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);
		// mockMvc.perform(request).andExpect(redirectedUrl("/blog"+"?username="+username))
		mockMvc.perform(request).andExpect(view().name("redirect:/blog"))//
				.andExpect(model().attribute("username", username));//
	}
	
}
