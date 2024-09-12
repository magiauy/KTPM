package DTO;

public class detailServiceDTO {
//	Các thuộc tính trong database
	private int id_pn;
	private int id_dv;
	private int soluong_dv;
	private double tongtien_dv;
//	Get,set
	public int getid_pn() {
		return id_pn;
	}
	public void setid_pn(int id_pn) {
		this.id_pn = id_pn;
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
