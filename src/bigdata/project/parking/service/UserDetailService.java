package bigdata.project.parking.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.project.dao.ClientDAO;
import bigdata.project.dto.Client;


public class UserDetailService implements ParkingService{
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Client user =null;
		String id = request.getParameter("id");
		try {
			user = ClientDAO.getInstance().selectById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("user", user);
		
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./user/detail.jsp");
		nextPage.setRedirect(false);
		return nextPage;
	}
}
