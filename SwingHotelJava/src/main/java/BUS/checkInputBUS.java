package BUS;

public class checkInputBUS {
	// Kiểm tra chuỗi có phải toàn số hay không
		public boolean isNumeric(String str) {
		    return str.matches("-?\\d+(\\.\\d+)?");  // matches()true là chuỗi số, false thì không
		}

		// số điện thoại đủ 10 số
		public boolean isValidPhone(String phone) {
		    return phone.length() == 10 && isNumeric(phone);
		}

		// cccd đủ 12 số
		public boolean isValidCCCD(String cccd) {
		    return cccd.length() == 12 && isNumeric(cccd);
		}
//		Kiểm tra email
		public boolean isValidEmail(String email) {
		    String emailRegex ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		    return email.matches(emailRegex);
		}
		// Kiểm tra username 0-9, chữ thường, không cách
		public boolean isValidUsername(String username) {
			String usernameRegex = "^[a-z0-9_-]{3,20}$";
			return username.matches(usernameRegex);
		}

		// Kiểm tra password (ít nhất 5 ký tự, có ký tự đặc biệt, không khoảng trắng, có số và chữ)
		public boolean isValidPassword(String password) {
			String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^\\w\\s]).{5,}$";
			return password.matches(passwordRegex);
		}	

}
