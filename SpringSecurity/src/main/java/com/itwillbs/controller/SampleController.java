package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/sample/*")
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	// http://localhost:8088/sample/all
	@GetMapping(value = "/all")
	public void doALL() throws Exception{
		logger.debug("/sample/all -> doALL() 실행");
	}
	
	// http://localhost:8088/sample/member
	@GetMapping(value = "/member")
	public void doMember() throws Exception{
		logger.debug("/sample/member -> doMember() 실행");
	}
	
	// http://localhost:8088/sample/admin
	@GetMapping(value = "/admin")
	public void doAdmin() throws Exception{
		logger.debug("/sample/admin -> doAdmin() 실행");
	}
}
