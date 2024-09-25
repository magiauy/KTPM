package DAO;
import DTO.staffDTO;

import java.util.ArrayList;
import java.sql.*;
import DAO.connectToSQL;

public class staffDAO extends connectToSQL{
//	Lấy danh sách nhân viên trả về mảng
	public ArrayList<staffDTO> getAllStaff(){
		ArrayList<staffDTO> arrStaff = new ArrayList<staffDTO>();
		if(openConectionToSQL()) {
			try {
				String sql="Select * from nhanvien ";
				
				Statement stmt = con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					staffDTO staff= new staffDTO();
					
					staff.setStaff_id(rs.getInt("ID_NV"));
					staff.setStaff_name(rs.getString("TEN_NV"));
					staff.setStaff_email(rs.getString("EMAIL"));
					staff.setStaff_phone(rs.getString("SDT_NV"));
					staff.setStaff_CCCD(rs.getString("CCCD_NV"));
					staff.setStaff_phai(rs.getString("PHAI_NV"));
					staff.setStaff_id_chucvu(rs.getInt("ID_CHUCVU"));
					staff.setStaff_status(rs.getString("TRANGTHAI_NV"));
					
					arrStaff.add(staff);
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
			
		}
		return arrStaff;
	}
//	Kiểm tra thêm nhân viên
	public boolean addStaff(staffDTO staff) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Insert into nhanvien values(?,?,?,?,?,?,?,?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1, staff.getStaff_id());
				stmt.setString(2,staff.getStaff_name());
				stmt.setString(3,staff.getStaff_email());
				stmt.setString(4,staff.getStaff_phone());
				stmt.setString(5,staff.getStaff_CCCD());
				stmt.setString(6,staff.getStaff_phone());
				stmt.setString(6,staff.getStaff_phai());
				stmt.setInt(7, staff.getStaff_id_chucvu());
				stmt.setString(8, staff.getStaff_status());

				if(stmt.executeUpdate()>=1) {
					result=true;
				}
				
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e);
				
			}finally{
				closeConnectionToSQL();
			}
		}
		return result;
	}
//	Kiểm tra xóa nhân viên
	public boolean deleteStaff(int idStaff) {
boolean result = false;
	    if(openConectionToSQL()) {
	        try {
	            String sql = "UPDATE nhanvien SET TRANGTHAI_NV = ? WHERE ID_NV = ?";

	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1,"NGỪNG HOẠT ĐỘNG");
	            stmt.setInt(2, idStaff);

	            if(stmt.executeUpdate() > 0) {
	                result = true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	        } finally {
	            closeConnectionToSQL();
	        }
	    }
	    return result;
	}
//	Kiểm tra chỉnh sửa nhân viên
	public boolean editStaff(staffDTO staff) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql ="Update nhanvien set TEN_NV = ?, EMAIL= ?, SDT_NV= ?, CCCD_NV= ?, PHAI_NV= ?, ID_CHUCVU= ?, TRANGTHAI_NV= ? where ID_NV=? ";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1,staff.getStaff_name());
				stmt.setString(2,staff.getStaff_email());
				stmt.setString(3,staff.getStaff_phone());
				stmt.setString(4,staff.getStaff_CCCD());
				stmt.setString(5,staff.getStaff_phai());
				stmt.setInt(6, staff.getStaff_id_chucvu());
				stmt.setString(7, staff.getStaff_status());
				
				stmt.setInt(8, staff.getStaff_id());
				
				if(stmt.executeUpdate()>0) {
					result= true;
				}
				
			}catch(SQLException e ) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return result;
	}
//	Lấy mảng nhân viên bao gồm ID và tên
	public ArrayList<staffDTO> getAllStaffWithIDAndName(){
		ArrayList<staffDTO> arrStaff = new ArrayList<staffDTO>();
		if(openConectionToSQL()) {
			try {
				String sql="Select ID_NV, TEN_NV"
						+ " from nhanvien ";
				
				Statement stmt = con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					staffDTO staff= new staffDTO();
					
					staff.setStaff_id(rs.getInt("ID_NV"));
					staff.setStaff_name(rs.getString("TEN_NV"));
					
					
					arrStaff.add(staff);
				}
				
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
			
		}
		return arrStaff;
		
	}

	

}
