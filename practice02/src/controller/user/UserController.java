package controller.user;

import java.util.ArrayList;

import service.user.UserService;
import vo.user.UserVO;

/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : UserController.java
 * @package : controller.user
 * @description : 회원과 관련된 모든 기능을 제어해주는 컨트롤러입니다.
 */
public class UserController {
	UserService us = new UserService();

//	아이디와 비밀번호를 받아 유저를 DB에서 찾아옵니다. (로그인시 사용)
	public UserVO searchByIdPw(String id, String pw) {
		UserVO user = us.searchByIdPw(id, pw);
		return user;
	}
	
//	아이디를 받아 DB에서 유저를 찾아옵니다. (아이디 중복체크)
	public UserVO searchById(String id) {
		UserVO user = us.searchById(id);
		return user;
	}

//	회원을 DB에 넣는 기능
	public int insertUser(UserVO user) {
		int rowcnt = 0;
		rowcnt = us.insertUser(user);
		return rowcnt;
	}

//	회원의 목록을 모두 가져와주는 기능 (여분)
	public ArrayList<UserVO> searchAll() {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		userlist = us.searchAll();
		return userlist;
	}

//	해당 인덱스에 속하는 회원의 목록을 모두 가져와주는 기능. (운영자)
	public ArrayList<UserVO> searchAll(int startRow, int endRow) {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		userlist = us.searchAll(startRow, endRow);
		return userlist;
	}

//	유저의 정보를 DB에 수정해주는 기능.
	public int updateUser(UserVO user) {
		int rowcnt = us.updataUser(user);
		return rowcnt;
	}

//	특정 id에 해당하는 유저를 DB에서 지워주는 기능.
	public void deleteUser(String id) {
		us.deleteUser(id);
	}

//	카테고리(이름,연락처,주소 등)에 해당 단어(searchword)가 있는 유저들을 모두 검색해 가져오는 기능 (여분)
	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword) {
		ArrayList<UserVO> userlist = us.searchAll_KS(keyword, searchword);
		return userlist;
	}

//	카테고리(이름,연락처,주소 등)에 해당 단어(searchword)가 있는 유저들을 모두 검색해 인덱스에 맞게 가져오는 기능 
	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword, int startRow, int endRow) {
		ArrayList<UserVO> userlist = us.searchAll_KS(keyword, searchword, startRow, endRow);
		return userlist;
	}

//	모든 회원 수를 가져오는 기능 (운영자 전용)
	public int getCount_admin() {
		int count = us.getCount_admin();
		return count;
	}

//	검색한 조건에 해당하는 회원들의 수를 가져오는 기능 (운영자 전용)
	public int getCount_KS_admin(String searchword, String keyword) {
		int count = us.getCount_KS_admin(searchword, keyword);
		return count;
	}

}
