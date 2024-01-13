package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardService {
	
	// 글쓰기 - 예외 던지기
	public void boardWrite(BoardVO vo) throws Exception;
	
	// 글 목록 가져오기 - 예외 던지기
	public List<BoardVO> boardListAll() throws Exception;
	
	// 글 정보 가져오기 - 예외 던지기
	public BoardVO getBoard(int bno) throws Exception;
	
	// 글 수정 - 예외 던지기
	public int boardModify(BoardVO vo) throws Exception;
	
	// 글 조회수 증가 - 예외 던지기
	public void incrementViewCnt(int bno) throws Exception;
	
	// 글 삭제 - 예외 던지기
	public int boardRemove(int bno) throws Exception;
	
	// 글 목록 (페이징 처리) - 예외 던지기
	public List<BoardVO> boardListPage(Criteria cri) throws Exception;
	
	// 글 전체 개수 - 예외 던지기
	public int totalBoardCount() throws Exception;

}
