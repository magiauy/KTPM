package GUI;

import DAO.typeRoomDAO;
import DTO.typeRoomDTO;
import DAO.roomDAO;
import BUS.typeRoomBUS;
import BUS.roomBUS;
import DTO.roomDTO;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.HashMap;

public class pnRoom extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNameRoom;
	private JTextField tfFind;
	private DefaultTableModel modelRoom = new DefaultTableModel();

	private typeRoomBUS trBUS = new typeRoomBUS();

	private JTable table;
	private JComboBox cbStatusRoom;
	private JComboBox cbTypeRoom;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnReset;

	private ArrayList<roomDTO> arrRoom;
	private ArrayList<roomDTO> arrRoomActive;
	private roomBUS rBUS = new roomBUS();

	private HashMap<Integer, String> typeRoomMap = new HashMap<>();// Tao HAshMap de luu tru thong tin id va ten_loaiphg
	private JTextField tfID;

	/**
	 * Create the panel.
	 */
	public pnRoom() {
		initComponent();
		updateInit();
		loadRoomList();
		autotfID();
		loadDataToCbTypeRoom();

	}

	// Chỉnh sửa giao diện
	private void updateInit() {
		tfID.setEnabled(true);

		btnAdd.setEnabled(true);
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnReset.setEnabled(true);
	}

	// Tải giao diện
	private void initComponent() {
		rBUS.loaddata();
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);

		JLabel lblQunLPhng = new JLabel("Quản lý phòng");
		lblQunLPhng.setForeground(new Color(0, 0, 128));
		lblQunLPhng.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblQunLPhng.setBounds(430, 10, 253, 33);
		add(lblQunLPhng);
		tfNameRoom = new JTextField();
		tfNameRoom.setColumns(10);
		tfNameRoom.setBorder(new LineBorder(Color.lightGray, 1));
		tfNameRoom.setBounds(208, 431, 227, 33);
		add(tfNameRoom);

		tfFind = new JTextField();
		tfFind.setColumns(10);
		tfFind.setBorder(new LineBorder(Color.lightGray, 1));
		tfFind.setBounds(350, 310, 349, 33);
		add(tfFind);

		tfFind.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				findRoom();
			}

			public void removeUpdate(DocumentEvent e) {
				findRoom();
			}

			public void changedUpdate(DocumentEvent e) {
				findRoom();
			}
		});

		tfID = new JTextField();
		tfID.setBackground(new Color(255, 255, 255));
		tfID.setColumns(10);
		tfID.setBorder(new LineBorder(Color.lightGray, 1));
		tfID.setBounds(208, 379, 227, 33);
		add(tfID);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(95, 379, 103, 33);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_6 = new JLabel("Tìm kiếm");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_6.setBounds(262, 308, 79, 33);
		add(lblNewLabel_1_6);

		JLabel lblNewLabel_1_1 = new JLabel("ID Loại PHG");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(95, 482, 103, 33);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_11 = new JLabel("Tên phòng");
		lblNewLabel_11.setForeground(new Color(0, 0, 128));
		lblNewLabel_11.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(95, 429, 103, 33);
		add(lblNewLabel_11);

		JLabel lblNewLabel_1_2 = new JLabel("Trạng thái");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(95, 525, 103, 33);
		add(lblNewLabel_1_2);

		btnAdd = new JButton("Thêm");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(0, 206, 209));
		btnAdd.setBounds(960, 379, 102, 33);

		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddActionPerformed(e);
			}
		});

		btnDelete = new JButton("Xóa");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(0, 206, 209));
		btnDelete.setBounds(960, 429, 102, 33);

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
		btnEdit.setBounds(960, 482, 102, 33);

		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnEditActionPerformed(e);
			}
		});

		cbStatusRoom = new JComboBox();
		cbStatusRoom.setBackground(new Color(255, 255, 255));
		cbStatusRoom.setBounds(208, 533, 227, 21);
		add(cbStatusRoom);

		cbTypeRoom = new JComboBox();
		cbTypeRoom.setBackground(new Color(255, 255, 255));
		cbTypeRoom.setBounds(208, 488, 227, 25);
		add(cbTypeRoom);

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
		btnReset.setBounds(960, 533, 102, 33);
		add(btnReset);

	}

	// Update mảng room
	private void UpdateListRoom() {
		arrRoomActive=rBUS.getAllRoomActive();
		modelRoom.setRowCount(0);
		for (roomDTO r : arrRoomActive) {
			modelRoom.addRow(new Object[] {
					r.getRoom_id(),
					r.getRoom_name(),
					r.getTypeRoome_id(),
					r.getType_room_name(),
					r.getRoom_status()
			});
		}

	}

	// Tải danh sách phòng lên bảng
	private void loadRoomList() {
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableRoomMouseClicked(e);
			}
		});

		table.setModel(modelRoom);
		modelRoom.addColumn("ID");
		modelRoom.addColumn("Tên phòng");
		modelRoom.addColumn("ID loại PHG");
		modelRoom.addColumn("Tên loại phòng");
		modelRoom.addColumn("Trạng thái");

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(25, 63, 1037, 233);
		scrollPane.getViewport().setBackground(Color.white);
		add(scrollPane);

		arrRoomActive = rBUS.getAllRoomActive();
		UpdateListRoom();
	}

	// Thêm lựa chọn cho combox loại phòng
	private void loadDataToCbTypeRoom() {
		ArrayList<typeRoomDTO> arrTypeRoom = trBUS.getAllTypeRoomsIdAndName();
		for (typeRoomDTO tr : arrTypeRoom) {
			cbTypeRoom.addItem(tr.getTypeRoom_id() + "-" + tr.getTypeRoom_name());
			// Thêm ánh xạ giữa ID và tên loại phòng vào HashMap
			typeRoomMap.put(tr.getTypeRoom_id(), tr.getTypeRoom_name());
			// Them thong tin giua ID va ten_loiaphg
			typeRoomMap.put(tr.getTypeRoom_id(), tr.getTypeRoom_name());
		}
	}

	// Xư lí sự kiện click chuột trong table
	private void tableRoomMouseClicked(MouseEvent e) {
		btnDelete.setEnabled(true);
		btnEdit.setEnabled(true);
		btnAdd.setEnabled(false);
		tfID.setEnabled(false);
		int i = table.getSelectedRow();
		if (i >= 0) {
			Statusroomcb(modelRoom.getValueAt(i, 4).toString());
			tfID.setText(modelRoom.getValueAt(i, 0).toString());
			tfNameRoom.setText(modelRoom.getValueAt(i, 1).toString());
			cbTypeRoom.setSelectedItem(modelRoom.getValueAt(i, 2).toString() + "-"
					+ typeRoomMap.get(Integer.parseInt(modelRoom.getValueAt(i, 2).toString())));
			cbStatusRoom.setSelectedItem(modelRoom.getValueAt(i, 4));
		}

	}
	public void Statusroomcb(String statusroom){
		if(statusroom.equals("ĐÃ ĐẶT")){
			cbStatusRoom.removeAllItems();
			cbStatusRoom.addItem("ĐÃ ĐẶT");
		}else{
			cbStatusRoom.removeAllItems();
			cbStatusRoom.addItem("TRỐNG");
			cbStatusRoom.addItem("BẢO TRÌ");
		}
	}
	// Set ID trong tfID tăng tự động
	private void autotfID() {
		arrRoom=rBUS.getAllRooms();
		int maxID = 0;
		for (roomDTO r : arrRoom) {
			if (r.getRoom_id() > maxID) {
				maxID = r.getRoom_id(); // Tìm ID lớn nhất trong danh sách
			}
		}
		int newID = maxID + 1; // Tạo ID mới bằng cách tăng thêm 1
		tfID.setText(String.valueOf(newID)); // Set ID mới cho tfID
		tfID.setEnabled(false); // Không cho phép chỉnh sửa tfID
	}

//	Thanh tìm kiếm bảng
	private void findRoom() {
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

	// Xử lí sự kiện nút thêm
	private void btnAddActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					tfNameRoom.getText().trim().equals("") ||
					cbTypeRoom.getSelectedItem().toString().equals("") ||
					cbStatusRoom.getSelectedItem().toString().equals("")) {

				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
			} else {
				roomDTO r = new roomDTO();

				r.setRoom_id(Integer.parseInt(tfID.getText()));
				r.setRoom_name(tfNameRoom.getText());
				r.setTypeRoome_id(Integer.parseInt(cbTypeRoom.getSelectedItem().toString().substring(0, 1)));
				r.setRoom_status(cbStatusRoom.getSelectedItem().toString());

				String message = rBUS.addRoom(r);

				JOptionPane.showMessageDialog(this, message);
				if (message.equals("Thêm phòng thành công")) {
					arrRoom.add(r);
					
					arrRoomActive.add(r);
					UpdateListRoom();
					
					btnResetActionPerformed(e);
				}

			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}

	}

	// Xử lí sự kiện nút xóa
	private void btnDeleteActionPerformed(ActionEvent e) {
		int i = table.getSelectedRow();
		if (i >= 0) {
			int idRoom = (int) modelRoom.getValueAt(i, 0);

			String message = rBUS.deleteRoom(idRoom);

			JOptionPane.showMessageDialog(this, message);

			if (message.equals("Xóa phòng thành công")) {
				modelRoom.removeRow(i);
				arrRoom.remove(i);
				arrRoomActive.remove(i);
			}

			btnResetActionPerformed(e);

		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng để xóa");
		}
	}

	// Xử lí sự kiện nút chỉnh sửa
	private void btnEditActionPerformed(ActionEvent e) {
		try {
			if (tfID.getText().trim().equals("") ||
					tfNameRoom.getText().trim().equals("") ||
					cbTypeRoom.getSelectedItem().toString().equals("") ||
					cbStatusRoom.getSelectedItem().toString().equals("")) {

				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");
			} else {
				int i = table.getSelectedRow();
				if (i >= 0) {
					roomDTO r = new roomDTO();

					r.setRoom_id(Integer.parseInt(modelRoom.getValueAt(i, 0).toString()));

					r.setRoom_name(tfNameRoom.getText());
					r.setTypeRoome_id(Integer.parseInt(cbTypeRoom.getSelectedItem().toString().substring(0, 1)));
					r.setRoom_status(cbStatusRoom.getSelectedItem().toString());

					String message = rBUS.editRoom(r);

					JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);

					if (message.equals("Chỉnh sửa thông tin phòng thành công")) {
						modelRoom.setValueAt(r.getRoom_name(), i, 1);
						modelRoom.setValueAt(r.getTypeRoome_id(), i, 2);
						modelRoom.setValueAt(r.getRoom_status(), i, 4);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng để sửa");
				}
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}

	// Xử lý sự kiện cho nút reset
	private void btnResetActionPerformed(ActionEvent e) {
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		btnAdd.setEnabled(true);
		autotfID();

		UpdateListRoom();

		tfNameRoom.setText("");
		cbTypeRoom.setSelectedItem(0);
		cbStatusRoom.setSelectedItem(0);
		
	}
}
