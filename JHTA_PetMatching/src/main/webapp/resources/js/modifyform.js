$(document).ready(function(){
	var check = 0;
	
	// submit 버튼 클릭할 때 이벤트 부분
	$("form").submit(function(){
		
		if($.trim($("#board_subject").val()) == ""){
			alert("제목을 입력하세요");
			$("#board_subject").focus();
			return false;
		}
		
		if($.trim($("textarea").val()) == ""){
			alert("내용을 입력하세요");
			$("#textarea").focus();
			return false;
		}
		
		if($.trim($("#board_pass").val()) == ""){
			alert("비밀번호를 입력하세요");
			$("#board_pass").focus();
			return false;
		}
		
		// 파일 첨부를 변경하지 않으면 $('filevalue').text()의 파일명을
		// 파라미터 'check'라는 이름으로 form에 추가하여 전송합니다.
		if(check == 0){
			value = $('#filevalue').text();
			html = "<input type = 'text' value = '" + value + "' name = 'check'>";
			$(this).append(html);
		}

	}); // submit end
	

	show();
	function show(){
		// 파일 이름이 있는 경우 remove 이미지를 보이게 하고
		// 파일 이름이 없는 경우 remove 이미지를 보이지 않게 합니다.
		if($('#filevalue').text() == ''){
			$(".remove").css('display', 'none');
		} else {
			$(".remove").css({'display':'inline-block',
							  'position' :'relative', 'top': '-5px'});
		}
	}
	
	$("#upfile").change(function(){
		check++;
		var inputfile = $("#upfile").val().split('\\');
		$('#filevalue').text(inputfile[inputfile.length -1]);
		show();
		console.log(check);
	});
	
	// remove 이미지를 클릭하면 파일명을 ''로 변경하고 remove 이미지를 보이지 않게 합니다.
	$(".remove").click(function(){
		$('#filevalue').text('');
		$(this).css('display','none');
	})
	
});	// ready() end