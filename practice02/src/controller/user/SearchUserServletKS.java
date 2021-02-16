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
			request.setAttribute("msg", "검색어를 입력해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("MainServlet");
			disp.forward(request, response);
		}else if(searchword!=null && id.equals("admin")) {
			RequestDispatcher disp 
				= request.getRequestDispatcher("jsp/adminmain_user.jsp");
			disp.forward(request, response);
		}else {
			RequestDispatcher disp = request.getRequestDispatcher("MainServlet");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
