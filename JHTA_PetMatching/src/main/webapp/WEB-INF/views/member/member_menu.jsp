<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<style>
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

a {
	color:red;
	decoration:none;
}
</style>
<body>
	<div class="container-menu"> <!-- 카테고리 리스트 컨테이너  -->
		<div class="category">
		<ul>
			<li><b><a href="${pageContext.request.contextPath}/member/destination" id="destination">배송지 입력</a></b></li>
			<li><b><a href="${pageContext.request.contextPath}/member/desti_info" id="desti_info">배송지 보기/수정</a></b></li>
			<li><b><a href="${pageContext.request.contextPath}/member/info?id=${memberinfo.id}" id="member_info">내정보 보기/수정</a></b></li>
			<li><b><a href="${pageContext.request.contextPath}/member/delete" id="delete">회원탈퇴</a></b></li>
		</ul>
		</div>
	</div>
</body>
<script>
$(function() {
	$("#delete").click(function(){
		if(confirm("정말 삭제하시겠습니까?")){
			return true;
		}else {
			return false;
		}
	});
	
	var destiinfo = "${destiinfo}";
	console.log("destiinfo 값 : " + destiinfo);
	
	if(destiinfo != ""){
		document.querySelector("#destination").removeAttribute('href');
		$("#destination").css('opacity', '0.3');
	};
	
	if(destiinfo == ""){
		document.querySelector("#desti_info").removeAttribute('href');
		$("#desti_info").css('opacity', '0.3');
	};
})
</script>
</html>