<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>배송지 정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.container-info{
	display:inline-block;
	width:70%;
}

h4{
	color:red;
}

tr > td:nth-child(1){
	color:red;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/member/member_menu.jsp" />
	<div class="container-info">
	<h4>회원 정보</h4>
	<table class="table">
		<tr>
			<td>아이디</td>
			<td>${memberinfo.id }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${memberinfo.name }</td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td>${memberinfo.phone }</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${memberinfo.gender }</td>
		</tr>
		<tr>
			<td>역할</td>
			<td>${memberinfo.position }</td>
	</table>
	<button type="button" id="update">수정</button>
	</div>
</body>
<script>
$(function() {
	$("#update").click(function(){
		location.href="${pageContext.request.contextPath}/member/update";
	})
})
</script>
</html>