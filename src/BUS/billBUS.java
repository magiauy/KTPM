package BUS;
import java.util.ArrayList;
import DTO.billDTO;
import DAO.billDAO;
public class billBUS {
	private billDAO billDAO= new billDAO();
//	Lấy mảng hóa đơn
	public ArrayList<billDTO> getAllBill(){
		return billDAO.getAllBill();
	}
//	Thêm bill
	public String addBill(billDTO bill) {
		if(billDAO.addBill(bill)) {
			return "Thêm hóa đơn thành công";
		}
		return "Thêm hóa đơn thất bại";
	}
//	Lấy danh sách bill đã kết
	public ArrayList<billDTO> getAllBillDetail(){
		return billDAO.getAllBillDetail();
	}

}
