<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">

			<!-- 방명록 aside -->
			<c:import url="/WEB-INF/views/includes/asideGuestbook.jsp"></c:import>
			<!-- //방명록 aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>ajax 방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">ajax 방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-pass" type="password" name="pass"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
							
							<button id="btnshow" type="button">보이기</button>
							<button id="btnhide" type="button">숨기기</button>
							

						</table>
						<!-- //guestWrite -->

					</form>
					<!--
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>1234555</td>
							<td>이정재</td>
							<td>2020-03-03 12:12:12</td>
							<td><a href="">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
						</tr>
					</table>
					-->

					<!-- //guestRead -->

					<div id="listArea">
						<!-- jquery로 리스트 그리는 영역 -->
					</div>




					<!-- 
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>1234555</td>
							<td>이정재</td>
							<td>2020-03-03 12:12:12</td>
							<td><a href="">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
						</tr>
					</table>
					-->
					<!-- //guestRead -->




				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->



	<!-- 삭제 모달창 -->
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록 삭제</h4>
				</div>
				<div class="modal-body">
					<label for="modalPassword">비밀번호</label> <input id="modalPassword" type="password"
						name="password"> <input type="text" name="no">
				</div>
				<div class="modal-footer">
					<button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>


<script type="text/javascript">
	//화면 로딩되기 직전
	$(document).ready(function() {
		console.log("화면 로딩 직전");

		//ajax 요청하기
		fetchList();

	});

	//로딩이 끝난 후
	//등록 버튼 클릭할때
	$("#btnSubmit").on("click", function() {

		event.preventDefault(); //원래 html에 있던거 작동 안함(폼 전송 기능 꺼버린거)
		console.log("등록버튼 클릭");
		/* 	
		 //name값 읽어오기
		 var userName = $("#input-uname").val(); //값 가져오는 메소드 사용(val())
		 console.log(userName);
		
		 //password값 읽어오기
		 var password = $("#input-pass").val();
		 console.log(password);
		
		 //content값 가져오기
		 var content = $("[name='content']").val();
		 console.log(content); */

		var guestbookVo = {
			name : $("#input-uname").val(),
			password : $("#input-pass").val(),
			content : $("[name='content']").val()
		};

		//데이터 ajax방식으로 서버에 전송 (리스트 추가된 화면)
		$.ajax({

			//url : "${pageContext.request.contextPath }/api/guestbook/write?name="+ userName+"&password="+password+"&content="+content,		
			url : "${pageContext.request.contextPath }/api/guestbook/write",

			type : "get",
			//contentType : "application/json",
			//data : {name: userName, password: password, content: content},
			data : guestbookVo,

			dataType : "json",
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");

				//입력폼 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	//삭제 버튼을 클릭할때
	$("#listArea").on("click", ".btnDel", function() {
		console.log("삭제 버튼 클릭");

		//hidden no 입력하기
		var tag = $(this);
		var no = tag.data("no");
		$("[name=no]").val(no);

		//비밀번호 창 초기화
		$("#modalPassword").val("");

		//모달창 보이기
		$("#delModal").modal();
	});

	//삭제모달창의 삭제버튼 클릭할때
	$("#modalBtnDel").on("click", function() {
		console.log("모달창 삭제버튼 클릭");

		var guestbookVo = {
			no : $("[name=no]").val(),
			password : $("[name=password]").val()

		};

		var no = $("[name='no']").val();

		console.log(guestbookVo);

		//서버에 삭제요청(no, password 전달)
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/remove",
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,

			dataType : "json",
			success : function(count) {
				/*성공시 처리해야될 코드 작성*/

				if (count === 1) {
					//모달창 닫기
					$("#delModal").modal("hide");

					//삭제된 테이블을 화면에서 지운다
					$("#t-" + no).remove();
				} else {
					//모달창 닫기
					$("#delModal").modal("hide");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	//
	function fetchList() {

		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post", //어차피 주소창 치고 가는거 아니라서 사용자한테 안보임, get 해도 됨
			contentType : "application/json",
			//data : {name: "홍길동"},

			//dataType : "json",
			success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);

				//화면에 그리기
				for (var i = 0; i < guestList.length; i++) {
					render(guestList[i], "down");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	};

	//방명록 한개씩 렌더링
	function render(guestbookVo, type) {

		var str = "";
		str += '<table id="t-'+ guestbookVo.no +'" class="guestRead">';
		str += '   <colgroup>';
		str += '         <col style="width: 10%;">';
		str += '         <col style="width: 40%;">';
		str += '         <col style="width: 40%;">';
		str += '         <col style="width: 10%;">';
		str += '   </colgroup>';
		str += '   <tr>';
		str += '      <td>' + guestbookVo.no + '</td>';
		str += '      <td>' + guestbookVo.name + '</td>';
		str += '      <td>' + guestbookVo.regDate + '</td>';
		str += '      <td><button class="btnDel" data-no="'+guestbookVo.no+'">삭제</button></td>';
		str += '   </tr>';
		str += '   <tr>';
		str += '      <td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '   </tr>';
		str += '</table>';

		if (type === 'down') {
			$("#listArea").append(str);
		} else if (type === 'up') {
			$("#listArea").prepend(str);
		} else {
			console.log("방향을 지정해 주세요");
		}

	};
	
	
	
	
	
	/* 보이기 숨기기 예제 */
	$("#btnhide").on("click", function(){
		console.log("숨기기버튼 클릭");
		
		$("#btnSubmit").hide();
		
	});
	
	$("#btnshow").on("click", function(){
		console.log("보이기버튼 클릭");
		
		$("#btnSubmit").show();
		
	});
	
	
</script>


</html>