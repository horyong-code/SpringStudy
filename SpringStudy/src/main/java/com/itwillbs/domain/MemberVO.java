package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

// @Data : 필요한 기본 설정값을 지정

@Data
public class MemberVO {
	// Value Object => DTO 개념으로 사용 (DB 테이블 정보를 저장하는 객체)
	
	// tbl_member 테이블의 정보를 저장
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private Timestamp regdate;
	private Timestamp updatedate;
	
	// private String uAbc; -> 첫 글자 소문자, 두 번째 글자 대문자 피하기
	
}
