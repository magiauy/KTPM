package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmRegistration {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JPasswordField pfPassword;
	private JPasswordField pfConfirmPassword;
	private JTextField tfCCCD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRegistration window = new frmRegistration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Lấy JFrame của frmRegistration
    public JFrame getFrameRegis() {
        return frame;
    }
    /**
	 * Create the application.
	 */
	public frmRegistration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 447, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 240, 245));
		panel.setBounds(26, 20, 381, 441);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create an account");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		lblNewLabel.setBounds(93, 10, 196, 34);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 66, 85, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Điện thoại");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(20, 112, 85, 24);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(20, 146, 95, 34);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Giới tính");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(20, 190, 75, 22);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Địa chỉ");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(20, 222, 75, 22);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mật khẩu");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(20, 288, 75, 27);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Xác nhận");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(20, 325, 75, 24);
		panel.add(lblNewLabel_7);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin loginFrame= new frmLogin();
				loginFrame.getFrameLogin().setVisible(true);
				frame.dispose();
			}
		});
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCancel.setBounds(117, 377, 111, 34);
		btnCancel.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel.add(btnCancel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(0, 0, 0));
		btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnRegister.setBounds(247, 377, 111, 34);
		btnRegister.setBorderPainted(false);//Bỏ viền btn
		panel.add(btnRegister);
		
		tfName = new JTextField();
		tfName.setBounds(102, 69, 256, 24);
		tfName.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel.add(tfName);
		tfName.setColumns(10);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(102, 115, 256, 24);
		tfPhone.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel.add(tfPhone);
		tfPhone.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(102, 154, 256, 24);
		tfEmail.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel.add(tfEmail);
		tfEmail.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(102, 224, 256, 24);
		tfAddress.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel.add(tfAddress);
		tfAddress.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(102, 292, 256, 24);
		pfPassword.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel.add(pfPassword);
		
		pfConfirmPassword = new JPasswordField();
		pfConfirmPassword.setBounds(102, 328, 256, 24);
		pfConfirmPassword.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel.add(pfConfirmPassword);
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBackground(new Color(255, 240, 245));
		rdbtnNam.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnNam.setBounds(97, 191, 59, 21);
		panel.add(rdbtnNam);
		
		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(new Color(255, 240, 245));
		rdbtnNu.setFont(new Font("SansSerif", Font.PLAIN, 15));
		rdbtnNu.setBounds(186, 191, 103, 21);
		panel.add(rdbtnNu);
		
//		ButtonGroup cho giới tính
		ButtonGroup btngrGender= new ButtonGroup();
		btngrGender.add(rdbtnNam);
		btngrGender.add(rdbtnNu);
		
		JLabel lblNewLabel_7_1 = new JLabel("CCCD");
		lblNewLabel_7_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_7_1.setBounds(20, 254, 68, 24);
		panel.add(lblNewLabel_7_1);
		
		tfCCCD = new JTextField();
		tfCCCD.setColumns(10);
		tfCCCD.setBorder(new LineBorder(Color.CYAN, 1));
		tfCCCD.setBounds(102, 258, 256, 24);
		panel.add(tfCCCD);
		
		
	}
}
