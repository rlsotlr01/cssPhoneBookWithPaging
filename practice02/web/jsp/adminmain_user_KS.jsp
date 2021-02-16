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

<!-- 페이징 처리 연습 -->
<%
	int pageSize = 10;
	String pageNum = request.getParameter("pageNum");
	if(pageNum==null){
		pageNum="1";
	}
	String searchword = request.getParameter("searchword");
	String keyword = request.getParameter("keyword");
	int currentPage = Integer.parseInt(pageNum);
	
	int startRow = (currentPage-1)*pageSize+1;
	int endRow = currentPage*pageSize;
	int count=0;
	UserDAO userDAO = new UserDAO();
	count = userDAO.getCount_KS_admin(searchword, keyword);
	
	ArrayList<UserVO> userlist = null;
	if(count>0){
		userlist = userDAO.searchAll_KS(keyword, searchword, startRow, endRow);
	}
%>

<!-- 페이징 처리 연습 여기까지 -->

<div id="adminusermain-box">
<br/>
<img src="<%=request.getContextPath()%>/img/phonecloud.jpg" width="80" height="80">
<h2>${name}님 환영합니다 &nbsp; <img src="<%=request.getContextPath()%>/img/user.png" width="30" height="30"> </h2>
<button class="social-signin"><a href="<%=request.getContextPath()%>/LogoutServlet">로그아웃</a></button>
<button class="social-signin"><a href="<%=request.getContextPath()%>/MainServlet">연락처 보기</a></button>
<!-- LogoutServlet, ModifyServlet, InsertServlet 만들어야 함. -->
<form action="<%=request.getContextPath()%>/jsp/adminmain_user_KS.jsp" method="get">
	검색 : <select name="keyword">
		<option value="name">이름</option>
		<option value="id">아이디</option>
		<option value="phone">연락처</option>
	</select>
	<input type="text" name="searchword">
	<!-- SearchServlet 에서 select 해서 이름 검색, LIKE %search% 이렇게. -->
	<input type="submit" name = "searching" value="검색">
</form>
<a href="<%=request.getContextPath()%>/jsp/adminmain_user.jsp"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="30" height="30" align="center"></a>
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
<!-- 페이징처리 연습 -->
	<%
		if (count>0){ // 데이터베이스가 있으면
			int number = count - (currentPage-1)*pageSize;
			for (int i=0; i<userlist.size(); i++){
				UserVO user = userlist.get(i);
		
	%>
	<tr>
		<td><%=user.getName() %></td>
		<td><%=user.getId() %></td>
		<td><%=user.getPhone1()%>-<%=user.getPhone2()%>-<%=user.getPhone3()%></td>
		<td><%=user.gender %></td>
		<td>
			<a href="<%=request.getContextPath()%>/ModifyUserServlet?id=<%=user.getId() %>" class="listing">수정</a>
		</td>
		<td>
			<a href="<%=request.getContextPath()%>/DeleteUserServlet?id=<%=user.getId() %>" class="listing">삭제</a>
		</td>
	<%
			}
		}else{
	%>
	<tr>
		<td colspan="6" align="center">유저가 없습니다.</td>
	</tr>
	<%
		}
	%>
			
<!-- 페이징처리 연습 -->
<%-- 	<c:forEach var="user1" items="${userlist}">
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
	</c:forEach> --%>
	<tr>
				<td colspan="6" align="center">
					<%	// 페이징  처리
						if(count > 0){
							// 총 페이지의 수
							int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
							// 한 페이지에 보여줄 페이지 블럭(링크) 수
							int pageBlock = 10;
							// 한 페이지에 보여줄 시작 및 끝 번호(예 : 1, 2, 3 ~ 10 / 11, 12, 13 ~ 20)
							int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
							int endPage = startPage + pageBlock - 1;
							
							// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
							if(endPage > pageCount){
								endPage = pageCount;
							}
							
							if(startPage > pageBlock){ // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
					%>
								<a href="<%=request.getContextPath()%>/jsp/adminmain_user.jsp?pageNum=<%=startPage - 10%>" class="page">[이전]</a>	
					<%			
							}
							
							for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
								if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
					%>
									[<%=i %>]
					<%									
								}else{ // 현재 페이지가 아닌 경우 링크 설정
					%>
									<a href="<%=request.getContextPath()%>/jsp/adminmain_user.jsp?pageNum=<%=i%>" class="page">[<%=i %>]</a>
					<%	
								}
							} // for end
							
							if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
					%>
								<a href="<%=request.getContextPath()%>/jsp/adminmain_user.jsp?pageNum=<%=startPage + 10 %>" class="page">[다음]</a>
					<%			
							}
						}
					%>
				</td>
			</tr>
	
</table>
<br/>


</div>
</body>
</html>