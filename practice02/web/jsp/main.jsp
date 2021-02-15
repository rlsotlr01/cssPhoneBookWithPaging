<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>연락처 웹사이트</title>
</head>
<body>
<div id="main-box" class ="bgimg">
	<br/>
	<img src="<%=request.getContextPath()%>/img/phonecloud.jpg" width="80" height="80">
	<h2>${name}님 환영합니다</h2>
	<button class="social-signin"><a href="<%=request.getContextPath()%>/LogoutServlet">로그아웃</a></button>
	<button class="social-signin"><a href="<%=request.getContextPath()%>/ModifyUserServlet">회원정보수정</a></button>
	<form action="SearchContactServlet" method="get">
		<select name="keyword">
			<option value="name">이름</option>
			<option value="phone">연락처</option>
			<option value="address">주소</option>
		</select>
		<input type="text" name="searchword">
		<!-- SearchServlet 에서 select 해서 이름 검색, LIKE %search% 이렇게. -->
		<input type="submit" name = "searching" value="검색">
	</form>
	<button class="social-signin"><a href="<%=request.getContextPath()%>/InsertContactServlet">연락처 추가</a></button> 
	<a href="MainServlet"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="30" height="30" align="center"></a>
	<!-- LogoutServlet, ModifyServlet, InsertServlet 만들어야 함. -->
	 
	<br/>
	<span style="color: red;">${msg }</span>
	<table>
		<tr>
			<th>이름</th>
			<th>연락처</th>
			<th>주소</th>
			<th>구분</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		
		<c:forEach var="contact" items="${contactlist}">
		<tr class="listing">
			<td>
				${contact.name}
			</td>
			<td>
				${contact.phone1}-${contact.phone2}-${contact.phone3}
			</td>
			<td>
				${contact.address}
			</td>
			<td>
				${contact.groupnm}
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/ModifyContactServlet?contactnum=${contact.contactnum}" class="listing">수정</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/DeleteContactServlet?contactnum=${contact.contactnum}" class="listing">삭제</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<br/>
</div>
</body>
</html>