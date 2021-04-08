<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- 부트스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	
</head>
<style>
	.menu-navi.menu-main .subMenuNaviListDiv {
    position: absolute;
    width: auto;
    height: auto;
    z-index: 1000;
    padding-top: 0.9em;
    margin-left: -0.9em;
    opacity: 0;
    visibility: hidden;
    -webkit-transition: opacity 0.4s,visibility 0.4s;
    -moz-transition: opacity 0.4s,visibility 0.4s;
    -ms-transition: opacity 0.4s,visibility 0.4s;
    -o-transition: opacity 0.4s,visibility 0.4s;
    transition: opacity 0.4s,visibility 0.4s;
}

.menu-name {
    display: inline-block;
}

#body.mobile .site-element.headerMenuList .menu-navi {
    color: #CD181E;
    font-size: 13px;
}

#body.mobile .site-element.headerMenuList .menu-navi.now > .menu-name, #body.mobile .site-element.headerMenuList .menu-navi.now > .menu-opener {
    color: #9C1217!important;
}

site-element .menu-navi {
    display: inline-block;
    cursor: pointer;
    color: inherit;
    margin-right: 1em;
}

li.menu-navi.menu-main.pageMenu.subMenu-exist{
color:#dc3545;
}
</style>
<div>
	<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 ">
    <a href="/home/main" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
      <img src="${pageContext.request.contextPath}/resources/image/mainPage_icon.png" width="180" height="180"></svg>
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
      <li class="menu-navi menu-main pageMenu subMenu-exist">
      <a href="/do_board/list" alt="산책 신청" class="nav-link px-2 link-danger">산책 신청</a>
       <div class="subMenuNaviListDiv">
        	<ul class="subMenuNaviList">
        		<li class="menu-navi menu-sub pageMenu">
        		 <a class="menu-name" alt="주인" href="/do_board/list">주인</a>
        		 <div class="menu-opner"></div>
        		</li>
        		<li class="menu-navi menu-sub pageMenu">
        		 <a class="menu-name" alt="산책러" href="/dw_board/list">산책러</a>
        		 <div class="menu-opner"></div>
        		</li>
        	</ul>
        </div>
      </li>
     <!--  <li>
      <select>
      	<option value="walker"><a href="/dw_board/list" class="nav-link px-2 link-danger">산책러</a></option>
      	<option value="owner"><a href="/do_board/list" class="nav-link px-2 link-danger">주인</a></option>
      </select>
      </li> -->
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