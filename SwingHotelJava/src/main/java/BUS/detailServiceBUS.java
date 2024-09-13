package BUS;
import java.util.ArrayList;
import DTO.detailBookingDTO;
import DTO.detailServiceDTO;
import DAO.detailServiceDAO;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;

public class detailServiceBUS {
	private detailServiceDAO dlServiceDAO= new detailServiceDAO();
	private ArrayList<detailServiceDTO> arrDetailService= new ArrayList<detailServiceDTO>();




	private ArrayList<detailServiceDTO> getAllDetailService(){
		return dlServiceDAO.getAllDetailService();
	}
	public ArrayList<detailServiceDTO> getDetailServiceByID_PD(int id) {
		return dlServiceDAO.getDetailServiceByID_PD(id);
	}
//	Thêm chi tiết dịch vụ
	public String addDetailSerice(detailServiceDTO dlService) {
		if(dlServiceDAO.addDetailSerice(dlService)) {
			return "Thêm chi tiết dịch vụ thành công";
		}
		return "Thêm chi tiết dịch vụ thất bại";
	}
	public void addtemp(detailServiceDTO dlService) {
		arrDetailService.add(dlService);
	}
	public void edittemp(int id, int amount, double total) {
		for(detailServiceDTO dlService: arrDetailService) {
			if(dlService.getId_dv()==id) {
				dlService.setSoluong_dv(amount);
				dlService.setTongtien_dv(total);

			}
		}
	}
	public boolean checkAvailableService(int id) {
		for(detailServiceDTO dlService: arrDetailService) {
			if(dlService.getId_dv()==id) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<detailServiceDTO> getArrDetailService() {
		return arrDetailService;
	}
	public void deletetempDetailService(int id) {
		for(detailServiceDTO dlService: arrDetailService) {
			if(dlService.getId_dv()==id) {
				arrDetailService.remove(dlService);
				break;
			}
		}
	}
	public void checkdup() {
		ArrayList<detailServiceDTO> temparrDetailService = getDetailServiceByID_PD(arrDetailService.get(0).getid_pn());
		if(temparrDetailService!=null){
			for (detailServiceDTO dlService : temparrDetailService) {
				for (detailServiceDTO dlService1 : arrDetailService) {
					if (dlService.getId_dv() == dlService1.getId_dv()) {
						dlService1.setSoluong_dv(dlService1.getSoluong_dv() + dlService.getSoluong_dv());
						dlService1.setTongtien_dv(dlService1.getTongtien_dv() + dlService.getTongtien_dv());
					}
				}
			}
		}

	}
	public void editDetailService(detailServiceDTO dlService) {
		dlServiceDAO.editDetailService(dlService);
	}
	public void adddatabase() {
		ArrayList<detailServiceDTO> temparrDetailService = getDetailServiceByID_PD(arrDetailService.get(0).getid_pn());
		if (temparrDetailService != null){
			for (detailServiceDTO dlService : arrDetailService) {
				boolean check = false;
				for (detailServiceDTO dlService1 : temparrDetailService) {
						if (dlService.getId_dv() == dlService1.getId_dv()) {
							editDetailService(dlService);
							check = true;
							break;
						}
					}
				if (!check) {
					addDetailSerice(dlService);
				}
			}
		}
	}
	public double calculateTotal(int id) {
		double total=0;
		ArrayList<detailServiceDTO> temparrDetailService = getDetailServiceByID_PD(id);
		if(temparrDetailService!=null){
			for(detailServiceDTO dlService: temparrDetailService) {
				total+=dlService.getTongtien_dv();
			}
		}
		return total;
	}
}
