<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
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
</style>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
</head>
<title>산책 신청(Owner)</title>
<body>
<div class = "container">

<%-- 게시글이 있는 경우 --%>
<c:if test = "${listcount > 0 }">

<!-- 게시판 타이틀 -->
<p class="text-danger">
	<font size = 4>산책 신청 게시판&nbsp;&nbsp;&nbsp;</font>
	<em id="listcount" class="text-danger"> ${listcount}개의 게시물</em>
	</p>
<hr class="text-danger"> 

<div class="responsive">
  <div class="gallery">
    <a target="_blank" href="${pageContext.request.contextPath}/resources/image/dog1.jpeg">
      <img src="${pageContext.request.contextPath}/resources/image/dog1.jpeg" alt="dog" width="600" height="400">
    </a>
    <div id="desc">산책시켜주실분 구합니다</div>
  </div>
</div>


<div class="responsive">
  <div class="gallery">
    <a target="_blank" href="egg.jpeg">
      <img src="${pageContext.request.contextPath}/resources/image/kongsoon.png" alt="dog" width="600" height="400">
    </a>
    <div id="desc">산책시켜주실분 구합니다</div>
  </div>
</div>

<div class="responsive">
  <div class="gallery">
    <a target="_blank" href="${pageContext.request.contextPath}/resources/image/dog2.jpeg">
      <img src="${pageContext.request.contextPath}/resources/image/dog2.jpeg" alt="dog" width="600" height="400">
    </a>
    <div id="desc">산책시켜주실분 구합니다</div>
  </div>
</div>

<div class="responsive">
  <div class="gallery">
    <a target="_blank" href="${pageContext.request.contextPath}/resources/image/dog3.jpeg">
      <img src="${pageContext.request.contextPath}/resources/image/dog3.jpeg" alt="dog" width="600" height="400">
    </a>
    <div id="desc">산책시켜주실분 구합니다</div>
  </div>
</div>


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
		<button type = "button" class = "btn btn-info float-right">글 쓰 기</button>
	</div>

</c:if> <%-- c:if test = "${listcount > 0}"> end --%>

<%-- 게시글이 없는 경우 --%>
<%-- <c:if test = "${listcount == 0 }">
	<font size = 5>등록된 글이 없습니다.</font>
</c:if> --%>

</div>
</body>
</html>
