package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController3 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);

	// http://localhost:8088/web/doC
	// http://localhost:8088/web/doC?userid=admin&gender=남&userpw=1234
	// @GetMapping(value = "/doC")
	@RequestMapping(value = "/doC", method = RequestMethod.GET)
	public void doC(Model model, MemberVO vo 
			/* @ModelAttribute("userid") String userid */) {
		// MemberVO 객체를 생성하면 자동으로 파라미터 수집 (해당 요소)
		logger.debug(" doC() 호출 ");
		logger.debug(" vo : " + vo);
		
//		logger.debug(" userid : " + userid);
		
		// DB에서 생성된 데이터 (가정)
		MemberVO resultVO = new MemberVO();
		resultVO.setUserid("admin");
		
		// => 연결된 뷰페이지로 전달 
		// Model 객체 생성 (view - controller 사이에 정보를 전달하는 정보 전달 전용 객체)
		// model.addAttribute("resultVO", resultVO);
		model.addAttribute(resultVO);
		// => 전달하는 대상의 이름이 없는 경우
		//    전달하는 데이터타입 (클래스명)의 첫글자를 소문자로 변경후 이름으로 사용
		
		// doC.jsp 페이지로 이동 (연결)
	}
}
