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
	public String getRegisterView() {

		return "register";
	}

	@GetMapping("/login")
	public String getLoginView() {

		return "login";
	}
		
	@PostMapping("/register")  //此操作在register中进行
	public ModelAndView register(//
			@RequestParam("username") String username, //
			@RequestParam("pw") String password, //
			@RequestParam("rpw") String repassword, //
			ModelAndView mv) {
	
		if (username.length() >= 2 && password.length() >= 6 //
				&& repassword.equals(password)) { //
			BlogUserInfo bloguserInfo = BlogUserInfo.builder()// 
					.username(username)//
					.password(password).build();
			bloguserInfoRepository.save(bloguserInfo);
			mv.addObject("username", bloguserInfo.getUsername());
			mv.setViewName("login");
			System.err.println("注册成功");
		} else {			
			mv.setViewName("Failed");
		}
		return mv;
	}

	@PostMapping("/login")//此操作在login中进行
	public ModelAndView login(//
			@RequestParam("username") String username, //
			@RequestParam("password") String password, //
			ModelAndView mv) {
		mv.addObject("username", username);
		BlogUserInfo bloguserInfo = bloguserInfoRepository.findByUsername(username);
		if (bloguserInfo != null && password.equals(bloguserInfo.getPassword())) {
			mv.setViewName("redirect:/blog");	
			System.out.println("登陆成功");
		} else {
			mv.setViewName("Failed");
		}

		return mv;
	}
			
}
