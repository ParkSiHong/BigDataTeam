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
		
		/*�� ����� ������ ��õ*/
		List<ParkingLot> recommendList = null;
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lng = Double.parseDouble(request.getParameter("lng")); 
		System.out.println("�̰��� service Ŭ����, DAO ȣ�� ��");
		
		try {
			recommendList = ParkingDAO.getInstance().parkingRecommendSearch(lat, lng);
			System.out.println("�̰��� service Ŭ����, DAO ȣ�� ��");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*���� ���� ���� �ߺ��� ȸ���� �ϸ�ũ ��õ*/
		System.out.println("bookmarkCount recommend service");
		HttpSession session = request.getSession();
		String myID = (String) session.getAttribute("id");
		System.out.println("�̰��� bookmarkCount service Ŭ���� DAO ȣ�� ��");
		
		List<String> aClientList = new ArrayList<>();
		List<Count> aCountList = new ArrayList<>();
		Count maxCountID = new Count();
		
		List<Bookmark> list = new ArrayList<>();
		try {
			//�� �ϸ�ũ�� �ߺ��� ȸ���� �̴´�.
			aClientList = BookmarkCountDAO.getInstance().getClientList(myID);

			//���� ���� ���� �ߺ��� ȸ���� �̴´�.
			for(int i = 0; i < aClientList.size(); i++){
				//ȸ������ �ߺ��� �ϸ�ũ�� ������ �̴´�.
				aCountList.add(BookmarkCountDAO.getInstance().getCountBookmark(myID, aClientList.get(i)));
			}
			
			//�ߺ��� �ϸ�ũ ������ �ִ밪�� ã�´�.
			for(int i = 0; i < aCountList.size(); i++){
				if(maxCountID != null && aCountList.get(i).getCount() > maxCountID.getCount()){
					maxCountID = aCountList.get(i);
				}
			}
			list = BookmarkCountDAO.getInstance().recommendBookmark(myID, maxCountID.getUserId());

			System.out.println("�̰��� bookmarkCount service Ŭ����, DAO ȣ�� ��");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("recommendList", recommendList);

		//4. �̵��� ���� ������ ó��
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./parking/recommend_list.jsp");

		nextPage.setRedirect(false);
		return nextPage;
	}
	

}
