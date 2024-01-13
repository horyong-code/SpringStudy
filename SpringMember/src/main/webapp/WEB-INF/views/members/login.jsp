<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>http://localhost:8088/members/login 호출</h1>
	<h1>/views/members/login.jsp</h1>
	
	<form method="POST">
		<fieldset>
			<legend>로그인</legend>
			<label>아이디 : <input type="text" name="userid"></label>
			<label>비밀번호 : <input type="password" name="userpw"></label>
			<hr>
			<input type="submit" value="로그인">
			<input type="button" value="회원가입" onclick="location.href='/members/join';">
		</fieldset>
	</form>
</body>
</html>