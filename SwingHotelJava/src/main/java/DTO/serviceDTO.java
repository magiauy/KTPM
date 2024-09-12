package DTO;

public class serviceDTO {
    private int service_id;
    private String service_name;
    private double service_price;
    private String service_description;
//    Thuộc tính bổ sung
    private int soluong_dv;
    private double phi_moidichvu;
    private int id_hoadon;

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public double getService_price() {
        return service_price;
    }

    public void setService_price(double service_price) {
        this.service_price = service_price;
    }
    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

	public int getSoluong_dv() {
		return soluong_dv;
	}

	public void setSoluong_dv(int soluong_dv) {
		this.soluong_dv = soluong_dv;
	}

	public double getPhi_moidichvu() {
		return phi_moidichvu;
	}

	public void setPhi_moidichvu(double phi_moidichvu) {
		this.phi_moidichvu = phi_moidichvu;
	}

	public int getId_hoadon() {
		return id_hoadon;
	}

	public void setId_hoadon(int id_hoadon) {
		this.id_hoadon = id_hoadon;
	}
	

}