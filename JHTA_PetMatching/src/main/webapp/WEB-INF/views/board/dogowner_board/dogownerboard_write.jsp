<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/doboard_writeform.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/write.css">
	<style>
		h1{font-size: 1.5rem; text-align : center; color: #1a92b9}
		.container{width:60%}
		label{font-weight:light; color:#dc3545}
		#upfile{display:none}
		img{width:20px;}
	</style>
</head>
<body>
	<div class = "container">
		<form action = "add" method = "post" enctype="multipart/form-data"
			  name = "boardform"> 
		<p class="text-danger">
		  <font size = 4>산책 신청 게시판 (주인)&nbsp;&nbsp;&nbsp;</font>
		</p>
		<hr class="text-danger"> 
		
		
		<div class = "form-group">
			<label for = "board_name">글쓴이</label>
			<input name = "BOARD_NAME" id="board_name" value="${id}" readOnly
			       type="text" maxlength="30"	class="form-control"
			       placeholder="Enter board_name">
		</div>
		
		<div class = "form-group">
			<label for = "board_pass">비밀번호</label>
			<input name = "BOARD_PASS" id="board_pass" type="password" maxlength="30"	
				   class="form-control" placeholder="Enter board_pass">
		</div><hr class="text-danger">
		
		<div class = "form-group">
			<label for = "dog_upfile">반려견 사진</label>
			<label for = "dog_upfile">
				<img src = "${pageContext.request.contextPath}/resources/image/attach.png" alt="파일첨부">
			</label>
			<input type="file" id = "dog_upfile" name="dog_uploadfile">
			<span id = "dog_filevalue"></span>
		</div>
		
		<div class = "form-group">
			<label for = "dog_age">반려견 이름</label>
			<input name = "DOG_NAME" id="dog_name" type="text" maxlength="20"
			       class="form-control" placeholder="Enter your dog's name.">
		</div>
		
		<div class = "form-group">
			<label for = "dog_age">반려견 나이</label>
			<input name = "DOG_AGE" id="dog_age" type="text" maxlength="2"
			       class="form-control" placeholder="Enter your dog's age.">
		</div>
		
		<div class = "form-group">
			<label for = "dog_breed">반려견 견종</label>
			<input name = "DOG_BREED" id="dog_breed" type="text" maxlength=20"
			       class="form-control" placeholder="Enter your dog breed.">
		</div>
		
		<div class = "form-group">
			<label for = "dog_special_note">반려견 특이사항</label>
			<input name = "DOG_SPECIAL_NOTE" id="dog_special_note" type="text" maxlength="200"
			       class="form-control" placeholder="Enter a special note.">
		</div><hr class="text-danger">
		
		<div class = "form-group">
			<label for = "board_subject">제목</label>
			<input name = "BOARD_SUBJECT" id="board_subject" type="text" maxlength="100"
			       class="form-control" placeholder="Enter board_subject">
		</div>
		
		<div class = "form-group">
			<label for = "board_content">내용</label>
			<textarea name = "BOARD_CONTENT" id="board_content" 
			       rows = "10" class="form-control"></textarea>
		</div>
		
		<div class = "form-group">
			<button type = "submit" class = "btn btn-primary">등록</button>
			<button type = "reset" class = "btn btn-danger" onclick="history.go(-1)">취소</button>
		</div>
		</form>
	</div>
</body>
</html>