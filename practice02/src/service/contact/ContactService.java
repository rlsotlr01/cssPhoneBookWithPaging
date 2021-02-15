package service.contact;

import java.util.ArrayList;

import dao.contact.ContactDAO;
import service.Checker;
import vo.contact.ContactVO;

public class ContactService {
	ContactDAO cDAO = new ContactDAO();
	Checker checker = new Checker();
	
	public ArrayList<ContactVO> searchAll(String id) {
		ArrayList<ContactVO> contactlist = cDAO.searchAll(id);
		return contactlist;
	}
	
	public ArrayList<ContactVO> searchAll() {
		ArrayList<ContactVO> contactlist = cDAO.searchAll();
		return contactlist;
	}

	public ContactVO searchByContactnum(String contactnum) {
		ContactVO contact = cDAO.searchByContactnum(contactnum);
		return contact;
	}

	public int updateContact(ContactVO contact) {
		int rowcnt = 0;
//		연락처 형식이 잘못될 땐 30을 리턴한다.
		if(!checker.checkphone(contact.getPhone1(),contact.getPhone2(),contact.getPhone3())) {
			System.out.println("연락처를 올바르게 기입해주세요. ContactService");
			return 30;
		}
//		그룹이 1~3 이 아닐 경우는 40을 리턴한다.
		if(!checker.checkgroupno(contact.getGroupno())) {
			System.out.println("그룹은 1~3 사이로 입력해주세요. ContactService");
			return 40;
		}
//		정상 처리일 경우 rowcnt 는 1이 된다.
		rowcnt = cDAO.updateContact(contact);
		return rowcnt;
	}

	public void deleteContact(String contactnum) {
		cDAO.deleteContact(contactnum);
	}

	public int insertContact(ContactVO contact) {
		int rowcnt = 0;
//		연락처 형식이 잘못될 땐 30을 리턴한다.
		if(!checker.checkphone(contact.getPhone1(),contact.getPhone2(),contact.getPhone3())) {
			System.out.println("연락처를 올바르게 기입해주세요. ContactService");
			return 30;
		}
//		그룹이 1~3 이 아닐 경우는 40을 리턴한다.
		if(!checker.checkgroupno(contact.getGroupno())) {
			System.out.println("그룹은 1~3 사이로 입력해주세요. ContactService");
			return 40;
		}
//		정상 처리일 경우 rowcnt 는 1이 된다.
		rowcnt = cDAO.insertContact(contact);
		return rowcnt;
	}

	public ArrayList<ContactVO> searchByKS(String keyword, String searchword, String id) {
		ArrayList<ContactVO> contactlist = cDAO.searchByKS(keyword,searchword, id);
		return contactlist;
	}

	public ArrayList<ContactVO> searchByKS(String keyword, String searchword) {
		ArrayList<ContactVO> contactlist = cDAO.searchByKS(keyword,searchword);
		return contactlist;
	}

}
