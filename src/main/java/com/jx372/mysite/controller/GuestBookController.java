package com.jx372.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jx372.mysite.service.GuestBookService;
import com.jx372.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

	@Autowired
	private GuestBookService guestbookService;

	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("[Controller] GuestBookVO");
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		list = guestbookService.getList();
		model.addAttribute("list", list);

		return "/guestbook/list";
	}

	@RequestMapping("/insert")

	public String insert(@ModelAttribute GuestBookVo vo) {
		
		guestbookService.insert(vo);
		
		return "redirect:/guestbook/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("no") Long no) {
		model.addAttribute("no", no);
		return "/guestbook/delete";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("no") Long no, @RequestParam("password") String passwd) {
		guestbookService.delete(no, passwd);
		return "redirect:/guestbook/list";
	}
}
