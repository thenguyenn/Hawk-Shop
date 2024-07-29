/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import model.voucher;
import repository.DBConnection;
import service.Voucher_service;

/**
 *
 * @author khuat
 */
public class Vouchers extends javax.swing.JPanel {

    Voucher_service sr = new Voucher_service();
    List<voucher> list = new ArrayList<>();
    int row = -1;
    DefaultTableModel model = new DefaultTableModel();

    public Vouchers() {
        initComponents();
        model = (DefaultTableModel) tblbangvoucher.getModel();
        FillTable(sr.getVoucherAll());
        init();
    }

    public void FillTable(List<voucher> lists) {
        model.setRowCount(0);
        for (voucher s : lists) {
            String vt = "";
            if (s.getTrangThai() == 1) {
                vt = "Đang Hoạt Động";
            } else {
                vt = "Ngừng Hoạt Động";
            }
            model.addRow(new Object[]{
                tblbangvoucher.getRowCount() + 1,
                s.getMa(),
                s.getTen(),
                s.getSoLuong(),
                s.getGiaTri(),
                s.getNgayBatDau(),
                s.getNgayKetThuc(),
                vt
            });
        }
    }

    public void showData() {
        row = tblbangvoucher.getSelectedRow();
        if (row >= 0) {
            txtma.setText(tblbangvoucher.getValueAt(row, 1).toString());
            txtten.setText(tblbangvoucher.getValueAt(row, 2).toString());
            txtgiatri.setText(tblbangvoucher.getValueAt(row, 3).toString());
            txtsoluong.setText(tblbangvoucher.getValueAt(row, 4).toString());
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdfs = new SimpleDateFormat("dd/MM/yyyy");
                Date ngay_bat_dau = sdf.parse(tblbangvoucher.getValueAt(row, 5).toString());

                datebatdau.setDate(ngay_bat_dau);
                Date ngay_ket_thuc = sdf.parse(tblbangvoucher.getValueAt(row, 6).toString());
                String nss = sdfs.format(ngay_ket_thuc);
                dateketthuc.setDate(ngay_ket_thuc);
            } catch (Exception e) {
            }
//            Date Date_of_Birth = new Date();
//            Date Today_Date = new Date();
//            SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
//
//            try {
//                Date_of_Birth = fomat.parse(fomat.format(datebatdau.getDate()));
//                Today_Date = fomat.parse(fomat.format(dateketthuc.getDate()));
//
//            } catch (Exception e) {
//            }
//
//            long Datediff = Today_Date.getTime() - Date_of_Birth.getTime();
//            long hours = Datediff / (60 * 60 * 1000);
//            long minutes = hours * 60;
            voucher n = sr.getVoucherAll().get(row);
            int loaiNV = n.getTrangThai();
            final int QUAN_LI = 1;
            final int NHAN_VIEN = 2;

            if (loaiNV == NHAN_VIEN) {
                rdodanghoatjdong.setSelected(false);
                rdongunghoatdong.setSelected(true);
            } else if (loaiNV == QUAN_LI) {
                rdodanghoatjdong.setSelected(true);
                rdongunghoatdong.setSelected(false);
            }

        }
    }

//    voucher Getmodel() {
//
//        try {
//            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM-dd-yyyy");
//
//            Date currentDate = new Date();
//            String text = datebatdau.getToolTipText();
//            String textt = dateketthuc.getToolTipText();
//            voucher v = new voucher();
//            // Assuming datebatdau.getToolTipText() and dateketthuc.getToolTipText() are not null
//            if (text != null) {
//                Date ngay_bat_dau = inputDateFormat.parse(text);
//                String bd = outputDateFormat.format(ngay_bat_dau);
//
//                Date ngay_ket_thuc = inputDateFormat.parse(textt);
//                String kt = outputDateFormat.format(ngay_ket_thuc);
//
//                String batdau = outputDateFormat.format(currentDate);
//                String ketthuc = outputDateFormat.format(currentDate);
//                int loaiNV;
//                if (rdodanghoatjdong.isSelected()) {
//                    loaiNV = 1;
//                } else if (rdongunghoatdong.isSelected()) {
//                    loaiNV = 2;
//                } else {
//                    loaiNV = 0;
//                }
//               String ten = v.getTen();
//                voucher vo = new voucher(Integer.parseInt(txtma.getText()), txtten.getText(), Integer.parseInt(txtsoluong.getText()), Integer.parseInt(txtgiatri.getText()), bd, kt, loaiNV);
//                return vo;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Handle the exception appropriately, log it, or return an error response
//            return null;
//        }
//
//    }
//
    private void clear() {
        txtma.setText("");
        txtten.setText("");
        txtsoluong.setText("");
        txtgiatri.setText("");
        rdodanghoatjdong.setSelected(false);
        rdongunghoatdong.setSelected(false);
        datebatdau.setDateFormatString("");
        dateketthuc.setDateFormatString("");
    }

    public boolean check() {
        if (txtten.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống!");
            return false;
        }
        if (txtsoluong.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống!");
            return false;
        }
        if (txtgiatri.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Giá trị không được để trống!");
            return false;
        }
        if (datebatdau.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được để trống!");
            return false;
        }
        if (dateketthuc.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Ngày Kết thúc không được để trống!");
            return false;
        }
        return true;
    }

//    public void delete() {
//        row = tblbangvoucher.getSelectedRow();
//        String id = tblbangvoucher.getValueAt(row, 1).toString();
//        voucher s = Getmodel();
//        if (sr.deteleUpdate(id) > 0) {
//            JOptionPane.showMessageDialog(this, "Thành công");
//        } else {
//            JOptionPane.showMessageDialog(this, "Không Thành công");
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtten = new javax.swing.JTextField();
        txtsoluong = new javax.swing.JTextField();
        txtgiatri = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtma = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        datebatdau = new com.toedter.calendar.JDateChooser();
        dateketthuc = new com.toedter.calendar.JDateChooser();
        rdodanghoatjdong = new javax.swing.JRadioButton();
        rdongunghoatdong = new javax.swing.JRadioButton();
        btnxoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnlammoi = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();
        lblDongHo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbangvoucher = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Phiếu Giảm Giá");

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã Giảm Giá");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên Giảm Giá");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số lượng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giá Trị");

        txtten.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenActionPerformed(evt);
            }
        });

        txtsoluong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        txtgiatri.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtgiatri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiatriActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Giảm giá");

        txtma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtma.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmaFocusGained(evt);
            }
        });
        txtma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtma)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtgiatri, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtten, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 420, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtten, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtgiatri, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày bắt đầu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ngày kết thúc");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Trạng thái");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Thời Gian Sửa dụng");

        datebatdau.setDateFormatString("yyyy-MM-dd");

        dateketthuc.setDateFormatString("yyyy-MM-dd");

        rdodanghoatjdong.setText("Đang Hoạt Động");

        rdongunghoatdong.setText("Ngừng Hoạt Động");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(datebatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdodanghoatjdong)
                                .addGap(87, 87, 87)
                                .addComponent(rdongunghoatdong)))
                        .addGap(0, 83, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datebatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdodanghoatjdong)
                    .addComponent(rdongunghoatdong))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnxoa.setBackground(new java.awt.Color(255, 255, 0));
        btnxoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoa.setForeground(new java.awt.Color(0, 102, 255));
        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/delete-button.png"))); // NOI18N
        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnsua.setBackground(new java.awt.Color(255, 255, 0));
        btnsua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnsua.setForeground(new java.awt.Color(0, 102, 255));
        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/sua.png"))); // NOI18N
        btnsua.setText("Sửa");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnlammoi.setBackground(new java.awt.Color(255, 255, 0));
        btnlammoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnlammoi.setForeground(new java.awt.Color(51, 102, 255));
        btnlammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/Refresh.png"))); // NOI18N
        btnlammoi.setText("Làm mới");
        btnlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlammoiActionPerformed(evt);
            }
        });

        btnthem.setBackground(new java.awt.Color(255, 255, 0));
        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem.setForeground(new java.awt.Color(0, 102, 255));
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/them.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttimkiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        btntimkiem.setBackground(new java.awt.Color(255, 255, 0));
        btntimkiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btntimkiem.setForeground(new java.awt.Color(0, 51, 255));
        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/timkiem.png"))); // NOI18N
        btntimkiem.setText("Search");
        btntimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntimkiemMouseClicked(evt);
            }
        });
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(0, 0, 153));
        lblDongHo.setText("00:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btntimkiem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnlammoi)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(100, 100, 100)
                                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(99, 99, 99)
                            .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntimkiem))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnlammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lblDongHo)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        tblbangvoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Voucher", "Tên Voucher", "Giá trị", "Số Lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbangvoucher.setRowHeight(35);
        tblbangvoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangvoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbangvoucher);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Danh sách Voucher");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenActionPerformed

    private void txtgiatriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiatriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiatriActionPerformed

    private void txtmaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmaFocusGained

    }//GEN-LAST:event_txtmaFocusGained

    private void txtmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
//        delete();
        //        FillTable(sr.getVoucherAll());
        String ma = txtma.getText();
        Connection cn = null;
        PreparedStatement ptm = null;
        String sql = null;
        sql = "delete KhuyenMai Where maKhuyenMai = ?";
        try {
            cn = DBConnection.getDBConect();
            ptm = cn.prepareStatement(sql);

            ptm.setObject(1, ma);
            int chk = ptm.executeUpdate();
            if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa Thành Công");
                    FillTable(sr.getVoucherAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa Không Thành Công");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed

        try {
            String ma = txtma.getText();
            String ten = txtten.getText();
            int soLuong = Integer.valueOf(txtsoluong.getText());
            int giaTri = Integer.valueOf(txtgiatri.getText());
            boolean trangThai = rdodanghoatjdong.isSelected();
            Connection cn = null;
            PreparedStatement ptm = null;
            String sql = null;

            sql = "UPDATE KhuyenMai SET tenKhuyeMai=?,giatri=?,soluong=?,ngaybatdau=?,ngayketthuc=?,trangthai=? WHERE maKhuyenMai = ?";
            try {
                cn = DBConnection.getDBConect();
                ptm = cn.prepareStatement(sql);
                ptm.setObject(1, ten);
                ptm.setObject(3, soLuong);
                ptm.setObject(2, giaTri);
                ptm.setObject(4, ((JTextField) datebatdau.getDateEditor().getUiComponent()).getText());
                ptm.setObject(5, ((JTextField) dateketthuc.getDateEditor().getUiComponent()).getText());
                ptm.setObject(6, trangThai);
                ptm.setObject(7, ma);
                int chk = ptm.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                    FillTable(sr.getVoucherAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Không Thành Công");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
//        int currentNumber = 1;
//        String Ma = txtma.getText();
//        String NguoiTao = txtten.getText();
//        String SoLuong = txtsoluong.getText();
//
//        String GiaTriTT = txtgiatri.getText();
//
//        Date Ngay_bat_dau = new Date(); // Lấy ngày hiện tại
//        Date Ngay_ket_thuc = dateketthuc.getDate();
//
//        if (NguoiTao.isEmpty() || SoLuong.isEmpty() || GiaTriTT.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin", "Hãy thử lại", JOptionPane.ERROR_MESSAGE);
//        } else {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String formattedNgay_bat_dau = dateFormat.format(Ngay_bat_dau);
//            String formattedNgay_ket_thuc = dateFormat.format(Ngay_ket_thuc);
//
//            LocalDate localDateBatDau = Ngay_bat_dau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate localDateKetThuc = Ngay_ket_thuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            String trangThai;
//            if (localDateBatDau.isBefore(localDateKetThuc)) {
//                trangThai = "Đang Hoạt động";
//            } else {
//                trangThai = "Ngừng Hoạt Động";
//            }
//
//            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm dữ liệu mới?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//            if (option == JOptionPane.YES_OPTION) {
//                DefaultTableModel model = (DefaultTableModel) tblbangvoucher.getModel();
//                int rowCount = model.getRowCount();
//                int newNumber = 1;
//                if (rowCount > 0) {
//                    Object lastRowNumber = model.getValueAt(0, 0);
//                    if (lastRowNumber instanceof Integer) {
//                        newNumber = (int) lastRowNumber + 1;
//                    }
//                }
//                model.insertRow(0, new Object[]{newNumber, NguoiTao, SoLuong, GiaTriTT, trangThai, formattedNgay_bat_dau, formattedNgay_ket_thuc});
//
//                txtma.setText("");
//                txtten.setText("");
//                txtsoluong.setText("");
//                txtgiatri.setText("");
//                datebatdau.setCalendar(null);
//                dateketthuc.setCalendar(null);
//            }
//        }
        try {
            String ma = txtma.getText();
            String ten = txtten.getText();
            int soLuong = Integer.valueOf(txtsoluong.getText());
            int giaTri = Integer.valueOf(txtgiatri.getText());
            boolean trangThai = rdodanghoatjdong.isSelected();
            Connection cn = null;
            PreparedStatement ptm = null;
            String sql = null;

            sql = "INSERT INTO KhuyenMai(MaKhuyenMai,tenKhuyeMai, giatri, soluong, ngaybatdau, ngayketthuc,trangthai) VALUES(?,?,?,?,?,?,?)";
            try {
                cn = DBConnection.getDBConect();
                ptm = cn.prepareStatement(sql);
                ptm.setObject(2, ten);
                ptm.setObject(4, soLuong);
                ptm.setObject(3, giaTri);
                ptm.setObject(5, ((JTextField) datebatdau.getDateEditor().getUiComponent()).getText());
                ptm.setObject(6, ((JTextField) dateketthuc.getDateEditor().getUiComponent()).getText());
                ptm.setObject(7, trangThai);
                ptm.setObject(1, ma);
                int chk = ptm.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                    FillTable(sr.getVoucherAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Không Thành Công");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
//        try {
//            String ten = txtten.getText();
//            int soLuong = Integer.valueOf(txtsoluong.getText());
//            String giaTri = txtgiatri.getText();
//            String trangThai = txttrangthai.getText();
//            Connection cn = null;
//            PreparedStatement ptm = null;
//            String sql = null;
//
//            sql = "INSERT INTO voucher(ten_voucher, soluong, giatri, ngay_bat_dau, ngay_ket_thuc,trangthai) VALUES(?,?,?,?,?,?)";
//            try {
//                cn = DBconnection.getConnection();
//                ptm = cn.prepareStatement(sql);
//
//                ptm.setString(1, ten);
//                ptm.setInt(2, soLuong);
//                ptm.setString(3, giaTri);
//                ptm.setString(4, ((JTextField) datebatdau.getDateEditor().getUiComponent()).getText());
//                ptm.setString(5, ((JTextField) dateketthuc.getDateEditor().getUiComponent()).getText());
//                ptm.setString(6, trangThai);
//                int chk = ptm.executeUpdate();
////                if (chk > 0) {
////                    JOptionPane.showMessageDialog(this, "Thêm Thành Công");
////                    FillTable(sr.getVoucherAll());
////                } else {
////                    JOptionPane.showMessageDialog(this, "Sửa Không Thành Công");
////                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlammoiActionPerformed
        clear();
    }//GEN-LAST:event_btnlammoiActionPerformed

    private void tblbangvoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangvoucherMouseClicked
        showData();


    }//GEN-LAST:event_tblbangvoucherMouseClicked

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
//        int fnh = sr.findNV(txttimkiem.getText());
//        if (fnh >= 0) {
//            tblbangvoucher.setRowSelectionInterval(fnh, fnh);
//            FillTable((List<voucher>) sr.getVoucherAll().get(fnh));
//        } else {
//            JOptionPane.showMessageDialog(this, "Không có");
//        }
//          Integer ma = Integer.valueOf(txttimkiem.getText());
//          ArrayList<voucher> checkTim = sr.timKiem(ma);
//          FillTable(checkTim);
        String ten = txttimkiem.getText();
        ArrayList<voucher> list = sr.TimKiem(ten);
        FillTable(list);
        System.out.println(list);


    }//GEN-LAST:event_btntimkiemActionPerformed

    private void txttimkiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyPressed

    }//GEN-LAST:event_txttimkiemKeyPressed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
//         Connection cn = null;
//          PreparedStatement ptm = null;
//          String sql = null;
//          ResultSet rs = null;
//          ArrayList<voucher>  arr= new ArrayList<>();
//          arr.clear();
//          try {
//              sql = "Select dbo.Voucher where ten_voucher like N'%" + txttimkiem.getText()+ "%'";
//              
//            cn = DBconnection.getConnection();
//              System.out.println(sql);
//            ptm = cn.prepareStatement(sql);
//              while (rs.next()) {
//                voucher nv = new voucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
//                arr.add(nv);
//            }
//              cn.close();
//              FillTable(sr.getVoucherAll());
//        } catch (Exception e) {
//        }
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void btntimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntimkiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btntimkiemMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlammoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser datebatdau;
    private com.toedter.calendar.JDateChooser dateketthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JRadioButton rdodanghoatjdong;
    private javax.swing.JRadioButton rdongunghoatdong;
    private javax.swing.JTable tblbangvoucher;
    private javax.swing.JTextField txtgiatri;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
private void init() {
        new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String text = sdf.format(date);
                lblDongHo.setText(text);
            }
        }).start();
    }
}
