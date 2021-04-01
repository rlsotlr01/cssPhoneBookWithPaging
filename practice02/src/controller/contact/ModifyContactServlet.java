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
 * Servlet implementation class ModifyContactServlet
 */
/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : ModifyContactServlet.java
 * @package : controller.contact
 * @description : 연락처를 수정해주는 서블릿입니다.
 */
@WebServlet("/ModifyContactServlet")
public class ModifyContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactController cc = new ContactController();

	public ModifyContactServlet() {
		super();
	}

//	get -> main 화면에서 오는 정보를 받아서 처리한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		?contactnum=${contactnum} 이렇게 정보가 들어오면
//		contactnum 을 따로 변수를 지정해 담아준다.
		String contactnum = request.getParameter("contactnum");
		
//		해당 고유번호(contactnum)을 통해 해당 고유번호를 가진 연락처를 가져와 연락처 객체에 담는다.
		ContactVO contact = cc.searchByContactnum(contactnum);
//		그 연락처를 request 에 contact 라는 이름으로 담아준다.
		request.setAttribute("contact", contact);
//		세션에 미연의 상황을 대비해 contactnum 을 담아준다.
		HttpSession session = request.getSession();
		session.setAttribute("contactnum", contactnum);
		
//		해당 연락처의 정보를 modifyContactForm 으로 보낸다.
		RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
		disp.forward(request, response);
	}

//	post -> form 화면에서 오는 정보를 받아서 처리한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int checker = 0;

		ContactVO contact = new ContactVO();
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		String groupno = request.getParameter("groupno");
		HttpSession session = request.getSession();
		String contactnum = (String) session.getAttribute("contactnum");
		String id = (String) session.getAttribute("id");

//		아무것도 기입하지 않았을 경우 다시 form 화면으로 돌아가도록 설정한다.
		if (name.equals("") || phone2.equals("") || phone3.equals("") || address.equals("") || groupno.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			contact = cc.searchByContactnum(contactnum);
			request.setAttribute("contact", contact);
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
			disp.forward(request, response);
		} else {
//			모두 기입되었을 경우 contact 객체 안에 값을 담아준다.
			contact.setName(name);
			contact.setPhone1(phone1);
			contact.setPhone2(phone2);
			contact.setPhone3(phone3);
			contact.setAddress(address);
			contact.setGroupno(groupno);
			contact.setContactnum(contactnum);

//			해당 연락처를 DB에 업데이트를 해준다.
			checker = cc.updateContact(contact);

//		30 : 연락처 기입 형식 오류
			if (checker == 30) {
				request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
				contact = cc.searchByContactnum(contactnum);
				request.setAttribute("contact", contact);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
				disp.forward(request, response);
			}
//		40 : 그룹 기입 형식 오류
			if (checker == 40) {
				request.setAttribute("msg", "그룹을 1~3 사이로 지정해주세요.");
				contact = cc.searchByContactnum(contactnum);
				request.setAttribute("contact", contact);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
				disp.forward(request, response);
			}
//		1 : 정상처리
			if (checker == 1) {
				response.sendRedirect("MainServlet");
			}
		}

	}

}
