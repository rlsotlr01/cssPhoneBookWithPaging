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
<title>phone cloud - 로그인</title>
</head>
<body>

  <div class="main_window">
     <div class="cloud" data-type="white_4" style="top: 238px;" data-speed="1"></div>
     <div class="cloud" data-type="white_4" style="top: 410px;" data-speed="6"></div>
     <div class="cloud" data-type="white_6" style="top: 197px;" data-speed="8"></div>
     <div class="cloud" data-type="white_5" style="top: 133px;" data-speed="11"></div>
     <div class="cloud" data-type="white_5" style="top: 126px;" data-speed="12"></div>
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
		<button class="social-signin" type="button" onclick="location.href='<%=request.getContextPath()%>/JoinServlet'"><a href="<%=request.getContextPath()%>/JoinServlet">회원가입</a>	</button>
	</form>
	</div>
  </div>



</body>
</html>