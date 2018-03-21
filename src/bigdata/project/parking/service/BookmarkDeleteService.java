package bigdata.project.parking.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bigdata.project.dao.BookmarkDAO;
import bigdata.project.dto.Bookmark;

public class BookmarkDeleteService implements ParkingService {

   @Override
   public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
      // TODO Auto-generated method stub
      
      System.out.println("DeleteService");
      
      HttpSession session = request.getSession();
      String primaryNum = request.getParameter("primaryNum");
      String clientId = (String) session.getAttribute("id");
      
      System.out.println("try primaryNum: "+primaryNum);
      System.out.println("try clientId: "+clientId);
      
      int result = 0;
      
      try {
         result = BookmarkDAO.getInstance().deleteBookmark(clientId, primaryNum);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      if(result != 0){
			System.out.println("삭제 성공");
			try {
				List<Bookmark> list = BookmarkDAO.getInstance().searchBookmark(clientId);
				System.out.println("after: " + list.size());
				System.out.println(list);
				System.out.println("JSON-----------------------");
				
				JSONArray array = new JSONArray();
				for(int i = 0; i <list.size(); i++){
					JSONObject obj = new JSONObject();
					obj.put("primaryNum", list.get(i).getPrimaryNum());
					obj.put("primaryName", list.get(i).getParkingName());//
					obj.put("parkingType", list.get(i).getParkingType());//
					obj.put("parkingBasicFee", list.get(i).getParkingBasicFee());
					//obj.put("primaryName", list.get(i).getParking().getParkingName());
					//obj.put("parkingType", list.get(i).getParking().getParkingType());
					
					array.add(obj);	
				}
				
				System.out.println(array);
				
				PrintWriter out = response.getWriter();
				out.print(array);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
      else {
    	  System.out.println("삭제 실패");
      }
      return null;
   }
}