package controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDAO;

/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : UserIdCheckServlet.java
 * @package : controller.user
 * @description : 아이디 중복체크 기능을 하는 서블릿입니다.
 */
@WebServlet("/UserIdCheckServlet")
public class UserIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserIdCheckServlet() {
		super();
	}

//	중복체크의 정보는 post 로 보냈기 때문에 get을 안쓴다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

//	중복체크를 처리한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		인코딩을 utf-8로 맞춰준다.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("test/html; charset=UTF-8");

//		id를 request에서 받아온다.
		String id = request.getParameter("id");

//		해당 id가 있는지 확인하고, 그에 대한 숫자를 response에 담아준다. 0 : 해당 아이디 이미 존재. 1 : 해당 아이디 존재하지 않음.
		response.getWriter().write(new UserDAO().idCheck(id) + "");
	}

}
