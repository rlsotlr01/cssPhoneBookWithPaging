package controller.user;

import java.util.ArrayList;

import service.user.UserService;
import vo.user.UserVO;

public class UserController {
	UserService us = new UserService();
	
	public UserVO searchByIdPw(String id, String pw) {
		UserVO user = us.searchByIdPw(id,pw);
		return user;
	}
	
	public UserVO searchById(String id) {
		UserVO user = us.searchById(id);
		return user;
	}
	
	public int insertUser(UserVO user) {
		int rowcnt = 0;
		rowcnt = us.insertUser(user);
		return rowcnt;
	}
	
	public ArrayList<UserVO> searchAll() {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		userlist = us.searchAll();
		return userlist;
	}
	
	public ArrayList<UserVO> searchall(String id, String pw) {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
//		id랑 pw를 check하고, - login
//		멤버에 이 해당 멤버 있으면 그대로 진행.
//		전화번호부에 한개라도 있으면 ArrayList에 담아서 return
//		하나도 없으면 isEmpty 기능 써서 있는지 없는지 확인
		
		return userlist;
	}

	public int updateUser(UserVO user) {
		int rowcnt = us.updataUser(user);
		return rowcnt;
	}

	public void deleteUser(String id) {
		us.deleteUser(id);
	}

	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword) {
		ArrayList<UserVO> userlist = us.searchAll_KS(keyword, searchword);
		return userlist;
	}


	
}
