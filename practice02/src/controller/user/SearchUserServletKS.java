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

import vo.user.UserVO;

/**
 * Servlet implementation class SearchUserServletKS
 */
@WebServlet("/SearchUserServletKS")
public class SearchUserServletKS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchUserServletKS() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String keyword = request.getParameter("keyword");
		String searchword = request.getParameter("searchword");
		
		System.out.println("SearchUserServlet 접속 테스트");
		
		if(searchword==null && id.equals("admin")) {
			System.out.println("SearchUserServlet 1 if");
			request.setAttribute("msg", "검색어를 입력해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("MainServlet");
			disp.forward(request, response);
		}else if(searchword!=null && id.equals("admin")) {
			System.out.println("SearchUserServlet 2 if");
			UserController uc = new UserController();
			ArrayList<UserVO> userlist = uc.searchAll_KS(keyword, searchword);
			request.setAttribute("userlist", userlist);
			session = request.getSession();
			RequestDispatcher disp 
				= request.getRequestDispatcher("jsp/adminmain_user.jsp");
			disp.forward(request, response);
		}else {
			System.out.println("SearchUserServlet 3 if");
			RequestDispatcher disp = request.getRequestDispatcher("MainServlet");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
