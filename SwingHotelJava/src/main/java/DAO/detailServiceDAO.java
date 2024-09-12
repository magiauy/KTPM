package DAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.detailServiceDTO;

public class detailServiceDAO extends connectToSQL{
//	Thêm dịch vụ
	public boolean addDetailSerice(detailServiceDTO dlService) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Insert into chitietdichvu values(?,?,?,?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,dlService.getId_hd());
				stmt.setInt(2,dlService.getId_dv());
				stmt.setInt(3,dlService.getSoluong_dv());
				stmt.setDouble(4,dlService.getTongtien_dv());

				if(stmt.executeUpdate()>=1) {
					result=true;
				}

			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return result;
	}
//	Set trạng thái phiếu đặt
	

}
