/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ROM;

/**
 *
 * @author lenovo
 */
public class ROMDAO {
        public List<ROM> getAll() {
        String query = "SELECT * FROM ROM";
        List<ROM> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new ROM(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ROM getByName(String name) {
        String query = "SELECT Top 1 * FROM ROM WHERE [name] = ?";
        ROM rom = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                rom = new ROM(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rom;
    }
    
    public List<String> getName() {
        String query = "SELECT [name] FROM ROM";
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
    
    public boolean insert(String rom) {
        String query = "INSERT INTO ROM(name) VALUES (?)";
        try {
            JdbcUtils.excuteUpdate(query, rom);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
