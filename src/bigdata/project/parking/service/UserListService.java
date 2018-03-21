package bigdata.project.parking.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.project.dao.ClientDAO;
import bigdata.project.dto.Client;


public class UserListService implements ParkingService{
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//1. 요청 파라미터 처리
		//2.
		List<Client> list =null;
		try{
			list = ClientDAO.getInstance().selectAll();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//3.DB결과 처리
		request.setAttribute("list", list);
		//4.이동할 다음페이지 처리
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./user/list.jsp");
		nextPage.setRedirect(false);
		return nextPage;
	}
}
