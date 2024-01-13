package com.itwillbs.web;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);
	
	// http://localhost:8088/web/doD
	// http://localhost:8088/web/doD?msg=itwill
	@RequestMapping(value = "/doD", method = RequestMethod.GET)
	public String doD(RedirectAttributes rttr /*, Model model*/
			/* @ModelAttribute("msg") String msg */) {
		logger.debug(" doD() 호출 ");
//		logger.debug(" msg : " + msg);
		
		rttr.addFlashAttribute("msg", "아이티윌");
//		model.addAttribute("msg2", "itwill");
		
		// addAttribute() : URI 표시 O, (Model, RedirectAttributes) 둘 다 사용 가능, F5 데이터 유지
		// addFlashAttribute() : URI 표시 X, (RedirectAttributes)만 사용 가능, F5 데이터 사라짐 (1회성)
		
		// 연결되는 뷰 페이지만 변경
		// return "/doE";
		return "redirect:/doE"; // 리다이렉트할 때 바로 데이터 (정보) 전달
		// return "redirect:/doE?msg="+msg;
		// return "forward:/doE";
	}
	
	// http://localhost:8088/web/doE
	@RequestMapping(value = "/doE", method = RequestMethod.GET)
	public void doE(@ModelAttribute("msg") String msg, 
			@ModelAttribute("msg2") String msg2) {
		logger.debug(" doE() 호출 ");
		logger.debug(" msg : " + msg);
		logger.debug(" msg2 : " + msg2);
	}
	
	// http://localhost:8088/web/doF?data=1234&data=2222
	@RequestMapping(value = "/doF", method = RequestMethod.GET)
	public void doF(@RequestParam("data") ArrayList<Integer> data /* @ModelAttribute("data") int data, */) {
		logger.debug(" doF() 호출 ");
		
		logger.debug(" data : " + data);
//		logger.debug(" data : " + data[0]);
//		logger.debug(" data : " + data[1]);
//		logger.debug(" data : " + (data + 100));

	}
}
