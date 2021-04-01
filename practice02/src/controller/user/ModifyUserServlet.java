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
/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : ModifyUserServlet.java
 * @package : controller.user
 * @description : 회원을 수정해주는 서블릿입니다.
 */
@WebServlet("/ModifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserController uc = new UserController();

	public ModifyUserServlet() {
		super();
	}

//	doGet : 메인화면에서 회원정보 수정을 눌렀을 때 처리해줍니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		로그인 된 상태인지 세션에서 id값을 가져와 확인한다.
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
//		id가 없을 경우 -> 로그인 되지 않은 상황
		if (id == null) {
			response.sendRedirect("MainServlet");
		} else if (id.equals("admin")) { // 아이디가 운영자일 경우
//			id값을 request 에서 가져와 해당 유저를 DB에서 검색하여 객체에 담아줍니다.
			id = request.getParameter("id"); // 운영자일 경우 request 에서 id를 가져와야 합니다. (session id는 운영자)
			UserVO user = uc.searchById(id);
//			유저 객체를 request 에 "user"라는 이름으로 담아줍니다.
			request.setAttribute("user", user);
//			그리고 수정 폼으로 보내줍니다.
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
			disp.forward(request, response);
		} else { // 아이디가 운영자가 아닐 경우
//			세션에 있는 아이디로 유저를 찾아 객체에 담아줍니다.
			UserVO user = uc.searchById(id);
//			해당 유저 객체를 request에 "user"라는 이름으로 담아줍니다.
			request.setAttribute("user", user);
//			회원정보 수정 폼으로 보내줍니다.
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
			disp.forward(request, response);
		}
	}

//	doPost : 회원정보 수정폼에서 가져온 정보를 처리합니다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request 의 인코딩을 utf-8로 맞춰줍니다.
		request.setCharacterEncoding("utf-8");
		int checker = 0;
		
//		세션에서 id값을 가져옵니다. 그리고 id를 체크하기 위한 값을 따로 빼냅니다.
		HttpSession session = request.getSession();
		String idcheck = (String) session.getAttribute("id");
		String id = (String) session.getAttribute("id");

//		유저객체를 만들고, request 에서 가져온 정보를 각각의 변수에 담습니다.
		UserVO user = new UserVO();
		String name = request.getParameter("name");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String pw = request.getParameter("pw");
		String gender = request.getParameter("gender");

//		아무것도 기입하지 않았을 경우 오류문을 출력하고 수정폼으로 다시 보냅니다.
		if (name.equals("") || phone1.equals("") || phone2.equals("") || phone3.equals("") || pw.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			id = request.getParameter("id");
			user = uc.searchById(id);
			request.setAttribute("user", user);
			RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
			disp.forward(request, response);
		} else {
//			정상적으로 기입됬을 경우
			if (id.equals("admin")) {
//				아이디가 운영자일 경우 request 에서 id를 다시 가져오고
				id = request.getParameter("id");
//				객체에 request 에 저장된 id를 담습니다.
				user.setId(id);
			} else {
//				그렇지 않을 경우 session 에 등록된 id를 그대로 담습니다.
				user.setId(id);
			}
			
//			해당 정보를 user 에 담아줍니다.
			user.setName(name);
			user.setPhone1(phone1);
			user.setPhone2(phone2);
			user.setPhone3(phone3);
			user.setPw(pw);
			user.setGender(gender);

//			그리고 DB에 회원정보를 수정해줍니다.
			checker = uc.updateUser(user);

//		1. 아이디는 영문+숫자로만
//		아이디가 영어 및 숫자로 되어 있지 않으면 10 리턴
			if (checker == 10) {
				request.setAttribute("msg", "아이디는 영문/숫자로 적어주세요.");
				user = uc.searchById(id);
				request.setAttribute("user", user);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
				disp.forward(request, response);
			}
//		2. 비밀번호가 영어 및 숫자로 되어 있지 않으면 20을 리턴
			if (checker == 20) {
				request.setAttribute("msg", "비밀번호는 영문/숫자로 적어주세요.");
				user = uc.searchById(id);
				request.setAttribute("user", user);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
				disp.forward(request, response);
			}
//		3. 연락처는 3-4-4 형식
			if (checker == 30) {
				request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
				user = uc.searchById(id);
				request.setAttribute("user", user);
				RequestDispatcher disp = request.getRequestDispatcher("jsp/modifyUserForm.jsp");
				disp.forward(request, response);
			}
			if (checker == 1) {

				if (idcheck.equals("admin")) {
					response.sendRedirect("jsp/adminmain_user.jsp");
				} else {
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
