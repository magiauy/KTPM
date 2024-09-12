package DAO;

import DTO.customerDTO;
import java.sql.*;
import java.util.ArrayList;

public class customerDAO {
	private Connection con;//Thiết lập kết nối
//	Thiết lập & Kiểm tra kết nối
	public boolean openConectionToCustomer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/hotelmanager?serverTimezone=UTC";
			String username="root";
			String password="";
			
			con=DriverManager.getConnection(url,username,password);
			
			return true;
			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
//	Đóng kết nối
	public void closeConnectionToCustomer() {
		try {
			if(con!=null) {
				con.close();
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
//	Lấy toàn bộ customer từ database trả về mảng
	public ArrayList<customerDTO> getAllCustomers(){
		ArrayList<customerDTO> arrCustomer= new ArrayList<customerDTO>();
		if(openConectionToCustomer()) {
			try {
				String sql="Select * from khachhang";
				
				Statement stmt= con.createStatement();
				
				ResultSet rs= stmt.executeQuery(sql);
				
				while(rs.next()) {
					customerDTO ctm = new customerDTO();
					ctm.setCustomer_id(rs.getInt("ID_KH"));
					ctm.setCustomer_name(rs.getString("TEN_KH"));
					ctm.setCustomer_gender(rs.getString("PHAI_KH"));
					ctm.setCustomer_phone(rs.getString("SDT_KH"));
					ctm.setCustomer_email(rs.getString("EMAIL_KH"));
					ctm.setCustomer_cccd(rs.getString("CCCD_KH"));
					
					
					arrCustomer.add(ctm);//Thêm đối tượng ctm vào mảng arrCustomer
				}
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToCustomer();
			}
		}
		return arrCustomer;
	}
//	Kiểm tra thêm  khách hàng vào database
	public boolean addCustomer(customerDTO ctm) {
		boolean result= false;
		if(openConectionToCustomer()) {
			try {
				String sql="Insert into khachhang values(?,?,?,?,?,?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setInt(1,ctm.getCustomer_id());
				stmt.setString(2,ctm.getCustomer_name());
				stmt.setString(3,ctm.getCustomer_gender());
				stmt.setString(4,ctm.getCustomer_phone());
				stmt.setString(5,ctm.getCustomer_email());
				stmt.setString(6,ctm.getCustomer_cccd());
				
				if(stmt.executeUpdate()>=1) {
					result= true;
				}
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToCustomer();
			}
		}
		return result;
	}
	// Delete Customertheo ID, boolean
	public boolean deleteCustomer(int ctmId) {
	    boolean result = false;
	    if(openConectionToCustomer()) {
	        try {
	            String sql = "DELETE FROM khachhang WHERE ID_KH = ?";
	            
	            PreparedStatement stmt = con.prepareStatement(sql);
	            
	            stmt.setInt(1, ctmId);
	            
	            if(stmt.executeUpdate() > 0) {
	                result = true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	        } finally {
	            closeConnectionToCustomer();
	        }
	    }
	    return result;
	}

//	Sửa thông tin khách hàng boolean
	public boolean editCustomer(customerDTO ctm) {
	    boolean result = false;
	    if (openConectionToCustomer()) {
	        try {
	            String sql = "UPDATE khachhang SET TEN_KH = ?, PHAI_KH = ?, SDT_KH = ?, EMAIL_KH = ?, CCCD_KH = ? WHERE ID_KH = ?";
	            
	            PreparedStatement stmt = con.prepareStatement(sql);
	            
	            stmt.setString(1, ctm.getCustomer_name());
	            stmt.setString(2, ctm.getCustomer_gender());
	            stmt.setString(3, ctm.getCustomer_phone());
	            stmt.setString(4, ctm.getCustomer_email());
	            stmt.setString(5, ctm.getCustomer_cccd());
	            stmt.setInt(6, ctm.getCustomer_id());
	            
	            if (stmt.executeUpdate() > 0) {
	                result = true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	        } finally {
	            closeConnectionToCustomer();
	        }
	    }
	    return result;
	}
//	Kiểm tra trùng ID customer
	public boolean hasCustomerID(int id) {
		boolean result= false;
		if(openConectionToCustomer()) {
			try {
				String sql="Select * from khachhang where ID_KH= ?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, id);

	            ResultSet rs = stmt.executeQuery();

	            result = rs.next();
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToCustomer();
			}
		}
		return result;
	}
//	Kiểm tra trùng CCCD
	

}
