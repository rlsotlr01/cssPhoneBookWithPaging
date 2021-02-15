<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>연락처 수정</title>
</head>
<body>
<div id="modifycontact-box">
<br/>
	<h2>수정 Form</h2>
	<span style="color: red;">${msg }</span>
		<form action="<%=request.getContextPath()%>/ModifyContactServlet" method="post">
			이름 : <input type="text" name="name" size="10" value="${contact.name }"><br/>
			연락처 : 
				<c:if test="${contact.phone1 eq'010'}">
					<select name="phone1">
							<option value="010" selected="selected">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select>
				</c:if>
				<c:if test="${contact.phone1 eq '011'}">
					<select name="phone1">
							<option value="010">010</option>
							<option value="011" selected="selected">011</option>
							<option value="016">016</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select>
				</c:if>
				<c:if test="${contact.phone1 eq '016'}">
					<select name="phone1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016" selected="selected">016</option>
							<option value="018">018</option>
							<option value="019">019</option>
					</select>
				</c:if>
				<c:if test="${contact.phone1 eq '018'}">
					<select name="phone1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="018" selected="selected">018</option>
							<option value="019">019</option>
					</select>
				</c:if>
				<c:if test="${contact.phone1 eq '019'}">
					<select name="phone1">
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="018">018</option>
							<option value="019" selected="selected">019</option>
					</select>
				</c:if>
				  - <input type="text" class="phone" name="phone2" size="4" value="${contact.phone2 }">
				  - <input type="text" class="phone" name="phone3" size="4" value="${contact.phone3 }"><br/>
			주소 : <input type="text" name="address" size="20" value="${contact.address }"><br/>
			소속 : 
			<select name="groupno">
			<option value="1">1.가족</option>
			<option value="2">2.친구</option>
			<option value="3">3.기타</option>
			</select>
			<!-- 소속 이거 select 로 1,2,3 해야 함. -->
			<br/>
			<input type="submit" value="수정"><br/><br/>
			<a href="<%=request.getContextPath()%>/MainServlet"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="40" height="40" align="center"></a>
		</form>
</div>
</body>
</html>