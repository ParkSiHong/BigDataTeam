package bigdata.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB ���� ��������ϴ� Ŭ����
 * Connection ����, ����
 * @author ChoISung 
 *
 */
public class DBUtil {
	
	private static final String CON_URL = "jdbc:mysql://13.124.172.112:3306/project?useUnicode=true&characterEncoding=utf8";
	private static final String USER_ID = "root";
	private static final String USER_PWD = "qlalfqjsgh1";
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	// static ����
	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 *  �����ͺ��̽� ������ �����ϴ� �޼ҵ�
	 * @return Connection �����ͺ��̽� ���� ���� ��ü ��ȯ
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(CON_URL, USER_ID, USER_PWD);

	}
	/**
	 *  ������ ���̽� ����� ����� �ڿ� ����
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
