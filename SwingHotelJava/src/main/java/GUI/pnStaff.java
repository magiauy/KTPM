package GUI;

import java.awt.Color;

import BUS.checkInputBUS;
import DTO.staffDTO;
import BUS.staffBUS;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DTO.staffDTO;
import BUS.staffBUS;
import DTO.customerDTO;
import DTO.positionDTO;
import BUS.positionBUS;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class pnStaff extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelStaff = new DefaultTableModel();
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfFind;
	private JTextField tfID;
	private JRadioButton rdbtnNam, rdbtnNu;
	private JRadioButton rdbtnStaff, rdbtnAdmin;
	private JRadioButton rdbtnActive, rdbtnInactive;
	private JButton btnAdd, btnDelete, btnEdit, btnReset;

	private ArrayList<staffDTO> arrStaff;

	private staffBUS staffBUS = new staffBUS();
	private positionBUS positionBUS = new positionBUS();
	private checkInputBUS checkInputBUS = new checkInputBUS();

	private int chucvu;
	private String gender;
	private boolean isSelectGender = false;
	private boolean isSeclectPosition = false;

	private ButtonGroup btngrGender, btngrPosition;
	private JTextField tfCCCD;

	/**
	 * Create the panel.
	 */
	public pnStaff() {
		initComponent();
		updateInit();
		loadListStaffToTable();
		autoSetID();

	}

	// Update giao diện
	private void updateInit() {
		tfID.setEnabled(true);

		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnReset.setEnabled(true);
	}

	// Giao diện
	private void initComponent() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản lý nhân viên");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBackground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setBounds(425, 10, 253, 33);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 441, 79, 33);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Điện thoại");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(20, 503, 79, 33);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(349, 386, 79, 33);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Giới tính");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(349, 503, 79, 33);
		add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Chức vụ");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(663, 386, 79, 33);
		add(lblNewLabel_1_4);

		tfName = new JTextField();
		tfName.setBounds(109, 443, 227, 33);
		tfName.setBorder(new LineBorder(Color.lightGray, 1));// Chỉnh màu lineBorder
		add(tfName);
		tfName.setColumns(10);

		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(109, 505, 227, 33);
		tfPhone.setBorder(new LineBorder(Color.lightGray, 1));// Chỉnh màu lineBorder
		add(tfPhone);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(422, 388, 227, 33);
		tfEmail.setBorder(new LineBorder(Color.lightGray, 1));// Chỉnh màu lineBorder
		add(tfEmail);

		JLabel lblNewLabel_1_6 = new JLabel("Tìm kiếm");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(257, 308, 79, 33);
		add(lblNewLabel_1_6);

		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBounds(345, 310, 349, 33);
		tfFind.setBorder(new LineBorder(Color.lightGray, 1));// Chỉnh màu lineBorder
		add(tfFind);
		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findStaff();
			}

			public void removeUpdate(DocumentEvent e) {
				findStaff();
			}

			public void changedUpdate(DocumentEvent e) {
				findStaff();
			}
		});
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setBounds(955, 367, 102, 33);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBorderPainted(false);
		add(btnAdd);

		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDelete.setBounds(955, 410, 102, 33);
		btnDelete.setBackground(new Color(0, 206, 209));
		btnDelete.setBorderPainted(false);
		add(btnDelete);

		btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditActionPerformed(e);
			}
		});
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEdit.setBounds(955, 453, 102, 33);
		btnEdit.setBackground(new Color(0, 206, 209));
		btnEdit.setBorderPainted(false);
		add(btnEdit);

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
		btnReset.setBounds(955, 503, 102, 33);
		add(btnReset);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setForeground(new Color(0, 128, 128));
		rdbtnNam.setFont(new Font("SansSerif", Font.ITALIC, 13));
		rdbtnNam.setBackground(new Color(255, 255, 255));
		rdbtnNam.setBounds(429, 509, 103, 21);
		add(rdbtnNam);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setForeground(new Color(0, 128, 128));
		rdbtnNu.setFont(new Font("SansSerif", Font.ITALIC, 13));
		rdbtnNu.setBackground(new Color(255, 255, 255));
		rdbtnNu.setBounds(534, 509, 103, 21);
		add(rdbtnNu);

		rdbtnNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "NAM";
				isSelectGender = true;
			}
		});

		rdbtnNu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "NỮ";
				isSelectGender = true;
			}
		});
		

		// ButtonGroup cho Gender
		btngrGender = new ButtonGroup();
		btngrGender.add(rdbtnNam);
		btngrGender.add(rdbtnNu);

		JLabel lblNewLabel_1_7 = new JLabel("Trạng thái");
		lblNewLabel_1_7.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_7.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_7.setBounds(756, 290, 79, 33);
		add(lblNewLabel_1_7);

		rdbtnActive = new JRadioButton("Đang hoạt động");
		rdbtnActive.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnActive.setBackground(new Color(255, 255, 255));
		rdbtnActive.setBounds(756, 320, 120, 21);
		add(rdbtnActive);

		rdbtnInactive = new JRadioButton("Ngừng hoạt động");
		rdbtnInactive.setFont(new Font("SansSerif", Font.PLAIN, 13));
		rdbtnInactive.setBackground(new Color(255, 255, 255));
		rdbtnInactive.setBounds(880, 320, 150, 21);
		add(rdbtnInactive);



		ButtonGroup statusGroup = new ButtonGroup();
		statusGroup.add(rdbtnActive);
		statusGroup.add(rdbtnInactive);

		rdbtnInactive.setEnabled(false);
		rdbtnActive.setEnabled(false);
		rdbtnActive.setSelected(true);

		rdbtnStaff = new JRadioButton("Nhân viên");
		rdbtnStaff.setForeground(new Color(0, 128, 128));
		rdbtnStaff.setFont(new Font("SansSerif", Font.ITALIC, 13));
		rdbtnStaff.setBackground(new Color(255, 255, 255));
		rdbtnStaff.setBounds(735, 392, 103, 21);
		add(rdbtnStaff);

		rdbtnAdmin = new JRadioButton("Quản lý");
		rdbtnAdmin.setForeground(new Color(0, 128, 128));
		rdbtnAdmin.setFont(new Font("SansSerif", Font.ITALIC, 13));
		rdbtnAdmin.setBackground(new Color(255, 255, 255));
		rdbtnAdmin.setBounds(838, 392, 103, 21);
		add(rdbtnAdmin);

		rdbtnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chucvu = 1;
				isSeclectPosition = true;
			}
		});

		rdbtnStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chucvu = 2;
				isSeclectPosition = true;
			}
		});

		// ButtonGroup cho Chức vụ
		btngrPosition = new ButtonGroup();
		btngrPosition.add(rdbtnStaff);
		btngrPosition.add(rdbtnAdmin);

		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBorder(new LineBorder(Color.lightGray, 1));
		tfID.setBounds(109, 386, 227, 33);
		add(tfID);

		JLabel lblNewLabel_1_5 = new JLabel("ID");
		lblNewLabel_1_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_5.setBounds(20, 386, 79, 33);
		add(lblNewLabel_1_5);

		tfCCCD = new JTextField();
		tfCCCD.setColumns(10);
		tfCCCD.setBorder(new LineBorder(Color.lightGray, 1));
		tfCCCD.setBounds(422, 443, 227, 33);
		add(tfCCCD);

		JLabel lblNewLabel_12 = new JLabel("CCCD");
		lblNewLabel_12.setForeground(new Color(0, 0, 128));
		lblNewLabel_12.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(349, 441, 79, 33);
		add(lblNewLabel_12);
		staffBUS.loaddata();
	}

	// UpdateListStaff
	private void updateTableStaff() {
		modelStaff.setRowCount(0);

		for (staffDTO staff : arrStaff) {
			modelStaff.addRow(new Object[] {
					staff.getStaff_id(),
					staff.getStaff_name(),
					staff.getStaff_phone(),
					staff.getStaff_email(),
					staff.getStaff_CCCD(),
					staff.getStaff_phai(),
					staff.getStaff_id_chucvu(),
					staff.getStaff_status()
			});
		}
	}

	// hiển thi list lên table
	private void loadListStaffToTable() {
		table = new JTable();

		table.setModel(modelStaff);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableStaffClicked(e);
			}
		});

		modelStaff.addColumn("ID");
		modelStaff.addColumn("Họ tên");
		modelStaff.addColumn("Số điện thoại");
		modelStaff.addColumn("Email");
		modelStaff.addColumn("CCCD");
		modelStaff.addColumn("Giới tính");
		modelStaff.addColumn("ID Chức vụ");
		modelStaff.addColumn("Trạng thái");
		// modelStaff.addColumn("Password");

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 63, 1037, 233);
		scrollPane.getViewport().setBackground(Color.white);
		add(scrollPane);

		arrStaff = staffBUS.getAllStaff();

		updateTableStaff();

		// Sau khi đã thiết lập model cho bảng và thêm vào JScrollPane
		// Thiết lập kích thước cột
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Tắt chế độ tự điều chỉnh
		// kích thước
		// for (int column = 0; column < table.getColumnCount(); column++) {
		// TableColumn tableColumn = table.getColumnModel().getColumn(column);
		// switch (column) {
		// case 0: // Cột "ID"
		// tableColumn.setPreferredWidth(50);
		// break;
		// case 1: // Cột "Họ tên"
		// tableColumn.setPreferredWidth(200);
		// break;
		// case 2: // Cột "Số điện thoại"
		// tableColumn.setPreferredWidth(200);
		// break;
		// case 3: // Cột "Email"
		// tableColumn.setPreferredWidth(250);
		// break;
		// case 4: // Cột "Giới tính"
		// tableColumn.setPreferredWidth(80);
		// break;
		// case 5: // Cột "Chức vụ"
		// tableColumn.setPreferredWidth(100);
		// break;
		// case 6: // Cột "Password"
		// tableColumn.setPreferredWidth(154);
		// break;
		// // Thêm các cài đặt cho các cột khác nếu cần
		// }
		// }
	}

	// sự kiện click chuột cho bảng Staff
	private void tableStaffClicked(MouseEvent e) {
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(true);
		btnEdit.setEnabled(true);

		int i = table.getSelectedRow();

		if (i >= 0) {
			tfID.setText(modelStaff.getValueAt(i, 0).toString());
			tfName.setText(modelStaff.getValueAt(i, 1).toString());
			tfPhone.setText(modelStaff.getValueAt(i, 2).toString());
			tfEmail.setText(modelStaff.getValueAt(i, 3).toString());
			tfCCCD.setText(modelStaff.getValueAt(i, 4).toString());

			String gendertmp = modelStaff.getValueAt(i, 5).toString();
			if ("NAM".equals(gendertmp)) {
				isSelectGender = true;
				rdbtnNam.setSelected(true);
			} else if ("NỮ".equals(gendertmp)) {
				isSelectGender = true;
				rdbtnNu.setSelected(true);
			}

			if (Integer.parseInt(modelStaff.getValueAt(i, 6).toString()) == 2) {
				isSeclectPosition = true;
				rdbtnStaff.setSelected(true);
			} else if (Integer.parseInt(modelStaff.getValueAt(i, 6).toString()) == 1) {
				isSeclectPosition = true;
				rdbtnAdmin.setSelected(true);
			}

			String status = modelStaff.getValueAt(i, 7).toString();
			if ("HOẠT ĐỘNG".equals(status)) {
				rdbtnActive.setSelected(true);
				rdbtnInactive.setEnabled(false);
				rdbtnActive.setEnabled(true);
			} else if ("NGỪNG HOẠT ĐỘNG".equals(status)) {
				rdbtnInactive.setSelected(true);
				rdbtnActive.setEnabled(true);
				rdbtnInactive.setEnabled(true);
			}
		}
		if (frmLogin.account.getAccount_id_nv() == Integer.parseInt(tfID.getText())) {
			rdbtnStaff.setEnabled(false);
			rdbtnAdmin.setEnabled(false);
		}else {
			rdbtnStaff.setEnabled(true);
			rdbtnAdmin.setEnabled(true);
		}
		checkadmin((int)modelStaff.getValueAt(i, 0));

	}
		public void checkadmin(int id){
		System.out.println(id==11);
		if(id == 11){

			btnDelete.setEnabled(false);
			btnEdit.setEnabled(false);
		}else {
			btnDelete.setEnabled(true);
			btnEdit.setEnabled(true);
		}

	}

	// setID_auto
	private void autoSetID() {
		int maxID = 0;
		for (staffDTO staff : arrStaff) {
			if (staff.getStaff_id() >= maxID) {
				maxID = staff.getStaff_id();
			}
		}
		int newID = maxID + 1;
		tfID.setText(String.valueOf(newID));
		tfID.setEnabled(false);
	}

	// Sự kiện nút thêm
	private void btnAddActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					tfName.getText().trim().equals("") ||
					tfPhone.getText().trim().equals("") ||
					tfEmail.getText().trim().equals("") ||
					tfCCCD.getText().trim().equals("") ||
					!isSelectGender ||
					!isSeclectPosition) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
			} else if (!checkInputBUS.isValidPhone(tfPhone.getText())) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
			} else if (!checkInputBUS.isValidCCCD(tfCCCD.getText())) {
				JOptionPane.showMessageDialog(this, "CCCD không hợp lệ!");
			} else if (!checkInputBUS.isValidEmail(tfEmail.getText())) {
				JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
			} else {
				staffDTO staff = new staffDTO();

				staff.setStaff_id(Integer.parseInt(tfID.getText()));
				staff.setStaff_name(tfName.getText());
				staff.setStaff_email(tfEmail.getText());
				staff.setStaff_phone(tfPhone.getText());
				staff.setStaff_CCCD(tfCCCD.getText());
				staff.setStaff_status("HOẠT ĐỘNG");

				staff.setStaff_phai(gender);

				staff.setStaff_id_chucvu(chucvu);

				String message = staffBUS.addStaff(staff);

				JOptionPane.showMessageDialog(this, message);

				if (message.equals("Thêm nhân viên thành công")) {
					arrStaff.add(staff);

					updateTableStaff();

					btnResetActionPerformed(e);

				}
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}

	}

//	Tìm kiếm
	private void findStaff() {
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

	// Sự kiện nút xóa
	private void btnDeleteActionPerformed(ActionEvent e) {
		int i = table.getSelectedRow();
		if (i >= 0) {
			int idStaff = (int) modelStaff.getValueAt(i, 0);

			String message = staffBUS.deleteStaff(idStaff);

			JOptionPane.showMessageDialog(this, message);

			if (message.equals("Xóa nhân viên thành công")) {
				arrStaff.get(i).setStaff_status("NGỪNG HOẠT ĐỘNG");
				updateTableStaff();
			}
			btnResetActionPerformed(e);
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên để xóa");
		}

	}

	// Sự kiện nút chỉnh sửa
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					tfName.getText().trim().equals("") ||
					tfPhone.getText().trim().equals("") ||
					tfEmail.getText().trim().equals("") ||
					tfCCCD.getText().trim().equals("") ||
					!isSelectGender ||
					!isSeclectPosition) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
			} else if (!checkInputBUS.isValidPhone(tfPhone.getText())) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!");
			} else if (!checkInputBUS.isValidCCCD(tfCCCD.getText())) {
				JOptionPane.showMessageDialog(this, "CCCD không hợp lệ!");
			} else if (!checkInputBUS.isValidEmail(tfEmail.getText())) {
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

					if (rdbtnAdmin.isSelected()) {
						chucvu = 1;
					} else if (rdbtnStaff.isSelected()) {
						chucvu = 2;
					} else {
						JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ");
						return;
					}
					String status ;
					if (rdbtnActive.isSelected()) {
						status = "HOẠT ĐỘNG";
					} else if (rdbtnInactive.isSelected()) {
						status = "NGỪNG HOẠT ĐỘNG";
					} else {
						JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái");
						return;
					}

					// Tạo đối tượng customerDTO mới với thông tin đã được chỉnh sửa
					staffDTO staff = new staffDTO();

					staff.setStaff_id((int) modelStaff.getValueAt(i, 0));

					staff.setStaff_name(tfName.getText());
					staff.setStaff_email(tfEmail.getText());
					staff.setStaff_phone(tfPhone.getText());
					staff.setStaff_CCCD(tfCCCD.getText());
					staff.setStaff_phai(gender);
					staff.setStaff_id_chucvu(chucvu);
					staff.setStaff_status(status);

					String message = staffBUS.editStaff(staff);

					JOptionPane.showMessageDialog(this, message);

					// Nếu chỉnh sửa thành công, cập nhật lại dữ liệu trong bảng
					if (message.equals("Chỉnh sửa thông tin nhân viên thành công")) {
						// Cập nhật lại lên table

						modelStaff.setValueAt(staff.getStaff_name(), i, 1);
						modelStaff.setValueAt(staff.getStaff_email(), i, 2);
						modelStaff.setValueAt(staff.getStaff_phone(), i, 3);
						modelStaff.setValueAt(staff.getStaff_CCCD(), i, 4);
						modelStaff.setValueAt(staff.getStaff_phai(), i, 5);
						modelStaff.setValueAt(staff.getStaff_id_chucvu(), i, 6);
						modelStaff.setValueAt(staff.getStaff_status(), i, 7);

					}
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

		updateTableStaff();

		tfName.setText("");
		tfPhone.setText("");
		tfEmail.setText("");
		tfCCCD.setText("");

		btngrGender.clearSelection();
		btngrPosition.clearSelection();

		rdbtnActive.setSelected(true);
		rdbtnInactive.setEnabled(false);
		rdbtnActive.setEnabled(false);
	}


}
