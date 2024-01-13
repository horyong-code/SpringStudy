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
	
	<!-- <form action="/members/join" method="POST"> -->
	<!-- action="/members/join" 주소 생략 가능 -> action 속성 정보가 없으면 자기 자신의 주소를 호출 -->
	<form method="POST">
		<fieldset>
			<legend>스프링MVC 회원가입</legend>
			<label>아이디 : <input type="text" name="userid"></label><br>
			<label>비밀번호 : <input type="password" name="userpw"></label><br>
			<label>이름 : <input type="text" name="username"></label><br>
			<label>이메일 : <input type="email" name="useremail"></label><br>
			<input type="submit" value="회원가입">
		</fieldset>
	</form>

</body>
</html>