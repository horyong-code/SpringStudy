package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController2 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);

	// http://localhost:8088/web/doB
	// http://localhost:8088/web/doB?msg="ITWILL"
	// http://localhost:8088/web/doB?msg="ITWILL"&age=20
	// @RequestMapping(value = "/doB", method = RequestMethod.GET)
	@GetMapping(value = "/doB")
	public String doB(@ModelAttribute("msg") String msg, 
					  @ModelAttribute("age") int age) { 
		// public String doB(@RequestParam("msg") String msg) { 
		/* int 기본형 타입 불가 => 나중에 REST에서 사용 가능 */
		
		// @ModelAttribute("파라미터명") 저장할 데이터 변수
		// => 전달 정보를 저장해서 연결된 view 페이지까지 전달
		logger.debug(" doB() 메서드 호출");
		
		logger.debug("msg : " + msg); // 전달된 파라미터 정보 저장
		logger.debug("age : " + (age + 100));
		// 메서드 리턴 타입이 String일 때 "리턴문자".jsp 뷰페이지로 이동 (연결)
		return "itwill";
	}

}
