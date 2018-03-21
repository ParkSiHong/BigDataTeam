package bigdata.project.parking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bigdata.project.dao.BookmarkDAO;
import bigdata.project.dto.Bookmark;

public class BookmarkEnrollService implements ParkingService {

   @Override
   public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
      // TODO Auto-generated method stub
      System.out.println("BookmarkEnrollService execute()");

      HttpSession session = request.getSession();
      String[] primaryNum = request.getParameterValues("check");
      String clientId = (String) session.getAttribute("id");

      int result = 0;

      try {
         for (String s : primaryNum) {

        	 System.out.println("Ȯ�� for��"+ s);
            result = BookmarkDAO.getInstance().insertBookmark(clientId, s);
         }

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      List<Bookmark> list = new ArrayList<>();
      
      try {
    	  //-----client ��ȣ ����!
         list = BookmarkDAO.getInstance().searchBookmark(clientId);
         System.out.println("List<Bookmark>: " + list);
      } catch (SQLException e) {
         e.printStackTrace();
      }

      // 3. db��� ó��
      request.setAttribute("list", list);
      // 4. �̵��� ���� ������ ó��
      NextPage nextPage = new NextPage();

      if (result == 1) { // request.setAttribute("checklist", checklist);
         nextPage.setPageName("./parking/bookmark_enroll.jsp");
         nextPage.setRedirect(false);
      } else {
         System.out.println("������ �����Ͽ����ϴ�.");
      }

      return nextPage;
   }

}