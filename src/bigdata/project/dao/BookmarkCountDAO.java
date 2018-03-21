package bigdata.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bigdata.project.dto.Bookmark;
import bigdata.project.dto.Count;
import bigdata.project.util.DBUtil;

public class BookmarkCountDAO {
	

	   private static BookmarkCountDAO dao = new BookmarkCountDAO();

	   private BookmarkCountDAO() {
	   }

	   public static BookmarkCountDAO getInstance() {
	      return dao;
	   }
   
	 //���� �ϸ�ũ�� �ߺ��Ǵ� ȸ��
	   public List<String> getClientList(String clientId) throws SQLException{
		   Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      List<String> list = new ArrayList<>();
		      System.out.println("�̰��� getClientList()");
		      try {
		         con = DBUtil.getConnection();
		         String sql = "select distinct client_id from Bookmark "
		         		+ "where primary_num in "
		         		+ "(select primary_num from Bookmark where client_id = ?) "
		         		+ "and client_id <> ?";
		         
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, clientId);
		         pstmt.setString(2, clientId);
		         rs = pstmt.executeQuery();

		         while (rs.next()) {
		            list.add(rs.getString("client_id"));
	 			}
		         
		      } finally {
		         DBUtil.close(con, pstmt, rs);
		      }
		      return list;
	   }
	   
	 //ȸ���� ���� �ߺ��� �ϸ�ũ ����
	   public Count getCountBookmark(String myID, String yourID) throws SQLException{
		   Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      Count countBookmark = new Count();
		      System.out.println("�̰��� getCountBookmark()");
		      try {
		         con = DBUtil.getConnection();
		         String sql = "select count(cnt) count_bookmark "
		         		+ "from (select count(primary_num) cnt from "
		         		+ "(select primary_num, client_id from Bookmark where client_id = ? "
		         		+ "UNION ALL "
		         		+ "select primary_num, client_id from Bookmark where client_id = ?) sub1 "
		         		+ "group by primary_num having count(primary_num) >1) sub2;";
		         //sql����
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, myID);
		         pstmt.setString(2, yourID);
		         rs = pstmt.executeQuery();

		         while (rs.next()) {
		        	 
		        	 countBookmark.setUserId(yourID);
		        	 countBookmark.setCount(rs.getInt("count_bookmark"));
	 			}
		         
		      } finally {
		         DBUtil.close(con, pstmt, rs);
		      }

		      return countBookmark;
	   }
	   
	   
	   //���ã�� ��õ
	   public List<Bookmark> recommendBookmark(String myID, String yourID) throws SQLException{
		   Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      List<Bookmark> list = new ArrayList<>();
		      System.out.println("�̰��� recommendBookmark()");
		      try {
		         con = DBUtil.getConnection();
		         String sql = "select * "
		         		+ "from Bookmark b inner join Parking_Lot p on b.primary_num = p.primary_num "
		         		+ "inner join Charge c on b.primary_num = c.primary_num "
		         		+ "where b.client_id = ? "
		         		+ "and b.primary_num not in "
		         		+ "(select primary_num from Bookmark where client_id = ?)";
		         //sql����
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, yourID);
		         pstmt.setString(2, myID);
		         rs = pstmt.executeQuery();

		         while (rs.next()) {
		        	 Bookmark bookmark = new Bookmark();
		        	 bookmark.setPrimaryNum(rs.getString("primary_num"));
		        	 bookmark.setParkingName(rs.getString("parking_name"));
		        	 bookmark.setParkingBasicFee(rs.getInt("parking_basic_fee"));
		        	 bookmark.setParkingType(rs.getString("parking_type"));

		            list.add(bookmark);
	 			}
		            
		      } finally {
		         DBUtil.close(con, pstmt, rs);
		      }
		      return list;
	   }
	   

}
