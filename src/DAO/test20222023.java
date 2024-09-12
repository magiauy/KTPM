package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class test20222023 {
	
	public float S(int n) {
		float tong=0;
		for(int i=0;i<=n;i++) {
			tmp=((2*i+1)*(1.0f))/(2*i+2);
			tong=tong+tmp;
		}
		return tong; 
	}

	btnTinh.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
                int n = Integer.parseInt(tfNhapn.getText());
                float result = S(n);
                tfKetqua.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                tfKetqua.setText("Nhập số hợp lệ!");
            }
		}
	});
	
//	Câu 2
//	class ThuCungDTO
	public class ThuCungDTO() {
		private String MaThuCung;
		private String Ten;
		private String MauLong;
		private float DonGia;
		private int SoLuong;
		
		public String getMaThuCung() {
			return MaThuCung;
		}
		public void setMaThuCung(String MaThuCung) {
			this.MaThuCung = MaThuCung;
		}
		public String getTen() {
			return Ten;
		}
		public void setTen(String Ten) {
			this.Ten = Ten;
		}
		public String getMauLong() {
			return MauLong;
		}
		public void setMauLong(String MauLong) {
			this.MauLong = MauLong;
		}
		public float getDonGia() {
			return DonGia;
		}
		public void setDonGia(float DonGia) {
			this.DonGia = DonGia;
		}
		public int getSoLuong() {
			return SoLuong;
		}
		public void setSoLuong(int SoLuong) {
			this.SoLuong = SoLuong;
		}
	}

//	Lớp kết nối CDSL MyConnection
	public class MyConnection{
		private Connection con;
//		Thiết lập kiểm tra kết nối
		public boolean openConectionToSQL() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				String url="jdbc:mysql://localhost:3306/QLThuCung?serverTimezone=UTC";
				String username="root";
				String password="";
				
				con=DriverManager.getConnection(url,username,password);
			
				return true;
			}catch(Exception e) {
				System.out.println(e);
				return false;
			}
			
		}
//		Đóng kết nối
		public void closeConnectionToSQL() {
			try {
				if(con!=null) {
					con.close();
				}
				
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
//		createStatement
		public Statement createStatement() throws SQLException {
			return con.createStatement();
		}
//		preparedStatement
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return con.prepareStatement(sql);
		}
	}
	
//	Lớp ThuCungDAO
	public class ThuCungDAO{
		private MyConnection con= new MyConnection();
//		2.1 Viết hàm Them(inset) thông tin một thú cưng vào CSDL
		public boolean Them(ThuCungDTO tc) {
			boolean result=false;
			if(con.openConectionToSQL()) {
				try {
					String sql="Insert into ThuCung values(?,?,?,?,?)";
					
					PreparedStatement stmt=con.prepareStatement(sql);
					
					stmt.setString(1,tc.getMaThuCung());
					stmt.setString(2,tc.getTen());
					stmt.setString(3,tc.getMauLong());
					stmt.setFloat(4, tc.getDonGia());
					stmt.setInt(5, tc.getSoLuong());
					
					if(stmt.executeUpdate()>=1) {
						result=true;
					}
					
				}catch(SQLException e) {
					System.out.println(e);
					
				}finally {
					con.closeConnectionToSQL();
				}
			}
			return result;
		}
	}
//	Lớp ThuCungBUS
	public class ThuCungBUS{
		private ThuCungDAO tcDAO= new ThuCungDAO();
		private ArrayList<ThuCungDTO> dsthucung = new ArrayList<>();
		
//		2.2 Gọi hàm thêm thông tin thú cưng của lớp ThuCungDAO
		public String Them(ThuCungDTO tc) {
			if(tcDAO.Them(tc)) {
				return "Thêm thú cưng thành công";
			}
			return "Thêm thú cưng thất bại";
		}

//		2.3 Trong lớp ThuCungBUS, viêt hàm tìm kiếm thú cưng theo màu lông và hàm trả về danh sách tìm thấy
//		(Giả sử trong lớp ThuCungBUS có sẵn ArrayList dsthucung chứa danh sách thú cưng, có sẵn hàm docDSThuCung(): đọc danh sách thú cưng)
		 public ArrayList<ThuCungDTO> timKiemThuCungTheoMauLong(String mauLong) {
			 ArrayList<ThuCungDTO> dsthucung = tcDAO.docDSThuCung();
		        ArrayList<ThuCungDTO> ketQuaTimKiem = new ArrayList<>();
		        for (ThuCungDTO tc : dsthucung) {
		            if (tc.getMauLong().equalsIgnoreCase(mauLong)) {
		                ketQuaTimKiem.add(tc);
		            }
		        }
		        return ketQuaTimKiem;
		    }
	}
//	GUI
	public class ThuCungFRM{
		....
		 private ThuCungBUS tcBUS = new ThuCungBUS();
		ArrayList<ThuCungDTO> dsthucung ;
		 ....
		 public ThuCungFRM() {
			...
			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addEvent(e);
				}
			});
			btnSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					searchEvent(e);
				}
			});
		}
//		 	2.5 Gọi sự kiện hàm thêm
		 private void addEvent(ActionEvent e) {
			 ThuCungDTO tc = new ThuCungDTO();
			 
			 tc.setMaThuCung(tfMa.getText());
			 tc.setTen(tfTen.getText());
			 tc.setMauLong(tfMauLong.getText());
			 tc.setDonGia(Float.parseFloat(tfDonGia.getText()));
			 tc.setSoLuong(Integer.parseInt(tfSoLuong.getText()));
			 
			 String message = tcBUS.Them(tc);
			 
			 if(message.equals("Thêm thú cưng thành công")) {
				 JOptionPane.showMessageDialog(this, message);
			 }
			 
		 }
//		 2.6 Gọi sự kiện hàm tìm kiếm, thực hiện tìm kiếm các thú cưng theo màu lông, hiển thị kết quả lên form
		 private void searchEvent(ActionEvent e) {
			 String mauLong = tfMauLong.getText();
		        ArrayList<ThuCungDTO> dsthucungtim = tcBUS.timKiemThuCungTheoMauLong(mauLong);
		        tableModel.setRowCount(0); // Clear existing results
		        for (ThuCungDTO tc : dsthucungtim) {
		            Object[] row = {
		                tc.getMaThuCung(),
		                tc.getTen(),
		                tc.getMauLong(),
		                tc.getDonGia(),
		                tc.getSoLuong()
		            };
		            tableModel.addRow(row);
		        }
		 }
	}
}
