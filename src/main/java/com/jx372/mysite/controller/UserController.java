package com.jx372.mysite.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService user;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join()
	{
		return "/user/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo uservo)
	{
		System.out.println("잘 들어왔지?");
		user.join(uservo);
		return "redirect:/user/joinsuccess";	
	}
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess()
	{
		return "/user/joinsuccess";	
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login()
	{
		return "/user/login";	
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession httpsession,Model model,@ModelAttribute UserVo uservo)
	{
		uservo=user.getUser(uservo);
		if(uservo==null)
		{
			model.addAttribute("result", "fail");
			return "/user/login";
		}
		httpsession.setAttribute("authUser", uservo);
		return "redirect:/";	
	}
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(Model model,HttpSession authUser)
	{
		
		UserVo uservo=(UserVo) authUser.getAttribute("authUser");
		uservo=user.getUser(uservo.getNo());
		System.out.println("[Controller] uservo :" + uservo);
		model.addAttribute("no",uservo.getNo());
		model.addAttribute("email",uservo.getEmail());
		model.addAttribute("gender",uservo.getGender());
		return "/user/modify";
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpSession authUser,@ModelAttribute UserVo uservo)
	{
		user.modify(uservo);
		System.out.println("[Controller] modify.uservo :" +uservo);
		uservo=user.getUser(uservo.getNo());
		System.out.println("[Controller] modify : "+uservo);
		authUser.setAttribute("authUser", uservo);
		return "redirect:/";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession authUser)
	{
		user.logout(authUser);
		
		return "redirect:/";
		
	}
	
	
}
