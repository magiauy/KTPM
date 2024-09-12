package DAO;

import java.sql.*;
import DTO.serviceDTO;
import java.util.ArrayList;

public class serviceDAO extends connectToSQL{

    // Lấy toàn bộ service từ database trả về mảng
    public ArrayList<serviceDTO> getAllService() {
        ArrayList<serviceDTO> arrService = new ArrayList<serviceDTO>();
        if (openConectionToSQL()) {
            try {
                String sql = "Select * from dichvu";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    serviceDTO sv = new serviceDTO();
                    sv.setService_id(rs.getInt("ID_DV"));
                    sv.setService_name(rs.getString("TEN_DV"));
                    sv.setService_price(rs.getDouble("DONGIA_DV"));
                    sv.setService_description(rs.getString("MOTA_DV"));
                    arrService.add(sv);// Thêm đối tượng sv vào mảng arrService
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                closeConnectionToSQL();
            }
        }
        return arrService;
    }

    public boolean addService(serviceDTO sv) {
        boolean result = false;
        if (openConectionToSQL()) {
            try {
                String sql = "Insert into dichvu values (?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, sv.getService_id());
                stmt.setString(2, sv.getService_name());
                stmt.setDouble(3, sv.getService_price());
                stmt.setString(4, sv.getService_description());
                if (stmt.executeUpdate() >= 1) {
                    result = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                closeConnectionToSQL();
            }
        }
        return result;
    }

    // Delete Service theo ID , boolean
    public boolean deleteService(int svId) {
        boolean result = false;
        if (openConectionToSQL()) {
            try {
                String sql = "Delete from dichvu where ID_DV = ? ";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, svId);
                if (stmt.executeUpdate() > 0) {
                    result = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                closeConnectionToSQL();
            }
        }
        return result;
    }

    public boolean editService(serviceDTO sv) {
        boolean result = false;
        if (openConectionToSQL()) {
            try {
                String sql = "update dichvu set TEN_DV = ? , DONGIA_DV = ? , MOTA_DV = ? where ID_DV = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, sv.getService_name());
                stmt.setDouble(2, sv.getService_price());
                stmt.setString(3, sv.getService_description());
                stmt.setInt(4, sv.getService_id());
                if (stmt.executeUpdate() > 0) {
                    result = true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                closeConnectionToSQL();
            }
        }
        return result;
    }

    public boolean hasServiceID(int id) {
        boolean result = false;
        if (openConectionToSQL()) {
            try {
                String sql = "SELECT * FROM dichvu WHERE ID_DV = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                result = rs.next();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                closeConnectionToSQL();
            }
        }
        return result;
    }
//    Lấy mảng dịch vụ kết với "chitietdichvu, hoadon"
    public ArrayList<serviceDTO> gettAllServiceInBIll(){
    	ArrayList<serviceDTO> arrServiceInBill= new ArrayList<serviceDTO>();
    	if(openConectionToSQL()) {
    		try {
    			String sql="SELECT dv.*, ctdv.*, hd.ID_HD " +
                        "FROM dichvu dv " +
                        "JOIN chitietdichvu ctdv ON dv.ID_DV = ctdv.ID_DV " +
                        "JOIN hoadon hd ON ctdv.ID_HD = hd.ID_HD";
    			Statement stmt=con.createStatement();
    			
    			ResultSet rs=stmt.executeQuery(sql);
    			
    			while(rs.next()) {
    				serviceDTO service = new serviceDTO();

    				service.setService_name(rs.getString("TEN_DV"));
    				service.setSoluong_dv(rs.getInt("SOLUONG_DV"));
    				service.setPhi_moidichvu(rs.getDouble("TONGTIEN_DV"));
    				service.setId_hoadon(rs.getInt("ID_HD"));   
    				
    				arrServiceInBill.add(service);
    			}
    			
    		}catch(SQLException e) {
    			System.out.println(e);
    		}finally {
    			closeConnectionToSQL();
    		}
    	}
    		return arrServiceInBill;
    }
}