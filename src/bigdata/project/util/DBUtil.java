package bigdata.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB 서버 연결관리하는 클래스
 * Connection 생성, 종료
 * @author ChoISung 
 *
 */
public class DBUtil {
	
	private static final String CON_URL = "jdbc:mysql://13.124.172.112:3306/project?useUnicode=true&characterEncoding=utf8";
	private static final String USER_ID = "root";
	private static final String USER_PWD = "qlalfqjsgh1";
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	// static 영역
	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 *  데이터베이스 서버를 연결하는 메소드
	 * @return Connection 데이터베이스 연결 정보 객체 반환
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(CON_URL, USER_ID, USER_PWD);

	}
	/**
	 *  데이터 베이스 연결시 사용한 자원 해제
	 *  @param Connection, PreparedStatement
	 *  @return void
	 */
	public static void close(Connection con, PreparedStatement pstmt){
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param con
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
