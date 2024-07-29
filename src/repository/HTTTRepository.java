
package repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.HTTT;
import model.KhachHang;

public class HTTTRepository {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<HTTT> getAllHTTT() {
        List<HTTT> listHTTT = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HinhThucTT";
            conn = DBConnection.getDBConect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                HTTT httt=new HTTT();
                httt.setMaHTTT(rs.getInt(1));
                httt.setTenHTTTT(rs.getNString(2));
                listHTTT.add(httt);
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
        return listHTTT;
    }
}
