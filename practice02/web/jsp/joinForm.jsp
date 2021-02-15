<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Cute+Font&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<title>회원가입</title>
<style type="text/css">

</style>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<script  type="text/JavaScript" src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#ajax_idDuplicate").click(function(){
	// userID 변수에 id의 입력된 값을 가져오게 함
		var id = $("#id").val();
		$.ajax({
			type: "POST",  // GET or POST 전송방법 
			url: "<%=request.getContextPath()%>/UserIdCheckServlet",  // 이쪽으로 보낸다(호출URL)
			data: {"id": id},  // userID 이름에 userID 데이터 값을 넣어서 보낸다
			success: function(result){  // 만약 성공적으로 수행되었다면 result로 값반환
				if(result == 1){  // id가 checkMessage인 것에 아래 텍스트 출력
					$('#checkMessage').html('사용할 수 있는 아이디입니다.');
				} else {
					$('#checkMessage').html('사용할 수 없는 아이디입니다.');
				}
				// id 가 exampleModal인 모달함수 실행시켜서 모달 실행시키기 위해
				$('#checkModal').modal("show");
			},
			error: function(xhr,status,error){
				alert(error);
			}
		});
	});
});
</script>
		
</head>
<body>
<div id="join-box">
  <div>
  <a href="<%=request.getContextPath()%>/MainServlet"><img src="<%=request.getContextPath()%>/img/joinForm/x_button.jpg" border="0" width="15" height="15" align="right"></a>
  <br>
    <h2>회원가입</h2><br/>
    <span style="color: red;">${msg }</span>
    <form action="<%=request.getContextPath()%>/JoinServlet" method="post">
	    <input type="text" name="name" class="name" placeholder="이름" /><br>
	    <input type="text" name="id" id="id" class="id" placeholder="아이디 입력" />
	    &nbsp
	    <!-- <button class="duplicate_check">중복확인</button> -->
	    
	    <!-- 부트스트랩 테스트 -->
		<!-- Button trigger modal -->
		<button type="button" value="중복확인" id="ajax_idDuplicate" class="social-signin" data-bs-toggle="modal" data-bs-target="#checkModal">
		중복확인
		</button>
		<!-- Modal -->
		<div class="modal fade" id="checkModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">아이디 중복 확인</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body" id="checkMessage">
		      <!-- 적을 내용 (알람창) -->
		      </div>
		      <div class="modal-footer">
		        <button type="button" class = "social-signin" data-bs-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>

	    <!-- 부트스트랩 테스트 -->
	    
	    
	    
	    <input type="password" name="pw" placeholder="비밀번호 입력" /><br>
    연락처 : <br/><select name="phone1">
		<option value="010">010</option>
		<option value="011">011</option>
		<option value="016">016</option>
		<option value="018">018</option>
		<option value="019">019</option>
		</select>
		-<input type="text" class="phone" name="phone2">
		-<input type="text" class="phone" name="phone3"><br>
		성별 : 
		남자 <input type="radio" name="gender" value="man">
		여자 <input type="radio" name="gender" value="woman"><br>
    	<input type="submit" name="signup_submit" value="회원가입" />
    </form>
    <br>
<a href="<%=request.getContextPath()%>/MainServlet"><img src="<%=request.getContextPath()%>/img/home.png" border="0" width="30" height="30" align="center"></a>
    <br>
    <br>
    
  </div>
  <div>
</body>
</html>