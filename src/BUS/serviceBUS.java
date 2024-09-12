package BUS;

import DTO.serviceDTO;
import DAO.serviceDAO;

import java.util.ArrayList;

public class serviceBUS {
    serviceDAO svDAO = new serviceDAO();

    // Lấy tất cả dich vu thêm vào mảng
    public ArrayList<serviceDTO> getAllService() {
        return svDAO.getAllService();
    }

    // Kiểm tra thêm dich vu có trùng ID hay không và thêm DV thành công hay
    // không
    public String addService(serviceDTO sv) {
        if (svDAO.hasServiceID(sv.getService_id())) {
            return "Mã dịch vụ đã tồn tại";
        }
        if (svDAO.addService(sv)) {
            return "Thêm dịch vụ thành công";
        }
        return "Thêm dịch vụ thất bại";
    }

    // Thông báo xóa khách hàng
    public String deleteService(int svId) {
        if (svDAO.deleteService(svId)) {
            return "Đã xóa dịch vụ";
        }
        return "Xóa dịch vụ thất bại";
    }

    // Thông báo chỉnh sửa thông tin khách hàng
    public String editService(serviceDTO sv) {
        if (svDAO.editService(sv)) {
            return "Chỉnh sửa thông tin dịch vụ thành công";
        }
        return "Chỉnh sửa thông tin dịch vụ thất bại";
    }
//    Lấy mảng dịch vụ ứng với BIll tường ứng
    public ArrayList<serviceDTO> gettAllServiceInBIll() {
    	return svDAO.gettAllServiceInBIll();
    }
}
