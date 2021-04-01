package service.contact;

import java.util.ArrayList;

import dao.contact.ContactDAO;
import service.Checker;
import vo.contact.ContactVO;


/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : ContactService.java
 * @package : service.contact
 * @description : 연락처와 관련된 기능들을 제어하는 Controller 와 DAO 를 이어주고, 조건처리를 해주는 연락처 Service 클래스 입니다.
 */
public class ContactService {
	ContactDAO cDAO = new ContactDAO();
	Checker checker = new Checker();

//	해당 id를 가진 연락처들 중 해당 인덱스에 맞는 것만 뽑아서 목록을 리턴해주는 기능.
	public ArrayList<ContactVO> searchAll(String id, int startRow, int endRow) {
		ArrayList<ContactVO> contactlist = cDAO.searchAll(id, startRow, endRow);
		return contactlist;
	}

//	해당 id를 가진 연락처들 중 해당 인덱스에 맞는 것만 뽑아서 목록을 리턴해주는 기능. (운영자 전용)
	public ArrayList<ContactVO> searchAll_admin(int startRow, int endRow) {
		ArrayList<ContactVO> contactlist = cDAO.searchAll_admin(startRow, endRow);
		return contactlist;
	}

//	연락처 고유번호(contactnum)을 이용하여 연락처를 리턴하는 기능.
	public ContactVO searchByContactnum(String contactnum) {
		ContactVO contact = cDAO.searchByContactnum(contactnum);
		return contact;
	}

//	연락처를 수정해주는 기능.
	public int updateContact(ContactVO contact) {
		int rowcnt = 0;
//		연락처 형식이 잘못될 땐 30을 리턴한다.
		if (!checker.checkphone(contact.getPhone1(), contact.getPhone2(), contact.getPhone3())) {
			System.out.println("연락처를 올바르게 기입해주세요. ContactService");
			return 30;
		}
//		그룹이 1~3 이 아닐 경우는 40을 리턴한다.
		if (!checker.checkgroupno(contact.getGroupno())) {
			System.out.println("그룹은 1~3 사이로 입력해주세요. ContactService");
			return 40;
		}
//		정상 처리일 경우 rowcnt 는 1이 된다.
		rowcnt = cDAO.updateContact(contact);
		return rowcnt;
	}

//	연락처 고유번호를 이용해 연락처를 삭제해주는 기능.
	public void deleteContact(String contactnum) {
		cDAO.deleteContact(contactnum);
	}

//	연락처를 DB에 넣어주는 기능.
	public int insertContact(ContactVO contact) {
		int rowcnt = 0;
//		연락처 형식이 잘못될 땐 30을 리턴한다.
		if (!checker.checkphone(contact.getPhone1(), contact.getPhone2(), contact.getPhone3())) {
			System.out.println("연락처를 올바르게 기입해주세요. ContactService");
			return 30;
		}
//		그룹이 1~3 이 아닐 경우는 40을 리턴한다.
		if (!checker.checkgroupno(contact.getGroupno())) {
			System.out.println("그룹은 1~3 사이로 입력해주세요. ContactService");
			return 40;
		}
//		정상 처리일 경우 rowcnt 는 1이 된다.
		rowcnt = cDAO.insertContact(contact);
		return rowcnt;
	}

//	해당 카테고리(연락처, 이름 등)에 해당 단어를 포함하는 연락처들의 목록을 인덱스에 맞게 가져오는 기능.
	public ArrayList<ContactVO> searchByKS(String keyword, String searchword, String id, int startRow, int endRow) {
		ArrayList<ContactVO> contactlist = cDAO.searchByKS(keyword, searchword, id, startRow, endRow);
		return contactlist;
	}

//	해당 카테고리(연락처, 이름 등)에 해당 단어를 포함하는 연락처들의 목록을 인덱스에 맞게 가져오는 기능. (운영자 전용)
	public ArrayList<ContactVO> searchByKS_admin(String keyword, String searchword, int startRow, int endRow) {
		ArrayList<ContactVO> contactlist = cDAO.searchByKS_admin(keyword, searchword, startRow, endRow);
		return contactlist;
	}

//	해당 카테고리(연락처, 이름 등)에 해당 단어를 포함하는 연락처들의 목록을 가져오는 기능. (여분 기능)
	public ArrayList<ContactVO> searchByKS(String keyword, String searchword) {
		ArrayList<ContactVO> contactlist = cDAO.searchByKS(keyword, searchword);
		return contactlist;
	}

//	해당 카테고리(연락처, 이름 등)에 해당 단어를 포함하는 연락처들의 수를 리턴하는 기능.
	public int getCount_KS(String id, String searchword, String keyword) {
		int count = cDAO.getCount_KS(id, searchword, keyword);
		return count;
	}

//	해당 카테고리(연락처, 이름 등)에 해당 단어를 포함하는 연락처들의 수를 리턴하는 기능. (운영자 전용)
	public int getCount_KS_admin(String searchword, String keyword) {
		int count = cDAO.getCount_KS_admin(searchword, keyword);
		return count;
	}

//	해당 id에 저장된 연락처들의 수를 리턴하는 기능.
	public int getCount_id(String id) {
		int count = cDAO.getCount_id(id);
		return count;
	}

//	모든 연락처들의 수를 리턴하는 기능. (운영자 전용)
	public int getCount_admin() {
		int count = cDAO.getCount_admin();
		return count;
	}

}
