package DAO;

import DTO.roomDTO;
import DAO.connectToSQL;
import java.util.ArrayList;
import java.sql.*;


public class roomDAO extends connectToSQL{
//	Kiem tra them Phong
	public boolean addRoom(roomDTO r) {
		boolean result=false;
		if(openConectionToSQL()) {
			try {
				String sql="Insert into phong values(?,?,?,?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,r.getRoom_id());
				stmt.setString(2,r.getRoom_name());
				stmt.setInt(3,r.getTypeRoome_id());
				stmt.setString(4, r.getRoom_status());
				
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
////	Kiem tra trung ID
//	public boolean hasIDRoom(int idRoom) {
//		boolean result=false;
//		if(openConectionToSQL()) {
//			try {
//				String sql="Select * from phong where ID_PHG= ?";
//
//				PreparedStatement stmt=con.prepareStatement(sql);
//
//				stmt.setInt(1,idRoom);
//
//				ResultSet rs=stmt.executeQuery();
//
//				result=rs.next();
//
//			}catch(SQLException e) {
//				System.out.println(e);
//
//			}finally {
//				closeConnectionToSQL();
//			}
//		}
//		return result;
//	}
////	Kiểm tra trùng tên
//	public boolean hasNameRoom(String name) {
//	    boolean result = false;
//	    if (openConectionToSQL()) {
//	        try {
//
//	            String sql = "SELECT * FROM phong WHERE TEN_PHG = ?";
//
//	            PreparedStatement stmt = con.prepareStatement(sql);
//
//	            stmt.setString(1, name);
//
//	            ResultSet rs = stmt.executeQuery();
//
//	            result = rs.next();
//
//	        } catch (SQLException e) {
//	            System.out.println(e);
//	        } finally {
//	            closeConnectionToSQL();
//	        }
//	    }
//	    return result;
//	}

//	Kiểm tra xóa phòng
	public boolean deleteRoom(int idRoom) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Delete from phong where ID_PHG= ?";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,idRoom);
				
				if(stmt.executeUpdate()>0) {
					result= true;
				}
				
			}catch(SQLException e) {
				
			}finally {
				closeConnectionToSQL();
			}	
		}
		return result;
	}
		public boolean updateStatusRoomtostop(roomDTO r) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Update phong set TINHTRANG_PHG= ? where ID_PHG= ?";

				PreparedStatement stmt=con.prepareStatement(sql);

				stmt.setString(1,"NGỪNG KINH DOANH");
				stmt.setInt(2, r.getRoom_id());


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
//	Kiểm tra chỉnh sửa thông tin phòng
	public boolean editRoom(roomDTO r) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Update phong set TEN_PHG= ?, ID_LOAIPHG= ?, TINHTRANG_PHG= ? where ID_PHG= ?";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setString(1,r.getRoom_name());
				stmt.setInt(2,r.getTypeRoome_id());
				stmt.setString(3,r.getRoom_status());
				stmt.setInt(4, r.getRoom_id());
				
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
//	Lấy tất cả những phòng có trạng thái trống và đang hoạt động và giá phòng
	public ArrayList<roomDTO> getAllRoomEmptyAndActive(){
		ArrayList<roomDTO> arrRoomEmpty= new ArrayList<roomDTO>();
		if(openConectionToSQL()) {
			try {
				String sql = "SELECT phong.*, loaiphong.TEN_LOAIPHG, loaiphong.DONGIA_PHG " +
			             "FROM phong, loaiphong " +
			             "WHERE phong.ID_LOAIPHG = loaiphong.ID_LOAIPHG " + // Thêm dấu cách ở đây
			             "AND phong.TINHTRANG_PHG='TRỐNG'"+
			             "AND loaiphong.TRANGTHAI_LOAIPHG='Hoạt động'";
			
				Statement stmt=createStatement();
				
				ResultSet rs= stmt.executeQuery(sql);
				
				while(rs.next()) {
					roomDTO room = new roomDTO();
					
					room.setRoom_id(rs.getInt("ID_PHG"));
					room.setRoom_name(rs.getString("TEN_PHG"));
					room.setTypeRoome_id(rs.getInt("ID_LOAIPHG"));
					room.setRoom_status(rs.getString("TINHTRANG_PHG"));
					room.setType_room_name(rs.getString("TEN_LOAIPHG"));
					room.setRoom_fee(rs.getDouble("DONGIA_PHG"));
					
					arrRoomEmpty.add(room);
				}
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrRoomEmpty;
	}

//	Lấy danh sách phòng
	public ArrayList<roomDTO> getAllRooms(){
		ArrayList<roomDTO> arrRoom= new ArrayList<roomDTO>();
		if(openConectionToSQL()) {
			try {
				String sql="SELECT * from phong";
				
				Statement stmt=con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					roomDTO r= new roomDTO();
					
					r.setRoom_id(rs.getInt("ID_PHG"));
					r.setRoom_name(rs.getString("TEN_PHG"));
					r.setTypeRoome_id(rs.getInt("ID_LOAIPHG"));
					r.setRoom_status(rs.getString("TINHTRANG_PHG"));
					
					arrRoom.add(r);
				}
				
			}catch(SQLException e) {
				
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrRoom;
	}
//	Lấy phòng ở trạng thái HOẠT ĐỘNG
	public ArrayList<roomDTO> getAllRoomActive(){
		ArrayList<roomDTO> arrRoomEmpty= new ArrayList<roomDTO>();
		if(openConectionToSQL()) {
			try {
				String sql = "SELECT phong.*, loaiphong.TEN_LOAIPHG " +
			             "FROM phong, loaiphong " +
			             "WHERE phong.ID_LOAIPHG = loaiphong.ID_LOAIPHG " + 
			             "AND loaiphong.TRANGTHAI_LOAIPHG='Hoạt động'";
			
				Statement stmt=con.createStatement();
				
				ResultSet rs= stmt.executeQuery(sql);
				
				while(rs.next()) {
					roomDTO room = new roomDTO();
					
					room.setRoom_id(rs.getInt("ID_PHG"));
					room.setRoom_name(rs.getString("TEN_PHG"));
					room.setTypeRoome_id(rs.getInt("ID_LOAIPHG"));
					room.setRoom_status(rs.getString("TINHTRANG_PHG"));
					
					room.setType_room_name(rs.getString("TEN_LOAIPHG"));
					
					arrRoomEmpty.add(room);
				}
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrRoomEmpty;
	}
//	Update lại trạng thái phòng Đã đặt
	public boolean updateStatusRoom(roomDTO r) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Update phong set TINHTRANG_PHG= ? where ID_PHG= ?";
				
				PreparedStatement stmt=con.prepareStatement(sql);
			
				stmt.setString(1,"ĐÃ ĐẶT");
				stmt.setInt(2, r.getRoom_id());
				
				
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
//	Update lại trạng tháu phòng trống
	public boolean updateStatusRoomEmpty(roomDTO r) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Update phong set TINHTRANG_PHG= ? where ID_PHG= ?";
				
				PreparedStatement stmt=con.prepareStatement(sql);
			
				stmt.setString(1,"TRỐNG");
				stmt.setInt(2, r.getRoom_id());
				
				
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
	
	
	
	
	
//	Lấy danh sách phòng
	public ArrayList<roomDTO> getAllRoom(){
		ArrayList<roomDTO> arrRoom= new ArrayList<roomDTO>();
		if(openConectionToSQL()) {
			try {
				String sql="Select * from phong";
				
				Statement stmt=con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					roomDTO room = new roomDTO();
					
					room.setRoom_id(rs.getInt("ID_PHG"));
					room.setRoom_name(rs.getString("TEN_PHG"));
					room.setRoom_status(rs.getString("TRANGTHAI"));
					room.setTypeRoome_id(rs.getInt("ID_LOAIPHG"));
					
					arrRoom.add(room);
				}
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrRoom;
	}

}
