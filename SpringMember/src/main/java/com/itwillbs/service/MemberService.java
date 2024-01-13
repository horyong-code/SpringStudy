package com.itwillbs.service;

import java.util.ArrayList;
import java.util.List;

import com.itwillbs.domain.MemberVO;

/**
 * 서비스 계층 (비지니스 로직 계층) : 사용자의 요구 사항을 구현하는 단계
 * 
 * => 컨트롤러 - DAO를 연결하는 계층 (접착제)
 * => 외부 호출이 영속 계층 (DB)에 종속적인 상황을 막아줌
 *
 */
public interface MemberService {
	
	// 구현하고자 하는 동작을 추상 메서드로 선언
	public void memberJoin(MemberVO vo);
	
	// 로그인 처리 동작
	public MemberVO memberLogin(MemberVO vo);
	
	// 회원정보 조회 동작
	public MemberVO memberInfo(String userid);
	
	// 회원정보 수정
	public void memberUpdate(MemberVO vo);
	
	// 회원정보 삭제
	public int memberDelete(MemberVO vo);
	
	// 회원정보 조회
	public List<MemberVO> memberList();

}
