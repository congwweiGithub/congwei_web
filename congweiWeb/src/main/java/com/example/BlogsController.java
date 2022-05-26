package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.BlogsInfo;
import com.example.repository.BlogsInfoRepository;

@Controller
public class BlogsController {

	@Autowired
	private BlogsInfoRepository blogsInfoRepository;

	@GetMapping("/edit")	
	public ModelAndView getEditView(//
			String username, //
			ModelAndView mv) {
			mv.addObject("username",username);
			mv.setViewName("edit");
			System.out.println("进入编辑界面");
		return mv;
	}

	@GetMapping("/lastPage")	
	public String getLastPage() {

		return "lastPage";
	}

	

	@GetMapping("/update") // 此操作在register中进
	public ModelAndView updateBlog( //
			String username,//
			Long blogId, //
			ModelAndView mv) {
		BlogsInfo blogsInfo = blogsInfoRepository.findByBlogId(blogId);
		mv.addObject("blog", blogsInfo);		
		mv.addObject("username", username);
		mv.setViewName("update");
		System.out.println("进入更新界面");
		return mv; 
	}

	@GetMapping("/delete") 
	public ModelAndView deleteBlog( //
			String username,//
			Long blogId, //
			ModelAndView mv) {
		BlogsInfo blogsInfo = blogsInfoRepository.findByBlogId(blogId);		
		blogsInfoRepository.delete(blogsInfo);
		mv.addObject("username", username);
		mv.setViewName("redirect:/blog");
		System.out.println("已执行删除操作");
		return mv; 
	}

	@PostMapping("/edit") 
	public ModelAndView addBlog(//
			@RequestParam("title") String title, //
			@RequestParam("description") String description, //
			@RequestParam("article") String article, //
			@RequestParam("username") String username,
			ModelAndView mv) {

		if (title.isEmpty() || description.isEmpty() //
				|| article.isEmpty()) { //
			mv.setViewName("editFailed");
		} else {
			BlogsInfo blogsInfo = BlogsInfo.builder()//
					.title(title)//
					.description(description)//
					.article(article)//
					.username(username)
					.build();
			blogsInfoRepository.save(blogsInfo);
			mv.addObject("username", username);
			mv.setViewName("redirect:/blog");
			System.out.println("新增内容成功");
		}
		return mv;
	}

	@PostMapping("/update") 
	public ModelAndView update(//
			@RequestParam("title") String title, //
			@RequestParam("description") String description, //
			@RequestParam("article") String article, //
			@RequestParam("username") String username,//
			@RequestParam("blogId") Long blogId, ModelAndView mv) {

		if (title.isEmpty() || description.isEmpty() //
				|| article.isEmpty()) { //
			mv.setViewName("editFailed");
		} else {
			BlogsInfo blogsInfo = blogsInfoRepository.findByBlogId(blogId);
			blogsInfo.setTitle(title);
			blogsInfo.setDescription(description);
			blogsInfo.setArticle(article);		
			blogsInfoRepository.save(blogsInfo);
			mv.addObject("username", username);
			mv.setViewName("redirect:/blog");
			System.out.println("更新内容成功");
		}
		return mv;
	}
	
	@GetMapping("/blog")	
	public ModelAndView getBlogView( //
			String username, //
			ModelAndView mv) {
		List<BlogsInfo> blogs = blogsInfoRepository.findAll();
				List<BlogsInfo> blog = new ArrayList<>();
		
				for(BlogsInfo b:blogs) {			
					if(b.getUsername().equals(username)) {
						blog.add(b);
					}
				}
		mv.addObject("username", username);
		mv.addObject("blogs", blog);
		mv.setViewName("blog");
		System.out.println("进入"+username+"的博客页面");
		return mv;
	}
	
}