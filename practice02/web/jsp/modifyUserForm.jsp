<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>phone cloud - 회원정보 수정</title>
</head>
<body>
<div class="main_window">
     <div class="cloud" data-type="white_4" style="top: 238px;" data-speed="1"></div>
     <div class="cloud" data-type="white_4" style="top: 410px;" data-speed="6"></div>
     <div class="cloud" data-type="white_6" style="top: 197px;" data-speed="8"></div>
     <div class="cloud" data-type="white_5" style="top: 133px;" data-speed="11"></div>
     <div class="cloud" data-type="white_5" style="top: 126px;" data-speed="12"></div>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

	<div id="modifyuser-box">
		<br/>
		<h2>회원정보 수정</h2>
		<span style="color: red;">${msg }</span>
		<form action="<%=request.getContextPath()%>/ModifyUserServlet" method="post">
			이름 : <input type="text" name="name" class="id" value="${user.name}" size="10"><br/>
			아이디 : <input type="text" name="id" value="${user.id}" readonly="readonly" ><br/>
			비밀번호 : <input type="password" name="pw" size="10"><br/>
			연락처 : 
				<c:if test="${user.phone1 eq '010'}">
				<select name="phone1">
						<option value="010" selected="selected">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="018">018</option>
						<option value="019">019</option>
				</select>
				</c:if>
				<c:if test="${user.phone1 eq '011'}">
				성별 : <select name="phone1">
						<option value="010">010</option>
						<option value="011" selected="selected">011</option>
						<option value="016">016</option>
						<option value="018">018</option>
						<option value="019">019</option>
				</select>
				</c:if>
				<c:if test="${user.phone1 eq '016'}">
				<select name="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016" selected="selected">016</option>
						<option value="018">018</option>
						<option value="019">019</option>
				</select>
				</c:if>
				<c:if test="${user.phone1} eq '018'}">
				<select name="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="018" selected="selected">018</option>
						<option value="019">019</option>
				</select>
				</c:if>
				<c:if test="${user.phone1} eq '019'}">
				<select name="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="018">018</option>
						<option value="019" selected="selected">019</option>
				</select>
				</c:if>
					-
					<input type="text" class="phone" name="phone2" value="${user.phone2}" size="4">
					-
					<input type="text" class="phone" name="phone3" value="${user.phone3}" size="4">
			<br/>
			성별 : <c:if test="${user.gender == 'man'}">
					<input type="radio" name="gender" value="man" checked="checked">남 
					<input type="radio" name="gender" value="woman">여
				</c:if>
				<c:if test="${user.gender == 'woman'}">
					<input type="radio" name="gender" value="man">남 
					<input type="radio" name="gender" value="woman" checked="checked" >여
				</c:if><br/>
				<input type="submit" name="modifyUser"/><br/><br/>
				<a href="MainServlet"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="40" height="40" align="center"></a>
				
		</form>
		<br><br>
	</div>
</div>
</body>
</html>