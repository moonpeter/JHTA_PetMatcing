<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery Lib -->
<script src="http://code.jquery.com/jquery-latest.js"></script>

<title>우리 주인이 달라졌어요</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/image/dog.ico">
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	 <div class="container">
		<img src="${pageContext.request.contextPath}/resources/image/main.jpg" alt="dog" width="600" height="400">
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
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