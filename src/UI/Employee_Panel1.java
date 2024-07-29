/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.NhanVien1;
import repository.DBConnection;
import repository.NhanVienService1;

/**
 *
 * @author tuantq148
 */
public class Employee_Panel1 extends javax.swing.JPanel {

    private NhanVienService1 nvsv = new NhanVienService1();
    ArrayList<NhanVien1> list = new ArrayList<>();
    private int index = -1;

    /**
     * Creates new form Employee_Management
     */
    public Employee_Panel1() {
        initComponents();
        fillTable(nvsv.getAll());
    }

    void fillTable(ArrayList<NhanVien1> list) {
        DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        model.setRowCount(0); // Reset số hàng về 0 trước khi điền dữ liệu mới

        for (NhanVien1 s : list) {
            String vt = "";
            if (s.getId_role() == 2) {
                vt = "Nhân Viên";
            } else {
                vt = "Quản Lý";
            }
            model.addRow(new Object[]{
                s.getId(),
                s.getTen(),
                s.getEmail(),
                s.getSDT(),
                s.getDiaChi(),
                vt
            });
        }
    }

    void showData() {
        index = tbNhanVien.getSelectedRow();
        if (index >= 0) {
            NhanVien1 n = nvsv.getAll().get(index);

            txtMaNV.setText(String.valueOf(tbNhanVien.getValueAt(index, 0)));
            txtTenNV.setText(String.valueOf(tbNhanVien.getValueAt(index, 1)));
            txtDiaChi1.setText(String.valueOf(tbNhanVien.getValueAt(index, 2)));
            txtSDT.setText(String.valueOf(tbNhanVien.getValueAt(index, 3)));
            txtDiaChi.setText(String.valueOf(tbNhanVien.getValueAt(index, 4)));

            int loaiNV = n.getId_role();

            // Assuming 'quản lí' and 'nhân viên' are constants
            // If not, replace them with the actual values you are comparing with
            final int QUAN_LI = 1;
            final int NHAN_VIEN = 2;

            if (loaiNV == QUAN_LI) {
                rdoNV.setSelected(false);
                rdoQL.setSelected(true);
            } else if (loaiNV == NHAN_VIEN) {
                rdoNV.setSelected(true);
                rdoQL.setSelected(false);
            }
        }
    }

    public boolean check() {
        if (txtTenNV.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống!");
            return false;
        }
        if (txtDiaChi.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return false;
        }
        if (txtDiaChi1.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Mail không được để trống!");
            return false;
        }
        if (txtSDT.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            return false;
        }
        return true;
    }

    NhanVien1 GetModel() {
        String ten = txtTenNV.getText();
        String mail = txtDiaChi1.getText();

        String sdtText = txtSDT.getText().replaceAll("\\D", ""); // Loại bỏ tất cả các ký tự không phải số
        int soDT = 0;

        try {
            soDT = Integer.parseInt(sdtText);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý trường hợp lỗi chuyển đổi
        }

        String diaChi = txtDiaChi.getText();

        int loaiNV;
        if (rdoNV.isSelected()) {
            loaiNV = 1;
        } else if (rdoQL.isSelected()) {
            loaiNV = 2;
        } else {

            loaiNV = 0;
        }

        return new NhanVien1(soDT, ten, loaiNV, mail, sdtText, diaChi);
    }

    void clear() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtDiaChi1.setText("");
    }

    public void addNhanVien() {
        NhanVien1 s = GetModel();

        if (nvsv.addNhanVien(s)) {
            JOptionPane.showMessageDialog(this, "Thành công");
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Không Thành công");
        }
    }

    public void delete() {
        index = tbNhanVien.getSelectedRow();
        String nv = tbNhanVien.getValueAt(index, 0).toString();
        NhanVien1 s = GetModel();
        if (nvsv.deteleSach(nv) > 0) {
            JOptionPane.showMessageDialog(this, "Thành công");
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Không Thành công");
        }
    }

    public void UpdateNV() {
        index = tbNhanVien.getSelectedRow();
        NhanVien1 s = GetModel();
        if (nvsv.UpdateNV(s) > 0) {
            JOptionPane.showMessageDialog(this, "Thành công");
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Không Thành công");
        }
    }

    public void firstNV() {
        index = 0;
        updateInfo();
    }

    public void lastNV() {
        index = list.size() - 1;
        updateInfo();
    }

    public void preNV() {
        if (index == 0) {
            lastNV();
        } else {
            index--;
        }

        updateInfo();
    }

    public void nextNV() {
        if (index == list.size() - 1) {
            firstNV();
        } else {
            index++;
        }

        updateInfo();
    }

    private void updateInfo() {
        tbNhanVien.setRowSelectionInterval(index, index);
        showData();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        lblTitle = new javax.swing.JLabel();
        pnlDanhSach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        lblDanhSach = new javax.swing.JLabel();
        pnlCapNhat = new javax.swing.JPanel();
        lblManv = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        lblVaiTro = new javax.swing.JLabel();
        rdoNV = new javax.swing.JRadioButton();
        rdoQL = new javax.swing.JRadioButton();
        txtMaNV = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtDiaChi1 = new javax.swing.JTextField();
        lblDiaChi1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        pnlChucNang = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(30, 55, 153));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("QUẢN LÍ NHÂN VIÊN");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        pnlDanhSach.setBackground(new java.awt.Color(255, 255, 255));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MÃ NV", "TÊN NV", "MAIL", "SĐT", "ĐỊA CHỈ", "VAI TRÒ"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        lblDanhSach.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDanhSach.setText("DANH SÁCH");

        javax.swing.GroupLayout pnlDanhSachLayout = new javax.swing.GroupLayout(pnlDanhSach);
        pnlDanhSach.setLayout(pnlDanhSachLayout);
        pnlDanhSachLayout.setHorizontalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addComponent(lblDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlDanhSachLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlDanhSachLayout.setVerticalGroup(
            pnlDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lblDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        pnlCapNhat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblManv.setText("Mã nhân viên :");

        lblTenNV.setText("Tên nhân viên :");

        lblSDT.setText("Số điện thoại :");

        lblDiaChi.setText("Địa chỉ :");

        lblVaiTro.setText("Vai trò :");

        buttonGroup1.add(rdoNV);
        rdoNV.setSelected(true);
        rdoNV.setText("Nhân viên");

        buttonGroup1.add(rdoQL);
        rdoQL.setText("Quản lí");

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMaNV.setForeground(new java.awt.Color(255, 0, 0));

        lblDiaChi1.setText("Email");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Send Mail"));

        jLabel1.setText("Mail send");

        jLabel2.setText("Pass");

        jLabel3.setText("Mail to");

        jLabel4.setText("Subject");

        jLabel5.setText("Mess");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("Send");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(9, 9, 9)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblManv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                                .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(143, 143, 143))
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                                .addComponent(rdoNV)
                                .addGap(18, 18, 18)
                                .addComponent(rdoQL))
                            .addComponent(lblVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDiaChi1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblManv)
                .addGap(6, 6, 6)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTenNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblSDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDiaChi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(lblDiaChi1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(lblVaiTro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNV)
                            .addComponent(rdoQL)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pnlChucNang.setBackground(new java.awt.Color(255, 255, 255));
        pnlChucNang.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(246, 185, 59));
        btnThem.setForeground(new java.awt.Color(30, 55, 153));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/them.png"))); // NOI18N
        btnThem.setText("Save");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(246, 185, 59));
        btnSua.setForeground(new java.awt.Color(30, 55, 153));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/sua.png"))); // NOI18N
        btnSua.setText("Edit");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(246, 185, 59));
        btnXoa.setForeground(new java.awt.Color(30, 55, 153));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/delete-button.png"))); // NOI18N
        btnXoa.setText("Delete");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(246, 185, 59));
        btnMoi.setForeground(new java.awt.Color(30, 55, 153));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/newww.png"))); // NOI18N
        btnMoi.setText("New");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnFirst.setBackground(new java.awt.Color(246, 185, 59));
        btnFirst.setForeground(new java.awt.Color(30, 55, 153));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/last.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(246, 185, 59));
        btnPrev.setForeground(new java.awt.Color(30, 55, 153));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/back.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(246, 185, 59));
        btnNext.setForeground(new java.awt.Color(30, 55, 153));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons/next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNangLayout = new javax.swing.GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlChucNangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnThem)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnSua)
                    .addGap(4, 4, 4)
                    .addComponent(btnXoa)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnMoi)
                    .addContainerGap(646, Short.MAX_VALUE)))
        );
        pnlChucNangLayout.setVerticalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlChucNangLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnXoa)
                        .addComponent(btnMoi))
                    .addContainerGap(13, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlChucNang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(pnlCapNhat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        showData();
    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (check()) {
            addNhanVien();
            fillTable(nvsv.getAll());
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
//        if (check()) {
//            UpdateNV();
//            fillTable(nvsv.getAll());
//        }
        try {
            int id = Integer.valueOf(txtMaNV.getText());
            String ten = txtTenNV.getText();
            String email = txtDiaChi1.getText();
            String SDT = txtSDT.getText();
            String diaChi = txtDiaChi.getText();
            
            Connection cn = null;
            PreparedStatement ptm = null;
            String sql = null;
            sql = "UPDATE nhanvien SET ten = ?, id_role = ?, email = ?, sdt = ?, diaChi = ? WHERE id = ?";
            try {
                cn = DBConnection.getDBConect();
                ptm = cn.prepareStatement(sql);

                ptm.setObject(1, ten);
                Object vt = null;
                ptm.setObject(2,vt);
                ptm.setObject(3, email);
                ptm.setObject(4, SDT);
                ptm.setObject(5, diaChi);
                ptm.setObject(6, id);
                int chk = ptm.executeUpdate();
                if (chk > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                    fillTable(nvsv.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Không Thành Công");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (check()) {
            delete();
            fillTable(nvsv.getAll());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        firstNV();


    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        preNV();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        nextNV();
    }//GEN-LAST:event_btnNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblDanhSach;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDiaChi1;
    private javax.swing.JLabel lblManv;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblVaiTro;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JPanel pnlDanhSach;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JRadioButton rdoQL;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChi1;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
