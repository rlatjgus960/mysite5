<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //해더 네비 -->


		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/asideGallery.jsp"></c:import>
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">

						<c:if test="${not empty authUser.no}">
							<button id="btnImgUpload">이미지올리기</button>
						</c:if>

						<div class="clear"></div>


						<ul id="viewArea">

							<c:forEach items="${gList}" var="gList">
								<!-- 이미지반복영역 -->
								<li>
									<div class="view"  id="t-${gList.no}">
										<img data-no="${gList.no}" class="imgItem"
											src="${pageContext.request.contextPath }/upload/${gList.saveName}">
										<div class="imgWriter" data-userno="${gList.userNo}">
											작성자: <strong>${gList.userName }</strong>
										</div>
									</div>
								</li>
								<!-- 이미지반복영역 -->
							</c:forEach>


						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->


	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath }/gallery/imgUpload"
					enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content"
								value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button data-authno="${authUser.no }" type="button" class="btn btn-danger" id="btnDel">삭제</button>
					</div>


				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
	//이미지 등록버튼
	$("#btnImgUpload").on("click", function() {
		console.log("이미지 등록버튼 클릭");

		//모달창 보이기
		$("#addModal").modal();

		//모달창 컨텐트 초기화
		$("#addModalContent").val("");

		//파일첨부 초기화
		$("#file").val("");

	});

	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ajax!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//이미지 보기 팝업
	$(".imgItem").on("click",function() {
		
		console.log("이미지 팝업");
		var no = $(this).data("no");
		console.log(no);
		$("#viewModal").modal();

		//서버에 데이터(no) 전송
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/gallery/showImg",
			type : "post",
			//contentType : "application/json",
			data : "no=" + no,

			dataType : "json",
			success : function(galleryVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log("galleryVo 받음");
				console.log(galleryVo);

				var path = "${pageContext.request.contextPath }/upload/"
						+ galleryVo.saveName;
				
				console.log(path);

				$("#viewModelImg").attr("src", path);
				$("#viewModelContent").text(galleryVo.content);

				var userNo = galleryVo.userNo;
				var authno = $("#btnDel")
						.data("authno");

				if (userNo != authno) {
					console.log("삭제버튼숨기기");
					$("#btnDel").hide();
				} else {
					console.log("삭제버튼보이기");
					$("#btnDel").show();
					
					
					//이미지 한개 삭제하기
					$("#btnDel").on("click", function(){
						
						console.log("삭제버튼 클릭");
						
						var galleryVo = {
								no: no,
								userNo: userNo
						};
						
						console.log(galleryVo);
						
						//서버에 데이터(galleryVo: no, userNo) 전송
						$.ajax({
							
							url : "${pageContext.request.contextPath }/api/gallery/delImg",
							type : "post",
							//contentType : "application/json",
							data : galleryVo,

							dataType : "json",
							success : function(count) {
								/*성공시 처리해야될 코드 작성*/
								console.log(count+"건 삭제");
								
								if (count === 1) {
									//모달창 닫기
									$("#viewModal").modal("hide");

									//삭제된 이미지 지우기
									console.log("#t-" + no);
									$("#t-" + no).remove();
								} else {
									//모달창 닫기
									$("#viewModal").modal("hide");
								}
							},
							error : function(XHR, status, error) {
								console.error(status + " : " + error);
							}
						});
						
					});
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		

	});

	
	
</script>

</html>

