package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import DTO.detailServiceDTO;
import DTO.serviceDTO;
import BUS.serviceBUS;
import BUS.detailServiceBUS;

public class frmService extends JFrame{

	private JTable table;
	private JScrollPane scrollPane;

	private JPanel mainpanel;
	private JPanel panel;
	private JPanel framepane;
	private FlowLayout flowLayout;

	private int id ;
    private JButton btnXacNhan;
	
	private ArrayList<serviceDTO> arrService;

	private serviceBUS serviceBUS= new serviceBUS();
	private JTable table_1;
	private DefaultTableModel modelTableService1 = new DefaultTableModel();
	private JLabel lblNewLabel;
	private detailServiceBUS detailServiceBUS = new detailServiceBUS();



//	Get frameService
	/**
	 * Create the application.
	 */
	public frmService(int id) {
		initialize();
		this.id = id;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		serviceBUS.loaddata();
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 660, 600);
		ArrayList<serviceDTO> arrService = serviceBUS.getArrService();
		framepane = new JPanel();
		framepane.setLayout(new BoxLayout(framepane, BoxLayout.Y_AXIS));

		flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 10);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(640, 250));
		scrollPane.setMaximumSize(new Dimension(640, 250));
		mainpanel = new JPanel();
		mainpanel.setPreferredSize(new Dimension(620, -1));
		mainpanel.setLayout(flowLayout);
		scrollPane.setViewportView(mainpanel);
		framepane.add(scrollPane);

		JPanel[] panel = new JPanel[arrService.size()];
		JPanel[] panel_1 = new JPanel[arrService.size()];
		JPanel[] panel_2 = new JPanel[arrService.size()];
		JPanel[] panel_3 = new JPanel[arrService.size()];
		JLabel[] lblServiceID = new JLabel[arrService.size()];
		JLabel[] lblServiceName = new JLabel[arrService.size()];
		JLabel[] lblServicePrice = new JLabel[arrService.size()];
		JLabel[] lblServiceDescribe = new JLabel[arrService.size()];
		JSpinner[] lblServiceAmount = new JSpinner[arrService.size()];
		JLabel[] lblDes = new JLabel[arrService.size()];
		for (int i=0;i<arrService.size();i++) {
			panel[i] = new JPanel();
			panel[i].setLayout(new BoxLayout(panel[i], BoxLayout.Y_AXIS));
			panel[i].setPreferredSize(new Dimension(200, 100));
			panel_1[i] = new JPanel();
			panel_1[i].setLayout(new FlowLayout());
			panel_2[i] = new JPanel();
			panel_2[i].setLayout(new GridLayout(2,1));
			panel_2[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // top, left, bottom, right
			panel_3[i] = new JPanel();

			lblDes[i] = new JLabel("Mô tả: ");
			panel[i].setLayout(new GridLayout(3,1));
			panel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			lblServiceID[i] = new JLabel("ID: " + arrService.get(i).getService_id());
//			lblServiceID[i].setBounds(10, 10, 100, 20);
			lblServiceName[i] = new JLabel("Tên DV: " + arrService.get(i).getService_name());
//			lblServiceName[i].setBounds(30, 10, 100, 20);
			lblServicePrice[i] = new JLabel("Giá: " + arrService.get(i).getService_price() + " $");
//			lblServicePrice[i].setBounds(30, 30, 100, 20);
			lblServiceDescribe[i] = new JLabel(arrService.get(i).getService_description());
			lblServiceDescribe[i].setBounds(50, 70, 100, 20);
			lblServiceAmount[i] = new JSpinner();
			lblServiceAmount[i].setPreferredSize(new Dimension(50, 20));
			panel_1[i].add(lblServiceID[i]);
			panel_1[i].add(lblServiceName[i]);
			panel_2[i].add(lblDes[i]);
			panel_2[i].add(lblServiceDescribe[i]);
			panel_3[i].add(lblServicePrice[i]);
			panel_3[i].add(lblServiceAmount[i]);
			panel[i].add(panel_1[i]);
			panel[i].add(panel_2[i]);
			panel[i].add(panel_3[i]);
			int finalI = i;
			lblServiceAmount[i].addChangeListener(e -> {
				if ((int) lblServiceAmount[finalI].getValue() >= 0){
					int amount = (int) lblServiceAmount[finalI].getValue();
					double price = (int) serviceBUS.getpricebyID(arrService.get(finalI).getService_id());
					System.out.println(price);
					double total = amount * price;
					if (detailServiceBUS.checkAvailableService(arrService.get(finalI).getService_id())) {
						detailServiceBUS.edittemp(arrService.get(finalI).getService_id(), amount, total);
					} else {
						detailServiceBUS.addtemp(new detailServiceDTO(id, arrService.get(finalI).getService_id(), amount, total));
					}
					if (amount== 0) {
						if(detailServiceBUS.checkAvailableService(arrService.get(finalI).getService_id()))	{
							detailServiceBUS.deletetempDetailService(arrService.get(finalI).getService_id());
						}
					}
					showtableDetailService(detailServiceBUS.getArrDetailService());
				}else{
					lblServiceAmount[finalI].setValue(0);
				}
				});

			mainpanel.add(panel[i]);
		}
		lblNewLabel = new JLabel("Dịch vụ đã chọn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		framepane.add(lblNewLabel);

		showTableService();
		btnXacNhan = new JButton("Xác nhận");
		framepane.add(btnXacNhan);
		btnXacNhan.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(framepane);
		btnXacNhan.addActionListener(e -> {
			btnXacNhanActionPerformed(e);
		});
	}
	private void showTableService() {
		table_1 = new JTable();
		table_1.setModel(modelTableService1);
		table_1.setPreferredSize(new Dimension(640, 250));
		modelTableService1.addColumn("ID DV");
		modelTableService1.addColumn("Tên dịch vụ");
		modelTableService1.addColumn("Số lượng");
		modelTableService1.addColumn("Tổng tiền(USD)");

		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setPreferredSize(new Dimension(640, 300));
		panel = new JPanel();
		panel.setBounds(10, 280, 660, 310);
		panel.setPreferredSize(new Dimension(640, 310));
		panel.setMaximumSize(new Dimension(640, 310));
		panel.add(scrollPane);
		framepane.add(panel);
	}
	public void showtableDetailService(ArrayList<detailServiceDTO> arrDetailService) {
		modelTableService1.setRowCount(0);
		for (detailServiceDTO detailServiceDTO : arrDetailService) {
			modelTableService1.addRow(new Object[] {detailServiceDTO.getId_dv(), serviceBUS.getnamebyID(detailServiceDTO.getId_dv()), detailServiceDTO.getSoluong_dv(), detailServiceDTO.getTongtien_dv()});
		}
	}
	public void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {
		detailServiceBUS.checkdup();
		detailServiceBUS.adddatabase();
		JOptionPane.showMessageDialog(null, "Đã thêm dịch vụ thành công");
		this.dispose();
	}
}
