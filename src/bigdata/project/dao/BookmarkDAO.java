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

public class BookmarkDAO {

   private static BookmarkDAO dao = new BookmarkDAO();

   private BookmarkDAO() {
   }

   public static BookmarkDAO getInstance() {
      return dao;
   }
   // 즐겨찾기 등록(BookmarkEnrollService)
   public int insertBookmark(String userId, String primaryNum) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      int result = 0;
      

      try {
         con = DBUtil.getConnection();
         String sql = "insert into Bookmark values(?,?)";
         // 수정필
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, userId);
         pstmt.setString(2,primaryNum);
         System.out.println(userId);
         System.out.println(primaryNum);

         result = pstmt.executeUpdate();
      } finally {
         DBUtil.close(con, pstmt);
      }
      System.out.println("3. insertBookmark()");
      return result;

   }
   
   // 즐겨찾기 리스트(BookmarkSearchService)
   public List<Bookmark> searchBookmark(String clientId) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<Bookmark> list = new ArrayList<>();
      System.out.println("3. searchBookmark()");
      try {
         con = DBUtil.getConnection();
         String sql = "select *"
               + " from Parking_Lot p inner join Bookmark b on b.primary_num = p.primary_num"
               + " inner join Client cl on b.client_id = cl.client_id"
               + " inner join Charge c on c.primary_num = p.primary_num"
               + " where b.client_id = ?";
         
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, clientId);

         rs = pstmt.executeQuery();

         while (rs.next()) {
        	 Bookmark bookmark = new Bookmark();
        	 //bookmark.getParking().setParkingName(rs.getString("parking_name"));
        	 bookmark.setParkingName(rs.getString("parking_name"));
        	 bookmark.setPrimaryNum(rs.getString("primary_num"));
        	 bookmark.setParkingBasicFee(rs.getInt("parking_basic_fee"));
        	 //bookmark.getParking().setParkingType(rs.getString("parking_type"));
        	 bookmark.setParkingType(rs.getString("parking_type"));

            list.add(bookmark);
         }
      } finally {
         DBUtil.close(con, pstmt, rs);
      }
      return list;
   }
   
   // 즐겨찾기 주차장 상세정보(BookmarkDetailService)
   
   // 즐겨찾기 삭제(BookMarkDeleteService)
   public int deleteBookmark(String clientId, String primaryNum) throws SQLException {
      
      Connection con = null;
      PreparedStatement pstmt = null;
      System.out.println("deleteBookmark()");
      int result = 0;
      
      try {
         con = DBUtil.getConnection();
         String sql = "delete from Bookmark where client_id = ? and primary_num = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, clientId);
         pstmt.setString(2, primaryNum);

         result = pstmt.executeUpdate();
         
      } finally {

         DBUtil.close(con, pstmt);
      }
   
      System.out.println("return: "+ result);
      return result;
   }

}