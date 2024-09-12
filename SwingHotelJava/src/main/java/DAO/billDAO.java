package DAO;
import java.util.ArrayList;
import java.util.HashMap;

import DTO.billDTO;
import java.sql.*;


public class billDAO extends connectToSQL{
//	Lấy tất cả hóa đơn
	public ArrayList<billDTO> getAllBill(){
		ArrayList<billDTO> arrBill = new ArrayList<>();
		if(openConectionToSQL()) {
			try {
				String sql = "Select * from hoadon";
				
				Statement stmt =con.createStatement();
				
				ResultSet rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					billDTO bill = new billDTO();
					
					bill.setId_hoadon(rs.getInt("ID_HD"));
					bill.setId_kh(rs.getInt("ID_KH"));
					bill.setId_phieudat(rs.getInt("ID_PHIEUDAT"));
					bill.setNgaylap_hd(rs.getDate("NGAYLAP_HD"));
					bill.setTongtien_hd(rs.getDouble("TONGTIEN_HD"));
					
					arrBill.add(bill);
				}
				
			}catch(SQLException e) {
				
			}finally {
				closeConnectionToSQL();
			}
		}
		return arrBill;
	}
//	Thêm bill
	public boolean addBill(billDTO bill) {
		boolean result= false;
		if(openConectionToSQL()) {
			try {
				String sql="Insert into hoadon values(?,?,?,?,?)";
				
				PreparedStatement stmt=con.prepareStatement(sql);
				
				stmt.setInt(1,bill.getId_hoadon());
				stmt.setDouble(2,bill.getTongtien_hd());
				stmt.setDate(3,bill.getNgaylap_hd());
				stmt.setInt(4, bill.getId_kh());
				stmt.setInt(5, bill.getId_phieudat());
				
				if(stmt.executeUpdate()>=1) {
					result=true;
				}
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
		}
		return result;
	}
//	Lấy mảng bill đã kết với bảng "phong, khachhang, phieudat, chitietphieudat"
	public ArrayList<billDTO> getAllBillDetail(){
		ArrayList<billDTO> arrBillDetail =new ArrayList<billDTO>();
		HashMap<Integer, Boolean> idHDMap = new HashMap<>(); 
		if(openConectionToSQL()) {
			try {
				String sql = "SELECT hd.*, p.*, kh.*, pdp.*, ctp.* " +
	                     "FROM hoadon hd " +
	                     "JOIN khachhang kh ON hd.ID_KH = kh.ID_KH " +
	                     "JOIN phieudatphong pdp ON hd.ID_PHIEUDAT = pdp.ID_PHIEUDAT " +
	                     "JOIN chitietphieudat ctp ON pdp.ID_PHIEUDAT = ctp.ID_PHIEUDAT " +
	                     "JOIN phong p ON ctp.ID_PHG = p.ID_PHG " ;
	              
				
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery(sql);
		        while (rs.next()) {
		        	int idHD = rs.getInt("ID_HD");
	                // Kiểm tra xem ID_HD đã tồn tại trong HashMap chưa
	                if (!idHDMap.containsKey(idHD)) {
	                    billDTO bill = new billDTO();

	                    // Thiết lập thuộc tính cho billDTO
	                    bill.setId_hoadon(idHD);
	                    bill.setId_kh(rs.getInt("ID_KH"));
	                    bill.setId_phieudat(rs.getInt("ID_PHIEUDAT"));
	                    bill.setNgaylap_hd(rs.getDate("NGAYLAP_HD"));
	                    bill.setTongtien_hd(rs.getDouble("TONGTIEN_HD"));
	                    bill.setName_room(rs.getString("TEN_PHG"));
	                    bill.setName_customer(rs.getString("TEN_KH"));
	                    bill.setNgaythue(rs.getDate("NGAYDAT"));
	                    bill.setNgaytra(rs.getDate("NGATRA"));

	                    arrBillDetail.add(bill);

	                    // Đánh dấu ID_HD đã xuất hiện trong HashMap
	                    idHDMap.put(idHD, true);
	                }
	            }
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				closeConnectionToSQL();
			}
			
		}
		return arrBillDetail;
	}
	
}
