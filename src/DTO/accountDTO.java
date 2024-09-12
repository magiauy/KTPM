package DTO;

public class accountDTO {
//	Các thuộc tính trong bảng account
	private int account_id;
	private String account_username;
	private String account_password;
	private int account_id_nv;
//	Thuộc tính bổ sung
	private String role;
//	Thuộc tính bổ sung trong bảng nhân viên
	 private int account_NVID;
     private String account_name;
     private String account_email;
     private String account_phone;
     private String account_cccd;
     private String account_gender;
     private int account_id_position;
     
     
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getAccount_username() {
		return account_username;
	}
	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
	public int getAccount_id_nv() {
		return account_id_nv;
	}
	public void setAccount_id_nv(int account_id_nv) {
		this.account_id_nv = account_id_nv;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_email() {
		return account_email;
	}
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}
	public String getAccount_phone() {
		return account_phone;
	}
	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}
	public String getAccount_cccd() {
		return account_cccd;
	}
	public void setAccount_cccd(String account_cccd) {
		this.account_cccd = account_cccd;
	}
	public String getAccount_gender() {
		return account_gender;
	}
	public void setAccount_gender(String account_gender) {
		this.account_gender = account_gender;
	}
	public int getAccount_NVID() {
		return account_NVID;
	}
	public void setAccount_NVID(int account_NVID) {
		this.account_NVID = account_NVID;
	}
	public int getAccount_id_position() {
		return account_id_position;
	}
	public void setAccount_id_position(int account_id_position) {
		this.account_id_position = account_id_position;
	}
	
	
	
	
	

}
