/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import service.HoaDonService;

/**
 *
 * @author khuat
 */
public class ThongKeUi extends javax.swing.JPanel {

    HoaDonService serviceHD = new HoaDonService();

    public ThongKeUi() {
        initComponents();
        showBarChart(0);
    }

    public void showBarChart(int luachon) {
        try {
            //create dataset for the graph
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            if (luachon == 0) {
                for (int i = 1; i <= 12; i++) {
                    dataset.setValue(serviceHD.getDoanhThuTheoThang(i), "monut", "T" + i);
                }
            } else {
                for (int i = 1; i <= 31; i++) {
                    double doanhThu = serviceHD.getDoanhThuTheoNgay(i, luachon);
                    if (doanhThu>0 || i==1 || i==5 || i==8 || i==12 || i==16 || i==20 || i==24 || i==28) {
                        dataset.setValue(doanhThu, "mount", "Ngày" + i);
                    }
                }
            }

            //create chart
            JFreeChart chart = ChartFactory.createBarChart("Doanh thu cửa hàng", "Doanh thu", "amu",
                    dataset, PlotOrientation.VERTICAL, false, true, false);

            CategoryPlot categoryPlot = chart.getCategoryPlot();
            //categoryPlot.setRangeGridlinePaint(Color.BLUE);
            categoryPlot.setBackgroundPaint(Color.WHITE);
            BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
            Color clr3 = new Color(204, 0, 51);
            renderer.setSeriesPaint(0, clr3);

            ChartPanel barpChartPanel = new ChartPanel(chart);
            pnlBarChart.removeAll();
            pnlBarChart.add(barpChartPanel, BorderLayout.CENTER);
            pnlBarChart.validate();
        } catch (Exception e) {
            System.out.println("Lỗiiiiiiiiiiiiiiiiiiii :");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBarChart = new javax.swing.JPanel();
        cboDoanhThu = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(153, 255, 255));

        pnlBarChart.setLayout(new java.awt.BorderLayout());

        cboDoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doanh Thu Theo Tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cboDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDoanhThuActionPerformed
        int index = cboDoanhThu.getSelectedIndex();
        showBarChart(index);
    }//GEN-LAST:event_cboDoanhThuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboDoanhThu;
    private javax.swing.JPanel pnlBarChart;
    // End of variables declaration//GEN-END:variables
}
