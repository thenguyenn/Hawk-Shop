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

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.HTTT;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.Imei;
import model.KhachHang;
import model.KhuyenMai;
import model.NhanVien;
import model.SanPhamHD;
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
 * @author khuat
 */
public class BanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

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

    public BanHang() {
        initComponents();
        listSanPhamHD = serviceSanPhamHD.getAllSanPhamHD();
        fillToTablSanPhamHD(listSanPhamHD);
        listKH = serviceKH.getAll();
        fillToComboboxKhachHang(listKH);
        listNV = serviceNV.getAllNhanVien();
        fillToComboboxNhanVien(listNV);
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        listHTTT = serviceHTTT.getAllHTTT();
        fillToComboboxHTTT(listHTTT);
        listKhuyenMai = serviceKhuyenMai.getAllKhuyenMai();
        fillToComboboxKhuyenMai(listKhuyenMai);
        initWebcam();
    }

    public void fillToTablSanPhamHD(List<SanPhamHD> list) {
        DefaultTableModel tblModel = new DefaultTableModel();
        String cols[] = {"MaSPCT", "MaSP", "Tên SP", "Số lượng", "Gía", "RAM", "ROM", "Pin", "Hệ điều hành",
            "Thương hiệu", "Chất liệu", "Màu Sắc"};
        tblModel.setColumnIdentifiers(cols);
        tblModel.setRowCount(0);
        for (SanPhamHD sphd : list) {
            tblModel.addRow(new Object[]{sphd.getMaSPCT(), sphd.getMaSP(), sphd.getTenSP(), sphd.getSoLuong(),
                sphd.getGia(), sphd.getRAM(), sphd.getROM(), sphd.getPin(), sphd.getHeDieuHanh(), sphd.getThuongHieu(),
                sphd.getChatLieu(), sphd.getMauSac()});
        }
        tblSanPhamHD.setModel(tblModel);
    }

    public void fillToTableHoaDon(List<HoaDon> list) {
        DefaultTableModel tblModel = new DefaultTableModel();
        String cols[] = {"MaHD", "NhanVien", "KhachHang", "Tổng tiền", "Giảm giá", "Thành tiền", "trạng thái", "Ngày tạo"};
        tblModel.setColumnIdentifiers(cols);
        tblModel.setRowCount(0);
        for (HoaDon hd : list) {
//            tblModel.addRow(new Object[]{hd.getMaHoaDon(),hd.getMaNhanVien(),hd.getMaKhachHang()
//                    ,hd.getTongTien(),hd.getTrangThai(),hd.getNgayTao()});
            String tenNV = listNV.get(hd.getMaNhanVien() - 1).getTenNV();
            String tenKH = listKH.get(hd.getMaKhachHang() - 1).getTen();
            tblModel.addRow(new Object[]{hd.getMaHoaDon(), tenNV, tenKH,
                hd.getTongTien(), hd.getGiamGia(), hd.getThanhTien(),
                hd.getTrangThai(), hd.getNgayTao()});
        }
        tblHoaDon.setModel(tblModel);
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
        tblGioHang.setModel(tblModel);
    }

    public void fillToComboboxKhachHang(List<KhachHang> list) {
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        for (int i = 0; i < list.size(); i++) {
            cboModel.addElement(list.get(i).getId());
        }
        cboKhachHang.setModel(cboModel);
    }

    public void fillToComboboxNhanVien(List<NhanVien> list) {
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        for (int i = 0; i < list.size(); i++) {
            cboModel.addElement(list.get(i).getMaNV());
        }
        cboNhanVien.setModel(cboModel);
    }

    public void fillToComboboxHTTT(List<HTTT> list) {
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        for (int i = 0; i < list.size(); i++) {
            cboModel.addElement(list.get(i).getTenHTTTT());
        }
        cboHTTT.setModel(cboModel);
    }

    public void fillToComboboxKhuyenMai(List<KhuyenMai> list) {
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        cboModel.addElement("Không dùng");
        for (int i = 0; i < list.size(); i++) {
            cboModel.addElement(list.get(i).getMaKhuyenMai());
        }
        cboKhuyenMai.setModel(cboModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpl125 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSanPham = new javax.swing.JButton();
        btnXoaTat = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        HoTen = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboHTTT = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        cboKhachHang = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboNhanVien = new javax.swing.JComboBox<>();
        lblTenKH = new javax.swing.JLabel();
        lblSDTKH = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamHD = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        pnlWebcam2 = new javax.swing.JPanel();
        btnAnDanhCloseWebcam = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jpl125.setBackground(new java.awt.Color(153, 255, 255));
        jpl125.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));
        jpl125.setName(""); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Mã NV", "Tên KH", "Trạng Thái", "Ngày Tạo"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon);

        btnTaoHoaDon.setBackground(new java.awt.Color(255, 204, 0));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpl125Layout = new javax.swing.GroupLayout(jpl125);
        jpl125.setLayout(jpl125Layout);
        jpl125Layout.setHorizontalGroup(
            jpl125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl125Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                    .addGroup(jpl125Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpl125Layout.setVerticalGroup(
            jpl125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpl125Layout.createSequentialGroup()
                .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ Hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành Tiền", "Trạng Thái"
            }
        ));
        jScrollPane5.setViewportView(tblGioHang);

        btnXoaSanPham.setBackground(new java.awt.Color(255, 204, 0));
        btnXoaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaSanPham.setText("Xóa Sản Phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnXoaTat.setBackground(new java.awt.Color(255, 204, 0));
        btnXoaTat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaTat.setText("Xóa tất cả");
        btnXoaTat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btnXoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128)
                .addComponent(btnXoaTat, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSanPham)
                    .addComponent(btnXoaTat)))
        );

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo Hóa Đơn"));
        jPanel7.setForeground(new java.awt.Color(153, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HoTen.setText("Ten KH: ");
        jPanel7.add(HoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 58, -1, -1));
        jPanel7.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 190, 269, 10));

        jLabel23.setText("Tổng Tiền  :");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel24.setText("Tiền Giảm  :");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel25.setText("Thành Tiền  :");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien.setText("0");
        jPanel7.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 198, -1));

        lblGiamGia.setText("0");
        jPanel7.add(lblGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 191, -1));

        lblThanhTien.setText("0");
        jPanel7.add(lblThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 193, -1));

        jLabel11.setText("HT ThanhToán");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, -1));

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn phương thức--", "Chuyển khoản", "Tiền Mặt" }));
        jPanel7.add(cboHTTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 140, -1));

        jLabel12.setText("Tiền Khách Đưa");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        jLabel14.setText("Tiền Thừa");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        lblTienThua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(255, 0, 0));
        lblTienThua.setText("jLabel15");
        jPanel7.add(lblTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 174, -1));
        jPanel7.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 460, 263, -1));

        btnThanhToan.setBackground(new java.awt.Color(51, 255, 0));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel7.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 200, 90));

        btnHuy.setBackground(new java.awt.Color(255, 51, 0));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setText("Hủy ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel7.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 150, 47));

        jLabel26.setText("Mã KH");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 27, -1, -1));

        jLabel28.setText("SDT");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 86, -1, -1));

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTienKhachDua.setForeground(new java.awt.Color(255, 0, 0));
        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        jPanel7.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, 112, -1));

        jLabel29.setText("Mã Hóa Đơn");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaHoaDon.setText("jLabel30");
        jPanel7.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 110, 23));

        jLabel15.setText("Mã KM  :");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        cboKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không dùng", "KM001", "KM002", "KM003" }));
        cboKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhuyenMaiActionPerformed(evt);
            }
        });
        jPanel7.add(cboKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 123, -1));

        cboKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhachHangActionPerformed(evt);
            }
        });
        jPanel7.add(cboKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 24, 129, -1));

        jLabel4.setText("Mã NV");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 119, -1, -1));

        cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhanVienActionPerformed(evt);
            }
        });
        jPanel7.add(cboNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 116, 135, -1));

        lblTenKH.setText("jLabel5");
        jPanel7.add(lblTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 117, 30));

        lblSDTKH.setText("jLabel5");
        jPanel7.add(lblSDTKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 117, 24));

        jLabel1.setText("Ten NV");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        lblTenNV.setText("jLabel2");
        jPanel7.add(lblTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 130, 20));

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Sản Phẩm"));

        tblSanPhamHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPhamHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamHD);

        btnTimKiem.setBackground(new java.awt.Color(255, 204, 0));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem)
                .addGap(151, 151, 151))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTimKiem)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlWebcam2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAnDanhCloseWebcam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/TaiTra4.png"))); // NOI18N
        btnAnDanhCloseWebcam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnDanhCloseWebcamActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/TaiPhai4.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jpl125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlWebcam2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAnDanhCloseWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAnDanhCloseWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlWebcam2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jpl125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        HoaDon hd = listHD.get(index);
        lblMaHoaDon.setText(String.valueOf(hd.getMaHoaDon()));
        String HoTenNV = listNV.get(hd.getMaNhanVien() - 1).getTenNV();
        String HoTenKH = listKH.get(hd.getMaKhachHang() - 1).getTen();
        //System.out.println(HoTenNV+" "+HoTenKH);
        cboNhanVien.setSelectedItem(HoTenNV);
        cboKhachHang.setSelectedItem(HoTenKH);
        listHDCT = serviceHDCT.getAllHoaDonChiTiet(Integer.parseInt(lblMaHoaDon.getText()));
        fillToTableHoaDonChiTiet(listHDCT);
        lblTongTien.setText(String.valueOf(hd.getTongTien()));
        lblGiamGia.setText(String.valueOf(hd.getGiamGia()));
        lblThanhTien.setText(String.valueOf(hd.getThanhTien()));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnXoaTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatActionPerformed
        DefaultTableModel tblModel = new DefaultTableModel();
        tblModel.setRowCount(0);
    }//GEN-LAST:event_btnXoaTatActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (lblMaHoaDon.getText().equals("jLabel30") || lblMaHoaDon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn không thể thanh toán vì không thấy hóa đơn");
            return;
        }
        if (txtTienKhachDua.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn không thể thanh toán vì Tiền khách đưa bé hơn thành tiền ");
            return;
        }
        float tienKhachDua = Float.parseFloat(txtTienKhachDua.getText());
        float thanhTien = Float.parseFloat(lblThanhTien.getText());
        if (tienKhachDua < thanhTien) {
            JOptionPane.showMessageDialog(this, "Bạn không thể thanh toán vì Tiền khách đưa bé hơn thành tiền ");
            return;
        }
        int maHD = Integer.parseInt(lblMaHoaDon.getText());
        String maKM = "";
        if (cboKhuyenMai.getSelectedIndex() > 0) {
            maKM = (String) cboKhuyenMai.getSelectedItem();
        }
        int maHTTT = cboHTTT.getSelectedIndex() + 1;
        serviceHD.updateTrangThaiTT(maHD, "DaThanhToan");
        serviceHD.updateKhuyenMaiHTTT(maKM, maHTTT, maHD);
        JOptionPane.showMessageDialog(this, "Thanh toán thành cong");
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        listHDCT = new ArrayList<>();
        fillToTableHoaDonChiTiet(listHDCT);
        lblMaHoaDon.setText("");
        lblTongTien.setText("");
        lblThanhTien.setText("");
        txtTienKhachDua.setText("");
        lblTienThua.setText("");
        lblGiamGia.setText("");
        lblTenNV.setText("");
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int MaNV = listNV.get(cboNhanVien.getSelectedIndex()).getMaNV();
        int MaKH = listKH.get(cboKhachHang.getSelectedIndex()).getId();
        JOptionPane.showMessageDialog(this, serviceHD.insertHoaDon(MaNV, MaKH));
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        tblHoaDon.setRowSelectionInterval(0, 0);
        listHDCT = serviceHDCT.getAllHoaDonChiTiet(listHD.get(0).getMaHoaDon());
        fillToTableHoaDonChiTiet(listHDCT);
        lblMaHoaDon.setText(String.valueOf(listHD.get(0).getMaHoaDon()));
        lblTongTien.setText("");
        lblThanhTien.setText("");
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (lblMaHoaDon.getText().equals("jLabel30") || lblMaHoaDon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn không thể huy vì không thấy hóa đơn");
            return;
        }
        int maHD = Integer.parseInt(lblMaHoaDon.getText());
        serviceHD.updateTrangThaiTT(maHD, "DaHuy");
        JOptionPane.showMessageDialog(this, "Da huy thành cong");
        for (int i = 0; i < listHDCT.size(); i++) {
            int maImei = serviceImei.getMaImeiTheoTen(listHDCT.get(i).getTenImei());
            serviceImei.UpdateTrangThaiIMEI(maImei, 1);
            serviceSanPhamHD.UpdateSoLuongSP(listHDCT.get(i).getMaSPCT(), -1);
        }
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        listSanPhamHD = serviceSanPhamHD.getAllSanPhamHD();
        fillToTablSanPhamHD(listSanPhamHD);
        listHDCT = new ArrayList<>();
        fillToTableHoaDonChiTiet(listHDCT);
        lblMaHoaDon.setText("");
        lblTongTien.setText("");
        lblThanhTien.setText("");
        txtTienKhachDua.setText("");
        lblTienThua.setText("");
        lblGiamGia.setText("");
        lblTenNV.setText("");
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        float tienKhachDua = Float.parseFloat(txtTienKhachDua.getText());
        float thanhTien = Float.parseFloat(lblThanhTien.getText());
        lblTienThua.setText(String.valueOf(tienKhachDua - thanhTien));
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void cboKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhuyenMaiActionPerformed
        if (lblMaHoaDon.equals("jLabel30") || lblMaHoaDon.equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn hóa đơn trước khi chọn khuyến mại");
            return;
        }
        int x = cboKhuyenMai.getSelectedIndex();
        int maHD = Integer.parseInt(lblMaHoaDon.getText());
        float tongTien = Float.parseFloat(lblTongTien.getText());
        float giamGia = 0;
        int vtHD = tblHoaDon.getSelectedRow();
        float thanhTien = tongTien;
        if (x > 0) {
            int giaTri = listKhuyenMai.get(x - 1).getGiaTri();
            System.out.println(" " + giaTri);
            giamGia = (tongTien * giaTri) / 100;
            thanhTien = tongTien - giamGia;
        }
        serviceHD.updateTien(tongTien, giamGia, thanhTien, maHD);
        lblTongTien.setText(String.valueOf(tongTien));
        lblGiamGia.setText(String.valueOf(giamGia));
        lblThanhTien.setText(String.valueOf(thanhTien));
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        tblHoaDon.setRowSelectionInterval(vtHD, vtHD);
    }//GEN-LAST:event_cboKhuyenMaiActionPerformed

    private void cboKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhachHangActionPerformed
        int x = cboKhachHang.getSelectedIndex();
        KhachHang kh = listKH.get(x);
        lblTenKH.setText(kh.getTen());
        lblSDTKH.setText(kh.getSdt());
    }//GEN-LAST:event_cboKhachHangActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblSanPhamHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamHDMouseClicked
        if (lblMaHoaDon.equals("jLabel30") || lblMaHoaDon.equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn phải chọn hóa đơn trước khi thêm sản phẩm");
            return;
        }
        int index = tblSanPhamHD.getSelectedRow();
        int vtHD = tblHoaDon.getSelectedRow();
        SanPhamHD sphd = listSanPhamHD.get(index);
        listImei = serviceImei.getAllImeiTheoMaSPCT(sphd.getMaSPCT());
        String[] items = new String[listImei.size()];
        for (int i = 0; i < listImei.size(); i++) {
            items[i] = listImei.get(i).getTenImei();
        }

        JComboBox<String> comboBox = new JComboBox<>(items);
        String selectedValue = (String) JOptionPane.showInputDialog(
                null,
                "Chọn imei",
                "Chọn 1 imei",
                JOptionPane.PLAIN_MESSAGE,
                null,
                items, // Danh sách các phần tử trong JComboBox
                items[0] // Phần tử mặc định được chọn ban đầu
        );
        if (selectedValue == null) {
            return;
        }
        String tenImei = selectedValue;
        int maHD = Integer.parseInt(lblMaHoaDon.getText());
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setMaHoaDon(maHD);
        hdct.setMaSPCT(sphd.getMaSPCT());
        hdct.setTenImei(tenImei);
        hdct.setTenSP(sphd.getTenSP());
        hdct.setGia(sphd.getGia());
        hdct.setThanhTien(sphd.getGia());
        JOptionPane.showMessageDialog(this, serviceHDCT.insertHoaDonChiTiet(hdct));
        listHDCT = serviceHDCT.getAllHoaDonChiTiet(maHD);
        fillToTableHoaDonChiTiet(listHDCT);

        int MaImei = serviceImei.getMaImeiTheoTen(selectedValue);
        System.out.println("MaIMei: " + MaImei);

        serviceImei.UpdateTrangThaiIMEI(MaImei, 2);
        serviceSanPhamHD.UpdateSoLuongSP(sphd.getMaSPCT(), 1);
        float tongTien = 0;
        for (HoaDonChiTiet hdct1 : listHDCT) {
            tongTien = tongTien + hdct1.getGia();
        }
        float giamGia = Float.parseFloat(lblGiamGia.getText());
        float thanhTien = tongTien - giamGia;
        serviceHD.updateTien(tongTien, giamGia, thanhTien, maHD);
        lblTongTien.setText(String.valueOf(tongTien));
        lblGiamGia.setText(String.valueOf(giamGia));
        lblThanhTien.setText(String.valueOf(thanhTien));
        listSanPhamHD = serviceSanPhamHD.getAllSanPhamHD();
        fillToTablSanPhamHD(listSanPhamHD);
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        tblHoaDon.setRowSelectionInterval(vtHD, vtHD);
    }//GEN-LAST:event_tblSanPhamHDMouseClicked

    private void cboNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhanVienActionPerformed
        NhanVien nv = listNV.get(cboNhanVien.getSelectedIndex());
        lblTenNV.setText(nv.getTenNV());
    }//GEN-LAST:event_cboNhanVienActionPerformed

    private void btnAnDanhCloseWebcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnDanhCloseWebcamActionPerformed
        stopWbcam();
    }//GEN-LAST:event_btnAnDanhCloseWebcamActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        stopWbcam();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        pnlWebcam2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 140));

        executor.execute(this);
    }

    public final void stopWbcam() {
        //executor.shutdown();
        webcam.close();
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
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn lấy thông tin từ sản phẩm " + s + " chứ?", "Xác nhận yêu cầu", JOptionPane.YES_NO_CANCEL_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    int maSPCT = Integer.parseInt(s);
                    int vtTable = maSPCT - 1;
                    tblSanPhamHD.setRowSelectionInterval(vtTable, vtTable);
                    
                    int index = vtTable;
                    int vtHD = tblHoaDon.getSelectedRow();
                    SanPhamHD sphd = listSanPhamHD.get(index);
                    listImei = serviceImei.getAllImeiTheoMaSPCT(sphd.getMaSPCT());
                    String[] items = new String[listImei.size()];
                    for (int i = 0; i < listImei.size(); i++) {
                        items[i] = listImei.get(i).getTenImei();
                    }

                    JComboBox<String> comboBox = new JComboBox<>(items);
                    String selectedValue = (String) JOptionPane.showInputDialog(
                            null,
                            "Chọn imei",
                            "Chọn 1 imei",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            items, // Danh sách các phần tử trong JComboBox
                            items[0] // Phần tử mặc định được chọn ban đầu
                    );
                    if (selectedValue == null) {
                        return;
                    }
                    String tenImei = selectedValue;
                    int maHD = Integer.parseInt(lblMaHoaDon.getText());
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setMaHoaDon(maHD);
                    hdct.setMaSPCT(sphd.getMaSPCT());
                    hdct.setTenImei(tenImei);
                    hdct.setTenSP(sphd.getTenSP());
                    hdct.setGia(sphd.getGia());
                    hdct.setThanhTien(sphd.getGia());
                    JOptionPane.showMessageDialog(this, serviceHDCT.insertHoaDonChiTiet(hdct));
                    listHDCT = serviceHDCT.getAllHoaDonChiTiet(maHD);
                    fillToTableHoaDonChiTiet(listHDCT);

                    int MaImei = serviceImei.getMaImeiTheoTen(selectedValue);
                    System.out.println("MaIMei: " + MaImei);

                    serviceImei.UpdateTrangThaiIMEI(MaImei, 2);
                    serviceSanPhamHD.UpdateSoLuongSP(sphd.getMaSPCT(), 1);
                    float tongTien = 0;
                    for (HoaDonChiTiet hdct1 : listHDCT) {
                        tongTien = tongTien + hdct1.getGia();
                    }
                    float giamGia = Float.parseFloat(lblGiamGia.getText());
                    float thanhTien = tongTien - giamGia;
                    serviceHD.updateTien(tongTien, giamGia, thanhTien, maHD);
                    lblTongTien.setText(String.valueOf(tongTien));
                    lblGiamGia.setText(String.valueOf(giamGia));
                    lblThanhTien.setText(String.valueOf(thanhTien));
                    listSanPhamHD = serviceSanPhamHD.getAllSanPhamHD();
                    fillToTablSanPhamHD(listSanPhamHD);
                    listHD = serviceHD.getAllHoaDonCho();
                    fillToTableHoaDon(listHD);
                    tblHoaDon.setRowSelectionInterval(vtHD, vtHD);
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
    private javax.swing.JLabel HoTen;
    private javax.swing.JButton btnAnDanhCloseWebcam;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btnXoaTat;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JComboBox<String> cboKhachHang;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JComboBox<String> cboNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel jpl125;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblSDTKH;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlWebcam2;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPhamHD;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
