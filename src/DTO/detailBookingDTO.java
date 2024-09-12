package DTO;

import java.sql.Date;

public class detailBookingDTO {
//	Thuộc tính trong bảng
	private int detail_booking_id_step2; //mã phiếu đặt
	private int deltai_booking_id_room_step2; //Mã phòng
	private double sum_fee_step2; //Tổng tiền tạm tính
	private String detail_booking_status; //Trạng thái
//	Thuộc tính kết bảng
	private String detail_booking_customer_name; //Tên khách hàng
	private int detail_booking_room_id; //Mã phòng
	private String detail_booking_room_name; //tên phòng
	private int detail_booking_customer_id; //mã khách hàng
	
	private Date date_start_booking; //Ngày đặt
	private Date date_end_booking; //Ngày trả
	public int getDetail_booking_id_step2() {
		return detail_booking_id_step2;
	}
	public void setDetail_booking_id_step2(int detail_booking_id_step2) {
		this.detail_booking_id_step2 = detail_booking_id_step2;
	}
	
	public int getDeltai_booking_id_room_step2() {
		return deltai_booking_id_room_step2;
	}
	public void setDeltai_booking_id_room_step2(int deltai_booking_id_room_step2) {
		this.deltai_booking_id_room_step2 = deltai_booking_id_room_step2;
	}
	public double getSum_fee_step2() {
		return sum_fee_step2;
	}
	public void setSum_fee_step2(double sum_fee_step2) {
		this.sum_fee_step2 = sum_fee_step2;
	}
	public String getDetail_booking_status() {
		return detail_booking_status;
	}
	public void setDetail_booking_status(String detail_booking_status) {
		this.detail_booking_status = detail_booking_status;
	}
	public String getDetail_booking_customer_name() {
		return detail_booking_customer_name;
	}
	public void setDetail_booking_customer_name(String detail_booking_customer_name) {
		this.detail_booking_customer_name = detail_booking_customer_name;
	}
	public String getDetail_booking_room_name() {
		return detail_booking_room_name;
	}
	public void setDetail_booking_room_name(String detail_booking_room_name) {
		this.detail_booking_room_name = detail_booking_room_name;
	}
	public int getDetail_booking_customer_id() {
		return detail_booking_customer_id;
	}
	public void setDetail_booking_customer_id(int detail_booking_customer_id) {
		this.detail_booking_customer_id = detail_booking_customer_id;
	}
	public Date getDate_start_booking() {
		return date_start_booking;
	}
	public void setDate_start_booking(Date date_start_booking) {
		this.date_start_booking = date_start_booking;
	}
	public Date getDate_end_booking() {
		return date_end_booking;
	}
	public void setDate_end_booking(Date date_end_booking) {
		this.date_end_booking = date_end_booking;
	}
	public int getDetail_booking_room_id() {
		return detail_booking_room_id;
	}
	public void setDetail_booking_room_id(int detail_booking_room_id) {
		this.detail_booking_room_id = detail_booking_room_id;
	}
	
}
