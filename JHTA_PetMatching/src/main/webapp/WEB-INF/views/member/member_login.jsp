<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<link rel="shortcut icon" href="#">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
* {
	margin:0;
	padding:0;
	
}

h3{margin:20px 0 20px 0}

.container {
	width:100%;
	display:flex;
	flex-direction:column;
	align-items:center;
	margin-top: 50px;
}

.container .container-wrapper{
	width:500px;
}

input[type=text]{
	width:90%;
	height:40px;
	border:1px solid red;
}

input[type=password]{
	width:90%;
	height:40px;
	border:1px solid red;
}

.form-check{
	margin-top:10px;
}

#remeber{
	outline:1px solid red;
	margin-
}

#login_btn{
	width:270px;
	height:50px;
	background-color:red;
	color:white;
	border:0;
	margin-bottom:15px;
}

#join_btn{
	border: 2px solid red;
	width:270px;
	height:50px;
	background-color:white;
	color:red;
}

.form-group-wrapper{
	margin:30px 0 0 16%;
}

.footer{
  width: 100%;
  background-color: white;
  color: red;
  text-align: center;
  margin-top:100px;
}

.footer-wrapper{
	margin:0 auto;
	 border-top:1px solid red;
	 width:95%;
	 height:100px;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" ></jsp:include>
<div class="container text-danger"> <!-- 로그인 창 전체를 감싸주는 컨테이너 -->
		<div class = "container-wrapper">
			<h3>로그인</h3>
			<form action="loginProcess" method="post">
				<div class="form-group"> <!-- 아이디  -->
					<label for=login_id>아이디</label><br>
					<input class="form-control"	type="text" name="id" id="id" required>
				</div>
				
				<div class="from-group"> <!-- 비밀번호 -->
					<label for=password>비밀번호</label><br>
					<input class="form-control"	type="password" name="password" id="password">
				</div>
				
				<div class="form-group form-check"> <!-- 아이디 기억하기 -->
					<input type="checkbox" class="form-check-input" id="remember" name="remember">
					<label class="from-check-label" for="remember">로그인 유지</label>
				</div>
				
				<!-- 로그인, 회원가입 버튼 -->
				<div class=form-group>
					<div class="form-group-wrapper">
						<button type="submit" id="login_btn">로그인하기</button>
						<button type="button" id="join_btn">회원가입</button>
					</div>
				</div>
				<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
			</form>
		</div>
</div>

<div class = "container">
	<div class = "footer text-danger">
			<div class = "footer-wrapper">
				<div>FOOTER</div>
			</div>
	</div>
</div>
</body>
<script>
$(function(){
	$("#join_btn").click(function(){
		location.href = "join"
	});
	
	var result = "${result}";
	if(result == 'joinSuccess'){
		alert("회원가입을 축하합니다~");
	}
	
	//로그인 실패
	result = "${loginFailMsg}";
		if(result){
			alert(result);
		}
})
</script>
</html>