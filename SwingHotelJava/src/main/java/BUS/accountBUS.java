package BUS;
import DTO.accountDTO;
import java.util.ArrayList;
import DAO.accountDAO;

public class accountBUS {
	private static accountDAO accountDAO= new accountDAO();
//	Lấy danh sách tài khoản trả về mảng
	public ArrayList<accountDTO> getAllAccount(){
		return accountDAO.getAllAccount();
	}
//	Kiểm tra thêm tài khoản
	public String addAccount(accountDTO account) {
		if(accountDAO.hasIDNVInAccount(account.getAccount_id_nv())) {
			return "Nhân viên đã có tài khoản";
		}
		if(accountDAO.addAccount(account)) {
			return "Thêm tài khoản thành công";
		}
		return "Thêm tài khoản thất bại";
	}
//	Kiểm tra xóa tài khoản
	public String deleteAccount(int idAccount) {
		if(accountDAO.deleteAccount(idAccount)) {
			return "Xóa tài khoản thành công";
		}
		return "Xóa tài khoản thất bại";
	}
//	Kiểm tra chỉnh sửa tài khoản
	public String editAccount(accountDTO account) {
		if(accountDAO.editAccount(account)) {
			return "Chỉnh sửa thông tin tài khoản thành công";
		}
		return "Chỉnh sửa thông tin tài khoản thất bại";
	}
//	Xử lý login
	public accountDTO login(String username, String password) {
		return accountDAO.login(username, password);
	}
//	KIểm tra Input username không có khoảng trắng
//	Kiểm tra Input password phải >6
	public static boolean checkAndCreateDatabase() {
		return accountDAO.openforcheck();
	}
	public static void maxConnection(){
		accountDAO.setMaxConnection();
	}

	

}
