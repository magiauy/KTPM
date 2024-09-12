package GUI;

import java.awt.Color;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import DTO.roomDTO;
import DTO.staffDTO;
import DTO.typeRoomDTO;
import DTO.bookingDTO;
import DTO.accountDTO;
import DTO.customerDTO;
import DTO.detailBookingDTO;
import BUS.deltailBookingBUS;
import BUS.roomBUS;
import BUS.staffBUS;
import BUS.customerBUS;
import BUS.bookingBUS;
import BUS.checkInputBUS;
import BUS.accountBUS;
import BUS.typeRoomBUS;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.RowFilter.Entry;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

import java.text.SimpleDateFormat;




public class pnBooking extends JPanel {
	
	private accountDTO account=frmLogin.account;
	

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfFeeTmp;
	private JTextField tfRoom;
	private JTextField tfIDStaff;
	
	private DefaultTableModel modelTableRoom= new DefaultTableModel();
	
	private JButton btnReset, btnAdd;
	private JComboBox cbCustomer ;
	
	private roomBUS roomBUS= new roomBUS();
	private ArrayList<roomDTO> arrRoomEmpty ;
	
	private customerBUS customerBUS= new customerBUS();
	private ArrayList<customerDTO> arrCustomer ;
	private bookingBUS bookingBUS= new bookingBUS();
	
	private JDateChooser dcEnd, dcStart;
	private JTextField tfFind;
	private JTextField tfNameRoom;
	
	private deltailBookingBUS detailBookingBUS= new deltailBookingBUS();
	private ArrayList<detailBookingDTO> arrDetailBooking;
	private JTextField tfIDBooking;
	private pnDetailBooking pnDetail= new pnDetailBooking();
	

    
	private ArrayList<bookingDTO> arrBooking;
	
	
	/**
	 * Create the panel.
	 */
	public pnBooking() {
		initComponent();
		addValueInCbCustomer();
		showTableRoom();
		autoSetIDBooking();
		
	}
//	Giao diện
	private void initComponent() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thêm phiếu đặt phòng");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(140, 25, 266, 25);
		add(lblNewLabel);
		
		JLabel lblDnhSchPhng = new JLabel("Danh sách phòng trống");
		lblDnhSchPhng.setForeground(new Color(0, 0, 128));
		lblDnhSchPhng.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDnhSchPhng.setBounds(654, 25, 266, 25);
		add(lblDnhSchPhng);
		
		
		JLabel lblIdNhnVin = new JLabel("ID Nhân viên");
		lblIdNhnVin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblIdNhnVin.setBounds(10, 137, 111, 25);
		add(lblIdNhnVin);
		
		JLabel lblChnKhchHng = new JLabel("Chọn khách hàng");
		lblChnKhchHng.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblChnKhchHng.setBounds(10, 196, 132, 25);
		add(lblChnKhchHng);
		
		JLabel lblNgyt = new JLabel("Ngày đặt");
		lblNgyt.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNgyt.setBounds(10, 255, 83, 25);
		add(lblNgyt);
		
		JLabel lblNgyTr = new JLabel("Ngày trả");
		lblNgyTr.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNgyTr.setBounds(10, 303, 83, 25);
		add(lblNgyTr);
		
		JLabel lblPhng = new JLabel("ID Phòng");
		lblPhng.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblPhng.setBounds(10, 354, 83, 25);
		add(lblPhng);
		
		JLabel lblTinTmTnh = new JLabel("Tiền tạm tính");
		lblTinTmTnh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTinTmTnh.setBounds(10, 469, 111, 25);
		add(lblTinTmTnh);
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
	
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBounds(938, 522, 102, 43);
		add(btnAdd);
		
		tfFeeTmp = new JTextField();
		tfFeeTmp.setColumns(10);
		tfFeeTmp.setBounds(140, 461, 217, 33);
		add(tfFeeTmp);
		
		tfRoom = new JTextField();
		tfRoom.setColumns(10);
		tfRoom.setBounds(140, 352, 217, 33);
		add(tfRoom);
		
		cbCustomer = new JComboBox();
		cbCustomer.setBackground(new Color(255, 255, 255));
		cbCustomer.setBounds(141, 194, 216, 33);
		add(cbCustomer);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnResetActionPerformed(e);
			}
		});
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnReset.setBorderPainted(false);
		btnReset.setBackground(new Color(0, 206, 209));
		btnReset.setBounds(805, 522, 102, 43);
		add(btnReset);
		
		tfIDStaff = new JTextField();
		tfIDStaff.setText(String.valueOf(account.getAccount_NVID()));
		tfIDStaff.setColumns(10);
		tfIDStaff.setBounds(140, 135, 217, 33);
		add(tfIDStaff);
		tfIDStaff.setEnabled(false);
		
		dcStart = new JDateChooser();
		dcStart.setDateFormatString("yyyy-MM-dd");
		dcStart.setBounds(140, 250, 217, 30);
		add(dcStart);
		
		dcEnd= new JDateChooser();
		dcEnd.setDateFormatString("yyyy-MM-dd");
		dcEnd.setBounds(140,303,217,30);
		add(dcEnd);
		
		
		PropertyChangeListener dateChangeListener = new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        updateTotalFee();
		    }
		};

		dcStart.addPropertyChangeListener("date", dateChangeListener);
		dcEnd.addPropertyChangeListener("date", dateChangeListener);

		
		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBounds(573, 469, 467, 33);
		add(tfFind);
		
		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findBooking();
			}

			public void removeUpdate(DocumentEvent e) {
				findBooking();
			}

			public void changedUpdate(DocumentEvent e) {
				findBooking();
			}
		});
		
		JLabel lblTmKm = new JLabel("Tìm kiếm");
		lblTmKm.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTmKm.setBounds(495, 469, 68, 25);
		add(lblTmKm);
		
		tfNameRoom = new JTextField();
		tfNameRoom.setColumns(10);
		tfNameRoom.setBounds(140, 408, 217, 33);
		add(tfNameRoom);
		
		JLabel lblTnPhng = new JLabel("Tên phòng");
		lblTnPhng.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTnPhng.setBounds(10, 410, 83, 25);
		add(lblTnPhng);
		
		JLabel lblIdPhiut = new JLabel("ID Phiếu đặt");
		lblIdPhiut.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblIdPhiut.setBounds(10, 76, 111, 25);
		add(lblIdPhiut);
		
		tfIDBooking = new JTextField();
		tfIDBooking.setText("0");
		tfIDBooking.setEnabled(false);
		tfIDBooking.setColumns(10);
		tfIDBooking.setBounds(140, 74, 217, 33);
		add(tfIDBooking);
		
		

	}
//	Tự động tăng ID phiếu đặt
	private void autoSetIDBooking() {
		 arrBooking=bookingBUS.getAllBooking();
		 int maxID = 0;
	        for (bookingDTO booking:arrBooking) {
	            if (booking.getBooking_id() > maxID) {
	                maxID = booking.getBooking_id();
	            }
	        }
	        int newID = maxID + 1; 
	        tfIDBooking.setText(String.valueOf(newID)); 
	        tfIDBooking.setEnabled(false); 
	}
//	Thêm giá trị vào combobox customer
	private void addValueInCbCustomer() {
		arrCustomer=customerBUS.getAllCustomer();
		for(customerDTO customer:arrCustomer) {
			cbCustomer.addItem(customer.getCustomer_id()+"-"+customer.getCustomer_name());
		}
	}
	
// Update table Room
	private void updateTableRoom() {
		arrRoomEmpty=roomBUS.getAllRoomEmptyAndActive();
		modelTableRoom.setRowCount(0);
		for(roomDTO room:arrRoomEmpty) {
			modelTableRoom.addRow(new Object[] {
					room.getRoom_id(),
					room.getRoom_name(),
					room.getRoom_fee(),
					room.getType_room_name(),
					room.getRoom_status()
					
			});
		}
	}
//	Hiển thị table phòng
	private void showTableRoom() {

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableRoomMouseClicked(e);
			}
		});
		table.setModel(modelTableRoom);
		
		modelTableRoom.addColumn("ID Phòng");
		modelTableRoom.addColumn("Tên Phòng");
		modelTableRoom.addColumn("Giá tiền(USD)");
		modelTableRoom.addColumn("Loại PHG");
		modelTableRoom.addColumn("Tình trạng");
	
		
		
		JScrollPane scrollPane= new JScrollPane(table);
		scrollPane.setBounds(485, 75, 555, 374);
		scrollPane.getViewport().setBackground(Color.white);
		add(scrollPane);
		
		
		
		arrRoomEmpty=roomBUS.getAllRoomEmptyAndActive();
		
		updateTableRoom();
		
	}
//	Sự kiện chọn phòng trong table Room
	private void tableRoomMouseClicked(MouseEvent e) {
		int i=table.getSelectedRow();
		if(i>=0) {
			tfRoom.setText(modelTableRoom.getValueAt(i, 0).toString());
			
			tfNameRoom.setText(modelTableRoom.getValueAt(i, 1).toString());
			
			
			if (dcStart.getDate() != null && dcEnd.getDate() != null && dcStart.getDate().before(dcEnd.getDate())) {
	            updateTotalFee();
	        }
			
			tfRoom.setEnabled(false);
			tfNameRoom.setEnabled(false);
		}
	}
//	Cập nhật giá phòng
	private void updateTotalFee() {
	    if(dcStart.getDate() != null && dcEnd.getDate() != null) {
	        long diff = dcEnd.getDate().getTime() - dcStart.getDate().getTime();
	        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	        if (days <= 0) {
	            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
	            btnAdd.setEnabled(false);
	            return;
	        }
	        
	        double day_hire = days;
	        int roomId = Integer.parseInt(tfRoom.getText());
	        
	        ArrayList<roomDTO> arroom = roomBUS.getAllRoomEmptyAndActive();
	        for(roomDTO room : arroom) {
	            if(room.getRoom_id() == roomId) {
	                double fee_room = room.getRoom_fee();
	                double total_fee = fee_room * day_hire;
	                tfFeeTmp.setText(String.valueOf(total_fee));
	                tfFeeTmp.setEnabled(false);
	                btnAdd.setEnabled(true);
	                break;
	            }
	        }
	    }
	}




//	Sự kiện nút Add
private void btnAddActionPerformed(ActionEvent e) {
    try {
        if(tfNameRoom.getText().equals("")||
            tfRoom.getText().equals("")||
            tfFeeTmp.getText().equals("")||
            dcEnd.getDate()==null||
            dcStart.getDate()==null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
        }
        else {
            
            bookingDTO booking = new  bookingDTO();
            
            booking.setBooking_id(Integer.parseInt(tfIDBooking.getText().toString()));
            
            java.util.Date startDate = dcStart.getDate();
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            booking.setDate_start_booking(sqlStartDate);
            
            java.util.Date endDate = dcEnd.getDate();
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
            booking.setDate_end_booking(sqlEndDate);

            booking.setBooking_staff_id(Integer.parseInt(tfIDStaff.getText().toString()));
            String customerInfo = cbCustomer.getSelectedItem().toString();
            String customerId = customerInfo.split("-")[0];
            booking.setBooking_customer_id(Integer.parseInt(customerId));

            
            String message=bookingBUS.addBooking(booking);
            
            
            if ("Thêm phiếu đặt thành công".equals(message)) {
              
                // Tạo một đối tượng detailBookingDTO và thiết lập thông tin
                detailBookingDTO detailBooking = new detailBookingDTO();
                
                detailBooking.setDetail_booking_id_step2(booking.getBooking_id()); 
                detailBooking.setDeltai_booking_id_room_step2(Integer.parseInt(tfRoom.getText().toString()));
                detailBooking.setSum_fee_step2(Double.parseDouble(tfFeeTmp.getText()));
                detailBooking.setDetail_booking_status("Chưa checkout");
                
                // Thêm chi tiết phiếu đặt
                String message2 = detailBookingBUS.addDetailBooking(detailBooking);
                
                JOptionPane.showMessageDialog(this, message2);
                
                
                // Lấy ID phòng từ trường tfRoom
                int roomId = Integer.parseInt(tfRoom.getText());
                
                // Tạo một đối tượng roomDTO và thiết lập ID phòng
                roomDTO room = new roomDTO();
                room.setRoom_id(roomId);
                
                // Cập nhật trạng thái phòng
                roomBUS.updateStatusRoom(room);
                updateTableRoom();	
                
             
              
            }
            pnDetail.updateTable();
    		
            btnResetActionPerformed(e);
        }  
    }catch(NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,"Thông tin không hợp lệ");
        
    }	
 
}
// Tìm kiếm đặt phòng
private void findBooking() {
	String keyword = tfFind.getText().trim().toLowerCase();
	TableRowSorter<TableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
	table.setRowSorter(rowSorter);

	if (!keyword.equals("")) {
		rowSorter.setRowFilter(new RowFilter<TableModel, Integer>() {
			@Override
			public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
				for (int i = 0; i < entry.getValueCount(); i++) {
					if (entry.getStringValue(i).toLowerCase().contains(keyword)) {
						return true; // Có ít nhất một trường khớp với từ khóa
					}
				}
				return false; // Không có trường nào khớp
			}
		});
	} else {
		rowSorter.setRowFilter(null); // Nếu không nhập gì, hiển thị tất cả dữ liệu
	}
}

//	Sự kiện nút reset
	private void btnResetActionPerformed(ActionEvent e) {
		
		
		tfRoom.setText("");
		tfNameRoom.setText("");
		tfFeeTmp.setText("");
		dcStart.setDate(null);
		dcEnd.setDate(null);
		
		autoSetIDBooking();
		
		
	
	}


	

	


}
