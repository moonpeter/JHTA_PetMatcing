<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery Lib -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  -->
<script type="text/javascript">
	$(document).ready(function() {
		//id속성이 boardCategory로 정의된 모든 li에 익명의 함수를 적용하겠다는 의미
		$("#boardCategory>li").each(function(){
			$(this).click(function(){
				//alert("선택됨")
				//alert($("#boardMain").outerWidth())
				//alert($("#boardMain").outerHeight())
				//현재 작업 중인 객체가 click되면 ajax를 요청할 수 있도록 처리
				category = $(this).text();
				//alert(category);
				$("#boardCategory>li").removeAttr("class")
				$(this).attr("class","active");
				$.ajax({
					type:"POST",
					url:"list_ajax",
					data:{
						"category":category
					},
					success:function(data){
						//alert(data[0].title+","+data[0].write_date);
						mydata="";//조회한 json객체안의 모든 데이터를 꺼내서 추가할 변수
						for(i=0;i<data.length;i++){
							mydata = mydata +
							"<tr><td class='boardContent' style=''>"
							+data[i].title+
							"</td><td class='boardDate' style=''>"
							+data[i].write_date+"</td></tr>"
						}
						//alert(mydata);
						$("#mydatalist").empty();
						$("#mydatalist").append(mydata);
					},
					error:function(a,b,c){//ajax실패시 원인(에러메시지)
						alert(c);
					}
					
				});
			})
		})
	})
</script>
<style>
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.panel-footer {
    padding: 10px 15px;
    background-color: #dc3545;
    /* border-top: 1px solid #ddd; */
    dd: ;
    /* border-bottom-right-radius: 3px; */
    /* border-bottom-left-radius: 3px; */
    color: white;
}

element.style {
    /* border-color: #edeef1; */
    height: 300px;
    width: 450px;
}

.panel-primary {
    border-color: #dc3545;
}

.panel {
    margin-bottom: 20px;
    background-color: #fff;
    border: 1px solid transparent;
    /* border-radius: 4px; */
    /* -webkit-box-shadow: 0 1px 1px rgb(0 0 0 / 5%); */
    /* box-shadow: 0 1px 1px rgb(0 0 0 / 5%); */
}

a {
    color: #0d6efd;
    /* text-decoration: underline; */
}

.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {
    color: #555;
    cursor: default;
    background-color: #fff;
    border: 1px solid #dc3545;
    border-bottom-color: transparent;
}

.nav-tabs {
    border-bottom: 1px solid #dc3545;
}

</style>
<title>우리 주인이 달라졌어요</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/image/dog.ico">
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div class="container">
		<%-- <img src="${pageContext.request.contextPath}/resources/image/main.jpg" alt="dog" width="600" height="400"> --%>
		<div class="row">
			<%-- <div class="col-sm-7">
				<div id="myCarousel" class="carousel slide" data-ride="carousel"
					style="width: 600px; height: 300px">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
						<li data-target="#myCarousel" data-slide-to="3"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="${pageContext.request.contextPath}/resources/upload/doBoard${b.DOG_PHOTO}"" alt="Image"
								style="width: 600px; height: 300px">

						</div>

						<div class="item">
							<img src="/erp/images/ktds2.jpg" alt="Image"
								style="width: 800px; height: 300px">

						</div>
						<div class="item">
							<img src="/erp/images/ktds3.jpg" alt="Image"
								style="width: 600px; height: 300px">

						</div>

						<div class="item">
							<img src="/erp/images/ktds4.jpg" alt="Image"
								style="width: 800px; height: 300px">

						</div>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div> --%>
			<div class="col-sm-5">
				<div class="panel panel-primary"
					style="border-color: #edeef1; height: 300px; width: 500px">
					<div class="panel-footer">게시판</div>
					<div class="panel-body">
						<ul class="nav nav-tabs" id="boardCategory">
							<li class="active"><a href="#">산책 신청</a></li>
							<li><a href="#">산책로 추천</a></li>
							<li><a href="#">자유게시판</a></li>
						</ul>
						<div id="boardMain" style="padding-top: 20px; padding-left: 10px;width: 450px;height: 150px">
							<table id="mydatalist">
							<c:forEach var="b" items="${boardlist}">
								<tr>
									<td class="boardContent" style="">${b.BOARD_SUBJECT }</td>
									<td class="boardDate" style="">${b.BOARD_DATE }</td>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>