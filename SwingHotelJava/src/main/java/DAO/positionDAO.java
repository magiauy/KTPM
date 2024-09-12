package DAO;
import DTO.positionDTO;
import DAO.connectToSQL;

import java.sql.*;

import java.util.ArrayList;

public class positionDAO {
	private connectToSQL conDAO= new connectToSQL();
//	Lấy mã chức vụ và tên chức vụ
	public ArrayList<positionDTO> getAllPosition(){
		ArrayList<positionDTO> arrPosition = new ArrayList<positionDTO>();
		if(conDAO.openConectionToSQL()) {
			try {
				String sql ="Select ID_CHUCVU "
						+ "from chucvu";
				
				Statement stmt= conDAO.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					positionDTO p = new positionDTO();
					
					p.setChucvu_id(rs.getInt("ID_CHUCVU"));
					
					arrPosition.add(p);
					
				}
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				conDAO.closeConnectionToSQL();
			}
		}
		return arrPosition;
	}
	

}
