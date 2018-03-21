package bigdata.project.parking.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.project.dao.ClientDAO;
import bigdata.project.dto.Client;


public class UserRegisterService implements ParkingService {

   @Override
   public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
      // TODO Auto-generated method stub
      // 1.요청 파라미터 처리
      
      String id = request.getParameter("id");
      String pwd = request.getParameter("pwd");
      String name = request.getParameter("name");
      String gender = request.getParameter("gender");
      String homeaddress = request.getParameter("homeaddress");
      String jobaddress = request.getParameter("jobaddress");
      System.out.println(id);
      Client user =new Client();
      user.setId(id);
      user.setName(name);
      user.setPwd(pwd);
      user.setGender(gender);
      user.setHomeaddress(homeaddress);
      user.setJobaddress(jobaddress);
      // 2.DB 처리 -- dao 호출

      int result = 0;
      System.out.println("-----???--------");
      try {
         result = ClientDAO.getInstance().insert(user);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      // 3.DB 결과 처리
      
      // 4.이동할 다음 페이지 처리
      NextPage nextPage = new NextPage();
      // 회원가입 성공
      if(result !=0){
         nextPage.setPageName("./index.jsp");
         nextPage.setRedirect(true);
      }
      // 회원가입 실패
      else{
         request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
         nextPage.setPageName("./errors/error.jsp");
         nextPage.setRedirect(false);
      }
      return nextPage;
      
   }

}