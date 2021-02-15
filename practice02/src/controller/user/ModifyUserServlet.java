package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.user.UserVO;

/**
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserController uc = new UserController();   
	
    public ModifyUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		로그인 된 상태이기에 id가 저장되어 있음.
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		if(id==null) {
			response.sendRedirect("MainServlet");
		}else if(id.equals("admin")) {
			id = request.getParameter("id");
			UserVO user = uc.searchById(id);
			request.setAttribute("user", user);
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
			disp.forward(request, response);
		}else {
			UserVO user = uc.searchById(id);
			request.setAttribute("user", user);
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int checker = 0;
		HttpSession session = request.getSession();
		String idcheck = (String) session.getAttribute("id");
		String id = (String) session.getAttribute("id");
		
		
		UserVO user = new UserVO();
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String pw = request.getParameter("pw");
		String gender = request.getParameter("gender");
		
		if(name.equals("")||phone1.equals("")
				||phone2.equals("")||phone3.equals("")
				||pw.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			id = request.getParameter("id");
			user = uc.searchById(id);
			request.setAttribute("user", user);
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
			disp.forward(request, response);
		}else {
			if(id.equals("admin")) {
				id = request.getParameter("id");
				user.setId(id);
			}else {
				user.setId(id);
			}
			user.setName(name);
			user.setPhone1(phone1);
			user.setPhone2(phone2);
			user.setPhone3(phone3);
			user.setPw(pw);
			user.setGender(gender);
			
			checker = uc.updateUser(user);
			
//		1. 아이디는 영문+숫자로만
//		아이디가 영어 및 숫자로 되어 있지 않으면 10 리턴
			if(checker == 10) {
				request.setAttribute("msg", "아이디는 영문/숫자로 적어주세요.");
				user = uc.searchById(id);
				request.setAttribute("user", user);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
				disp.forward(request, response);
			}
//		2. 비밀번호가 영어 및 숫자로 되어 있지 않으면 20을 리턴
			if(checker == 20) {
				request.setAttribute("msg", "비밀번호는 영문/숫자로 적어주세요.");
				user = uc.searchById(id);
				request.setAttribute("user", user);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
				disp.forward(request, response);
			}
//		3. 연락처는 3-4-4 형식
			if(checker == 30) {
				request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
				user = uc.searchById(id);
				request.setAttribute("user", user);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
				disp.forward(request, response);
			}
			if(checker == 1) {
				
				if(idcheck.equals("admin")) {
					response.sendRedirect("MainServlet");
				}else {
					response.sendRedirect("LogoutServlet");
				}
			}
			
		}
		
		
//		셋 다 문제 없으면 숫자 0 리턴
//		1번 조건에 문제 있으면 10 리턴
//		2번 조건에 문제 있으면 20 리턴
//		3번 조건에 문제 있으면 30 리턴
	}

}
