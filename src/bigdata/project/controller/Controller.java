package bigdata.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigdata.project.parking.service.NextPage;
import bigdata.project.parking.service.ParkingService;

/**
 * Servlet implementation class Controller
 */
@WebServlet(value="*.project", 
		initParams={@WebInitParam(name = "url", value = "/bigdata/project/util/url.properties")})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Map<String, ParkingService> map = new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String url = config.getInitParameter("url");
		System.out.println(url);

		Properties prop = new Properties();

		try {
			prop.load(getClass().getResourceAsStream(url));
			System.out.println(prop);

			// for(:prop.keySet())
			Iterator<Object> it = prop.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();

				// 클래스 로딩
				Class clazz = Class.forName(prop.getProperty(key));
				// 객체 생성
				ParkingService service = (ParkingService) clazz.newInstance();

				map.put(key, service);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		int cmdIdx = url.lastIndexOf("/") + 1;
		String cmd = url.substring(cmdIdx);
		System.out.println(cmd);

		ParkingService service = map.get(cmd);

		NextPage nextPage = service.execute(request, response);
		// 2. 다음 페이지 이동 -------------------------------------
		System.out.println(nextPage);
		if (nextPage == null) {
			System.out.println("null을 반환하지마!!");
		} else {
			if (nextPage.isRedirect()) {
				response.sendRedirect(nextPage.getPageName());
			} else {
				request.getRequestDispatcher(nextPage.getPageName()).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
