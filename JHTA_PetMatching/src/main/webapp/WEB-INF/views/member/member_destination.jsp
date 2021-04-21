<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>배송지 입력</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container text-danger">
		<div class="container-wrapper">
			<div class="logo">
				<h3>배송지 입력</h3>
				<b>*안에 내용은 반드시 작성해주세요!</b>
			</div>
			<form action="desti_Process" method="post">
				<label for="name">받는사람*</label> <span id = "message"></span> <!-- *은 필수입력이라는 표시 -->
				<input class="form-control" type="text" name="name" id="name" required>
				
				
				<label for="post">우편번호*</label> <input type="button" value="우편검색" id="post1">
				<input class="form-control" type="text" name="post" id="post" readonly required>
				
				<label for="address">주소* [동, 호수까지 정확하게 게시]</label>
				<input class="form-control" type="text" name="address" id="address" required>
				
				
				<label for="phone">핸드폰 번호* [예) 010-1234-5678]</label> <span id = "message2"></span>
				<input class="form-control"	type="text" name="phone" id="phone" required>

				<label for="request">요청사항 [예) 공동현관 비밀번호, 부재시 문 앞]</label> 
				<input class="form-control" type="text" name="request" id="request">
					

				<div class="form-group">
					<button type="button" id="cencelbtn">취소</button>
					<button type="submit" id="submitbtn">작성완료</button>
				</div>
				<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}">
			</form>

		</div>
	</div>
</body>
<script>
$(document).ready(function (){
var checkname = false;
var checkphone = false;
	
	//가입하기 눌렀을 때
	$('form').submit(function(){
		if(!checkname){
			alert("이름을 한글로 입력해주세요");
			$("#name").val('').focus();
			return false;
		}
	
		if(!checkphone){
			alert("핸드폰번호를 양식대로 입력해주세요");
			$("#phone").val('').focus();
			return false;
		}
	});
	
	//이름 검사
	$("#name").on('keyup', function(){
		checkname = true;
		$("#message").empty();
		var pattern = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$/;
		var name = $("#name").val();
		if (!pattern.test(name)){
			$("#message").css('color', 'blue').html("이름은 한글만 입력 가능합니다.")
			checkname = false;
			return;
		}
	})
	
	//핸드폰번호 검사
	$("#phone").on('keyup', function(){
		checkphone = true;
		$("#message2").empty();
		var pattern = /^01([0|1])-([0-9]{3,4})-([0-9]{4})$/;
		var phone = $("#phone").val();
		if (!pattern.test(phone)){
			$("#message2").css('color', 'blue').html("핸드폰번호를 예시대로 입력해주세요.")
			checkphone = false;
			return;
		}
	})
	
	//취소버튼 클릭 시 뒤로가기
	$("#cencelbtn").click(function() {
		history.back();
	});
})

//우편검색 클릭 시
$('#post1').click(function() {
    new daum.Postcode({
        oncomplete: function (
            data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== ''
                && /[동|로|가]$/g
                    .test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== ''
                && data.apartment === 'Y') {
                extraRoadAddr += (extraRoadAddr !== '' ? ', '
                    + data.buildingName
                    : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== '') {
                extraRoadAddr = ' ('
                    + extraRoadAddr
                    + ')';
            }

            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if (fullRoadAddr !== '') {
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $("#post")
                .val(
                    data.zonecode);
            $("#address")
                .val(
                    fullRoadAddr);

        }
    }).open();
})
</script>
</html>