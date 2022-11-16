<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="./assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	
		<!-- header -->
		<c:import url = "/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${BoardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${BoardVo.contents }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="./board">글목록</a>
					<c:if test = "${not empty authUser}">
					<a href="./board?a=modifyform&no=${param.no }">글수정</a> <!-- authuser가 not empty일때만 보이도록 -->
					</c:if>
				</div>
			</div>
		</div>
		
		<!-- navigation -->
		<c:import url = "/WEB-INF/views/includes/navigation.jsp" />
		
		<!-- footer -->
		<c:import url = "/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>