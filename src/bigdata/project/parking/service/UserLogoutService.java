package bigdata.project.parking.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogoutService implements ParkingService {
	@Override
	 public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
	      // TODO Auto-generated method stub
	      HttpSession session = request.getSession();
	      NextPage nextPage = new NextPage();

	      if(session.getAttribute("id") != null)
	         session.invalidate();
	      
	      System.out.println("�α׾ƿ���");
	      nextPage.setPageName("./index.jsp");// �����������ΰ�����
	      nextPage.setRedirect(true);
	      return nextPage;
	   }
}
