package controller.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.contact.ContactDAO;
import vo.contact.ContactVO;
import vo.user.UserVO;

/**
 * Servlet implementation class LoginServlet
 */
/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : LoginServlet.java
 * @package : controller.user
 * @description : 로그인 기능을 해주는 서블릿입니다.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserController us = new UserController();

	public LoginServlet() {
		super();
	}

//	메인 화면에서 로그인 버튼을 눌렀을 때, 메인화면에서 가져온 정보를 처리합니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request 로부터 세션을 받아내어, 세션에 등록된 id를 확인합니다.
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// Session에 id,pw가 없을 경우 일로 보내진다.
		if (id == null) { // 로그인 되지 않은 경우
			response.sendRedirect("jsp/loginForm.jsp");
		} else { // 로그인 된 경우 메인화면으로 보내줍니다.
			response.sendRedirect("MainServlet");
		}
	}

//	로그인 폼을 입력하고 로그인할 때, 로그인 기능을 수행합니다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

//	doPost 안에 행해질 작업을 담아둔 함수입니다.
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		utf-8로 인코딩하고, id랑 pw를 각각의 변수에 담아줍니다.
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

//		해당 id와 pw에 모두 해당하는 user를 가져와 회원 객체에 담아줍니다.
		UserVO user = us.searchByIdPw(id, pw);

//		해당 회원에 id값이 있을 경우 -> 정상 로그인
		if (user.getId() != null) {
//			세션을 가져와 세션에 아이디, 이름, 유저객체를 담아주고 메인화면으로 넘겨줍니다.
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", user.getName());
			session.setAttribute("user", user);

			response.sendRedirect("MainServlet");
		} else {
//			비정상적 로그인 (id가 없을 경우)
//			오류 메시지 출력하고 로그인 폼으로 다시 되돌려 보냅니다.
			request.setAttribute("msg", "아이디나 비밀번호가 틀렸습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/loginForm.jsp");
			disp.forward(request, response);
		}

	}

}
