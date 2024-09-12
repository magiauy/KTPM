package GUI;

import DTO.customerDTO;
import BUS.checkInputBUS;
import BUS.customerBUS;
import java.util.ArrayList;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class pnCustomer extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String ArrayList = null;

	private JTable table;
	private DefaultTableModel modelCustomer = new DefaultTableModel();
	ArrayList<customerDTO> arrCustomer = new ArrayList<customerDTO>();

	private JTextField tfFind;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfID;
	private JTextField tfCCCD;

	private JRadioButton rdbtnNu, rdbtnNam;
	private boolean isGenderSelected = false;
	private String gender;
	private ButtonGroup genderGroup;

	private JButton btnAdd, btnDelete, btnEdit, btnReset;

	customerBUS ctmBUS = new customerBUS();
	checkInputBUS chkBUS = new checkInputBUS();

	/**
	 * Create the panel.
	 */
	public pnCustomer() {
		initComponents();
		updateInit();
		loadCustomerList();
		autoSetID();
	}

	// Update cho giao diện
	private void updateInit() {
		tfID.setEnabled(true);

		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnReset.setEnabled(true);

	}

	// Load giao diện
	private void initComponents() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản lý khách hàng");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBackground(new Color(248, 248, 255));
		lblNewLabel.setIcon(new ImageIcon(pnCustomer.class.getResource("/Picture/customer.png")));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setBounds(336, 0, 318, 66);
		add(lblNewLabel);

		JLabel lblNewLabel_1_6 = new JLabel("Tìm kiếm");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(273, 330, 79, 33);
		add(lblNewLabel_1_6);

		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBorder(new LineBorder(Color.lightGray, 1));
		tfFind.setBounds(361, 332, 349, 33);
		add(tfFind);

		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findCustomer();
			}

			public void removeUpdate(DocumentEvent e) {
				findCustomer();
			}

			public void changedUpdate(DocumentEvent e) {
				findCustomer();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(36, 463, 79, 33);
		add(lblNewLabel_1);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBorder(new LineBorder(Color.lightGray, 1));
		tfName.setBounds(125, 465, 227, 33);
		add(tfName);

		JLabel lblNewLabel_1_1 = new JLabel("Điện thoại");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(430, 408, 79, 33);
		add(lblNewLabel_1_1);

		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBorder(new LineBorder(Color.lightGray, 1));
		tfPhone.setBounds(519, 410, 227, 33);
		add(tfPhone);

		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(430, 463, 79, 33);
		add(lblNewLabel_1_2);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBorder(new LineBorder(Color.lightGray, 1));
		tfEmail.setBounds(519, 465, 227, 33);
		add(tfEmail);

		JLabel lblNewLabel_1_3 = new JLabel("Giới tính");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(36, 525, 79, 33);
		add(lblNewLabel_1_3);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("SansSerif", Font.ITALIC, 13));
		rdbtnNam.setBackground(new Color(255, 255, 255));
		rdbtnNam.setBounds(125, 531, 103, 21);
		add(rdbtnNam);
		rdbtnNam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGenderSelected = true;
				gender = "NAM";
			}
		});

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("SansSerif", Font.ITALIC, 13));
		rdbtnNu.setBackground(new Color(255, 255, 255));
		rdbtnNu.setBounds(225, 531, 103, 21);
		add(rdbtnNu);
		rdbtnNu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGenderSelected = true;
				gender = "NỮ";
			}
		});

		genderGroup = new ButtonGroup();
		genderGroup.add(rdbtnNam);
		genderGroup.add(rdbtnNu);

		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBounds(946, 396, 102, 33);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addCustomerActionPerformed(e);
			}
		});

		btnDelete = new JButton("Xóa");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(0, 206, 209));
		btnDelete.setBounds(946, 439, 102, 33);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteCustomerActionPerformed(e);
			}
		});

		btnEdit = new JButton("Sửa");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(0, 206, 209));
		btnEdit.setBounds(946, 482, 102, 33);
		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editCustomerActionPerformed(e);
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
		btnReset.setBounds(946, 525, 102, 33);
		add(btnReset);

		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBorder(new LineBorder(Color.lightGray, 1));
		tfID.setBounds(125, 408, 227, 33);
		add(tfID);

		JLabel lblNewLabel_1_4 = new JLabel("ID");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(36, 408, 79, 33);
		add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_2_1 = new JLabel("CCCD");
		lblNewLabel_1_2_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(430, 525, 79, 33);
		add(lblNewLabel_1_2_1);

		tfCCCD = new JTextField();
		tfCCCD.setColumns(10);
		tfCCCD.setBorder(new LineBorder(Color.lightGray, 1));
		tfCCCD.setBounds(519, 525, 227, 33);
		add(tfCCCD);

	}

	// Hiển thị lại list
	private void showListCustomer() {
		modelCustomer.setRowCount(0);
		for (customerDTO ctm : arrCustomer) {
			modelCustomer.addRow(new Object[] {
					ctm.getCustomer_id(),
					ctm.getCustomer_name(),
					ctm.getCustomer_gender(),
					ctm.getCustomer_phone(),
					ctm.getCustomer_email(),
					ctm.getCustomer_cccd()
			});
		}
	}

	// Load danh sách khách hàng vào bảng
	public void loadCustomerList() {
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableCustomerMouseClicked(e);
			}
		});

		table.setModel(modelCustomer);
		modelCustomer.addColumn("ID");
		modelCustomer.addColumn("Họ tên");
		modelCustomer.addColumn("Giới tính");
		modelCustomer.addColumn("Điện thoại");
		modelCustomer.addColumn("Email");
		modelCustomer.addColumn("CCCD");

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(26, 66, 1022, 243);
		scrollPane.getViewport().setBackground(Color.WHITE);
		add(scrollPane);

		arrCustomer = ctmBUS.getAllCustomer();
		showListCustomer();

	}

	// Sự kiện click chuột trong table
	private void tableCustomerMouseClicked(MouseEvent e) {
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(true);
		btnEdit.setEnabled(true);

		tfID.setEnabled(false);

		int i = table.getSelectedRow();
		if (i >= 0) {
			tfID.setText(modelCustomer.getValueAt(i, 0).toString());
			tfName.setText(modelCustomer.getValueAt(i, 1).toString());
			String gendertmp = modelCustomer.getValueAt(i, 2).toString();
			if ("NAM".equals(gendertmp)) {
				isGenderSelected = true;
				rdbtnNam.setSelected(true);
			} else if ("NỮ".equals(gendertmp)) {
				isGenderSelected = true;
				rdbtnNu.setSelected(true);
			}
			tfPhone.setText(modelCustomer.getValueAt(i, 3).toString());
			tfEmail.setText(modelCustomer.getValueAt(i, 4).toString());
			tfCCCD.setText(modelCustomer.getValueAt(i, 5).toString());

		}
	}

	// Mã tự tăng
	private void autoSetID() {
		int maxID = 0;
		for (customerDTO ctm : arrCustomer) {
			if (ctm.getCustomer_id() > maxID) {
				maxID = ctm.getCustomer_id();
			}
		}
		int newID = maxID + 1;
		tfID.setText(String.valueOf(newID));
		tfID.setEnabled(false);
	}

	private void findCustomer() {
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

	// Viết hàm xử lí sự kiện cho nút AddCustomer
	private void addCustomerActionPerformed(ActionEvent e) {
		try {
			// Kiểm tra các trường không đuọc để trống
			if (tfID.getText().trim().equals("") ||
					tfName.getText().trim().equals("") ||
					!isGenderSelected ||
					tfPhone.getText().trim().equals("") ||
					tfEmail.getText().trim().equals("") ||
					tfCCCD.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
			} else if (!chkBUS.isValidPhone(tfPhone.getText())) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
			} else if (!chkBUS.isValidCCCD(tfCCCD.getText())) {
				JOptionPane.showMessageDialog(this, "CCCD không hợp lệ!");
			} else if (!chkBUS.isValidEmail(tfEmail.getText())) {
				JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
			} else {
				customerDTO ctm = new customerDTO();
				ctm.setCustomer_id(Integer.parseInt(tfID.getText()));
				ctm.setCustomer_name(tfName.getText());
				ctm.setCustomer_gender(gender);
				ctm.setCustomer_phone(tfPhone.getText());
				ctm.setCustomer_email(tfEmail.getText());
				ctm.setCustomer_cccd(tfCCCD.getText());

				String message = ctmBUS.addCustomer(ctm);

				JOptionPane.showMessageDialog(this, message);

				if (message.equals("Thêm khách hàng thành công")) {
					arrCustomer.add(ctm);// Thêm vào list cũ
					showListCustomer(); // Nếu thêm thành công, cập nhật lại dữ liệu trên bảng
					btnResetActionPerformed(e);
				}
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	// Xử lí sự kiện cho nút deleteCustomer
	private void deleteCustomerActionPerformed(ActionEvent e) {
		int i = table.getSelectedRow();
		if (i >= 0) {
			int customerId = (int) modelCustomer.getValueAt(i, 0);

			String message = ctmBUS.deleteCustomer(customerId);

			JOptionPane.showMessageDialog(this, message);

			if (message.equals("Đã xóa khách hàng")) {
				modelCustomer.removeRow(i);
				arrCustomer.remove(i);
			}
			btnResetActionPerformed(e);
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để xóa!");
		}
	}

	// Xử lí sự kiện cho nút editCustomer
	private void editCustomerActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					tfName.getText().trim().equals("") ||
					!isGenderSelected ||
					tfPhone.getText().trim().equals("") ||
					tfEmail.getText().trim().equals("") ||
					tfCCCD.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
			} else if (!chkBUS.isValidPhone(tfPhone.getText())) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
			} else if (!chkBUS.isValidCCCD(tfCCCD.getText())) {
				JOptionPane.showMessageDialog(this, "CCCD không hợp lệ!");
			} else if (!chkBUS.isValidEmail(tfEmail.getText())) {
				JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
			} else {
				int i = table.getSelectedRow();

				if (i >= 0) {
					if (rdbtnNam.isSelected()) {
						gender = "NAM";
					} else if (rdbtnNu.isSelected()) {
						gender = "NỮ";
					} else {
						JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
						return;
					}

					// Tạo đối tượng customerDTO mới với thông tin đã được chỉnh sửa
					customerDTO ctm = new customerDTO();

					ctm.setCustomer_id((int) modelCustomer.getValueAt(i, 0));// Không set lại id

					ctm.setCustomer_name(tfName.getText());
					ctm.setCustomer_gender(gender);
					ctm.setCustomer_phone(tfPhone.getText());
					ctm.setCustomer_email(tfEmail.getText());
					ctm.setCustomer_cccd(tfCCCD.getText());

					String message = ctmBUS.editCustomer(ctm);

					JOptionPane.showMessageDialog(this, message);

					// Nếu chỉnh sửa thành công, cập nhật lại dữ liệu trong bảng
					if (message.equals("Chỉnh sửa thông tin khách hàng thành công")) {
						// Cập nhật lại lên table
						modelCustomer.setValueAt(ctm.getCustomer_name(), i, 1);
						modelCustomer.setValueAt(ctm.getCustomer_gender(), i, 2);
						modelCustomer.setValueAt(ctm.getCustomer_phone(), i, 3);
						modelCustomer.setValueAt(ctm.getCustomer_email(), i, 4);
						modelCustomer.setValueAt(ctm.getCustomer_cccd(), i, 5);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để chỉnh sửa!");
				}

			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	// Sự kiện nút reset
	private void btnResetActionPerformed(ActionEvent e) {
		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);

		autoSetID();

		showListCustomer();

		tfName.setText("");
		genderGroup.clearSelection();
		tfPhone.setText("");
		tfEmail.setText("");
		tfCCCD.setText("");

	}

}
