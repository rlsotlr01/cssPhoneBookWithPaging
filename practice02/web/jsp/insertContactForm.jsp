<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>phone cloud - 연락처 추가</title>
</head>
<body>

<div class="main_window">
     <div class="cloud" data-type="white_4" style="top: 238px;" data-speed="1"></div>
     <div class="cloud" data-type="white_4" style="top: 410px;" data-speed="6"></div>
     <div class="cloud" data-type="white_6" style="top: 197px;" data-speed="8"></div>
     <div class="cloud" data-type="white_5" style="top: 133px;" data-speed="11"></div>
     <div class="cloud" data-type="white_5" style="top: 126px;" data-speed="12"></div>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
	
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
	<br><br>
	</div>
	</div>
</body>
</html>