<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<title>로그인창</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

 <div id="login-box">
 <br/>
	<br/>
	<img src="<%=request.getContextPath()%>/img/phonecloud.jpg" width="130" height="130">	
	<br/><br/><br/>
	<span style="color: red;">${msg }</span>
	<form action = "<%=request.getContextPath()%>/LoginServlet" method="post">
	<input type="text" name="id" placeholder="아이디"><br/>
		<input type="password" name="pw" placeholder="비밀번호"><br>
		<input type="submit" value="로그인">
	<button class="social-signin"><a href="<%=request.getContextPath()%>/JoinServlet">회원가입</a>	</button>
	</form>
	</div>
</body>
</html>