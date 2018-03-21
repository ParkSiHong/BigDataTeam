package bigdata.project.parking.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.project.dao.ParkingDAO;
import bigdata.project.dto.ParkingLot;


public class ParkingDetail implements ParkingService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("2. ParkingDetail execute()");
		
		String parkingSearch = request.getParameter("parkingSearch");
		System.out.println("__________where?");
		ParkingLot parking = null;
		
		try {
			parking = ParkingDAO.getInstance().selectById(parkingSearch);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("parkingSearch", parking);
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./parking/parking_detail.jsp");
		nextPage.setRedirect(false);
		
		return nextPage;
	}

}
