<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- 부트스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	
	
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

</style>
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
      <li><a href="/shop/main" class="nav-link px-2 link-danger">쇼핑몰</a></li>
    </ul>
   
    <div class="col-md-3 text-end">
      <button type="button" class="btn btn-outline-danger me-2">Login</button>
      <button type="button" class="btn btn-danger">Sign-up</button>
    </div>
  </header>
</div>