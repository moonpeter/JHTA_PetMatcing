<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<jsp:include page = "/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<script src = "${pageContext.request.contextPath}/resources/js/views3.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/list.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
	<script>
		var result = "${result}";
		if(result == 'deleteSucess'){
			alert("삭제 성공입니다.")
		} else if(result == 'updateSuccess'){
			alert("회원 정보가 수정되었습니다.")
		}
	</script>
<style>
#speechbubble{float:right;}
#count {position: relative;color: #dc3545;}
</style>
<title>자유게시판</title>
</head>
<body>
<div class = "container">

<%-- 게시글이 있는 경우 --%>
<c:if test = "${listcount > 0 }">

		<!-- 게시판 타이틀 -->
		<p class="text-danger">
		  <font size = 4>자유게시판&nbsp;&nbsp;&nbsp;</font>
		  <em id="listcount" class="text-danger"> ${listcount}개의 게시물</em>
		</p>
		<hr class="text-danger"> 
		
		
		<!-- 게시판 리스트 기능 메뉴 -->
		<div class="container">
				<select class = "form-control" id="viewcount">
					<option value="5">5개씩 보기</option>
					<option value="10" selected>10개씩 보기</option>
					<option value="15">15개씩 보기</option>
				</select>&nbsp;&nbsp;&nbsp; 

				<select id="searchField">
					<option value="dogowner" selected>견주</option>
					<option value="walker">산책러</option>
					<option value="subject+content">제목+내용</option>
				</select>
				
				<span class="search">
					<button id="search_button"><img src="${pageContext.request.contextPath}/resources/image/search.png" width="15px"></button>
					<input type="text" id="search" placeholder=" 검색어를 입력하세요.">
				</span>
		</div>
		
	<table>
	  <thead class="text-danger">
		<tr>
			<td><div>번호</div></td>
			<td><div>제목</div></td>
			<td><div>작성자</div></td>
			<td><div>날짜</div></td>
			<td><div>조회수</div></td>
		</tr>
	 </thead>
	<tbody>
		<c:set var = "num" value="${listcount-(page-1)*limit}" />
		<c:forEach var = "b" items="${boardlist}">
		<tr>
			<td><%-- 번호 --%>
				<c:out value = "${num}" />	<%-- num 출력 --%>
				<c:set var = "num" value="${num-1}" /> <%-- num = num - 1; 의미 --%>
			</td>
			<td> <%-- 제목 --%>
				<div>
					<%-- 답변 글 제목 앞에 여백 처리 부분
						 BOARD_RE_LEV, BOARD_NUM,
						 BOARD_SUBJECT, BOARD_NAME, BOARD_DATE,
						 BOARD_READCOUNT : property 이름 --%>
				<c:if test = "${b.BOARD_RE_LEV != 0 }"> <!-- 답글인 경우 -->
					<c:forEach var = "a" begin = "0" end = "${b.BOARD_RE_LEV*2}" step = "1">
					&nbsp;
					</c:forEach>
					<img src ="${pageContext.request.contextPath}/resources/image/line.gif">
				</c:if>
					
				<c:if test = "${b.BOARD_RE_LEV == 0}">	<%-- 원문인 경우 --%>
					&nbsp;
				</c:if>
				
				<a href = "detail?num=${b.BOARD_NUM}">
					<c:out value = "${b.BOARD_SUBJECT}" escapeXml="true" />
					<%-- escapeXml = "true" : HTML 태그를 화면에 그대로 보여줍니다. --%>
				</a>
				<span id="count">
				<img id="speechbubble" src ="${pageContext.request.contextPath}/resources/image/speechbubble.png" width="30px">${count}</span>
			</div>
			</td>
			<td><div>${b.BOARD_NAME}</div></td>
			<td><div>${b.BOARD_DATE}</div></td>
			<td><div>${b.BOARD_READCOUNT}</div></td>
		 </tr>
		</c:forEach>
	</tbody>
	</table>
	
	<div class = "center-block">
		<ul class = "pagination justify-content-center">
			<c:if test = "${page <= 1}">
				<li class = "page-item">
					<a class = "page-link gray">이전&nbsp;</a>
				</li>
			</c:if>
			<c:if test = "${page > 1 }">
				<li class = "page-item">
					<a href = "BoardList.bo?page=${page-1}"
					   class = "page-link">이전&nbsp;</a>
				</li>
			</c:if> 
			
			<c:forEach var = "a" begin = "${startpage}" end="${endpage}">
				<c:if test = "${a == page }">
					<li class = "page-item">
						<a class = "page-link gray">${a}</a>
					</li>
				</c:if>
				<c:if test = "${a != page }">
					<li class = "page-item">
						<a href = "BoardList.bo?page=${a}"
						class="page-link">${a}</a>
					</li>
				</c:if>
			</c:forEach>
			
			<c:if test = "${page >= maxpage }">
				<li class = "page-item">
					<a class = "page-link gray">&nbsp;다음</a>
				</li>
			</c:if>
			<c:if test = "${page < maxpage }">
				<li class = "page-item">
					<a href = "BoardList.bo?page=${page+1}"
					   class = "page-link">&nbsp;다음</a>
				</li>
			</c:if>
		</ul>
	</div>
	
</c:if> <%-- c:if test = "${listcount > 0}"> end --%>

<%-- 게시글이 없는 경우 --%>
<c:if test = "${listcount == 0 }">
	<font size = 5>등록된 글이 없습니다.</font>
</c:if>
		<button type="button" onclick="location.href='/free_board/write'" class = "btn btn-info float-right">글 쓰 기</button>
</div>
</body>
</html>