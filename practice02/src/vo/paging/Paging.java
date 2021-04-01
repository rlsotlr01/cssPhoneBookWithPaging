package vo.paging;

import dao.contact.ContactDAO;
import dao.user.UserDAO;


/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : Paging.java
 * @package : vo.paging
 * @description : 페이징 처리를 위한 페이징vo 클래스입니다. 
 */
public class Paging {
	public final static int pageSize = 5;

	public static int getPagesize() {
		return pageSize;
	}
	
}
