<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.container {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 50px;
}

.logo {
	margin-bottom: 20px;
}

label {
	margin-bottom: 10px;
}

input[type=text] {
	width: 100%;
	height: 40px;
	border: 1px solid red;
	margin-bottom: 10px;
}

input[type=password] {
	width: 100%;
	height: 40px;
	border: 1px solid red;
	margin-bottom: 10px;
}

#submitbtn {
	width: 270px;
	height: 50px;
	background-color: red;
	color: white;
	border: 0;
	margin-bottom: 15px;
}

#cencelbtn {
	border: 2px solid red;
	width: 270px;
	height: 50px;
	background-color: white;
	color: red;
}

.span-right {
	margin-right: 230px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container text-danger">
		<div class="container-wrapper">
			<div class="logo">
				<h3>회원 가입</h3>
				<b>*안에 내용은 반드시 작성해주세요!</b>
			</div>
			<form action="joinProcess" method="post">
				<label for="login_id">아이디*</label> <span id = "message"></span> <!-- *은 필수입력이라는 표시 -->
				<input class="form-control" type="text" name="id" id="id">
				
				
				<label for="password">비밀번호*</label> <span id = "message2"></span>
				<input class="form-control" type="password" name="password" id="password">
				
				
				<label for="password2">비밀번호 확인*</label>
				<input class="form-control" type="password" name="password2" id="password2">
				
				
				<label for="name">이름*</label> <span id = "message3"></span>
				<input class="form-control" type="text" name="name" id="name">
				
				
				<label for="phone">핸드폰 번호 [예) 010-1234-5678]</label> 
				<input class="form-control"	type="text" name="phone" id="phone">

				<div class="form-group">
					<label>성별</label><br> 
					<input type="radio" name="gender" value="남자" checked><span class="span-right">남자</span> 
					<input type="radio" name="gender" value="여자"><span>여자</span>
				</div>

				<div class="form-group">
					<label>역할</label><br>
					<input type="radio" name="position"	id="position" value="견주" checked><span class="span-right">견주</span>
					<input type="radio" name="position" id="position" value="산책러"><span>산책러</span>
				</div>

				<div class="form-group">
					<button type="button" id="cencelbtn">취소</button>
					<button type="submit" id="submitbtn">가입하기</button>
				</div>

			</form>

		</div>
	</div>

<script>
$(document).ready(function (){
	$("#cencelbtn").click(function() {
		history.back();
	});
	
	$("#id").on('keyup', function(){	//아이디 유효성검사
		checkid = true;
		$("#message").empty();
		var pattern = /^\w{5,12}$/;				//패턴설정
		var id = $("#id").val();
		if (!pattern.test(id)){					//패턴과 id값 비교
			$("#message").css('color', 'blue').html("영문자와 숫자,_로 5자~12자까지 가능합니다.")
			checkid = false;
			return;
		}
		
	$.ajax({
		url : "idcheck",
		data : {"id" : id},
		success : function(resp){
			if (resp == -1){
				$("#message").css('color', 'green').html("사용 가능한 아이디입니다.");
				checkid = true;
			} else {
				$("#message").css('color', 'orange').html("이미 사용중인 아이디입니다.");
				checkid = false;
			}
		}
	})
	})
	
	$("#password").blur(function(){
		if($("#password").val() != $("#password2").val()){
			if($("#password2").val() != ''){
				$("#message2").css('color', 'blue').html("비밀번호가 일치하지 않습니다.");
			}else if($("#password2").val() == ''){
				$("#message2").css('color', 'red').html("비밀번호를 확인해주세요.");
			}
		}
	})
	
	
	$("#name").on('keyup', function(){
		checkname = true;
		$("#message3").empty();
		var pattern =  /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$/;
		var name = $("#name").val();
		if (!pattern.test(name)){
			$("#message3").css('color', 'blue').html("이름은 한글만 입력 가능합니다.")
			checkname = false;
			return;
		}
	})
})
</script>
</body>
</html>