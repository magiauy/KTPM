package GUI;

import javax.swing.JPanel;
import DTO.detailServiceDTO;
import DTO.roomDTO;
import BUS.detailServiceBUS;
import BUS.roomBUS;

import java.util.concurrent.TimeUnit;
import com.toedter.calendar.JDateChooser;
import DTO.serviceDTO;
import BUS.serviceBUS;

import java.util.ArrayList;
import java.util.Calendar;

import DTO.bookingDTO;
import DTO.detailBookingDTO;
import BUS.deltailBookingBUS;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import DTO.billDTO;
import BUS.billBUS;

public class pnCheckOut extends JPanel {

	private static final long serialVersionUID = 1L;
	private deltailBookingBUS detailBookingBUS= new deltailBookingBUS();
	private ArrayList<detailBookingDTO> arrDetailBookingNotCheckout;
			
	
	private JTable table;
	private DefaultTableModel modelCheckout = new DefaultTableModel();
	private JTextField tfFind;
	private JTextField tfID;
	private JTextField tfName_Room;
	private JTextField tfSumm_fee;
	private JTextField tfroom_fee;
	
	private JDateChooser dcEnd, dcStart, dcBill;
	
	private JButton btnCheckout ;
	private JTextField tfName_Customer;
	
	private billBUS billBUS= new billBUS();
	private ArrayList<billDTO> arrBill;
	private JLabel lblBngDchV;
	
	private ArrayList<serviceDTO> arrService;
	private serviceBUS serviceBUS= new serviceBUS();
	
	private detailServiceBUS dlServiceBUS= new detailServiceBUS();
	
	private roomBUS roomBUS= new roomBUS();
	
	
	
	private JTable tableService;
	private DefaultTableModel modelTableService = new DefaultTableModel() {
	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        switch (columnIndex) {
	            case 0: return String.class;  // Tên dịch vụ
	            case 1: return Double.class; // Giá dịch vụ
	            case 2: return String.class; // Mô tả
	            case 3: return Boolean.class; // Duyệt
	            default: return Object.class;
	        }
	    }
	    
	    @Override
	    public boolean isCellEditable(int row, int column) {
	        // Chỉ cột "Duyệt" là có thể chỉnh sửa
	        return column == 3;
	    }
	};
	
	private DefaultTableModel modelTableService1 = new DefaultTableModel(); 
	private JTable table_1;
	private JLabel lblHan;
	private JLabel lblDchV;
	
	

	/**
	 * Create the panel.
	 */
	public pnCheckOut() {
		initComponent();
		table();
		autoSetID() ;

		
	}
//	Giao diện
	private void initComponent() {
		serviceBUS.loaddata();
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);
		
		JLabel lblDanhSchTr = new JLabel("Danh sách trả phòng");
		lblDanhSchTr.setForeground(new Color(0, 0, 128));
		lblDanhSchTr.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDanhSchTr.setBounds(196, 10, 206, 25);
		add(lblDanhSchTr);
		
		
		btnCheckout = new JButton("Thanh toán và trả phòng");
		
		
		btnCheckout.setForeground(Color.WHITE);
		btnCheckout.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnCheckout.setBorderPainted(false);
		btnCheckout.setBackground(new Color(0, 206, 209));
		btnCheckout.setBounds(840, 527, 227, 33);
		add(btnCheckout);
		
		JLabel lblTngTin = new JLabel("Tổng tiền");
		lblTngTin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTngTin.setBounds(559, 531, 63, 25);
		add(lblTngTin);
		
		JLabel lblNewLabel_111 = new JLabel("Tìm kiếm");
		lblNewLabel_111.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_111.setBounds(20, 43, 123, 33);
		add(lblNewLabel_111);
		
		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBorder(new LineBorder(Color.lightGray,1));
		tfFind.setBounds(89, 45, 417, 33);
		add(tfFind);
		
		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findCheckOut();
			}

			public void removeUpdate(DocumentEvent e) {
				findCheckOut();
			}

			public void changedUpdate(DocumentEvent e) {
				findCheckOut();
			}
		});
		
		JLabel lblMHan = new JLabel("Mã hóa đơn");
		lblMHan.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblMHan.setBounds(559, 303, 111, 25);
		add(lblMHan);
		
		JLabel lblNgyThu = new JLabel("Ngày thuê");
		lblNgyThu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNgyThu.setBounds(559, 381, 111, 25);
		add(lblNgyThu);
		
		JLabel lblNgyTr = new JLabel("Ngày trả");
		lblNgyTr.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNgyTr.setBounds(559, 416, 111, 25);
		add(lblNgyTr);
		
		JLabel lblNgyInHa = new JLabel("Ngày in HD");
		lblNgyInHa.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNgyInHa.setBounds(559, 451, 111, 25);
		add(lblNgyInHa);
		JLabel lbltienphong = new JLabel("Tiền phòng");
		lbltienphong.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbltienphong.setBounds(559, 490, 111, 25);
		add(lbltienphong);

		tfroom_fee = new JTextField();
		tfroom_fee.setColumns(10);
		tfroom_fee.setBorder(new LineBorder(Color.lightGray, 1));
		tfroom_fee.setBounds(645, 490, 164, 33);
		add(tfroom_fee);

		JLabel lblTnPhng = new JLabel("Tên phòng");
		lblTnPhng.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTnPhng.setBounds(686, 303, 111, 25);
		add(lblTnPhng);
		
		
		dcStart = new JDateChooser();
		dcStart.setDateFormatString("yyyy-MM-dd");
		dcStart.setBounds(645, 376, 217, 30);
		add(dcStart);
		
		dcEnd= new JDateChooser();
		dcEnd.setDateFormatString("yyyy-MM-dd");
		dcEnd.setBounds(645,411,217,30);
		add(dcEnd);
		
		dcBill= new JDateChooser();
		dcBill.setDateFormatString("yyyy-MM-dd");
		dcBill.setBounds(645,451,217,30);
		add(dcBill);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBorder(new LineBorder(Color.lightGray, 1));
		tfID.setBounds(645, 301, 31, 33);
		add(tfID);
		
		tfName_Room = new JTextField();
		tfName_Room.setColumns(10);
		tfName_Room.setBorder(new LineBorder(Color.lightGray, 1));
		tfName_Room.setBounds(770, 301, 92, 33);
		add(tfName_Room);
		
		tfSumm_fee = new JTextField();
		tfSumm_fee.setColumns(10);
		tfSumm_fee.setBorder(new LineBorder(Color.lightGray, 1));
		tfSumm_fee.setBounds(645, 529, 164, 33);
		add(tfSumm_fee);
		

		JLabel lblTnKh = new JLabel("Tên KH");
		lblTnKh.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblTnKh.setBounds(559, 344, 111, 25);
		add(lblTnKh);
		
		tfName_Customer = new JTextField();
		tfName_Customer.setColumns(10);
		tfName_Customer.setBorder(new LineBorder(Color.lightGray, 1));
		tfName_Customer.setBounds(645, 338, 217, 33);
		add(tfName_Customer);
		
		table_1 = new JTable();
		table_1.setBounds(21, 229, 395, 174);
		add(table_1);
		
		table_1.setModel(modelTableService1);
		
		modelTableService1.addColumn("ID DV");
		modelTableService1.addColumn("Tên dịch vụ");
		modelTableService1.addColumn("Số lượng");
		modelTableService1.addColumn("Tổng tiền(USD)");
		
		JScrollPane scrollPane1= new JScrollPane(table_1);
		scrollPane1.setBounds(559, 118, 508, 163);
		scrollPane1.getViewport().setBackground(Color.white);
		add(scrollPane1);

//		Sự kiện tính tổng tiền khi thay đổi số lượng
		table_1.getModel().addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {

		    }
		  });
		

	    lblHan = new JLabel("Hóa đơn");
		lblHan.setForeground(new Color(0, 0, 128));
		lblHan.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblHan.setBounds(780, 20, 206, 25);
		add(lblHan);
		
		lblDchV = new JLabel("Dịch vụ đã chọn");
		lblDchV.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblDchV.setBounds(559, 83, 111, 25);
		add(lblDchV);

		
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddBillActionPerformed(e);
			}
		});
		
	}
//	Update bảng
	private void updateTableDetailBookingCheckout() {

		modelCheckout.setRowCount(0);
		for(detailBookingDTO detailBookingCheckout:arrDetailBookingNotCheckout) {
			double fee = detailBookingCheckout.getSum_fee_step2() + dlServiceBUS.calculateTotal(detailBookingCheckout.getDetail_booking_id_step2());
			modelCheckout.addRow(new Object[] {
					detailBookingCheckout.getDetail_booking_id_step2(),
		            detailBookingCheckout.getDetail_booking_room_name(),
		            detailBookingCheckout.getDetail_booking_customer_name(),
		            fee,
		            detailBookingCheckout.getDetail_booking_status()
			});
		}
	}
//	Tab 
	public void updateTableCheckout() {
		arrDetailBookingNotCheckout=detailBookingBUS.getDetailBookingNotCheckout();
		updateTableDetailBookingCheckout();
	}
//	Tải lên bảng (chưa checkout)
	private void table() {
		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableCheckoutClicked(e);
			}
		});
		
		table.setModel(modelCheckout);
		modelCheckout.addColumn("Mã phiếu đặt");
	    modelCheckout.addColumn("Tên phòng");
	    modelCheckout.addColumn("Khách hàng");
	    modelCheckout.addColumn("Tổng tiền(USD)");
	    modelCheckout.addColumn("Trạng thái");
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 83, 522, 198);
		scrollPane.getViewport().setBackground(Color.white);
		add(scrollPane);
		
		
		arrDetailBookingNotCheckout=detailBookingBUS.getDetailBookingNotCheckout();
		
		updateTableDetailBookingCheckout();	
	}
//	setID tự tăng cho mỗi hóa đơn
	private void autoSetID() {
		 arrBill=billBUS.getAllBill();
		 int maxID = 0;
	        for (billDTO bill:arrBill) {
	            if (bill.getId_hoadon() > maxID) {
	                maxID = bill.getId_hoadon();
	            }
	        }
	        int newID = maxID + 1; 
	        tfID.setText(String.valueOf(newID)); 
	        tfID.setEnabled(false); 
	}

//	Sự kiện click bảng
	private void tableCheckoutClicked(MouseEvent e) {
		int i=table.getSelectedRow();
		if(i>=0) {
			detailBookingDTO selectedDetaiBooking = arrDetailBookingNotCheckout.get(i);
			
			tfName_Customer.setText(selectedDetaiBooking.getDetail_booking_customer_name());
			tfName_Room.setText(selectedDetaiBooking.getDetail_booking_room_name());
			
			
			
			Date startDate = selectedDetaiBooking.getDate_start_booking(); 
		    dcStart.setDate(startDate);

		    Date endDate =selectedDetaiBooking.getDate_end_booking();
		    dcEnd.setDate(endDate);
		    

//		    Phần tổng tiền cần gọi lại hàm

		    tfName_Customer.setEnabled(false);
			tfName_Room.setEnabled(false);
			tfroom_fee.setText(String.valueOf(selectedDetaiBooking.getSum_fee_step2()));
			tfroom_fee.setEnabled(false);
			tfSumm_fee.setText(String.valueOf(selectedDetaiBooking.getSum_fee_step2()+dlServiceBUS.calculateTotal(selectedDetaiBooking.getDetail_booking_id_step2())));
		    dcStart.setEnabled(false);
		    dcBill.setEnabled(false);
			tfSumm_fee.setEnabled(false);
			dcBill.setDate(new java.util.Date());
			loadservicetable(selectedDetaiBooking.getDetail_booking_id_step2());
		}
	}
	public void loadservicetable(int id){
		modelTableService1.setRowCount(0);
		ArrayList<detailServiceDTO> arrDetailService = dlServiceBUS.getDetailServiceByID_PD(id);
		for (detailServiceDTO detailService : arrDetailService) {
			modelTableService1.addRow(new Object[] {
					detailService.getId_dv(),
					serviceBUS.getnamebyID(detailService.getId_dv()),
					detailService.getSoluong_dv(),
					detailService.getTongtien_dv()
			});
		}

	}

//	show table dịch vụ
//	private void showTableService() {
//	tableService= new JTable();
//	tableService.setModel(modelTableService);
//
//	modelTableService.addColumn("Tên dịch vụ");
//	modelTableService.addColumn("Giá dịch vụ");
//	modelTableService.addColumn("Mô tả");
//	modelTableService.addColumn("Duyệt");
//
//	JScrollPane scrollPane= new JScrollPane(tableService);
//	scrollPane.setBounds(20, 381, 522, 179);
//	scrollPane.getViewport().setBackground(Color.white);
//	add(scrollPane);
//
//
//	arrService=serviceBUS.getAllService();
//
//	updateTableService();
//
//	 // Đặt cell editor cho cột duyệt
//    TableColumn duyetColumn = tableService.getColumn("Duyệt");
//    duyetColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
//
//    modelTableService.addTableModelListener(new TableModelListener() {
//        @Override
//        public void tableChanged(TableModelEvent e) {
//            int row = e.getFirstRow();
//            int column = e.getColumn();
//            if (column == 3 && row != -1) {
//                Boolean checked = (Boolean) modelTableService.getValueAt(row, column);
//                if (checked) {
//                    addServiceToSecondTable(row);
//                    caculFee();
//                } else {
//                    removeServiceFromSecondTable(row);
//                    caculFee();
//                }
//            }
//        	}
//    	});
//
//	}
//	Tìm kiếm Phòng cần trả
	private void findCheckOut() {
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
	

	



//	Sự kiện nút thanh toán và trả phòng
	public void btnAddBillActionPerformed(ActionEvent e) {
	    try {
	        if (dcEnd.getDate() == null || dcBill.getDate() == null || tfSumm_fee.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Thông tin không được để trống");
	        } else {
	            billDTO bill = new billDTO();
	            bill.setId_hoadon(Integer.parseInt(tfID.getText()));
	            bill.setTongtien_hd(Double.parseDouble(tfSumm_fee.getText()));

	            java.util.Date endDate = dcBill.getDate();
	            java.sql.Date sqlBillDate = new java.sql.Date(endDate.getTime());
	            bill.setNgaylap_hd(sqlBillDate);

	            int i = table.getSelectedRow(); // Bảng chi tiết dịch vụ
	            if (i >= 0) {
	                detailBookingDTO selectedDetailBooking = arrDetailBookingNotCheckout.get(i);
	                bill.setId_phieudat(selectedDetailBooking.getDetail_booking_id_step2());
	            }

	            String message = billBUS.addBill(bill);
	            boolean detailServiceAdded = true; 
	            if(i>=0) {
	            	 detailBookingDTO selectedDetailBooking = arrDetailBookingNotCheckout.get(i);
	            	 int roomId =selectedDetailBooking.getDeltai_booking_id_room_step2();
	                 
	                 // Tạo một đối tượng roomDTO và thiết lập ID phòng
	                 roomDTO room = new roomDTO();
	                 room.setRoom_id(roomId);
	                 
	                 // Cập nhật trạng thái phòng
	                 roomBUS.updateStatusRoomEmpty(room);
	                 
	                 
	                 int detailBookingId =selectedDetailBooking.getDetail_booking_id_step2();
	                 
	                 detailBookingDTO dlBooking= new detailBookingDTO();
	                 dlBooking.setDetail_booking_id_step2(detailBookingId);
	                 
	                 detailBookingBUS.updateStatusDetailBooking(dlBooking);
	                 
	                 updateTableCheckout();
	
	            }
	            if (message.equals("Thêm hóa đơn thành công")) {
	                JOptionPane.showMessageDialog(this, message);

	                for (int j = 0; j < table_1.getRowCount(); j++) {
	                    detailServiceDTO dlsv = new detailServiceDTO();
	                    dlsv.setid_pn(Integer.parseInt(tfID.getText()));
	                    dlsv.setId_dv((int) table_1.getValueAt(j, 0));
	                    dlsv.setSoluong_dv(Integer.parseInt(table_1.getValueAt(j, 2).toString()));
	                    dlsv.setTongtien_dv(Double.parseDouble(table_1.getValueAt(j, 3).toString()));

	                    String message1 = dlServiceBUS.addDetailSerice(dlsv);
	                    
	                    if (!"Thêm chi tiết dịch vụ thành công".equals(message1)) {
	                        detailServiceAdded = false;
	                        break; 
	                    }
	                    
	                }
	            }
	            if (detailServiceAdded) {
                    reset();
                }
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
	    }
	}
//	Reset
	private void reset() {
		 autoSetID();
	        
	        tfSumm_fee.setText("");
	        tfName_Customer.setText("");
	        tfName_Room.setText("");
	        dcBill.setDate(null);
	        dcStart.setDate(null);
	        dcEnd.setDate(null);
			tfroom_fee.setText("");
	        modelTableService1.setRowCount(0);
	}

	
	
}
