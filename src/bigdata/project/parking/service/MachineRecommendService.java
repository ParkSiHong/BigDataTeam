package bigdata.project.parking.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bigdata.project.dao.BookmarkDAO;
import bigdata.project.dao.ParkingDAO;
import bigdata.project.dto.Bookmark;
import bigdata.project.dto.Client;
import bigdata.project.dto.ParkingLot;

public class MachineRecommendService implements ParkingService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<ParkingLot> list = new ArrayList<>();
		
		try{
		    
			String[] line = {"9296","9307", "13088", "9260","15190","13076","5683","13064","15225","17680"};
		    for(int i = 0; i < line.length; i++){ 
		    	String s = line[i];
		    	
		    	ParkingLot parking = ParkingDAO.getInstance().selectById(s); 
		        list.add(parking);
		    }
//			try{
//			    Process p = Runtime.getRuntime().exec("python 13.124.172.112/home/ec2-user/python.py");    //�ý��� ��ɾ python ~~~
//			    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())); /// output���� �޾ƿ� �� ����..
//			    String line = null;
//			    System.out.println("python ���� ����: " + br.readLine());
//			    while((line = br.readLine()) != null){
//			        System.out.println(line);
//			        list = ParkingDAO.getInstance().selectByAddress(line);
//			    }
		    
		}catch(Exception e){
		    System.out.println(e);
		}
		
		//3. db��� ó��
		request.setAttribute("list", list);
		
		//4. �̵��� ���� ������ ó��
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./parking/machine_recommend.jsp");
		nextPage.setRedirect(false);
		return nextPage;
	}

}
