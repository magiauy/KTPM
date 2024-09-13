package DAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import DTO.detailServiceDTO;

public class detailServiceDAO extends connectToSQL{

//	Thêm dịch vụ
	public boolean addDetailSerice(detailServiceDTO dlService) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Insert into chitietdichvu values(?,?,?,?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,dlService.getid_pn());
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
	public ArrayList<detailServiceDTO> getDetailServiceByID_PD(int id) {
		ArrayList<detailServiceDTO> arrDetailService = new ArrayList<detailServiceDTO>();
		if (openConectionToSQL()) {
			try {
				String sql = "Select * from chitietdichvu where ID_PD=?";

				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					detailServiceDTO dlService = new detailServiceDTO();

					dlService.setid_pn(rs.getInt("ID_PD"));
					dlService.setId_dv(rs.getInt("ID_DV"));
					dlService.setSoluong_dv(rs.getInt("SOLUONG_DV"));
					dlService.setTongtien_dv(rs.getDouble("TONGTIEN_DV"));

					arrDetailService.add(dlService);
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}return arrDetailService;
	}
//	Set trạng thái phiếu đặt
	public ArrayList<detailServiceDTO> getAllDetailService() {
		ArrayList<detailServiceDTO> arrDetailService = new ArrayList<detailServiceDTO>();
		if(openConectionToSQL()) {
			try {
				String sql="Select * from chitietdichvu ";

				PreparedStatement stmt = con.prepareStatement(sql);

				ResultSet rs=stmt.executeQuery();

				while(rs.next()) {
					detailServiceDTO dlService= new detailServiceDTO();

					dlService.setid_pn(rs.getInt("ID_PD"));
					dlService.setId_dv(rs.getInt("ID_DV"));
					dlService.setSoluong_dv(rs.getInt("SOLUONG_DV"));
					dlService.setTongtien_dv(rs.getDouble("TONGTIEN_DV"));

					arrDetailService.add(dlService);
				}


			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}

		}
		return arrDetailService;
	}
//	edit
	public boolean editDetailService(detailServiceDTO dlService) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Update chitietdichvu set SOLUONG_DV=?, TONGTIEN_DV=? where ID_PD=? and ID_DV=?";

				PreparedStatement stmt=con.prepareStatement(sql);

				stmt.setInt(1,dlService.getSoluong_dv());
				stmt.setDouble(2,dlService.getTongtien_dv());
				stmt.setInt(3,dlService.getid_pn());
				stmt.setInt(4,dlService.getId_dv());

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

}
