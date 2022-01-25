<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    	<header class="bg-light d-flex align-items-center justify-content-between">
			<h1 class="ml-3">Memo</h1>
			
			<%-- 로그인 정보에 따라 나오는 화면이 다르다. --%>
			<c:choose>
				<c:when test="${not empty userId}">	<%-- 세션에 userId값이 존재하는지 확인 --%>
					<div class="mr-3">${userName}님 <a href="/user/sign_out">로그아웃</a></div> <%-- session을 통해 로그인시 출력 / session에 userId나 userName같은 정보들이 들어있어서 쉽게 불러올 수 있다.--%>
				</c:when>
				<c:otherwise>
					<div class="mr-3"><a href="/user/signin_view">로그인</a></div>
				</c:otherwise>
			</c:choose>
			
		</header>