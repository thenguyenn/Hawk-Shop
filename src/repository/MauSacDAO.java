/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import model.MauSac;

/**
 *
 * @author lenovo
 */
public class MauSacDAO {

    public List<MauSac> getAll() {
        String query = "SELECT * FROM MauSac";
        List<MauSac> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new MauSac(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getBoolean(7))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public MauSac getByName(String name) {
        String query = "SELECT Top 1 * FROM MauSac WHERE [tenMau] = ?";
        MauSac mauSac = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                mauSac = new MauSac(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getBoolean(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSac;
    }

    public List<String> getName() {
        String query = "SELECT [tenMau] FROM MauSac";
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

    public boolean insert(String mau) {
        String query = "INSERT INTO MauSac(tenMau) VALUES (?)";
        try {
            JdbcUtils.excuteUpdate(query, mau);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
