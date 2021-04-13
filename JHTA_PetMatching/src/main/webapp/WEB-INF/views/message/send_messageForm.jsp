<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
<meta charset="utf-8">
<script>
function messagePopUpClose(){
	window.close();
};
//sendSuccess는 MessageController.java에서 설정함.
var result="${result}";
if(result == 'sendSuccess'){
	alert("메시지 보내기 성공")
	//$(opener.document).find("#test").val('보낼값');  이런식으로 부모창에 값을 보낼 수 있다. header.jsp에도 보낼 수 있음. 참고로만 알아둘 것
	window.close();
}

$(function(){ //페이지 로드 완료되면 ajax 실행
	var receiver_id = $("#receiver_id").val();
	$.ajax({
		url: "${pageContext.request.contextPath}/message/idCheck", //요청 전송 url
		data: {"receiver_id" : receiver_id}, 
		success : function(data){
			if(data == -1){ //db에 해당 id가 없는 경우
				$("#idcheck1").css('color', 'red').html("탈퇴한 회원 입니다.");
				$("#idcheck_value").val('1');  // id=idcheck_value 인 태크에 value값 1을 주고, value값 1일 때 submit시 return false 시킨다.
			}else{ //db에 해당 id가 있는 경우. 'data == 1'
				$("#idcheck1").css('color', 'green').html("등록된 회원입니다.");
			}
		}	
	});//ajax end
	
	$('form').submit(function() {
		//ajax 메시지 받는이 체크
		if($("#idcheck_value").val()==1){  
			alert('탈퇴한 회원에게는 메시지를 보낼 수 없습니다.');
			return false;
		}else if($("#idcheck_value").val()==2){  
			alert('등록되지 않은 회원에게는 메시지를 보낼 수 없습니다.');
			$("#receiver_id").val('').focus;
			return false;
		}
		
		
		//공백(스페이스바)를 입력한 경우
		if($.trim($('#message_title').val())==''){
			alert("제목을 입력해주세요.");
			$('#message_title').val('').focus();
			return false;
		}else if($.trim($('#message_content').val())==''){
			alert("내용을 입력해주세요.");
			$('#message_content').val('').focus();
			return false;
		}
		
	});
	
	// 메시지함 하단에 있는 '메시지 보내기 버튼'을 클릭한 경우, 받는이를 직접 입력하도록 했는데
	// 받는이를 직접 입력할 때의 이벤트 
	$('#receiver_id').keyup(function() {
		var receiver_id = $("#receiver_id").val();
		$.ajax({
			url: "${pageContext.request.contextPath}/message/idCheck", //요청 전송 url
			data: {"receiver_id" : receiver_id}, 
			success : function(data){
				if(data == -1){ //db에 해당 id가 없는 경우
					$("#idcheck2").css('color', 'red').html("등록되지 않은 회원입니다.");
					$("#idcheck_value").val('2');  // id=idcheck_value 인 태크에 value값 2를 주고, value값 2일 때 submit시 return false 시킨다.
				}else{ //db에 해당 id가 있는 경우. 'data == 1'
					$("#idcheck2").css('color', 'green').html("등록된 회원입니다.");
					$("#idcheck_value").val('3');
				}
			}	
		});//ajax end
	});
	
	
	
	//content 글자수 나타내는 이벤트
	$("#message_content").keyup(function(){
		var length = $(this).val().length;
		if(length>800){
			length =800;
		}
		$("#content_count").css('color', 'blue').text("(작성 글자 수: "+length+"/800)")
	});
	
}); // ready end
</script>


<style>
header{background-color:#dc3545; color:white; height:60px; line-height:60px;}
form{margin:5%;}
#sender_id{width:40%}
#receiver_id{width:40%}
#message_title{width:98%;}
#message_content{width:98%;}
input{border-radius: 10px;}
input:focus{outline:none;}
textarea{border-radius: 10px; border-width:2px;}
textarea:focus{outline:none;}
#image1{margin-bottom:1%}
</style>
<title>메시지 보내기</title>
</head>
<body>
<header>
&nbsp;&nbsp;&nbsp;<b><img id="image1" src="${pageContext.request.contextPath}/resources/image/new_message.png" alt="메시지 " width="40px">메시지 보내기</b>
</header>
<form method="post" action="sendProcess">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  <b>보내는이</b><br>
  <input type="text" name="sender_id" id="sender_id" value="${sender_id}" readOnly><br><br>
  <b>받는이</b><br>
  <c:if test="${!empty receiver_id}">
    <input type="text" name="receiver_id" id="receiver_id" value="${receiver_id}" readOnly>
    <span id="idcheck1"></span>
  </c:if>
  <c:if test="${empty receiver_id}">
    <input type="text" name="receiver_id" id="receiver_id" placeholder="받는이 id를 입력하세요." required>
    <span id="idcheck2"></span>
  </c:if>
    <input type="hidden" id="idcheck_value"><br><br>
  
  
  <b>제목</b><br>
  <c:if test="${!empty re_title}">
    <input type="text" name="message_title" id="message_title" maxlength="30" value="${re_title}" required><br><br>
  </c:if>
  <c:if test="${empty re_title}">
    <input type="text" name="message_title" id="message_title" maxlength="30" required><br><br>
  </c:if>
  
  <b>메시지 내용</b>&nbsp; <span id="content_count"></span><br>
  <c:if test="${!empty re_content}">
    <textarea name="message_content" id="message_content" maxlength="800" rows="15" required>${re_content}</textarea><br>
  </c:if>
  <c:if test="${empty re_content}">
    <textarea name="message_content" id="message_content" maxlength="800" rows="15" required></textarea><br>
  </c:if>
  
  
  <button type="submit" class="btn btn-primary">전송</button>
  <button type="button" class="btn btn-danger"
              onClick="messagePopUpClose()">취소</button>
</form>



</body>
</html>