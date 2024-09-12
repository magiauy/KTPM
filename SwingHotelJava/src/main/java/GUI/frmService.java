package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.util.ArrayList;
import DTO.serviceDTO;
import BUS.serviceBUS;
public class frmService {

	private JFrame frame;
	private JTable table;
	
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
	private JButton btnAdd;
	
	private ArrayList<serviceDTO> arrService;

	private serviceBUS serviceBUS= new serviceBUS();
	private JTable table_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_3;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frmService window = new frmService();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//	Get frameService
	public JFrame getFrameService() {
		return frame;
	}
	/**
	 * Create the application.
	 */
	public frmService() {
		initialize();
		tableChooseService();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1071, 450);
		
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(23, 33, 996, 246);
		frame.getContentPane().add(table);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setEnabled(true);
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBounds(892, 343, 127, 46);
		frame.getContentPane().add(btnAdd);
		

		table_1 = new JTable();
		table_1.setBounds(21, 229, 395, 174);
		frame.getContentPane().add(table_1);
		
		table_1.setModel(modelTableService1);
		
		modelTableService1.addColumn("ID DV");
		modelTableService1.addColumn("Tên dịch vụ");
		modelTableService1.addColumn("Số lượng");
		modelTableService1.addColumn("Tổng tiền");
		
		JScrollPane scrollPane1= new JScrollPane(table_1);
		scrollPane1.setBounds(23, 215, 527, 174);
		scrollPane1.getViewport().setBackground(Color.white);
		frame.getContentPane().add(scrollPane1);
		
		table_1.getModel().addTableModelListener(new TableModelListener() {
		    @Override
		    public void tableChanged(TableModelEvent e) {
		        int row = e.getFirstRow();
		        int column = e.getColumn();
		        if (column == 2) { // Chỉ xử lý khi cột số lượng thay đổi
		            String quantityStr = (String) table_1.getValueAt(row, column);
		            try {
		                int quantity = Integer.parseInt(quantityStr);
		                double price = (double) table_1.getValueAt(row, 3); // Lấy giá dịch vụ từ cột 3
		                double totalPrice = quantity * price; // Tính tổng tiền

		                // Cập nhật giá trị tổng tiền trong cùng một hàng
		                table_1.setValueAt(totalPrice, row, 3);
		            } catch (NumberFormatException ex) {
		                // Xử lý ngoại lệ nếu không thể chuyển đổi thành số nguyên
		                // Ví dụ: hiển thị thông báo hoặc thực hiện hành động khác tùy thuộc vào yêu cầu của bạn
		                ex.printStackTrace();
		            }
		        }
		    }
		});

		
		frame.setLocationRelativeTo(null);
	}
//	Tạo bảng chọn dịch vụ
	private void tableChooseService() {
		table.setModel(modelTableService);
		
		modelTableService.addColumn("Tên dịch vụ");
		modelTableService.addColumn("Giá dịch vụ");
		modelTableService.addColumn("Mô tả");
		modelTableService.addColumn("Duyệt");
		
		JScrollPane scrollPane= new JScrollPane(table);
		scrollPane.setBounds(23, 33, 971, 156);
		scrollPane.getViewport().setBackground(Color.white);
		frame.getContentPane().add(scrollPane);
		
		lblNewLabel = new JLabel("Tổng tiền dịch vụ:");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(560, 356, 118, 33);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(684, 356, 103, 33);
		frame.getContentPane().add(lblNewLabel_3);
		
		arrService=serviceBUS.getAllService();
		
		updateTableService();
		
		 // Đặt cell editor cho cột duyệt
	    TableColumn duyetColumn = table.getColumn("Duyệt");
	    duyetColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
		
	    modelTableService.addTableModelListener(new TableModelListener() {
	        @Override
	        public void tableChanged(TableModelEvent e) {
	            int row = e.getFirstRow();
	            int column = e.getColumn();
	            if (column == 3 && row != -1) { 
	                Boolean checked = (Boolean) modelTableService.getValueAt(row, column);
	                if (checked) {
	                    addServiceToSecondTable(row);
	                } else {
	                    removeServiceFromSecondTable(row);
	                }
	            }
	        }
	    });
	}
//	UPdate table load dữ liệu dịch vụ
	private void updateTableService() {
		modelTableService.setRowCount(0);
		for(serviceDTO service : arrService) {
			modelTableService.addRow(new Object[] {
					service.getService_name(),
					service.getService_price(),
					service.getService_description(),
					false
			});
		}
	}
//	Inner xuống bảng con
	private void addServiceToSecondTable(int row) {
		serviceDTO selectedService = arrService.get(row);
		
	    String serviceName = selectedService.getService_name();
	    Double servicePrice = selectedService.getService_price();

	    modelTableService1.addRow(new Object[] { 
	    		selectedService.getService_id(),
	    		serviceName, 1, 
	    		servicePrice 
	    	});
	}
	
//	Bỏ checkbox thì xóa khỏi bảng con
	private void removeServiceFromSecondTable(int row) {
	    String serviceName = (String) modelTableService.getValueAt(row, 0);

	    // Find the row with this service name and remove it
	    for (int i = 0; i < modelTableService1.getRowCount(); i++) {
	        if (modelTableService1.getValueAt(i, 1).equals(serviceName)) {
	            modelTableService1.removeRow(i);
	            break;
	        }
	    }
	}
}
