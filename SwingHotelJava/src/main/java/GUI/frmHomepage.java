package GUI;

import java.awt.EventQueue;

import DTO.accountDTO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JToggleButton;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.MenuItemUI;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class frmHomepage {

	private JFrame frame;
	private JPanel pnInner;
	private JPanel pnNevigation = new JPanel();
	
	private accountDTO account;
	
	public JLabel lbUserName, lbUserName1;
	private JButton btnLogout, btnLogout1;


	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public frmHomepage(accountDTO account) {
        this.account = account;
        initialize();
        // Các thiết lập khác
    }
//	Lấy Jframe của frmHome
	public JFrame getFrameHome() {
		return frame;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		switch (account.getRole()) {
	    case "Admin":
	        // Logic cho Admin
	    	frame = new JFrame();
			frame.getContentPane().setBackground(Color.WHITE);
			frame.setBounds(100, 100, 1311, 719);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			
			pnNevigation.setBounds(0, 0, 222, 682);
			pnNevigation.setBackground(new Color(175, 238, 238));
			frame.getContentPane().add(pnNevigation);
			pnNevigation.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setBackground(new Color(175, 238, 238));
			lblNewLabel_3.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/review (1).png")));
			lblNewLabel_3.setBounds(27, 10, 128, 126);
			pnNevigation.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("AbyssHotel");
			lblNewLabel_4.setForeground(new Color(0, 0, 128));
			lblNewLabel_4.setFont(new Font("Script MT Bold", Font.BOLD, 24));
			lblNewLabel_4.setBounds(27, 134, 140, 29);
			pnNevigation.add(lblNewLabel_4);
			
			JToggleButton tglbtnBooking = new JToggleButton("Quản lý đặt phòng");
			tglbtnBooking.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/bed.png")));
			tglbtnBooking.setUI(new MetalToggleButtonUI() {
				@Override
			    protected Color getFocusColor() {
			        return tglbtnBooking.getBackground();
			    }
			    protected Color getSelectColor() {
			        return tglbtnBooking.getBackground();
			    }
			});
			tglbtnBooking.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnBooking(e);
				}
			});
			tglbtnBooking.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnBooking.setBackground(new Color(175, 238, 238));
			tglbtnBooking.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			
			JToggleButton tglbtnRoom = new JToggleButton("Quản lý phòng");
			tglbtnRoom.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/beds.png")));
			tglbtnRoom.setUI(new MetalToggleButtonUI(){
				@Override 
				protected Color getFocusColor() {
					return tglbtnRoom.getBackground();
					
				}
				protected Color getSelectColor() {
					return tglbtnRoom.getBackground();
				}
			});
			tglbtnRoom.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnRoom(e);
					
				}
			});
			tglbtnRoom.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnRoom.setBackground(new Color(175, 238, 238));
			tglbtnRoom.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			JToggleButton tglbtnTypeRoom = new JToggleButton("Quản lý loại phòng");
			tglbtnTypeRoom.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/living-room.png")));
			tglbtnTypeRoom.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getFocusColor() {
					return tglbtnTypeRoom.getBackground();
				}
				protected Color getSelectColor() {
					return tglbtnTypeRoom.getBackground();
				}
			});
			tglbtnTypeRoom.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnTypeRoom(e);
				}
			});
			tglbtnTypeRoom.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnTypeRoom.setBackground(new Color(175, 238, 238));
			tglbtnTypeRoom.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			JToggleButton tglbtnInvoice = new JToggleButton("Quản lý hóa đơn");
			tglbtnInvoice.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/book-club.png")));
			tglbtnInvoice.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getFocusColor() {
					return tglbtnInvoice.getBackground();
				}
				protected Color getSelectColor() {
					return tglbtnInvoice.getBackground();
				}
			});
			tglbtnInvoice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnInvoice(e);
				}
			});
			tglbtnInvoice.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnInvoice.setBackground(new Color(175, 238, 238));
			tglbtnInvoice.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			JToggleButton tglbtnCustomer = new JToggleButton("Quản lý khách hàng");
			tglbtnCustomer.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/meeting.png")));
			tglbtnCustomer.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getFocusColor() {
					return tglbtnCustomer.getBackground();
				}
				protected Color getSelectColor() {
					return tglbtnCustomer.getBackground();
				}
			});
			tglbtnCustomer.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnCustomer(e);
				}
			});
			tglbtnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnCustomer.setBackground(new Color(175, 238, 238));
			tglbtnCustomer.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			JToggleButton tglbtnStaff = new JToggleButton("Quản lý nhân viên");
			tglbtnStaff.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/teamwork (1).png")));
			tglbtnStaff.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getFocusColor() {
					return tglbtnStaff.getBackground();
				}
				protected Color getSelectColor() {
					return tglbtnStaff.getBackground();
				}
			});
			tglbtnStaff.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnStaff(e);
				}
			});
			tglbtnStaff.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnStaff.setBackground(new Color(175, 238, 238));
			tglbtnStaff.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			JToggleButton tglbtnRevenue = new JToggleButton("Thống kê");
			tglbtnRevenue.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/budget.png")));
			tglbtnRevenue.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getFocusColor() {
					return tglbtnRevenue.getBackground();
				}
				protected Color getSelectColor() {
					return tglbtnRevenue.getBackground();
				}
			});
			tglbtnRevenue.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnRevenue(e);
				}
			});
			tglbtnRevenue.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnRevenue.setBackground(new Color(175, 238, 238));
			tglbtnRevenue.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			JToggleButton tglbtnProfile = new JToggleButton("Thông tin cá nhân");
			tglbtnProfile.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/user (4).png")));
			tglbtnProfile.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getFocusColor() {
			        return tglbtnProfile.getBackground();
			    }

			    @Override
			    protected Color getSelectColor() {
			        return tglbtnProfile.getBackground(); 
			    }
			});
			tglbtnProfile.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnProfile(e);
				};
			});
			tglbtnProfile.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnProfile.setBackground(new Color(175, 238, 238));
			tglbtnProfile.setFont(new Font("SansSerif", Font.PLAIN, 15));

			
			JToggleButton tglbtnService = new JToggleButton("Quản lý dịch vụ");
			tglbtnService.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/customer-service.png")));
			tglbtnService.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getFocusColor() {
			        return tglbtnService.getBackground();
			    }

			    @Override
			    protected Color getSelectColor() {
			        return tglbtnService.getBackground(); 
			    }
			});
			tglbtnService.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnService(e);
				};
			});
			tglbtnService.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnService.setFont(new Font("SansSerif", Font.PLAIN, 15));
			tglbtnService.setBorderPainted(false);

			
//			tgbtnAccount
			JToggleButton tgbtnAccount = new JToggleButton("Quản lý tài khoản");
			tgbtnAccount.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/skills.png")));
			tgbtnAccount.setHorizontalAlignment(SwingConstants.LEFT);
			tgbtnAccount.setFont(new Font("SansSerif", Font.PLAIN, 15));
			tgbtnAccount.setBorderPainted(false);
			tgbtnAccount.setBackground(new Color(175, 238, 238));

			tgbtnAccount.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getFocusColor() {
			        return tgbtnAccount.getBackground();
			    }

			    @Override
			    protected Color getSelectColor() {
			        return tgbtnAccount.getBackground(); 
			    }
			});
			tgbtnAccount.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnAccount(e);
				};
			});
			// Quản lý đặt phòng
			tglbtnBooking.setBounds(10, 175, 212, 41);
			tglbtnBooking.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnBooking);
			// Quản lý phòng
			tglbtnRoom.setBounds(10, 223, 212, 41);
			tglbtnRoom.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnRoom);
			// Quản lý loại phòng
			tglbtnTypeRoom.setBounds(10, 274, 212, 41);
			tglbtnTypeRoom.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnTypeRoom);
			// Quản lý dịch vụ
			tglbtnService.setBackground(new Color(175, 238, 238));
			tglbtnService.setBounds(10, 327, 212, 41);
			pnNevigation.add(tglbtnService);
			// Quản lý hóa đơn
			tglbtnInvoice.setBounds(10, 378, 212, 41);
			tglbtnInvoice.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnInvoice);
			// Quản lý khách hàng
			tglbtnCustomer.setBounds(10, 429, 212, 41);
			tglbtnCustomer.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnCustomer);
			// Quản lý nhân viên
			tglbtnStaff.setBounds(10, 480, 212, 41);
			tglbtnStaff.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnStaff);
			// Thống kê
			tglbtnRevenue.setBounds(10, 583, 212, 41);
			tglbtnRevenue.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnRevenue);

			// Thông tin cá nhân
			tglbtnProfile.setBounds(10, 629, 212, 41);
			tglbtnProfile.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnProfile);
			// Quản lý tài khoản
			tgbtnAccount.setBounds(10, 531, 212, 41);
			pnNevigation.add(tgbtnAccount);


//			BUTTON GROUP JTOGGLEBUTTON
			ButtonGroup btngr= new ButtonGroup();
			btngr.add(tglbtnBooking);
			btngr.add(tglbtnRoom);
			btngr.add(tglbtnTypeRoom);
			btngr.add(tglbtnService);
			btngr.add(tglbtnInvoice);
			btngr.add(tglbtnCustomer);
			btngr.add(tglbtnStaff);
			btngr.add(tglbtnRevenue);
			btngr.add(tglbtnProfile);
			btngr.add(tgbtnAccount);
			
			
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(222, 0, 1085, 44);
			panel_1.setBackground(new Color(175, 238, 238));
			frame.getContentPane().add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/user (2).png")));
			lblNewLabel.setBounds(810, 4, 24, 37);
			panel_1.add(lblNewLabel);
			
			lbUserName = new JLabel(account.getAccount_username());
			lbUserName.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lbUserName.setBounds(839, 4, 150, 37);
			panel_1.add(lbUserName);

			
			
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					btnLogoutActionPerformed(e);
				}
			});
			btnLogout .setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/exit.png")));
			btnLogout .setForeground(new Color(0, 0, 0));
			btnLogout .setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnLogout .setBorderPainted(false);
			btnLogout .setBackground(new Color(175, 238, 238));
			btnLogout .setBounds(956, 4, 117, 37);
			panel_1.add(btnLogout );
			
			pnInner = new JPanel();
			pnInner.setBounds(222, 43, 1089, 639);
			
			frame.getContentPane().add(pnInner);
//			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Đặt kích thước frame full màn hình
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);//Khóa nút phóng to toàn màn hình của frame
			
			tglbtnBooking.doClick();//Mặc định cho quản lý đặt phòng hiển thị
	        break;
	        
	    case "Staff":
	    	
	    	 // Logic cho Staff
	    	frame = new JFrame();
			frame.getContentPane().setBackground(Color.WHITE);
			frame.setBounds(100, 100, 1311, 719);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			
			pnNevigation.setBounds(0, 0, 222, 682);
			pnNevigation.setBackground(new Color(175, 238, 238));
			frame.getContentPane().add(pnNevigation);
			pnNevigation.setLayout(null);
			
			JLabel lblNewLabel_31 = new JLabel("");
			lblNewLabel_31.setBackground(new Color(175, 238, 238));
			lblNewLabel_31.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/review (1).png")));
			lblNewLabel_31.setBounds(27, 10, 128, 126);
			pnNevigation.add(lblNewLabel_31);
			
			JLabel lblNewLabel_41 = new JLabel("AbyssHotel");
			lblNewLabel_41.setForeground(new Color(0, 0, 128));
			lblNewLabel_41.setFont(new Font("Script MT Bold", Font.BOLD, 24));
			lblNewLabel_41.setBounds(27, 134, 140, 29);
			pnNevigation.add(lblNewLabel_41);
			
			JToggleButton tglbtnBooking1 = new JToggleButton("Quản lý đặt phòng");
			tglbtnBooking1.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/bed.png")));
			tglbtnBooking1.setUI(new MetalToggleButtonUI() {
				@Override
			    protected Color getFocusColor() {
			        return tglbtnBooking1.getBackground();
			    }
			    protected Color getSelectColor() {
			        return tglbtnBooking1.getBackground();
			    }
			});
			tglbtnBooking1.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnBooking(e);
				}
			});
			tglbtnBooking1.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnBooking1.setBackground(new Color(175, 238, 238));
			tglbtnBooking1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			tglbtnBooking1.setBounds(10, 175, 212, 41);
			tglbtnBooking1.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnBooking1);
			

			JToggleButton tglbtnInvoice1 = new JToggleButton("Quản lý hóa đơn");
			tglbtnInvoice1.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/book-club.png")));
			tglbtnInvoice1.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getFocusColor() {
					return tglbtnInvoice1.getBackground();
				}
				protected Color getSelectColor() {
					return tglbtnInvoice1.getBackground();
				}
			});
			tglbtnInvoice1.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnInvoice(e);
				}
			});
			tglbtnInvoice1.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnInvoice1.setBackground(new Color(175, 238, 238));
			tglbtnInvoice1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			tglbtnInvoice1.setBounds(10, 223, 212, 41);
			tglbtnInvoice1.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnInvoice1);
			
			JToggleButton tglbtnCustomer1 = new JToggleButton("Quản lý khách hàng");
			tglbtnCustomer1.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/meeting.png")));
			tglbtnCustomer1.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getFocusColor() {
					return tglbtnCustomer1.getBackground();
				}
				protected Color getSelectColor() {
					return tglbtnCustomer1.getBackground();
				}
			});
			tglbtnCustomer1.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnCustomer(e);
				}
			});
			tglbtnCustomer1.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnCustomer1.setBackground(new Color(175, 238, 238));
			tglbtnCustomer1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			tglbtnCustomer1.setBounds(10, 271, 212, 41);
			tglbtnCustomer1.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnCustomer1);
			
			
			
			JToggleButton tglbtnProfile1 = new JToggleButton("Thông tin cá nhân");
			tglbtnProfile1.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/user (4).png")));
			tglbtnProfile1.setUI(new MetalToggleButtonUI() {
			    @Override
			    protected Color getFocusColor() {
			        return tglbtnProfile1.getBackground();
			    }

			    @Override
			    protected Color getSelectColor() {
			        return tglbtnProfile1.getBackground(); 
			    }
			});
			tglbtnProfile1.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					innerpnProfile(e);
				};
			});
			tglbtnProfile1.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnProfile1.setBackground(new Color(175, 238, 238));
			tglbtnProfile1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			tglbtnProfile1.setBounds(10, 319, 212, 41);
			tglbtnProfile1.setBorderPainted(false);//Bỏ viền btn
			pnNevigation.add(tglbtnProfile1);
			
//			BUTTON GROUP JTOGGLEBUTTON
			ButtonGroup btngr1= new ButtonGroup();
			btngr1.add(tglbtnBooking1);
			
			
			btngr1.add(tglbtnInvoice1);
			btngr1.add(tglbtnCustomer1);
			
	
			btngr1.add(tglbtnProfile1);
			

			JPanel panel_11 = new JPanel();
			panel_11.setBounds(222, 0, 1085, 44);
			panel_11.setBackground(new Color(175, 238, 238));
			frame.getContentPane().add(panel_11);
			panel_11.setLayout(null);
			
			JLabel lblNewLabel1 = new JLabel("");
			lblNewLabel1.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/user (2).png")));
			lblNewLabel1.setBounds(810,4, 24, 37);
			panel_11.add(lblNewLabel1);
			
			lbUserName1 = new JLabel(account.getAccount_username());
			lbUserName1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			lbUserName1.setBounds(839, 4,150, 37);
			panel_11.add(lbUserName1);
			
			
			btnLogout1 = new JButton("Logout");
			btnLogout1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					btnLogoutActionPerformed(e);
				}
			});
			btnLogout1.setIcon(new ImageIcon(frmHomepage.class.getResource("/Picture/exit.png")));
			btnLogout1.setForeground(new Color(0, 0, 0));
			btnLogout1.setFont(new Font("SansSerif", Font.PLAIN, 15));
			btnLogout1.setBorderPainted(false);
			btnLogout1.setBackground(new Color(175, 238, 238));
			btnLogout1.setBounds(956, 4, 117, 37);
			panel_11.add(btnLogout1);
			
			pnInner = new JPanel();
			pnInner.setBounds(222, 43, 1089, 639);
			
			frame.getContentPane().add(pnInner);
//			frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Đặt kích thước frame full màn hình
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);//Khóa nút phóng to toàn màn hình của frame
			
			tglbtnBooking1.doClick();//Mặc định cho quản lý đặt phòng hiển thị
	        break;
	    default:
	        // Logic cho trường hợp không phải là Admin hoặc Staff
	        JOptionPane.showMessageDialog(frame, "Vai trò không được hỗ trợ", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        break;
	}
			
	}
//	Hàm đổi JToggleButton, đổi Panel
	private void switchPanel(JPanel panel) {
        frame.getContentPane().remove(pnInner); // Xóa panel hiện tại
        pnInner = panel; // Gán panel mới cho biến panel_2
        pnInner.setBounds(222, 44, 1089, 639); // Đặt lại kích thước cho panel mới (nếu cần)

        frame.getContentPane().add(pnInner); // Thêm panel mới vào frame
        frame.getContentPane().validate(); // Xác nhận các thay đổi
        frame.getContentPane().repaint(); // Vẽ lại frame để hiển thị panel mới
    }
//	Panel của quản lý đặt phòng
	private void innerpnBooking(ItemEvent e) {
		if(e.getStateChange()== ItemEvent.SELECTED) {
			// Tạo panel mới chứa các tab
	        JPanel pnBooking = new JPanel();
	        pnBooking.setBounds(0, 0,1089, 639);
	        pnBooking.setLayout(null);

	        // Tạo JTabbedPane để chứa các tab
	        JTabbedPane tabbedPaneBooking = new JTabbedPane(JTabbedPane.TOP);
	        tabbedPaneBooking.setBounds(0, 0,1089, 639);
	        pnBooking.add(tabbedPaneBooking);

	        // thêm pnBooking vào tab
	        pnBooking pnBookingtab = new pnBooking();
	        // Thêm các thành phần vào pnBookingTab1
	        tabbedPaneBooking.addTab("Thêm phiếu đặt", null, pnBookingtab, null);

	        pnDetailBooking pnDetailBookingtab = new pnDetailBooking();
	        // Thêm các thành phần vào pnBookingTab2
	        tabbedPaneBooking.addTab("Danh sách phiếu đặt", null, pnDetailBookingtab, null);
	     
	        

	        pnCheckOut pnCheckouttab = new pnCheckOut();
	        // Thêm các thành phần vào pnBookingTab3
	        tabbedPaneBooking.addTab("Trả phòng", null, pnCheckouttab, null);

	        // Hiển thị panel quản lý đặt phòng
	        switchPanel(pnBooking);
	        
	        // Cập nhật bảng khi chọn tab "Danh sách phiếu đặt"
	        // Cập nhật bảng khi chọn tab "Danh sách phiếu đặt"
	        tabbedPaneBooking.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	                int selectedIndex = tabbedPaneBooking.getSelectedIndex();
	                if (selectedIndex == 1) { // cập nhật khi chọn tab "Danh sách phiếu đặt"
	                    pnDetailBookingtab.updateTable();
	                } else if (selectedIndex == 2) { //  cập nhật khi chọn tab "Trả phòng"
	                    pnCheckouttab.updateTableCheckout() ;
	                }
	            }
	        });

		        
		        ((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!= ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}

//	Panel quản lý phòng
	private void innerpnRoom(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			JPanel pnroom= new pnRoom();
			
			switchPanel(pnroom);
			
			((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!=ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
//	Panel quản lý loại phòng
	private void innerpnTypeRoom(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			JPanel pntyperoom= new pnTyperoom();
			
			switchPanel(pntyperoom);
			
			((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!=ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
//	Panel Quản lý hóa đơn
	private void innerpnInvoice(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			JPanel pninvoice= new pnInvoice();
			
			switchPanel(pninvoice);
			
			((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!=ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
//	Panel quản lý khách hàng
	private void innerpnCustomer(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			JPanel pncustomer= new pnCustomer();
			
			switchPanel(pncustomer);
			
			((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!=ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
	
//	Panel quản lý nhân viên
	private void innerpnStaff(ItemEvent e) {
		if(e.getStateChange()== ItemEvent.SELECTED) {
			 JPanel pnstaff = new pnStaff(); // Tạo một instance mới của JPanel bạn muốn hiển thị

			 switchPanel(pnstaff);
		        
		        ((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!= ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
	
//	Panel quản lý doanh thu
	private void innerpnRevenue(ItemEvent e) {
		if(e.getStateChange()== ItemEvent.SELECTED) {
			 JPanel pnrevenue = new pnRevenue(); // Tạo một instance mới của JPanel bạn muốn hiển thị

			 switchPanel(pnrevenue);
		        
		        ((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!= ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
	  
//	Panel quản lý dịch vụ
	private void innerpnService(ItemEvent e) {
		if(e.getStateChange()== ItemEvent.SELECTED) {
			 JPanel pnservice = new pnService(); // Tạo một instance mới của JPanel bạn muốn hiển thị

			 switchPanel(pnservice);
		        
		        ((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!= ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
	
//	Panel thông tin cá nhân
	private void innerpnProfile(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			JPanel pnprofile= new pnProfile();
			
			switchPanel(pnprofile);
			
			((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!=ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
//	Panel quản lý tài khoản
	private void innerpnAccount(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED) {
			JPanel pnAccount= new pnAccount();
			
			switchPanel(pnAccount);
			
			((JToggleButton)e.getSource()).setBackground(Color.white);
		}else if(e.getStateChange()!=ItemEvent.SELECTED) {
			((JToggleButton)e.getSource()).setBackground(pnNevigation.getBackground());
		}
	}
//	Sự kiện nút logout
	private void btnLogoutActionPerformed(ActionEvent e) {
		frmLogin frmlogin = new frmLogin();
		frmlogin.getFrameLogin().setVisible(true);
		frame.dispose();
	}
}
