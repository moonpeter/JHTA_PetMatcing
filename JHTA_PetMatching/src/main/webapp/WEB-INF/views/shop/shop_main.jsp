<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
	.container {
/* 		background-color : pink; */
		padding : 10px;
	}
	
	#category-bar {
		border-bottom: solid 1px #dc3545;
	}
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
  	<button class="btn btn-danger" type="submit" onclick="location.href='/shop/writeForm'">글작성</button>
  	<button id="categoryName" class="btn btn-danger"> 카테고리 : ${category} </button>
</div>

<div class="container">
	<form class="d-flex">
	    <input class="form-control me-2" type="search" placeholder="검색어를 입력하세요." aria-label="Search">
	    <button class="btn btn-danger" type="submit">Search</button>
    </form>
    
    <div class="row" id="row">
        <c:forEach var="shop" items="${shopList}">
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="detail?shop_num=${shop.shop_num}">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/resources/upload/shop${shop.shop_thumnail }" alt="" height="200px">
                </a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="detail?shop_num=${shop.shop_num}">${shop.shop_title}</a>
                    </h4>
                    <h5 style="text-align: right"><strong><fmt:formatNumber value="${shop.shop_price}" pattern="###,###,###원"/></strong></h5>
                    <p class="card-text" style="overflow: hidden; line-height: 1.2; height: 3.6em; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;">&nbsp&nbsp${shop.shop_text_content}</p>
                </div>
                <div class="card-footer">
					<small class="text-danger">평점 : &#9733; &#9733; &#9733; &#9733; &#9733;</small>
                </div>
            </div>
        </div>
        </c:forEach>
	</div>
	
	<div class="row" id="testAjax"></div>
	
	<c:if test="${page != endPage}">
		<div class="d-grid gap-2 col-6 mx-auto">
		    <button class="pageInc btn btn-danger btn-lg" style="width: 300px">더보기</button>
		</div>
	</c:if>
	
	<script>
	    $(document).ready(function () {
	            var page = 1;
	            
	            if(${listCount} == 0) {
                	$(".pageInc").hide();
                	alert("해당 게시물이 존재하지 않습니다.");
                }
	            
	            $('.pageInc').click(function () {
	                page += 1;
	                $.get("/shop/list_ajax", {"page": page},
	                    function (data) {
	                        $("#testAjax").append(data);
	                        if(page == ${endPage}) {
	                        	$(".pageInc").hide();
	                        }
	                    })
	            })
	        }
	    )
	</script>

</div>
