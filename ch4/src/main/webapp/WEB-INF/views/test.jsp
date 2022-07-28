<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>CommentTest</title>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
	<h2>CommentTest</h2>
	<label>comment: </label>
	<input type="text" name="comment">
	<br>
	<button id="sendBtn" type="button">SEND</button>
	<button id="modBtn" type="button">수정완료</button>
	<div id="commentList"></div>
	<div id="replyForm" style="display: none">
		<input type="text" name="replyComment">
		<button id="wrtRepBtn">등록</button>
	</div>
	<script>
		let bno = 2924;

		let showList = function(bno) {
			$.ajax({
				type : 'GET', // 서버에 요청
				url : '/ch4/comments?bno=' + bno, // 요청 URI
				success : function(result) {
					$("#commentList").html(toHtml(result)); // result는 서버가 전송한 데이터
				},
				error : function() {
					alert("error")
				} // 에러가 발생했을 때, 호출될 함수
			});
		}

		let toHtml = function(comments) {
			let tmp = "<ul>";
			comments
					.forEach(function(comment) {
						tmp += '<li data-cno=' + comment.cno
						tmp += ' data-pcno=' + comment.pcno
						tmp += ' data-bno=' + comment.bno + '>'
						if(comment.cno != comment.pcno )
							tmp += '└>'
						tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
						tmp += ' comment=<span class="comment">' + comment.comment + '</span>'
						tmp += ' up_date=' + comment.up_date
						tmp += ' <button class="delBtn">삭제</button>'
						tmp += ' <button class="modBtn">수정</button>'
						tmp += ' <button class="replyBtn">답글</button>'
						tmp += '</li>'
					});
			return tmp + "</ul>";
		}

		$(document).ready(function() {
			showList(bno);

			$("#modBtn").click(function() {
				let comment = $("input[name=comment]").val()
				let cno = $(this).attr("data-cno");

				if (comment.trim() == '') {
					alert('내용을 입력해주세요.');
					$("input[name=comment]").focus();
					return;
				}
				$.ajax({
					type : 'PATCH', // 서버에 정보 전달하는 요청 메서드
					url : '/ch4/comments/' + cno, // 요청 URI -> @PathVariable cno로 전달
					headers : {
						"content-type" : "application/json"
					}, // 요청 헤더
					data : JSON.stringify({
						cno : cno,
						comment : comment
					}), // 서버로 전송할 데이터. stringify()로 직렬화 필요.
					// @RequestBody가 있는 파라미터에 전달됨
					success : function(result) {
						alert(result);
						showList(bno);
					},
					error : function() {
						alert("error")
					}
				});
				$("input[name=comment]").val('')
			})

			$("#sendBtn").click(function() {
				let comment = $("input[name=comment]").val()

				if (comment.trim() == '') {
					alert('내용을 입력해주세요.');
					$("input[name=comment]").focus();
					return;
				}
				$.ajax({
					type : 'POST', // 서버에 정보 전달하는 요청 메서드
					url : '/ch4/comments?bno=' + bno, // 요청 URI
					headers : {
						"content-type" : "application/json"
					}, // 요청 헤더
					data : JSON.stringify({
						bno : bno,
						comment : comment
					}), // 서버로 전송할 데이터. stringify()로 직렬화 필요.
					// @RequestBody가 있는 파라미터에 전달됨
					success : function(result) {
						alert(result);
						showList(bno);
					},
					error : function() {
						alert("error")
					}
				});
				$("input[name=comment]").val('')
			})

			//		$(".delBtn").click(function() { 
			//    서버에서 응답이 오기 전에 실행되어 미적용, 클릭 이벤트 전에 이미 실행됨
			//    아래와 같이 이벤트 실행 전후에 상관 없이 고정된 요소에 이벤트 설정 필
			$("#commentList").on("click", ".delBtn", function() {
				let cno = $(this).parent().attr("data-cno");
				let bno = $(this).parent().attr("data-bno");
				$.ajax({
					type : 'DELETE', // 요청 메서드
					url : '/ch4/comments/' + cno + '?bno=' + bno, // 요청 URI
					success : function(result) {
						alert(result)
						showList(bno);
					},
					error : function() {
						alert("error")
					} // 에러가 발생했을 때, 호출될 함수
				});
			})

			$("#commentList").on("click", ".modBtn", function() {
				let cno = $(this).parent().attr("data-cno");
				//1. comment의 내용을 input에 출력
				// comment 내용 가져오기
				// let comment = $(this).parent().children("span.comment").html();
				let comment = $("span.comment", $(this).parent()).text();
				// input에 가져온 내용 출력하기
				$("input[name=comment]").val(comment);
				//2. cno를 수정완료 버튼 속성에 전달
				$("#modBtn").attr("data-cno", cno);
			});

			$("#commentList").on("click", ".replyBtn", function() {
				//답글 입력폼 이동 - 답글버튼 클릭한 <li> 하위 요소로 이동
				$("#replyForm").appendTo($(this).parent());
				//답급 입력폼 출력
				$("#replyForm").css("display", "block");
			});

			$("#wrtRepBtn").click(function() {
				let comment = $("input[name=replyComment]").val()
				let pcno = $("#replyForm").parent().attr("data-pcno"); // 부모 댓글이 몇 번인지 저장

				if (comment.trim() == '') {
					alert('내용을 입력해주세요.');
					$("input[name=replyComment]").focus();
					return;
				}
				$.ajax({
					type : 'POST', // 서버에 정보 전달하는 요청 메서드
					url : '/ch4/comments?bno=' + bno, // 요청 URI
					headers : {
						"content-type" : "application/json"
					}, // 요청 헤더
					data : JSON.stringify({
						pcno:pcno,
						bno : bno,
						comment : comment
					}), // 서버로 전송할 데이터. stringify()로 직렬화 필요.
					// @RequestBody가 있는 파라미터에 전달됨
					success : function(result) {
						alert(result);
						showList(bno);
					},
					error : function() {
						alert("error")
					}
				});
				
				//ajax 요청 후 대댓글 내용 삭제 및 원위치
				$("#replyForm").css("display", "none");
				$("input[name=replyComment]").val('');
				$("#replyForm").appendTo("body");
			})

		});
	</script>
</body>
</html>