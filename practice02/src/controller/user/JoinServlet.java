package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.user.UserService;
import vo.user.UserVO;

/**
 * Servlet implementation class JoinServlet
 */
/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : JoinServlet.java
 * @package : controller.user
 * @description : 회원가입을 하는 서블릿입니다.
 */
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinServlet() {
		super();
	}

//	main 에서 회원가입 버튼을 누르면 회원가입 폼 홈페이지로 보내줍니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("jsp/joinForm.jsp");
	}

//	회원가입 폼에서 기입된 정보를 이용하여 DB에 유저를 추가하는 작업을 수행합니다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

//	doPost 안에 행할 작업들을 갖고 있는 함수입니다.
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		utf-8로 인코딩을 해줍니다.
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
//		유저 컨트롤러 객체를 생성합니다.
		UserController uc = new UserController();
		
//		기입된 각각의 정보를 각각의 변수에 담습니다.
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String gender = request.getParameter("gender");

//		입력칸에 아무것도 없을 경우 에러를 출력하고 다시 회원가입 폼으로 되돌려보냅니다.
		if (name.equals("") || id.equals("") || pw.equals("") || phone2.equals("") || phone3.equals("")
				|| gender.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
			disp.forward(request, response);
		} else {
//			모든 입력칸이 잘 기입됬을 경우
			int rowcnt 	= 0;
//			유저 객체를 생성하고 id를 이용해 해당 id를 갖고 있는 회원을 그 객체에 담아줍니다.
			UserVO user = new UserVO();
			user 		= uc.searchById(id);
//			회원의 이름이 null이 아닐 경우 (해당 유저가 DB에 존재할 경우)
			if (user.getName() != null) {
//				기존에 있는 아이디임을 출력하고 회원가입 폼으로 넘겨줍니다.
				request.setAttribute("msg", "기존에 있는 아이디입니다. 다른 아이디로 가입해주세요.");
				RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
				disp.forward(request, response);
			} else {

//				기존에 해당 아이디로 등록된 회원이 없을 경우 회원가입폼에 적은 정보를 유저객체에 담아줍니다.
				user.setName(name);
				user.setId(id);
				user.setPw(pw);
				user.setPhone1(phone1);
				user.setPhone2(phone2);
				user.setPhone3(phone3);
				user.setGender(gender);
				// 아이디 중복확인도 해줘야 함.

//				해당 유저를 DB에 넣어주고, 그 결과값(숫자)를 받아줍니다.
				rowcnt = uc.insertUser(user);

//				결과값에 따른 오류를 출력해줍니다. 
				if (rowcnt == 1) { // 1 : 정상 등록
					request.setAttribute("name", name);
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinResult.jsp");
					disp.forward(request, response);
				} else if (rowcnt == 10) { // 10 : 아이디 비밀번호 영문자/숫자 아닌 다른 문자 입력
					request.setAttribute("msg", "아이디를 영문자/숫자로 적어주세요.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				} else if (rowcnt == 20) { // 20 : 비밀번호를 영문자/숫자 아닌 다른 문자 입력
					request.setAttribute("msg", "비밀번호를 영문자/숫자로 적어주세요.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				} else if (rowcnt == 30) { // 30 : 연락처를 숫자 아닌 다른 문자를 입력 또는 3자리 4자리 4자리 아닌 경우
					request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				} else { // 0 또는 다른 숫자 : 그 외에 다른 오류
					request.setAttribute("msg", "모든 항목을 입력해주셔야 합니다.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				}
			}
		}
	}
}
