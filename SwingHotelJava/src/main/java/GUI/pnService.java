package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DTO.customerDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.JTextArea;
import DTO.serviceDTO;
import BUS.serviceBUS;
import javax.swing.ImageIcon;

public class pnService extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField tfFind;
	private JTextField tfName;
	private JTextField tfPrice;
	private DefaultTableModel modelService = new DefaultTableModel();
	private JTextField tfID;
	private JTextPane tpDescription;
	ArrayList<serviceDTO> arrService = new ArrayList<serviceDTO>();
	serviceBUS svBUS = new serviceBUS();

	JButton btnAdd, btnDelete, btnEdit, btnReset;

	/**
	 * Create the panel.
	 */
	public pnService() {
		intitComponents();
		updateInit();
		loadServiceList();
		autoSetID();
	}

	// Update Giao diện
	private void updateInit() {
		tfID.setEnabled(true);
		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnReset.setEnabled(true);
	}

	// Giao diện
	public void intitComponents() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);

		table = new JTable();

		JLabel lblNewLabel_1_6 = new JLabel("Tìm kiếm");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(294, 328, 79, 33);
		add(lblNewLabel_1_6);

		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBorder(new LineBorder(Color.lightGray, 1));
		tfFind.setBounds(383, 330, 349, 33);
		add(tfFind);

		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findService();
			}

			public void removeUpdate(DocumentEvent e) {
				findService();
			}

			public void changedUpdate(DocumentEvent e) {
				findService();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Tên dịch vụ");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(24, 474, 112, 33);
		add(lblNewLabel_1);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBorder(new LineBorder(Color.lightGray, 1));
		tfName.setBounds(146, 474, 227, 33);
		add(tfName);

		JLabel lblNewLabel_1_1 = new JLabel("Đơn giá(USD)");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(24, 527, 112, 33);
		add(lblNewLabel_1_1);

		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBorder(new LineBorder(Color.lightGray, 1));
		tfPrice.setBounds(146, 529, 227, 33);
		add(tfPrice);

		JLabel lblNewLabel_1_2 = new JLabel("Mô tả");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(488, 412, 41, 33);
		add(lblNewLabel_1_2);

		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBounds(952, 388, 102, 33);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addServiceActionPerformed(e);
			}
		});

		btnDelete = new JButton("Xóa");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(0, 206, 209));
		btnDelete.setBounds(952, 431, 102, 33);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteServiceActionPerformed(e);
			}
		});
		btnEdit = new JButton("Sửa");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(0, 206, 209));
		btnEdit.setBounds(952, 474, 102, 33);
		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editServiceActionPerformed(e);
			}
		});

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
		btnReset.setBounds(952, 517, 102, 33);
		add(btnReset);

		JLabel lblQunLDch = new JLabel(" Quản lý dịch vụ");
		lblQunLDch.setIcon(new ImageIcon(pnService.class.getResource("/Picture/operator.png")));
		lblQunLDch.setForeground(new Color(0, 0, 128));
		lblQunLDch.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblQunLDch.setBounds(409, 1, 273, 63);
		add(lblQunLDch);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(24, 412, 102, 33);
		add(lblNewLabel);

		tfID = new JTextField();
		tfID.setBackground(new Color(255, 255, 255));
		tfID.setBounds(146, 414, 227, 33);
		tfID.setBorder(new LineBorder(Color.lightGray, 1));
		add(tfID);
		tfID.setColumns(10);
		tpDescription = new JTextPane();
		tpDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tpDescription.setBackground(new Color(255, 240, 245));
		tpDescription.setBounds(539, 414, 334, 136);
		add(tpDescription);
	}

	// Load list lên bảng
	public void loadServiceList() {
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableServiceMouseClicked(e);
			}
		});

		table.setModel(modelService);
		modelService.addColumn("ID");
		modelService.addColumn("Tên Dịch Vụ");
		modelService.addColumn("Đơn giá");
		modelService.addColumn("Mô tả");
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 66, 1022, 254);
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane);

		arrService = svBUS.getAllService();
		showListService();
	}

	// Update lại bảng
	private void showListService() {
		modelService.setRowCount(0);
		for (serviceDTO sv : arrService) {
			modelService.addRow(new Object[] {
					sv.getService_id(),
					sv.getService_name(),
					sv.getService_price(),
					sv.getService_description()
			});
		}
	}

	// Sự kiện click hàng trong bảng
	private void tableServiceMouseClicked(MouseEvent e) {
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(true);
		btnEdit.setEnabled(true);
		tfID.setEnabled(false);

		int i = table.getSelectedRow();
		if (i >= 0) {
			tfID.setText(modelService.getValueAt(i, 0).toString());
			tfName.setText(modelService.getValueAt(i, 1).toString());
			tfPrice.setText(modelService.getValueAt(i, 2).toString());
			tpDescription.setText(modelService.getValueAt(i, 3).toString());
		}
	}

	// Xử lí mã tự tăng
	private void autoSetID() {
		int maxID = 0;
		for (serviceDTO s : arrService) {
			if (s.getService_id() > maxID) {
				maxID = s.getService_id();
			}
		}
		int newID = maxID + 1;
		tfID.setText(String.valueOf(newID));
		tfID.setEnabled(false);

	}

	private void findService() {
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

	// Sự kiện thêm
	private void addServiceActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					tfName.getText().trim().equals("") ||
					tfPrice.getText().trim().equals("") ||
					tpDescription.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			} else {
				serviceDTO sv = new serviceDTO();
				sv.setService_id(Integer.parseInt(tfID.getText()));
				sv.setService_name(tfName.getText());
				sv.setService_price(Double.parseDouble(tfPrice.getText()));
				sv.setService_description(tpDescription.getText());
				String message = svBUS.addService(sv);
				JOptionPane.showMessageDialog(this, message);

				if (message.equals("Thêm dịch vụ thành công")) {
					arrService.add(sv);// Thêm vào list cũ
					showListService(); // Nếu thêm thành công, cập nhật lại dữ liệu trên bảng

					btnResetActionPerformed(e);
				}
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	// Sự kiện xóa
	private void deleteServiceActionPerformed(ActionEvent e) {
		int i = table.getSelectedRow();
		if (i >= 0) {
			int serviceId = (int) modelService.getValueAt(i, 0);

			String message = svBUS.deleteService(serviceId);

			JOptionPane.showMessageDialog(this, message);

			if (message.equals("Đã xóa dịch vụ")) {
				modelService.removeRow(i);
				arrService.remove(i);
			}

			btnResetActionPerformed(e);
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một dịch vụ để xóa!");
		}
	}

	// Sự kiện chỉnh sửa
	private void editServiceActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					tfName.getText().trim().equals("") ||
					tfPrice.getText().trim().equals("") ||
					tpDescription.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			} else {
				int i = table.getSelectedRow();
				if (i >= 0) {
					serviceDTO sv = new serviceDTO();
					sv.setService_id((int) modelService.getValueAt(i, 0));

					if (!tfID.getText().equals(modelService.getValueAt(i, 0).toString())) {
						JOptionPane.showMessageDialog(this, "Không được phép chỉnh sửa mã", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					sv.setService_name(tfName.getText());
					sv.setService_price(Double.parseDouble(tfPrice.getText()));
					sv.setService_description(tpDescription.getText());
					String message = svBUS.editService(sv);
					JOptionPane.showMessageDialog(this, message);
					if (message.equals("Chỉnh sửa thông tin dịch vụ thành công")) {
						// Cập nhật lại lên table
						modelService.setValueAt(sv.getService_name(), i, 1);
						modelService.setValueAt(sv.getService_price(), i, 2);
						modelService.setValueAt(sv.getService_description(), i, 3);
					} else {
						JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ để chỉnh sửa!");
					}
				}
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	// Xử lý sự kiện nút chỉnh sửa
	private void btnResetActionPerformed(ActionEvent e) {
		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);

		autoSetID();

		showListService();

		tfName.setText("");
		tfPrice.setText("");
		tpDescription.setText("");
	}
	// Xử lý sự kiện tìm kiế

}