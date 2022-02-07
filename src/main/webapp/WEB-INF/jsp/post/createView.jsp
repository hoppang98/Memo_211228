<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>메모 입력</title>
</head>
<body>
	<div id="wrap">
	
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section class="d-flex justify-content-center">
			<div class="w-75 my-5"><%-- 가로 길이 바꾸기 --%>
				<h1 class="text-center">메모 입력</h1>
				<div class="d-flex mt-3">
					<label class="mr-2">제목 : </label>
					<input type="text" class="form-control col-11" id="titleInput">
				</div>
				<textarea class="form-control mt-3" rows="5" id="contentInput"></textarea>
				<input type="file" class="mt-3" id="fileInput">
				<div class="d-flex justify-content-between mt-3">
					<a href="/post/list_view" class="btn btn-info">목록으로</a>
					<button type="button" class="btn btn-success" id="saveBtn">저장</button>
				</div>
			</div>
		</section>

		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
	
	<script>
		$(document).ready(function(){
			$("#saveBtn").on("click", function(){
				let title = $("#titleInput").val();
				let content = $("#contentInput").val().trim(); // trim - 공백제거
				
				if(title == "") {
					alert("제목을 입력하세요");
					return;
				}
				
				if(content == "") {
					alert("내용을 입력하세요");
					return;
				}
				
				var formData = new FormData(); // 파일 전송을 위해서는 formData 객체를 만들어야 한다., 파라미터 전달과정이 원래 하던 ajax와 다르다.
				formData.append("subject", title);
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0]); // 파일을 하나만 올리는 경우에는 files[0]
				
				$.ajax({
					type:"post"
					,url:"/post/create"
					,data:formData
					,enctype:"multipart/form-data"		// 파일 업로드시 필수
					,processData:false					// 파일 업로드시 필수 - 일반적인 파라미터 전달이 아니라는 뜻
					,contentType:false					// 파일 업로드시 필수 - 일반적인 파라미터 전달이 아니라는 뜻
					,success:function(data){
						if(data.result == "success"){
							alert("글쓰기 성공");
							location.href="/post/list_view";
						} else {
							alert("글쓰기 실패");
						}
					}
					,error:function(){
						alert("에러발생");
					}
					
				});
			});
		});
	</script>
</body>
</html>