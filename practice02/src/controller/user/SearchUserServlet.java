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

import controller.contact.ContactController;
import vo.contact.ContactVO;
import vo.user.UserVO;

/**
 * Servlet implementation class SearchUserServlet
 */
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(id==null) {
//			로그인이 안 되어 있는 상태일 경우 (LoginServlet - doGet)
			response.sendRedirect("MainServlet");
		}else if(id.equals("admin")){
//			관리자 아이디로 로그인 할 시
			UserController uc = new UserController();
			ArrayList<UserVO> userlist = uc.searchAll();
			request.setAttribute("userlist",userlist);
			session = request.getSession();
			
			RequestDispatcher disp 
				= request.getRequestDispatcher("jsp/adminmain_user.jsp");
			disp.forward(request, response);	
		}else{
			RequestDispatcher disp 
					= request.getRequestDispatcher("MainServlet");
			disp.forward(request, response);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
