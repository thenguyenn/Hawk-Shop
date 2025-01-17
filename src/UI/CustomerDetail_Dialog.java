/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import model.KhachHang;
import repository.KhachHangRepo;
import service.KhachHangService;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class CustomerDetail_Dialog extends javax.swing.JDialog {
    
    
    KhachHangRepo repoKH = new KhachHangRepo();
    KhachHangService KHservice = new KhachHangService();
    
    int row = -1;
    public CustomerDetail_Dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        fillTable(repoKH.getAll());
    }
    
    public void fillTable(List<KhachHang> list) {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) tbKhachHang3.getModel();
        model.setRowCount(0);
        for (KhachHang kh : list) {
            model.addRow(new Object[]{
                kh.getId(),
                kh.getTen(),
                kh.getSdt(),
                kh.getEmai(),
                kh.getDiaChi()
            });
        }
    }
    
    KhachHang getModel() {

        int id = Integer.valueOf(txtMaKH.getText());
        String tenKH = txtTenKH.getText();
        String Sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String diaChi = txtDC.getText();
        return new KhachHang(id, tenKH, Sdt, email, diaChi);
    }
    
    public void showData() {
        row = tbKhachHang3.getSelectedRow();
        KhachHang kH = KHservice.getAll().get(row);
        if (row >= 0) {
            txtMaKH.setText(tbKhachHang3.getValueAt(row, 0).toString());
            txtTenKH.setText(tbKhachHang3.getValueAt(row, 1).toString());
            txtSDT.setText(tbKhachHang3.getValueAt(row, 2).toString());
            txtEmail.setText(tbKhachHang3.getValueAt(row, 3).toString());
            txtDC.setText(tbKhachHang3.getValueAt(row, 4).toString());
        }
    }

    public void add() {

        String ten = txtTenKH.getText();
        String sdt = txtSDT.getText();
        String diaChi = txtDC.getText();
        String email = txtEmail.getText();

        KhachHang khachHang = new KhachHang(WIDTH, ten, sdt, email, diaChi);
        String kq = KHservice.addNew(khachHang);
        JOptionPane.showMessageDialog(this, kq);
        fillTable(repoKH.getAll());

    }

    public void delete() {
        row = tbKhachHang3.getSelectedRow();
        String maKH = tbKhachHang3.getValueAt(row, 0).toString();
        KhachHang kh = getModel();
        if (repoKH.deleteKH(maKH) > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            fillTable(KHservice.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }

    public void update() {
        row = tbKhachHang3.getSelectedRow();

        KhachHang kh = getModel();
//        System.out.println(kh);
        int IdKhachHang = Integer.valueOf(txtMaKH.getText());
        kh.setId(IdKhachHang);
//        System.out.println(kh);
        if (repoKH.update(kh) > 0) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            fillTable(KHservice.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }
    public void clear(){
        txtDC.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtTenKH.setText("");
        txtMaKH.setText("");
     
    }
    
   
    public boolean check() {
        if (txtTenKH.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống!");
            return false;
        }
        if (txtDC.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!");
            return false;
        }
        if (txtEmail.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Email không được để trống!");
            return false;
        }
        if (txtSDT.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            return false;
        }
        return true;
        
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
        pnlCapNhat = new javax.swing.JPanel();
        lblMaKH = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDC = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbKhachHang3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pnlChucNang2 = new javax.swing.JPanel();
        btnThem2 = new javax.swing.JButton();
        btnSua2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        btnMoi2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(30, 55, 153));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pnlCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        pnlCapNhat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMaKH.setText("Mã khách hàng :");

        lblTenKH.setText("Tên khách hàng :");

        lblSDT.setText("Số điện thoại :");

        lblDiaChi.setText("Địa chỉ :");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        lblEmail.setText("Email :");

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        txtMaKH.setEditable(false);
        txtMaKH.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMaKH.setForeground(new java.awt.Color(255, 0, 0));
        txtMaKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaKHFocusGained(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(281, 281, 281))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblDiaChi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMaKH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblTenKH)
                .addGap(8, 8, 8)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiaChi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tbKhachHang3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ KH", "TÊN KH", "SỐ ĐIỆN THOẠI", "ĐỊA CHỈ", "EMAIL"
            }
        ));
        tbKhachHang3.getTableHeader().setReorderingAllowed(false);
        tbKhachHang3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhachHang3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbKhachHang3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Danh Sách");

        pnlChucNang2.setBackground(new java.awt.Color(255, 255, 255));
        pnlChucNang2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem2.setBackground(new java.awt.Color(246, 185, 59));
        btnThem2.setForeground(new java.awt.Color(30, 55, 153));
        btnThem2.setText("Thêm");
        btnThem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThem2MouseClicked(evt);
            }
        });
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnSua2.setBackground(new java.awt.Color(246, 185, 59));
        btnSua2.setForeground(new java.awt.Color(30, 55, 153));
        btnSua2.setText("Sửa");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        btnXoa2.setBackground(new java.awt.Color(246, 185, 59));
        btnXoa2.setForeground(new java.awt.Color(30, 55, 153));
        btnXoa2.setText("Xóa");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        btnMoi2.setBackground(new java.awt.Color(246, 185, 59));
        btnMoi2.setForeground(new java.awt.Color(30, 55, 153));
        btnMoi2.setText("Mới");
        btnMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoi2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNang2Layout = new javax.swing.GroupLayout(pnlChucNang2);
        pnlChucNang2.setLayout(pnlChucNang2Layout);
        pnlChucNang2Layout.setHorizontalGroup(
            pnlChucNang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNang2Layout.createSequentialGroup()
                .addComponent(btnThem2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua2)
                .addGap(12, 12, 12)
                .addComponent(btnXoa2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoi2)
                .addContainerGap(527, Short.MAX_VALUE))
        );
        pnlChucNang2Layout.setVerticalGroup(
            pnlChucNang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNang2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChucNang2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem2)
                    .addComponent(btnSua2)
                    .addComponent(btnXoa2)
                    .addComponent(btnMoi2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlChucNang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(pnlCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChucNang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtMaKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaKHFocusGained

    }//GEN-LAST:event_txtMaKHFocusGained

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void tbKhachHang3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhachHang3MouseClicked
        showData();
    }//GEN-LAST:event_tbKhachHang3MouseClicked

    private void btnThem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem2MouseClicked
        int add = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (add == JOptionPane.YES_OPTION) {
            if(check()){
              add();
            }
        } else {
            
        }
    }//GEN-LAST:event_btnThem2MouseClicked

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed

    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        int update = JOptionPane.showConfirmDialog(this, "Bạn có cập nhật không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(update == JOptionPane.YES_OPTION){
            if (check()) {
            tbKhachHang3.setRowSelectionInterval(0, 0);
            update();
            fillTable(repoKH.getAll());
            }
        }
        
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        int xoa = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xoa == JOptionPane.YES_OPTION) {
            if(check()){
              delete();
             fillTable(repoKH.getAll());  
            }
            
        } else {
            
        }
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoi2ActionPerformed
        int clear = JOptionPane.showConfirmDialog(this, "Bạn có muốn Làm mới không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (clear == JOptionPane.YES_OPTION) {
            clear();
        } else {
            
        }
    }//GEN-LAST:event_btnMoi2ActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerDetail_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDetail_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDetail_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDetail_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerDetail_Dialog dialog = new CustomerDetail_Dialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi2;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnXoa2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JPanel pnlChucNang2;
    private javax.swing.JTable tbKhachHang3;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
