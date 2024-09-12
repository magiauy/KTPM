package DTO;

public class roomDTO {
	private int room_id;
	private String room_name;
	private int typeRoome_id;
	private String room_status;
//	Thuộc tính thêm
	private Double room_fee;
	private String type_room_name;
	
	
	
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getTypeRoome_id() {
		return typeRoome_id;
	}
	public void setTypeRoome_id(int typeRoome_id) {
		this.typeRoome_id = typeRoome_id;
	}
	public String getRoom_status() {
		return room_status;
	}
	public void setRoom_status(String room_status) {
		this.room_status = room_status;
	}
	public Double getRoom_fee() {
		return room_fee;
	}
	public void setRoom_fee(Double room_fee) {
		this.room_fee = room_fee;
	}
	public String getType_room_name() {
		return type_room_name;
	}
	public void setType_room_name(String type_room_name) {
		this.type_room_name = type_room_name;
	}
	
	
}
