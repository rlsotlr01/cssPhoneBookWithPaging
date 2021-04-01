package controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUserServlet
 */
/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : DeleteUserServlet.java
 * @package : controller.user
 * @description : 회원을 지우는 기능을 하는 클래스입니다.
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserController uc = new UserController();

	public DeleteUserServlet() {
		super();
	}

//	get -> main 화면에서 들어온 정보를 처리한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		id 값을 request 로부터 가져와 담아준다.
		String id = request.getParameter("id");
//		UserController 안의 deleteUser 기능을 통해 회원을 지웁니다.
		uc.deleteUser(id);
//		그리고 MainServlet 으로 다시 보내준다.
		response.sendRedirect("MainServlet");
	}

//	delete 는 form 이 없기에 post 는 쓰이지 않는다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
