package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberDAOTest {
	// 컨트롤러 대신 테스트 전용 클래스
	
	// 로거 객체 생성 (출력 전용 객체)
	// mylogger 단축어
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	// MemberDAO 객체가 필요함 => memberDAOImpl 객체가 주입 (DI)
	@Inject
	private MemberDAO mdao; // 업캐스팅
	
	//@Test
	public void mybatis_첫쿼리구문실행_테스트() {
		System.out.println("결과 : " + mdao.getTime());
		logger.info("결과 : " + mdao.getTime());
		logger.info("@@@@@@@@@@@@@@@@@@@@@"); // li 단축어
		logger.debug("$$$$$$$$$$$$$$$$$$$$$"); // ld 단축어
	}
	
	//@Test
	public void 회원가입_테스트() {
		logger.debug(" 회원가입_테스트() - 시작 ");
		// 임시 회원 정보
		MemberVO vo = new MemberVO();
		vo.setUserid("admin2");
		vo.setUserpw("1234");
		vo.setUsername("관리자2");
		vo.setUseremail("admin2@admin.com");
		
		mdao.insertMember(vo);
		
		logger.debug(" 회원가입_테스트() - 종료 ");
	}
	
	//@Test
	public void 로그인_테스트() {
		logger.debug(" 로그인_테스트() ");
		// 임시 계정
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("1234");
		
		MemberVO resultVO = mdao.loginMember("admin", "1234");
		
		logger.debug(" 결과 : " + resultVO);
		
		if(resultVO != null) {
			logger.debug(" (❁´◡`❁) 로그인 성공!!! ");
		}else {
			logger.debug(" (┬┬﹏┬┬) 로그인 실패!!! ");
		}
	}
	
	//@Test
	public void 회원정보조회_테스트() {
		logger.debug(" 사용자의 ID 사용해서 회원 정보 모두 조회 ");
		MemberVO vo = mdao.getMember("admin");
		logger.debug(" ╰(*°▽°*)╯ 회원 정보 : " + vo);
	}
	
	//@Test
	public void 회원정보_수정_테스트() {
		logger.debug(" 회원정보_수정_테스트() ");
		MemberVO vo = new MemberVO();
		vo.setUserid("admin2");
		vo.setUserpw("1234");
		vo.setUsername("관리자2");
		vo.setUseremail("admin2@admin2.com");
		
		int result = mdao.updateMember(vo);
		if(result == 1) {
			logger.debug(" (❁´◡`❁) 회원 정보 변경 성공!!! ");
			logger.debug(" 변경된 회원 정보 : " + mdao.getMember(vo.getUserid()));}
		else {
			logger.debug(" (┬┬﹏┬┬) 회원 정보 변경 실패!!! ");
		}
	}
	
	//@Test
	public void 회원정보_삭제_테스트() {
		logger.debug(" 회원정보_삭제_테스트() ");
		MemberVO vo = new MemberVO();
		vo.setUserid("admin2");
		vo.setUserpw("12345555");
		
		int result = mdao.deleteMember(vo);
		if(result == 1) {
		logger.debug(" (❁´◡`❁) 회원 정보 삭제 성공!!! ");}
		else {
			logger.debug(" (┬┬﹏┬┬) 회원 정보 삭제 실패!!! ");
		}
	}
	
	// 회원 정보 리스트 조회
	@Test
	public void 회원정보리스트조회_테스트() {
		logger.debug(" 회원정보리스트조회_테스트() ");
		List<MemberVO> memberList = mdao.getMemberList();
		
		logger.debug("" + memberList);
		
		for(MemberVO vo : memberList) {
			logger.debug("id : " + vo.getUserid() + "//pw : " + vo.getUserpw());
		}
	}
}
