<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/listAll.jsp</h1>
${result }<br>
${pageVO }<br>

<div class="box">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판</h3>
	</div>

	<div class="box-body">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th style="width: 10px">BNO</th>
					<th>TITLE</th>
					<th>WRITER</th>
					<th>REGDATE</th>
					<th style="width: 40px">VIEWCNT</th>
				</tr>
				<c:forEach var="boardVO" items="${boardList }">
				<tr>
					<td>${boardVO.bno }</td>
					<td>
					<a href="/board/read?bno=${boardVO.bno }&page=${pageVO.cri.page}">${boardVO.title }</a>
					</td>
					<td>${boardVO.writer }</td>
					<td><fmt:formatDate value="${boardVO.regdate }" dateStyle="short" pattern="yy-MM-dd [E]"/> </td>
					<td><span class="badge bg-orange">${boardVO.viewcnt }</span></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="box-footer clearfix">
		<ul class="pagination pagination-sm no-margin pull-right">
			<c:if test="${pageVO.prev }">
			<li><a href="/board/listPage?page=${pageVO.startPage - 1 }">«</a></li>
			</c:if>
			<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
				<li ${pageVO.cri.page == i? "class='active'" : "" }>
					<a href="/board/listPage?page=${i }">${i }</a>
				</li>
			</c:forEach>
			<c:if test="${pageVO.next }">
			<li><a href="/board/listPage?page=${pageVO.endPage + 1 }">»</a></li>
			</c:if>
		</ul>
	</div>
</div>

<script>
// JSP (Java) -> JSTL, EL -> HTML -> JavaScript
// 	alert("${result}");
	var result = "${result}";
	
	if(result == "CREATEOK"){
		alert(" 글 쓰기 완료! ");
	}else if(result == "MODIFYOK"){
		alert(" 글 수정 완료! ")
	}else if(result == "REMOVEOK"){
		alert(" 글 삭제 완료! ")
	}
</script>

<%@ include file="../include/footer.jsp"%>
