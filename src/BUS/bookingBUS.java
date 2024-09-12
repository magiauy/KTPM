package BUS;
import java.util.ArrayList;
import DAO.bookingDAO;
import DTO.bookingDTO;


public class bookingBUS {
	private bookingDAO bookingDAO= new  bookingDAO();
//	Mảng phiếu đặt
	public ArrayList<bookingDTO> getAllBooking(){
		return bookingDAO.getAllBooking();
	}
//	Thêm phiếu đặt
	public String addBooking(bookingDTO booking) {
		if(bookingDAO.addBooking(booking)) {
			return "Thêm phiếu đặt thành công";
		}
		return "Thêm phiếu đặt thất bại";
	}

}
