package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService bService;

	// http://localhost:8088/board/regist
	// 글쓰기 - GET
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public void registGET() throws Exception{
		logger.debug("/board/regist -> registGET() 호출");
		logger.debug("/board/regist.jsp 뷰페이지 이동");
	}
	
	// 글쓰기 - POST
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		logger.debug("Form Submit -> registPOST() 호출");
		
		// 한글 인코딩 (필터)
		// 전달 정보 저장
		logger.debug(" vo : " + vo);
		
		// 서비스 - DB에 글쓰기 (insert) 동작 호출
		bService.boardWrite(vo);
		logger.debug(" 글 작성 완료! ");
		
		rttr.addFlashAttribute("result", "CREATEOK");
		
		logger.debug("/board/listAll 이동");
		return "redirect:/board/listAll";
	}
	
	// http://localhost:8088/board/listAll
	// 게시판 리스트 - GET
	@GetMapping(value = "/listAll")
	public String listALLGET(Model model, @ModelAttribute("result") String result, 
			HttpSession session) throws Exception {
		logger.debug(" /board/listAll -> listALLGET() ");
		
		session.setAttribute("viewcntCheck", true);
		// viewcntCheck == true일 때만 조회수 1 증가
		
		// 서비스 - DB에 저장된 글을 가져오기
		List<BoardVO> boardList = bService.boardListAll();
		logger.debug(" @@@ " + boardList);
		
		// 데이터를 연결된 뷰페이지로 전달 (Model)
		model.addAttribute("boardList", boardList);
		// 파라미터명 입력 X -> boardVOList (List는 파라미터명 입력하기)
		// model.addAttribute("boardList", bService.boardListAll());
		
		return "/board/listAll";
	}
	
	// 글 본문 보기 - GET
	@GetMapping(value = "/read")
	public void readGET(@RequestParam("bno") int bno, Model model, 
			HttpSession session) throws Exception{
		logger.debug("/board/read -> readGET() ");
		
		// 전달 정보 저장
		logger.debug(" bno : " + bno);
		
		if((boolean) session.getAttribute("viewcntCheck")) { // 형 변환
			// 서비스 - bno에 해당하는 글 조회수 1 증가 
			//          (페이지 호출 당 1번씩 증가 / read 페이지 새로고침 시 증가 X)
			bService.incrementViewCnt(bno);
			// RedirectAttributes = 리다이렉트에만 사용 가능
			session.setAttribute("viewcntCheck", false);
		}
		
		// 서비스 - bno에 해당하는 특정 글 정보만 조회
		// 연결된 뷰페이지로 이동시 정보를 전달
		model.addAttribute("resultVO", bService.getBoard(bno));
		
		// /board/read.jsp 뷰페이지로 이동
	}
	
	// 게시판 글 수정 -> GET
	// http://localhost:8088/board/modify?bno=1
	@GetMapping(value = "/modify")
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{ // 예외 던지기
		// ModelAttribute <-> RequestParam
		// ModelAttribute : Controller -> View
		// RequestParam : Controller
		logger.debug("/board/modify -> modifyGET() 호출");
		logger.debug("수정할 글번호 : " + bno);
		
		// 기존의 글 정보를 가져와서 화면에 출력
		BoardVO resultVO = bService.getBoard(bno);
		
		// 글정보를 Model 객체 저장
		model.addAttribute("resultVO", resultVO);
		
		// 뷰페이지로 이동
	}
	
	// 게시판 글 수정 -> POST
	@PostMapping(value = "/modify")
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		logger.debug("/modify form -> modifyPOST()"); // 405 오류 -> 404 오류
		// 전달된 정보 저장 (수정할 정보)
		logger.debug(" 수정할 정보 : " + vo);
		
		// 서비스 - 정보 수정 동작
		int result = bService.boardModify(vo);
		
		// 처리 완료 후 페이지 이동 (리스트)
		// + 수정 완료! 리스트에서 출력
		if(result == 1) {
			rttr.addFlashAttribute("result", "MODIFYOK");
		}
		return "redirect:/board/listAll";
	}
	
	// 게시판 글 삭제 -> POST
	@PostMapping(value = "/remove")
	public String removePOST(@ModelAttribute("bno") int bno, RedirectAttributes rttr) throws Exception{
		// @ModelAttribute("bno") int bno -> int bno [생략 가능]
		logger.debug("/read form -> removePOST() 호출");	
		
		// 서비스 - 글 삭제 동작
		int result = bService.boardRemove(bno);
		
		// "글 삭제 완료!" 메세지 출력
		if(result == 1) {
			rttr.addFlashAttribute("result", "REMOVEOK");
		}
				
		return "redirect:/board/listAll";
		// 뷰 페이지 연결
		// 뷰 페이지 이동 - 이동하는 페이지가 미리 연결되어있으면 됨
	}
	
	/**
	 *  페이징 처리
	 *  0) 반드시 GET 방식으로 처리
	 *  1) 원하는 만큼의 데이터를 가져와서 출력
	 *  2) 페이지 블럭 생성
	 *  3) 본문 / 수정 / 삭제 등 처리 후 리스트 이동 시 기존 정보를 유지
	 *  
	 *  a 태그 : 네이버 쇼핑 / 유사한 코드의 반복적인 동작 수행
	 *  		=> 검색 엔진 노출이 쉬움
	 *  
	 *  form 태그 : 쿠팡 / input 태그를 사용해서 처리
	 *  		=> 데이터 처리 (빠른 처리)
	 *  
	 *  
	 *  - 하단부 페이지 블럭
	 *    1) 시작 페이지 번호 = startPage
	 *    2) 끝 페이지 번호 = endPage
	 *    3) 전체 데이터 (글)의 개수 = result
	 *    4) 이전 페이지 링크 (boolean)
	 *    5) 다음 페이지 링크 (boolean)
	 *    
	 *    ex) 총 122개 / 페이지 당 10개씩 출력
	 *    - 총 페이지 : 13개
	 *    -  1페이지 : 시작 페이지 번호 '1', 끝 페이지 번호 '10' / 이전 : N, 다음 : Y
	 *    -  7페이지 : 시작 페이지 번호 '1', 끝 페이지 번호 '10' / 이전 : N, 다음 : Y
	 *    - 12페이지 : 시작 페이지 번호 '11', 끝 페이지 번호 '20' -> '13' / 이전 : Y, 다음 : N
	 */
	
	// http://localhost:8088/board/listPage
	// http://localhost:8088/board/listPage?page=1
	// http://localhost:8088/board/listPage?page=3&pageSize=15
	// 게시판 리스트 - GET
	@GetMapping(value = "/listPage")
	public String listPageGET(Model model, @ModelAttribute("result") String result, 
			HttpSession session, Criteria cri) throws Exception {
		logger.debug(" /board/listPage -> listPageGET() ");
		
		session.setAttribute("viewcntCheck", true);
		// viewcntCheck == true일 때만 조회수 1 증가
//		Criteria cri = new Criteria();
//		cri.setPage(1);
//		cri.setPageSize(20);
		
		// 서비스 - DB에 저장된 글을 가져오기
		List<BoardVO> boardList = bService.boardListPage(cri);
		logger.debug(" @@@ " + boardList);
		
		// 페이지 블럭 정보 준비 -> view 페이지 전달
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		// pageVO.setTotalCount(73728); // DB에서 직접 실행 결과 가져오기
		pageVO.setTotalCount(bService.totalBoardCount());
		model.addAttribute("pageVO", pageVO);
		
		logger.debug(" 확인 : " + pageVO);
		
		// 데이터를 연결된 뷰페이지로 전달 (Model)
		model.addAttribute("boardList", boardList);
		// 파라미터명 입력 X -> boardVOList (List는 파라미터명 입력하기)
		// model.addAttribute("boardList", bService.boardListAll());
		
		return "/board/listAll"; // 원래 목록 연결
	}
}
