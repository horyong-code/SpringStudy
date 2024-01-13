package com.itwillbs.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

@Controller // 클래스 -> 컨트롤러 (HttpServlet 상속 X / doGet, doPost 구현 X)
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	// http://localhost:8088/fileUpload
	@GetMapping(value = "/fileUpload")
	public void fileUploadFormGET() throws Exception{
		logger.debug(" fileUploadGET() 실행 ");
		logger.debug(" fileUpload.jsp 뷰페이지 연결 ");
	}
	
	@PostMapping(value = "/upload")
	public String fileUploadPOST(MultipartHttpServletRequest multi, Model model) throws Exception{
		logger.debug(" fileUploadPOST() - 파일 업로드 처리 ");
		
		// 파일 업로드 시 전달된 모든 정보를 저장
		// logger.debug(" multi : " + multi);
		Map paramMap = new HashMap();
		
		// 파일 정보를 제외한 모든 파라미터의 이름을 가져오기
		Enumeration enu = multi.getParameterNames(); // 파라미터명 전부 저장
		while(enu.hasMoreElements()) { 
			String name = (String) enu.nextElement(); // 다운캐스팅 [파라미터명] - 파라미터명 1개 저장
			String value = multi.getParameter(name); // name에 대한 모든 값 받아오기 (getParameter는 항상 String)
//			logger.debug(" name : " + name + ", value : " + value);
			
			paramMap.put(name, value);
		}
		logger.debug(" paramMap : " + paramMap); // 객체 : {}, 배열 (목록) : []
		
		// 파일 정보를 저장
		List fileList = fileProcess(multi);
		paramMap.put("fileList", fileList);
		
		logger.debug(" paramMap : " + paramMap);
		// => 추가 동작 : DB에 전달해서 저장
		
		model.addAttribute("paramMap", paramMap);
		
		return "/fileResult";
	} // fileUploadPOST
	
	// 컨트롤러와 메서드 사이
	// 파일 정보 (이름)을 저장, 파일 업로드 처리
	private List<String> fileProcess(MultipartHttpServletRequest multi) throws Exception{
		
		// 파일의 이름을 저장
		List<String> fileList = new ArrayList<String>();
		
		// 폼태그에서 전달된 파일의 정보를 가져오기
		// (input 태그 file의 이름을 모두 가져오기)
		Iterator<String> fileNames = multi.getFileNames(); // 파라미터명 전부 저장
		while(fileNames.hasNext()) {
			// 파라미터명 1개 저장
			String fileName = fileNames.next();
			logger.debug(" fileName : " + fileName);
			
			// 전달된 파일 이름에 해당하는 MultipartFile 정보 저장 [파라미터명 -> MultipartFile (실제 파일 정보)]
			MultipartFile mFile = multi.getFile(fileName);
			String oFileName = mFile.getOriginalFilename();
			logger.debug(" oFileName : " + oFileName);
			
			// 업로드된 실제 파일의 이름을 저장
			fileList.add(oFileName);
			
			// 실제 폴더 생성
			File file = new File("D:\\springupload\\" + oFileName); // java.io import
			
			if(mFile.getSize() != 0) { // 첨부 파일이 있을 때
				if(!file.exists()) { // 파일, 디렉터리 (폴더)가 존재하는지 체크
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				} // exists
				mFile.transferTo(file);
			} // getSize
		}
		return fileList;
	}
	
	// 다운로드 처리
	@GetMapping(value = "/download")
	public void fileDownloadGET(@RequestParam("fileName") String fileName, HttpServletResponse response) throws Exception{
		logger.debug(" fileDownloadGET() 실행 ");
		logger.debug(" fileName : " + fileName);
		
		// response를 통하는 통로 생성
		OutputStream out = response.getOutputStream(); // 추상 클래스 [객체 생성 불가]
		
		// 다운로드할 파일의 정보
		String downFile = "D:\\springupload\\" + fileName;
		
		// 다운로드할 파일 객체 생성
		File file = new File(downFile);
		
		// 썸네일 처리 동작 ---------------------------------------------------
		// 1. 썸네일 파일 생성 (폴더), 화면에 원본 데이터 출력
		int lastIdx = fileName.lastIndexOf("."); // test[. 인덱스].jpg
		String tmpFileName = fileName.substring(0, lastIdx); // test
		
//		File thumbFile = new File("D:\\springupload\\" + "\\thumb\\" + tmpFileName + ".png");

		if(file.exists()) {
			// thumbFile.getParentFile().mkdirs();
			// Thumbnails.of(file).size(50, 50).outputFormat("png").toFile(thumbFile);
			
			// 2. 썸네일 파일을 바로 생성에서 화면에 출력
			Thumbnails.of(file).size(50, 50).outputFormat("png").toOutputStream(out);
		}
		
		// 썸네일 처리 동작 ---------------------------------------------------
		
		// 한글 파일의 경우 인코딩 처리
		fileName = URLEncoder.encode(fileName, "UTF-8");
		
		// 다운로드 창의 형태로 다운로드되도록 설정
		// response.setHeader("Cache-Control", "no-cache");
		// response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		
//		FileInputStream fis = new FileInputStream(file);
//		byte[] b = new byte[1024 * 8]; // 버퍼 생성
//		int data = 0;
//		
//		while((data = fis.read(b)) != -1) { // -1 = EOF
//			out.write(b, 0, data); // 버퍼의 처음부터 데이터 형태로
//		}
//		out.flush(); // 공백 채워서 전달
//		
//		fis.close(); // 자원 해제
//		out.close(); // 자원 해제
		
	}
	
} // Controller
