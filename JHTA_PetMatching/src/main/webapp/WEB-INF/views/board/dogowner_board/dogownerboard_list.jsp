<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>	
	<jsp:include page = "/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
	<script src="${pageContext.request.contextPath}/resources/js/list_do.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/resources/js/sigungu.js"></script> --%>
	<script>
	$(function(){
		var selectedValue = '${search_field}'
		if(selectedValue != '')
			$("#viewcount").val(selectedValue);
	
	$("#search_button").click(function(){
		var word = $(".search input").val();
		if(word == ''){
			alert("검색어를 입력하세요.");
			return false;
		}
	});
	
	})
	
	var result = "${result}";
	if(result == 'deleteSucess'){
		alert("삭제 성공입니다.")
	} else if(result == 'updateSuccess'){
		alert("수정되었습니다.")
	}
	</script>
<style>
div.gallery {
  border: none
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

#desc{text-align:left; width:80; margin:0 }
body > div.container > div.container{margin-bottom:3em;}
body > div.container > div.container > div > form > div{height:50px}
#speechbubble{width:30px}
#count{float:right; margin-right:0.1em; color:#dc3545}
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

				<!-- 검색창 -->
			<div class="search">
				<form action="search_list">
				<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="S">제목</option>
					<option value="C">내용</option>
					<option value="W">작성자</option>
					<option value="SC">제목+내용</option>
				</select> &nbsp;
				
				<!-- <select name="addressRegion" id="addressRegion"></select>
				<select name="addressDo" id="addressDo"></select>
				<select name="addressSiGunGu" id="addressSiGunGu"></select> -->
				
				
				<div class="search">
					<input  class="form-control" type="text" id="search" name="search_word" placeholder=" 검색어를 입력하세요." value="${search_word}">
				</div>&nbsp;
					<button id="search_button" type="submit">
					<img src="${pageContext.request.contextPath}/resources/image/search.png" width="15px"></button>
				</div>
			  </form>
		     </div>
		</div>
		

<c:forEach var = "b" items="${boardlist}">

	<div class="responsive">
	  <div class="gallery">
	    <a href="detail?num=${b.BOARD_NUM}">
	      <img src="${pageContext.request.contextPath}/resources/upload/doBoard${b.DOG_PHOTO}" alt="dog" >
	    </a>
	    <span id="desc">${b.BOARD_SUBJECT}</span>
	    <span id="count">
			<img id="speechbubble" src ="${pageContext.request.contextPath}/resources/image/speechbubble.png" width="30px">(${b.cnt})</span>
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
<%-- <c:if test = "${listcount == 0 }">
	<font size = 5>등록된 글이 없습니다.</font>
</c:if> --%>

<c:if test = "${listcount == 0 && !empty search_word }">
	
		<!-- 게시판 타이틀 -->
		<p class="text-danger">
		  <font size = 4>산책 신청 게시판 (주인)&nbsp;&nbsp;&nbsp;</font>
		  <em id="listcount" class="text-danger"> ${listcount}개의 게시물</em>
		</p>
		<hr class="text-danger"> 
		
		
		<!-- 게시판 리스트 기능 메뉴 -->
		<div class="container">
				<select class = "form-control">
					<option value="5">5개씩 보기</option>
					<option value="10" selected>10개씩 보기</option>
					<option value="15">15개씩 보기</option>
				</select>

		<!-- 검색창 -->
			<div class="search">
				<form action="search_list">
				<div class="input-group">
				<select id="viewcount" name="search_field">
					<option value="S">제목</option>
					<option value="C">내용</option>
					<option value="W">작성자</option>
					<option value="SC">제목+내용</option>
				</select> &nbsp;
				
				<div class="search">
					<input  class="form-control" type="text" id="search" name="search_word" placeholder=" 검색어를 입력하세요." value="${search_word}">
				</div>&nbsp;
					<button id="search_button" type="submit">
					<img src="${pageContext.request.contextPath}/resources/image/search.png" width="15px"></button>
				</div>
			  </form>
		     </div>
		</div>
		
	<font size = 5 color="#dc3545">검색 결과가 없습니다.</font>
	<div class="button">
	<a href = "list">
		<button  type="button" class = "btn btn-info">목록</button>
	</a>
</div></c:if>
		<button type = "button" onclick="location.href='/do_board/write'" class = "btn btn-info float-right">글 쓰 기</button>
		
</div>
</body>
</html>


