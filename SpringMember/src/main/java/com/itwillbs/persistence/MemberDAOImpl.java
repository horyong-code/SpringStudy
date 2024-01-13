package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

@Repository // 클래스 - 인터페이스 객체 인식
public class MemberDAOImpl implements MemberDAO {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";
	
	// DB에 접근할 객체
	@Inject
	private SqlSession sqlSession;
	
	@Override // 추상 메서드 오버라이딩
	public void insertMember(MemberVO vo) {
		logger.debug(" mapper (DB) 회원가입 처리 구문 실행 - 시작 ");
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
		logger.debug(" mapper (DB) 회원가입 처리 구문 실행 - 끝 ");

	}

	@Override
	public MemberVO selectLoginMember(MemberVO vo) {
		logger.debug(" DAO - 로그인 처리 selectLoginMember(MemberVO vo) ");
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		logger.debug(" 결과 : " + resultVO);
		
		return resultVO;
	}

	@Override
	public MemberVO getMember(String userid) {
		logger.debug(" DAO - 회원정보 조회 getMember(String userid) ");
		return sqlSession.selectOne(NAMESPACE + ".getMember", userid);
	}

	@Override
	public void updateMember(MemberVO vo) {
		logger.debug(" DAO - 회원정보 수정 updateMember(MemberVO vo) ");
		sqlSession.update(NAMESPACE + ".updateMember", vo);
	}

	@Override
	public int deleteMember(MemberVO vo) {
		logger.debug(" DAO - 회원정보 삭제 deleteMember(MemberVO vo) ");
		return sqlSession.delete(NAMESPACE + ".deleteMember", vo); 
		// insert, update, delete : 행 개수 리턴 (int)
	}

	@Override
	public List<MemberVO> getMemberList() {
		logger.debug(" DAO - 회원목록 조회 memberList() ");
		return sqlSession.selectList(NAMESPACE + ".getMemberList");
	}
	
	
	
	
	
	
}
