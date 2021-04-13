<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>MVC 게시판</title>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<script src = "${pageContext.request.contextPath}/resources/js/modifyform.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/write.css">
	<style>
		h1{font-size:1.5em; text-align:center; color:#1a92b9}
		label{font-weight:light; color:#dc3545}
		.container{width:60%}
		img{width:20px;}
		#upfile{display:none;}
		body > div > form > div.form-group.read > img{width: 10px;}
	</style>
</head>
<script>
	if('${result}'=='passFail'){
		alert("비밀번호가 다릅니다.")
	}
</script>
<body>
	<div class = "container">
		<form action = "modifyAction" method = "post" name = "modifyform"
				enctype="multipart/form-data">
			<input type = "hidden" name = "BOARD_NUM" value = "${boarddata.BOARD_NUM}">
			<input type = "hidden" name = "BOARD_FILE" value = "${boarddata.BOARD_FILE}">
			<p class="text-danger">
			  <font size = 4>산책 신청 게시판 (주인)&nbsp;&nbsp;&nbsp;</font>
			</p>
			<hr class="text-danger">
			
			
				<div class = "form-group">
					<label for = "board_name">글쓴이</label>
					<input name = "BOARD_NAME" id = "board_name" type = "text" value = "${boarddata.BOARD_NAME}"
							class = "form-control" readOnly>
				</div>
				<div class = "form-group">
					<label for = "dog_name">반려견 이름</label>
					<textarea name = "DOG_NAME" id = "dog_name" rows="1" maxlength="20"
							class = "form-control" maxlength="100">${boarddata.DOG_NAME }</textarea>
				</div>
				<div class = "form-group">
					<label for = "dog_age">반려견 나이</label>
					<textarea name = "DOG_AGE" id = "dog_age" rows="1" maxlength="2"
							class = "form-control" maxlength="100">${boarddata.DOG_AGE }</textarea>
				</div>
				<div class = "form-group">
					<label for = "dog_breed">반려견 견종</label>
					<textarea name = "DOG_BREED" id = "dog_breed" rows="1" maxlength="20"
							class = "form-control" maxlength="100">${boarddata.DOG_BREED }</textarea>
				</div>
				<div class = "form-group">
					<label for = "dog_special_note">반려견 특이사항</label>
					<textarea name = "DOG_SPECIAL_NOTE" id = "dog_special_note" rows="1" maxlength="200"
							class = "form-control" maxlength="100">${boarddata.DOG_SPECIAL_NOTE }</textarea>
				</div>
				<div class = "form-group">
					<label for = "board_subject">제목</label>
					<textarea name = "BOARD_SUBJECT" id = "board_subject" rows="1" maxlength="100"
							class = "form-control" maxlength="100">${boarddata.BOARD_SUBJECT}</textarea>
				</div>
				<div class = "form-group">
					<label for = "board_content">내용</label>
					<textarea name = "BOARD_CONTENT" id = "board_content" cols="67" rows="15"
							class = "form-control">${boarddata.BOARD_CONTENT}</textarea>
				</div>
				
				<%-- 원문 글인 경우에만 파일 첨부 수정 가능합니다. --%>
				<c:if test = "${boarddata.BOARD_RE_LEV==0 }">
					<div class = "form-group read">
						<label for = "board_file">파일 첨부</label>
						<label for = "upfile">
							<img src = "${pageContext.request.contextPath}/resources/image/attach.png" alt="파일첨부">
						</label>
						<input type = "file" id = "upfile" name = "uploadfile">
						<span id = "filevalue">${boarddata.BOARD_ORIGINAL}</span>
						<img src = "${pageContext.request.contextPath}/resources/image/remove.png" alt="파일삭제" width="10px" class = "remove">
					</div>	
				</c:if>
				
				<div class = "form-group">
					<label for = "board_pass">비밀번호</label>
					<input name = "BOARD_PASS" id = "board_pass" type = "password"
							size = "10" maxlength="30" placeholder="Enter board_pass" 
							class = "form-control" value="">
				</div>
				
				<div class = "form-group">	
					<button type = "submit" class = "btn btn-primary">수정</button>
					<button type = "reset" class = "btn btn-danger" 
					 			onClick = "history.go(-1)">취소</button>
				
				</div>
		</form>
	</div> <%-- class = "container" end --%>
	
</body>
</html>