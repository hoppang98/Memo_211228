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
<title>메모리스트</title>
</head>
<body>
	<div id="wrap">
	
		<%-- 공통부분인 header은 include 폴더 안의 header.jsp에서 불러온다 --%>
		<c:import url="/WEB-INF/jsp/include/header.jsp"/>
		
		<section class="d-flex justify-content-center">	
			<div class="w-75 my-5">
				<h1 class="text-center">메모게시판</h1>
				
				<table class="table text-center">
					<thead>
						<tr>
							<th>NO.</th>
							<th>제목</th>
							<th>시간</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="post" items="${postlist}">
						<tr>
							<td>${post.id}</td>
							<td>
								<a href="/post/detail_view?postId=${post.id}">${post.subject}</a>
							</td>
							<td>
								<fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="d-flex justify-content-end">
					<a href="/post/create_view" class="btn btn-info">글쓰기</a>
				</div>
			</div>
			
			
		</section>
		
		<%-- 공통부분인 footer은 include 폴더 안의 footer.jsp에서 불러온다 --%>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>
</body>
</html>