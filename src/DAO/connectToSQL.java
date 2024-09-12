package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class connectToSQL {
	private Connection con;
//	Thiết lập kiểm tra kết nối
	public boolean openConectionToSQL() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/uydb";
			String username="root";
			String password="";
			
			con=DriverManager.getConnection(url,username,password);
		
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
//	Đóng kết nối
	public void closeConnectionToSQL() {
		try {
			if(con!=null) {
				con.close();
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
//	createStatement
	public Statement createStatement() throws SQLException {
		return con.createStatement();
	}
//	preparedStatement
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

}
