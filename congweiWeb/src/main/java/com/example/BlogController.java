package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.BlogInfo;
import com.example.repository.BlogInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogController {

	@Autowired
	private BlogInfoRepository blogInfoRepository;

	@GetMapping("/edit")	
	public ModelAndView getEditView(//
			String username, //
			ModelAndView mv) {
			mv.addObject("username",username);
			mv.setViewName("edit");
			log.info("进入编辑界面");
		return mv;
	}

	@GetMapping("/lastPage")	
	public String getLastPage() {

		return "lastPage";
	}

	

	@GetMapping("/update") // 
	public ModelAndView getUpdateView( //
			String username,//
			Long blogId, //
			ModelAndView mv) {
		BlogInfo blogInfo = blogInfoRepository.findByBlogId(blogId);
		mv.addObject("blog", blogInfo);		
		mv.addObject("username", username);
		mv.setViewName("update");
		log.info("进入更新界面");
		return mv; 
	}

	@GetMapping("/delete") 
	public ModelAndView deleteBlog( //
			String username,//
			Long blogId, //
			ModelAndView mv) {
		BlogInfo blogInfo = blogInfoRepository.findByBlogId(blogId);		
		blogInfoRepository.delete(blogInfo);
		mv.addObject("username", username);
		mv.setViewName("redirect:/blog");
		log.info("已执行删除操作");
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
			BlogInfo blogInfo = BlogInfo.builder()//
					.title(title)//
					.description(description)//
					.article(article)//
					.username(username)
					.build();
			blogInfoRepository.save(blogInfo);
			mv.addObject("username", username);
			mv.setViewName("redirect:/blog");
			log.info("新增blog成功");;
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
			BlogInfo blogInfo = blogInfoRepository.findByBlogId(blogId);
			blogInfo.setTitle(title);
			blogInfo.setDescription(description);
			blogInfo.setArticle(article);		
			blogInfoRepository.save(blogInfo);
			mv.addObject("username", username);
			mv.setViewName("redirect:/blog");
			log.info("更新blog成功");;
		}
		return mv;
	}
	
	@GetMapping("/blog")	
	public ModelAndView getBlogView( //
			String username, //
			ModelAndView mv) {
		List<BlogInfo> blogs = blogInfoRepository.findAll();
				List<BlogInfo> blog = new ArrayList<>();
		
				for(BlogInfo b:blogs) {			
					if(b.getUsername().equals(username)) {
						blog.add(b);
					}
				}
		mv.addObject("username", username);
		mv.addObject("blogs", blog);
		mv.setViewName("blog");
		log.info("进入"+username+"的博客页面");
		return mv;
	}
	
}