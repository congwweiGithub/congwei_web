package com.example.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

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
import com.example.model.BlogsInfo;
import com.example.repository.BlogUserInfoRepository;
import com.example.repository.BlogsInfoRepository;

import antlr.collections.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogsControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BlogsInfoRepository blogsInfoRepository;

//	@WithMockUser(username = "test")   //使用Security时使用的语句，写在@Test注释下		

//	@Test  //其中foreach语句标黄，for下边的if条件判断标红
//	public void testGetBlogView_Succcess() throws Exception{
//		
//		String username = "熊大";		
//		RequestBuilder request = MockMvcRequestBuilders//
//				.get("/blog")//
//				.param("username",username)//
//				.accept(MediaType.APPLICATION_JSON);
//	
//		BlogsInfo blog = new BlogsInfo();
//		when(blogsInfoRepository.findAll().thenReturn(List.class);
//		
//		mockMvc.perform(request).andExpect(view().name("blog"));
//	}

	@Test //通过，但不确定对错不对
	public void testGetEditView_Succcess() throws Exception {

		String username = "熊大";
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/edit")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("edit"));
	}

	@Test 
	public void testGetLastPage_Succcess() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/lastPage")//
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(view().name("lastPage"));
	}
//
//	@Test  //未开始 //有问题（param（String，Long）不允许）
//	public void testUpdateBlog_Succcess() throws Exception{
//		
//		String username = "熊大";
//		Long blogId =  1l;	
//		BlogsInfo blogsInfo = BlogsInfo.builder()//
//				.username(username)//
//				.blogId(blogId)//
//				.build();
//		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);
//		
//		RequestBuilder request = MockMvcRequestBuilders//
//				.get("/update")//
//				.param("username","熊大")//	
////				.param("blogId",1l)//
//				.accept(MediaType.APPLICATION_JSON);
//		mockMvc.perform(request).andExpect(view().name("update"));
//	}
//	
//	@Test  //未开始
//	public void testDeleteBlog_Succcess() throws Exception{
//		RequestBuilder request = MockMvcRequestBuilders//
//				.get("/lastPage")//
//				.accept(MediaType.APPLICATION_JSON);
//		mockMvc.perform(request).andExpect(view().name("lastPage"));
//	}
//		

	@Test // 通过
	public void testAaddBlog_Succcess() throws Exception {
	
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "熊大日记1")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("redirect:/blog"));//
	}

	@Test // 通过
	public void testAaddBlog_Failed1() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}
	
	@Test //通过
	public void testAaddBlog_Failed2() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "熊大日记1")//
				.param("description", "")//
				.param("article", "今天认识个叫光头强的家伙")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}
	
	@Test //通过
	public void testAaddBlog_Failed3() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "熊大日记1")//
				.param("description","第一天 5.25 天气 晴" )//
				.param("article","")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test //通过
	public void testAaddBlog_Failed4() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "")//
				.param("description","" )//
				.param("article","今天认识个叫光头强的家伙")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test //通过
	public void testAaddBlog_Failed5() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "熊大日记1")//
				.param("description","" )//
				.param("article","")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}
	
	@Test //通过
	public void testAaddBlog_Failed6() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "")//
				.param("description","第一天 5.25 天气 晴" )//
				.param("article","")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}
	
	@Test //通过
	public void testAaddBlog_Failed7() throws Exception {


		RequestBuilder request = MockMvcRequestBuilders//
				.post("/edit")//
				.param("title", "")//
				.param("description","" )//
				.param("article","")//
				.param("username", "熊大")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.save(any(BlogsInfo.class))).then(i -> i.getArgument(0));

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
	}

	@Test // 通过（post中blogId和username有疑问）
	public void testUpdate_Succcess() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "熊大日记1")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙，是个大光头")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		

		mockMvc.perform(request)//
				.andExpect(view().name("redirect:/blog"));//
		}
	
	@Test // 通过
	public void testUpdate_Failed1() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "今天认识个叫光头强的家伙，是个大光头")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
		}

	@Test // 通过
	public void testUpdate_Failed2() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "熊大日记1")//
				.param("description", "")//
				.param("article", "今天认识个叫光头强的家伙，是个大光头")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
		}
	
	@Test // 通过
	public void testUpdate_Failed3() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "熊大日记1")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
		}
	
	@Test // 通过
	public void testUpdate_Failed4() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "熊大日记1")//
				.param("description", "")//
				.param("article", "")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
		}
	
	@Test // 通过
	public void testUpdate_Failed5() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "")//
				.param("description", "第一天 5.25 天气 晴")//
				.param("article", "")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
		}
	
	@Test // 通过
	public void testUpdate_Failed6() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "")//
				.param("description", "")//
				.param("article", "今天认识个叫光头强的家伙，是个大光头")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
		}
	
	@Test // 通过
	public void testUpdate_Failed7() throws Exception {
				
		String username = "熊大";
		Long blogId = 1l;
		BlogsInfo blogsInfo = BlogsInfo.builder()//
				.username(username)//
				.blogId(blogId)//
				.build();		
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", "")//
				.param("description", "")//
				.param("article", "")//
				.param("username", "熊大")//
				.param("blogId", "1")//
				.accept(MediaType.APPLICATION_JSON);
		when(blogsInfoRepository.findByBlogId(blogId)).thenReturn(blogsInfo);

		mockMvc.perform(request)//
				.andExpect(view().name("editFailed"));//
		}
	
}
