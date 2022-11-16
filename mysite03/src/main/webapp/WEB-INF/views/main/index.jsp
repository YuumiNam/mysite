<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="./assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<!-- header -->
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="<%=request.getContextPath() %>/assets/images/nature.jpg" style="width:200px">
					<h2>안녕하세요. 이선무의 mysite에 오신것을 환영합니다.</h2>
					<p>
						이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.<br>
						데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서
						만들어 놓은 사이트 입니다.<br>
						메뉴는 사이트 소개, 방명록, 게시판이 있습니다.<br>
						<a href="${pageContext.request.contextPath }/guest?a=list">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		
		<!-- navigation -->
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		
		<!-- footer -->
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
		
	</div>
</body>
</html>