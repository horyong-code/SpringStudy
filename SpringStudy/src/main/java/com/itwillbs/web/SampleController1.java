package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// @Controller 어노테이션 : 스프링이 컨트롤러로 인식하도록 함
//                          HttpServlet 상속 (X), doGET / doPOST 메서드 구현 (X)

@Controller
public class SampleController1 {
	
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController1.class);
	
	// http://localhost:8088/web/doA
	// http://localhost:8088/web/doA.me
	// 구현하고자 하는 동작을 메서드로 선언
	// @RequestMapping(value = "URI 주소", method = 전달 방식)
	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	// doA 주소를 GET 방식으로 전달하면 아래의 메서드 호출
	public void doA() {
		logger.debug(" doA() 실행 ");
		
		// 페이지 이동 (스프링이 처리)
		// => 메서드 선언 시 방법 결정
		// * 메서드의 리턴타입이 void일 때 주소이름.jsp 뷰페이지로 이동
	}
	
	// doA1 주소 사용 페이지 호출 메서드 구현

	// http://localhost:8088/web/doA1
	@RequestMapping(value = "/doA1", method = RequestMethod.GET)
	public void doA1() {
		logger.debug(" doA1() 실행 ");
	}

}
