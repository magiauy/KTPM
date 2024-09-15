package GUI;

import java.awt.Color;
import DTO.accountDTO;
import DTO.roomDTO;
import DTO.typeRoomDTO;
import BUS.accountBUS;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.accountDAO;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import DTO.staffDTO;
import BUS.staffBUS;
import BUS.checkInputBUS;

public class pnAccount extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfID;
	private JTextField tfUsername;
	private JTextField tfFind;
	private JTable table;
	private DefaultTableModel modelAccount = new DefaultTableModel();
	private JPasswordField pfPassword;
	private JPasswordField pfConfirmPassword;
	private JButton btnAdd, btnDelete, btnEdit, btnReset;
	private ArrayList<accountDTO> arrAccount;
	private accountBUS accountBUS = new accountBUS();
	private staffBUS staffBUS = new staffBUS();
	private JLabel lblNewLabel;
	private JComboBox cbIDNV;
	private checkInputBUS chkBUS = new checkInputBUS();
	private HashMap<Integer, String> maNhanVienMap = new HashMap<>();// Tao HAshMap de luu tru thong tin id va ten_nv

	/**
	 * Create the panel.
	 */
	public pnAccount() {
		initComponent();
		updateInit();
		loadTableListAccount();
		loadDataToCbIDNV();
		autoSetID();

	}

	// UPdate init
	private void updateInit() {
		tfID.setEnabled(true);
		cbIDNV.setEnabled(true);

		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnReset.setEnabled(true);
	}

	// Giao diện
	private void initComponent() {
		staffBUS.loaddata();
		setBounds(222, 44, 1089, 590);
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JLabel lblQunLPhng = new JLabel("Quản lý tài khoản");
		lblQunLPhng.setForeground(new Color(0, 0, 128));
		lblQunLPhng.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblQunLPhng.setBounds(415, 10, 253, 33);
		add(lblQunLPhng);

		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setEnabled(true);
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBounds(955, 377, 102, 33);
		add(btnAdd);

		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDelete.setEnabled(false);
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(0, 206, 209));
		btnDelete.setBounds(955, 420, 102, 33);
		add(btnDelete);

		btnEdit = new JButton("Sửa");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditActionPerformed(e);
			}
		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEdit.setEnabled(false);
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(0, 206, 209));
		btnEdit.setBounds(955, 461, 102, 33);
		add(btnEdit);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnResetActionPerformed(e);
			}
		});
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnReset.setEnabled(true);
		btnReset.setBorderPainted(false);
		btnReset.setBackground(new Color(0, 206, 209));
		btnReset.setBounds(955, 504, 102, 33);
		add(btnReset);

		tfID = new JTextField();
		tfID.setEnabled(true);
		tfID.setColumns(10);
		tfID.setBorder(new LineBorder(Color.lightGray, 1));
		tfID.setBackground(Color.WHITE);
		tfID.setBounds(132, 379, 227, 33);
		add(tfID);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(73, 377, 49, 33);
		add(lblNewLabel_1);

		JLabel lblNewLabel_11 = new JLabel("Username");
		lblNewLabel_11.setForeground(new Color(0, 0, 128));
		lblNewLabel_11.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(445, 377, 103, 33);
		add(lblNewLabel_11);

		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBorder(new LineBorder(Color.lightGray, 1));
		tfUsername.setBounds(607, 379, 227, 33);
		add(tfUsername);

		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(445, 432, 103, 33);
		add(lblNewLabel_1_1);

		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBorder(new LineBorder(Color.lightGray, 1));
		tfFind.setBounds(335, 310, 349, 33);
		add(tfFind);

		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findAccount();
			}

			public void removeUpdate(DocumentEvent e) {
				findAccount();
			}

			public void changedUpdate(DocumentEvent e) {
				findAccount();
			}
		});

		pfPassword = new JPasswordField();
		pfPassword.setBounds(607, 437, 227, 27);
		pfPassword.setBorder(new LineBorder(Color.lightGray));
		add(pfPassword);

		pfConfirmPassword = new JPasswordField();
		pfConfirmPassword.setBounds(607, 485, 227, 27);
		pfConfirmPassword.setBorder(new LineBorder(Color.lightGray));
		add(pfConfirmPassword);

		JLabel lblNewLabel_111 = new JLabel("Xác nhận mật khẩu");
		lblNewLabel_111.setForeground(new Color(0, 0, 128));
		lblNewLabel_111.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_111.setBounds(445, 480, 152, 33);
		add(lblNewLabel_111);

		JLabel lblNewLabel_1_6 = new JLabel("Tìm kiếm");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(247, 308, 79, 33);
		add(lblNewLabel_1_6);

		lblNewLabel = new JLabel("ID NV");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(73, 432, 49, 33);
		add(lblNewLabel);

		cbIDNV = new JComboBox();
		cbIDNV.setBackground(new Color(255, 255, 255));
		cbIDNV.setBounds(131, 434, 228, 27);
		add(cbIDNV);

	}

	// Update table account
	private void updateTableAccount() {
		modelAccount.setRowCount(0);
		for (accountDTO account : arrAccount) {
			modelAccount.addRow(new Object[] {
					account.getAccount_id(),
					account.getAccount_id_nv(),
					account.getAccount_username(),
					account.getAccount_password()

			});
		}

	}

	// Hiển thị list account
	private void loadTableListAccount() {
		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clickTableAccount(e);
			}
		});

		table.setModel(modelAccount);

		modelAccount.addColumn("ID Account");
		modelAccount.addColumn("ID Nhân viên");
		modelAccount.addColumn("Username");
		modelAccount.addColumn("Password");

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 67, 1037, 233);
		scrollPane.getViewport().setBackground(Color.white);
		add(scrollPane);

		arrAccount = accountBUS.getAllAccount();
		updateTableAccount();
	}

	// Sự kiện click vào table
	private void clickTableAccount(MouseEvent e) {
		cbIDNV.setEnabled(false);

		btnAdd.setEnabled(false);
		btnDelete.setEnabled(true);
		btnEdit.setEnabled(true);

		int i = table.getSelectedRow();
		if (i >= 0) {
			tfID.setText(modelAccount.getValueAt(i, 0).toString());
			cbIDNV.setSelectedItem(modelAccount.getValueAt(i, 1) + "-"
					+ maNhanVienMap.get(Integer.parseInt(modelAccount.getValueAt(i, 1).toString())));
			tfUsername.setText(modelAccount.getValueAt(i, 2).toString());
			pfPassword.setText(modelAccount.getValueAt(i, 3).toString());
			pfConfirmPassword.setText(modelAccount.getValueAt(i, 3).toString());
			checkstatus((int)modelAccount.getValueAt(i, 1));
			checkadmin((int)modelAccount.getValueAt(i, 1));
		}

	}
	public void checkstatus(int id){
		System.out.println(staffBUS.checkstatus(id));
		if(!staffBUS.checkstatus(id)){

			btnDelete.setEnabled(false);

		}else{
			btnDelete.setEnabled(true);
		}
	}
	public void checkadmin(int id){
		if(id==11){
			btnDelete.setEnabled(false);
			btnEdit.setEnabled(false);
		}else {
			btnDelete.setEnabled(true);
			btnEdit.setEnabled(true);
	}
	}

	// Thêm các lựa chọn cho combobox IDNV
	private void loadDataToCbIDNV() {
		ArrayList<staffDTO> arrStaff = staffBUS.getAllStaffWithIDAndName();
		for (staffDTO staff : arrStaff) {
			cbIDNV.addItem(staff.getStaff_id() + "-" + staff.getStaff_name());
			maNhanVienMap.put(staff.getStaff_id(), staff.getStaff_name());
		}
	}

	// tfID tự tăng
	private void autoSetID() {
		int maxID = 0;
		for (accountDTO account : arrAccount) {
			if (account.getAccount_id() >= maxID) {
				maxID = account.getAccount_id();
			}
		}
		int newID = maxID + 1;
		tfID.setText(String.valueOf(newID));
		tfID.setEnabled(false);
	}

	private void findAccount() {
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

	// Sự kiện nút thêm
	private void btnAddActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					cbIDNV.getSelectedItem().toString().equals("") ||
					tfUsername.getText().equals("") ||
					String.valueOf(pfPassword.getPassword()).equals("") ||
					String.valueOf(pfConfirmPassword.getPassword()).equals("")) {

				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
			} else if (!chkBUS.isValidUsername(tfUsername.getText())) {
				JOptionPane.showMessageDialog(this, "Username không hợp lệ");
			} else if (!chkBUS.isValidPassword(String.valueOf(pfPassword.getPassword()))) {
				JOptionPane.showMessageDialog(this, "Password không hợp lệ");
			} else if (!Arrays.equals(pfPassword.getPassword(), pfConfirmPassword.getPassword())) {
				JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không khớp");
			} else {
				accountDTO account = new accountDTO();

				account.setAccount_id(Integer.parseInt(tfID.getText()));
				account.setAccount_id_nv(Integer.parseInt(cbIDNV.getSelectedItem().toString().substring(0, 1)));
				account.setAccount_username(tfUsername.getText());
				account.setAccount_password(String.valueOf(pfPassword.getPassword()));

				String message = accountBUS.addAccount(account);

				JOptionPane.showMessageDialog(this, message);
				if (message.equals("Thêm tài khoản thành công")) {
					arrAccount.add(account);
					updateTableAccount();
					btnResetActionPerformed(e);

				}

			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}

	}

	// Sự kiện nút xóa
	private void btnDeleteActionPerformed(ActionEvent e) {
		int i = table.getSelectedRow();
		if (i >= 0) {
			int idAccount = (int) modelAccount.getValueAt(i, 0);

			String message = accountBUS.deleteAccount(idAccount);

			JOptionPane.showMessageDialog(this, message);

			if (message.equals("Xóa tài khoản thành công")) {
				modelAccount.removeRow(i);
				arrAccount.remove(i);
				updateTableAccount();

			}
			btnResetActionPerformed(e);
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để xóa");
		}

	}

	// Sự kiện nút chỉnh sửa
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					cbIDNV.getSelectedItem().toString().equals("") ||
					tfUsername.getText().equals("") ||
					pfPassword.getPassword().toString().equals("") ||
					pfConfirmPassword.getPassword().toString().equals("")) {

				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
			} else if (!Arrays.equals(pfPassword.getPassword(), pfConfirmPassword.getPassword())) {
				JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không khớp");
			} else if (!chkBUS.isValidUsername(tfUsername.getText())) {
				JOptionPane.showMessageDialog(this, "Username không hợp lệ");
			} else if (!chkBUS.isValidPassword(String.valueOf(pfPassword.getPassword()))) {
				JOptionPane.showMessageDialog(this, "Password không hợp lệ");
			} else {
				int i = table.getSelectedRow();
				if (i >= 0) {
					accountDTO account = new accountDTO();

					account.setAccount_id(Integer.parseInt(modelAccount.getValueAt(i, 0).toString()));
					account.setAccount_id_nv(Integer.parseInt(cbIDNV.getSelectedItem().toString().substring(0, 1)));
					account.setAccount_username(tfUsername.getText().toString());
					account.setAccount_password(String.valueOf(pfPassword.getPassword()));

					String message = accountBUS.editAccount(account);

					JOptionPane.showMessageDialog(this, message);

					if (message.equals("Chỉnh sửa thông tin tài khoản thành công")) {
						modelAccount.setValueAt(account.getAccount_id(), i, 0);
						modelAccount.setValueAt(account.getAccount_id_nv(), i, 1);
						modelAccount.setValueAt(account.getAccount_username(), i, 2);
						modelAccount.setValueAt(account.getAccount_password(), i, 3);

					}

				} else {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản để chỉnh sửa");
				}
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}

	}

	// Sự kiện nút reset
	private void btnResetActionPerformed(ActionEvent e) {
		cbIDNV.setEnabled(true);

		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);

		autoSetID();

		updateTableAccount();

		cbIDNV.setSelectedItem(0);
		tfUsername.setText("");
		pfPassword.setText("");
		pfConfirmPassword.setText("");

	}
}
