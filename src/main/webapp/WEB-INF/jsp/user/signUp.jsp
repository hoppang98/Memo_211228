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

<title>회원가입</title>
</head>
<body>
	<div id="wrap">

		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="d-flex justify-content-center">
			<div class="signup-box d-flex align-items-center my-5">
				<div class="w-100">
					<h2 class="text-center display-4">회원가입</h2>
					<form id="signupForm">
						<input type="text" id="loginIdInput" name="loginId" class="form-control mt-3" placeholder="아이디">
						<input type="password" id="passwordInput" name="password" class="form-control mt-3" placeholder="패스워드">
						<input type="password" id="passwordConfirmInput" class="form-control mt-3" placeholder="패스워드 확인">
						<small id="errorPassword" class="text-danger d-none">비밀번호가 일치하지 않습니다.</small>
						<input type="text" id="nameInput" name="name" class="form-control mt-3" placeholder="이름">
						<input type="text" id="emailInput" name="email" class="form-control mt-3" placeholder="이메일">
						
						<button type="submit" id="signUpBtn" class="btn btn-info btn-block mt-3">회원가입</button>
					</form>
				</div>
			</div>
		
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#signupForm").on("submit", function(e) {
				
				e.preventDefault();
				
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				var passwordConfirm = $("#passwordConfirmInput").val();
				var name = $("#nameInput").val().trim();
				var email = $("#emailInput").val().trim();
				
				if(loginId == null || loginId == "") {
					alert("아이디를 입력하세요");
					return false;
				}
				
				if(password == null || password == "") {
					alert("비밀번호를 입력하세요");
					return false;
				}
				
				if(password != passwordConfirm) {
					$("#errorPassword").removeClass("d-none");
					return false;
				}
				
				if(name == null || name == "") {
					alert("이름을 입력하세요");
					return false;
				}
				
				if(email == null || email == "") {
					alert("이메일을 입력하세요");
					return false;
				}
				
				$.ajax({
					type:"post",
					url:"/user/sign_up",
					data:{"loginId":loginId, "password":password, "name":name, "email":email},
					success:function(data) {
						if(data.result == "success") {
							alert("회원 가입 성공!");
							// location.href="/user/signin_view";
							
						} else {
							alert("회원 가입 실패");
						}
					}, 
					error:function(e) {
						alert("에러발생");
					}
					
					
				});
				
			});
		});
	
	</script>
</body>
</html>