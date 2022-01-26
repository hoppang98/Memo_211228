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
		
		<%-- 공통부분인 header은 include 폴더 안의 header.jsp에서 불러온다 --%>
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section class="content d-flex justify-content-center">
			<div class="join-box my-5">
				<div class="display-4">회원가입</div>
				<!--  아이디, 패스워드, 패스워드 확인, 이름, 이메일 -->
				<input type="text" class="form-control mt-3" placeholder="아이디" id="loginIdInput" >
				<input type="password" class="form-control mt-3" placeholder="비밀번호" id="passwordInput">
				<input type="password" class="form-control mt-3" placeholder="비밀번호 확인" id="passwordConfirmInput">
				<input type="text" class="form-control mt-3" placeholder="이름" id="nameInput">
				<input type="text" class="form-control mt-3" placeholder="이메일" id="emailInput">
				
				<button type="button" id="joinBtn" class="btn btn-info btn-block mt-3">회원가입</button>
			</div>
		</section>
		
		<%-- 공통부분인 footer은 include 폴더 안의 footer.jsp에서 불러온다 --%>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	
	</div>
	
	<script>
		$(document).ready(function() {
			$("#joinBtn").on("click", function() {
				
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				var passwordConfirm = $("#passwordConfirmInput").val();
				var name = $("#nameInput").val();
				var email = $("#emailInput").val();
				
				if(loginId == "") {
					alert("아이디를 입력하세요");
					return;
				}
				
				if(password == "") {
					alert("비밀번호를 입력하세요");
					return;
				}
				
				if(password != passwordConfirm) {
					alert("비밀번호가 일치하지 않습니다");
					return;
				}
				
				if(name == "") {
					alert("이름을 입력하세요");
					return;
				}
				
				if(email == "") {
					alert("이메일을 입력하세요");
					return;
				}
				
				$.ajax({
					type:"post",
					url:"/user/sign_up",
					data:{"loginId":loginId, "password":password, "name":name, "email":email},
					success:function(data) {
						if(data.result == "success") {
							alert("회원가입 성공!");
							// 로그인 화면으로 이동
							//location.href = "/user/signin_view";
							
						} else {
							alert("회원가입 실패");
						}
					}, 
					error:function() {
						alert("에러 발생");
					}
				});
				
				
			});
		});
	
	
	</script>
	

</body>
</html>