package DTO;

import java.sql.Date;

public class billDTO {
//	Các thuộc tính có trong bill
	private int id_hoadon;
	private double tongtien_hd;
	private Date ngaylap_hd;
	private int id_kh;
	private int id_phieudat;
//	Các thuộc tính bổ sung
	private String name_customer;
	private String name_room;
	private Date ngaythue;
	private Date ngaytra;
	
	private String ten_dichvu;
	private int soluong_dichvu;
	private double tien_moidichvu;
	
	
//	GET SET
	public int getId_hoadon() {
		return id_hoadon;
	}
	public void setId_hoadon(int id_hoadon) {
		this.id_hoadon = id_hoadon;
	}
	public double getTongtien_hd() {
		return tongtien_hd;
	}
	public void setTongtien_hd(double tongtien_hd) {
		this.tongtien_hd = tongtien_hd;
	}
	public Date getNgaylap_hd() {
		return ngaylap_hd;
	}
	public void setNgaylap_hd(Date ngaylap_hd) {
		this.ngaylap_hd = ngaylap_hd;
	}
	public int getId_kh() {
		return id_kh;
	}
	public void setId_kh(int id_kh) {
		this.id_kh = id_kh;
	}
	public int getId_phieudat() {
		return id_phieudat;
	}
	public void setId_phieudat(int id_phieudat) {
		this.id_phieudat = id_phieudat;
	}
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
	public String getName_room() {
		return name_room;
	}
	public void setName_room(String name_room) {
		this.name_room = name_room;
	}
	public Date getNgaythue() {
		return ngaythue;
	}
	public void setNgaythue(Date ngaythue) {
		this.ngaythue = ngaythue;
	}
	public Date getNgaytra() {
		return ngaytra;
	}
	public void setNgaytra(Date ngaytra) {
		this.ngaytra = ngaytra;
	}
	public String getTen_dichvu() {
		return ten_dichvu;
	}
	public void setTen_dichvu(String ten_dichvu) {
		this.ten_dichvu = ten_dichvu;
	}
	public int getSoluong_dichvu() {
		return soluong_dichvu;
	}
	public void setSoluong_dichvu(int soluong_dichvu) {
		this.soluong_dichvu = soluong_dichvu;
	}
	public double getTien_moidichvu() {
		return tien_moidichvu;
	}
	public void setTien_moidichvu(double tien_moidichvu) {
		this.tien_moidichvu = tien_moidichvu;
	}
	
	

}
