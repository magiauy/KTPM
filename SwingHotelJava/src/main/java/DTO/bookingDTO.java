package DTO;
import java.sql.Date;
public class bookingDTO {
	private int booking_id;
	private int booking_staff_id;
	private int booking_customer_id;
	private Date date_start_booking;
	private Date date_end_booking;
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public int getBooking_staff_id() {
		return booking_staff_id;
	}
	public void setBooking_staff_id(int booking_staff_id) {
		this.booking_staff_id = booking_staff_id;
	}
	public int getBooking_customer_id() {
		return booking_customer_id;
	}
	public void setBooking_customer_id(int booking_customer_id) {
		this.booking_customer_id = booking_customer_id;
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

	

}
