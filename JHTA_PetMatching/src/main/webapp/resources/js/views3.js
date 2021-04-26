$(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options){
		xhr.setRequestHeader(header, token);
	});
	
	var table_name = $("#table_name").val();
	
	$("#comment table").hide(); // 1
	var page = 1; // 더 보기에서 보여줄 페이지를 기억할 변수
	count = parseInt($("#count").text());
	if (count != 0) { // 댓글 갯수가 0이 아니면
		getList(1); // 첫 페이지의 댓글을 구해 옵니다.(한 페이지에 3개씩 가져옵니다.)
	} else { // 댓글이 없는 경우
		$("#message").text("등록된 댓글이 없습니다.")
	}

	// 글자 수 50개 제한하는 이벤트
	$("#content").on('keyup', function() {
		length = $(this).val().length;
		if (length > 50) {
			length = 50;
			content = content.substring(0, length);
		}
		$(".float-left").text(length + "/50")
	})

	function getList(currentPage) {
		$.ajax({
					type : "post",
					url : "/comment/list",
					data : {
						"board_num" : $("#board_num").val(),
						"page" : currentPage,
						"table_name" : table_name
					},
					dataType : "json",
					success : function(rdata) {
						$("#count").text(rdata.listcount);
						if (rdata.listcount > 0) {
							$("#comment table").show(); // 문서가 로딩될 때 hide() 했던
							// 부분을 보이게 합니다. (1)
							$("#comment tbody").empty();
							$(rdata.list).each(function() {
												output = '';
												img = '';
												if ($("#loginid").text() == this.id) {
													img = "<img src='${pageContext.request.contextPath}/resources/image/pencil2.png' width='15px' class='update'>"
															+ "<img src='${pageContext.request.contextPath}/resources/image/delete.png' width='15px' class='remove'>"
															+ "<input type='hidden' value='"
															+ this.num + "'>";
												}
												output += "<tr><td>" + this.id + "<label><input type='radio' style='visibility:hidden' value='"+ this.id +"' id='sendMessage' name='sendMessage' onClick='messagePopUp2()'><img src='../resources/image/send_message.png' alt='메세지 보내기' width='30px'></label></td>";
												output += "<td>" + this.content + "</td>";
												output += "<td>"
														+ this.reg_date + img
														+ "</td></tr>";
												$("#comment tbody").append(
														output);
												// append한 마지막 tr의 2번째 자식 td를 찾아
												// text() 메서드로 content를 넣습니다.
												$("#comment tbody tr:last")
														.find("td:nth-child(2)")
														.text(this.content);

											}); // each end

							if (rdata.listcount > rdata.list.length) {
								$("#message").text("더보기")
							} else {
								$("#message").text("")
							}
						} else {
							$("#message").text("등록된 댓글이 없습니다.")
							$("#comment tbody").empty();
							$("#comment table").hide() // 1
						}
					}
				}); // ajax end
	} // function end

	// 더 보기를 클릭하면 page 내용이 추가로 보여집니다.
	$("#message").click(function() {
		getList(++page);
	}); // click end

	// 등록 또는 수정완료 버튼을 클릭한 경우
	// 버튼의 라벨이 '등록'인 경우는 댓글을 추가하는 경우
	// 버튼의 라벨이 '수정완료'인 경우는 댓글을 수정하는 경우
	$("#write").click(function() {
		buttonText = $("#write").text(); // 버튼의 라벨로 add할지 update할지 결정
		content = $("#content").val();
		$(".float-left").text('총 50자까지 가능합니다.');

		if (buttonText == "등록") { // 댓글을 추가하는 경우
			url = "/comment/add";
			data = {
				"content" : content,
				"id" : $("#id").val(),
				"board_num" : $("#board_num").val(),
				"table_name" : table_name
			};
		} else { // 댓글을 수정하는 경우
			url = "/comment/update";
			data = {
				"num" : num,
				"content" : content,
				"table_name" : table_name
			};
			$("#write").text("등록"); // 다시 등록으로 변경
		}

		$.ajax({
			type : "post",
			url : url,
			data : data,
			success : function(result) {
				$("#content").val('');
				if (result == 1) {
					// page = 1
					getList(page); // 등록, 수정완료 후 해당 페이지 보여줍니다.
				} // if
			} // success
		}) // ajax end
	}) // $("#write") end
	
	
	
	// pencil2.png를 클릭하는 경우(수정)
	$("#comment").on('click','.update', function(){
		before = $(this).parent().prev().text(); // 선택하나 내용을 가져옵니다.
		$("#content").focus().val(before); // textarea 에 수정 전 내용을 보여줍니다.
		num = $(this).next().next().val();	// 수정할 댓글 번호를 저장합니다.
		$("#write").text("수정완료");			// 등록버튼의 라벨을 '수정완료'로 변경합니다.
		
		// $("#comment.update").parent().parent() // #comment영역의 update 클래스를 가진 엘리먼트의 부모의 부모 => <tr>
		// not(this) : 테이블의 <tr> 중에서 현재 선택한 <tr>을 제외한 <tr>에 배경색을 흰색으로 설정합니다.
		// 즉, 선택한 수정(.update)만 'lightgray'의 배경색이 나타나도록 하고 선택하지 않은 수정의 <tr> 엘리먼트는 흰색으로 설정합니다.
		$("#comment .update").parent().parent().not(this).css('background','white');
		
		$(this).parent().parent().css('background','lightgray');	// 수정할 행의 배경색으르 변경합니다. 
	})
	
	
	
	// remove.png를 클릭하는 경우(삭제)
	$("#comment").on('click','.remove', function(){
		if(!confirm("정말 삭제하시겠습니까?")){
			return;
		}
		var num = $(this).next().val(); // 댓글번호
		$.ajax({
			type : "post",
			url : "/comment/delete",
			data : {
				"num" : num,
				"table_name" : table_name
			},
			success : function(result) {
				if (result == 1) {
					// page = 1
					getList(page); // 삭제 후 해당 페이지 보여줍니다.
				} // if
			} // success
		}) // ajax end
	})

})
