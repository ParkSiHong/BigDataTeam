package bigdata.project.parking.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.project.dao.ClientDAO;

public class UserIdCheckService implements ParkingService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("User");
		String id = request.getParameter("id");
		System.out.println(id);
		
		int result =0;
		try {
			result = ClientDAO.getInstance().idCheck(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		PrintWriter out =null;
		try {
			out =response.getWriter();
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
