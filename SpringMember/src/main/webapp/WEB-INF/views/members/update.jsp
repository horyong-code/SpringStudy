<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update</title>
</head>
<body>
	<h1>http://localhost:8088/members/update 실행</h1>
	<h1>views/members/update.jsp</h1>
	
	<!-- 로그인 여부 (세션 정보)에 따라서 페이지 이동 (JSTL) -->
	<c:if test="${empty id }">
		<c:redirect url="/members/login"/>
	</c:if>
	
	<form method="POST">
		<fieldset>
			<legend>스프링MVC 회원정보수정</legend>
			<label>아이디 : <input type="text" name="userid" value="${vo.userid }" readonly></label><br>
			<label>비밀번호 : <input type="password" name="userpw" required></label><br>
			<label>이름 : <input type="text" name="username" value="${vo.username }" required></label><br>
			<label>이메일 : <input type="email" name="useremail" value="${vo.useremail }" required></label><br>
			<hr>
			<input type="submit" value="회원정보수정">
		</fieldset>
	</form>

</body>
</html>