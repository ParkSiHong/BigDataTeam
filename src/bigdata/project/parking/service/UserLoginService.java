package bigdata.project.parking.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import bigdata.project.dao.ClientDAO;
import bigdata.project.dto.Client;

public class UserLoginService implements ParkingService{
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("UserLoginService");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id);
		System.out.println(pwd);

		Client client = new Client();
		try {
			client = ClientDAO.getInstance().login(id, pwd);
			
			if(client != null){
				HttpSession session = request.getSession();
				session.setAttribute("id", client.getId());
				session.setAttribute("pwd", client.getPwd());
				session.setAttribute("homeaddress", client.getHomeaddress());

			}else{
				System.out.println("UserLoginService execute() 'session ½ÇÆÐ' " );
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
}
