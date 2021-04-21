<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>산책 신청(Owner)</title>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/views3.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/view.css">
<script>
	
	var slideIndex = 1;
	showSlides(slideIndex);
	
	function plusSlides(n) {
	  showSlides(slideIndex += n);
	}
	
	function currentSlide(n) {
	  showSlides(slideIndex = n);
	}
	
	function showSlides(n) {
	  var i;
	  var slides = document.getElementsByClassName("mySlides");
	  var dots = document.getElementsByClassName("demo");
	  if (n > slides.length) {slideIndex = 1}
	  if (n < 1) {slideIndex = slides.length}
	  for (i = 0; i < slides.length; i++) {
	      slides[i].style.display = "none";
	  }
	  for (i = 0; i < dots.length; i++) {
	      dots[i].className = dots[i].className.replace(" active", "");
	  }
	  slides[slideIndex-1].style.display = "block";
	  dots[slideIndex-1].className += " active";
	}
	
	// //////////////////////////////////////////////////////
	var result = "${result}";
	if (result == 'passFail') {
		alert("비밀번호가 일치하지 않습니다.")
	}
	$(function() {
		$("form").submit(function() {
			if ($("#board_pass").val() == '') {
				alert("비밀번호를 입력하세요");
				$("#board_pass").focus();
				return false;
			}
		})
	})
	
</script>
<style>
body>div>table>tbody>tr:nth-child(1) {text-align: center}

td:nth-child(1) {width: 20%}

a {color: black}

body>div>table>tbody>tr:nth-child(5)>td:nth-child(2)>a {color: black}

body>div>table>tbody tr:last-child {text-align: center;}

.btn-primary {background-color: #4f97e5}

#myModal {display: none}

#comment>table>tbody>tr>td:nth-child(2) {width: 60%}

#count {position: relative;color: #dc3545;}

textarea {resize: none}

#date {float: right}

body>div>div:nth-child(4), body>div>div:nth-child(5)>textarea {padding-bottom: 2em;}

body>div>div:nth-child(6) {padding-bottom: 0.53em;}

body>div>div:nth-child(6) {color: #dc3545}

body > div.container > div.container > div > div.col-4 > div > img{margin-bottom: 5em;}

body > div.container > div.center{margin-top: 2em;}

/* ////////////////////////////////////////////////////////////////////// */


body {
  font-family: Arial;
  margin: 0;
}

* {
  box-sizing: border-box;
}

img {
  vertical-align: middle;
}

/* Position the image container (needed to position the left and right arrows) */
.container {
  position: relative;
}

/* Hide the images by default */
.mySlides {
  display: none;
}

/* Add a pointer when hovering over the thumbnail images */
.cursor {
  cursor: pointer;
}

/* Next & previous buttons */
.prev,
.next {
  cursor: pointer;
  position: absolute;
  top: 40%;
  width: auto;
  padding: 16px;
  margin-top: -50px;
  color: white;
  font-weight: bold;
  font-size: 20px;
  border-radius: 0 3px 3px 0;
  user-select: none;
  -webkit-user-select: none;
}

/* Position the "next button" to the right */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover,
.next:hover {
  background-color: rgba(0, 0, 0, 0.8);
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Six columns side by side */
.column {
  padding: 0
}


/* Add a transparency effect for thumnbail images */
.demo {
  opacity: 0.6;
}

.active,
.demo:hover {
  opacity: 1;
}

.mySlides{width:500px; margin:0}

.row {
    --bs-gutter-x: 0; 
    --bs-gutter-y: 0;
    }
</style>
</head>
<body>
	<input type="hidden" id="loginid" value="${id}" name="loginid">
	
	<!-- 게시판명  --> 
	<div class="container">
		<p class="text-danger">
			<font size=4>산책 신청 게시판 (주인)&nbsp;&nbsp;&nbsp;</font>
		</p>
		<hr class="text-danger">

		<!-- 게시글 제목 -->
		<c:out value="${boarddata.BOARD_SUBJECT}" />
		<hr class="text-danger">

		<div>
			<span id="writer">작성자</span>&nbsp;&nbsp;&nbsp;
			${boarddata.BOARD_NAME} <span id="date">${boarddata.BOARD_DATE}</span>
		</div>

    	<!-- 반려견 정보란  -->
		<div class="container">
			<div class="container" id="dog_photo">
				<div class="mySlides">
					<img src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO}" width="500px" >
				  </div>
<%-- 				<div class="mySlides">
					<img src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO1}" width="500px">
				  </div>
				<div class="mySlides">
					<img src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO2}" width="500px">
				  </div>
				<div class="mySlides">
					<img src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO3}" width="500px">
				  </div>
				 <div class="mySlides">
					<img src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO4}" width="500px">
				  </div> --%>
				    <a class="prev" onclick="plusSlides(-1)">❮</a>
  					<a class="next" onclick="plusSlides(1)">❯</a>
			
			  <div class="row" >
			    <div class="column">
			      <img class="demo cursor" src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO}" style="width:100px;"  onclick="currentSlide(1)">
			    </div>
<%-- 			    <div class="column">
			      <img class="demo cursor" src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO}" style="width:100px; float:left;" onclick="currentSlide(2)" >
			    </div>
			    <div class="column">
			      <img class="demo cursor" src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO}" style="width:100px; float:left;" onclick="currentSlide(3)">
			    </div>
			    <div class="column">
			      <img class="demo cursor" src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO}" style="width:100px; float:left;" onclick="currentSlide(4)">
			    </div>
			    <div class="column">
			      <img class="demo cursor" src="${pageContext.request.contextPath}/resources/upload/doBoard${boarddata.DOG_PHOTO}" style="width:100px; float:left;" onclick="currentSlide(5)" >
			    </div>
			  </div> --%>
			  </div>
  
			<div class="container">
				<div class="col-8">
					<div>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름&nbsp;&nbsp;&nbsp;&nbsp;${boarddata.DOG_NAME }</div>
					<div>나&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;${boarddata.DOG_AGE }</div>
					<div>견&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;종&nbsp;&nbsp;&nbsp;&nbsp;${boarddata.DOG_BREED }</div>
					<div>특이사항&nbsp;&nbsp;&nbsp;${boarddata.DOG_SPECIAL_NOTE }</div>
					</div>
			</div>
		</div> <!-- <div class="container"> end -->
</div>
		<!-- 내용 -->
		<div class="container">
		<p class="text-danger"> 내용
			<hr class="text-danger">
			 ${boarddata.BOARD_CONTENT}

		<div class="center">
			<button id="comment">댓글</button>
			<span id="count">${count}</span>
			<c:if test="${boarddata.BOARD_NAME == id || id == 'admin' }">
				<a href="modifyView?num=${boarddata.BOARD_NUM}">
					<button id="viewbtn">수정</button>
				</a>
				<a href="#"> <!-- # : 최상단 페이지 이동 -->
					<button id="viewbtn" data-toggle="modal" data-target="#myModal">삭제</button>
				</a>
			</c:if>

			<a href="list">
				<button id="viewbtn">목록</button>
			</a>
			<%-- 게시판 view end --%>


			<%-- modal 시작 --%>
			<div class="modal" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">
						<%-- Modal body --%>
						<div class="modal-body">
							<form name="deleteForm" action="delete" method="post">
								<%-- http://localhost:8088/Board/BoardDetailAction.bo?num=22
		 				 주소를 보면 num을 파라미터로 넘기고 있습니다.
		 				 이 값을 가져와서 %{param.num}를 사용
		 				 또는 ${boarddata.BOARD_NUM}
		 			 --%>
								<input type="hidden" name="num" value="${param.num}"
									id="board_num">
								<div class="form-group">
									<label for="pwd">비밀번호</label> <input type="password"
										class="form-control" placeholder="Enter password"
										name="BOARD_PASS" id="board_pass">
								</div>
								<button type="submit" class="btn btn-primary">전송</button>
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">취소</button>

							</form>
						</div>
					</div>
				</div>
			</div>
			<%-- <div class="modal" id="myModal"> --%>

			<div id="comment">
				<button class="btn btn-info float-left">총 50자 까지 가능합니다.</button>
				<button id="write" class="btn btn-info float-right">등록</button>
				<textarea rows=3 class="form-control" id="content" maxLength="50"></textarea>
				<table class="table table_striped">
					<thead>
						<tr>
							<td id="commenthead">아이디</td>
							<td id="commenthead">내용</td>
							<td id="commenthead">날짜</td>
						</tr>
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