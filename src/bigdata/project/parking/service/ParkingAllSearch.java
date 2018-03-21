package bigdata.project.parking.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.project.dao.ParkingDAO;
import bigdata.project.dto.ParkingLot;

public class ParkingAllSearch implements ParkingService{

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<ParkingLot> list = null;
		int totalCount = 0;
		String address = request.getParameter("parkingSearch");
		System.out.println(address);
		try {
			list = ParkingDAO.getInstance().selectByAddress(address);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3. db��� ó��
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		//4. �̵��� ���� ������ ó��
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./parking/parking_list.jsp");
		//nextPage.setPageName("./parking/parking_map_list.jsp");
		nextPage.setRedirect(false);
		return nextPage;
	}

}
