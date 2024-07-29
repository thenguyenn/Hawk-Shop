/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ManHinh;

/**
 *
 * @author lenovo
 */
public class ManHinhDAO {

    public List<ManHinh> getAll() {
        String query = "SELECT * FROM ManHinh";
        List<ManHinh> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new ManHinh(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ManHinh getByName(String name) {
        String query = "SELECT Top 1 * FROM ManHinh WHERE [name] = ?";
        ManHinh manHinh = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                manHinh = new ManHinh(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manHinh;
    }
    
    public List<String> getName() {
        String query = "SELECT [name] FROM ManHinh";
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
    
    public boolean insert(String mh) {
        String query = "INSERT INTO ManHinh(name) VALUES (?)";
        try {
            JdbcUtils.excuteUpdate(query, mh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
