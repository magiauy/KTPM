package BUS;
import java.util.ArrayList;
import DTO.detailBookingDTO;
import DTO.detailServiceDTO;
import DAO.detailServiceDAO;
public class detailServiceBUS {
	private detailServiceDAO dlServiceDAO= new detailServiceDAO();
//	Thêm chi tiết dịch vụ
	public String addDetailSerice(detailServiceDTO dlService) {
		if(dlServiceDAO.addDetailSerice(dlService)) {
			return "Thêm chi tiết dịch vụ thành công";
		}
		return "Thêm chi tiết dịch vụ thất bại";
	}

}
