package BUS;
import DTO.staffDTO;
import DAO.staffDAO;
import java.util.ArrayList;

public class staffBUS {
	private staffDAO stDAO= new staffDAO();

//	Lấy mảng nhân viên
	public ArrayList<staffDTO> getAllStaff(){
		return stDAO.getAllStaff();
	}
//	Kiểm tra thêm nhân viên
	public String addStaff(staffDTO staff) {
		if(stDAO.addStaff(staff)) {
			return "Thêm nhân viên thành công";
		}
		return "Thêm nhân viên thất bại";
	}
//	Kiểm tra xóa nhân viên
	public String deleteStaff(int idStaff) {
		if(stDAO.deleteStaff(idStaff)) {
			return "Xóa nhân viên thành công";
		}
		return "Xóa nhân viên thất bại";
	}
//	Kiểm tra chỉnh sửa nhân viên
	public String editStaff(staffDTO staff) {
		if(stDAO.editStaff(staff)) {
			return "Chỉnh sửa thông tin nhân viên thành công";
		}
		return "Chỉnh sửa thông tin nhân viên thất bại";
	}
//	Lấy mảng nhân viên gồm id với tên
	public ArrayList<staffDTO> getAllStaffWithIDAndName(){
		return stDAO.getAllStaffWithIDAndName();
	}

}
