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
				<input class="form-control" type="text" name="id" id="id" required>
				
				
				<label for="password">비밀번호*</label>
				<input class="form-control" type="password" name="password" id="password" required>
				
				
				<label for="password2">비밀번호 확인*</label> <span id = "message2"></span>
				<input class="form-control" type="password" name="password2" id="password2" onKeyup="password_Check();" required>
				
				
				<label for="name">이름*</label> <span id = "message3"></span>
				<input class="form-control" type="text" name="name" id="name" required>
				
				
				<label for="phone">핸드폰 번호* [예) 010-1234-5678]</label> <span id = "message4"></span>
				<input class="form-control"	type="text" name="phone" id="phone" required>

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
				<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
			</form>

		</div>
	</div>

<script>
$(document).ready(function (){
var checkid = false;
var checkname = false;
var checkphone = false;

	//가입하기 눌렀을 때
	$('form').submit(function(){
		if(!checkid){
			alert("아이디를 확인해주세요");
			$("#id").val('').focus();
			return false;
		}
		
		if(!checkname){
			alert("이름을 한글로 입력해주세요");
			$("#name").val('').focus();
			return false;
		}
		
		if(!checkpassword){
			alert("비밀번호가 일치하지 않습니다");
			$("#password").val('').focus();
			$("#password2").val('');
			return false;
		}
		
		if(!checkphone){
			alert("핸드폰번호를 양식대로 입력해주세요");
			$("#phone").val('').focus();
			return false;
		}
	});
	
	//취소버튼 클릭 시 뒤로가기
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
		data : {"id" : $("#id").val()},
		dataType : "json",
		success : function(resp){
			if (resp == -1){
				$("#message").css('color', 'green').html("사용 가능한 아이디입니다.");
				checkid = true;
			} else {
				$("#message").css('color', 'blue').html("이미 존재하는 아이디입니다.");
				checkid = false;
			}
		}
	})
	})
	
	//이름 검사
	$("#name").on('keyup', function(){
		checkname = true;
		$("#message3").empty();
		var pattern = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$/;
		var name = $("#name").val();
		if (!pattern.test(name)){
			$("#message3").css('color', 'blue').html("이름은 한글만 입력 가능합니다.")
			checkname = false;
			return;
		}
	})
	
	//핸드폰번호 검사
	$("#phone").on('keyup', function(){
		checkphone = true;
		$("#message4").empty();
		var pattern = /^01([0|1])-([0-9]{3,4})-([0-9]{4})$/;
		var phone = $("#phone").val();
		if (!pattern.test(phone)){
			$("#message4").css('color', 'blue').html("핸드폰번호를 예시대로 입력해주세요.")
			checkphone = false;
			return;
		}
	})
});

var checkpassword = false;
//비밀번호 확인
function password_Check(){
	var pass1 = $("#password").val();
	var pass2 = $("#password2").val();
	
	if(pass1 == pass2){
		checkpassword = true;
		$("#message2").css('color', 'green').html("비밀번호가 일치합니다");
		return;
	}
	
	checkpassword = false;
	$("#message2").css('color', 'blue').html("비밀번호가 일치하지 않습니다");
}
</script>
</body>
</html>