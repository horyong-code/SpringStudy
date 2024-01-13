package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {
	
	// 추상 메서드로 처리 동작 구현 선언
	
	// 디비의 시간 정보 조회
	public String getTime();
	
	// 회원 가입 처리 동작
	public void insertMember(MemberVO vo);
	
	// 로그인 처리 동작
	public MemberVO loginMember(String userid, String userpw);
	public MemberVO loginMember(MemberVO vo); // 데이터를 한 번에 보내는 게 좋음
	
	// 회원 정보 조회
	public MemberVO getMember(String userid);
	
	// 회원 정보 수정
	public int updateMember(MemberVO vo);
	
	// 회원 정보 삭제
	public int deleteMember(MemberVO vo);
	
	// 회원 정보 리스트 조회
	public List<MemberVO> getMemberList(); // List : java.util -> <MemberVO> 형태로 구체화

}
