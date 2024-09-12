package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import BUS.billBUS;
import BUS.deltailBookingBUS;
import BUS.roomBUS;
import BUS.staffBUS;
import DTO.billDTO;
import DTO.detailBookingDTO;
import DTO.roomDTO;
import DTO.staffDTO;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;

import javax.swing.JTextField;
//Revenue(n): doanh thu
public class pnRevenue extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modelRevenue = new DefaultTableModel();
	private JDateChooser dcStart = new JDateChooser();
	private JDateChooser dcEnd = new JDateChooser();
	private ArrayList<billDTO> arrInvoice = new ArrayList<>();
	private billBUS bBUS = new billBUS();
	
	private ArrayList<staffDTO>  arrStaff = new ArrayList<>();
	private staffBUS sBUS = new staffBUS();
	
	private ArrayList<roomDTO> arrRoom = new ArrayList<>();
	private roomBUS rBUS = new roomBUS();
	private JTextField tfSum;
	private JLabel lbSum_Invoice;
	private JLabel lbSum_Customer ;
	private int Sum = 0;
	private JTextField tftotalSUM;
	/**
	 * Create the panel.
	 */
	public pnRevenue() {
		initComponent();
		ListRevenue();
		sumCount();
	}
	private void initComponent() {
		setBackground(Color.white);
		setBounds(222, 44, 1079, 587);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Từ Ngày");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel.setBounds(25, 111, 69, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Đến Ngày");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel_1.setBounds(394, 111, 81, 30);
		add(lblNewLabel_1);
		
		
		dcStart.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dcStart.setDateFormatString("yyyy-MM-dd");
		dcStart.setBounds(96, 111, 217, 30);
		add(dcStart);
		
		dcEnd.setDateFormatString("yyyy-MM-dd");
		dcEnd.setBounds(461, 111, 217, 30);
		add(dcEnd);
		PropertyChangeListener dateChangeListener = new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		       
		    }
		};
		dcStart.addPropertyChangeListener("date", dateChangeListener);
		dcEnd.addPropertyChangeListener("date", dateChangeListener);
		JButton btnFind = new JButton("Tìm Kiếm");
		btnFind.setBorderPainted(false);
		btnFind.setForeground(Color.WHITE);
		btnFind.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnFind.setBackground(new Color(0, 206, 209));
		btnFind.setBounds(733, 105, 115, 41);
		add(btnFind);
		
//		tìm kiếm thống kê theo điều kiện
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 181, 1016, 328);
		add(scrollPane);
		table = new JTable();
		table.setModel(modelRevenue);
		JScrollPane scrollPane_1= new JScrollPane(table);
		scrollPane.setColumnHeaderView(scrollPane_1);
		scrollPane_1.getViewport().setBackground(Color.white);
	
		lbSum_Customer = new JLabel("0");
		lbSum_Customer.setFont(new Font("Serif", Font.BOLD, 17));
		lbSum_Customer.setBounds(230, 36, 68, 30);
		add(lbSum_Customer);
		
		JLabel lblNewLabel_3 = new JLabel("Tổng số lượng khách hàng");
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 17));
//		lblNewLabel_3.setBorder(new RoundedBorder(Color.black,20));
		lblNewLabel_3.setBackground(new Color(0, 206, 209));
		lblNewLabel_3.setForeground(Color.white);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBounds(25, 28, 195, 41);
		add(lblNewLabel_3);
		
		lbSum_Invoice = new JLabel("0");
		lbSum_Invoice.setFont(new Font("Serif", Font.BOLD, 17));
		lbSum_Invoice.setBounds(667, 45, 45, 13);
		add(lbSum_Invoice);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tổng số lượng hóa đơn");
		lblNewLabel_3_1.setFont(new Font("Serif", Font.BOLD, 17));
//		lblNewLabel_3_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 127), new Color(0, 255, 0)));
		lblNewLabel_3_1.setBackground(new Color(0, 206, 209));
		lblNewLabel_3_1.setForeground(Color.white);
		lblNewLabel_3_1.setOpaque(true);
		lblNewLabel_3_1.setBounds(461, 28, 195, 41);
		add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tổng tiền : ");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_2.setBounds(733, 545, 96, 19);
		add(lblNewLabel_2);
		
		tfSum = new JTextField();
		tfSum.setFont(new Font("SansSerif", Font.BOLD, 14));
		tfSum.setBackground(new Color(255, 255, 255));
		tfSum.setEditable(false);
		tfSum.setEnabled(false);
		tfSum.setBounds(830, 544, 96, 21);
		add(tfSum);
		tfSum.setColumns(10);
		
		JButton btnPdf = new JButton("Xuất PDF");
        btnPdf.setForeground(Color.WHITE);
        btnPdf.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnPdf.setBorderPainted(false);
        btnPdf.setBackground(new Color(0, 206, 209));
        btnPdf.setBounds(895, 105, 144, 41);
        btnPdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportToPDF();
            }
        });
        add(btnPdf);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tổng doanh thu:");
		lblNewLabel_2_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(733, 43, 126, 19);
		add(lblNewLabel_2_1);
		
		tftotalSUM = new JTextField();
		tftotalSUM.setText("0");
		tftotalSUM.setFont(new Font("SansSerif", Font.BOLD, 14));
		tftotalSUM.setEnabled(false);
		tftotalSUM.setEditable(false);
		tftotalSUM.setColumns(10);
		tftotalSUM.setBackground(Color.WHITE);
		tftotalSUM.setBounds(858, 42, 96, 21);
		add(tftotalSUM);
		btnFind.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				findRevenue(e);
			}
		});
	}
	private void ListRevenue() {
		modelRevenue.addColumn("Mã hóa đơn");
		modelRevenue.addColumn("Tên khách hàng");
		modelRevenue.addColumn("Phòng");
		modelRevenue.addColumn("Ngày Lập HD");
		modelRevenue.addColumn("Tổng tiền(USD)");
		
		arrInvoice = bBUS.getAllBillDetail();
//		
//		arrStaff = sBUS.getAllStaff();
//		
//		arrRoom = rBUS.getAllRoomActive();
		updateRevenue();
}
	private void updateRevenue() {
		modelRevenue.setRowCount(0);
		for(billDTO bill: arrInvoice) {
			modelRevenue.addRow(new Object[] {
					bill.getId_hoadon(),
					bill.getName_customer(),
					bill.getName_room(),
					bill.getNgaylap_hd(),
					bill.getTongtien_hd()
			});
		}
	}
	private void findRevenue(ActionEvent e) {
	    if (dcStart.getDate() != null && dcEnd.getDate() != null) {
	        long diff = dcEnd.getDate().getTime() - dcStart.getDate().getTime();
	        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	        if (days < 0) {
	            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
	            return;
	        }
	    } 
	        // Tạo một danh sách mới để lưu trữ các hóa đơn thỏa mãn điều kiện lọc
	        ArrayList<billDTO> filteredBills = new ArrayList<>();

	        // Duyệt qua danh sách hóa đơn và thêm các hóa đơn thỏa mãn điều kiện vào danh sách mới
	        for (billDTO bill : arrInvoice) {
	            // Lấy ngày từ JDateChooser dcStart
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(dcStart.getDate());

	            // Giảm giá trị của ngày đi 1 ngày
	            calendar.add(Calendar.DATE, -1);

	            // Tạo một JDateChooser mới và đặt giá trị là ngày giảm đi 1 ngày
	            JDateChooser tempStart = new JDateChooser();
	            tempStart.setDate(calendar.getTime());

	            if (bill.getNgaylap_hd().after(tempStart.getDate()) || bill.getNgaylap_hd().equals(tempStart.getDate())) {
	                if (bill.getNgaylap_hd().before(dcEnd.getDate()) || bill.getNgaylap_hd().equals(dcStart.getDate())) {
	                    filteredBills.add(bill);
	                }
	            }
	        }

	        if(filteredBills.isEmpty()) {
	        	JOptionPane.showMessageDialog(this, "Không có hóa đơn nào xuất hiện trong ngày này !!");
	            return;
	        }
	        // Xóa toàn bộ dữ liệu hiển thị trên bảng
	        modelRevenue.setRowCount(0);
	        Sum=0;
	        // Duyệt qua danh sách hóa đơn đã lọc và hiển thị trên bảng
	        for (billDTO bill : filteredBills) {
	            modelRevenue.addRow(new Object[] {
	                bill.getId_hoadon(),
	                bill.getName_customer(),
	                bill.getName_room(),
	                bill.getNgaylap_hd(),
	                bill.getTongtien_hd()
	            });
	            Sum += bill.getTongtien_hd();
	        }
	        tfSum.setText(String.valueOf(Sum));
	   
	}

	private void sumCount() {
	    int sumCustomer = 0;
	    int sumBill = 0;
	    
	    // Tạo một Set để lưu trữ các tên khách hàng đã xuất hiện
	    Set<String> customerNames = new HashSet<>();

	    // Duyệt qua danh sách hóa đơn
	    for (billDTO bill : arrInvoice) {
	        sumBill++;
	        Sum+=bill.getTongtien_hd();
	        // Kiểm tra xem tên khách hàng đã tồn tại trong Set hay chưa
	        // Nếu chưa tồn tại, tăng biến đếm sumCustomer và thêm tên vào Set
	        if (!customerNames.contains(bill.getName_customer())) {
	            sumCustomer++;
	            customerNames.add(bill.getName_customer());
	        }
	    }

	    // Gán giá trị cho các JLabel
	    lbSum_Customer.setText(String.valueOf(sumCustomer));
	    lbSum_Invoice.setText(String.valueOf(sumBill));
	    tftotalSUM.setText(String.valueOf(Sum));
	}

	private void exportToPDF() {
	    // Khởi tạo một tài liệu PDF
	    Document document = new Document();
	    try {
	        // Đường dẫn tới file font
	        String fontPath = "D:\\Arial-Unicode-Font\\Arial Unicode Font.ttf";
	        
	        // Đăng ký font "Arial Unicode MS" với iText
	        FontFactory.register(fontPath, "Arial Unicode MS");
	        
	        // Khởi tạo một đối tượng PDFWriter để ghi vào file PDF
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("hoadon.pdf"));
	        
	        // Mở tài liệu PDF để có thể thêm nội dung vào
	        document.open();

	        // Tạo một bảng PDF với 5 cột
	        PdfPTable pdfTable = new PdfPTable(5);

	        // Thêm tiêu đề cho từng cột của bảng
	        com.itextpdf.text.Font fontHeader = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        PdfPCell cell1 = new PdfPCell(new Phrase("Mã hóa đơn", fontHeader));
	        PdfPCell cell2 = new PdfPCell(new Phrase("Tên khách hàng", fontHeader));
	        PdfPCell cell3 = new PdfPCell(new Phrase("Phòng", fontHeader));
	        PdfPCell cell4 = new PdfPCell(new Phrase("Ngày Lập HD", fontHeader));
	        PdfPCell cell5 = new PdfPCell(new Phrase("Tổng tiền", fontHeader));
	        pdfTable.addCell(cell1);
	        pdfTable.addCell(cell2);
	        pdfTable.addCell(cell3);
	        pdfTable.addCell(cell4);
	        pdfTable.addCell(cell5);

	        // Thêm dữ liệu từ bảng Swing vào bảng PDF
	        com.itextpdf.text.Font fontData = FontFactory.getFont("Arial Unicode MS", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        for (int i = 0; i < table.getRowCount(); i++) {
	            for (int j = 0; j < table.getColumnCount(); j++) {
	                Object value = table.getValueAt(i, j);
	                // Thêm giá trị của mỗi ô vào bảng PDF
	                pdfTable.addCell(new Phrase(value.toString(), fontData));
	            }
	        }

	        // Thêm bảng PDF vào tài liệu
	        document.add(pdfTable);

	    } catch (DocumentException | IOException ex) {
	        // Xử lý lỗi nếu có
	        JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu vào tệp PDF: " + ex.getMessage());
	    } finally {
	        // Đảm bảo đóng tài liệu PDF sau khi đã thêm dữ liệu
	        document.close();
	    }

	    // Thông báo cho người dùng biết rằng việc xuất file PDF đã hoàn thành
	    JOptionPane.showMessageDialog(this, "Xuất file PDF thành công!");
	}




}
