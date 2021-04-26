<%@ page language="java" contentType="text/html; charset=utf-8"%>     
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../common/header.jsp"/>
<title>산책로 추천 게시판 - 리스트</title>

<script>
var result="${result}";
if(result == 'deleteSuccess'){
	alert("${num}번글 삭제 성공입니다.")
}
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
	

	
});


$(function(){	
	$("#write").click(function(){
		location.href="write";
	})
});
</script>
<style>
table caption{caption-side:top; text-align:center;}
h1{text-align:center}
li .current{
  background:#faf7f7; color:gray;
}
body > div > table > tbody > tr > td:last-child > a{color:red}
#form_list{margin: 0 auto; width: 80%; text-align:center;}
select{
  color: #495057;
  background-color:#fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: .25rem;
  transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
  outline: none;
}
.container{width:60%}
td:nth-child(1){width:10%}
td:nth-child(2){width:40%}
td:nth-child(3){width:20%}
td:nth-child(4){width:20%}
.input-group{margin-bottom:3em}
.anchor{color:black;}
.anchor:hover{text-decoration:none; color:black;}
#title{font-size:32px;}
#write, #search{color:white; background-color:#dc3545; border:none;}
</style>
<%--
Console창에서 실행된 sql문을 확인하려면
log4j.xml에
<logger name="ImageBoards">
  <level value="trace" />
</logger>
이것을 추가해야 한다.

 --%>
</head>
<body>
<div class="container">
  <a id="title" class="anchor" href="${pageContext.request.contextPath}/image_board/list">
    <img src="${pageContext.request.contextPath}/resources/image/dog_walking.png" alt="산책 이미지" width="60px">
        산책로 추천 게시판
  </a><br><br><br>
  <form id="form_list" action="list" >
    <div class="input-group">
      <select id="viewcount" name="search_field">
        <option value="N" selected>작성자</option>
        <option value="S">제목</option>
        <option value="C">내용</option>
        <option value="SC">제목 또는 내용</option>
      </select>
      <input name="search_word" type="text" class="form-control"
              placeholder="작성자를 입력하세요." value="${search_word}">
      <button id="search" class="btn btn-primary" type="submit">검색</button>        
    </div>
  </form>
 
  <!--------------------------- best3 게시글 시작 ---------------------------->
  <c:if test="${bestlistcount >0}">
  <table class="table table-striped">
  <thead>
    <tr>
      <th colspan="5">
        <img src="${pageContext.request.contextPath}/resources/image/best.png" alt="best" width="45px">
        Best3 게시글
      </th>
    </tr>	
    <tr>
      <th><div>추천수</div></th>
      <th><div>제목</div></th>
      <th><div>작성자</div></th>
      <th><div>날짜</div></th>
      <th><div>조회수</div></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="b" items="${bestlist}">
    <tr>
      <td> <%-- 추천수 --%>
        <c:out value="${b.RECOMMEND_COUNT}"/> 
      </td>
      <td> <%-- 제목 --%>
        <div>             
            <a class="anchor" href="detail?num=${b.BOARD_NUM }">
              <c:out value="${b.BOARD_SUBJECT}"/><span style="color:red">&nbsp;[${b.REPLY_COUNT}]</span>
              <%-- escapeXml="true" : HTML 태그를 화면에 그대로 보여줍니다. --%>        
            </a>
        </div>
      </td>
      <td><div>${b.BOARD_NAME}</div></td>
      <td><div>${b.BOARD_DATE}</div></td>
      <td><div>${b.BOARD_READCOUNT}</div></td>
    </tr>
    </c:forEach>
  </tbody>
  </table><br><br><br>
  </c:if>
  <!----------------- best3 게시글 끝 ------------------------>
 
 
 
 
  <c:if test="${listcount >0}"> <%-- 등록된 글이 있는 경우 --%>
  <table class="table table-striped">
    <thead>
      <tr>
        <th colspan="3">일반 게시글</th>
        <th colspan="2"><font size=3>글 개수: ${listcount}</font>
        </th>
      </tr>
      <tr>
        <th><div>번호</div></th>
        <th><div>제목</div></th>
        <th><div>작성자</div></th>
        <th><div>날짜</div></th>
        <th><div>조회수</div></th>
      </tr>
    </thead>
    <tbody>
      <c:set var="num" value="${listcount-(page-1)*limit}"/>
      <c:forEach var="b" items="${boardlist}">
      <tr>
        <td> <%-- 번호 --%>
          <c:out value="${num}"/> 
          <c:set var="num" value="${num-1}"/> 
        </td>
        <td> <%-- 제목 --%>
          <div>             
              <a class="anchor" href="detail?num=${b.BOARD_NUM }">
                <c:out value="${b.BOARD_SUBJECT}"/><span style="color:red">&nbsp;[${b.REPLY_COUNT}]</span>
                <%-- escapeXml="true" : HTML 태그를 화면에 그대로 보여줍니다. --%>        
              </a>
          </div>
        </td>
        <td><div>${b.BOARD_NAME}</div></td>
        <td><div>${b.BOARD_DATE}</div></td>
        <td><div>${b.BOARD_READCOUNT}</div></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  
  
  
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
          <a href="list?page=${page-1}&search_field=${search_field}&search_word=${search_word}"    
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
            <a href="list?page=${a}&search_field=${search_field}&search_word=${search_word}"
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
          <a href="list?page=${page+1}&search_field=${search_field}&search_word=${search_word}"
             class="page-link">&nbsp;다음</a>
        </li>
      </c:if>      
    </ul>
  </div>  
  </c:if>  <%-- <c:if test="${listcount > 0}">  end --%>

<%-- 게시글이 없는 경우 --%>
<c:if test="${listcount == 0 && empty search_word}">
  <h1>등록된 글이 없습니다.</h1>
</c:if>

<%-- 검색 결과가 없는 경우 --%>
<c:if test="${listcount == 0 && !empty search_word}">
  <h1>검색 결과가 없습니다.</h1>
</c:if>

<button id="write" type="button" class="btn btn-info float-right">글쓰기</button>

</div>  <%-- <div class="container"> end --%>
<br><br><br><br><br><br>

</body>
</html>