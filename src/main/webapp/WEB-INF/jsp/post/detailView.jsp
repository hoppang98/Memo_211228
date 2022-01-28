<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- bootstrap --%>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>  
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>     
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		
	<%-- css파일 --%>
	<link rel="stylesheet" type="text/css" href="/static/css/style.css">
<title>메모 보기</title>
</head>
<body>
	<div id="wrap">
	
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section class="d-flex justify-content-center">
			<div class="w-75 my-5"><%-- 가로 길이 바꾸기 --%>
				<h1 class="text-center">메모 보기</h1>
				<div class="d-flex mt-3">
					<label class="mr-2">제목 : </label>
					<input type="text" class="form-control col-11" id="titleInput" value="${post.subject}">
				</div>
				<textarea class="form-control mt-3" rows="5" id="contentInput">${post.content}</textarea>
				<div class="d-flex justify-content-between mt-3">
					<div>
						<a href="/post/list_view" class="btn btn-info">목록으로</a>
						<button type="button" class="btn btn-danger" id="deleteBtn" data-post-id="${post.id}">삭제</button><%-- data-post-id로 button 객체에 데이터를 저장 --%>
					</div>
					<button type="button" class="btn btn-success" id="updateBtn">수정</button>
				</div>
			</div>
		</section>

		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	<script>
		$(document).ready(function(){
			$("#deleteBtn").on("click", function() {
				
				let postId = $(this).data("post-id");
				
				$.ajax({
					type:"get"
					,url:"/post/delete"
					,data:{"postId":postId}
					,success:function(data) {
						if(data.result == "success"){
							location.href="/post/list_view";
						} else {
							alert("삭제 실패");
						}
					}, error:function(){
						alert("에러발생");
					}
				});
			});
			
			$("#updateBtn").on("click", function(){
				let title = $("#titleInput").val();
				let content = $("#contentInput").val().trim();
				let postId = $(this).data("post-id");
				
				$.ajax({
					type:"post"
					,url:"/post/update"
					,data:{"postId":postId, "subject":title, "content":content}
					,success:fucntion(data) {
						if(data.result == "success"){
							location.href="/post/list_view";
						} else {
							alert("수정 실패");
						}
					}, error:function(){
						alert("삭제 실패");
					}
				});
			});
		});
	</script>
</body>
</html>