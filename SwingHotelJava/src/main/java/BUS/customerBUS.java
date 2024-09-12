package BUS;

import DTO.customerDTO;
import DAO.customerDAO;

import java.util.ArrayList;

public class customerBUS {
	customerDAO ctmDAO= new customerDAO();
//	Lấy tất cả khách hàng thêm vào mảng
	public ArrayList<customerDTO> getAllCustomer(){
		return ctmDAO.getAllCustomers();
	}
//	Kiểm tra thêm khách hàng có trùng ID hay không và thêm KH thành công hay không
	public String addCustomer(customerDTO ctm) {
		if(ctmDAO.hasCustomerID(ctm.getCustomer_id())) {
			return "Mã khách hàng đã tồn tại";
		}
		if(ctmDAO.addCustomer(ctm)) {
			return "Thêm khách hàng thành công";
		}
		return "Thêm khách hàng thất bại";
	}
//	Thông báo xóa khách hàng
	public String deleteCustomer(int ctmId) {
		if(ctmDAO.deleteCustomer(ctmId)) {
			return"Đã xóa khách hàng";
		}
		return "Xóa khách hàng thất bại";
	}
//	Thông báo chỉnh sửa thông tin khách hàng
	public String editCustomer(customerDTO ctm) {
		if(ctmDAO.editCustomer(ctm)) {
			return "Chỉnh sửa thông tin khách hàng thành công";
		}
		return "Chỉnh sửa thông tin khách hàng thất bại";
	}

}
