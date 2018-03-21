package bigdata.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bigdata.project.dto.Client;
import bigdata.project.util.DBUtil;



public class ClientDAO {
   
   private static ClientDAO dao = new ClientDAO();
   private ClientDAO(){
      
   }
   public static ClientDAO getInstance(){
      return dao;
   }
   
   // 회원가입
      public int insert(Client client) throws SQLException {
         Connection con = null;
         PreparedStatement pstmt = null;
         int result = 0;

         try {
            con = DBUtil.getConnection();
            String sql = "insert into Client (client_id, client_pw, client_name, client_gender, client_homeaddress, client_jobaddress, client_reg_date) values (?,?,?,?,?,?,default)";
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, client.getId());
            pstmt.setString(2, client.getPwd());
            pstmt.setString(3, client.getName());
            pstmt.setString(4, client.getGender());
            pstmt.setString(5, client.getHomeaddress());
            pstmt.setString(6, client.getJobaddress());
            result = pstmt.executeUpdate();
         } finally {
            DBUtil.close(con, pstmt);
         }
         System.out.println("3. UserDAO insert()");
         return result;
      }

      // 회원리스트
      public List<Client> selectAll() throws SQLException {
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         List<Client> list = new ArrayList<>();

         try {
            con = DBUtil.getConnection();
            String sql = "select * from Client order by client_id";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
               Client client = new Client();
               client.setId(rs.getString("client_id"));
               client.setName(rs.getString("clinet_name"));
               client.setRegDate(rs.getDate("client_reg_date"));

               list.add(client);
            }
         } finally {
            DBUtil.close(con, pstmt, rs);
         }
         return list;
      }

      // 회원상세정보
      public Client selectById(String id) throws SQLException {
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         Client client = new Client();

         try {
            con = DBUtil.getConnection();
            String sql = "select * from Client where client_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
               client.setId(rs.getString("client_id"));
               client.setPwd(rs.getString("client_pw"));
               client.setName(rs.getString("clinet_name"));
               client.setRegDate(rs.getDate("client_reg_date"));
            }
         } finally {
            DBUtil.close(con, pstmt, rs);
         }
         return client;

      }

      
      
      // 회원정보 삭제
      public void delete() {

      }

      // 중복체크
      public int idCheck(String id) throws SQLException{
         Connection con = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         int check = 0;
         
         try{
            con = DBUtil.getConnection();
            String sql = "select * from Client where client_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            
            rs = pstmt.executeQuery();
            //사용불가
            if(rs.next()){
               check = 1;
            }
            //사용가능
            else{
               check = 0;
            }
         }finally{
            DBUtil.close(con, pstmt, rs);
         }
         return check;
         
      }
      // 로그인
      public Client login(String id, String pwd) throws SQLException{
         Connection con = null;
         PreparedStatement pstmt =null;
         ResultSet rs = null;
         Client client = new Client();
         
         try{
         con = DBUtil.getConnection();
         String sql = "select * from Client where client_id = ? and client_pw = ?";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, id);
         pstmt.setString(2, pwd);
         
         rs = pstmt.executeQuery();

         if(rs.next()){
        	 client.setId(rs.getString("client_id"));
        	 client.setName(rs.getString("client_name"));
        	 client.setRegDate(rs.getDate("client_reg_date"));
        	 client.setGender(rs.getString("client_gender"));
        	 client.setHomeaddress(rs.getString("client_homeaddress"));
        	 client.setJobaddress(rs.getString("client_jobaddress"));
         }

         }finally{
            DBUtil.close(con, pstmt, rs);
         }
         return client;
      }

      
}