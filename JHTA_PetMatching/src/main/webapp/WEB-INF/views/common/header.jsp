<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
    <!-- 부트스트랩 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
		
</head>
<style>
.dropbtn {
  background-color: white;
  color: #dc3545;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #dc3545;
  width: 70px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: white;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ab0010; color:white}

.dropdown:hover .dropdown-content {display: block;}

.navbar{padding-right:3em;}
</style>
<script>
	$(function (){
		$("#logout").click(function(event){
			$("form").submit();
		})
		
		$(".btn1").click(function(){
			location.href = "${pageContext.request.contextPath}/member/login";
		})
		
		$(".btn2").click(function() {
			location.href = "${pageContext.request.contextPath}/member/join";
		})
	})
</script>
<div>
	<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 ">
    <a href="/home/main" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
      <img src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" width="180" height="180"></svg>
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
	  <li>
       <div class="dropdown">
       <button class="dropbtn nav-link px-2 link-danger">산책 신청</button>
        	<div class="dropdown-content">
        		 <a class="nav-link px-2 link-danger" href="/do_board/list">주인</a>
        		 <a class="nav-link px-2 link-danger" href="/dw_board/list">산책러</a>
	        </div>
	       </div>
      </li>
      <li><a href="#" class="nav-link px-2 link-danger">산책로 추천</a></li>
      <li><a href="/free_board/list" class="nav-link px-2 link-danger">자유게시판</a></li>
      <li><a href="/shop/list" class="nav-link px-2 link-danger">쇼핑몰</a></li>
    </ul>
   
   <sec:authorize access="isAnonymous()">
    <div class="col-md-3 text-end">
      <span class="btn1"><button type="button" class="btn btn-outline-danger me-2">Login</button></span>
      <span class="btn2"><button type="button" class="btn btn-danger">Sign-up</button></span>
    </div>
    </sec:authorize>
    
    <nav class="navbar navbar-expand-sm right-block navbar-dark">
	
	<ul class="navbar-nav">
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="pinfo" />
			<li class="nav-item">
				<form action="${pageContext.request.contextPath }/member/logout" method = "post">
					<a class="nav-link" href="#" id ="logout">
					<span id = "loginid" style="color:#dc3545">${pinfo.username} 님(로그아웃)</span></a>
					<input type="hidden" name ="${_csrf.parameterName }" value = "${_csrf.token }">
				</form>
			</li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/member/update" style="color:#dc3545">정보수정</a></li>

			<c:if test="${pinfo.username=='admin'}">
				<!-- Dropdown -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown" style="color:#dc3545"> 관리자 </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/member/list">회원정보</a> 
						<a class="dropdown-item" href="${pageContext.request.contextPath }/board/list">게시판</a>
					</div></li>
			</c:if>
		</sec:authorize>
	</ul>
</nav>
  </header>
</div>