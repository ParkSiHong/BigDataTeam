package bigdata.project.parking.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bigdata.project.dao.ParkingDAO;
import bigdata.project.dto.ChartCount;
import bigdata.project.dto.ParkingLot;

public class ParkingMapSearch implements ParkingService{

   @Override
   public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
      response.setCharacterEncoding("UTF-8");
      List<ParkingLot> list = null;
      String address = request.getParameter("parkingMapList");
      System.out.println(address);
      try {
         list = ParkingDAO.getInstance().parkingMapSearch(address);
         
         JSONArray array = new JSONArray();
         
         for (ParkingLot c: list) {
            JSONObject obj = new JSONObject();
            obj.put("ParkingName",c.getParkingName());
            obj.put("Old_address",c.getOld_address());
            obj.put("Latitude", c.getLatitude());
            obj.put("Longtitude",c.getLongtitude());
            
            array.add(obj);
         }
         
         PrintWriter out = response.getWriter();
         
         out.print(array);
      } catch (SQLException | IOException e) {
         e.printStackTrace();
      }
      
      
      return null;
      
      /*//3. db결과 처리
      request.setAttribute("list", list);
      //4. 이동할 다음 페이지 처리
      
      NextPage nextPage = new NextPage();
      nextPage.setPageName("./parking/parking_map_list.jsp");
      nextPage.setRedirect(false);
      return nextPage;*/
   }

}