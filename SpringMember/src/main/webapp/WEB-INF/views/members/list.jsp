<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
	<h1>/members/list.jsp</h1>
	
<%-- 	${requestScope } --%>
	<hr>
	${memberList }
	<hr>
	
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>회원가입일</th>
			<th>최종수정일</th>
		</tr>
		<c:forEach var="list" items="${memberList }">
		<tr>
			<td>${list.userid }</td>
			<td>${list.userpw }</td>
			<td>${list.username }</td>
			<td>${list.useremail }</td>
			<td>${list.regdate }</td>
			<td>${list.updatedate }</td>
		</tr>	
		</c:forEach>
	</table>
	<br>
	<input type="button" value="이전 페이지로" onclick="history.back();">
	<a href="javascript:history.back();">이전 페이지로</a>

</body>
</html>