/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.common.GlobalHistogramBinarizer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.HTTT;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.Imei;
import model.KhachHang;
import model.KhuyenMai;
import model.NhanVien;
import model.SanPhamHD;
import repository.HoaDonChiTietFillTable;
import repository.HoaDonService1;
import service.HTTTService;
import service.HoaDonChiTietService;
import service.HoaDonService;
import service.ImeiService;
import service.KhachHangService;
import service.KhuyenMaiService;
import service.NhanVienService;
import service.SanPhamHDService;

/**
 *
 * @author khanh
 */
public final class BILL1 extends javax.swing.JPanel implements Runnable, ThreadFactory {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DecimalFormat currencyFormat = new DecimalFormat("#,##0");

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    List<SanPhamHD> listSanPhamHD = new ArrayList<>();
    List<KhachHang> listKH = new ArrayList<>();
    List<NhanVien> listNV = new ArrayList<>();
    List<HoaDon> listHD = new ArrayList<>();
    List<Imei> listImei = new ArrayList<>();
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    List<HTTT> listHTTT = new ArrayList<>();
    List<KhuyenMai> listKhuyenMai = new ArrayList<>();

    SanPhamHDService serviceSanPhamHD = new SanPhamHDService();
    KhachHangService serviceKH = new KhachHangService();
    NhanVienService serviceNV = new NhanVienService();
    HoaDonService serviceHD = new HoaDonService();
    ImeiService serviceImei = new ImeiService();
    HoaDonChiTietService serviceHDCT = new HoaDonChiTietService();
    HTTTService serviceHTTT = new HTTTService();
    KhuyenMaiService serviceKhuyenMai = new KhuyenMaiService();

    public BILL1() {
        initComponents();
        listSanPhamHD = serviceSanPhamHD.getAllSanPhamHD();
        listKH = serviceKH.getAll();
        listNV = serviceNV.getAllNhanVien();
        listHTTT = serviceHTTT.getAllHTTT();
        listKhuyenMai = serviceKhuyenMai.getAllKhuyenMai();
        listHD = serviceHD.getAllHoaDon();
        fillToTableHoaDon(listHD);
        initWebcam();
    }

    public void fillToTableHoaDon(List<HoaDon> list) {
        DefaultTableModel tblModel = new DefaultTableModel();
        String cols[] = {"MaHD", "NhanVien", "KhachHang", "Tổng tiền", "Mã KM", "Giảm giá", "Thành tiền", "HTTT", "trạng thái", "Ngày tạo"};
        tblModel.setColumnIdentifiers(cols);
        tblModel.setRowCount(0);
        for (HoaDon hd : list) {
//            tblModel.addRow(new Object[]{hd.getMaHoaDon(),hd.getMaNhanVien(),hd.getMaKhachHang()
//                    ,hd.getTongTien(),hd.getTrangThai(),hd.getNgayTao()});
            String tenNV = listNV.get(hd.getMaNhanVien() - 1).getTenNV();
            String tenKH = listKH.get(hd.getMaKhachHang() - 1).getTen();
            String tenHTTT = "";
            if (hd.getMaHTTT() != 0) {
                tenHTTT = listHTTT.get(hd.getMaHTTT() - 1).getTenHTTTT();
            }
            tblModel.addRow(new Object[]{hd.getMaHoaDon(), tenNV, tenKH,
                hd.getTongTien(), hd.getMaKhuyenMai(), hd.getGiamGia(), hd.getThanhTien(),
                tenHTTT, hd.getTrangThai(), hd.getNgayTao()});
        }
        tblHoadon.setModel(tblModel);
    }

    public void fillToTableHoaDonChiTiet(List<HoaDonChiTiet> list) {
        DefaultTableModel tblModel = new DefaultTableModel();
        String cols[] = {"MaHD", "MaSCPT", "TenSP", "TenImei", "Tổng tiền"};
        tblModel.setColumnIdentifiers(cols);
        tblModel.setRowCount(0);
        for (HoaDonChiTiet hdct : list) {
            tblModel.addRow(new Object[]{hdct.getMaHoaDon(), hdct.getMaSPCT(),
                hdct.getTenSP(), hdct.getTenImei(), hdct.getGia()});
        }
        tbHDCT.setModel(tblModel);
    }

    private void generateAndPrintInvoice() {
        int vt = tblHoadon.getSelectedRow();
        HoaDon hd = listHD.get(vt);
        Date currentDate = new Date();
        String ngayTao = dateFormat.format(hd.getNgayTao());
        String ngayThanhToan = dateFormat.format(currentDate);
        String maHoaDon = String.valueOf(hd.getMaHoaDon());
        String tenKhachHang = listKH.get(hd.getMaKhachHang() - 1).getTen();
        String sdtKH = listKH.get(hd.getMaKhachHang() - 1).getSdt();

        int soSP = listHDCT.size();
        String dsSP[] = new String[soSP];
        for (int i = 0; i < soSP; i++) {
            HoaDonChiTiet hdct = listHDCT.get(i);
            dsSP[i] = hdct.getTenSP() + "  Imei" + hdct.getTenImei() + "    TT: " + currencyFormat.format(hdct.getThanhTien());
        }

        String tongTien = String.valueOf(hd.getTongTien());
        String giamGia = String.valueOf(hd.getGiamGia());
        String thanhTien = String.valueOf(hd.getThanhTien());

        // Tạo hóa đơn PDF
        String pdfFileName = "HoaDon_" + maHoaDon + ".pdf";
        createInvoicePDF(pdfFileName, maHoaDon, tenKhachHang, sdtKH, ngayTao, ngayThanhToan,
                dsSP, tongTien, giamGia, thanhTien);

        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createInvoicePDF(String fileName, String maHoaDon, String tenKhachHang, String SDT, String ngayTao, String ngayThanhToan,
            String dsSP[], String tongTien, String giamGia, String thanhTien) {
        Document document = new Document();
//        document.addLanguage(document.toString());
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();
            Paragraph title = new Paragraph("HOA DON THANH TOAN");
            Paragraph light = new Paragraph("----------------------------------------------------------------------------------------------------------------");
            Paragraph Ngaytao = new Paragraph("NGAY TAO: " + ngayTao);
            Paragraph NgayThanhToan = new Paragraph("NGAY THANH TOAN: " + ngayThanhToan);
            Paragraph light1 = new Paragraph("---------------------------------------------------------------------------------------------------------------");
            Paragraph MaHoaDon = new Paragraph("MA HOA DON: " + maHoaDon);
            Paragraph Khachhang = new Paragraph(" KHACH HANG: " + tenKhachHang);
            Paragraph SDTKhachHang = new Paragraph("SDT KHACH HANG: " + SDT);
            Paragraph TitleSanPham = new Paragraph("SAN PHAM DUOC MUA");
            String TenSanPham = "";
            for (int i = 0; i < dsSP.length; i++) {
                TenSanPham = TenSanPham + dsSP[i] + "\n";
            }
            Paragraph SanPham = new Paragraph("SAN PHAM BAO GOM: \n" + TenSanPham);
            Paragraph light3 = new Paragraph("==============================");
            Paragraph tongTien3 = new Paragraph("TONG TIEN:  " + tongTien);
            Paragraph giamgia = new Paragraph("GIAM GIA:  " + giamGia);
            Paragraph ThanhTien = new Paragraph("THANH TOAN:  " + thanhTien);
            document.add(title);
            document.add(light);
            document.add(Ngaytao);
            document.add(NgayThanhToan);
            document.add(light1);
            document.add(MaHoaDon);
            document.add(Khachhang);
            document.add(SDTKhachHang);
            document.add(TitleSanPham);
            document.add(SanPham);
            document.add(light3);
            document.add(tongTien3);
            document.add(giamgia);
            document.add(ThanhTien);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbotrangthai = new javax.swing.JComboBox<>();
        cboHTTT = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoadon = new javax.swing.JTable();
        pnlWebcam = new javax.swing.JPanel();
        btnInHoaDon = new javax.swing.JToggleButton();
        btnAnDanhCloseWebCam = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHDCT = new javax.swing.JTable();

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel1.setText("Tìm kiếm hoá đơn");

        cbotrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "ChuaThanhToan", "DaThanhToan", "DaHuy" }));
        cbotrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotrangthaiActionPerformed(evt);
            }
        });

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Thẻ ngân hàng", "Ví điện tử", "Trực tiếp(Tiền mặt)" }));
        cboHTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHTTTActionPerformed(evt);
            }
        });

        jLabel4.setText("Trạng thái: ");

        jLabel5.setText("Hình thức thanh toán :");

        btnSearch.setText("Tìm Kiếm");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        tblHoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hoá Đơn", "Ngày Tạo", "Ngày Thanh Toán", "Mã NV", "Tổng Tiền", "Tên Khách Hàng", "SĐT", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoadon);

        pnlWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInHoaDon.setBackground(new java.awt.Color(255, 204, 0));
        btnInHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/Download.png"))); // NOI18N
        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnAnDanhCloseWebCam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/TaiTra4.png"))); // NOI18N
        btnAnDanhCloseWebCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnDanhCloseWebCamActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/TaiTra4.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(29, 29, 29)
                                .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnInHoaDon)
                                .addGap(108, 108, 108)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAnDanhCloseWebCam, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAnDanhCloseWebCam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("HOÁ ĐƠN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("HOÁ ĐƠN CHI TIẾT");

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));

        tbHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "Hãng", "Màu", "IMEI", "Chất Lượng", " Số Lượng", "Thành Tiền"
            }
        ));
        tbHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbHDCT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ĐơnHàng", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDCTMouseClicked

    }//GEN-LAST:event_tbHDCTMouseClicked

    private void tblHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonMouseClicked
        int x = tblHoadon.getSelectedRow();
        int maHD = listHD.get(x).getMaHoaDon();
        listHDCT = serviceHDCT.getAllHoaDonChiTiet(maHD);
        fillToTableHoaDonChiTiet(listHDCT);
    }//GEN-LAST:event_tblHoadonMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked

    }//GEN-LAST:event_btnSearchMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate

    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void cbotrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangthaiActionPerformed
        if (cbotrangthai.getSelectedIndex() == 0) {
            listHD = serviceHD.getAllHoaDon();
            fillToTableHoaDon(listHD);
            return;
        }
        String trangThai = (String) cbotrangthai.getSelectedItem();
        listHD = serviceHD.getAllHoaDonTheoTrangThai(trangThai);
        fillToTableHoaDon(listHD);
    }//GEN-LAST:event_cbotrangthaiActionPerformed

    private void cboHTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHTTTActionPerformed
        if (cboHTTT.getSelectedIndex() == 0) {
            listHD = serviceHD.getAllHoaDon();
            fillToTableHoaDon(listHD);
            return;
        }
        int maHTTT = cboHTTT.getSelectedIndex();
        listHD = serviceHD.getAllHoaDonTheoHTTT(maHTTT);
        fillToTableHoaDon(listHD);
    }//GEN-LAST:event_cboHTTTActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn chứ?", "Xác nhận yêu cầu", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            int x = -1;
            x = tblHoadon.getSelectedRow();
            if (x < 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn hóa đơn muốn in");
                return;
            } else {
                generateAndPrintInvoice();
                JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
            }
        }
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnAnDanhCloseWebCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnDanhCloseWebCamActionPerformed
        stopWbcam();
    }//GEN-LAST:event_btnAnDanhCloseWebCamActionPerformed

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        pnlWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 140));

        executor.execute(this);
    }

    public final void stopWbcam() {
        //executor.shutdown();
        webcam.close();
    }

    public final void openWbcam() {
        webcam.open();
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(BILL1.class.getName()).log(Level.SEVERE, null, ex);
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (result != null) {
                String s = result.getText();
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn lấy thông tin từ hóa đơn " + s + " chứ?", "Xác nhận yêu cầu", JOptionPane.YES_NO_CANCEL_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    int maHD = Integer.parseInt(s);
                    int maHDDau = listHD.get(0).getMaHoaDon();
                    int maHDCuoi = listHD.get(listHD.size() - 1).getMaHoaDon();
                    int khoangCach = maHDDau - maHD;
                    int vtTable = khoangCach;
                    listHDCT = serviceHDCT.getAllHoaDonChiTiet(maHD);
                    fillToTableHoaDonChiTiet(listHDCT);
                    tblHoadon.setRowSelectionInterval(vtTable, vtTable);
                }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnDanhCloseWebCam;
    private javax.swing.JToggleButton btnInHoaDon;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JComboBox<String> cbotrangthai;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JTable tbHDCT;
    private javax.swing.JTable tblHoadon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
