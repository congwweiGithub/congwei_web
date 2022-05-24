package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.BlogUserInfo;
import com.example.repository.BlogUserInfoRepository;

@Controller
public class BlogUserController {

	@Autowired
	private BlogUserInfoRepository bloguserInfoRepository;
	

	@GetMapping("/register")
	public String getSetUpView() {

		return "register";
	}

	@GetMapping("/login")
	// <a>超链接 GetMppping()方法 超链接写成这样 <a href="/login">signin</a>
	public String getLoginView() {

		return "login";
	}

	@PostMapping("/register")  //此操作在register中进行
	public ModelAndView login(//
			@RequestParam("username") String username, //
			@RequestParam("pw") String password, //
			@RequestParam("rpw") String repassword, //
			ModelAndView mv) {
	
		if (username.length() < 3 || password.length() < 6 //
				|| !repassword.equals(password)) { //
			mv.setViewName("loginFailed");
		} else {
			BlogUserInfo bloguserInfo = BlogUserInfo.builder()// 
					.username(username)//
					.password(password).build();
			bloguserInfoRepository.save(bloguserInfo);
			mv.addObject("username", bloguserInfo.getUsername());
			mv.setViewName("redirect:/login");
		}
		return mv;
	}

	@PostMapping("/login")//此操作在login中进行
	public ModelAndView blog(//
			@RequestParam("username") String username, //
			@RequestParam("password") String password, //
			ModelAndView mv) {

		mv.addObject("username", username);
		BlogUserInfo userInfo = bloguserInfoRepository.findByUsername(username);

		if (userInfo != null && password.equals(userInfo.getPassword())) {
			mv.setViewName("/blog");	
			
			
		} else {
			mv.setViewName("loginFailed");
		}

		return mv;
	}
			
}
