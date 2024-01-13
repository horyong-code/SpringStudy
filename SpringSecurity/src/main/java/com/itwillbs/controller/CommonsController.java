package com.itwillbs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonsController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonsController.class);
	
	@GetMapping(value = "/accessErr")
	public void accessErr(Authentication auth) throws Exception{
		logger.debug("accessErr() 호출 - 접근 권한 문제 발생!");
		logger.debug("/accessErr.jsp 뷰페이지 연결");
		
		logger.debug("auth : " + auth);
		logger.debug("auth : " + auth.getAuthorities());
		
		List authList = (List) auth.getAuthorities();
		logger.debug("authList.get(0).toString() : " + authList.get(0).toString());
		if(authList.get(0).toString().equals("ROLE_MEMBER")) {
			logger.debug("사용자 권한! -> 접근 권한에 따른 페이지 이동");
		}
	}
	
	// 로그인 페이지
	@GetMapping(value = "/customLogin")
	public void myLoginPage() throws Exception{
		logger.debug("myLoginPage() 호출");
		logger.debug("/custom.jsp 뷰페이지 연결");		
	}
	
	// 로그아웃
	@GetMapping(value = "/logout")
	public void myLogoutPage() throws Exception{
		logger.debug(" myLogoutPage() 실행 ");
	}
	
}
