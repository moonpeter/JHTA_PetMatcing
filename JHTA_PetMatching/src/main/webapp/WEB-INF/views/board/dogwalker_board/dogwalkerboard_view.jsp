<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>산책 신청 게시판(산책러)</title>
	<jsp:include page = "/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<script src = "${pageContext.request.contextPath}/resources/js/views3.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/view.css">
	<script>
		
		var result="${result}";
		if(result == 'passFail'){
			alert("비밀번호가 일치하지 않습니다.")
		}
		$(function(){
			$("form_delete").submit(function(){
				if($("#board_pass").val()==''){
					alert("비밀번호를 입력하세요");
					$("#board_pass").focus();
					return false;
				}
			})
		})
		
	//메시지 보내기 버튼 클릭
	function messagePopUp(){
	var receiver = "${boarddata.BOARD_NAME}"; 

	window.open("${pageContext.request.contextPath}/message/send?receiver_id="+receiver,
			    "post", "width=600, height=700, scrollbars=yes");
	};
	</script>
	<script type="text/javascript">
	$(document).ready(function (e){
		
		$(document).on("click","img",function(){
			var path = $(this).attr('src')
			showImage(path);
		});//end click event
		
		function showImage(fileCallPath){
		    
		    $(".bigPictureWrapper").css("display","flex").show();
		    
		    $(".bigPicture")
		    .html("<img src='"+fileCallPath+"' >")
		    .animate({width:'100%', height: '100%'}, 1000);
		    
		  }//end fileCallPath
		  
		$(".bigPictureWrapper").on("click", function(e){
		    $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
		    setTimeout(function(){
		      $('.bigPictureWrapper').hide();
		    }, 1000);
		  });//end bigWrapperClick event
	});
	</script>
<style>
	body > div > table > tbody >tr:nth-child(1) {
	text-align: center
}

td:nth-child(1) {
	width: 20%
}

a {
	color: black
}

body > div > table > tbody >tr:nth-child(5)>td:nth-child(2)>a {
	color: black
}

body > div > table > tbody tr:last-child {
	text-align: center;
}

.btn-primary {
	background-color: #4f97e5
}


#myModal {
	display: none
}

#comment > table > tbody > tr > td:nth-child(2){
 width:60%
}

#count {
    position: relative;
    color: #dc3545;
}

textarea{resize:none}

#date{float:right}

body > div > div:nth-child(4),body > div > div:nth-child(5) > textarea{padding-bottom: 2em;}
body > div > div:nth-child(6){padding-bottom:0.53em;}

body > div > div:nth-child(6){color:#dc3545}
#send{background-color:white; color:#dc3545;  height:40px;
      border-radius:10px; border: 2px solid #dc3545;}
      
 .bigPictureWrapper {
			position: absolute;
			display: none;
			justify-content: center;
			align-items: center;
			top:0%;
			width:100%;
			height:100%;
			/* background-color: gray;  */
			z-index: 100;
			/* background:rgba(255,255,255,0.5); */
		}
		.bigPicture {
			position: relative;
			display:flex;
			/* justify-content: center; */
			align-items: center;
		}
		.bigPicture img {
			width:600px;
		}
		body > div.container > div:nth-child(6) > img{width:300px}
		.form-control{border:none; height:500px}
		body > div.container > div:nth-child(6) > textarea{padding:0; margin-top: 2em; margin-bottom:5em;}
		
</style>
</head>
<body>
	<input type="hidden" id="id" value="${loginid}" name="loginid">
	<input type="hidden" id="table_name" value="${table_name}">
	
	<div class = "container">
		<p class="text-danger">
		  <font size = 4>산책 신청 게시판 (산책러)&nbsp;&nbsp;&nbsp;</font>
		</p>
		<hr class="text-danger"> 
			
			<!-- 게시글 제목 -->
				<c:out value="${boarddata.BOARD_SUBJECT}" />
				<hr class="text-danger">
				
					<div><span id="writer">작성자</span>&nbsp;&nbsp;&nbsp;
						${boarddata.BOARD_NAME}
						<span id="date">${boarddata.BOARD_DATE}</span></div>
				
			<!-- 내용 -->
			<c:if test="${boarddata.BOARD_RE_LEV == 0}">
			<div class="bigPictureWrapper">
				<div class="bigPicture">
				</div>
			</div>
				<div style="padding-right:0px">
				<c:if test = "${!empty boarddata.BOARD_FILE}">
					<img class="demo cursor"
						 src="${pageContext.request.contextPath}/resources/dwboard_upload${boarddata.BOARD_FILE}">
				</c:if>
				<c:if test = "${empty boarddata.BOARD_FILE}">
				</c:if> 
				<textarea class = "form-control"readOnly> ${boarddata.BOARD_CONTENT}</textarea>
			</div>
			</c:if>
			
			<c:if test="${boarddata.BOARD_RE_LEV != 0}">
				<div style="padding-right:0px">
				<textarea class = "form-control"readOnly> ${boarddata.BOARD_CONTENT}</textarea>
			</div>
			</c:if>
			
			<button id="send" class="btn btn-info" onClick="messagePopUp()">
  				<img id="image1" src="${pageContext.request.contextPath}/resources/image/reply_message.png" alt="메시지  보내기" width="30px">              
    			메시지 보내기
			</button>

			
				<div class = "center">
				 		<button id = "comment">댓글</button>
				 		<span id="count">${count}</span>
				<c:if test = "${boarddata.BOARD_NAME == loginid || loginid == 'admin' }">
				 <a href = "modifyView?num=${boarddata.BOARD_NUM}">
				 	<button id="viewbtn">수정</button>
				 </a>
				<a href = "#"> <!-- # : 최상단 페이지 이동 -->
				<button id="viewbtn" data-toggle = "modal"
						data-target="#myModal">삭제</button>
						</a>
				</c:if>
				
				<a href = "replyView?num=${boarddata.BOARD_NUM}">
					<button id="viewbtn">답변</button>
				</a>
				
				<a href = "list">
					<button id="viewbtn">목록</button>
				</a>
	<%-- 게시판 view end --%>
	
	
		<%-- modal 시작 --%>
		<div class = "modal" id = "myModal">
		 <div class = "modal-dialog">
		 	<div class = "modal-content">
		 		<%-- Modal body --%>
		 	<div class = "modal-body">
		 		<form id="form_delete" name = "deleteForm" action="delete" method = "post">
		 			<%-- http://localhost:8088/Board/BoardDetailAction.bo?num=22
		 				 주소를 보면 num을 파라미터로 넘기고 있습니다.
		 				 이 값을 가져와서 %{param.num}를 사용
		 				 또는 ${boarddata.BOARD_NUM}
		 			 --%>
		 		<input type="hidden" name="num" value="${param.num}" id="board_num">
		 		<div class = "form-group">
		 			<label for = "pwd">비밀번호</label>
		 			<input type = "password"
		 			       class = "form-control" placeholder = "Enter password"
		 			       name = "BOARD_PASS" id = "board_pass">
		 		</div>
		 		<button type = "submit" class = "btn btn-primary">전송</button>
		 		<button type = "button" class = "btn btn-danger" data-dismiss = "modal">취소</button>
		 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">	
		 		
		 		</form>
		 	</div>
		 	</div>
		 </div>
	</div><%-- <div class="modal" id="myModal"> --%>
	
	<div id="comment">
		<button class="btn btn-info float-left">총 50자 까지 가능합니다.</button>
		<button id="write" class="btn btn-info float-right">등록</button>
		<textarea rows=3 class="form-control" id="content" maxLength="50"></textarea>
		<table class="table table_striped">
			<thead>
				<tr><td id="commenthead">아이디</td><td id="commenthead">내용</td><td id="commenthead">날짜</td></tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
			<div id="message"></div>
		</div>
	   </div>
	</div>
</body>
</html>