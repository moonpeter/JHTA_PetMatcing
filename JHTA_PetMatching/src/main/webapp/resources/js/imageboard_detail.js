$(function(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(header, token);
	});
	
	
	$("#comment table").hide(); // (1)
	var page=1; //더 보기에서 보여줄 페이지를 기억할 변수
	count = parseInt($("#count").text());
	if(count !=0){ //댓글 수가 0이 아니면
		getList(1); //1은 page=1을 의미. 첫 페이지의 댓글을 구해 옵니다. (한 페이지에 3개씩 가져옵니다.) CommentServiceImpl.java에서 int endrow=page*3; 에 의해 한 페이지에 3개씩 가져옴.
	}else{ //댓글이 없는 경우
		$("#message").text("등록된 댓글이 없습니다.")
	}
	
	//글자수 50개 제한하는 이벤트
	$("#content").keyup(function(){
		length = $(this).val().length;
		if(length>50){
			length =50;
			content = content.substring(0, length);
		}
		$(".float-left").text(length+"/50")
	});
	
	function getList(currentPage){
		$.ajax({
			    type: "post",
			    url: "../imageboardcomment/list",
			    data: {
			    	   "board_num" : $("#board_num").val(),
			    	   "page" : currentPage
			    },
			    dataType: "json",
			    success : function(rdata){ //rdata는  CommentController의 public Map<String, Object> commentListAjax()에서
			    	                       //Map에 담은 객체이다.
			    	$("#count").text(rdata.listcount);
			    	if(rdata.listcount>0){ //총 갯수가 0보다 큰 경우
			    	    $('#comment table').show(); //문서가 로딩될 때 hide() 했던 부분을 보이게 합니다.  (1): 2번라인 $("#comment table").hide();
			    	    $('#comment tbody').empty();
			    		$(rdata.list).each(function(){
			    			output ="";
			    			img ="";
			    			if($('#loginid').text() == this.id){
			    				img = "<img src='../resources/image/pencil2.png' width='15px' class='update'>"
			    					+ "<img src='../resources/image/delete.png' width='15px' class='remove'>"
			    					+ "<input type='hidden' value='"+this.num+"'>";
			    			}
			    			output += "<tr><td>" + this.id + "<label><input type='radio' style='visibility:hidden' value='"+ this.id +"' id='sendMessage' name='sendMessage' onClick='messagePopUp2()'><img src='../resources/image/send_message.png' alt='메세지 보내기' width='30px'></label></td>";
			    			output += "<td></td>"
			    			output += "<td>" + this.reg_date + img + "</td></tr>";
			    			$("#comment tbody").append(output);
			    			//append한 마지막 tr의 2번째 자식 td를 찾아 text()메소드로 content를 넣습니다.
			    			$("#comment tbody tr:last").find("td:nth-child(2)").text(this.content);
			    	    })//each end;
			    		
			    	    if(rdata.listcount > rdata.list.length){
			    	    	$("#message").text("더보기")
			    	    }else{
			    	    	$("#message").text("")
			    	    }
			    	    
			    	}else{
			    		$("#message").text("등록된 댓글이 없습니다.")
			    		$("#comment tbody").empty();
			    		$("#comment table").hide(); // (1): 2번라인 $("#comment table").hide();
			    	}
			    }, //success end
			    error : function(){
					console.log('에러')
				}
			
		})// ajax end
	}// function getList end
	
	
	//더보기를 클릭하면 page숫자가 증가하면서 추가적인 댓글 내용들이 보인다.
	$("#message").click(function(){
		getList(++page);
	})
	
	
	//등록 또는 수정완료 버튼을 클릭한 경우
	//버튼의 라벨이 '등록'인 경우는 댓글을 추가하는 경우
	//버튼의 라벨이 '수정완료'인 경우는 댓글을 수정하는 경우
	$("#write").click(function(){
		buttonText = $("#write").text(); // 버튼의 라벨로 add를 할지 update를 할지 결정.
		content = $("#content").val();
		$(".float-left").text("총 50자까지 가능합니다.");
		
		if(buttonText == "등록"){
			url = "../imageboardcomment/add";
			data = {
					"content" : content,
					"id" : $('#loginid').text(),
					"board_num" : $("#board_num").val()
			};
		}else{//댓글을 수정하는 경우
			url = "../imageboardcomment/update";
			data = {
					"num" : num, //이 num값은 아래에 있는 $("#comment").on('click', '.update', function(){ 에서 정함.
					"content" : content
			};
			$("#write").text("등록"); //다시 등록으로 변경
		}
		//위의 $("#write").click(function(){})을 작성하고난 뒤
		//아래에 ajax를 작성
		
		// 1. 전송방식은 "post"                 
		// 2. 요청주소는 변수 url이 갖고 있는 값
		// 3. 보낼 데이터는 변수 data가 갖고 있는 값      
		// 4. ajax 요청이 성공하면 #content 영역을 초기화하고 받은 값이 1이면 getList(page)호출
		$.ajax({ //$.ajax() 안에 오브젝트가 오기 때문에  $.ajax({}) 이렇게 쓰는 것
			    type: "post",
			    url: url,
			    data: data,  // dataType은 return data의 Type(에이잭스 성공 후 돌려받은 자료의 형을 정의)
			                 // 즉, CommentController.java에서 public void CommentAdd()의 반환형이 void 이므로
			                 // dataType이 작성되지 않은 것이다. dataType이 작성되지 않았기 때문에
			                 // CommentController.java의  CommentAdd() 위에는 @ResponseBody 애노테이션이 없다.
			                 // ajax에서 dataType: "Json"으로 쓰인 경우에 컨트롤러에서 @ResponseBody 애노테이션을 작성해서 json 형식으로 리턴하는 것이기 때문이다.
			                 // 컨트롤러에서 @ResponseBody를 작성하면 json형식의 String을 보내주고 (http://localhost:8088/myhome6/comment/list?board_num=2&page=1 에 작성된 json형식)
			                 // 이 String을 ajax에서 받을 때, dataType: "Json"이라고 되어 있어야 String을 Object로 받아서
			                 // rdata.id 이런식으로 객체로서 접근이 가능해진다.
			    success: function(result){
					$("#content").val('');
			    	if(result==1){ // CommentController.java의 CommentAdd(), CommentUpdate()에 있는 response.getWriter().print(ok); 값이다.
			    		getList(page); //등록, 수정완료 후 해당 페이지를 보여줍니다.
					}else if(result=="notLogin"){
						alert("로그인 후에 댓글 작성이 가능합니다.")
						location.href="../member/login";
					}//if
				}, //success end
			    error : function(){
					console.log('에러')
				}
		})// ajax end
	})// $("#write").click end  
	
	
	
	// pencil2.png를 클릭하는 경우(수정)
	// 1. 선택한 내용을 변수 before에 저장합니다.
	// 2. $("#content")에 before의 내용을 보여줍니다.
	// 3. 수정할 글 번호를 num(전역변수)이라는 변수에 저장합니다.
	// 4. $("write")의 "등록" 라벨을 "수정완료"로 변경합니다.
	// 5. 선택한 배경색은 'lightgray'로 나타납니다. 선택하지 않은 곳은 'white'입니다.
	$("#comment").on('click', '.update', function(){
		before = $(this).parent().prev().text(); //선택한 내용을 가져옵니다.
		$("#content").focus().val(before); // textarea에 수정전 내용을 보여줍니다.
		num = $(this).next().next().val(); // 수정할 댓글번호를 저장합니다.
		$("#write").text("수정완료"); // 등록버튼의 라벨을 '수정완료'로 변경합니다.
		
		//$("#comment .update").parent().parent() // #comment영역의 update클래스를 가진 엘리먼트 부모의 부모 => <tr>
		//not(this) : 테이블의 <tr>중에서 현재 선택한 <tr>을 제외한 <tr>의 배경색을 흰색으로 설정합니다.
		//즉, 선택한 수정(.update)만 'lightgray'의 배경색이 나타나도록 하고 선택하지 않은 수정의 <tr>엘리먼트는 흰색으로 설정합니다.
		$("#comment .update").parent().parent().not(this).css('background', 'white');
		
		$(this).parent().parent().css('background', 'lightgray'); //수정할 행의 배경색을 변경합니다.
		
	})
	
	// remove.png를 클릭하는 경우(삭제)
	// 1. "정말 삭제하시겠습니까?" 확인을 클릭하면 ajax를 진행하고 취소를 클릭하면 클릭 이벤트를 종료합니다.
	// 2. 확인을 클릭한 경우
	//    (1) 댓글 번호를 구해옵니다.
	//    (2) 전송 방식은 "post"
	//    (3) 요청주소는 "../comment/delete"
	//    (4) 전송할 데이터는 "num="+(1번에서 구한 댓글 번호)
	//    (5) 요청이 성공하면 받은 데이터가 1인 경우 getList(page);를 호출합니다.
	$("#comment").on('click', '.remove', function(){
		if(!confirm("정말 삭제하시겠습니까?")){
			return;
		}
		var num = $(this).next().val(); //댓글 번호
		$.ajax({ //$.ajax() 안에 오브젝트가 오기 때문에  $.ajax({}) 이렇게 쓰는 것
			    type: "post",
			    url: "../imageboardcomment/delete",
			    data: {
			    	"num" : num
			    },  
			    success: function(result){
			    	if(result==1){
			    		getList(page); //삭제 후 해당 페이지를 보여줍니다.
					}//if
				}, //success end
			    error : function(){
					console.log('에러')
				}
		})// ajax end
		
	})
});



