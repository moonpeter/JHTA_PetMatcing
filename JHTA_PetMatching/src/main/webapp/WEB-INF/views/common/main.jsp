<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery Lib -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<style>
	.img{position:relative; }
	.img-cover{position:absolute; height:100%; width:100%;}
	.img .content{position:absolute; top:35%; left:50%; transform:translate(-50%, -50%); color:white;}
	.jb-light{font-weight:lighter; font-size:30px}
</style>
<link rel="shortcut icon" href="#">
<title>우리 주인이 달라졌어요</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/image/dog.ico">
</head>
<body>
	<div class="img">
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="content">
			<p class="jb-light">우리 주인이 달라졌어요</p>
		</div>
		<img src="${pageContext.request.contextPath}/resources/image/dog_wallpaper3.jpeg" alt="dog" width="100%">
		<div class="img-cover"></div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
</body>
<script>
$(document).ready(function() {
	var result = "${result}";
	if(result == 'desti_updateSuccess'){
		alert("배송지 수정이 완료되었습니다.");
	}
	
	if(result == 'insertSuccess'){
		alert("배송지 정보가 입력되었습니다.");
	}
	
	if(result == 'updateSuccess'){
		alert("내정보 수정이 완료되었습니다.");
	}
})
</script>
</html>