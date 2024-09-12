package BUS;

import DTO.customerDTO;
import DAO.customerDAO;

import java.util.ArrayList;

public class customerBUS {
	ArrayList<customerDTO> arrCustomer= new ArrayList<customerDTO>();
	customerDAO ctmDAO= new customerDAO();
	public void loaddata(){
		arrCustomer= ctmDAO.getAllCustomers();

	}
//	Lấy tất cả khách hàng thêm vào mảng
	public ArrayList<customerDTO> getAllCustomer(){
		return ctmDAO.getAllCustomers();
	}
//	Kiểm tra thêm khách hàng có trùng ID hay không và thêm KH thành công hay không
	public String addCustomer(customerDTO ctm) {
		if (checkID(ctm.getCustomer_id())) {
			return "ID đã tồn tại";
		}
		if (checkPhone(ctm.getCustomer_phone())) {
			return "Số điện thoại đã tồn tại với ID là : " + getidbyphone(ctm.getCustomer_phone()) ;
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
	public customerDTO getcustomerbyID(int ctmId) {
	   for (customerDTO ctm : arrCustomer) {
	       if (ctm.getCustomer_id() == ctmId) {
	           return ctm;
	       }
	   }
	   return null;
	}
	public int getidbyphone(String phone){
		for (customerDTO ctm : arrCustomer) {
			if (ctm.getCustomer_phone().equals(phone)) {
				return ctm.getCustomer_id();
			}
		}
		return 0;
	}
	public boolean checkID(int ctmId) {
		for(customerDTO ctm: arrCustomer) {
			if(ctm.getCustomer_id()==ctmId) {
				return true;
			}
		}
		return false;
	}
	public boolean checkPhone(String phone) {
		for(customerDTO ctm: arrCustomer) {
			if(ctm.getCustomer_phone().equals(phone)) {
				return true;
			}
		}
		return false;
	}

}
