package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.example.model.BlogsInfo;
import com.example.repository.BlogsInfoRepository;

@Controller
public class BlogsController {

	@Autowired	
	private BlogsInfoRepository blogsInfoRepository;
	
	@GetMapping("/edit")
	// <a>超链接 GetMppping()方法 超链接写成这样 <a href="/login">signin</a>
	public String getEditView() {

		return "edit";
	}

	@PostMapping("/edit")  //此操作在register中进行
	public ModelAndView blog(//
			@RequestParam("title") String title, //
			@RequestParam("description") String description, //
			@RequestParam("article") String article, //
			ModelAndView mv) {
	
		if (title.isEmpty()|| description.isEmpty() //
				||article.isEmpty()) { //
			mv.setViewName("editFailed");
		} else {
			BlogsInfo blogsInfo = BlogsInfo.builder()// 
					.title(title)//
					.description(description)//
					.article(article)//
					.build();
			blogsInfoRepository.save(blogsInfo);
			mv.addObject("title", blogsInfo.getTitle());
			mv.setViewName("blog");
			System.out.println("22222222");
		}
		return mv;
	}
	@PostMapping("/blog")
	// <a>超链接 GetMppping()方法 超链接写成这样 <a href="/login">signin</a>
	public String getBlogView(ModelAndView mv) {
		List<BlogsInfo> blogs = blogsInfoRepository.findAll();
		
		mv.addObject("blogs", blogs);
		mv.setViewName("blog");
		System.out.println("1111111111");
		return "mv";
	}
	
	

	
}