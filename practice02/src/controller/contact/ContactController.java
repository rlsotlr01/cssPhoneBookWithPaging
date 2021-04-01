package controller.contact;

import java.util.ArrayList;

import dao.contact.ContactDAO;
import service.Checker;
import service.contact.ContactService;
import vo.contact.ContactVO;

/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : ContactController.java
 * @package : controller.contact
 * @description : 연락처와 관련된 모든 기능을 제어하는 컨트롤러입니다.
 */
public class ContactController {
	ContactService cService = new ContactService();

//	DAO를 이용해서 해당 id에 저장된 연락처를 인덱스 값에 맞도록 가져오는 기능.
	public ArrayList<ContactVO> searchAll(String id, int startRow, int endRow) {
		ArrayList<ContactVO> contacts = new ArrayList<ContactVO>();
		contacts = cService.searchAll(id, startRow, endRow);
		return contacts;
	}

//	DAO를 이용해서 서버에 저장된 연락처를 인덱스 값에 맞도록 가져오는 기능. (운영자 전용)
	public ArrayList<ContactVO> searchAll_admin(int startRow, int endRow) {
		ArrayList<ContactVO> contacts = new ArrayList<ContactVO>();
		contacts = cService.searchAll_admin(startRow, endRow);
		return contacts;
	}

//	연락처에 등록된 고유번호를 통해 연락처를 가져오는 기능.
	public ContactVO searchByContactnum(String contactnum) {
		ContactVO contact = null;
		contact = cService.searchByContactnum(contactnum);
		return contact;
	}

//	하나의 연락처에 대한 정보를 수정하는 기능.
	public int updateContact(ContactVO contact) {
		int rowcnt = cService.updateContact(contact);
		return rowcnt;
	}

//	연락처에 등록된 고유번호를 이용하여 연락처를 지우는 기능.
	public void deleteContact(String contactnum) {
		cService.deleteContact(contactnum);
	}

//	연락처 하나를 추가하는 기능.
	public int insertContact(ContactVO contact) {
		int rowcnt = cService.insertContact(contact);
		return rowcnt;
	}

//	해당 카테고리(keyword-이름,연락처,주소 등)와 검색단어(searchword), 그리고 세션에 저장된 id를 통해 해당 인덱스에 속하는 연락처 목록을 가져오는 기능.
	public ArrayList<ContactVO> searchByKS(String keyword, String searchword, String id, int startRow, int endRow) {
		ArrayList<ContactVO> contactlist = cService.searchByKS(keyword, searchword, id, startRow, endRow);
		return contactlist;
	}

//	해당 카테고리(keyword-이름,연락처,주소 등)와 검색단어(searchword), 그리고 세션에 저장된 id를 통해 해당 인덱스에 속하는 연락처 목록을 가져오는 기능.(운영자 전용)
	public ArrayList<ContactVO> searchByKS_admin(String keyword, String searchword, int startRow, int endRow) {
		ArrayList<ContactVO> contactlist = cService.searchByKS_admin(keyword, searchword, startRow, endRow);
		return contactlist;
	}

//	해당 카테고리(keyword-이름,연락처,주소 등)와 검색단어(searchword), 그리고 세션에 저장된 id를 통해 연락처 목록을 가져오는 기능. (미연을 위한 여분기능)
	public ArrayList<ContactVO> searchByKS(String keyword, String searchword) {
		ArrayList<ContactVO> contactlist = cService.searchByKS(keyword, searchword);
		return contactlist;
	}

//	해당 카테고리(keyword-이름,연락처,주소 등)와 검색단어(searchword), 그리고 세션에 저장된 id를 통해 해당 조건에 맞는 연락처의 수를 가져오는 기능.
	public int getCount_KS(String id, String searchword, String keyword) {
		int count = cService.getCount_KS(id, searchword, keyword);
		return count;
	}
	
//	해당 카테고리(keyword-이름,연락처,주소 등)와 검색단어(searchword), 그리고 세션에 저장된 id를 통해 해당 조건에 맞는 연락처의 수를 가져오는 기능.(운영자 전용)
	public int getCount_KS_admin(String searchword, String keyword) {
		int count = cService.getCount_KS_admin(searchword, keyword);
		return count;
	}

//	세션에 저장된 id를 통해 해당 아이디로 저장된 연락처의 수를 가져오는 기능.
	public int getCount_id(String id) {
		int count = cService.getCount_id(id);
		return count;
	}

//	서버에 저장된 모든 연락처의 수를 가져오는 기능. (운영자 전용)
	public int getCount_admin() {
		int count = cService.getCount_admin();
		return count;
	}

}
