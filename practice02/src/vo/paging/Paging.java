package vo.paging;

import dao.contact.ContactDAO;
import dao.user.UserDAO;

public class Paging {
	public final static int postEachPage = 10;
	private int postStartNum = 0;
	private int postLastNum = 0;
	private int lastPageNum = 0;
	
	public int getPostStartNum() {
		return postStartNum;
	}
	public void setPostStartNum(int postStartNum) {
		this.postStartNum = postStartNum;
	}
	public int getPostLastNum() {
		return postLastNum;
	}
	public void setPostLastNum(int postLastNum) {
		this.postLastNum = postLastNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	public static int getPosteachpage() {
		return postEachPage;
	}
	
	public void makePostNum(int presentPage) {
		int postNum = 0;
		
		postNum = (int) Math.floor((presentPage-1)/postEachPage);
		postStartNum = (postEachPage*postNum)+1;
		postLastNum = postStartNum+(postEachPage-1);
	}

	public void makeLastPageNum_User_admin() {
		UserDAO uDAO = new UserDAO();
		int total = uDAO.getCount_admin();
		
		if(total % postEachPage == 0) {
			lastPageNum = (int)Math.floor(total/postEachPage);
		}else {
			lastPageNum = (int)Math.floor(total/postEachPage)+1;
		}
		
	}
	public void makeLastPageNum_Contact_admin() {
		ContactDAO cDAO = new ContactDAO();
		int total = cDAO.getCount_admin();
		
		if(total % postEachPage == 0) {
			lastPageNum = (int)Math.floor(total/postEachPage);
		}else {
			lastPageNum = (int)Math.floor(total/postEachPage)+1;
		}
	}
	
	public void makeLastPageNum_Contact(String id, String searchword, String keyword) {
		ContactDAO cDAO = new ContactDAO();
		int total = cDAO.getCount_KS(id, searchword, keyword);
		if(total%postEachPage == 0) {
			lastPageNum = (int) Math.floor(total/postEachPage);
		}else {
			lastPageNum = (int) Math.floor(total/postEachPage)+1;
		}
	}
}
