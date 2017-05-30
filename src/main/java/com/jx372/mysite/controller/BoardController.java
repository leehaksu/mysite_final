package com.jx372.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.jx372.mysite.service.BoardService;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getList(Model model,@RequestParam(value="page", required=true, defaultValue="0") int page)
	{
		System.out.println("[Controller] firstNumber : " + page);
		List<BoardVo> list = new ArrayList<BoardVo>();
		list=boardService.getList(page);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write()
	{
		return "/board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession authUser,@ModelAttribute BoardVo vo)
	{
		System.out.println("[Controller] BoardVo : " +vo);
		UserVo vo1 = (UserVo) authUser.getAttribute("authUser");
		System.out.println(vo1.getNo());
	
		vo.setUser_no(vo1.getNo());
		boardService.write(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Model model,@RequestParam("no") Long no,@RequestParam("depth") int depth)
	{
		BoardVo vo=boardService.view(no);
		boardService.updateHit(no);
		model.addAttribute("title",vo.getTitle());
		model.addAttribute("content",vo.getContent());
		model.addAttribute("no",no);
		model.addAttribute("depth", depth);
		return "/board/view";
		
	}
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(Model model,@RequestParam("no") Long no)
	{
		BoardVo vo=boardService.view(no);
		model.addAttribute("title",vo.getTitle());
		model.addAttribute("content",vo.getContent());
		model.addAttribute("no",no);
		return "/board/modify";
	}
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo)
	{
		System.out.println("[Controller] BoardVo" + vo);
		boardService.modify(vo);
		return "redirect:/board/list";
	}
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply(Model model,@RequestParam("no") Long no,@RequestParam("depth") int depth)
	{
		System.out.println("[controller] no : " +no +"depth : " + depth);
		model.addAttribute("no", no);
		model.addAttribute("depth", depth);
		return "/board/reply";
	}
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@ModelAttribute BoardVo vo)
	{
		System.out.println("[controller] BoardVo : " +vo);
		BoardVo vo1= boardService.get(vo.getNo());
		vo.setGroup_no(vo1.getGroup_no());
		vo.setOrder_no(vo1.getOrder_no());
		System.out.println("[controller] BoardVo1 : " + vo);
		/*
		boardService.updateOrder(vo1.getNo());
		boardService.write(vo);
		*/
		return "/board/reply";
	}
	

	
	
}
