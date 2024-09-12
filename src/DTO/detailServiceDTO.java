package DTO;

public class detailServiceDTO {
//	Các thuộc tính trong database
	private int id_hd;
	private int id_dv;
	private int soluong_dv;
	private double tongtien_dv;
//	Get,set
	public int getId_hd() {
		return id_hd;
	}
	public void setId_hd(int id_hd) {
		this.id_hd = id_hd;
	}
	public int getId_dv() {
		return id_dv;
	}
	public void setId_dv(int id_dv) {
		this.id_dv = id_dv;
	}
	public int getSoluong_dv() {
		return soluong_dv;
	}
	public void setSoluong_dv(int soluong_dv) {
		this.soluong_dv = soluong_dv;
	}
	public double getTongtien_dv() {
		return tongtien_dv;
	}
	public void setTongtien_dv(double tongtien_dv) {
		this.tongtien_dv = tongtien_dv;
	}
	

}
