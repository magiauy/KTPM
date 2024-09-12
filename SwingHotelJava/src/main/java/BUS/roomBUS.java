package BUS;
import DTO.roomDTO;
import DAO.roomDAO;
import java.util.ArrayList;

public class roomBUS {
	private roomDAO rDAO= new roomDAO();
//	Lấy tất cả phòng
	public ArrayList<roomDTO> getAllRooms(){
		return rDAO.getAllRooms();
	}
//	Kiem tra them phong
	public String addRoom(roomDTO r) {
		if(rDAO.hasIDRoom(r.getRoom_id())) {
			return "ID phòng đã tồn tại";
		}
		if(rDAO.hasNameRoom(r.getRoom_name())) {
			return "Tên phòng đã tồn tại";
		}
		if(rDAO.addRoom(r)) {
			return "Thêm phòng thành công";
		}
		return "Thêm phòng thất bại";
	}
//	Kiểm tra xóa phòng
	public String deleteRoom(int idRoom) {
		if(rDAO.deleteRoom(idRoom)) {
			return "Xóa phòng thành công";
		}
		return "Xóa phòng thất bại";
	}
//	Kiểm tra chỉnh sửa thông tin phòng
	public String editRoom(roomDTO r) {
		if(rDAO.hasNameRoom(r.getRoom_name())) {
			return "Tên phòng đã tồn tại";
		}
		if(rDAO.editRoom(r)) {
			return "Chỉnh sửa thông tin phòng thành công";
		}
		return "Chỉnh sửa thông tin phòng thất bại";
	}
//	Lấy tất cả phòng TRỐNG và HOẠT ĐỘNG
	public ArrayList<roomDTO> getAllRoomEmptyAndActive(){
		return rDAO.getAllRoomEmptyAndActive();
	}

//	Lấy phòng HOẠT ĐỘNG
	public ArrayList<roomDTO> getAllRoomActive(){
		return rDAO.getAllRoomActive();
	}
//	Update lại tráng thái phòng đã đặt
	public String updateStatusRoom(roomDTO r) {
		if(rDAO.updateStatusRoom(r)) {
			return "Update trạng thái phòng thành công";
		}
		return "Update trạng thái phòng thất bại";
	}
//	Update lại trạng thái phòng trống
	public String updateStatusRoomEmpty(roomDTO r) {
		if(rDAO.updateStatusRoomEmpty(r)) {
			return "Update trạng thái phòng thành công";
		}
		return "Update trạng thái phòng thất bại";
	}
	

}
