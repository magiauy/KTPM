package GUI;

import java.awt.Color;
import DTO.billDTO;
import BUS.billBUS;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

import DTO.serviceDTO;
import BUS.serviceBUS;

public class pnInvoice extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JLabel lblNewLabel_1_4;
	private JLabel lblNewLabel_1_5;
	private JLabel lblNewLabel_1_6;
	private JLabel lblNewLabel;

	private DefaultTableModel modelBill = new DefaultTableModel();
	private DefaultTableModel modelService = new DefaultTableModel();
	
	private billBUS billBUS= new billBUS();
	
	private ArrayList<billDTO> arrBillDetail;
	private JTextField tfID_HD;
	private JTextField tfName_Room;
	private JTextField tfName_Customer;
	private JTextField tfSum_Fee;
	
	
	private ArrayList<serviceDTO> arrService;
	private serviceBUS serviceBUS= new serviceBUS();
	JDateChooser dcStart, dcEnd, dcBill;
	
	/**
	 * Create the panel.
	 */
	public pnInvoice() {
		initComponent();
		tableBill();
		tableService();
		
	}
//	Giao diện
	private void initComponent() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);
		
		
		
		JLabel lblHan = new JLabel("Hóa đơn");
		lblHan.setForeground(new Color(0, 0, 128));
		lblHan.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblHan.setBackground(new Color(0, 0, 128));
		lblHan.setBounds(230, 25, 110, 33);
		add(lblHan);
		
		JLabel lblChiTit = new JLabel("Chi tiết");
		lblChiTit.setForeground(new Color(0, 0, 128));
		lblChiTit.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblChiTit.setBackground(new Color(0, 0, 128));
		lblChiTit.setBounds(789, 25, 101, 33);
		add(lblChiTit);
		
		
		
		lblNewLabel_1 = new JLabel("Dịch vụ");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(587, 374, 79, 33);
		add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("Mã HD");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(587, 81, 79, 33);
		add(lblNewLabel_1_1);
		
		lblNewLabel_1_2 = new JLabel("Phòng");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(587, 132, 79, 33);
		add(lblNewLabel_1_2);
		
		lblNewLabel_1_3 = new JLabel("Tên KH");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(587, 184, 79, 33);
		add(lblNewLabel_1_3);
		
		lblNewLabel_1_4 = new JLabel("Ngày thuê");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(587, 233, 79, 33);
		add(lblNewLabel_1_4);
		
		lblNewLabel_1_5 = new JLabel("Ngày trả");
		lblNewLabel_1_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_5.setBounds(811, 233, 79, 33);
		add(lblNewLabel_1_5);
		
		lblNewLabel_1_6 = new JLabel("Ngày lập HD");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(587, 283, 84, 33);
		add(lblNewLabel_1_6);
		
		lblNewLabel = new JLabel("Tổng tiền");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(587, 330, 79, 33);
		add(lblNewLabel);
		
		tfID_HD = new JTextField();
		tfID_HD.setColumns(10);
		tfID_HD.setBorder(new LineBorder(Color.lightGray, 1));
		tfID_HD.setBounds(675, 83, 110, 33);
		add(tfID_HD);
		
		tfName_Room = new JTextField();
		tfName_Room.setColumns(10);
		tfName_Room.setBorder(new LineBorder(Color.lightGray, 1));
		tfName_Room.setBounds(676, 134, 110, 33);
		add(tfName_Room);
		
		tfName_Customer = new JTextField();
		tfName_Customer.setColumns(10);
		tfName_Customer.setBorder(new LineBorder(Color.lightGray, 1));
		tfName_Customer.setBounds(675, 186, 168, 33);
		add(tfName_Customer);
		
		tfSum_Fee = new JTextField();
		tfSum_Fee.setColumns(10);
		tfSum_Fee.setBorder(new LineBorder(Color.lightGray, 1));
		tfSum_Fee.setBounds(675, 330, 168, 33);
		add(tfSum_Fee);
		
		dcStart = new JDateChooser();
		dcStart.setDateFormatString("yyyy-MM-dd");
		dcStart.setBounds(673, 236, 128, 30);
		add(dcStart);
		
		dcEnd = new JDateChooser();
		dcEnd.setDateFormatString("yyyy-MM-dd");
		dcEnd.setBounds(872, 236, 128, 30);
		add(dcEnd);
		
		dcBill = new JDateChooser();
		dcBill.setDateFormatString("yyyy-MM-dd");
		dcBill.setBounds(673, 286, 128, 30);
		add(dcBill);
	}
//	Update bảng hóa đơn
	private void updateTableBill() {
		modelBill.setRowCount(0);
		for(billDTO bill:arrBillDetail) {
			modelBill.addRow(new Object[] {
					bill.getId_hoadon(),
					bill.getName_customer(),
					bill.getName_room(),
					bill.getTongtien_hd(),
					bill.getNgaylap_hd()
			});
		}
	}
//	Table Hóa đơn
	private void tableBill() {
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseClick(e);
			}
		});
		table.setModel(modelBill);
		
		modelBill.addColumn("ID HD");
		modelBill.addColumn("Tên KH");
		modelBill.addColumn("Tên phòng");
		modelBill.addColumn("Tổng tiền(USD)");
		modelBill.addColumn("Ngày lập HD");
		
		JScrollPane scrollPane= new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBounds(10, 68, 556, 438);
		add(scrollPane);
		
//		Lấy từ mảng đã kết bảng
		arrBillDetail=billBUS.getAllBillDetail();
		
		updateTableBill();
	}
//	Table dịch vụ
	private void tableService() {
		table_1 = new JTable();
		
		
		table_1.setModel(modelService);
		modelService.addColumn("Dịch vụ");
		modelService.addColumn("Số lượng");
		modelService.addColumn("Đơn giá");
		
		JScrollPane scrollPane1= new JScrollPane(table_1);
		scrollPane1.getViewport().setBackground(Color.white);
		scrollPane1.setBounds(587, 410, 413, 96);
		add(scrollPane1);
	}
//	Sự kiện click chuột cho bảng hoas đơn
	private void mouseClick(MouseEvent e) {
		int i=table.getSelectedRow();
		if(i>=0) {
			billDTO billSelected= arrBillDetail.get(i);
			
			tfID_HD.setText(String.valueOf(billSelected.getId_kh()));
			tfName_Room.setText(billSelected.getName_room());
			tfName_Customer.setText(billSelected.getName_customer());
			dcStart.setDate(billSelected.getNgaythue());
			dcEnd.setDate(billSelected.getNgaytra());
			dcBill.setDate(billSelected.getNgaylap_hd());
			tfSum_Fee.setText(String.valueOf(billSelected.getTongtien_hd()));
			
			arrService=serviceBUS.gettAllServiceInBIll();
			modelService.setRowCount(0);
			for(serviceDTO service : arrService) {
				if(billSelected.getId_hoadon()==service.getId_hoadon()) {
					modelService.addRow(new Object[] {
							service.getService_name(),
							service.getSoluong_dv(),
							service.getPhi_moidichvu()
					});
				}

			}
			
		
			tfID_HD.setEnabled(false);
			tfName_Room.setEnabled(false);
			tfName_Customer.setEnabled(false);
			dcStart.setEnabled(false);
			dcEnd.setEnabled(false);
			dcBill.setEnabled(false);
			tfSum_Fee.setEnabled(false);
			
		}
	}
}
