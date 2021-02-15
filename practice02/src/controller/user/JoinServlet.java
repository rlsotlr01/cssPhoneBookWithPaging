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
@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("jsp/joinForm.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request,response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		UserController uc = new UserController();
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String gender = request.getParameter("gender");
		
		if(name.equals("")||id.equals("")
				||pw.equals("")||phone2.equals("")
				||phone3.equals("")||gender.equals("")) {
			request.setAttribute("msg", "모든 항목을 기입해주세요.");
			RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
			disp.forward(request, response);
		}else {
		
			int rowcnt = 0;
			UserVO user = new UserVO();
			user = uc.searchById(id);
			if(user.getName() != null) {
				request.setAttribute("msg", "기존에 있는 아이디입니다. 다른 아이디로 가입해주세요.");
				RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
				disp.forward(request, response);
			}else {
		
				//		name,id,pw,phone1,phone2,phone3,gender
				user.setName(name);
				user.setId(id);
				user.setPw(pw);
				user.setPhone1(phone1);
				user.setPhone2(phone2);
				user.setPhone3(phone3);
				user.setGender(gender);
		//		아이디 중복확인도 해줘야 함.
				
				rowcnt = uc.insertUser(user);
				
				
				if(rowcnt==1) {
					request.setAttribute("name", name);
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinResult.jsp");
					disp.forward(request, response);
				}else if(rowcnt == 10) { // 아이디 비밀번호가 공백일땐?
					request.setAttribute("msg", "아이디를 영문자/숫자로 적어주세요.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				}else if(rowcnt == 20) {
					request.setAttribute("msg", "비밀번호를 영문자/숫자로 적어주세요.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				}else if(rowcnt == 30) {
					request.setAttribute("msg", "연락처를 올바르게 기입해주세요.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				}else {
					request.setAttribute("msg", "모든 항목을 입력해주셔야 합니다.");
					RequestDispatcher disp = request.getRequestDispatcher("jsp/joinForm.jsp");
					disp.forward(request, response);
				}
			}
		}
	}
}
