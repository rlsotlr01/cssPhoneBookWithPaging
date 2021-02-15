<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>연락처 추가</title>
</head>
<body>
<div id="insertcontact-box">
<br/>
<h2>연락처 추가 Form</h2>
<form action="<%=request.getContextPath()%>/InsertContactServlet" method="post">
<span style="color: red;">${msg }</span><br/>
	이름 : <input type="text" name="name" size="10"><br/>
	핸드폰 번호 : 
			<select name="phone1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="018">018</option>
					<option value="019">019</option>
			</select>
			  - <input type="text" name="phone2" class="phone" size="4">
			  - <input type="text" name="phone3" class="phone" size="4"><br/>
		주소 : <input type="text" name="address" size="20"><br/>
		소속 : 
		<select name="groupno">
		<option value="1">1.가족</option>
		<option value="2">2.친구</option>
		<option value="3">3.기타</option>
		</select>
		<br/>
		<input type="submit" value="연락처 추가">
		<br/>
		<br>
		<a href="<%=request.getContextPath()%>/MainServlet"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="40" height="40" align="center"></a>
</form>

</div>
</body>
</html>