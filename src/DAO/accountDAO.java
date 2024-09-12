package DAO;
import DTO.accountDTO;
import DTO.staffDTO;

import java.util.ArrayList;
import java.sql.*;
import DAO.connectToSQL;
// Câu lệnh sql có tham số thì dùng PreparedStatement

 
public class accountDAO {

	private connectToSQL con= new connectToSQL();
//	Lấy tất cả tài khoản trả về mảng
	public ArrayList<accountDTO> getAllAccount(){
		ArrayList<accountDTO> arrAccount = new ArrayList<accountDTO>();
		if(con.openConectionToSQL()) {
			try {
				String sql="Select * from taikhoan";
				
				Statement stmt= con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					accountDTO account= new accountDTO();
					
					account.setAccount_id(rs.getInt("ID_TK"));
					account.setAccount_username(rs.getString("USERNAME"));
					account.setAccount_password(rs.getString("password"));
					account.setAccount_id_nv(rs.getInt("ID_NV"));
					
					arrAccount.add(account);
				}
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				con.closeConnectionToSQL();
			}
		}
		return arrAccount;
	}
//	Kiểm tra thêm tài khoản
	public boolean addAccount(accountDTO account) {
		boolean result= false;
		if(con.openConectionToSQL()) {
			try {
				String sql="Insert into taikhoan values(?,?,?,?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1, account.getAccount_id());
				stmt.setString(2,account.getAccount_username());
				stmt.setString(3,account.getAccount_password());
				stmt.setInt(4,account.getAccount_id_nv());
		
				
				if(stmt.executeUpdate()>=1) {
					result=true;
				}
				
				
			}catch(SQLException e) {
				System.out.println(e);
				
			}finally{
				con.closeConnectionToSQL();
			}
		}
		return result;
	}

//	Kiểm tra xóa tài khoản
	public boolean deleteAccount(int idAccount) {
		boolean result = false;
		if(con.openConectionToSQL()) {
			try {
				String sql= "Delete from taikhoan where ID_TK= ?";
				
				PreparedStatement stmt= con.prepareStatement(sql);
				
				stmt.setInt(1, idAccount);
				
				if(stmt.executeUpdate()>0) {
					result= true;
				}
				
			}catch(SQLException e) {
				System.out.println(e);
				
			}finally {
				con.closeConnectionToSQL();
			}
		}
		return result;
	}
//	Kiểm tra chỉnh sửa tài khoản
	public boolean editAccount(accountDTO account) {
		boolean result = false;
		if(con.openConectionToSQL()) {
			try {
				String sql="Update taikhoan set USERNAME= ?, password= ?, ID_NV= ? where ID_TK= ?";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setString(1,account.getAccount_username());
				stmt.setString(2,account.getAccount_password());
				stmt.setInt(3,account.getAccount_id_nv());
				stmt.setInt(4, account.getAccount_id());
				
				if(stmt.executeUpdate()>0) {
					result= true;
				}
				
			}catch(SQLException e) {
				System.out.println(e);
				
			}finally {
				con.closeConnectionToSQL();
			}
		}
		return result;
	}
//	Kiểm tra nhân viên đã có tài khoản(1 nhân viên có 1 tài khoản duy nhất)
	public boolean hasIDNVInAccount(int idIDNV) {
		boolean result=false;
		if(con.openConectionToSQL()) {
			try {
				String sql="Select * from taikhoan where ID_NV= ?";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,idIDNV);
				
				ResultSet rs=stmt.executeQuery();
				
				result=rs.next();
				
			}catch(SQLException e) {
				System.out.println(e);
				
			}finally {
				con.closeConnectionToSQL();
			}
		}
		return result;
	}
//	Xử lý đăng nhập, lấy thông tin tài khoản ứng với nhân viên tương ứng, đồng thời lấy hết các thuộc tính trong bảng nhân viên
	public accountDTO login(String username, String password) {
	    accountDTO account = null;
	    if(con.openConectionToSQL()) {
	        try {
	            String sql = "SELECT "
	            		+ "taikhoan.ID_TK, taikhoan.USERNAME, taikhoan.ID_NV, "
	            		+ "nhanvien.ID_NV,nhanvien.TEN_NV, nhanvien.EMAIL, nhanvien.SDT_NV,nhanvien.CCCD_NV,nhanvien.PHAI_NV, nhanvien.ID_CHUCVU "
	            		+ "FROM taikhoan "
	            		+ "INNER JOIN nhanvien ON taikhoan.ID_NV = nhanvien.ID_NV "
	            		+ "WHERE taikhoan.USERNAME = ? AND taikhoan.password = ?";

	            PreparedStatement stmt = con.prepareStatement(sql);

	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            ResultSet rs = stmt.executeQuery();

	            if(rs.next()) {
	                account = new accountDTO();
	                account.setAccount_id(rs.getInt("ID_TK"));
	                account.setAccount_username(rs.getString("USERNAME"));
	                account.setAccount_id_nv(rs.getInt("ID_NV"));
	                // Set thông tin nhân viên vào account
	                account.setAccount_NVID(rs.getInt("ID_NV"));
	                account.setAccount_name(rs.getString("TEN_NV"));
	                account.setAccount_email(rs.getString("EMAIL"));
	                account.setAccount_phone(rs.getString("SDT_NV"));
	                account.setAccount_cccd(rs.getString("CCCD_NV"));
	                account.setAccount_gender(rs.getString("PHAI_NV"));
	                account.setAccount_id_position(rs.getInt("ID_CHUCVU"));
	               
	                
	                int roleID = rs.getInt("ID_CHUCVU");
	                String role = (roleID == 1) ? "Admin" : "Staff"; // ID_CHUCVU = 1 là Admin, còn lại là Staff
	                account.setRole(role);
	            }
	        } catch(SQLException e) {
	            System.out.println(e);
	        } finally {
	            con.closeConnectionToSQL();
	        }
	    }
	    return account;
	}

//	Thêm kiểm tra username không được trùng

	

}
