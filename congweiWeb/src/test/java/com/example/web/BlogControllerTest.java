package com.example.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.BlogInfo;
import com.example.repository.BlogInfoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BlogInfoRepository blogInfoRepository;

//	@WithMockUser(username = "test")   //使用Security时使用的语句，写在@Test注释下		

	@Test // 其中foreach语句标黄，for下边的if条件判断标红
	public void testGetBlogView_Succcess() throws Exception {

		String username1 = "熊大";
		BlogInfo blogInfo1 = BlogInfo.builder()//
				.username(username1)//
				.build();
		String username2 = "熊二";
		BlogInfo blogInfo2 = BlogInfo.builder()//
				.username(username2)//
				.build();
		BlogInfo blogInfo3 = BlogInfo.builder()//
				.username(username1)//
				.build();

		when(blogInfoRepository.findAll()).thenReturn(List.of(blogInfo1, blogInfo2, blogInfo3));// return时自动创建链表

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/blog")//
				.param("username", username1)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("blog"))//
				.andExpect(model().attribute("blogs",
						Matchers.everyItem(Matchers.hasProperty("username", Matchers.is(username1)))))
						.andExpect(model().attribute("username", username1));
	}

	@Test // 通过
	public void testGetEditView_Succcess() throws Exception {

		String username = "熊大";
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/edit")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("edit")).andExpect(model().attribute("username", username));
	}

	@Test // 通过
	public void testGetLastPage_Succcess() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/lastPage")//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("lastPage"));
	}

	@Test // 通过 但不确定对错不对
	public void testGetUpdateView_Succcess() throws Exception {

		String username = "熊大";
		Long blogId = 1l;
		BlogInfo blogInfo = BlogInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();
		when(blogInfoRepository.findByBlogId(blogId)).thenReturn(blogInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/update")//
				.param("username", username)//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("update"))//
				.andExpect(model().attribute("username", username))//
				.andExpect(model().attribute("blog", blogInfo));
	}

	@Test // 通过 但不确定对错不对
	public void testDeleteBlog_Succcess() throws Exception {

		String username = "熊大";
		Long blogId = 1l;
		BlogInfo blogInfo = BlogInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();
		when(blogInfoRepository.findByBlogId(blogId)).thenReturn(blogInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/delete")//
				.param("username", "熊大")//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);
		// mockMvc.perform(request).andExpect(redirectedUrl("/blog"+"?username="+username))
		mockMvc.perform(request).andExpect(view().name("redirect:/blog"))//
				.andExpect(model().attribute("username", username));//
	}

	@Test // 通过
	public void testAddBlog_Succcess() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "熊大日记1")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("redirect:/blog"))//
				.andExpect(model().attribute("username", "熊大"));//
	}

	@Test // 通过
	public void testAaddBlog_Failed_TitleIsEmpty() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过
	public void testAaddBlog_Failed_DescriptionIsEmpty() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "熊大日记1")//
				.param("description", "")//
				.param("article", "今天认识个叫光头强的家伙")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过
	public void testAaddBlog_Failed_ArticleIsEmpty() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "熊大日记1")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过
	public void testAaddBlog_Failed_AllEmpty() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "")//
				.param("description", "")//
				.param("article", "")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过（post中blogId和username有疑问）
	public void testUpdate_Succcess() throws Exception {

		String username = "熊大";
		Long blogId = 1l;
		BlogInfo blogInfo = BlogInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();
		when(blogInfoRepository.findByBlogId(blogId)).thenReturn(blogInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "熊大日记1")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙，是个大光头")//
				.param("username", username)//
				.param("blogId",  blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("redirect:/blog"))
				.andExpect(model().attribute("username", username));
	}

	@Test // 通过
	public void testUpdate_Failed_TitleIsEmpty() throws Exception {

		String username = "熊大";
		Long blogId = 1l;
//		BlogsInfo blogInfo = BlogsInfo.builder()//
//				.username(username)//
//				.blogId(blogId)//
//				.build();
//		when(blogInfoRepository.findByBlogId(blogId)).thenReturn(blogInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙，是个大光头")//
				.param("username",username)//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过
	public void testUpdate_Failed_DescriptionIsEmpty() throws Exception {

		String username = "熊大";
		Long blogId = 1l;

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "熊大日记1")//
				.param("description", "")//
				.param("article", "今天认识个叫光头强的家伙，是个大光头")//
				.param("username",username)//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过
	public void testUpdate_Failed_ArticleIsEmpty() throws Exception {

		String username = "熊大";
		Long blogId = 1l;

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "熊大日记1")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "")//
				.param("username",username)//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过
	public void testUpdate_Failed_AllEmpty() throws Exception {

		String username = "熊大";
		Long blogId = 1l;

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "")//
				.param("description", "")//
				.param("article", "")//
				.param("username",username)//
				.param("blogId", blogId.toString())//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

}
