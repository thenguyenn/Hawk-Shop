/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonChiTiet;

/**
 *
 * @author khuat
 */
public class HoaDonChiTietFillTable {
    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HoaDonChiTiet";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHoaDon(rs.getInt(1));
                hdct.setMaSPCT(rs.getInt(2));
                hdct.setMaHoaDon(rs.getInt(3));
                hdct.setTenSP(rs.getString(11));
                hdct.setTenImei(rs.getString(10));
                hdct.setGia(rs.getFloat(4));
                hdct.setThanhTien(rs.getFloat(4));
                listHDCT.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }
}
