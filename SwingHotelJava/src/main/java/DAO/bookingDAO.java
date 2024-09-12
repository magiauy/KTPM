package DAO;
import DTO.bookingDTO;

import java.sql.*;
import java.util.ArrayList;
import DAO.connectToSQL;

public class bookingDAO {
	private connectToSQL con= new connectToSQL();
//	Lấy mảng phiếu đặt
	public ArrayList<bookingDTO> getAllBooking(){
		ArrayList<bookingDTO> arrBooking = new ArrayList<bookingDTO>();
		if(con.openConectionToSQL()) {
			try {
				String sql="Select * from phieudatphong";
				
				Statement stmt=con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					bookingDTO booking = new bookingDTO();
					
					booking.setBooking_id(rs.getInt("ID_PHIEUDAT"));
					booking.setDate_start_booking(rs.getDate("NGAYDAT"));
					booking.setDate_end_booking(rs.getDate("NGATRA"));
					booking.setBooking_staff_id(rs.getInt("ID_NV"));
					booking.setBooking_customer_id(rs.getInt("ID_KH"));
					
					arrBooking.add(booking);
				} 
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				con.closeConnectionToSQL();
			}
		}
		return arrBooking;
	}
//	Thêm phiếu đặt
	public boolean addBooking(bookingDTO booking) {
		boolean result=false;
		if(con.openConectionToSQL()) {
			try {
				String sql="INSERT INTO phieudatphong  VALUES (?, ?, ?, ?, ?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
			
				stmt.setInt(1, booking.getBooking_id());
				stmt.setDate(2,booking.getDate_start_booking());
				stmt.setDate(3,booking.getDate_end_booking());
				stmt.setInt(4,booking.getBooking_staff_id() );
				stmt.setInt(5, booking.getBooking_customer_id());
				
				
				if(stmt.executeUpdate()>=1) {
					result=true;
				}
				
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				con.closeConnectionToSQL();
			}
		}
		return result;
		
	}

}
