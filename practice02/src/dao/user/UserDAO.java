package dao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.contact.ContactVO;
import vo.user.UserVO;

public class UserDAO {
	
//	DB와 자바 사이의 커넥션을 만들어 주는 함수입니다.
	private Connection getConnection(){
		Connection con = null;
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
	
	public int insertUser(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowcnt = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into member		");
		sql.append("values(?,?,?,?,?,?,?)	");
				
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, user.name);
			pstmt.setString(2, user.id);
			pstmt.setString(3, user.pw);
			pstmt.setString(4, user.phone1);
			pstmt.setString(5, user.phone2);
			pstmt.setString(6, user.phone3);
			pstmt.setString(7, user.gender);
			
			rowcnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(con,pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowcnt;
		
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
//	con과 pstmt, rs를 닫아주는 함수입니다.
	private void close(Connection con
			, PreparedStatement pstmt, ResultSet rs) {
//		null이 아닐 경우에만 닫아줍니다.
//		(nullPointException 방지)
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) { 
				con.close();
			}
			if(rs != null) { 
				rs.close();
			}
		}catch(SQLException e) {
//			DB에 문제가 있음을 알리는 내용을 출력합니다. (SQLException)
//			uv.DBFail();
			e.printStackTrace();
		}
			
	}

//	로그인시 유저 정보 가져오는 기능
	public UserVO searchByIdPw(String id, String pw) {
		UserVO user = new UserVO();
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String query = "SELECT name, id, phone1,"
				+ "phone2, phone3, gender FROM member WHERE id=? AND pw=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPhone1(rs.getString("phone1"));
				user.setPhone2(rs.getString("phone2"));
				user.setPhone3(rs.getString("phone3"));
				user.setGender(rs.getString("gender"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return user;
	}
	
	public UserVO searchById(String id) {
		UserVO user = new UserVO();
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String query = "SELECT name, id, phone1, phone2, phone3, gender FROM member WHERE id=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPhone1(rs.getString("phone1"));
				user.setPhone2(rs.getString("phone2"));
				user.setPhone3(rs.getString("phone3"));
				user.setGender(rs.getString("gender"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	public int updateUser(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowcnt = 0;
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE member				");
		query.append("   SET name 		= ?,	");
		query.append("		 pw 		= ?,	");
		query.append("       phone1 	= ?,	");
		query.append("		 phone2 	= ?,	");
		query.append("       phone3	 	= ?,	");
		query.append("       gender	 	= ?		");
		query.append(" WHERE id			= ?		");
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getPhone1());
			pstmt.setString(4, user.getPhone2());
			pstmt.setString(5, user.getPhone3());
			pstmt.setString(6, user.getGender());
			pstmt.setString(7, user.getId());
			rowcnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		return rowcnt;
	}

	public int idCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from member where id = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next() || id.equals("")) { // 결과가 있다면
				return 0; // 이미 존재하는 아이디
			} else {
				return 1; // 가입 가능한 아이디
			} 
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return -1; // 데이터베이스 오류
	}

	public ArrayList<UserVO> searchAll() {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		String query = "SELECT name, id, phone1, phone2, phone3, gender FROM member";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			int i=0;
			while(rs.next()) {
				UserVO user = null;
				user = new UserVO();
//				while 문 안에 user 객체를 생성해주어야 함.
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPhone1(rs.getString("phone1"));
				user.setPhone2(rs.getString("phone2"));
				user.setPhone3(rs.getString("phone3"));
				user.setGender(rs.getString("gender"));
				userlist.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return userlist;
	}
	
	public ArrayList<UserVO> searchAll(int startRow, int endRow) {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		
		Connection con 			= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT * 													");
		query.append("  FROM (SELECT ROW_NUMBER() OVER(ORDER BY name ASC) AS rn,");
		query.append("				 name, id, pw, 								");
		query.append("				 phone1, phone2, phone3, 					");
		query.append("				 gender										");
		query.append("			FROM member m) 									");
		query.append(" WHERE rn BETWEEN ? AND ?									");
		
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, String.valueOf(startRow));
			pstmt.setString(2, String.valueOf(endRow));
			rs = pstmt.executeQuery();
			int i=0;
			while(rs.next()) {
				UserVO user = null;
				user = new UserVO();
//				while 문 안에 user 객체를 생성해주어야 함.
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPhone1(rs.getString("phone1"));
				user.setPhone2(rs.getString("phone2"));
				user.setPhone3(rs.getString("phone3"));
				user.setGender(rs.getString("gender"));
				userlist.add(user);
				System.out.println(user.getName());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return userlist;
	}

// 	전체 회원 명수를 세는 함수.
	public int getCount_admin() {
//		커넥션을 통해 DB와 자바 사이를 잇습니다.
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder query 		= new StringBuilder();
		int count = 0;
		
		query.append("SELECT count(*)	FROM member");
		
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
		
		System.out.println("uDAO getCount 테스트 : count(*) 갯수");
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
		
		sql.append("SELECT count(*) as count							");
		sql.append("  FROM (select name,id,phone1,phone2,phone3, 		");
		sql.append("  			   phone1||phone2||phone3 as phone  	");
		sql.append("  		  FROM member)  							     ");
		sql.append(" WHERE  							     ");
		sql.append(keyword);
		sql.append(" like ?");
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchword+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("userDAO count KS 돌아감 : "+ count);
				System.out.println(searchword);
				System.out.println(keyword);
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		System.out.println("cDAO getCount 테스트 : id 있는거 count(*) 갯수");
		return count;
	}

	public void deleteUser(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		String query1 = "DELETE contact WHERE id = ?";
		String query2 = "DELETE member  WHERE id = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query1);
			pstmt2 = con.prepareStatement(query2);
			pstmt.setString(1, id);
			pstmt2.setString(1, id);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}

	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword) {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 		= new StringBuilder();
		int count = 0;
		
		sql.append("SELECT *											");
		sql.append("  FROM (SELECT m.name, m.id, 							");
		sql.append("  			   m.phone1, m.phone2, 						");
		sql.append("  			   m.phone3, m.gender,						");
		sql.append("  			   m.phone1 || m.phone2 || m.phone3 as phone");
		sql.append("  		  FROM member m) m 								");
		sql.append(" WHERE  												");
		sql.append(keyword);
		sql.append("  LIKE ?");
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO user = new UserVO();
//				while 문 안에 user 객체를 생성해주어야 함.
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPhone1(rs.getString("phone1"));
				user.setPhone2(rs.getString("phone2"));
				user.setPhone3(rs.getString("phone3"));
				user.setGender(rs.getString("gender"));
				userlist.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return userlist;
	}
	
	public ArrayList<UserVO> searchAll_KS(String keyword, String searchword, int startRow, int endRow) {
		ArrayList<UserVO> userlist = new ArrayList<UserVO>();
		Connection con 				= null;
		PreparedStatement pstmt 	= null;
		ResultSet rs 				= null;
//		DB에 입력할 sql 구문을 담을 sql 변수를 만듭니다.
		StringBuilder sql 		= new StringBuilder();
		int count = 0;
		
		sql.append("SELECT *													");
		sql.append("  FROM (SELECT ROW_NUMBER() OVER(ORDER BY name ASC) AS rn,	");
		sql.append("  			   name, id, pw, 								");
		sql.append("  			   phone1, phone2, phone3,						");
		sql.append("  			   m.phone1 || m.phone2 || m.phone3 as phone,	");
		sql.append("  			   gender										");
		sql.append("  		  FROM member m) m 									");
		sql.append("  WHERE rn BETWEEN ? AND ?									");
		sql.append("    AND														");
		sql.append(keyword);
		sql.append("  LIKE ?													");
		
		
		try {
			con=getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, String.valueOf(startRow));
			pstmt.setString(2, String.valueOf(endRow));
			pstmt.setString(3, "%"+searchword+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO user = new UserVO();
//				while 문 안에 user 객체를 생성해주어야 함.
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPhone1(rs.getString("phone1"));
				user.setPhone2(rs.getString("phone2"));
				user.setPhone3(rs.getString("phone3"));
				user.setGender(rs.getString("gender"));
				userlist.add(user);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		
		return userlist;
	}
}
	

