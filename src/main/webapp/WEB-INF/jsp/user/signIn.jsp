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

<title>로그인</title>
</head>
<body>
	<div id="wrap">
	
		<%-- 공통부분인 header은 include 폴더 안의 header.jsp에서 불러온다 --%>
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section class="d-flex justify-content-center">
		<%-- form으로 감싸야 submit 버튼을 통해 enter키로 로그인 가능 --%>
			<form id="loginForm">
				<div class="login-box my-5">
					<input type="text" class="form-control mt-3" placeholder="아이디" id="loginIdInput" >
					<input type="password" class="form-control mt-3" placeholder="비밀번호" id="passwordInput">
					<button type="submit" class="btn btn-info btn-block mt-3">로그인</button>
					
					<div class="text-center mt-2">
						<a href="/user/signup_view">회원가입</a>
					</div>
				</div>
			</form>
		</section>
		
		<%-- 공통부분인 footer은 include 폴더 안의 footer.jsp에서 불러온다 --%>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
		
	</div>
	
	<script>
		$(document).ready(function(){
			$("#loginForm").on("submit", function(e){ 		// 버튼이 아닌 form자체에 이벤트를 잡는다. / e를 넣으면 발생한 이벤트와 관련된 정보가 변수 e에 저장된다.
				e.preventDefault();	// 에러가 생겼을 경우 e와 관련된 이벤트가 모두 삭제
				
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				
				if(loginId == "") {
					alert("아이디를 입력하세요");
					return;
				}
				
				if(password == "") {
					alert("비밀번호를 입력하세요");
					return;
				}
				
				$.ajax({
					type:"post"
					,url:"/user/sign_in"	// 로그인을 위한 api url(설계문서 참고)
					,data:{"loginId":loginId, "password":password}
					,success:function(data) {
						// 성공, 실패시 데이터(api 설계문서 참고)
						if(data.result == "success"){
							location.href="/post/list_view";
						} else {
							alert("아이디/비밀번호를 확인하세요.");
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