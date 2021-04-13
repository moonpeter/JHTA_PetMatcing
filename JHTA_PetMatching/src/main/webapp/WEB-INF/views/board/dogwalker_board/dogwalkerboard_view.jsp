<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>MVC 게시판 - view</title>
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
			$("form").submit(function(){
				if($("#board_pass").val()==''){
					alert("비밀번호를 입력하세요");
					$("#board_pass").focus();
					return false;
				}
			})
		})
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
</style>
</head>
<body>
	<input type="hidden" id="loginid" value="${id}" name="loginid">
	<div class = "container">
		<p class="text-danger">
		  <font size = 4>산책 신청 게시판&nbsp;&nbsp;&nbsp;</font>
		</p>
		<hr class="text-danger"> 
			
			<!-- 게시글 제목 -->
				<c:out value="${boarddata.BOARD_SUBJECT}" />
				<hr class="text-danger">
				
					<div><span id="writer">작성자</span>&nbsp;&nbsp;&nbsp;
						${boarddata.BOARD_NAME}
						<span id="date">${boarddata.BOARD_DATE}</span></div>
				
			<!-- 내용 -->
				<div style="padding-right:0px"><textarea class = "form-control"
				readOnly> ${boarddata.BOARD_CONTENT}</textarea></div>

			<c:if test="${boarddata.BOARD_RE_LEV == 0}"> <%-- 원문글인 경우에만 첨부파일을 추가할 수 있습니다. --%>

				<div>첨부파일&nbsp;
				<c:if test = "${!empty boarddata.BOARD_FILE}"> <%-- 파일 첨부한 경우 --%>
				<img src = "../resources/image/down.png" width = "10px">
					<a href = "down?filename=${boarddata.BOARD_FILE}&original=${boarddata.BOARD_ORIGINAL}">
					${boarddata.BOARD_ORIGINAL}</a>
				</c:if>
				<c:if test = "${empty boarddata.BOARD_FILE}"> <%-- 파일 첨부하지 않은 경우 --%> 			
				</c:if> </div>
			</c:if>	
			
				<div class = "center">
				 		<button id = "comment">댓글</button>
				 		<span id="count">${count}</span>
				<c:if test = "${boarddata.BOARD_NAME == id || id == 'admin' }">
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
		 		<form name = "deleteForm" action="delete" method = "post">
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