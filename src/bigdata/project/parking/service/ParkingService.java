package bigdata.project.parking.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ParkingService {

	public NextPage execute(HttpServletRequest request, HttpServletResponse response);
}
