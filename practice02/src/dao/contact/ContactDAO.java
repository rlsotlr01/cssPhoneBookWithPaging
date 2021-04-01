package dao.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import vo.contact.ContactVO;


/**
 * @작성자 : 김동윤
 * @작성일 : 2021. 2. 18.
 * @filename : ContactDAO.java
 * @package : dao.contact
 * @description : 연락처를 DB랑 연동해주고 다양한 기능을 해주는 연락처DAO 클래스입니다.
 */
public class ContactDAO {

//	DB와 자바 사이의 커넥션을 만들어 주는 함수입니다.
	private Connection getConnection(){
		Connection con 		= null;
//		url 로는 오라클 서버까지의 경로를 지정해줍니다.
		String url 			= "jdbc:oracle:thin:@localhost:1521:xe"; 
//		오라클 서버에 가입된 유저의 아이디와 비밀번호를 지정해줍니다.
		String user 		= "ora_user"; // (열쇠)
		String password 	= "hong";
		
//		경로와 아이디 비밀번호로 커넥션을 만들어주며, SQL 예외처리를 해줍니다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);			
		}catch(Exception e) {
//			DB에 문제가 있음을 알리는 내용을 출력합니다.
//			uv.DBFail();
			e.printStackTrace();
		}
		
		return con;
	} // 이 getConnection 은 EmpDAO 에서만 쓰일 것.
	
	
	
//	con, pstmt, rs 을 한꺼번에 닫을 수 있는 함수입니다.
	private void close(Connection con
			 , PreparedStatement pstmt
			 , ResultSet rs) {
		try {
//			null 이 아닐 경우에만 닫아줍니다.
//			(nullpointException 방지)
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) { 
				con.close();
			}
		}catch(SQLException e) {
//			DB에 문제가 있음을 알리는 내용을 출력합니다. (SQLException)
//			uv.DBFail();
			e.printStackTrace();
		}
	}	

//	con과 pstmt를 닫아주는 함수입니다.
	private void close(Connection con
			, PreparedStatement pstmt) {
//		null이 아닐 경우에만 닫아줍니다.
//		(nullPointException 방지)
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) { 
				con.close();
			}
		}catch(SQLException e) {
//			DB에 문제가 있음을 알리는 내용을 출력합니다. (SQLException)
//			uv.DBFail();
			e.printStackTrace();
		}
			
	}
	
//	모든 유저를 찾아 어레이리스트에 담아주는 함수입니다.
	public ArrayList<ContactVO> searchAll(String id){
//		유저 목록을 받아들일 어레이리스트를 만들어줍니다.
		ArrayList<ContactVO> contactList 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT c.contactnum				");
		sql.append("	 , c.name					");
		sql.append("	 , c.phone1					");
		sql.append("	 , c.phone2					");
		sql.append("     , c.phone3					");
		sql.append("     , c.address				");
		sql.append("     , c.groupno				");
		sql.append("     , g.groupnm				");
		sql.append("  FROM contact c				");
		sql.append("	 , group_table g			");
		sql.append(" WHERE c.groupno = g.groupno	");
		sql.append("   AND c.id = ?					");
		
//		INSERT INTO contact 
//		values('rlsotlr02',contact_seq.nextval,'김동후','010','2255','6637','가가도 가가시 가가읍','1');
//		contact_seq.nextval 하면 자동으로 다음 인덱스 해줌.
		
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setContactnum(rs.getString("contactnum"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactList.add(contact);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactList;
	}

//	모든 유저를 찾아 어레이리스트에 담아주는 함수입니다.
	public ArrayList<ContactVO> searchAll(String id, int startRow, int endRow){
//		유저 목록을 받아들일 어레이리스트를 만들어줍니다.
		ArrayList<ContactVO> contactList 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT *				");
		sql.append("  FROM (SELECT ROW_NUMBER() OVER(ORDER BY contactnum ASC) AS rn,	");
		sql.append("	 			id, contactnum, name, 								");
		sql.append("	 			phone1, phone2, phone3,								");
		sql.append("     			phone, address,										");
		sql.append("     	 		groupno, groupnm									");
		sql.append("         FROM (select id, contactnum, name,							");
		sql.append(" 						phone1, phone2, phone3,						");
		sql.append("   						phone1 || phone2 ||phone3 as phone,			");
		sql.append("   						address, c.groupno, g.groupnm				");
		sql.append("   				FROM contact c, group_table g						");
		sql.append("   				WHERE c.groupno = g.groupno)						");
		sql.append("   		 WHERE   id = ?)											");
		sql.append("   		WHERE rn BETWEEN ? AND ?									");
		
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			pstmt.setString(1, id);
			pstmt.setString(2, String.valueOf(startRow));
			pstmt.setString(3, String.valueOf(endRow));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setContactnum(rs.getString("contactnum"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactList.add(contact);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactList;
	}
	
//	모든 유저를 찾아 어레이리스트에 담아주는 함수입니다.
	public ArrayList<ContactVO> searchAll_admin(int startRow, int endRow){
//		유저 목록을 받아들일 어레이리스트를 만들어줍니다.
		ArrayList<ContactVO> contactList 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT *				");
		sql.append("  FROM (SELECT ROW_NUMBER() OVER(ORDER BY contactnum ASC) AS rn,	");
		sql.append("	 			id, contactnum, name,								");
		sql.append("	 			phone1, phone2, phone3,								");
		sql.append("     			address, c.groupno, g.groupnm						");
		sql.append("     	  FROM contact c, group_table g								");
		sql.append("         WHERE c.groupno = g.groupno)								");
		sql.append(" WHERE	rn BETWEEN ? AND ?											");
		
		 
		  
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			pstmt.setString(1, String.valueOf(startRow));
			pstmt.setString(2, String.valueOf(endRow));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setContactnum(rs.getString("contactnum"));
				contact.setId(rs.getString("id"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactList.add(contact);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactList;
	}
	

	public ArrayList<ContactVO> searchAll() {
		ArrayList<ContactVO> contactList 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT c.contactnum				");
		sql.append("	 , c.id				");
		sql.append("	 , c.name					");
		sql.append("	 , c.phone1					");
		sql.append("	 , c.phone2					");
		sql.append("     , c.phone3					");
		sql.append("     , c.address				");
		sql.append("     , c.groupno				");
		sql.append("     , g.groupnm				");
		sql.append("  FROM contact c				");
		sql.append("	 , group_table g			");
		sql.append(" WHERE c.groupno = g.groupno	");
		
//		INSERT INTO contact 
//		values('rlsotlr02',contact_seq.nextval,'김동후','010','2255','6637','가가도 가가시 가가읍','1');
//		contact_seq.nextval 하면 자동으로 다음 인덱스 해줌.
		
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setId(rs.getString("id"));
				contact.setContactnum(rs.getString("contactnum"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactList.add(contact);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactList;
		
	}


	public ContactVO searchByContactnum(String contactnum) {
		ContactVO contact 		= new ContactVO();
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String query = "SELECT c.id					"
					 + "	 , c.name				"
					 + "	 , c.phone1				"
					 + "	 , c.phone2				"
					 + "	 , c.phone3				"
					 + "	 , c.address			"
					 + "	 , c.groupno			"
					 + "	 , g.groupnm			"
					 + "  FROM contact c			"
					 + "	 , group_table g 		"
					 + " WHERE c.groupno = g.groupno"
					 + "   AND contactnum=?		";
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, contactnum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				contact.setContactnum(contactnum);
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return contact;
	}



	public int updateContact(ContactVO contact) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowcnt = 0;
		
		// contactnum 이 null 이야.
		StringBuilder query = new StringBuilder();
		query.append("UPDATE contact			");
		query.append("   SET name 		= ?,	");
		query.append("		 phone1 	= ?,	");
		query.append("       phone2 	= ?,	");
		query.append("		 phone3 	= ?,	");
		query.append("       groupno 	= ?,	");
		query.append("       address 	= ?		");
		query.append(" WHERE contactnum = ?		");
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, contact.getName());
			pstmt.setString(2, contact.getPhone1());
			pstmt.setString(3, contact.getPhone2());
			pstmt.setString(4, contact.getPhone3());
			pstmt.setString(5, contact.getGroupno());
			pstmt.setString(6, contact.getAddress());
			pstmt.setString(7, contact.getContactnum());
			rowcnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		return rowcnt;
	}



	public void deleteContact(String contactnum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "DELETE contact WHERE contactnum = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, contactnum);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}



	public int insertContact(ContactVO contact) {
		int rowcnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO contact						");
		query.append("VALUES(?,contact_seq.nextval,?,?,?,?,?,?)	");
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, contact.getId());
			pstmt.setString(2, contact.getName());
			pstmt.setString(3, contact.getPhone1());
			pstmt.setString(4, contact.getPhone2());
			pstmt.setString(5, contact.getPhone3());
			pstmt.setString(6, contact.getAddress());
			pstmt.setString(7, contact.getGroupno());
			
			rowcnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		
		return rowcnt;
	}



	public ArrayList<ContactVO> searchByKS(String keyword, String searchword, String id) {
		ArrayList<ContactVO> contactlist 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT c.contactnum										");
		sql.append("	 , c.id												");
		sql.append("	 , c.name											");
		sql.append("	 , c.phone1											");
		sql.append("	 , c.phone2											");
		sql.append("     , c.phone3											");
		sql.append("     , phone											");
		sql.append("     , c.address										");
		sql.append("     , c.groupno										");
		sql.append("     , g.groupnm										");
		sql.append("  FROM (SELECT c.contactnum								");
		sql.append("  			 , c.id										");
		sql.append("  			 , c.name									");
		sql.append("  			 , c.phone1									");
		sql.append("  			 , c.phone2									");
		sql.append("  			 , c.phone3									");
		sql.append("  			 , c.address								");
		sql.append("  			 , c.groupno								");
		sql.append("  			 , c.phone1 || c.phone2 || c.phone3 as phone");
		sql.append("  		  FROM contact c) c								");
		sql.append("	 , group_table g									");
		sql.append(" WHERE c.groupno = g.groupno							");
		sql.append("   AND id = ?											");
		sql.append("   AND 											");
		sql.append(keyword);
		sql.append(" like ?");
		
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			pstmt.setString(1, id);
			pstmt.setString(2, "%"+searchword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setContactnum(rs.getString("contactnum"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactlist.add(contact);
			}
			return contactlist;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactlist;
	}
	

//	운영자 전용 검색 메소드 입니다. (id 무필요)
	public ArrayList<ContactVO> searchByKS(String keyword, String searchword) {
		ArrayList<ContactVO> contactlist 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT c.contactnum										");
		sql.append("	 , c.id												");
		sql.append("	 , c.name											");
		sql.append("	 , c.phone1											");
		sql.append("	 , c.phone2											");
		sql.append("     , c.phone3											");
		sql.append("     , phone											");
		sql.append("     , c.address										");
		sql.append("     , c.groupno										");
		sql.append("     , g.groupnm										");
		sql.append("  FROM (SELECT c.contactnum								");
		sql.append("  			 , c.id										");
		sql.append("  			 , c.name									");
		sql.append("  			 , c.phone1									");
		sql.append("  			 , c.phone2									");
		sql.append("  			 , c.phone3									");
		sql.append("  			 , c.address								");
		sql.append("  			 , c.groupno								");
		sql.append("  			 , c.phone1 || c.phone2 || c.phone3 as phone");
		sql.append("  		  FROM contact c) c								");
		sql.append("	 , group_table g									");
		sql.append(" WHERE c.groupno = g.groupno							");
		sql.append("   AND 													");
		sql.append(keyword);
		sql.append(" like ?");
		
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			pstmt.setString(1, "%"+searchword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setContactnum(rs.getString("contactnum"));
				contact.setId(rs.getString("id"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactlist.add(contact);
			}
			return contactlist;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactlist;
	}


//	모든 유저를 찾아 어레이리스트에 담아주는 함수입니다.
	public ArrayList<ContactVO> searchByKS(String keyword, String searchword, String id, int startRow, int endRow){
//		유저 목록을 받아들일 어레이리스트를 만들어줍니다.
		ArrayList<ContactVO> contactList 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT *				");
		sql.append("  FROM (SELECT ROW_NUMBER() OVER(ORDER BY contactnum ASC) AS rn,	");
		sql.append("	 			id, contactnum, name, 								");
		sql.append("	 			phone1, phone2, phone3,								");
		sql.append("	 			phone, address,										");
		sql.append("     			groupno, groupnm									");
		sql.append("     	 	FROM (select id, contactnum, name,						");
		sql.append("     	 			     phone1, phone2, phone3,					");
		sql.append("     	 				 phone1 || phone2 ||phone3 as phone,		");
		sql.append("     	 				 address, c.groupno, g.groupnm				");
		sql.append("     	 			FROM contact c, group_table g					");
		sql.append("          		   WHERE c.groupno = g.groupno)						");
		sql.append("   			WHERE   									 			");
		sql.append(keyword);
		sql.append(" 	like ?															");
		sql.append(" 			  AND id = ?)											");
		sql.append(" WHERE rn BETWEEN ? AND ?											");
		
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			pstmt.setString(1, "%"+searchword+"%");
			pstmt.setString(2, id);
			pstmt.setString(3, String.valueOf(startRow));
			pstmt.setString(4, String.valueOf(endRow));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setContactnum(rs.getString("contactnum"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactList.add(contact);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactList;
	}
	
	public ArrayList<ContactVO> searchByKS_admin(String keyword, String searchword, int startRow, int endRow){
//		유저 목록을 받아들일 어레이리스트를 만들어줍니다.
		ArrayList<ContactVO> contactList 	= new ArrayList<ContactVO>();
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 			= new StringBuilder();
		
//		ORACLE SQL 에서 Select 구문을 sql에 삽입합니다.
		sql.append("SELECT *				");
		sql.append("  FROM (SELECT ROW_NUMBER() OVER(ORDER BY contactnum ASC) AS rn,	");
		sql.append("	 			id, contactnum, name, 								");
		sql.append("	 			phone1, phone2, phone3,								");
		sql.append("	 			phone, address,										");
		sql.append("     			groupno, groupnm									");
		sql.append("     	 	FROM (select id, contactnum, name,						");
		sql.append("     	 			     phone1, phone2, phone3,					");
		sql.append("     	 				 phone1 || phone2 ||phone3 as phone,		");
		sql.append("     	 				 address, c.groupno, g.groupnm				");
		sql.append("     	 			FROM contact c, group_table g					");
		sql.append("          		   WHERE c.groupno = g.groupno)						");
		sql.append("   			WHERE   									 			");
		sql.append(keyword);
		sql.append(" 	like ?)															");
		sql.append(" WHERE rn BETWEEN ? AND ?											");
		
		try {
//			prepareStatement 의 파라미터로는 String 만 받을 수 있으므로,
//			StringBuilder -> String 으로 변환해줍니다.
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
//			그 sql을 실행시켜 결과를 받아들이고 이를 rs에 담습니다.
			pstmt.setString(1, "%"+searchword+"%");
			pstmt.setString(2, String.valueOf(startRow));
			pstmt.setString(3, String.valueOf(endRow));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
//				유저 객체를 생성하고, 그 안에 rs가 받은 Setting 합니다.
				ContactVO contact 		= new ContactVO();
				contact.setContactnum(rs.getString("contactnum"));
				contact.setId(rs.getString("id"));
				contact.setName(rs.getString("name"));
				contact.setPhone1(rs.getString("phone1"));
				contact.setPhone2(rs.getString("phone2"));
				contact.setPhone3(rs.getString("phone3"));
				contact.setAddress(rs.getString("address"));
				contact.setGroupno(rs.getString("groupno"));
				contact.setGroupnm(rs.getString("groupnm"));
//				그리고 그 유저 객체를 목록에 추가합니다.
				contactList.add(contact);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
//		결과적으로 만들어진 연락처 유저목록을 반환합니다.
		return contactList;
	}

	

//	운영자 아이디로 로그인할 때 모든 연락처의 수를 세어주는 함수입니다.
	public int getCount_admin() {
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder query 		= new StringBuilder();
		int count = 0;
		
		query.append("SELECT count(*)	FROM contact");
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return count;
	}
	
//	일반 유저 아이디로 로그인할 때 모든 연락처의 수를 세어주는 함수입니다.
	public int getCount_id(String id) {
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder query 		= new StringBuilder();
		int count = 0;
		
		query.append("SELECT count(*) FROM contact WHERE id = ?");
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return count;
	}

//	운영자 아이디로 로그인하여 검색할 때 모든 연락처의 수를 세어주는 함수입니다.
	public int getCount_KS_admin(String searchword, String keyword) {
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 		= new StringBuilder();
		int count = 0;
		
		sql.append("SELECT count(*)										");
		sql.append("  FROM (SELECT c.contactnum								");
		sql.append("  			 , c.id										");
		sql.append("  			 , c.name									");
		sql.append("  			 , c.phone1									");
		sql.append("  			 , c.phone2									");
		sql.append("  			 , c.phone3									");
		sql.append("  			 , c.address								");
		sql.append("  			 , c.groupno								");
		sql.append("  			 , c.phone1 || c.phone2 || c.phone3 as phone");
		sql.append("  		  FROM contact c) c								");
		sql.append("	 , group_table g									");
		sql.append(" WHERE c.groupno = g.groupno							");
		sql.append("   AND 											");
		sql.append(keyword);
		sql.append(" like ?");
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchword+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return count;
	}
	
//	일반 유저 아이디로 로그인할 때 모든 연락처의 수를 세어주는 함수입니다.
	public int getCount_KS(String id, String searchword, String keyword) {
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 		= new StringBuilder();
		int count = 0;
		
		sql.append("SELECT count(*)										");
		sql.append("  FROM (SELECT c.contactnum								");
		sql.append("  			 , c.id										");
		sql.append("  			 , c.name									");
		sql.append("  			 , c.phone1									");
		sql.append("  			 , c.phone2									");
		sql.append("  			 , c.phone3									");
		sql.append("  			 , c.address								");
		sql.append("  			 , c.groupno								");
		sql.append("  			 , c.phone1 || c.phone2 || c.phone3 as phone");
		sql.append("  		  FROM contact c) c								");
		sql.append("	 , group_table g									");
		sql.append(" WHERE c.groupno = g.groupno							");
		sql.append("   AND id = ?											");
		sql.append("   AND 											");
		sql.append(keyword);
		sql.append(" like ?");
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, "%"+searchword+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return count;
	}





}


