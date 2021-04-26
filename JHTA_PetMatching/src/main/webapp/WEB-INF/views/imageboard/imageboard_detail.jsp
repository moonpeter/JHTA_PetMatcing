<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- 
         주의사항
     header.jsp 파일을 include 하니까 부트스트랩 때문인지 modal css가 이상해짐. 가로로 이미지가 나열되던게
         세로로 나열되기 시작함.
         이를 해결하기 위해 <style>에서 .modal-content2에  display:block;을 추가해주니 해결됨
 --%>
<!DOCTYPE html>
<html>
<meta id="_csrf" name="_csrf" content="${_csrf.token}">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/imageboard_detail.js"></script>

<jsp:include page="../common/header.jsp"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>산책로 추천 게시판-상세보기</title>
<style>
body {
  font-family: Verdana, sans-serif;
  margin: 0;
}

* {
  box-sizing: border-box;
}

.row > .column {
  padding: 0 8px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

.column {
  float: left;
  width: 25%;
}

/* The Modal (background) */
.modal2 {
  display: none;
  position: fixed;
  z-index: 1;
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: black;
}

/* Modal Content */
.modal-content2 {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  width: 90%;
  max-width: 1200px;
  display:block;
}

/* The Close Button */
.close {
  color: white;
  position: absolute;
  top: 10px;
  right: 25px;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #999;
  text-decoration: none;
  cursor: pointer;
}

.mySlides {
  display: none;
}

.cursor {
  cursor: pointer;
}

/* Next & previous buttons */
.prev,
.next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  padding: 16px;
  margin-top: -50px;
  color: white;
  font-weight: bold;
  font-size: 20px;
  transition: 0.6s ease;
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

.img {
  margin-bottom: -4px;
  border: 3px solid black;
  border-radius: 10px;
}

.caption-container {
  text-align: center;
  background-color: black;
  padding: 2px 16px;
  color: white;
}

.demo {
  opacity: 0.6;
}

.active,
.demo:hover {
  opacity: 1;
}

img.hover-shadow {
  transition: 0.3s;
}

.hover-shadow:hover {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}




#row{width:70%; margin:auto;}
.main_image{height:180px;}

a:not([href]):not([tabindex]):focus, 
a:not([href]):not([tabindex]):hover,
a:not([href]):not([tabindex]){color:white;}

#send{background-color:white; color:#dc3545;  height:40px;
      border-radius:5px; border: 2px solid #dc3545;}
#myModal{display: none}
td:nth-child(1) {width: 20%}
td:nth-child(2) {width: 58%}
#div1, #div2, #div3, #div4{float:left; padding:10px;}
#div1234{margin: 0 auto; width:50%}
.span{background-color:#e9ecef; display:inline-block; width:100%; height:40px; line-height:40px; border-radius:8px;}
.anchor{color:black;}
.anchor:hover{text-decoration:none; color:black;}
.anchor2{color:#dc3545;}
.anchor2:hover{text-decoration:none; color:#dc3545;}
#title{font-size:32px;}
fieldset {border: 1px solid black; border-radius: 10px; padding: 20px;}
.column{margin: 0 auto; width:25%;}
.recommend{border: 2px solid #dc3545; width:100px; margin:0 auto; border-radius: 10px;}
.comment_button{color:white; background-color:#dc3545; border:none;}
</style>
<body>

<div class="container">
<a id="title" class="anchor" href="${pageContext.request.contextPath}/image_board/list">
<img src="${pageContext.request.contextPath}/resources/image/dog_walking.png" alt="산책 이미지" width="60px">
산책로 추천 게시판</a><br><br><br><br><br>

<fieldset>
<b>글쓴이</b><br>
<span class="span">&nbsp;&nbsp;${boarddata.BOARD_NAME}</span><br><br>

<b>제목</b><br>
<span class="span">
&nbsp;&nbsp;<c:out value="${boarddata.BOARD_SUBJECT}" />
</span>
<br><br><br><br>


<div id="row" class="row">
  <c:if test="${!empty boarddata.BOARD_FILE1}">
  <div class="column">
    <img class="main_image img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE1}" style="width:100%" onclick="openModal();currentSlide(1)" class="hover-shadow cursor">
  </div>
  </c:if>
  
  <c:if test="${!empty boarddata.BOARD_FILE2}">
  <div class="column">
    <img class="main_image img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE2}" style="width:100%" onclick="openModal();currentSlide(2)" class="hover-shadow cursor">
  </div>
  </c:if>
  
  <c:if test="${!empty boarddata.BOARD_FILE3}">
  <div class="column">
    <img class="main_image img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE3}" style="width:100%" onclick="openModal();currentSlide(3)" class="hover-shadow cursor">
  </div>
  </c:if>
  
  <c:if test="${!empty boarddata.BOARD_FILE4}">
  <div class="column">
    <img class="main_image img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE4}" style="width:100%" onclick="openModal();currentSlide(4)" class="hover-shadow cursor">
  </div>
  </c:if>
</div><br><br><br>

<b>내용</b><br>
<textarea class="form-control" rows="5" readOnly>${boarddata.BOARD_CONTENT}</textarea><br><br>

  <div id="div1234">
    <div id="div1">
    <button id="send" class="btn btn-info" onClick="messagePopUp()">
      <img id="image1" src="${pageContext.request.contextPath}/resources/image/reply_message.png" alt="메시지  보내기" width="30px">              
            메시지 보내기
    </button>
	</div>

	<div id="div2">
    <button class="btn btn-success" id="BackToList">목록으로</button>
    </div>
    
    <sec:authorize access="isAuthenticated()">
      <sec:authentication property="principal" var="pinfo" /> 
        <c:if test="${boarddata.BOARD_NAME == pinfo.username || pinfo.username == 'admin'}">
          <form id="form_modify" action="modifyView" method="post" style="display:inline-block">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" name="modify_num" value="${boarddata.BOARD_NUM}">
            <div id="div3">
            <button id="modify_button" class="btn btn-warning">수정</button>
            </div>
          </form>
          <div id="div4">
          <button class="btn btn-danger" data-toggle="modal" data-target="#myModal">삭제</button>
          </div>
        </c:if> 
    </sec:authorize>
  </div>
</fieldset><br><br>


<!----------------------- 추천 버튼 ------------------------------------>
<div class="recommend">
<table style="width:95px; height:90px; margin:0 auto;">
  <tr>
    <td style="color:red; text-align:center;">
      <a class="anchor2" href="recommend?num=${boarddata.BOARD_NUM }">
        <img id="recommend_img" src="${pageContext.request.contextPath}/resources/image/recommend.png" alt="추천" width="30px">
        <span>추&nbsp;천</span>
      </a>
    </td>
  </tr>
  <tr style="border-top:2px solid #dc3545;">
    <td style="text-align:center"><a class="anchor2" href="recommend?num=${boarddata.BOARD_NUM }"><span style="font-size:25px">${boarddata.RECOMMEND_COUNT}</span></a></td>
  </tr>
</table>
</div>
<!----------------------- 추천 버튼 끝 ---------------------------- -->



<br><br><br>
<div><b>(&nbsp;댓글 수:&nbsp;</b><b id="count" style="color:red">${count}</b><b>&nbsp;)</b></div>
<div id="comment">
    <button class="btn btn-info float-left comment_button">총 50자까지 가능합니다.</button>
    <button id="write" class="btn btn-info float-right comment_button">등록</button>
    <textarea rows=3 class="form-control" id="content" maxLength="50"></textarea>
    <table class="table table_striped">
      <thead>
        <tr><td>아이디</td><td>내용</td><td>날짜</td></tr>
      </thead>
      <tbody>
      
      </tbody>
    </table>
    <div id="message"></div><br><br><br>
</div>
</div>




<%-- 삭제 버튼의 modal 시작 --%>
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <%-- Modal body --%>
        <div class="modal-body">
          <form id="deleteForm" name="deleteForm" action="delete" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            
            <%-- http://localhost:8088/myhome6/detail?num=22
                               주소를 보면 num을 파라미터로 넘기고 있습니다.
                               이 값을 가져와서 ${param.num}을 사용
                               또는 ${boarddata.BOARD_NUM}
             --%>
            <input type="hidden" name="num" value="${param.num}" id="board_num"> <%-- id="board_num"을 ajax에서 이용 --%>
            <div class="form-group">
              <label for="pwd">비밀번호</label>
              <input type="password" class="form-control"
                     placeholder="Enter password"
                     name="BOARD_PASS" id="board_pass">
            </div>
            <button type="submit" class="btn btn-primary">전송</button> 
            <button type="submit" class="btn btn-danger"
                    data-dismiss="modal">취소</button>
          </form>
        </div>
      </div>
    </div>
  </div><%-- 삭제 버튼의 modal 끝 --%>






<%----------------------------------------------- 여기서부터 w3school modal --------------------------------------------------%>
<div id="myModal2" class="modal2">
  <span class="close cursor" onclick="closeModal()">&times;</span>
  <div class="modal-content2">

    <div class="mySlides">
    <c:if test="${!empty boarddata.BOARD_FILE1}">
      <img class="img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE1}" style="width:100%">
	</c:if>
	<c:if test="${empty boarddata.BOARD_FILE1}">
      <img style="display:none" class="img" src="" style="width:100%">
	</c:if>
    </div>
	
    <div class="mySlides">
	<c:if test="${!empty boarddata.BOARD_FILE2}">
      <img class="img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE2}" style="width:100%">
    </c:if>
    <c:if test="${empty boarddata.BOARD_FILE2}">
      <img style="display:none" class="img" src="" style="width:100%">
	</c:if>
    </div>
	
    <div class="mySlides">
	<c:if test="${!empty boarddata.BOARD_FILE3}">
      <img class="img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE3}" style="width:100%">
    </c:if>
    <c:if test="${empty boarddata.BOARD_FILE3}">
      <img style="display:none" class="img" src="" style="width:100%">
	</c:if>
    </div>
    
    <div class="mySlides">
    <c:if test="${!empty boarddata.BOARD_FILE4}">
      <img class="img" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE4}" style="width:100%">
    </c:if>
    <c:if test="${empty boarddata.BOARD_FILE4}">
      <img style="display:none" class="img" src="" style="width:100%">
	</c:if>
    </div>
    
    <!-- 
        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>
     -->

    <div class="caption-container">
      <p id="caption"></p>
    </div>

	
    
    <div class="column">
	<c:if test="${!empty boarddata.BOARD_FILE1}">
      <img class="main_image img demo cursor" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE1}" style="width:100%" onclick="currentSlide(1)" alt="${boarddata.BOARD_ORIGINAL1}">
    </c:if>
    <c:if test="${empty boarddata.BOARD_FILE1}"> <!-- 이걸 추가해준 이유는 F12 console창에서 발생하는 오류를 없애기 위함. class="demo"가 없으면 function에서 찾지 못하고 에러 발생하는 듯-->
      <img style="display:none" class="main_image img demo cursor" src="" style="width:100%" alt="">
    </c:if>
    </div>
    
    
    <div class="column">
    <c:if test="${!empty boarddata.BOARD_FILE2}">
      <img class="main_image img demo cursor" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE2}" style="width:100%" onclick="currentSlide(2)" alt="${boarddata.BOARD_ORIGINAL2}">
    </c:if>
    <c:if test="${empty boarddata.BOARD_FILE2}">
      <img style="display:none" class="main_image img demo cursor" src="" style="width:100%" alt="">
    </c:if>
    </div>
    
    <div class="column">
    <c:if test="${!empty boarddata.BOARD_FILE3}">
      <img class="main_image img demo cursor" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE3}" style="width:100%" onclick="currentSlide(3)" alt="${boarddata.BOARD_ORIGINAL3}">
    </c:if>
    <c:if test="${empty boarddata.BOARD_FILE3}">
      <img style="display:none" class="main_image img demo cursor" src="" style="width:100%" alt="">
    </c:if>
    </div>
    
    <div class="column">
    <c:if test="${!empty boarddata.BOARD_FILE4}">
      <img class="main_image img demo cursor" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE4}" style="width:100%" onclick="currentSlide(4)" alt="${boarddata.BOARD_ORIGINAL4}">
    </c:if>
    <c:if test="${empty boarddata.BOARD_FILE4}">
      <img style="display:none" class="main_image img demo cursor" src="" style="width:100%" alt="">
    </c:if>
    </div>
  </div>
</div>

<script>
function openModal() {
  document.getElementById("myModal2").style.display = "block";
}

function closeModal() {
  document.getElementById("myModal2").style.display = "none";
}

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
  var captionText = document.getElementById("caption");
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
  captionText.innerHTML = dots[slideIndex-1].alt;
}
//////////윗 부분이 w3school에서 가져온 내용


//메시지 보내기 버튼 클릭 - detail 페이지 중앙에 있는 버튼.
function messagePopUp(){
	var receiver = "${boarddata.BOARD_NAME}"; 
	window.open("${pageContext.request.contextPath}/message/send?receiver_id="+receiver,
			    "post", "width=600, height=700, scrollbars=yes");
};

// 댓글 작성자 옆에 있는 메시지 이미지 클릭
function messagePopUp2(){
	var receiver = $('input[name=sendMessage]:checked').val(); //메시지 이미지를 클릭하면 옆에 있는 id를 가져옴 
	window.open("${pageContext.request.contextPath}/message/send?receiver_id="+receiver,
			    "post", "width=600, height=700, scrollbars=yes");
};

//목록으로 버튼 클릭시 이벤트
$("#BackToList").click(function(){
	location.href="${pageContext.request.contextPath}/image_board/list";
});

//public String BoardDeleteAction()에서 비밀번호 틀린 경우 detail페이지로 온다.
var result="${result}";
if(result == 'passFail'){
	alert("비밀번호가 일치하지 않습니다.")
}else if(result == 'Duplication'){
	alert("하나의 게시글에는 한 번만 추천이 가능합니다.")
}

//삭제 버튼 클릭 후 나오는 modal에서 submit하는 것
$("#deleteForm").submit(function(){
	if($("#board_pass").val() ==''){
		alert("비밀번호를 입력하세요!");
		$("#board_pass").focus();
		return false;
	}
})//form
</script>
    
</body>
</html>