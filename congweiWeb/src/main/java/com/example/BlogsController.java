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
	// <a>超链接 GetMppping()方法 超链接写成这样 <a href="/login">signin</a>
	public String getEditView() {
		return "edit";
	}

	@GetMapping("/lastPage")
	// <a>超链接 GetMppping()方法 超链接写成这样 <a href="/login">signin</a>
	public String getLastPage() {

		return "lastPage";
	}

	

	@GetMapping("/update") // 此操作在register中进
	public ModelAndView updateBlog( //
			Long blogId, //
			ModelAndView mv) {
//     	System.out.println("00000000000"+blogId);
		BlogsInfo blogsInfo = blogsInfoRepository.findByBlogId(blogId);
//		System.out.println("222222222222222"+blogsInfo.getTitle() );
//		mv.addObject("title",blogsInfo.getTitle() ); blog里包括所有内容，不需要额外添加title等内容了
		mv.addObject("blog", blogsInfo);
		mv.setViewName("update");
		return mv; // 返回值是mv，才能显示在屏幕上
	}

	@GetMapping("/delete") // 此操作在register中进
	public ModelAndView deleteBlog( //
			Long blogId, //
			ModelAndView mv) {
		BlogsInfo blogsInfo = blogsInfoRepository.findByBlogId(blogId);
		System.out.println(blogId);
		System.out.println(blogsInfo);
		blogsInfoRepository.delete(blogsInfo);
		mv.setViewName("redirect:/blog");
		return mv; // 返回值是mv，才能显示在屏幕上
	}

	@PostMapping("/edit") // 此操作在register中进行
	public ModelAndView blog(//
			@RequestParam("title") String title, //
			@RequestParam("description") String description, //
			@RequestParam("article") String article, //
//			String username,
//			@RequestParam("blog_Id") Long blogId, 
			ModelAndView mv) {

		if (title.isEmpty() || description.isEmpty() //
				|| article.isEmpty()) { //
			mv.setViewName("editFailed");
		} else {
			BlogsInfo blogsInfo = BlogsInfo.builder()//
					.title(title)//
					.description(description)//
					.article(article)//
//					.username(username)
					.build();
			blogsInfoRepository.save(blogsInfo);
			mv.addObject("title", blogsInfo.getTitle());
//			mv.addObject("username", "熊大");
//			mv.addObject("blogId", blogId);
			mv.setViewName("redirect:/blog");

		}
		return mv;
	}

	@PostMapping("/update") // 此操作在register中进行
	public ModelAndView update(//
			@RequestParam("title") String title, //
			@RequestParam("description") String description, //
			@RequestParam("article") String article, //
			String username,//
			@RequestParam("blogId") Long blogId, ModelAndView mv) {

		if (title.isEmpty() || description.isEmpty() //
				|| article.isEmpty()) { //
			mv.setViewName("editFailed");
		} else {
			BlogsInfo blogsInfo = blogsInfoRepository.findByBlogId(blogId);
			blogsInfo.setTitle(title);
			blogsInfo.setDescription(description);
			blogsInfo.setArticle(article);
//			blogsInfo = BlogsInfo.builder()//
//					.title(title)//
//					.description(description)//
//					.article(article)//
//					.build();
			blogsInfoRepository.save(blogsInfo);
			System.out.println("99999999999999" + blogsInfo.getTitle());
			mv.addObject("username", "熊大");
//			mv.addObject("blogId", blogId);
			mv.setViewName("redirect:/blog");

		}
		return mv;
	}
	
	@GetMapping("/blog")
	// <a>超链接 GetMppping()方法 超链接写成这样 <a href="/login">signin</a>
	public ModelAndView getBlogView( String username, //
//			@RequestParam("blogId") Long blogId, 
			ModelAndView mv) {
		List<BlogsInfo> blogs = blogsInfoRepository.findAll();
//		List<BlogsInfo> blog = new ArrayList<>();
//		for(BlogsInfo b:blogs) {
//			if(b.getUsername().equals(username)) {
//				blog.add(b);
//			}
//		}
		System.out.println("1111111111111111");
		mv.addObject("username", "熊大");
//		mv.addObject("blogId", blogId);
//		mv.addObject("blogs", blog);
		mv.addObject("blogs", blogs);
		mv.setViewName("blog");

		return mv;
	}
	
}