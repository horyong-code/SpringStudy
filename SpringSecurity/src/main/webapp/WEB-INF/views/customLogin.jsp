<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>customLogin.jsp</h1>
	
	<form action="/login" method="POST">
		<fieldset>
			<legend>ITWILL 로그인</legend>
			<label>아이디 : <input type="text" name="username"></label><br>
			<label>비밀번호 : <input type="password" name="password"></label><br>
			<!-- csrf 토큰 정보 : 사이트 간 위조방지 토큰 설정 -->
			<input type="hidden" value="${_csrf.token }" name="${_csrf.parameterName }"><br> <!-- NullPointerException -> 토큰 필요 : _csrf -->
			<input type="submit" value="로그인">
		</fieldset>
	</form>

</body>
</html>