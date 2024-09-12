package GUI;

import java.awt.EventQueue;
import DTO.accountDTO;
import BUS.accountBUS;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmLogin {

	private JFrame frame;
	private JTextField tfEmail;
	private JPasswordField pfPassword;
	
	private accountBUS accountBus = new accountBUS();
	static  accountDTO account= new accountDTO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin window = new frmLogin();
					
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//	Lấy Jframe của frmLogin
	public JFrame getFrameLogin() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public frmLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 409, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 395, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 240, 245));
		panel_1.setBounds(23, 22, 350, 407);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome back");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblNewLabel.setBounds(29, 29, 233, 30);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(29, 136, 85, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(29, 199, 96, 13);
		panel_1.add(lblNewLabel_2);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(29, 159, 296, 30);
		tfEmail.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền 
		panel_1.add(tfEmail);
		tfEmail.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(29, 222, 296, 30);
		pfPassword.setBorder(new LineBorder(Color.CYAN, 1));//Đổi màu viền
		panel_1.add(pfPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        login();
		    }
		});
		pfPassword.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        login();
    }
});
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setBounds(217, 290, 108, 37);
		btnLogin.setBorderPainted(false);//Bỏ viền btn
		panel_1.add(btnLogin);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome back! AbyssHotel glad to see you");
		lblNewLabel_3.setForeground(new Color(112, 128, 144));
		lblNewLabel_3.setBackground(new Color(119, 136, 153));
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(29, 69, 311, 30);
		panel_1.add(lblNewLabel_3);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	private void login() {
	    
	    String username = tfEmail.getText().trim();
	    String password = new String(pfPassword.getPassword());

	    // Lấy thông tin tài khoản trả về đối tượng account
	   
	    account = accountBus.login(username, password);

	    // Kiểm tra, xử lí đăng nhập
	    if(account != null) {
	        
	        	frmHomepage frmHome = new frmHomepage(account);
	 	        frmHome.getFrameHome().setVisible(true);
	 	       frame.setVisible(false);
	        
	    } else {
	        JOptionPane.showMessageDialog(frame, "Tên đăng nhập hoặc mật khẩu không chính xác!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public accountDTO getAccount() {
		return account;
	}


}
