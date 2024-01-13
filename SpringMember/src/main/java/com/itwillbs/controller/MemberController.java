package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping(value = "/members/*") // 컨트롤러를 구분하는 주소 매핑 (.me / .bo) -> 뷰 페이지 바뀜
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 서비스 객체 주입
	@Inject
	private MemberService mService;
	
	// http://localhost:8088/controller/join
	// http://localhost:8088/controller/members/join
	// http://localhost:8088/members/join
	// 회원 가입 (정보 입력) - 폼 호출 GET (조회, 입력 [패턴 1])
//	@RequestMapping(value = "/MemberJoin", method = RequestMethod.GET)
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	// /join -> GET 방식 -> memberJoinGET 호출
	public void memberJoinGET() {
		logger.debug(" /members/join 호출 -> memberJoinGET() 호출 ");
		// 연결된 뷰페이지로 이동
		logger.debug(" /views/members/join.jsp 페이지로 이동 ");	
	}
	
	// 회원 가입 (정보 처리) - 폼 처리 POST (처리 [패턴 2, 3])
//	@RequestMapping(value = "/MemberJoinAction", method = RequestMethod.POST)
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	// /join -> POST 방식 -> memberJoinPOST 호출
	public String memberJoinPOST(/* @ModelAttribute */ MemberVO vo) {
		logger.debug(" memberJoinPOST() 호출 ");
		// MemberVO vo = new MemberVO();
		// vo.setUserid(request.getParameter("userid"));
		// 한글 처리 (인코딩 설정) => 필터
		// 전달 정보 저장
		
		// 전달 정보 저장
		logger.debug(" vo : " + vo);
		
		// DB에 정보를 저장 => 서비스 객체 사용 (호출)
		// new MemberDAO().method() 호출 : 객체 생성 -> 메서드 호출 (강한 결합) => 서비스 [인터페이스]
		logger.debug(" 서비스 회원가입 동작을 호출 - 시작");
		mService.memberJoin(vo);
		logger.debug(" 서비스 회원가입 동작을 호출 - 끝");
		
		
		// 페이지 이동 (로그인 페이지 - /members/login)
		return "redirect:/members/login"; // 아직 매핑 X
	}
	
	// http://localhost:8088/members/login
	// 로그인 - 정보 입력 (GET)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void memberLoginGET() {
		logger.debug(" /members/login 호출 -> memberLoginGET() 실행 ");
		logger.debug(" 연결된 뷰페이지 (/views/members/login.jsp) 이동 ");
	}
	
	// 로그인 - 정보 처리 (POST)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memberLoginPOST(MemberVO vo, HttpSession session) {
		logger.debug(" /members/login.jsp - POST 방식 호출 -> memberLoginPOST() 실행 ");
		
		// 전달 정보 저장 (파라미터 - userid, userpw)
		logger.debug(" 로그인 처리 - vo : " + vo);
		
		// DB 접근 -> 서비스 접근 -> 로그인 처리
		MemberVO resultVO = mService.memberLogin(vo);
		
		// 로그인 결과에 따른 페이지 이동
		if(resultVO != null) {
			// O -> /members/main 페이지 호출 (리다이렉트), 세션 아이디정보 저장
			session.setAttribute("id", resultVO.getUserid()); 
			// 리다이렉트 유지 : 세션, 어플리케이션 (페이지, 리퀘스트 X)
			return "redirect:/members/main";
			// return "/실행주소" - 뷰 페이지 연결
			// return "redirect:/실행주소" - 뷰 페이지 이동
		} else {
			// X -> /members/login 페이지 호출 (리다이렉트)
			return "redirect:/members/login";	
		}
	}
	
	// http://localhost:8088/members/main
	// 메인 페이지 구현
	@RequestMapping(value = "/main", method = RequestMethod.GET) // 조회, 입력, 출력 -> GET
	public void mainGET() {
		logger.debug("/members/main 호출 -> mainGET() 실행");
		
		logger.debug("/members/main.jsp 뷰페이지 이동");
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String memberLogoutGET(HttpSession session) { // 매개변수 : 전 페이지에서 받아옴
		logger.debug("/members/logout 호출 -> memberLogoutGET() 실행");
		
		// 세션정보 초기화
		session.invalidate();
		logger.debug("session.invalidate() 실행 -> 세션 초기화 (로그아웃)");
		
		// 페이지 이동 (메인 페이지)
		return "redirect:/members/main";
	}
	
	// 회원정보 조회
	@GetMapping(value = "/info")
	public void memberInfoGET(HttpSession session, Model model) {
		logger.debug("/members/info 호출 -> memberInfoGET() 실행");
		// ID 정보 받아오기 (세션 영역)
		String userid = (String) session.getAttribute("id");
		logger.debug("아이디 정보 : " + userid);
		
		// 서비스 -> id를 사용해서 회원정보 모두 조회 동작
		MemberVO vo = mService.memberInfo(userid);
		logger.debug("vo : " + vo);
		
		// DB에서 조회된 결과를 view 페이지로 전달 => Model 객체 (Controll <-> View)
		model.addAttribute("vo", vo);
		
		// 이름이 없는 경우 전달되는 데이터 클래스 타입의 첫 글자를 소문자로 바꿔서 이름으로 사용
		model.addAttribute(mService.memberInfo(userid));
		
		// 페이지 이동 (/members/info.jsp)
	}
	
	
	// 회원정보 수정 GET - 기존의 회원정보를 가져와서 출력
	@GetMapping(value = "/update")
	public void memberUpdateGET(HttpSession session, Model model) {
		logger.debug("/members/update 호출 -> memberUpdateGET() 실행");
		
		String userid = (String) session.getAttribute("id");
		
		model.addAttribute("vo", mService.memberInfo(userid));
	}
	
	// 회원정보 수정 POST - 수정된 회원정보를 디비에서 변경
	@PostMapping(value = "/update")
	public String memberUpdatePOST(MemberVO vo) {
		logger.debug("/members/update -> memberUpdatePOST()");
		// 한글처리 인코딩 (생략 - 필터 사용)
		// 전달정보 저장 (폼태그 - 파라미터)
		logger.debug("수정할 정보 : " + vo);     
		
		// 서비스 - 회원정보 수정하는 동작
		mService.memberUpdate(vo);
		
		// 메인페이지로 이동
		return "redirect:/members/main";
	}
	
	// 회원정보 삭제 GET - 비밀번호 입력
	@GetMapping(value = "/delete")
	public void memberDeleteGET() {
		logger.debug("/members/delete 호출 -> memberUpdateGET() 실행");
		logger.debug("/members/delete.jsp 페이지 이동");
	}
	
	// 회원정보 삭제 POST - 회원정보 삭제
	@PostMapping(value = "/delete")
	public String memberDeltePOST(MemberVO vo, HttpSession session) {
		logger.debug("/members/delete -> memberDeletePOST()");
		// 전달 정보 저장
		logger.debug("삭제할 정보 : " + vo);  
//		vo.setUserid((String) session.getAttribute("id"));
		
		// 서비스 - 회원 정보 삭제 (삭제 성공 / 실패)
		int result = mService.memberDelete(vo);
		// 성공 -> 메인페이지 이동
		if(result == 1) {
			session.invalidate();
			logger.debug("/members/main.jsp 페이지 이동");
			return "redirect:/members/main";
		}
		// 실패 -> 삭제 페이지 (이전페이지)로 이동
		return "redirect:/members/delete"; // 기본 주소 이동 : GET
	}
	
	// 회원정보 조회 (관리자)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void memberListGET(Model model) {
		logger.debug("/members/list 호출 -> memberListGET() 실행");
		
		// 서비스 - 회원 목록 모두 조회 동작
		// 전달받은 정보를 view 페이지로 전달 (Model 객체 생성)
		model.addAttribute("memberList", mService.memberList());
//		model.addAttribute(mService.memberList());
		// => memberVOList (이름이 생략된 경우의 이름)
		
		// /members/list.jsp 페이지 이동
	}
}
