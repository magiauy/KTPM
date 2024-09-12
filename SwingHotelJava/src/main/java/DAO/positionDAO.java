package DAO;
import DTO.positionDTO;
import DAO.connectToSQL;

import java.sql.*;

import java.util.ArrayList;

public class positionDAO extends connectToSQL{
//	Lấy mã chức vụ và tên chức vụ
	public ArrayList<positionDTO> getAllPosition(){
		ArrayList<positionDTO> arrPosition = new ArrayList<positionDTO>();
		if(openConectionToSQL()) {
			try {
				String sql ="Select ID_CHUCVU "
						+ "from chucvu";
				
				Statement stmt= con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					positionDTO p = new positionDTO();
					
					p.setChucvu_id(rs.getInt("ID_CHUCVU"));
					
					arrPosition.add(p);
					
				}
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrPosition;
	}
	

}
