package DAO;
import DAO.connectToSQL;
import DAO.customerDAO;
import DAO.roomDAO;
import DTO.detailBookingDTO;
import DTO.roomDTO;

import java.sql.*;
import java.util.ArrayList;

public class deltailBookingDAO extends connectToSQL{
//	Chi tiết đặt phòng, kết bảng Phòng, Phiếu đặt, Khách hàng
	public ArrayList<detailBookingDTO> getAllDeatailBooking(){
		ArrayList<detailBookingDTO> arrDetailBooking = new ArrayList<detailBookingDTO>();
		if(openConectionToSQL()) {
			try {
//				Kết 4 bảng á
				  String sql = "SELECT chitietphieudat.*, khachhang.TEN_KH, phong.TEN_PHG " +
	                         "FROM chitietphieudat, phieudatphong, khachhang, phong " +
	                         "WHERE chitietphieudat.ID_PHIEUDAT = phieudatphong.ID_PHIEUDAT " +
	                         "AND phieudatphong.ID_KH = khachhang.ID_KH " +
	                         "AND chitietphieudat.ID_PHG = phong.ID_PHG " +
	                         "ORDER BY chitietphieudat.ID_PHIEUDAT ASC";
				
				
				Statement stmt = con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					  	detailBookingDTO detailBooking= new detailBookingDTO();

		                detailBooking.setDetail_booking_id_step2(rs.getInt("ID_PHIEUDAT"));
		                detailBooking.setDeltai_booking_id_room_step2(rs.getInt("ID_PHG"));
		                detailBooking.setSum_fee_step2(rs.getDouble("TONGTIEN_CTPHIEUDAT"));
		                detailBooking.setDetail_booking_status(rs.getString("TRANGTHAI"));

		                detailBooking.setDetail_booking_room_name(rs.getString("TEN_PHG"));
		                detailBooking.setDetail_booking_customer_name(rs.getString("TEN_KH"));

		                arrDetailBooking.add(detailBooking);
					
				}	
			}catch(SQLException e) {
				System.out.println(e);
				
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrDetailBooking;
	}

//	Thêm chi tiết phiếu đặt
	public boolean addDetailBooking(detailBookingDTO detailBooking) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Insert into chitietphieudat values(?,?,?,?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setInt(1,detailBooking.getDetail_booking_id_step2());
				stmt.setInt(2,detailBooking.getDeltai_booking_id_room_step2());
				stmt.setDouble(3,detailBooking.getSum_fee_step2());
				stmt.setString(4,detailBooking.getDetail_booking_status());
				
				
				if(stmt.executeUpdate()>0) {
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
//	Lấy ra  những chi tiết phiếu đặt ở trạng thái "Chưa checkout"
	public ArrayList<detailBookingDTO> getDetailBookingNotCheckout(){
		ArrayList<detailBookingDTO> arrDetailBookingNotCheckout= new ArrayList<detailBookingDTO>();
		if(openConectionToSQL()) {
			try {
				 String sql = "SELECT chitietphieudat.*, khachhang.*, phong.*, phieudatphong.* " +
                         "FROM chitietphieudat, phieudatphong, khachhang, phong " +
                         "WHERE chitietphieudat.ID_PHIEUDAT = phieudatphong.ID_PHIEUDAT " +
                         "AND phieudatphong.ID_KH = khachhang.ID_KH " +
                         "AND chitietphieudat.ID_PHG = phong.ID_PHG " +
                         "AND chitietphieudat.TRANGTHAI='Chưa checkout' " +
                         "ORDER BY chitietphieudat.ID_PHIEUDAT ASC";
				 Statement stmt =con.createStatement();
				 
				 ResultSet rs=stmt.executeQuery(sql);
				 while(rs.next()) {
					 detailBookingDTO detailBooking = new detailBookingDTO();
					 
					 	detailBooking.setDetail_booking_id_step2(rs.getInt("ID_PHIEUDAT"));
		                detailBooking.setDeltai_booking_id_room_step2(rs.getInt("ID_PHG"));
		                detailBooking.setSum_fee_step2(rs.getDouble("TONGTIEN_CTPHIEUDAT"));
		                detailBooking.setDetail_booking_status(rs.getString("TRANGTHAI"));

		                detailBooking.setDetail_booking_room_id(rs.getInt("ID_PHG"));
		                detailBooking.setDetail_booking_room_name(rs.getString("TEN_PHG"));
		                detailBooking.setDetail_booking_customer_id(rs.getInt("ID_KH"));
		                detailBooking.setDetail_booking_customer_name(rs.getString("TEN_KH"));
		                detailBooking.setDate_start_booking(rs.getDate("NGAYDAT"));
		                detailBooking.setDate_end_booking(rs.getDate("NGATRA"));

		                arrDetailBookingNotCheckout.add(detailBooking);
					
				 }
				 
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrDetailBookingNotCheckout;
	}
//	Set lại trạng thái chi tiết phiếu đặt là "Đã checkout"
	public boolean updateStatusDetailBooking(detailBookingDTO dl) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Update chitietphieudat set TRANGTHAI= ? where ID_PHIEUDAT= ?";
				
				PreparedStatement stmt=con.prepareStatement(sql);
			
				stmt.setString(1,"Đã checkout");
				stmt.setInt(2, dl.getDetail_booking_id_step2());
				
				
				if(stmt.executeUpdate()>0) {
					result= true;
				}
				
			}catch(SQLException e) {
				System.out.println (e);
				
			}finally {
				closeConnectionToSQL();
			}
			
		}
		return result;
	}
}
