<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<jsp:include page = "/WEB-INF/views/common/header.jsp" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/list.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/list.css">
	<script>
		
		var result = "${result}";
		if(result == 'deleteSucess'){
			alert("삭제 성공입니다.")
		} else if(result=='updateSuccess'){
			alert("수정되었습니다.")
		}
		
		
		$(function(){
			var selectedValue = '${search_field}'
			if(selectedValue != '')
				$("#viewcount").val(selectedValue);
		
		$("button").click(function(){
			var word=$(".input-group input").val();
			if(word==''){
				alert("검색어를 입력하세요.");
				$("input").focus();
				return false;
			}
		});
		})
	</script>
	<style>
		#count{float:right; color:#dc3545}
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
				<img id="speechbubble" src ="${pageContext.request.contextPath}/resources/image/speechbubble.png" width="30px">(${b.cnt})</span>
				<%-- <span class="badge bg-danger"><i class="fa fa-comment-o"></i> + ${count}</span> --%>
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
<%-- <c:if test = "${listcount == 0 }">
	<font size = 5>등록된 글이 없습니다.</font>
</c:if> --%>

<c:if test = "${listcount == 0 && !empty search_word }">
	
		<!-- 게시판 타이틀 -->
		<p class="text-danger">
		  <font size = 4>자유게시판&nbsp;&nbsp;&nbsp;</font>
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
		<button type="button" onclick="location.href='/free_board/write'" class = "btn btn-info float-right">글 쓰 기</button>
		
</div>
</body>
</html>