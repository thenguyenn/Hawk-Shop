/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.RAM;

/**
 *
 * @author lenovo
 */
public class RAMDAO {

    public List<RAM> getAll() {
        String query = "SELECT * FROM RAM";
        List<RAM> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new RAM(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public RAM getByName(String name) {
        String query = "SELECT Top 1 * FROM RAM WHERE [name] = ?";
        RAM ram = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                ram = new RAM(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ram;
    }
    
    public List<String> getName() {
        String query = "SELECT [name] FROM RAM";
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
    
    public boolean insert(String ram) {
        String query = "INSERT INTO RAM(name) VALUES (?)";
        try {
            JdbcUtils.excuteUpdate(query, ram);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
