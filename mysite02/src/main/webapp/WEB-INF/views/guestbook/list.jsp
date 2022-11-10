<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- contents의 개행을 나타내주는것은 불가피하게 자바언어가 필요 -->
<%
		pageContext.setAttribute("newline", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	
		<c:import url = "/WEB-INF/views/includes/header.jsp" />
		
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				
				<ul>
					<li>
						<table>
							<c:forEach items = '${list }' var ='vo' varStatus = 'status'>
								<tr>
									<td>[${status.count }]</td>
									<td>${vo.name }</td>
									<td>${vo.date }</td>
									<td><a href="${pageContext.request.contextPath}/guestbook?a=deleteform&no=${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
										${fn:replace(vo.contents, 'newline', '<br/>') }
									</td>
								</tr>
							</c:forEach>	
						</table>
						<br>
					</li>
				</ul>
			</div>
		</div>
		
		<c:import url = "/WEB-INF/views/includes/navigation.jsp" />
		
		<c:import url = "/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>