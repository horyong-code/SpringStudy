<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/modify.jsp</h1>

<!-- 글수정 폼태그 시작 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 글수정</h3>
	</div>


	<form role="form" method="POST">
		<input type="hidden" value="${resultVO.bno }" name="bno">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label> <input type="text" class="form-control" id="exampleInputEmail1" value="${resultVO.writer }" name="writer" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">제 목</label> <input type="text" class="form-control" id="exampleInputEmail1" value="${resultVO.title }" name="title" required>
			</div>
			<div class="form-group">
				<label>내 용</label>
				<textarea class="form-control" rows="3" name="content">${resultVO.content }</textarea>
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-danger">글수정</button>
		</div>
	</form>
</div>
<!-- 글수정 폼태그 끝 -->

<%@ include file="../include/footer.jsp"%>