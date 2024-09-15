package BUS;
import DTO.staffDTO;
import DAO.staffDAO;
import java.util.ArrayList;

public class staffBUS {
	private staffDAO stDAO= new staffDAO();
	private  ArrayList<staffDTO> arrStaff= new ArrayList<staffDTO>();
	public void loaddata() {
		arrStaff= stDAO.getAllStaff();
	}
//	Lấy mảng nhân viên
	public ArrayList<staffDTO> getAllStaff(){
		return stDAO.getAllStaff();
	}
//	Kiểm tra thêm nhân viên
	public String addStaff(staffDTO staff) {
		if(checkID(staff.getStaff_id())) {
			return "ID đã tồn tại";
		}if (checkCCCD(staff.getStaff_CCCD())) {
			return "CCCD đã tồn tại với ID là : " + getidbycccd(staff.getStaff_CCCD());
		}if (stDAO.addStaff(staff)) {
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
	public String getidbycccd(String CCCD) {
		for (staffDTO staff : arrStaff) {
			if (staff.getStaff_CCCD().equals(CCCD)) {
				return "ID: " + staff.getStaff_id();
			}
		}
		return "Không tìm thấy ID";
	}
	public boolean checkCCCD(String CCCD) {
		for (staffDTO staff : arrStaff) {
			if (staff.getStaff_CCCD().equals(CCCD)) {
				return true;
			}
		}
		return false;
	}
	public boolean checkID(int id) {
		for (staffDTO staff : arrStaff) {
			if (staff.getStaff_id() == id) {
				return true;
			}
		}
		return false;
	}
	public boolean checkstatus(int id){
		for (staffDTO staff : arrStaff) {
			if (staff.getStaff_id() == id) {
				if (staff.getStaff_status().equals("HOẠT ĐỘNG")) {
					return true;
				}
			}
		}
		return false;
	}
}
