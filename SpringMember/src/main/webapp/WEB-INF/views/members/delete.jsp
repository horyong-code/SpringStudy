<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>http://localhost:8088/members/delete 호출</h1>
	<h1>/views/members/delete.jsp</h1>
	
	<form method="POST">
		<fieldset>
			<legend>회원정보 삭제</legend>
			<input type="hidden" name="userid" value="${id }" readonly>
			<label>비밀번호 입력 : <input type="password" name="userpw" required></label>
			<hr>
			<input type="submit" value="회원정보삭제">
		</fieldset>
	</form>

</body>
</html>