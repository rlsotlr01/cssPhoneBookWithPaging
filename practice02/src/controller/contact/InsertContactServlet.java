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
 * Servlet implementation class InsertContactServlet
 */
@WebServlet("/InsertContactServlet")
public class InsertContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactController cc = new ContactController();
       
    public InsertContactServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("jsp/insertContactForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ContactVO contact = new ContactVO();
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		String groupno = request.getParameter("groupno");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(name.equals("")||phone2.equals("")
				||phone3.equals("")||address.equals("")
				||groupno.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/insertContactForm.jsp");
			disp.forward(request, response);
		}
		
		
		contact.setName(name);
		contact.setPhone1(phone1);
		contact.setPhone2(phone2);
		contact.setPhone3(phone3);
		contact.setAddress(address);
		contact.setGroupno(groupno);
		contact.setId(id);
		
		int checker = cc.insertContact(contact);
//		그다음 MainServlet 으로 다시 보낸다. - 이것도 포워딩 해야되나? 필요 없을것 같은데?
//		RequestDispatcher disp = request.getRequestDispatcher("MainServlet");
//		disp.forward(request, response);


//		30 : 연락처 기입 형식 오류
		if(checker == 30) {
			request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/insertContactForm.jsp");
			disp.forward(request, response);
		}
//		40 : 그룹 기입 형식 오류
		if(checker == 40) {
			request.setAttribute("msg", "그룹을 1~3 사이로 지정해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/insertContactForm.jsp");
			disp.forward(request, response);
		}
		if(checker == 1) {
			response.sendRedirect("MainServlet");
		}

	}

}
