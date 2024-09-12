package GUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DTO.detailBookingDTO;
import DTO.roomDTO;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.RowFilter.Entry;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.util.ArrayList;
import java.util.Arrays;

import DTO.detailBookingDTO;
import BUS.deltailBookingBUS;
import BUS.roomBUS;

public class pnDetailBooking extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelDetailBooking  = new DefaultTableModel();

	private JTextField tfID_Booking;
	private JTextField tfID_PHG;
	private JTextField tfName_Customer;
	private JTextField tfSum_fee;
	private JTextField tfStatus;
	private JTextField tfName_Room;
	
	private ArrayList<detailBookingDTO> arrDetailBooking;
	
	private deltailBookingBUS detailBookingBUS= new deltailBookingBUS();
	private JTextField tfFind;
	private JScrollPane scrollPane ;

	/**
	 * Create the panel.
	 */
	public pnDetailBooking() {
		initComponent();
		tableDetailBooking();

		
	}
//	Giao diện
	private void initComponent() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách phiếu đặt phòng");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(198, 21, 266, 25);
		add(lblNewLabel);
		
		JLabel lblThngTinChi = new JLabel("Thông tin chi tiết");
		lblThngTinChi.setForeground(new Color(0, 0, 128));
		lblThngTinChi.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblThngTinChi.setBounds(779, 21, 174, 25);
		add(lblThngTinChi);
		
	
		
		JLabel lblNewLabel_11 = new JLabel("Mã phiếu đặt");
		lblNewLabel_11.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(705, 103, 123, 33);
		add(lblNewLabel_11);
		
		JLabel lblNewLabel_11_1 = new JLabel("Mã phòng");
		lblNewLabel_11_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11_1.setBounds(705, 185, 103, 33);
		add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_11_2 = new JLabel("Tên phòng");
		lblNewLabel_11_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11_2.setBounds(874, 185, 103, 33);
		add(lblNewLabel_11_2);
		
		JLabel lblNewLabel_11_4 = new JLabel("Tên khách hàng");
		lblNewLabel_11_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11_4.setBounds(705, 279, 123, 33);
		add(lblNewLabel_11_4);
		
		JLabel lblNewLabel_11_2_1 = new JLabel("Tổng tiền");
		lblNewLabel_11_2_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11_2_1.setBounds(705, 372, 103, 33);
		add(lblNewLabel_11_2_1);
		
		JLabel lblNewLabel_11_2_2 = new JLabel("Trạng thái");
		lblNewLabel_11_2_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11_2_2.setBounds(705, 452, 103, 33);
		add(lblNewLabel_11_2_2);
		
		tfID_Booking = new JTextField();
		tfID_Booking.setColumns(10);
		tfID_Booking.setBorder(new LineBorder(Color.lightGray,1));
		tfID_Booking.setBounds(705, 131, 123, 33);
		add(tfID_Booking);
		
		tfID_PHG = new JTextField();
		tfID_PHG.setColumns(10);
		tfID_PHG.setBorder(new LineBorder(Color.lightGray,1));
		tfID_PHG.setBounds(705, 216, 123, 33);
		add(tfID_PHG);
		
		tfName_Room = new JTextField();
		tfName_Room.setColumns(10);
		tfName_Room.setBorder(new LineBorder(Color.lightGray,1));
		tfName_Room.setBounds(874, 216, 186, 33);
		add(tfName_Room);
		
		tfName_Customer = new JTextField();
		tfName_Customer.setColumns(10);
		tfName_Customer.setBorder(new LineBorder(Color.lightGray,1));
		tfName_Customer.setBounds(705, 314, 186, 33);
		add(tfName_Customer);
		
		tfSum_fee = new JTextField();
		tfSum_fee.setColumns(10);
		tfSum_fee.setBorder(new LineBorder(Color.lightGray,1));
		tfSum_fee.setBounds(705, 406, 149, 33);
		add(tfSum_fee);
		
		tfStatus = new JTextField();
		tfStatus.setColumns(10);
		tfStatus.setBorder(new LineBorder(Color.lightGray,1));
		tfStatus.setBounds(705, 483, 205, 33);
		add(tfStatus);
		
		tfFind = new JTextField();
	    tfFind.setColumns(10);
	    tfFind.setBorder(new LineBorder(Color.lightGray,1));
	    tfFind.setBounds(89, 69, 474, 33);
	    add(tfFind);
	    
		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findDetailBooking();
			}

			public void removeUpdate(DocumentEvent e) {
				findDetailBooking();
			}

			public void changedUpdate(DocumentEvent e) {
				findDetailBooking();
			}
		});
	    
	    JLabel lblNewLabel_111 = new JLabel("Tìm kiếm");
	    lblNewLabel_111.setFont(new Font("SansSerif", Font.PLAIN, 15));
	    lblNewLabel_111.setBounds(10, 69, 123, 33);
	    add(lblNewLabel_111);
		
		
		
	}
	// Update Table DetailBooking
	private void updateTableDetailBooking() {
	    // Xóa tất cả các dòng dữ liệu hiện có trong model
	    modelDetailBooking.setRowCount(0);

//	    for (detailBookingDTO detailBooking : arrDetailBooking) {
//	        // Tạo một mảng đối tượng để chứa dữ liệu của mỗi dòng
//	        Object[] rowData = {
//	            detailBooking.getDetail_booking_id_step2(),
//	            detailBooking.getDetail_booking_room_name(),
//	            detailBooking.getDetail_booking_customer_name(),
//	            detailBooking.getSum_fee_step2(),
//	            detailBooking.getDetail_booking_status()
//	        };
//	        
//	        
//	        
//	        // Thêm mảng dữ liệu vào model của bảng
//	        modelDetailBooking.addRow(rowData);
//	    }
	    for(int i=0;i<arrDetailBooking.size();i++) {
	    	 detailBookingDTO dt = arrDetailBooking.get(i);
	    	 int id = dt.getDetail_booking_id_step2();
	    	 String room_name = dt.getDetail_booking_room_name();
	    	 String cus_name = dt.getDetail_booking_customer_name();
	    	 double sum_fee = dt.getSum_fee_step2();
	    	 String status = dt.getDetail_booking_status();
	    	 
	    	 Object[] row = {id, room_name, cus_name, sum_fee, status};
	    	 
//	    	 System.out.println(Arrays.toString(row));

	    	 
	    	 try {
	    		    modelDetailBooking.addRow(row);
//	    		    System.out.println("Thêm thành công");
	    		} catch (Exception e) {
	    		    e.printStackTrace();
	    		}
	    }
	  

	}

	// Bảng chi tiết phiếu đặt
	private void tableDetailBooking() {
	    arrDetailBooking = detailBookingBUS.getAllDeatailBooking();

	    table = new JTable();
	    table.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		clickOnTableDetailBooking(e);
	    	}
	    });

	    table.setModel(modelDetailBooking);
	    modelDetailBooking.addColumn("Mã phiếu đặt");
	    modelDetailBooking.addColumn("Tên phòng");
	    modelDetailBooking.addColumn("Khách hàng");
	    modelDetailBooking.addColumn("Tổng tiền(USD)");
	    modelDetailBooking.addColumn("Trạng thái");

	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(10, 103, 637, 477);
	    scrollPane.getViewport().setBackground(Color.white);
	    add(scrollPane);
	    
	    

	    updateTableDetailBooking();
	}

	// Tạo phương thức công khai để cập nhật bảng
	public void updateTable() {
	    arrDetailBooking = detailBookingBUS.getAllDeatailBooking();
	    updateTableDetailBooking();
	}
//	Tìm kiếm chi tiết đặt phòng
	
	private void findDetailBooking() {
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
	
//	Sự kiện click chuộtt vào bảng
	private void clickOnTableDetailBooking(MouseEvent e) {
	    int rowIndex = table.getSelectedRow();

	    if (rowIndex >= 0) {
	        detailBookingDTO selectedDetailBooking = arrDetailBooking.get(rowIndex);

	        tfID_Booking.setText(String.valueOf(selectedDetailBooking.getDetail_booking_id_step2()));
	        tfName_Customer.setText(selectedDetailBooking.getDetail_booking_customer_name());
	        tfID_PHG.setText(String.valueOf(selectedDetailBooking.getDeltai_booking_id_room_step2())); 
	        tfName_Room.setText(selectedDetailBooking.getDetail_booking_room_name());
	        tfSum_fee.setText(String.valueOf(selectedDetailBooking.getSum_fee_step2()));
	        tfStatus.setText(selectedDetailBooking.getDetail_booking_status());
	        
	    
	        Color disabledColor = Color.black; // Màu chữ khi vô hiệu hóa
	        tfID_Booking.setDisabledTextColor(disabledColor);
	        tfName_Customer.setDisabledTextColor(disabledColor);
	        tfID_PHG.setDisabledTextColor(disabledColor);
	        tfName_Room.setDisabledTextColor(disabledColor);
	        tfSum_fee.setDisabledTextColor(disabledColor);
	        tfStatus.setDisabledTextColor(disabledColor);
	        
	        tfID_Booking.setEnabled(false);
	        tfName_Customer.setEnabled(false);
	        tfID_PHG.setEnabled(false);
	        tfName_Room.setEnabled(false);
	        tfSum_fee.setEnabled(false);
	        tfStatus.setEnabled(false);
	    }
	}
}
