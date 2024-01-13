<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- JQuery 라이브러리 추가 시작 (CDN) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<!-- JQuery 라이브러리 추가 끝 (CDN) -->

<!-- JQuery 사용 준비 시작 -->
<script type="text/javascript">
$(document).ready(function(){
	var cnt = 1;
	
	// 버튼 클릭시 파일 입력 버튼 추가 (input 태그 이름 변경)
	$("#addBtn").click(function(){
		$("#fileDiv").append("<input type='file' name='file" + cnt +" ' accept='image/*, application/pdf'>"); /* append : 뒤에 추가 (태그 가능) */
		cnt++;
	});
	
});
</script>
<!-- JQuery 사용 준비 끝 -->

</head>
<body>
	<h1>http://localhost:8088/fileUpload 호출</h1>
	<h1>/views/fileUpload.jsp</h1>
	
	<form action="/upload" method="POST" enctype="multipart/form-data">
		<fieldset>
			<legend>다중 파일 업로드</legend>
				<label>아이디 : <input type="text" name="userid"></label><br>
				<label>이름 : <input type="text" name="username"></label><hr>
				<input type="button" id="addBtn" value="파일 추가"><br>
				<div id="fileDiv">
				
				</div>
				<hr>
				<input type="submit" value="파일 업로드">
		</fieldset>
	</form>

</body>
</html>