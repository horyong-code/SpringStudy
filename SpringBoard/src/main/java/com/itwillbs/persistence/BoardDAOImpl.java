package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";

	@Inject
	private SqlSession sqlSession;
	
	// 글 쓰기
	@Override
	public void insertBoard(BoardVO vo) throws Exception{
		logger.debug(" DAO : insertBoard(BoardVO vo) ");
		sqlSession.insert(NAMESPACE + ".insertBoard", vo);
	}

	// 글 목록
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		logger.debug(" DAO : getBoardListAll() ");
		return sqlSession.selectList(NAMESPACE + ".listALL");
	}

	// 글 정보
	@Override
	public BoardVO getBoard(int bno) throws Exception {
		logger.debug(" DAO : getBoard(int bno) ");
		return sqlSession.selectOne(NAMESPACE + ".getBoard", bno);
	}

	// 글 수정
	@Override
	public int updateBoard(BoardVO vo) throws Exception {
		logger.debug(" DAO : updateBoard(BoardVO vo) ");
		return sqlSession.update(NAMESPACE + ".updateBoard", vo);
	}

	// 글 조회수 증가
	@Override
	public void updateViewCnt(int bno) throws Exception {
		logger.debug(" DAO : updateViewCnt(int bno) ");
		sqlSession.update(NAMESPACE + ".updateViewCnt", bno);
		
	}

	// 글 삭제
	@Override
	public int deleteBoard(int bno) throws Exception {
		logger.debug(" DAO : deleteBoard(int bno) ");
		return sqlSession.delete(NAMESPACE + ".deleteBoard", bno);
	}

	// 글 목록 (페이징 처리)
	@Override
	public List<BoardVO> getBoardListPage(int page) throws Exception {
		logger.debug(" DAO : getBoardListPage(int page) ");
		
		// 페이징 처리 계산
		// page 1 => 1 ~ 10, page 2 => 11 ~ 20
		// page 1 => limit 0, 10 [1번부터 10개]
		// page 2 => limit 10, 10 [11번부터 10개]
		// page 3 => limit 20, 10 [21번부터 10개]
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE + ".listPage", page);
	}

	// 글 목록 (페이징 처리) - 오버로딩
	@Override
	public List<BoardVO> getBoardListPage(Criteria cri) throws Exception {
		logger.debug(" DAO : getBoardListPage(Criteria cri) ");
		
		return sqlSession.selectList(NAMESPACE + ".listPage", cri);
	}

	// 글 전체 개수
	@Override
	public int getBoardCount() throws Exception {
		logger.debug(" DAO : getBoardCount() ");
		
		return sqlSession.selectOne(NAMESPACE + ".countBoard");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
