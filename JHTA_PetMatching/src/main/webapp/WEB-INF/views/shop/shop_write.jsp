<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
      <li><a href="#" class="nav-link px-3 link-danger">사료</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">간식/건강</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">미용/목욕</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">외출용품</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">의류/악세사리</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">장난감</a></li>
      <li><a href="#" class="nav-link px-3 link-danger">생활용품</a></li>
    </ul>
  	<button class="btn btn-danger" type="submit" onclick="location.href='/shop/write'">글작성</button>
</div>

<div class="container">
	<form class="d-flex">
	    <input class="form-control me-2" type="search" placeholder="검색어를 입력하세요." aria-label="Search">
	    <button class="btn btn-danger" type="submit">Search</button>
    </form>
    
    <form action="write" method="post" enctype="multipart/form-date">
    	<div class="form-group">
         	<label for="board_title"> Category :&nbsp; </label> 
	         <select name="shop_category">
	            <option>----</option>
	            <option>사료</option>
	            <option>간식/건강</option>
	            <option>미용/목욕</option>
	            <option>외출용품</option>
	            <option>의류/악세사리</option>
	            <option>장난감</option>
	            <option>생활용품</option>
	         </select>
			<div>
		    제목 : <input type="text" id="shop_title" name="shop_title"><br>
		    가격 : <input type="text" id="shop_price" name="shop_price"><br>
		    원산지 : <input type="text" id="shop_country_of_origin" name="shop_country_of_origin"><br>
		    브랜드 : <input type="text" id="shop_brand" name="shop_brand"><br>
		    썸네일 : <input type="file" id="shop_thumnail" name="shop_upload_thumnail"><br>
		    본문 이미지 : <input type="file" id="shop_img_content" name="shop_upload_img_content"><br>
		    본문 텍스트 : <input type="text" id="shop_text_content" name="shop_text_content"><br>
		    </div>
			<br>	
			<button type=submit class="btn btn-primary">등록</button>
		</div>	
	</form>

</div>