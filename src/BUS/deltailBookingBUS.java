package BUS;
import DTO.detailBookingDTO;

import DAO.deltailBookingDAO;
import java.util.ArrayList;

public class deltailBookingBUS {
	private deltailBookingDAO detailBookingDAO= new deltailBookingDAO();
//	Kiểm tra lấy danh sách chi tiết phiếu đặt
	public ArrayList<detailBookingDTO> getAllDeatailBooking(){
		return detailBookingDAO.getAllDeatailBooking();		
	}

//	Thêm chi tiết phiếu đặt
	public String addDetailBooking(detailBookingDTO detailBooking) {
		if(detailBookingDAO.addDetailBooking(detailBooking)) {
			return "Thêm chi tiết phiếu đặt thành công";
		}
		return "Thêm chi tiết phiếu đặt thất bại";
	}
//	Chi tiết đặt phòng chưa checkout
	public ArrayList<detailBookingDTO> getDetailBookingNotCheckout(){
		return detailBookingDAO.getDetailBookingNotCheckout();
	}
//	Set lại trạng thái
	public String updateStatusDetailBooking(detailBookingDTO dl) {
		if(detailBookingDAO.updateStatusDetailBooking(dl)) {
			return "Cập nhật trạng thái thành công";
		}
		return "Cập nhật trạng thái thất bại";
	}
}
