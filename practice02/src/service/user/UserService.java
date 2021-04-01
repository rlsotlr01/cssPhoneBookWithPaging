package service.user;

import java.util.ArrayList;

import dao.user.UserDAO;
import service.Checker;
import vo.user.UserVO;


/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : UserService.java
 * @package : service.user
 * @description : 유저 컨트롤러와 DAO 사이를 연결해주고, 조건처리를 도와주는 Service 클래스 입니다.
 */
public class UserService {
	UserDAO uDAO = new UserDAO();
	Checker checker = new Checker();

//	회원을 넣어주는 기능.
	public int insertUser(UserVO user) {
		int rowcnt = 0;
//		각각의 요소들을 Check 할 것임.
//		1. 아이디는 영문+숫자로만
//		아이디가 영어 및 숫자로 되어 있지 않으면 10 리턴
		if (!checker.checkIdPw(user.id)) {
			return 10;
		}

//		2. 비밀번호가 영어 및 숫자로 되어 있지 않으면 20을 리턴
		if (!checker.checkIdPw(user.pw)) {
			return 20;
		}

//		3. 연락처는 3-4-4 형식
		if (!checker.checkphone(user.phone1, user.phone2, user.phone3)) {
			return 30;
		}

//		셋 다 문제 없으면 숫자 0 리턴
//		1번 조건에 문제 있으면 10 리턴
//		2번 조건에 문제 있으면 20 리턴
//		3번 조건에 문제 있으면 30 리턴

		rowcnt = uDAO.insertUser(user);
		return rowcnt;
	}

//	모든 회원 목록을 리턴하는 기능(여분)
	public ArrayList<UserVO> searchAll() {
		ArrayList<UserVO> userlist = uDAO.searchAll();
		return userlist;
	}

//	모든 회원 목록을 인덱스에 맞게 리턴하는 기능(여분)
	public ArrayList<UserVO> searchAll(int startRow, int endRow) {
		ArrayList<UserVO> userlist = uDAO.searchAll(startRow, endRow);
		return userlist;
	}

//	해당 아이디 비밀번호에 맞는 회원을 찾는 기능. (로그인용)
	public UserVO searchByIdPw(String id, String pw) {
		UserVO user = null;
		user = uDAO.searchByIdPw(id, pw);

		return user;
	}
	
//	해당 id를 가진 회원을 가져오는 기능.
	public UserVO searchById(String id) {
		UserVO user = null;
		user = uDAO.searchById(id);

		return user;
	}

//	회원정보 수정해주는 기능
	public int updataUser(UserVO user) {
		int rowcnt = 0;
//		1. 아이디는 영문+숫자로만
//		아이디가 영어 및 숫자로 되어 있지 않으면 10 리턴
		if (!checker.checkIdPw(user.id)) {
			return 10;
		}

//		2. 비밀번호가 영어 및 숫자로 되어 있지 않으면 20을 리턴
		if (!checker.checkIdPw(user.pw)) {
			return 20;
		}

//		3. 연락처는 3-4-4 형식
		if (!checker.checkphone(user.phone1, user.phone2, user.phone3)) {
			return 30;
		}

		rowcnt = uDAO.updateUser(user);
//		셋 다 문제 없으면 숫자 0 리턴
//		1번 조건에 문제 있으면 10 리턴
//		2번 조건에 문제 있으면 20 리턴
//		3번 조건에 문제 있으면 30 리턴
		return rowcnt;
	}

//	회원을 삭제해주는 기능
	public void deleteUser(String id) {
		uDAO.deleteUser(id);
	}

//	해당 카테고리에 해당 검색어를 가진 유저를 출력하는 기능 (여분기능)
	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword) {
		ArrayList<UserVO> userlist = uDAO.searchAll_KS(keyword, searchword);
		return userlist;
	}

//	해당 카테고리에 해당 검색어를 가진 유저를 인덱스에 맞게 출력하는 기능
	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword, int startRow, int endRow) {
		ArrayList<UserVO> userlist = uDAO.searchAll_KS(keyword, searchword, startRow, endRow);
		return userlist;
	}

//	모든 유저의 수를 가져오는 기능.
	public int getCount_admin() {
		int count = uDAO.getCount_admin();
		return count;
	}

//	해당 검색 조건에 맞는 유저의 수를 가져오는 기능.
	public int getCount_KS_admin(String searchword, String keyword) {
		int count = uDAO.getCount_KS_admin(searchword, keyword);
		return count;
	}

}
