package GUI;

import javax.swing.JPanel;
import DTO.accountDTO;
import BUS.accountBUS;
import DTO.staffDTO;
import BUS.staffBUS;
import BUS.checkInputBUS;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.util.ArrayList;
import javax.swing.border.MatteBorder;

public class pnProfile extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfCCCD;
	private JButton btnUpdate;
	
	private boolean isSelectedGender=false;
	private boolean isSelectedPosition=false;
	private String gender;
	private int position;
	
	private JRadioButton rdbtnNam, rdbtnNu;
	private JRadioButton rdbtnStaff, rdbtnAdmin;
	
	private ButtonGroup btngrGender;
	private ButtonGroup btngrPosition ;
	
	private frmLogin frmlogin = new frmLogin();
	private accountBUS accountBUS= new accountBUS();
	private accountDTO account=frmLogin.account;
	private checkInputBUS chkBUS= new checkInputBUS();
	private staffBUS staffBUS= new staffBUS();
	
	private frmHomepage frmHome= new frmHomepage(account);
	
	private ArrayList<staffDTO> arrStaff=staffBUS.getAllStaff();

	/**
	 * Create the panel.
	 */
	public pnProfile() {
		initComponent();
		showInfo();
	}
//	Giao diện
	private void initComponent() {
		setBackground(new Color(255, 255, 255));
		setBounds(222, 44, 1089, 590);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin cá nhân");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setBounds(393, 10, 261, 46);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(44, 105, 122, 31);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Họ tên");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(44, 183, 95, 31);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CCCD");
		lblNewLabel_4.setForeground(new Color(0, 0, 128));
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(491, 94,95, 31);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Số điện thoại");
		lblNewLabel_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(44, 262, 122, 31);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Giới tính");
		lblNewLabel_6.setForeground(new Color(0, 0, 128));
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(491, 183,95, 31);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setForeground(new Color(0, 0, 128));
		lblNewLabel_7.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(44, 339, 95, 31);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Chức vụ");
		lblNewLabel_8.setForeground(new Color(0, 0, 128));
		lblNewLabel_8.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(491, 262, 95, 31);
		add(lblNewLabel_8);
//	BTN CẬP NHẬT
		btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnUpdateActionPerformed(e);
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(0, 206, 209));
		btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnUpdate.setBounds(864, 511, 146, 56);
		btnUpdate.setBorderPainted(false);
		add(btnUpdate);
		
		tfID = new JTextField();
		tfID.setBackground(new Color(255, 255, 255));
		tfID.setBounds(44, 135, 95, 31);
		tfID.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(102, 205, 170)));//Chỉnh màu lineBorder
		add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(44, 211, 271, 31);
		tfName.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(102, 205, 170)));//Chỉnh màu lineBorder
		add(tfName);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(44, 287, 271, 31);
		tfPhone.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(102, 205, 170)));//Chỉnh màu lineBorder
		add(tfPhone);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(44, 369, 271, 31);
		tfEmail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(102, 205, 170)));//Chỉnh màu lineBorder
		add(tfEmail);
		
		tfCCCD = new JTextField();
		tfCCCD.setColumns(10);
		tfCCCD.setBounds(491, 129, 271, 31);
		tfCCCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(102, 205, 170)));//Chỉnh màu lineBorder
		add(tfCCCD);
		
		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBackground(new Color(255, 255, 255));
		rdbtnNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnNam.setBounds(501, 220, 103, 21);
		add(rdbtnNam);
		
		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(new Color(255, 255, 255));
		rdbtnNu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnNu.setBounds(623, 221, 103, 21);
		add(rdbtnNu);
		
//		BUTTON GROUP CHO GIỚI TÍNH
		btngrGender = new ButtonGroup();
		btngrGender.add(rdbtnNam);
		btngrGender.add(rdbtnNu);
		
		rdbtnStaff = new JRadioButton("Nhân viên");
		rdbtnStaff.setBackground(new Color(255, 255, 255));
		rdbtnStaff.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnStaff.setBounds(501, 297, 103, 21);
		add(rdbtnStaff);
		
		rdbtnAdmin = new JRadioButton("Quản lý");
		rdbtnAdmin.setBackground(new Color(255, 255, 255));
		rdbtnAdmin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnAdmin.setBounds(623, 297, 103, 21);
		add(rdbtnAdmin);

//		BUTTON GROUP CHO CHỨC VỤ
		btngrPosition = new ButtonGroup();//Position(n): chức vụ
		btngrPosition.add(rdbtnStaff);
		btngrPosition.add(rdbtnAdmin);

	}
//	Hiển thị thông tin cá nhân
	private void showInfo() {
		if(account!=null) {
			tfID.setText(String.valueOf(account.getAccount_NVID()));
			tfID.setEnabled(false);
			tfName.setText(account.getAccount_name());
			tfEmail.setText(account.getAccount_email());
			tfPhone.setText(account.getAccount_phone());
			tfCCCD.setText(account.getAccount_cccd());
			
			if("NAM".equals(account.getAccount_gender())) {
				isSelectedGender=true;
				rdbtnNam.setSelected(true);
			}
			else if("NỮ".equals(account.getAccount_gender())) {
				isSelectedGender=true;
				rdbtnNu.setSelected(true);
			}
			
			if(1==(account.getAccount_id_position())) {
				isSelectedPosition=true;
				rdbtnAdmin.setSelected(true);
			}
			else if(2==(account.getAccount_id_position())) {
				isSelectedPosition=true;
				rdbtnStaff.setSelected(true);
				rdbtnStaff.setEnabled(false);
				rdbtnAdmin.setEnabled(false);
			}
			
		}
	}
//	Xử lý sự kiện nút Cập nhật
	private void btnUpdateActionPerformed(ActionEvent e) {
		try {
			if(tfName.getText().trim().equals("")||
					tfEmail.getText().trim().equals("")||
					tfPhone.getText().trim().equals("")||
					tfCCCD.getText().trim().equals("")||
					!isSelectedGender||
					!isSelectedPosition) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin");	
			}
			else if(!chkBUS.isValidCCCD(tfCCCD.getText())) {
				JOptionPane.showMessageDialog(this, "CCCD không hợp lệ");
			}
			else if(!chkBUS.isValidEmail(tfEmail.getText())) {
				JOptionPane.showMessageDialog(this, "Email không hợp lệ");
			}
			else if(!chkBUS.isValidPhone(tfPhone.getText())) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
			}
			else {
				 if (rdbtnNam.isSelected()) {
	                    gender = "NAM";
	                } else if (rdbtnNu.isSelected()) {
	                    gender = "NỮ";
	                } else {
	                    JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
	                    return;
	                }
	                
	                if (rdbtnAdmin.isSelected()) {
	                    position=1;
	                } else if (rdbtnStaff.isSelected()) {
	                    position=2;
	                } else {
	                    JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ");
	                    return;
	                }
	                
				staffDTO staff= new staffDTO();
				
				staff.setStaff_id(Integer.parseInt(tfID.getText()));
				staff.setStaff_name(tfName.getText());
				staff.setStaff_email(tfEmail.getText());
				staff.setStaff_phone(tfPhone.getText());
				staff.setStaff_CCCD(tfCCCD.getText());
				staff.setStaff_phai(gender);
				staff.setStaff_id_chucvu(position);

				String message=staffBUS.editStaff(staff);
				
				JOptionPane.showMessageDialog(this, message);

	            // Nếu chỉnh sửa thành công, cập nhật lại dữ liệu 
	            if (message.equals("Chỉnh sửa thông tin nhân viên thành công")) {
	            	account.setAccount_name(tfName.getText());
	                account.setAccount_email(tfEmail.getText());
	                account.setAccount_phone(tfPhone.getText());
	                account.setAccount_cccd(tfCCCD.getText());
	                account.setAccount_gender(gender);
	                account.setAccount_id_position(position);
	                
	                
	            	
	            	
	            	
	                if (account.getAccount_id_position() == 1) {
	                     // Nếu là quản lý
	                     frmHome.lbUserName.setText(account.getAccount_username());
	                } else if (account.getAccount_id_position() == 2) {	
	                     // Nếu là nhân viên
	                     frmHome.lbUserName1.setText(account.getAccount_username());
	                }
	            	 
	            	
	            	 
	            	 showInfo();
	            }
				
			}
			
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Thông tin không hợp lệ");
		}
	}
}
