/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.HTTT;
import model.KhuyenMai;

public class KhuyenMaiRepository {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<KhuyenMai> getAllKhuyenMai() {
        List<KhuyenMai> listKM = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM KhuyenMai\n"
                    + "WHERE trangThai=1";
            conn = DBConnection.getDBConect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                KhuyenMai km=new KhuyenMai();
                km.setMaKhuyenMai(rs.getString(1));
                km.setTenKhuyenMai(rs.getNString(2));
                km.setGiaTri(rs.getInt(3));
                km.setSoLuong(rs.getInt(4));
                listKM.add(km);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.close();
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKM;
    }
}
