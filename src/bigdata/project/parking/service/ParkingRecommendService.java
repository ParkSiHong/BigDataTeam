package bigdata.project.parking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bigdata.project.dao.BookmarkCountDAO;
import bigdata.project.dao.ParkingDAO;
import bigdata.project.dto.Bookmark;
import bigdata.project.dto.Count;
import bigdata.project.dto.ParkingLot;

public class ParkingRecommendService implements ParkingService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		/*집 가까운 주차장 추천*/
		List<ParkingLot> recommendList = null;
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng")); 
		System.out.println("이곳은 service 클래스, DAO 호출 전");
		
		try {
			recommendList = ParkingDAO.getInstance().parkingRecommendSearch(lat, lng);
			System.out.println("이곳은 service 클래스, DAO 호출 후");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*나와 가장 많이 중복된 회원의 북마크 추천*/
		System.out.println("bookmarkCount recommend service");
		HttpSession session = request.getSession();
		String myID = (String) session.getAttribute("id");
		System.out.println("이곳은 bookmarkCount service 클래스 DAO 호출 전");
		
		List<String> aClientList = new ArrayList<>();
		List<Count> aCountList = new ArrayList<>();
		Count maxCountID = new Count();
		
		List<Bookmark> list = new ArrayList<>();
		try {
			//내 북마크와 중복된 회원을 뽑는다.
			aClientList = BookmarkCountDAO.getInstance().getClientList(myID);

			//나와 가장 많이 중복된 회원을 뽑는다.
			for(int i = 0; i < aClientList.size(); i++){
				//회원별로 중복된 북마크의 개수를 뽑는다.
				aCountList.add(BookmarkCountDAO.getInstance().getCountBookmark(myID, aClientList.get(i)));
			}
			
			//중복된 북마크 개수의 최대값을 찾는다.
			for(int i = 0; i < aCountList.size(); i++){
				if(maxCountID != null && aCountList.get(i).getCount() > maxCountID.getCount()){
					maxCountID = aCountList.get(i);
				}
			}
			list = BookmarkCountDAO.getInstance().recommendBookmark(myID, maxCountID.getUserId());

			System.out.println("이곳은 bookmarkCount service 클래스, DAO 호출 후");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("recommendList", recommendList);

		//4. 이동할 다음 페이지 처리
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./parking/recommend_list.jsp");

		nextPage.setRedirect(false);
		return nextPage;
	}
	

}
