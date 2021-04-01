package controller.contact;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.contact.ContactVO;


/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : DeleteContactServlet.java
 * @package : controller.contact
 * @description : 연락처를 지우는 서블릿입니다.
 */
@WebServlet("/DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactController cc = new ContactController();
	
    public DeleteContactServlet() {
        super();
    }

//    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contactnum = request.getParameter("contactnum");
		cc.deleteContact(contactnum);
		RequestDispatcher disp = request.getRequestDispatcher("MainServlet");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
