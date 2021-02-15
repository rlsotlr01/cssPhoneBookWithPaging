package controller.contact;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.contact.ContactVO;

@WebServlet("/SearchContactServlet")
public class SearchContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactController cc = new ContactController();
       
    public SearchContactServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String keyword = request.getParameter("keyword");
		String searchword = request.getParameter("searchword");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id.equals("admin")){
			ArrayList<ContactVO> contactlist = cc.searchByKS(keyword,searchword);
			
			request.setAttribute("contactlist", contactlist);
			RequestDispatcher disp 
					= request.getRequestDispatcher("jsp/adminmain.jsp");
			disp.forward(request, response);
			
		}else {
			
			ArrayList<ContactVO> contactlist = cc.searchByKS(keyword,searchword, id);
			
			request.setAttribute("contactlist", contactlist);
			System.out.println("SearchContactServlet test");
			System.out.println("contactlist size : "+contactlist.size());
//			여기서 0이 나옴.			
			RequestDispatcher disp 
					= request.getRequestDispatcher("jsp/main.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
