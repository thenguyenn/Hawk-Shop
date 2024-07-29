/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.Imei;
import model.KhachHang;
import model.NhanVien;
import model.SanPhamHD;
import service.HoaDonChiTietService;
import service.HoaDonService;
import service.ImeiService;
import service.KhachHangService;
import service.NhanVienService;
import service.SanPhamHDService;

/**
 *
 * @author khuat
 */
public class MainFraimUI extends javax.swing.JFrame {

    List<SanPhamHD> listSanPhamHD = new ArrayList<>();
    List<KhachHang> listKH = new ArrayList<>();
    List<NhanVien> listNV = new ArrayList<>();
    List<HoaDon> listHD = new ArrayList<>();
    List<Imei> listImei = new ArrayList<>();
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();

    SanPhamHDService serviceSanPhamHD = new SanPhamHDService();
    KhachHangService serviceKH = new KhachHangService();
    NhanVienService serviceNV = new NhanVienService();
    HoaDonService serviceHD = new HoaDonService();
    ImeiService serviceImei = new ImeiService();
    HoaDonChiTietService serviceHDCT = new HoaDonChiTietService();

    public MainFraimUI() {
        initComponents();
        listSanPhamHD = serviceSanPhamHD.getAllSanPhamHD();
        fillToTablSanPhamHD(listSanPhamHD);
        listKH = serviceKH.getAll();
        fillToComboboxKhachHang(listKH);
        listNV = serviceNV.getAllNhanVien();
        fillToComboboxNhanVien(listNV);
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
    }

    public void fillToTablSanPhamHD(List<SanPhamHD> list) {
        DefaultTableModel tblModel = new DefaultTableModel();
        String cols[] = {"MaSPCT", "MaSP", "Tên SP", "Số lượng", "Gía", "Nội dung", "Hệ điều hành",
            "Thương hiệu", "Chất liệu", "Màu Sắc"};
        tblModel.setColumnIdentifiers(cols);
        tblModel.setRowCount(0);
        for (SanPhamHD sphd : list) {
            tblModel.addRow(new Object[]{sphd.getMaSPCT(), sphd.getMaSP(), sphd.getTenSP(), sphd.getSoLuong(),
                sphd.getGia(), sphd.getNoiDung(), sphd.getHeDieuHanh(), sphd.getThuongHieu(),
                sphd.getChatLieu(), sphd.getMauSac()});
        }
        tblSanPhamHD.setModel(tblModel);
    }

    public void fillToTableHoaDon(List<HoaDon> list) {
        DefaultTableModel tblModel = new DefaultTableModel();
        String cols[] = {"MaHD", "NhanVien", "KhachHang", "Tổng tiền", "trạng thái", "Ngày tạo"};
        tblModel.setColumnIdentifiers(cols);
        tblModel.setRowCount(0);
        for (HoaDon hd : list) {
//            tblModel.addRow(new Object[]{hd.getMaHoaDon(),hd.getMaNhanVien(),hd.getMaKhachHang()
//                    ,hd.getTongTien(),hd.getTrangThai(),hd.getNgayTao()});
            String tenNV = listNV.get(hd.getMaNhanVien() - 1).getTenNV();
            String tenKH = listKH.get(hd.getMaKhachHang() - 1).getTen();
            tblModel.addRow(new Object[]{hd.getMaHoaDon(), tenNV, tenKH,
                hd.getTongTien(), hd.getTrangThai(), hd.getNgayTao()});
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
            cboModel.addElement(list.get(i).getTen());
        }
        cboKhachHang.setModel(cboModel);
    }

    public void fillToComboboxNhanVien(List<NhanVien> list) {
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        for (int i = 0; i < list.size(); i++) {
            cboModel.addElement(list.get(i).getTenNV());
        }
        cboNhanVien.setModel(cboModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlView = new javax.swing.JPanel();
        panelHoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpl125 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSanPham = new javax.swing.JButton();
        btnXoaTat = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamHD = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnHuyTK = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTienGiam = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboHTTT = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnThanhToan = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboMaKhuyenMai = new javax.swing.JComboBox<>();
        cboKhachHang = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cboNhanVien = new javax.swing.JComboBox<>();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        logoApp = new javax.swing.JLabel();
        NameStore = new javax.swing.JLabel();
        pnlSanPham = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        lblNhanVien = new javax.swing.JLabel();
        pnlKhachHang = new javax.swing.JPanel();
        lblKhachHang = new javax.swing.JLabel();
        pnlGiamGia = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        pnlTrangChu = new javax.swing.JPanel();
        lblTrangChu = new javax.swing.JLabel();
        pnlUser = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        vaitro = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        pnlThongKe = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlView.setBackground(new java.awt.Color(255, 255, 255));

        panelHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        panelHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout jpl125Layout = new javax.swing.GroupLayout(jpl125);
        jpl125.setLayout(jpl125Layout);
        jpl125Layout.setHorizontalGroup(
            jpl125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl125Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpl125Layout.setVerticalGroup(
            jpl125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl125Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ Hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành Tiền", "Trạng Thái"
            }
        ));
        jScrollPane5.setViewportView(tblGioHang);

        btnXoaSanPham.setText("Xóa Sản Phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

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
                .addContainerGap(224, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSanPham)
                    .addComponent(btnXoaTat)))
        );

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

        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnHuyTK.setText("Hủy");
        btnHuyTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyTKActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHuyTK)
                .addGap(67, 67, 67))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyTK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo Hóa Đơn"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setText("SDT");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 58, -1, -1));
        jPanel7.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 150, 269, 7));

        jLabel23.setText("Tổng Tiền");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 210, -1, -1));

        jLabel24.setText("Tiền Giảm");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 275, -1, -1));

        jLabel25.setText("Thành Tiền");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 309, -1, -1));

        lblTongTien.setText("jLabel8");
        jPanel7.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 210, 198, -1));

        lblTienGiam.setText("jLabel9");
        jPanel7.add(lblTienGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 275, 191, -1));

        lblThanhTien.setText("jLabel10");
        jPanel7.add(lblThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 309, 193, -1));

        jLabel11.setText("HT ThanhToán");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 349, -1, -1));

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn phương thức--", "Chuyển khoản", "Tiền Mặt" }));
        jPanel7.add(cboHTTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 343, -1, -1));

        jLabel12.setText("Tiền Khách Đưa");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 374, -1, -1));

        jLabel14.setText("Tiền Thừa");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 399, -1, -1));

        lblTienThua.setText("jLabel15");
        jPanel7.add(lblTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 399, 174, -1));
        jPanel7.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 433, 263, -1));

        btnThanhToan.setBackground(new java.awt.Color(51, 153, 255));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel7.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 442, 145, 44));

        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });
        jPanel7.add(btnTaoHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 498, 136, 47));

        btnHuy.setText("Hủy ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel7.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 498, 115, 47));

        jLabel26.setText("Tên KH");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 27, -1, -1));

        jLabel28.setText("Địa Chỉ");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 86, -1, -1));

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });
        jPanel7.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 371, 112, -1));

        jLabel29.setText("Mã Hóa Đơn");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 175, -1, -1));

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaHoaDon.setText("jLabel30");
        jPanel7.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 175, 110, 23));

        jLabel15.setText("Mã KM");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 241, -1, -1));

        cboMaKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không dùng", "KM001", "KM002", "KM003" }));
        cboMaKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaKhuyenMaiActionPerformed(evt);
            }
        });
        jPanel7.add(cboMaKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 238, 123, -1));

        cboKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhachHangActionPerformed(evt);
            }
        });
        jPanel7.add(cboKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 24, 129, -1));

        jLabel4.setText("NhanVien");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 119, -1, -1));

        cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cboNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 116, 135, -1));

        lblSDT.setText("jLabel5");
        jPanel7.add(lblSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 58, 117, 22));

        lblDiaChi.setText("jLabel5");
        jPanel7.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 86, 117, 24));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpl125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jpl125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout panelHoaDonLayout = new javax.swing.GroupLayout(panelHoaDon);
        panelHoaDon.setLayout(panelHoaDonLayout);
        panelHoaDonLayout.setHorizontalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1062, Short.MAX_VALUE)
            .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelHoaDonLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelHoaDonLayout.setVerticalGroup(
            panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(panelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelHoaDonLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(56, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlViewLayout = new javax.swing.GroupLayout(pnlView);
        pnlView.setLayout(pnlViewLayout);
        pnlViewLayout.setHorizontalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1106, Short.MAX_VALUE)
            .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlViewLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );
        pnlViewLayout.setVerticalGroup(
            pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
            .addGroup(pnlViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlViewLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlMenu.setBackground(new java.awt.Color(153, 153, 255));

        jSeparator1.setForeground(new java.awt.Color(51, 255, 0));

        logoApp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/Riverhawks-MainLogo-1568x1582.png"))); // NOI18N

        NameStore.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        NameStore.setForeground(new java.awt.Color(246, 185, 59));
        NameStore.setText("HAWK SHOP");

        pnlSanPham.setBackground(new java.awt.Color(153, 153, 255));
        pnlSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSanPhamMouseClicked(evt);
            }
        });

        lblSanPham.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/Mobile phone.png"))); // NOI18N
        lblSanPham.setText("SẢN PHẨM");

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlNhanVien.setBackground(new java.awt.Color(153, 153, 255));

        lblNhanVien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/nhanvien.png"))); // NOI18N
        lblNhanVien.setText("NHÂN VIÊN");

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlKhachHang.setBackground(new java.awt.Color(153, 153, 255));

        lblKhachHang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/khachhang.png"))); // NOI18N
        lblKhachHang.setText("KHÁCH HÀNG");

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlGiamGia.setBackground(new java.awt.Color(153, 153, 255));

        lblHoaDon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/Label.png"))); // NOI18N
        lblHoaDon.setText("GIẢM GIÁ");

        javax.swing.GroupLayout pnlGiamGiaLayout = new javax.swing.GroupLayout(pnlGiamGia);
        pnlGiamGia.setLayout(pnlGiamGiaLayout);
        pnlGiamGiaLayout.setHorizontalGroup(
            pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlGiamGiaLayout.setVerticalGroup(
            pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlHoaDon.setBackground(new java.awt.Color(153, 153, 255));

        lblThongKe.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/hoadon.png"))); // NOI18N
        lblThongKe.setText("HÓA ĐƠN");
        lblThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThongKeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlTrangChu.setBackground(new java.awt.Color(153, 153, 255));
        pnlTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTrangChuMouseClicked(evt);
            }
        });

        lblTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/Home.png"))); // NOI18N
        lblTrangChu.setText("TRANG CHỦ");

        javax.swing.GroupLayout pnlTrangChuLayout = new javax.swing.GroupLayout(pnlTrangChu);
        pnlTrangChu.setLayout(pnlTrangChuLayout);
        pnlTrangChuLayout.setHorizontalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTrangChuLayout.setVerticalGroup(
            pnlTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        pnlUser.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("User    :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Vai trò :");

        user.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        vaitro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseExited(evt);
            }
        });
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vaitro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(btnDangXuat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(vaitro, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlThongKe.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/thongke.png"))); // NOI18N
        jLabel3.setText("THỐNG KÊ");

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(pnlSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTrangChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoApp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameStore))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoApp, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameStore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnlSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlSanPhamMouseClicked

    private void lblThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThongKeMouseClicked

    }//GEN-LAST:event_lblThongKeMouseClicked

    private void pnlTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrangChuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlTrangChuMouseClicked

    private void btnDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseEntered
        btnDangXuat.setBackground(new java.awt.Color(249, 7, 22));
    }//GEN-LAST:event_btnDangXuatMouseEntered

    private void btnDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseExited
        btnDangXuat.setBackground(new java.awt.Color(255, 255, 255));
    }//GEN-LAST:event_btnDangXuatMouseExited

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed

    }//GEN-LAST:event_btnDangXuatActionPerformed

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
        lblThanhTien.setText(String.valueOf(hd.getTongTien()));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed

    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnXoaTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatActionPerformed

    }//GEN-LAST:event_btnXoaTatActionPerformed

    private void tblSanPhamHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamHDMouseClicked
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
        //serviceHD.updateTongTien(tongTien, maHD);
        lblTongTien.setText(String.valueOf(tongTien));
        lblThanhTien.setText(String.valueOf(tongTien));
        listSanPhamHD = serviceSanPhamHD.getAllSanPhamHD();
        fillToTablSanPhamHD(listSanPhamHD);
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        tblHoaDon.setRowSelectionInterval(vtHD, vtHD);

    }//GEN-LAST:event_tblSanPhamHDMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnHuyTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyTKActionPerformed

    }//GEN-LAST:event_btnHuyTKActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (lblMaHoaDon.getText().equals("jLabel30") || lblMaHoaDon.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn không thể thanh toán vì không thấy hóa đơn");
            return;
        }
        if(txtTienKhachDua.getText().equals("")){
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
        serviceHD.updateTrangThaiTT(maHD,"DaThanhToan");
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
        serviceHD.updateTrangThaiTT(maHD,"DaHuy");
        JOptionPane.showMessageDialog(this, "Da huy thành cong");
        for (int i=0;i<listHDCT.size();i++) {
            int maImei=serviceImei.getMaImeiTheoTen(listHDCT.get(i).getTenImei());
            serviceImei.UpdateTrangThaiIMEI(maImei, 1);
            serviceSanPhamHD.UpdateSoLuongSP(listHDCT.get(i).getMaSPCT(), -1);
        }
        listHD = serviceHD.getAllHoaDonCho();
        fillToTableHoaDon(listHD);
        listSanPhamHD=serviceSanPhamHD.getAllSanPhamHD();
        fillToTablSanPhamHD(listSanPhamHD);
        listHDCT = new ArrayList<>();
        fillToTableHoaDonChiTiet(listHDCT);
        lblMaHoaDon.setText("");
        lblTongTien.setText("");
        lblThanhTien.setText("");
        txtTienKhachDua.setText("");
        lblTienThua.setText("");
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        float tienKhachDua = Float.parseFloat(txtTienKhachDua.getText());
        float thanhTien = Float.parseFloat(lblThanhTien.getText());
        lblTienThua.setText(String.valueOf(tienKhachDua - thanhTien));
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void cboMaKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaKhuyenMaiActionPerformed

    }//GEN-LAST:event_cboMaKhuyenMaiActionPerformed

    private void cboKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhachHangActionPerformed
        int x = cboKhachHang.getSelectedIndex();
        KhachHang kh = listKH.get(x);
        lblSDT.setText(kh.getSdt());
        lblDiaChi.setText(kh.getDiaChi());
    }//GEN-LAST:event_cboKhachHangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFraimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFraimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFraimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFraimUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFraimUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameStore;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuyTK;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btnXoaTat;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JComboBox<String> cboKhachHang;
    private javax.swing.JComboBox<String> cboMaKhuyenMai;
    private javax.swing.JComboBox<String> cboNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel jpl125;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblTienGiam;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel logoApp;
    private javax.swing.JPanel panelHoaDon;
    private javax.swing.JPanel pnlGiamGia;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlTrangChu;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JPanel pnlView;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPhamHD;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JLabel user;
    private javax.swing.JLabel vaitro;
    // End of variables declaration//GEN-END:variables
}
