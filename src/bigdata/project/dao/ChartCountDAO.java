package bigdata.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bigdata.project.dto.ChartCount;
import bigdata.project.dto.ParkingLot;
import bigdata.project.util.DBUtil;

public class ChartCountDAO {
	private static ChartCountDAO dao = new ChartCountDAO();

	private ChartCountDAO() {
	}

	public static ChartCountDAO getInstance() {
		return dao;
	}

	public List<ChartCount> selectByChart(String ChartSearch) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<ChartCount> list = new ArrayList<>();
		String sql;
		try {
			con = DBUtil.getConnection();
			if (ChartSearch.equals("%")) {
				sql = "select rs , count(*) as countnum from (SELECT SUBSTR(old_address, 1, INSTR(old_address,' ')-1) as rs from Parking_Lot where old_address like ?) a where rs is not null group by rs";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ChartSearch);
			}
			else if(ChartSearch.equals("대전광역시")||ChartSearch.equals("서울특별시")) {
				sql = "select rs , count(*) as countnum from (SELECT SUBSTR(old_address, 7, INSTR(old_address,' ')-2) as rs from Parking_Lot where old_address like ?) a where rs is not null group by rs";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + ChartSearch + "%");
			}
			else {
				sql = "select rs , count(*) as countnum from (SELECT SUBSTR(old_address, 10, INSTR(old_address,' ')-2) as rs from Parking_Lot where old_address like ?) a where rs is not null group by rs";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + ChartSearch + "%");
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ChartCount ChartCount = new ChartCount();
				ChartCount.setName(rs.getString("rs"));
				ChartCount.setCount(rs.getInt("countnum"));
				list.add(ChartCount);

			}

		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return list;
	}
}
