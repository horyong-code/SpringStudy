package com.itwillbs.web;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
 
// @WebAppConfiguration : 스프링 MVC로 컨트롤러를 테스트하겠다 [없으면 컨트롤러 제외 테스트]
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)
public class SampleControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleControllerTest.class);
	
	@Inject
	private WebApplicationContext wac;
	
	// MockMvc : 요청 (Request), 응답 (Response)을 처리하는 테스트용 객체
	private MockMvc mockMvc;
	
	// @Test
	// @Before : @Test 실행 전에 반드시 처리해야하는 메서드
	@Before // JUnit
	public void setUp() {
		// MockMvc 객체를 생성 (준비)
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		logger.debug(" MockMvc 객체를 생성 완료! (테스트 준비) ");
	}
	
	@Test
	public void controllerTest() {
		// 서버없이 컨트롤러를 테스트
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
