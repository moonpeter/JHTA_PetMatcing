<%@ page language="java" contentType="text/html; charset=utf-8"%>     
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../common/header.jsp"/>
<title>받은 메시지함</title>
<%--

4. 회원 목록의 삭제를 클릭한 경우
   confirm("정말 삭제하시겠습니까?")를 실행해서 취소를 클릭하면 "memberDelete.net"으로 이동하지 않습니다.   

 --%>
<script>
$(function(){
	//검색 클릭 후 응답화면에는 검색시 선택한 필드가 선택되도록 합니다.
	var selectedValue = '${search_field}'
	if(selectedValue != ''){
		$('#viewcount').val(selectedValue);
	}
	
	//검색 버튼 클릭한 경우
	$("#search").click(function(){
		//검색어 공백 유효성을 검사합니다.
		var word=$(".input-group input").val();
		if(word==''){
			alert("검색어를 입력하세요");
			return false;
		}
				
	}); //$("button").click end;
	
	
	//검색어 입력창에 placeholder가 나타나도록 합니다.
	$('#viewcount').change(function(){
		var value =$("select option:selected").text();
		$("input").val('');
		$("input").attr("placeholder", value+ " 입력하세요");
	}); // $('#viewcount').change end;
	
	
	
	
	//선택 메시지 삭제 버튼을 클릭한 경우
	$("#delete").click(function(event){
		var answer = confirm("정말 삭제하시겠습니까?");
		console.log(answer); //취소를 클릭한 경우-false
		if(!answer){ //취소를 클릭한 경우
			event.preventDefault(); //이동하지 않습니다.
		}
	});
	
	
	// '선택 메시지 삭제' 버튼을 눌러서 submit하는 경우
	$("#delete_data").submit(function(){
		//체크박스 선택한 것이 1개도 없는 경우
		var cnt1 =$('input[name=select_delete]:checked').length;
		if (cnt1==0){
			alert("삭제할 메시지를 선택하세요.");
			return false;
		}
	});
		
}); //ready end


//메시지 보내기 버튼 클릭
function messagePopUp(){
      window.open("${pageContext.request.contextPath}/message/send",
    		      "post", "width=600, height=700, scrollbars=yes");
};


//컨트롤러에서 보내는 결과값으로 경고창 출력
var result="${result}";
if(result == 'deleteSuccess'){
	alert("${delete_count}개의 메시지가 삭제되었습니다.")
}


</script>
<style>
h2{text-align:center;}
li .current{
  background:#faf7f7; color:gray;
}

form:nth-child(6){margin: 0 auto; width: 80%; text-align:center;}
select{
  color: #495057;
  background-color:#fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: .25rem;
  transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
  outline: none;
}
.container{width:80%}
.input-group{margin-bottom:3em}
.sender{display:inline-block; width:15%}
.title{display:inline-block; width:45%}
.date{display:inline-block; width:20%}
.read{display:inline-block; width:17%}
a{color:black;}
a:hover{text-decoration:none;}
#count{float:right; margin-right:4%}
#title{font-size:35px;}
#image1{margin-bottom:1%; margin-right:2%}
#delete, #send{background-color:white; color:#dc3545; border-color:#dc3545; height:40px;
        border-radius:10px;}
#search{background-color:#dc3545; border:0;}
button:focus{outline:none;}        
</style>
<%--
Console창에서 실행된 sql문을 확인하려면
log4j.xml에
<logger name="Messages">
  <level value="trace" />
</logger>
이것을 추가해야 한다.

 --%>
</head>
<body>
<div class="container">
  <img id="image1" src="${pageContext.request.contextPath}/resources/image/message.png" alt="메시지 " width="50px">
  <span id="title">받은 메시지함</span><br><br><br>
  <form action="receiveMessageList" >
    <div class="input-group">
      <select id="viewcount" name="search_field">
        <option value="S" selected>보낸이</option>
        <option value="T">제목</option>
        <option value="C">내용</option>
        <option value="TC">제목 또는 내용</option>
      </select>
      <input name="search_word" type="text" class="form-control"
              placeholder="보낸이의 id를 입력하세요." value="${search_word}">
      <button id="search" class="btn btn-primary" type="submit">검색</button>        
    </div>
  </form><br>
    
  <%-- 받은 메시지가 있는 경우 --%>
  <c:if test="${receiveMessageCount >0}"> 
  <form action="deleteByReceiver" method="post" id="delete_data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div id="count"><b>받은 메시지 수 : ${receiveMessageCount}</b></div>
    <button type="submit" id="delete">
      <img id="image2" src="${pageContext.request.contextPath}/resources/image/delete_message.png" alt="메시지  삭제" width="30px">    
          선택 메시지 삭제
    </button><br><br>
    &nbsp;&nbsp;&nbsp;&nbsp;<span class="sender"><b>보낸이</b></span><span class="title"><b>제목</b></span>
    <span class="date"><b>받은 날짜</b></span><span class="read">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>읽음여부</b></span><hr>
    <c:forEach var="r" items="${receiveMessageList}">
      <input type="checkbox" name="select_delete" value="${r.message_num}">
      <a href="detailReceiveMessage?num=${r.message_num}">
      <span class="sender">${r.sender_id}</span><span class="title">${r.message_title}</span><span class="date">${r.register_date}</span>
      <c:if test="${r.read_count >0}">
        <span class="read">
          &nbsp;<img src="${pageContext.request.contextPath}/resources/image/open_message.png" alt="메시지 읽음" width="20px">&nbsp;메시지 읽음
        </span>
      </c:if>
      <c:if test="${r.read_count==0}">
        <span class="read">
          <img src="${pageContext.request.contextPath}/resources/image/new_message.png" alt="메시지 읽지않음" width="30px">메시지 읽지않음
        </span>
      </c:if>
      </a><hr>
    </c:forEach>
  

  <div>
    <ul class="pagination justify-content-center">
      <c:if test="${page <=1 }">
        <li class="page-item">
          <a class="page-link current" href="#">이전&nbsp;</a>
        </li>
      </c:if>
      <c:if test="${page >1 }">
        <li class="page-item">
          <%-- 아래 주소 띄어쓰면 안 됨 --%>
          <a href="receiveMessageList?page=${page-1}&search_field=${search_field}&search_word=${search_word}"    
             class="page-link">이전</a>&nbsp;
        </li>
      </c:if>
      
      <c:forEach var="a" begin="${startpage}" end="${endpage}">
        <c:if test="${a==page }">
          <li class="page-item">
            <a class="page-link current" href="#">${a}</a>
          </li>
        </c:if>
        <c:if test="${a !=page }">
          <li class="page-item">
            <%-- 아래 주소 띄어쓰면 안 됨 --%>
            <a href="receiveMessageList?page=${a}&search_field=${search_field}&search_word=${search_word}"
               class="page-link" >${a}</a>
          </li>
        </c:if>
      </c:forEach>
      
      <c:if test="${page >= maxpage }">
        <li class="page-item">
          <a class="page-link current" href="#">&nbsp;다음</a>
        </li>
      </c:if>
      <c:if test="${page < maxpage }">
        <li class="page-item">
          <a href="receiveMessageList?page=${page+1}&search_field=${search_field}&search_word=${search_word}"
             class="page-link">&nbsp;다음</a>
        </li>
      </c:if>      
    </ul>
  </div>  
  </form>
  </c:if>
</div>

<%-- 받은 메시지가 없는 경우 --%>
<c:if test="${receiveMessageCount == 0 && empty search_word}">
  <br><br><br><h2>받은 메시지가 없습니다.</h2><br><br><br>
</c:if>

<%-- 검색 결과가 없는 경우 --%>
<c:if test="${receiveMessageCount == 0 && !empty search_word}">
  <br><br><br><h2>검색 결과가 없습니다.</h2><br><br><br>
</c:if>

<div class="container">
  <div class="input-group">
  

  <button type="button" id="send" onClick="messagePopUp()">
    <img id="image3" src="${pageContext.request.contextPath}/resources/image/send_message.png" alt="메시지  보내기" width="30px">  
       메시지 보내기
  </button>

  </div>
</div>

</body>
</html>