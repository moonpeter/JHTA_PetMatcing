<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>


<style>
	.container {
/* 		background-color : pink; */
		padding : 10px;
	}
	
	#category-bar {
		border-bottom: solid 1px #dc3545;
	}
	
	#detail_title{
		font-size: 45px;
		border-bottom: solid 1px #dc3545;		
	}
	
	#detail_info_table{
		font-size: 20px;
	}
	td{width : 50%}
</style>

<jsp:include page="/WEB-INF/views/common/header.jsp" ></jsp:include>

<div class="container" id="category-bar">
	<ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <li><a href="/shop/category_list?category=사료" class="nav-link px-3 link-danger">사료</a></li>
      <li><a href="/shop/category_list?category=간식/건강" class="nav-link px-3 link-danger">간식/건강</a></li>
      <li><a href="/shop/category_list?category=미용/목욕" class="nav-link px-3 link-danger">미용/목욕</a></li>
      <li><a href="/shop/category_list?category=외출용품" class="nav-link px-3 link-danger">외출용품</a></li>
      <li><a href="/shop/category_list?category=의류/악세사리" class="nav-link px-3 link-danger">의류/악세사리</a></li>
      <li><a href="/shop/category_list?category=장난감" class="nav-link px-3 link-danger">장난감</a></li>
      <li><a href="/shop/category_list?category=생활용품" class="nav-link px-3 link-danger">생활용품</a></li>
    </ul>
</div>

<div class="container">
  <div class="row">
    <div class="col-4">
    	<img src="${pageContext.request.contextPath}/resources/upload/shop${shop.shop_thumnail }" class="d-block w-100" alt="thumnail">
    </div>
    
    <div class="col-8">
    	<div id="detail_title" class="">${shop.shop_title }</div>
    	<table id="detail_info_table">
    		<tr>
    			<td class="text-danger">판매가격</td>
    			<td><fmt:formatNumber value="${shop.shop_price}" pattern="###,###,###원"/></td>
    		</tr>
    		<tr>
    			<td class="text-danger">원산지</td>
    			<td>${shop.shop_country_of_origin }</td>
    		</tr>
    		<tr>
    			<td class="text-danger">브랜드</td>
    			<td>${shop.shop_brand }</td>
    		</tr>
    		<tr>
    			<td class="text-danger">평점</td>
    			<td><small class="text-danger">&#9733; &#9733; &#9733; &#9733; &#9733;</small></td>
    		</tr>
    	</table>
    </div>
  </div>
</div>

<div class="container d-flex flex-row-reverse bd-highlight">
	<sec:authentication property="principal" var="pinfo" />
    <c:if test="${pinfo.username == 'admin'}">
  		<button class="btn btn-primary" type="submit" onclick="delete_button()">삭 제</button> 
  		<button class="btn btn-info" type="submit" onclick="location.href='/shop/modifyForm?shop_num=${shop.shop_num}'">수 정</button>
  	</c:if>
</div>

<div class="container d-flex flex-row-reverse bd-highlight">
	<button type="button" class="btn btn-danger me-2">장바구니</button>
	<button type="button" class="btn btn-outline-danger">바로 구매</button>
</div>

<div class="container">
  <div class="row">
    <div class="col-sm ">상품설명</div>
    <div class="col-sm ">상품리뷰(2)</div>
    <div class="col-sm ">배송/교환/환불</div>
  </div>
</div>

<div class="container ">
	<img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/shop${shop.shop_img_content }">
	<%-- <img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/shop${shop.shop_img_content_2 }">
	<img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/shop${shop.shop_img_content_3 }">
	<img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/shop${shop.shop_img_content_4 }">
	<img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/shop${shop.shop_img_content_5 }"> --%>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-7 ">상품후기</div>
    <div class="col-sm ">최신순</div>
    <div class="col-sm ">별점 높은순</div>
    <div class="col-sm ">별점 낮은순</div>    
  </div>
  <div class="row ">
      <div class="col-md-2 ">Best</div><div class="col-sm ">맛있습니다.</div>
  </div>
  <div class="row ">
      <div class="col-md-2 ">Best</div><div class="col-sm ">맛있습니다.</div>
  </div>
  <div class="row ">
      <div class="col-md-2 ">Best</div><div class="col-sm ">맛있습니다.</div>
  </div>

  <div class="row ">
	<p>페이징 처리할 곳</p>
  </div>

  <div class="row ">
	<div class="container  d-flex flex-row-reverse bd-highlight">
		<button type="button" class="btn btn-danger me-2">리뷰작성</button>
	</div>
  </div>
  
</div>

<script>
	function delete_button() {
		var Del = confirm("정말 삭제하시겠습니까?")
		if(Del == true) {
			location.href="/shop/delete?shop_num=${shop.shop_num}"
		} else {
			alert("취소되었습니다.")
		}
	}
</script>
