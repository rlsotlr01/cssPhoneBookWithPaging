<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>phone cloud - 회원가입 완료</title>
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<div class="main_window">
     <div class="cloud" data-type="white_4" style="top: 238px;" data-speed="1"></div>
     <div class="cloud" data-type="white_4" style="top: 410px;" data-speed="6"></div>
     <div class="cloud" data-type="white_6" style="top: 197px;" data-speed="8"></div>
     <div class="cloud" data-type="white_5" style="top: 133px;" data-speed="11"></div>
     <div class="cloud" data-type="white_5" style="top: 126px;" data-speed="12"></div>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

	<div id="join-result-box">
	<%
		String name = request.getParameter("name");
	%>
		<br/>
		<h2><%=name %>님 <br/> 회원가입 성공하셨습니다.</h2>
		<a href="<%=request.getContextPath()%>/MainServlet"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="30" height="30" align="center"></a>
	<div/>
	</div>
</html>