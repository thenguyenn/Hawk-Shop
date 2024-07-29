package repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonChiTiet;

public class HoaDonChiTietRepo {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<HoaDonChiTiet> getAllHoaDonChiTiet(int MaHoaDon) {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HoaDonChiTiet\n"
                    + "WHERE id_HoaDon=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, MaHoaDon);
            rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
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
        try {
            rs.close();
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }

    public int insertHoaDonChiTiet(HoaDonChiTiet hdct) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "INSERT HoaDonChiTiet(id_SanPhamChiTiet,id_HoaDon,gia,TenImei,TenSP)\n"
                    + "VALUES (?,?,?,?,?)";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, hdct.getMaSPCT());
            sttm.setInt(2, hdct.getMaHoaDon());
            sttm.setFloat(3, hdct.getGia());
            sttm.setString(4, hdct.getTenImei());
            sttm.setString(5, hdct.getTenSP());
            return sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
