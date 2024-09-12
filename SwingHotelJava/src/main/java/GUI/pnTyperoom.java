package GUI;

import java.awt.Color;
import DTO.typeRoomDTO;
import BUS.typeRoomBUS;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class pnTyperoom extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfFind;
	private JTextField tfNameTypeRoom;
	private JTextField tfFeeTypeRoom;
	private DefaultTableModel modelTypeRoom = new DefaultTableModel();

	private ArrayList<typeRoomDTO> arrTypeRoom;

	private typeRoomBUS trBUS = new typeRoomBUS();

	private JTable table;
	private JTextField tfIDTypeRoom;
	private JTextPane tpInfoTypeRoom;
	private JComboBox<String> cbStatus;
	private JButton btnAdd, btnDelete, btnEdit, btnReset;

	/**
	 * Create the panel.
	 */
	public pnTyperoom() {
		initComponents();
		updateInit();
		loadTypeRoomList();
		autosetID();

	}

	// Chỉnh sửa thêm giao diện
	private void updateInit() {
		tfIDTypeRoom.setEnabled(true);
		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnReset.setEnabled(true);
	}

	// Hiểm thị các thành phần giao diện
	private void initComponents() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);

		JLabel lblQunLLoi = new JLabel("  Quản lý loại phòng");
		lblQunLLoi.setIcon(new ImageIcon(pnTyperoom.class.getResource("/Picture/bedroom (1).png")));
		lblQunLLoi.setForeground(new Color(0, 0, 128));
		lblQunLLoi.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblQunLLoi.setBounds(368, 10, 321, 50);
		add(lblQunLLoi);

		JLabel lblNewLabel_1_6 = new JLabel("Tìm kiếm");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(292, 339, 79, 33);
		add(lblNewLabel_1_6);

		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBorder(new LineBorder(Color.lightGray, 1));
		tfFind.setBounds(380, 341, 349, 33);
		add(tfFind);

		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findTyperoom();
			}

			public void removeUpdate(DocumentEvent e) {
				findTyperoom();
			}

			public void changedUpdate(DocumentEvent e) {
				findTyperoom();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Tên loại phòng");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(22, 472, 112, 33);
		add(lblNewLabel_1);

		tfNameTypeRoom = new JTextField();
		tfNameTypeRoom.setColumns(10);
		tfNameTypeRoom.setBorder(new LineBorder(Color.lightGray, 1));
		tfNameTypeRoom.setBounds(144, 474, 227, 33);
		add(tfNameTypeRoom);

		JLabel lblNewLabel_1_1 = new JLabel("Đơn giá(USD)");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(22, 531, 112, 33);
		add(lblNewLabel_1_1);

		tfFeeTypeRoom = new JTextField();
		tfFeeTypeRoom.setColumns(10);
		tfFeeTypeRoom.setBorder(new LineBorder(Color.lightGray, 1));
		tfFeeTypeRoom.setBounds(144, 533, 227, 33);
		add(tfFeeTypeRoom);

		JLabel lblNewLabel_1_2 = new JLabel("Mô tả");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(497, 472, 41, 33);
		add(lblNewLabel_1_2);

		tpInfoTypeRoom = new JTextPane();
		tpInfoTypeRoom.setBackground(new Color(255, 240, 245));
		tpInfoTypeRoom.setBounds(548, 478, 285, 88);
		add(tpInfoTypeRoom);

		JLabel lblNewLabel_1_11 = new JLabel("Trạng thái");
		lblNewLabel_1_11.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_11.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_11.setBounds(467, 417, 79, 33);
		add(lblNewLabel_1_11);

		tfIDTypeRoom = new JTextField();
		tfIDTypeRoom.setColumns(10);
		tfIDTypeRoom.setBorder(new LineBorder(Color.lightGray, 1));
		tfIDTypeRoom.setBounds(144, 417, 227, 33);
		add(tfIDTypeRoom);

		JLabel lblNewLabel_1_1_1 = new JLabel("ID");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(22, 417, 112, 33);
		add(lblNewLabel_1_1_1);

		cbStatus = new JComboBox<>();
		cbStatus.setBackground(new Color(255, 255, 255));
		cbStatus.setBounds(548, 425, 145, 21);
		cbStatus.addItem("Hoạt động");
		cbStatus.addItem("Không hoạt động");
		add(cbStatus);

		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBounds(957, 374, 102, 33);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddAtionPerformed(e);
			}
		});

		btnDelete = new JButton("Xóa");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(0, 206, 209));
		btnDelete.setBounds(957, 425, 102, 33);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnDeleteActionPerformed(e);
			}
		});

		btnEdit = new JButton("Sửa");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(0, 206, 209));
		btnEdit.setBounds(957, 478, 102, 33);
		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnEditActionPerformed(e);
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
		btnReset.setBounds(957, 531, 102, 33);
		add(btnReset);

	}

	// UpdateList
	private void showListTypeRoom() {
		modelTypeRoom.setRowCount(0);
		for (typeRoomDTO tr : arrTypeRoom) {
			modelTypeRoom.addRow(new Object[] {
					tr.getTypeRoom_id(),
					tr.getTypeRoom_name(),
					tr.getTypeRoom_fee(),
					tr.getTypeRoom_info(),
					tr.getTypeRoom_status()
			});
		}
	}

	// Hiểm thị list loại phòng lên table
	private void loadTypeRoomList() {

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableTypeRoomMouseClicked(e);
			}
		});

		table.setModel(modelTypeRoom);
		modelTypeRoom.addColumn("ID");
		modelTypeRoom.addColumn("Tên loại phòng");
		modelTypeRoom.addColumn("Đơn giá");
		modelTypeRoom.addColumn("Mô tả");
		modelTypeRoom.addColumn("Trạng thái");

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 80, 1037, 233);
		scrollPane.getViewport().setBackground(Color.white);
		add(scrollPane);

		arrTypeRoom = trBUS.getAllTypeRooms();
		showListTypeRoom();
	}

	// Xử lý sự kiện click chuột trong table
	private void tableTypeRoomMouseClicked(MouseEvent e) {
		btnAdd.setEnabled(false);
		btnDelete.setEnabled(true);
		btnEdit.setEnabled(true);

		tfIDTypeRoom.setEnabled(false);

		int i = table.getSelectedRow();
		if (i >= 0) {
			tfIDTypeRoom.setText(modelTypeRoom.getValueAt(i, 0).toString());
			tfNameTypeRoom.setText(modelTypeRoom.getValueAt(i, 1).toString());
			tfFeeTypeRoom.setText(modelTypeRoom.getValueAt(i, 2).toString());
			tpInfoTypeRoom.setText(modelTypeRoom.getValueAt(i, 3).toString());
			cbStatus.setSelectedItem(modelTypeRoom.getValueAt(i, 4).toString());

		}
	}

	// Xử lý mã tự tăng
	private void autosetID() {
		int maxID = 0;
		for (typeRoomDTO tr : arrTypeRoom) {
			if (tr.getTypeRoom_id() > maxID) {
				maxID = tr.getTypeRoom_id();
			}
		}
		int newID = maxID + 1;
		tfIDTypeRoom.setText(String.valueOf(newID));
		tfIDTypeRoom.setEnabled(false);
	}

	private void findTyperoom() {
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

	// Xử lý sự kiện cho nút thêm
	private void btnAddAtionPerformed(ActionEvent e) {
		try {
			// Kiểm tra các trường không được rỗng
			if (tfIDTypeRoom.getText().equals("") ||
					tfNameTypeRoom.getText().equals("") ||
					tfFeeTypeRoom.getText().equals("") ||
					cbStatus.getSelectedItem().toString().equals("") ||
					tpInfoTypeRoom.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng điền đủ thông tin");
			} else {
				typeRoomDTO tr = new typeRoomDTO();

				tr.setTypeRoom_id(Integer.parseInt(tfIDTypeRoom.getText()));
				tr.setTypeRoom_name(tfNameTypeRoom.getText());
				tr.setTypeRoom_fee(Double.parseDouble(tfFeeTypeRoom.getText()));
				tr.setTypeRoom_status(cbStatus.getSelectedItem().toString());
				tr.setTypeRoom_info(tpInfoTypeRoom.getText());

				String message = trBUS.addTypeRoom(tr);

				JOptionPane.showMessageDialog(this, message);

				if (message.equals("Thêm loại phòng thành công")) {
					arrTypeRoom.add(tr);
					showListTypeRoom();
					btnResetActionPerformed(e);
				}
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	// Xử lí sự kiện cho nút xóa
	private void btnDeleteActionPerformed(ActionEvent e) {
		int i = table.getSelectedRow();
		if (i >= 0) {
			int typeRoomId = (int) modelTypeRoom.getValueAt(i, 0);

			String message = trBUS.deleteTypeRoom(typeRoomId);

			JOptionPane.showMessageDialog(this, message);

			if (message.equals("Xóa loại phòng thành công")) {
				modelTypeRoom.removeRow(i);
				arrTypeRoom.remove(i);
			}
			btnResetActionPerformed(e);

		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn loại phòng để xóa");
		}
	}

	// Xử lý sự kiện cho nút chỉnh sửa
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			if (tfIDTypeRoom.getText().equals("") ||
					tfNameTypeRoom.getText().equals("") ||
					tfFeeTypeRoom.getText().equals("") ||
					cbStatus.getSelectedItem().toString().equals("") ||
					tpInfoTypeRoom.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng điền đủ thông tin");
			} else {
				int i = table.getSelectedRow();
				if (i >= 0) {
					// Tạo đối tượng mới lưu trữ thông tin
					typeRoomDTO tr = new typeRoomDTO();

					tr.setTypeRoom_id((int) modelTypeRoom.getValueAt(i, 0));// Không set lại id

					tr.setTypeRoom_name(tfNameTypeRoom.getText());
					tr.setTypeRoom_fee(Double.parseDouble(tfFeeTypeRoom.getText()));
					tr.setTypeRoom_status(cbStatus.getSelectedItem().toString());
					tr.setTypeRoom_info(tpInfoTypeRoom.getText());

					String message = trBUS.editTypeRoom(tr);

					JOptionPane.showMessageDialog(this, message);
					// Cập nhật lại trên bảng
					if (message.equals("Chỉnh sửa loại phòng thành công")) {
						modelTypeRoom.setValueAt(tr.getTypeRoom_name(), i, 1);
						modelTypeRoom.setValueAt(tr.getTypeRoom_fee(), i, 2);
						modelTypeRoom.setValueAt(tr.getTypeRoom_info(), i, 3);
						modelTypeRoom.setValueAt(tr.getTypeRoom_status(), i, 4);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn loại phòng để xóa");
				}
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	// Xử lí sự kiện cho nút Reset
	private void btnResetActionPerformed(ActionEvent e) {
		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);

		showListTypeRoom();
		autosetID();

		tfNameTypeRoom.setText("");
		tfFeeTypeRoom.setText("");
		cbStatus.setSelectedItem(0);
		tpInfoTypeRoom.setText("");

	}

}
