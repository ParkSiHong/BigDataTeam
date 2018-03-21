package bigdata.project.parking.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bigdata.project.dao.BookmarkDAO;
import bigdata.project.dto.Bookmark;

public class BookmarkListService implements ParkingService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("BookmarkSearchService execute()");
		
		List<Bookmark> list = null;
		HttpSession session = request.getSession();
		String clientId = (String) session.getAttribute("id");

		try {
			list = BookmarkDAO.getInstance().searchBookmark(clientId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3. db결과 처리
		request.setAttribute("list", list);
		//4. 이동할 다음 페이지 처리
		NextPage nextPage = new NextPage();
		nextPage.setPageName("./parking/bookmark_list.jsp");
		nextPage.setRedirect(false);
		return nextPage;
	}

}
