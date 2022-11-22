<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> <!-- 에러 발생시 메세지 출력 -->

<!DOCTYPE html>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!-- header -->
		<c:import url = "/WEB-INF/views/includes/header.jsp" />
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">
					<p style="text-align:left; padding:2px 0 2px 0; color:red">
					<spring:hasBindErrors name="userVo"> <!-- 에러가 발생한 경우에만 들어오는거 -->
						<c:if test="${errors.hasFieldErrors('name') }">
							<strong>${errors.getFieldError('name').defaultMessage }</strong>
						</c:if>
					</spring:hasBindErrors>
					</p>

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input type="button" value="중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		
		<!-- navigation -->
		<c:import url = "/WEB-INF/views/includes/navigation.jsp" />
		
		<!-- footer -->
		<c:import url = "/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>