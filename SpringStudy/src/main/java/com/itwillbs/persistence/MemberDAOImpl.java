package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 스프링이 해당 파일이 MemberDAO 역할을 수행하는 객체로
//               인식되게 하는 코드

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	// 디비 연결 정보 (자동 연결, mapper 접근...) 처리하는 객체가 필요하다
	// => root-context.xml에서 생성되어있는 객체 (빈)을 주입
	
	@Inject
	private SqlSession sqlSession;
	
	// mapper 위치 (연결 정보) 
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";

	@Override
	public String getTime() {
		// DB 연결
		// SQL 구문 작성 -> mapper.XML 파일
		// SQL 구문 실행
//		sqlSession.selectOne("SQL 구문의 위치 정보");
		String time = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		System.out.println(" SQL 구문 실행 완료! ");
		System.out.println(" time : " + time);
		
		return time;
	}

	@Override
	public void insertMember(MemberVO vo) {
		logger.debug(" insertMember(MemberVO vo) 호출 - 시작 ");
		logger.debug(" MyBatis가 mapper에 접근 ");
		logger.debug(" SQL 구문 실행 ");
		logger.debug(" SQL 구문 실행 결과 발생 ");
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
		logger.debug(" insertMember(MemberVO vo) 호출 - 끝 ");
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		logger.debug(" loginMember(MemberVO vo) 호출 ");
		logger.debug(" DAO -> mapper 호출 ");
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		return resultVO;
	}

	@Override
	public MemberVO loginMember(String userid, String boardpw) {
		logger.debug(" loginMember(String userid, String userpw) 호출 ");
//		MemberVO vo = new MemberVO();
//		vo.setUserid(userid);
//		vo.setUserpw(userpw);
		
		// 전달된 정보가 하나의 객체 (VO) 저장이 불가능한 경우 => JOIN
		// => 하나의 형태로 만들어서 mapper로 전달
		// Map<K,V> 컬렉션 사용
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", boardpw);
		
		MemberVO resultVO =
		sqlSession.selectOne(NAMESPACE + ".loginMember", paramMap);
		// => mapper로 전달 가능한 객체는 1개뿐
		
		return resultVO;
	}

	@Override
	public MemberVO getMember(String userid) {
		logger.debug(" getMember(String userid) 호출 ");
//		MemberVO resultVO = // 수정 O
//		sqlSession.selectOne(NAMESPACE + ".getMember", userid);
//		return resultVO;
		
		return sqlSession.selectOne(NAMESPACE + ".getMember", userid); // 수정 X
	}

	@Override
	public int updateMember(MemberVO vo) {
		logger.debug(" updateMember(MemberVO vo) 호출 ");
		return sqlSession.update(NAMESPACE + ".updateMember", vo);
	}

	@Override
	public int deleteMember(MemberVO vo) {
		logger.debug(" deleteMember(MemberVO vo) 호출 ");
		return sqlSession.delete(NAMESPACE + ".deleteMember", vo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		logger.debug(" getMemberList() 호출 ");
		return sqlSession.selectList(NAMESPACE + ".getList");
		// <E> 타입으로 구체화되어있는 배열 리턴 -> resultType="E"
	}

}
