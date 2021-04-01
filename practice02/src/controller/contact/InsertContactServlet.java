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
/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : InsertContactServlet.java
 * @package : controller.contact
 * @description : 연락처를 추가하는 서블릿입니다.
 */
@WebServlet("/InsertContactServlet")
public class InsertContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactController cc = new ContactController();

	public InsertContactServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("jsp/insertContactForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request를 utf-8 인코딩으로 맞춰줍니다.
		request.setCharacterEncoding("utf-8");
		
//		연락처 객체를 생성하고, request 에서 받아온 정보를 각각의 변수에 담습니다.
		ContactVO contact = new ContactVO();
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address = request.getParameter("address");
		String groupno = request.getParameter("groupno");
		
//		아이디는 정보 저장 유지를 위해 세션에 담아줍니다.
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

//		form 항목에 아무것도 입력되지 않을 경우 오류를 출력하고, form 화면으로 다시 돌려보냅니다.
		if (name.equals("") || phone2.equals("") || phone3.equals("") || address.equals("") || groupno.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/insertContactForm.jsp");
			disp.forward(request, response);
		}

//		문제가 없으면 각각의 정보를 contact 객체 안에 담아줍니다.
		contact.setName(name);
		contact.setPhone1(phone1);
		contact.setPhone2(phone2);
		contact.setPhone3(phone3);
		contact.setAddress(address);
		contact.setGroupno(groupno);
		contact.setId(id);

//		해당 객체를 유저DB에 insert 하고, 그 결과값에 대한 숫자를 checker 에 받습니다. (그 숫자는 오류 종류를 표시합니다.)
		int checker = cc.insertContact(contact);

//		30 : 연락처 기입 형식 오류
		if (checker == 30) {
			request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/insertContactForm.jsp");
			disp.forward(request, response);
		}
//		40 : 그룹 기입 형식 오류
		if (checker == 40) {
			request.setAttribute("msg", "그룹을 1~3 사이로 지정해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/insertContactForm.jsp");
			disp.forward(request, response);
		}
//		1 : 연락처 등록 성공
		if (checker == 1) {
			response.sendRedirect("MainServlet");
		}

	}

}
