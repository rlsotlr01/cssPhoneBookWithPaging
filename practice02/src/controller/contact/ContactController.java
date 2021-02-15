package controller.contact;

import java.util.ArrayList;

import dao.contact.ContactDAO;
import service.Checker;
import service.contact.ContactService;
import vo.contact.ContactVO;

public class ContactController {
	ContactService cService = new ContactService();
	
	
//	DAO를 이용해서 해당 id에 저장된 연락처를 모두 가져온다.
	public ArrayList<ContactVO> searchAll(String id){
		ArrayList<ContactVO> contacts = new ArrayList<ContactVO>();
		contacts = cService.searchAll(id);
		return contacts;
	}
	public ArrayList<ContactVO> searchAll(){
		ArrayList<ContactVO> contacts = new ArrayList<ContactVO>();
		contacts = cService.searchAll();
		return contacts;
	}
	
	
	public ContactVO searchByContactnum(String contactnum) {
		ContactVO contact = null;
		contact = cService.searchByContactnum(contactnum);
		return contact;
	}

	public int updateContact(ContactVO contact) {
		int rowcnt = cService.updateContact(contact);
		
		return rowcnt;
		
	}

	public void deleteContact(String contactnum) {
		cService.deleteContact(contactnum);
	}

	public int insertContact(ContactVO contact) {
		int rowcnt = cService.insertContact(contact);
		return rowcnt;
	}

	public ArrayList<ContactVO> searchByKS(String keyword, String searchword, String id) {
		ArrayList<ContactVO> contactlist 
					= cService.searchByKS(keyword, searchword, id);
		return contactlist;
	}
	
//	운영자 전용입니다.
	public ArrayList<ContactVO> searchByKS(String keyword, String searchword) {
		// TODO Auto-generated method stub
		ArrayList<ContactVO> contactlist 
			= cService.searchByKS(keyword, searchword);
		return contactlist;
	}
}
