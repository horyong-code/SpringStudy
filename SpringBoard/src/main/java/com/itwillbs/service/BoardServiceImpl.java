package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO bdao;

	// 글쓰기
	@Override
	public void boardWrite(BoardVO vo) throws Exception {
		logger.debug(" S : boardWrite(BoardVO vo) ");
		bdao.insertBoard(vo);
	}

	// 글 목록
	@Override
	public List<BoardVO> boardListAll() throws Exception {
		logger.debug(" S : boardListAll() ");
		return bdao.getBoardListAll();
	}

	// 글 정보
	@Override
	public BoardVO getBoard(int bno) throws Exception {
		logger.debug(" S : getBoard(int bno) ");
		return bdao.getBoard(bno);
	}

	// 글 수정
	@Override
	public int boardModify(BoardVO vo) throws Exception {
		logger.debug(" S : boardModify(BoardVO vo) ");
		return bdao.updateBoard(vo);
	}

	// 글 조회수 증가
	@Override
	public void incrementViewCnt(int bno) throws Exception {
		logger.debug(" S : incrementViewCnt(int bno) ");
		bdao.updateViewCnt(bno);
		
	}
	
	// 글 삭제
	@Override
	public int boardRemove(int bno) throws Exception {
		logger.debug(" S : boardRemove(int bno) ");
		return bdao.deleteBoard(bno);
	}

	// 글 목록 (페이징 처리)
	@Override
	public List<BoardVO> boardListPage(Criteria cri) throws Exception {
		logger.debug(" S : boardListPage(Criteria cri) ");
		return bdao.getBoardListPage(cri);
	}

	// 글 전체 개수
	@Override
	public int totalBoardCount() throws Exception {
		logger.debug(" S : totalBoardCount() ");
		return bdao.getBoardCount();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
