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
      // 1.��û �Ķ���� ó��
      
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
      // 2.DB ó�� -- dao ȣ��

      int result = 0;
      System.out.println("-----???--------");
      try {
         result = ClientDAO.getInstance().insert(user);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      // 3.DB ��� ó��
      
      // 4.�̵��� ���� ������ ó��
      NextPage nextPage = new NextPage();
      // ȸ������ ����
      if(result !=0){
         nextPage.setPageName("./index.jsp");
         nextPage.setRedirect(true);
      }
      // ȸ������ ����
      else{
         request.setAttribute("errorMsg", "ȸ�����Կ� �����߽��ϴ�.");
         nextPage.setPageName("./errors/error.jsp");
         nextPage.setRedirect(false);
      }
      return nextPage;
      
   }

}