<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<script src = "${pageContext.request.contextPath}/resources/js/writeform.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/write.css">
	<style>
		h1{font-size: 1.5rem; text-align : center; color: #1a92b9}
		.container{width:60%}
		label{font-weight:bold}
		#upfile{display:none}
		img{width:20px;}
	</style>
</head>
<body>
	<div class = "container">
		<form action = "replyAction" method = "post" 
			  name = "boardform"> 
		<input type="hidden" name="BOARD_RE_REF" value="${boarddata.BOARD_RE_REF}">
		<input type="hidden" name="BOARD_RE_LEV" value="${boarddata.BOARD_RE_LEV}">
		<input type="hidden" name="BOARD_RE_SEQ" value="${boarddata.BOARD_RE_SEQ}">
		
		<h1>MVC 게시판 - Reply</h1>
		<div class = "form-group">
			<label for = "board_name">글쓴이</label>
			<input name = "BOARD_NAME" id="board_name" value="${boarddata.BOARD_NAME}" readOnly
			       type="text" maxlength="30"	class="form-control">
		</div>
		<div class = "form-group">
			<label for = "board_subject">제목</label>
			<textarea name = "BOARD_SUBJECT" id="board_subject" maxlength="100" rows="1"
			       class="form-control">Re:${boarddata.BOARD_SUBJECT}</textarea>
		</div>
		<div class = "form-group">
			<label for = "board_content">내용</label>
			<textarea name = "BOARD_CONTENT" id="board_content" 
			       cols="67" rows = "15" class="form-control"></textarea>
		</div>
		<div class = "form-group">
			<label for = "board_pass">비밀번호</label>
			<input name = "BOARD_PASS" id="board_pass" type="password" maxlength="30"	
				   class="form-control" placeholder="Enter board_pass">
		</div>
		<div class = "form-group">
			<button type = "submit" class = "btn btn-primary">등록</button>
			<button type = "reset" class = "btn btn-danger">취소</button>
		
		</div>
		</form>
	</div>
</body>
</html>