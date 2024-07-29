/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ThuongHieu;

/**
 *
 * @author lenovo
 */
public class ThuongHieuDAO {

    public List<ThuongHieu> getAll() {
        String query = "SELECT * FROM ThuongHieu";
        List<ThuongHieu> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new ThuongHieu(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getBoolean(7))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ThuongHieu getByName(String name) {
        String query = "SELECT Top 1 * FROM ThuongHieu WHERE [ten] = ?";
        ThuongHieu thuongHieu = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                thuongHieu = new ThuongHieu(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getBoolean(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return thuongHieu;
    }

    public List<String> getName() {
        String query = "SELECT [ten] FROM ThuongHieu";
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(String th) {
        String query = "INSERT INTO ThuongHieu(ten) VALUES (?)";
        try {
            JdbcUtils.excuteUpdate(query, th);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
