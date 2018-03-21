package bigdata.project.parking.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bigdata.project.dao.ChartCountDAO;
import bigdata.project.dto.ChartCount;

public class ChartCountSearch implements ParkingService{
	
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<ChartCount> list = new ArrayList<>();
		response.setCharacterEncoding("UTF-8");
		
		String ChartSearch = request.getParameter("ChartSearch");
		System.out.println(ChartSearch);
		if(ChartSearch.equals("전국주차장"))
		{
			ChartSearch = "%";
		}
		try {
			list = ChartCountDAO.getInstance().selectByChart(ChartSearch);
			
			JSONArray array = new JSONArray();
			
			for (ChartCount c: list) {
				JSONObject obj = new JSONObject();
				obj.put("cityname",c.getName());
				obj.put("count", c.getCount());
				System.out.println(c.getName());
				array.add(obj);
			}
			
			PrintWriter out = response.getWriter();
			
			out.print(array);
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
		//3. db결과 처리
		//request.setAttribute("list", list);
		//4. 이동할 다음 페이지 처리
		//NextPage nextPage = new NextPage();
		//nextPage.setPageName("./parking/parking_chart.jsp");
		//nextPage.setRedirect(false);
		//return nextPage;
	}


}
