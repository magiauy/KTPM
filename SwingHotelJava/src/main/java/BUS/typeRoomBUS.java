package BUS;
import DTO.typeRoomDTO;
import DAO.typeRoomDAO;

import java.util.ArrayList;

public class typeRoomBUS {
	typeRoomDAO trDAO=  new typeRoomDAO();
//	Lấy tất cả loại phòng
	public ArrayList<typeRoomDTO> getAllTypeRooms(){
		return trDAO.getAllTypeRooms();
	}
//	Lấy phòng trả về mảng với ID va TEN_LOAIPHG
	public ArrayList<typeRoomDTO> getAllTypeRoomsIdAndName(){
		return trDAO.getAllTypeRoomsIdAndName();
	}
//	Kiểm tra thêm Loại phòng
	public String addTypeRoom(typeRoomDTO tr) {
		if(trDAO.hasIDTypeRoom(tr.getTypeRoom_id())) {
			return "Mã loại phòng đã tồn tại";
		}
		if(trDAO.addTypeRoom(tr)) {
			return "Thêm loại phòng thành công";
		}
		return "Thêm loại phòng thất bại";
	}
//	Kiểm tra xóa loại phòng
	public String deleteTypeRoom(int trId) {
		if(trDAO.deleteTypeRoom(trId)) {
			return "Xóa loại phòng thành công";
		}
		return "Xóa loại phòng thất bại";
	}
//	Kiểm tra chỉnh sửa thông tin phòng
	public String editTypeRoom(typeRoomDTO tr) {
		if(trDAO.editTypeRoom(tr)) {
			return "Chỉnh sửa loại phòng thành công";
		}
		return "Chỉnh sửa loại phòng thất bại";
	}

}
