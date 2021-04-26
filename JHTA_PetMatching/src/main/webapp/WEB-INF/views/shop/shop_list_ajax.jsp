<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <div class="row" id="row"> -->
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
<!-- </div> -->