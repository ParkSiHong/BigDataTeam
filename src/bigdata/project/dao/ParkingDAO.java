package bigdata.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bigdata.project.dto.ParkingLot;
import bigdata.project.util.DBUtil;

public class ParkingDAO {
   
   private static ParkingDAO dao = new ParkingDAO();
   
   private ParkingDAO(){}
   
   public static ParkingDAO getInstance(){
      return dao;
   }

   public List<ParkingLot> selectByAddress(String address) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<ParkingLot> list = new ArrayList<>();

      System.out.println("select by address");
      try {
         con = DBUtil.getConnection();
         String sql = "select * from Parking_Lot p inner join Charge c on p.primary_num = c.primary_num where old_address like ? order by p.primary_num * 1 asc";
         
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, "%"+ address + "%");

         rs = pstmt.executeQuery();

         while (rs.next()) {
            ParkingLot parkingLot = new ParkingLot();
            parkingLot.setParkingName(rs.getString("parking_name"));
            parkingLot.setPrimaryNum(rs.getString("primary_num"));
            parkingLot.setParkingBasicFee(rs.getInt("parking_basic_fee"));
            parkingLot.setParkingBasicTime(rs.getInt("parking_basic_time"));
            parkingLot.setParkingType(rs.getString("parking_type"));

            list.add(parkingLot);
         }
      } finally {
         DBUtil.close(con, pstmt, rs);
      }
      return list;
   }

   public ParkingLot selectById(String parkingSearch) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      ParkingLot parkingLot = new ParkingLot();

      try {
         con = DBUtil.getConnection();
         String sql = "select * from Parking_Lot p inner join Charge c on p.primary_num = c.primary_num where p.primary_num=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, parkingSearch);

         rs = pstmt.executeQuery();
         if (rs.next()) {
            parkingLot.setParkingName(rs.getString("parking_name"));
            parkingLot.setPrimaryNum(rs.getString("primary_num"));
            parkingLot.setParkingType(rs.getString("parking_type"));
            parkingLot.setSectionDount(rs.getInt("section_count"));
            parkingLot.setRunDay(rs.getString("run_day"));
            parkingLot.setTel(rs.getString("tel"));
            parkingLot.setOld_address(rs.getString("old_address"));
            parkingLot.setLatitude(rs.getDouble("latitude"));
            parkingLot.setLongtitude(rs.getDouble("longtitude"));
            parkingLot.setParkingBasicFee(rs.getInt("parking_basic_fee"));
            parkingLot.setParkingBasicTime(rs.getInt("parking_basic_time"));
            parkingLot.setAddUnitFee(rs.getInt("add_unit_fee"));
            parkingLot.setAddUnitTime(rs.getInt("add_unit_time"));
            

         }
      } finally {
         DBUtil.close(con, pstmt, rs);
      }
      return parkingLot;
   }

   public List<ParkingLot> parkingMapSearch(String address) throws SQLException {
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         List<ParkingLot> list = new ArrayList<>();

         try {
            con = DBUtil.getConnection();
            String sql = "select * from Parking_Lot p inner join Charge c on p.primary_num = c.primary_num where old_address like ? order by p.primary_num * 1 asc";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%"+ address + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {
               ParkingLot parkingLot = new ParkingLot();
               parkingLot.setParkingName(rs.getString("parking_name"));
               parkingLot.setPrimaryNum(rs.getString("primary_num"));
               parkingLot.setParkingType(rs.getString("parking_type"));
               parkingLot.setSectionDount(rs.getInt("section_count"));
               parkingLot.setRunDay(rs.getString("run_day"));
               parkingLot.setTel(rs.getString("tel"));
               parkingLot.setOld_address(rs.getString("old_address"));
               parkingLot.setLatitude(rs.getDouble("latitude"));
               parkingLot.setLongtitude(rs.getDouble("longtitude"));
               parkingLot.setParkingBasicFee(rs.getInt("parking_basic_fee"));
               parkingLot.setParkingBasicTime(rs.getInt("parking_basic_time"));
               parkingLot.setAddUnitFee(rs.getInt("add_unit_fee"));
               parkingLot.setAddUnitTime(rs.getInt("add_unit_time"));

               list.add(parkingLot);
            }
         } finally {
            DBUtil.close(con, pstmt, rs);
         }
         return list;
      }
   
   public List<ParkingLot> parkingRecommendSearch(double lat, double lng) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<ParkingLot> recommendList = new ArrayList<>();

      System.out.println("parkingRecommendSearch(), sql문 실행 전");
      try {
         con = DBUtil.getConnection();
         String sql = "SELECT *, "
               + "( 6371 * acos( cos( radians(?) ) * cos( radians( latitude ) ) "
               + "* cos( radians( longtitude ) - radians(?) ) + sin( radians(?) ) * sin( radians( latitude ) ) ) ) "
               + "AS distance "
               + "FROM Parking_Lot p inner join Charge c on p.primary_num = c.primary_num HAVING distance < 10 ORDER BY distance LIMIT 0 , 5";
      
         pstmt = con.prepareStatement(sql);
         pstmt.setDouble(1, lat);
         pstmt.setDouble(2, lng);
         pstmt.setDouble(3, lat);

         rs = pstmt.executeQuery();

         while (rs.next()) {
            ParkingLot parkingLot = new ParkingLot();
            parkingLot.setParkingName(rs.getString("parking_name"));
            parkingLot.setPrimaryNum(rs.getString("primary_num"));
            parkingLot.setOld_address(rs.getString("old_address"));
            parkingLot.setParkingBasicFee(rs.getInt("parking_basic_fee"));
            parkingLot.setParkingBasicTime(rs.getInt("parking_basic_time"));
            parkingLot.setParkingType(rs.getString("parking_type"));
            parkingLot.setLatitude(rs.getDouble("latitude"));
            parkingLot.setLongtitude(rs.getDouble("longtitude"));

            recommendList.add(parkingLot);
         }
      } finally {
         DBUtil.close(con, pstmt, rs);
      }
      return recommendList;
   }

}