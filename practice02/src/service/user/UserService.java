package service.user;

import java.util.ArrayList;

import dao.user.UserDAO;
import service.Checker;
import vo.user.UserVO;

public class UserService {
	UserDAO uDAO = new UserDAO();
	Checker checker = new Checker();
	public int insertUser(UserVO user) {
		int rowcnt = 0;
//		각각의 요소들을 Check 할 것임.
//		1. 아이디는 영문+숫자로만
//		아이디가 영어 및 숫자로 되어 있지 않으면 10 리턴
		if(!checker.checkIdPw(user.id)) {
			return 10;
		}
		
//		2. 비밀번호가 영어 및 숫자로 되어 있지 않으면 20을 리턴
		if(!checker.checkIdPw(user.pw)) {
			return 20;
		}
		
//		3. 연락처는 3-4-4 형식
		if(!checker.checkphone(user.phone1, user.phone2, user.phone3)) {
			return 30;
		}
		
//		셋 다 문제 없으면 숫자 0 리턴
//		1번 조건에 문제 있으면 10 리턴
//		2번 조건에 문제 있으면 20 리턴
//		3번 조건에 문제 있으면 30 리턴
		
		
		rowcnt = uDAO.insertUser(user);
		return rowcnt;
	}
	
	public ArrayList<UserVO> searchAll() {
		ArrayList<UserVO> userlist = uDAO.searchAll();
		return userlist;
	}
	
	public UserVO searchByIdPw(String id, String pw) {
		UserVO user = null;
		user = uDAO.searchByIdPw(id,pw);
		
		return user;
	}
	
	public UserVO searchById(String id) {
		UserVO user = null;
		user = uDAO.searchById(id);
		
		return user;
	}
	public int updataUser(UserVO user) {
		int rowcnt = 0;
//		1. 아이디는 영문+숫자로만
//		아이디가 영어 및 숫자로 되어 있지 않으면 10 리턴
		if(!checker.checkIdPw(user.id)) {
			return 10;
		}
		
//		2. 비밀번호가 영어 및 숫자로 되어 있지 않으면 20을 리턴
		if(!checker.checkIdPw(user.pw)) {
			return 20;
		}
		
//		3. 연락처는 3-4-4 형식
		if(!checker.checkphone(user.phone1, user.phone2, user.phone3)) {
			return 30;
		}
		
		rowcnt = uDAO.updateUser(user);
//		셋 다 문제 없으면 숫자 0 리턴
//		1번 조건에 문제 있으면 10 리턴
//		2번 조건에 문제 있으면 20 리턴
//		3번 조건에 문제 있으면 30 리턴
		return rowcnt;
	}

	public void deleteUser(String id) {
		uDAO.deleteUser(id);
	}

	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword) {
		ArrayList<UserVO> userlist = uDAO.searchAll_KS(keyword, searchword);
		return userlist;
	}

	
}
