<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>	
	<jsp:include page = "/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
	<script src="${pageContext.request.contextPath}/resources/js/list.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/sigungu.js"></script>
	
<style>
div.gallery {
  border: 1px solid #ccc;
}

div.gallery:hover {
  border: 1px solid #777;
}

div.gallery img {
  width: 100%;
  height: auto;
}

div.desc {
  padding: 15px;
  text-align: center;
}

* {
  box-sizing: border-box;
}

.responsive {
  padding: 0 6px;
  float: left;
  width: 24.99999%;
}

#desc{text-align:left}
body > div.container > div.container{margin-bottom:3em;}
</style>
</head>
<title>산책 신청(Owner)</title>
<body>
<div class = "container">

<%-- 게시글이 있는 경우 --%>
<c:if test = "${listcount > 0 }">
<!-- 게시판 타이틀 -->
<p class="text-danger">
	<font size = 4>산책 신청 게시판 (주인)&nbsp;&nbsp;&nbsp;</font>
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

				<!-- <select id="searchField">
					<option value="dogowner" selected>견주</option>
					<option value="walker">산책러</option>
					<option value="subject+content">제목+내용</option>
				</select> -->
				
				<select name="addressRegion" id="addressRegion"></select>
				<select name="addressDo" id="addressDo"></select>
				<select name="addressSiGunGu" id="addressSiGunGu"></select>
				
				
				<span class="search">
					<button id="search_button"><img src="${pageContext.request.contextPath}/resources/image/search.png" width="15px"></button>
					<input type="text" id="search" placeholder=" 검색어를 입력하세요.">
				</span>
		</div>

<c:forEach var = "b" items="${boardlist}">

	<div class="responsive">
	  <div class="gallery">
	    <a target="_blank" href="detail?num=${b.BOARD_NUM}">
	      <img src="${pageContext.request.contextPath}/resources/upload${b.DOG_PHOTO}" alt="dog" width="600" height="400">
	    </a>
	    <div id="desc">${b.BOARD_SUBJECT}</div>
	  </div>
	</div>
</c:forEach>

<div class="clearfix"></div>

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
	<font size=5>등록된 글이 없습니다.</font>
</c:if> 
		<button type = "button" onclick="location.href='/do_board/write'" class = "btn btn-info float-right">글 쓰 기</button>

</div>
</body>
</html>
