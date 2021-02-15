<%@page import="vo.paging.Paging"%>
<%@page import="dao.user.UserDAO"%>
<%@page import="vo.user.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>연락처 웹사이트 - 운영자 회원보기</title>
</head>
<body>


<div id="adminusermain-box">
<br/>
<img src="<%=request.getContextPath()%>/img/phonecloud.jpg" width="80" height="80">
<h2>${name}님 환영합니다 &nbsp; <img src="<%=request.getContextPath()%>/img/user.png" width="30" height="30"> </h2>
<button class="social-signin"><a href="LogoutServlet">로그아웃</a></button>
<button class="social-signin"><a href="MainServlet">연락처 보기</a></button>
<!-- LogoutServlet, ModifyServlet, InsertServlet 만들어야 함. -->
<form action="SearchUserServletKS" method="get">
	검색 : <select name="keyword">
		<option value="name">이름</option>
		<option value="id">아이디</option>
		<option value="phone">연락처</option>
	</select>
	<input type="text" name="searchword">
	<!-- SearchServlet 에서 select 해서 이름 검색, LIKE %search% 이렇게. -->
	<input type="submit" name = "searching" value="검색">
</form>
<a href="MainServlet"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="30" height="30" align="center"></a>
<!-- adminmain_user 에 추가버튼 서블릿 아직 안만듬 -->
<br/>
<span style="color: red;">${msg }</span>
<table>
	<tr>
		<th>이름</th>
		<th>아이디</th>
		<th>연락처</th>
		<th>성별</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	
	<c:forEach var="user1" items="${userlist}">
	<tr  class="listing">
		<td>
			${user1.name}
		</td>
		<td>
			${user1.id}
		</td>
		<td>
			${user1.phone1}-${user1.phone2}-${user1.phone3}
		</td>
		<td>
			${user1.gender}
		</td>
		<td>
			<a href="ModifyUserServlet?id=${user1.id}" class="listing">수정</a>
		</td>
		<td>
			<a href="DeleteUserServlet?id=${user1.id}" class="listing">삭제</a>
		</td>
	</tr>
	</c:forEach>
</table>
<br/>


</div>
</body>
</html>