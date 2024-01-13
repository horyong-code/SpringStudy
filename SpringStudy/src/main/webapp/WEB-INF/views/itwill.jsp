<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/WEB-INF/views/itwill.jsp</h1>
	
	<h2>전달된 msg 정보를 출력</h2>
	JSP : <%=request.getParameter("msg") %> <hr>
	EL 표현식 : ${param.msg } <hr>
	<h2>modelAttribute 어노테이션을 사용하여 정보를 전달 -> EL 표현식 출력</h2>
	Spring - EL 표현식 : ${msg } <hr>
	Spring - EL 표현식 : ${requestScope.msg } <hr>
	
	${age }

</body>
</html>