package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.contact.ContactController;
import controller.user.UserController;
import dao.contact.ContactDAO;
import vo.contact.ContactVO;
import vo.user.UserVO;

/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : MainServlet.java
 * @package : controller
 * @description : 메인화면을 컨트롤하는 서블릿입니다.
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Post 로 들어오는 건 Form 뿐이므로 Form에 대한 조건처리만 해주면 됨.
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if (id == null) {
//			로그인이 안 되어 있는 상태일 경우 (LoginServlet - doGet)
			response.sendRedirect("LoginServlet");
		} else if (id.equals("admin")) {
//			관리자 아이디로 로그인 할 시
			RequestDispatcher disp = request.getRequestDispatcher("jsp/adminmain.jsp");
			disp.forward(request, response);
		} else {
//			정상적으로 로그인이 된 상태일 경우 (연락처리스트 뽑아서 main.jsp로 포워딩해서 넘겨줌.)
			RequestDispatcher disp = request.getRequestDispatcher("jsp/main.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		MainServlet 은 Post 로 들어올 일이 딱히 없다. 	
	}
}
