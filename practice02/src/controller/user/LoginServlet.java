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
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserController us = new UserController();
	
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		//		Session에 id,pw가 없을 경우 일로 보내진다.
		if(id==null) {
			response.sendRedirect("jsp/loginForm.jsp");
		}else {
			response.sendRedirect("MainServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserVO user = us.searchByIdPw(id, pw);
		
		if(user.getId()!=null) {
//			정상 로그인
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("name", user.getName());
			session.setAttribute("user", user);
			
			response.sendRedirect("MainServlet");			
		}else {
//			비정상적 로그인
			request.setAttribute("msg", "아이디나 비밀번호가 틀렸습니다.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/loginForm.jsp");
			disp.forward(request, response);
		}
				
	}

}
