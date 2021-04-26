<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
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
*{
	margin:0px;
}

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
	border-spacing:10px;
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

.container-menu{
	margin-left:-230px;
	margin-right:150px;
	width:20%;
	float:left;
}

li{
	list-style:none;
	margin-bottom:10px;
	}

b > a {
	color:red;
	decoration:none;
}

li > a {
	color:red;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="container">
	<div class="container-menu"> <!-- 카테고리 리스트 컨테이너  -->
		<div class="category">
		<ul>
			<li><b><a href="${pageContext.request.contextPath}/member/destination" id="destination">배송지 입력</a></b></li>
			<li><b><a href="${pageContext.request.contextPath}/member/desti_info" id="desti_info">배송지 보기/수정</a></b></li>
			<li><b><a href="${pageContext.request.contextPath}/member/info?id=${destiinfo.id}" id="member_info">내정보 보기/수정</a></b></li>
			<li><b><a href="${pageContext.request.contextPath}/member/delete" id="delete">회원탈퇴</a></b></li>
		</ul>
		</div>
	</div>
	<div class="container-info">
	<h3>배송지 정보</h3>
	<table class="table">
		<tr>
			<td>이름</td>
			<td>${destiinfo.name }</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td>${destiinfo.post }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${destiinfo.address }</td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td>${destiinfo.phone }</td>
		</tr>
		<tr>
			<td>요청사항</td>
			<td>${destiinfo.request }</td>
	</table>
	<button type="button" id="update">수정</button>
	</div>
</div>
</body>
<script>
$(function() {
	$("#update").click(function(){
		location.href="${pageContext.request.contextPath}/member/desti_update";
	})
	
	$("#delete").click(function(){
		if(confirm("정말 삭제하시겠습니까?")){
			return true;
		}else {
			return false;
		}
	});
	
	document.querySelector("#destination").removeAttribute('href');
	$("#destination").css('opacity', '0.3');
})
</script>
</html>