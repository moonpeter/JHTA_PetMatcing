<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
<script>
$(function(){
	var count1=0;
	var count2=0;
	var count3=0;
	var count4=0;
	
	//취소 버튼 클릭시 detail 페이지로 이동
	$("#modify_cancel").click(function(){
		$("#form_modify_cancel").submit();
	});
	
	//수정 버튼 클릭시
	$("#modify_request").click(function(){
		$("#form_modify").submit();
	});
	
	
	
	// submit 버튼 클릭할 때 이벤트 부분
	$("#form_modify").submit(function(){
		if($.trim($("#board_subject").val()) ==""){
			alert("제목을 입력하세요");
			$("#board_subject").focus();
			return false;
		}
		
		if($.trim($("#board_content").val()) ==""){
			alert("내용을 입력하세요");
			$("#board_content").focus();
			return false;
		}
		
		if($.trim($("#board_pass").val()) ==""){
			alert("비밀번호를 입력하세요");
			$("#board_pass").focus();
			return false;
		}
		
		//파일첨부를 변경하지 않으면 오리지널 파일명을
		//파라미터 'check1'라는 이름으로 form에 추가하여 전송합니다.
		if (count1 ==0 ){  //맨 위에 var count1=0; 있고, 업로드 파일이 변경되면 function preview1()에서 count1++이 된다. 
			value1 = "${boarddata.BOARD_ORIGINAL1}"; //오리지널 이름
			html1 = "<input type='text' value='"+ value1 + "' name='check1'>";       
			$(this).append(html1);	//this는 submit 이벤트 발생 객체인 form을 의미
		}
		
		if (count2 ==0 ){  
			value2 = "${boarddata.BOARD_ORIGINAL2}"; 
			html2 = "<input type='text' value='"+ value2 + "' name='check2'>";       
			$(this).append(html2);	
		}
		
		if (count3 ==0 ){  
			value3 = "${boarddata.BOARD_ORIGINAL3}"; 
			html3 = "<input type='text' value='"+ value3 + "' name='check3'>";       
			$(this).append(html3);	
		}
		
		if (count4 ==0 ){  
			value4 = "${boarddata.BOARD_ORIGINAL4}"; 
			html4 = "<input type='text' value='"+ value4 + "' name='check4'>";       
			$(this).append(html4);	
		}
	}); //submit end;
	
	
	
	//이미지 미리보기.
	$('#upfile1').on('change', preview1);
	$('#upfile2').on('change', preview2);
	$('#upfile3').on('change', preview3);
	$('#upfile4').on('change', preview4);
	
	
	//if문에서 break와 return의 차이점
	//break는 해당 if문만 종료시키지만, return은 if문을 포함한 메소드 자체를 종료시킨다.
	function preview1(e) {
		count1++; //업로드 파일이 변경될 때마다 1씩 증가
		console.log("첫번째 이미지 파일의 변경 횟수= "+count1);
		
		console.log($(this).val()) // console.log($("#upfile1").val()) 을 의미함.
		
		if($('#upfile1').val()!=''){
			$("#upfile1_remove").css({'display':'inline', 'width':'30px'}); //display:none 의 반대는 inline element일 경우 display:inline, block element일 경우 display:block 이다.    
		}                                                                   

		
		// 선택한 그림의 File 객체를 취득
		//File객체 리스트에서 첫번째 File객체를 가져옵니다.
		var file = e.target.files[0];	
		
		
		//file.type : 파일의 형식 (MIME타입 - 예) text/html)
		if (!file.type.match('image.*')) { //파일 타입이 image인지 확인합니다.
			alert("확장자는 이미지 확장자만 가능합니다.");
			$(this).val('')	//$("#upfile1").val('')을 의미. 이걸 안해주면 $("#upfile1").val() 값이 남아 있어서 업로드가 되어버림.
			console.log($(this).val()) //console.log($("#upfile1").val()) 을 의미함.
			$("#image1").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css({'width':'50px', 'height':'50px'});
			return;                                                                              //.css('width', '50px').css('height', '50px'); 이것과 같음     
		}
			
		//파일을 읽기 위한 객체 생성
		var reader = new FileReader();				
		
		//DataURL 형식으로 파일을 읽어옵니다. 
		//읽어온 결과는 reader객체의 result 속성에 저장됩니다.			
		reader.readAsDataURL(file);
		
		//읽기에 성공했을 때 실행되는 이벤트 핸들러	
		reader.onload = function(e) {
			//result:읽기 결과가 저장됩니다.
			//reader.result 또는 e.target.result
		    $("#image1").attr('src', e.target.result);
		    $("#image1").css('width', '220px').css('height', '220px');
		}//reader.onload end				
	}//function end
	
	function preview2(e) {
		count2++; //업로드 파일이 변경될 때마다 1씩 증가
		console.log("두번째 이미지 파일의 변경 횟수= "+count2);
		
		if($('#upfile2').val()!=''){
			$("#upfile2_remove").css({'display':'inline', 'width':'30px'}); 
		} 
		
		var file = e.target.files[0];
		
		if (!file.type.match('image.*')) { //파일 타입이 image인지 확인합니다.
			alert("확장자는 이미지 확장자만 가능합니다.");
			$(this).val('')	
			$("#image2").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css('width', '50px').css('height', '50px');
			return;
		}
		//파일을 읽기 위한 객체 생성
		var reader = new FileReader();				
				
		reader.readAsDataURL(file);
		
		//읽기에 성공했을 때 실행되는 이벤트 핸들러	
		reader.onload = function(e) {

		$("#image2").attr('src', e.target.result);
		$("#image2").css('width', '220px').css('height', '220px');
		}//reader.onload end	
	}//function end
	
	function preview3(e) {
		count3++; //업로드 파일이 변경될 때마다 1씩 증가
		console.log("세번째 이미지 파일의 변경 횟수= "+count3);
		
		if($('#upfile3').val()!=''){
			$("#upfile3_remove").css({'display':'inline', 'width':'30px'}); 
		}
		
		var file = e.target.files[0];
		
		if (!file.type.match('image.*')) { //파일 타입이 image인지 확인합니다.
			alert("확장자는 이미지 확장자만 가능합니다.");
			$(this).val('')	
			$("#image3").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css('width', '50px').css('height', '50px');
			return;
		}
		//파일을 읽기 위한 객체 생성
		var reader = new FileReader();				
				
		reader.readAsDataURL(file);
		
		//읽기에 성공했을 때 실행되는 이벤트 핸들러	
		reader.onload = function(e) {

		$("#image3").attr('src', e.target.result);
		$("#image3").css('width', '220px').css('height', '220px');
		}//reader.onload end	
	}//function end
	
	function preview4(e) {
		count4++; //업로드 파일이 변경될 때마다 1씩 증가
		console.log("네번째 이미지 파일의 변경 횟수= "+count4);
		
		if($('#upfile4').val()!=''){
			$("#upfile4_remove").css({'display':'inline', 'width':'30px'}); 
		}
		
		var file = e.target.files[0];
		
		if (!file.type.match('image.*')) { //파일 타입이 image인지 확인합니다.
			alert("확장자는 이미지 확장자만 가능합니다.");
			$(this).val('')	
			$("#image4").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css('width', '50px').css('height', '50px');
			return;
		}
		//파일을 읽기 위한 객체 생성
		var reader = new FileReader();				
				
		reader.readAsDataURL(file);
		
		//읽기에 성공했을 때 실행되는 이벤트 핸들러	
		reader.onload = function(e) {

		$("#image4").attr('src', e.target.result);
		$("#image4").css('width', '220px').css('height', '220px');
		}//reader.onload end	
	}//function end
	
	
	
	// X버튼 클릭시 이미지 선택 취소
	$("#upfile1_remove").click(function(){
		count1++;
		
		console.log("upfile1 삭제 전= "+$("#upfile1").val())
		$("#upfile1").val('');
		console.log("upfile1 삭제 후= "+$("#upfile1").val())
		$("#upfile1_remove").css('display', 'none');
		$("#image1").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css('width', '50px').css('height', '50px');
	});
	$("#upfile2_remove").click(function(){
		count2++;
		
		console.log("upfile2 삭제 전= "+$("#upfile2").val())
		$("#upfile2").val('');
		console.log("upfile2 삭제 후= "+$("#upfile2").val())
		$("#upfile2_remove").css('display', 'none');
		$("#image2").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css({'width':'50px', 'height':'50px'});
	});
	$("#upfile3_remove").click(function(){
		count3++;
		
		console.log("upfile3 삭제 전= "+$("#upfile3").val())
		$("#upfile3").val('');
		console.log("upfile3 삭제 후= "+$("#upfile3").val())
		$("#upfile3_remove").css('display', 'none');
		$("#image3").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css('width', '50px').css('height', '50px');
	});
	$("#upfile4_remove").click(function(){
		count4++;
		
		console.log("upfile4 삭제 전= "+$("#upfile4").val())
		$("#upfile4").val('');
		console.log("upfile4 삭제 후= "+$("#upfile4").val())
		$("#upfile4_remove").css('display', 'none');
		$("#image4").attr('src', '${pageContext.request.contextPath}/resources/image/plus.png').css('width', '50px').css('height', '50px');
	});
	
	
	
	
	

	
});
</script>
<style>
h1{font-size:1.5rem; text-align:center; color:#1a92b9;}
.container{width:60%}
label{font-weight:bold}
.upfile{display:none}
.border{border:1px solid black; border-radius:10px; width:228px; height:228px;
        display: table-cell; text-align: center; vertical-align: middle;}
.attach{width:50px; height:50px;
        margin: auto; display: block;}
.remove{width:30px;}
.out_border{display:inline-block; margin-right:10px;}
</style>
</head>
<body>
<div class="container">
  <form id="form_modify" action="modifyProcess" method="post" enctype="multipart/form-data"
        name="boardform">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">    
    <!-- 컨트롤러에서 command 객체로 값을 받아내기 위해 작성 -->
    <input type="hidden" name="BOARD_NUM" value="${boarddata.BOARD_NUM}">    
    <input type="hidden" name="BOARD_FILE1" value="${boarddata.BOARD_FILE1}"> <!-- 컨트롤러에서 변경 전의 파일명을 받아내기 위해 작성 -->
    <input type="hidden" name="BOARD_FILE2" value="${boarddata.BOARD_FILE2}">
    <input type="hidden" name="BOARD_FILE3" value="${boarddata.BOARD_FILE3}">
    <input type="hidden" name="BOARD_FILE4" value="${boarddata.BOARD_FILE4}">
    
    <h1>산책로 추천 게시판-modify 페이지</h1>      
    
    <div class="form-group">
      <label for="board_name">글쓴이</label>
      <input name="BOARD_NAME" id="board_name" value="${boarddata.BOARD_NAME}" readOnly
             type="text" class="form-control" placeholder="Enter board_name">   <%-- ${id}는 세션에 저장된 로그인 아이디 --%>      
    </div>
    
    <div class="form-group">
      <label for="board_pass">비밀번호</label>
      <input name="BOARD_PASS" id="board_pass" type="password" maxlength="30"
             class="form-control" placeholder="Enter board_pass" required>         
    </div>
    
    <div class="form-group">
      <label for="board_subject">제목</label>
      <input name="BOARD_SUBJECT" id="board_subject" type="text" maxlength="100"
             class="form-control" value="${boarddata.BOARD_SUBJECT}" required>         
    </div><br><br>
    
    <div class="form-group">
      <label for="board_file">이미지 파일 첨부</label><br><br>
      
      <div class="out_border">
      <label for="upfile1">
        <span class="border">
          <c:if test="${!empty boarddata.BOARD_FILE1}">
            <img id="image1" class="attach" style="width:220px; height:220px" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE1}" alt="파일첨부">     
          </c:if>
          <c:if test="${empty boarddata.BOARD_FILE1}">
            <img id="image1" class="attach" src="${pageContext.request.contextPath}/resources/image/plus.png" alt="파일첨부">     
          </c:if>
        </span>
      </label>
      <input type="file" class="upfile" id="upfile1" name="uploadfile1" accept="image/gif, image/jpeg, image/png">
      <c:if test="${!empty boarddata.BOARD_FILE1}">
        <img id="upfile1_remove" class="remove" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      <c:if test="${empty boarddata.BOARD_FILE1}">
        <img id="upfile1_remove" class="remove" style="display:none" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      </div>
      
      <div class="out_border">
      <label for="upfile2">
        <span class="border">
          <c:if test="${!empty boarddata.BOARD_FILE2}">
            <img id="image2" class="attach" style="width:220px; height:220px" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE2}" alt="파일첨부">     
          </c:if>
          <c:if test="${empty boarddata.BOARD_FILE2}">
            <img id="image2" class="attach" src="${pageContext.request.contextPath}/resources/image/plus.png" alt="파일첨부">     
          </c:if>
        </span>
      </label>
      <input type="file" class="upfile" id="upfile2" name="uploadfile2" accept="image/gif, image/jpeg, image/png">
      <c:if test="${!empty boarddata.BOARD_FILE2}">
        <img id="upfile2_remove" class="remove" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      <c:if test="${empty boarddata.BOARD_FILE2}">
        <img id="upfile2_remove" class="remove" style="display:none" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      </div>
      
      
      <div class="out_border">
      <label for="upfile3">
        <span class="border">
          <c:if test="${!empty boarddata.BOARD_FILE3}">
            <img id="image3" class="attach" style="width:220px; height:220px" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE3}" alt="파일첨부">     
          </c:if>
          <c:if test="${empty boarddata.BOARD_FILE3}">
            <img id="image3" class="attach" src="${pageContext.request.contextPath}/resources/image/plus.png" alt="파일첨부">     
          </c:if>
        </span>
      </label>
      <input type="file" class="upfile" id="upfile3" name="uploadfile3" accept="image/gif, image/jpeg, image/png">
      <c:if test="${!empty boarddata.BOARD_FILE3}">
        <img id="upfile3_remove" class="remove" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      <c:if test="${empty boarddata.BOARD_FILE3}">
        <img id="upfile3_remove" class="remove" style="display:none" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      </div>
      
      <div class="out_border">
      <label for="upfile4">
        <span class="border">
          <c:if test="${!empty boarddata.BOARD_FILE4}">
            <img id="image4" class="attach" style="width:220px; height:220px" src="${pageContext.request.contextPath}/resources/imageboard_upload${boarddata.BOARD_FILE4}" alt="파일첨부">     
          </c:if>
          <c:if test="${empty boarddata.BOARD_FILE4}">
            <img id="image4" class="attach" src="${pageContext.request.contextPath}/resources/image/plus.png" alt="파일첨부">     
          </c:if>
        </span>
      </label>
      <input type="file" class="upfile" id="upfile4" name="uploadfile4" accept="image/gif, image/jpeg, image/png">
      <c:if test="${!empty boarddata.BOARD_FILE4}">
        <img id="upfile4_remove" class="remove" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      <c:if test="${empty boarddata.BOARD_FILE4}">
        <img id="upfile4_remove" class="remove" style="display:none" src="${pageContext.request.contextPath}/resources/image/remove.png" alt="파일제거">
      </c:if>
      </div>
    </div>
    
    
    <div class="form-group">
      <label for="board_content">내용</label>
      <textarea name="BOARD_CONTENT" id="board_content" rows="10"
             class="form-control" required>${boarddata.BOARD_CONTENT}</textarea>       
    </div>
  </form> <!-- id="form_modify" end -->
  
  <button id="modify_request" type="button" class="btn btn-primary">수정</button>
  <form id="form_modify_cancel" action="detail" style="display:inline-block">
        <input type="hidden" name="num" value="${boarddata.BOARD_NUM}">
        <button id="modify_cancel" class="btn btn-danger">취소</button>
  </form>
</div>
</body>
</html>