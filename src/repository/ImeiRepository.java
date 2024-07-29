package repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Imei;

public class ImeiRepository {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Imei> getAllImeiTheoMaSPCT(int MaSPCT) {
        List<Imei> listImeil = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM IMEI\n"
                    + "WHERE id_SanPham=?\n"
                    + "AND TrangThai=1";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, MaSPCT);
            rs = sttm.executeQuery();
            while (rs.next()) {
                Imei imei = new Imei();
                imei.setMaImei(rs.getInt(1));
                imei.setTenImei(rs.getString(2));
                imei.setMaSPCT(rs.getInt(8));
                imei.setTrangThai(rs.getInt(9));
                listImeil.add(imei);
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
        return listImeil;
    }

    public int UpdateTrangThaiIMEI(int MaIMEI, int trangThai) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE IMEI SET TrangThai=?\n"
                    + "WHERE id=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, trangThai);
            sttm.setInt(2, MaIMEI);
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

    public int getMaImeiTheoTen(String TenImei) {
        int maImei = 0;
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT id FROM IMEI\n"
                    + "WHERE ten=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, TenImei);
            rs = sttm.executeQuery();
            while (rs.next()) {
                maImei = rs.getInt(1);
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
        return maImei;
    }
}
