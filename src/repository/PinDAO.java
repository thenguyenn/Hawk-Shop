/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.Pin;

/**
 *
 * @author lenovo
 */
public class PinDAO {

    public List<Pin> getAll() {
        String query = "SELECT * FROM Pin";
        List<Pin> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new Pin(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pin getByName(String name) {
        String query = "SELECT Top 1 * FROM Pin WHERE [name] = ?";
        Pin pin = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                pin = new Pin(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pin;
    }
    
    public List<String> getName() {
        String query = "SELECT [name] FROM Pin";
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
    
    public boolean insert(String pin) {
        String query = "INSERT INTO PIN(name) VALUES (?)";
        try {
            JdbcUtils.excuteUpdate(query, pin);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
