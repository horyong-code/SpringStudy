<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<h1>/board/regist.jsp</h1>

<!-- 글쓰기 폼태그 시작 -->
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">ITWILL 게시판 글쓰기</h3>
	</div>


	<form role="form" method="POST">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">작성자</label> <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Enter writer" name="writer" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">제 목</label> <input type="text" class="form-control" id="exampleInputEmail1" placeholder="title" name="title" required>
			</div>
			<div class="form-group">
				<label>내 용</label>
				<textarea class="form-control" rows="3" placeholder="content" name="content"></textarea>
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-danger">글쓰기</button>
		</div>
	</form>
</div>
<!-- 글쓰기 폼태그 끝 -->

<%@ include file="../include/footer.jsp"%>