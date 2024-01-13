package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

public interface BoardDAO {
	
	// 글 쓰기 - 예외 던지기
	public void insertBoard(BoardVO vo) throws Exception;
	
	// 글 목록 - 예외 던지기
	public List<BoardVO> getBoardListAll() throws Exception;
	
	// 글 정보 - 예외 던지기
	public BoardVO getBoard(int bno) throws Exception;
	
	// 글 수정 - 예외 던지기
	public int updateBoard(BoardVO vo) throws Exception;
	
	// 글 조회수 증가 - 예외 던지기
	public void updateViewCnt(int bno) throws Exception;
	
	// 글 삭제 - 예외 던지기
	public int deleteBoard(int bno) throws Exception;
	
	// 글 목록 (페이징 처리)
	public List<BoardVO> getBoardListPage(int page) throws Exception;
	
	// 글 목록 (페이징 처리) - 오버로딩
	public List<BoardVO> getBoardListPage(Criteria cri) throws Exception;
	
	// 글 전체 개수
	public int getBoardCount() throws Exception;

}
