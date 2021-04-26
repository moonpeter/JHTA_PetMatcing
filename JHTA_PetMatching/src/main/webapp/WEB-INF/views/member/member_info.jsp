<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>내 정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
*{margin:0px;}

.container{
line-height:100%;
padding:10px;
}

.container-info{
	display:inline-block;
	width:80%;
}

h3{
	color:red;
}

table{
	border:0;
}

tr > td:nth-child(1){
	color:red;
}

#update{
	float:right;
	border:0;
	border-radius:12px;
	background-color:red;
	color:white;
	width:70px;
	height:50px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />	<!-- 헤더 -->
<div class="container"> <!-- 메인 컨테이너 -->
	<jsp:include page="/WEB-INF/views/member/member_menu.jsp" /> <!-- 왼쪽 사이드 메뉴 -->
	<div class="container-info"> <!-- 오른쪽 컨텐츠 정보 -->
	<h3>회원 정보</h3>
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
	<button type="button" id="update">수  정</button>
	</div>
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