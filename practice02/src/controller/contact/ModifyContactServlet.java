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
@WebServlet("/ModifyContactServlet")
public class ModifyContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ContactController cc = new ContactController();   
	
    public ModifyContactServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		이걸로 넘어옴. ?contactnum=${contactnum}
//		문자로 들어온걸 숫자로 변경해주고,
		String contactnum = request.getParameter("contactnum");
//		ContactController:searchByContactnum 함수를 사용해서 
//		해당 contactnum의 contact를 추출해냄.
//		System.out.println(contactnum); 잘 가져옴.
		ContactVO contact = cc.searchByContactnum(contactnum);
//		그 contact를 request에 setParam 해서 contact에 넣고
		request.setAttribute("contact", contact);
		HttpSession session = request.getSession();
		session.setAttribute("contactnum", contactnum);
//		modifyContactForm 으로 보낸다.
		RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		수정하고자 하는 정보들을 받아들여 ContactController:updateContact 함수 이용해서
//		DB에 update 한다..
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
//		이거 하기 전에 modifyContactForm 에 group select로 1~3 지정해줘야함.
		
		if(name.equals("")||phone2.equals("")
				||phone3.equals("")||address.equals("")
				||groupno.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			contact = cc.searchByContactnum(contactnum);
			request.setAttribute("contact", contact);
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
			disp.forward(request, response);
		}else {
			contact.setName(name);
			contact.setPhone1(phone1);
			contact.setPhone2(phone2);
			contact.setPhone3(phone3);
			contact.setAddress(address);
			contact.setGroupno(groupno);
			contact.setContactnum(contactnum);
			
//		아무것도 기입하지 않았을 시
			
			checker = cc.updateContact(contact);
//		그다음 MainServlet 으로 다시 보낸다. - 이것도 포워딩 해야되나? 필요 없을것 같은데?
//		RequestDispatcher disp = request.getRequestDispatcher("MainServlet");
//		disp.forward(request, response);
			
			
			System.out.println("ModifyContactService checker : "+checker);
//		30 : 연락처 기입 형식 오류
			if(checker == 30) {
				request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
				contact = cc.searchByContactnum(contactnum);
				request.setAttribute("contact", contact);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
				disp.forward(request, response);
			}
//		40 : 그룹 기입 형식 오류
			if(checker == 40) {
				request.setAttribute("msg", "그룹을 1~3 사이로 지정해주세요.");
				contact = cc.searchByContactnum(contactnum);
				request.setAttribute("contact", contact);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyContactForm.jsp");
				disp.forward(request, response);
			}
			if(checker == 1) {
				response.sendRedirect("MainServlet");
			}
		}
		
		
	}

}
