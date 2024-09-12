package BUS;
import DTO.positionDTO;
import DAO.positionDAO;

import java.util.ArrayList;

public class positionBUS {
	private positionDAO pDAO= new positionDAO();
//	Lấy mảng id chức vụ + tên chức vụ
	public ArrayList<positionDTO> getAllPosition(){
		return pDAO.getAllPosition();
	}

}
