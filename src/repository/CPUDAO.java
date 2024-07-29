/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.CPU;

/**
 *
 * @author lenovo
 */
public class CPUDAO {

    public List<CPU> getAll() {
        String query = "SELECT * FROM CPU";
        List<CPU> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new CPU(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getName() {
        String query = "SELECT [name] FROM CPU";
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
    
    public CPU getByName(String name) {
        String query = "SELECT Top 1 * FROM CPU WHERE [name] = ?";
        CPU cpu = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                cpu = new CPU(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpu;
    }
    
    public boolean insert(String cpu) {
        String query = "INSERT INTO CPU(name) VALUES (?)";
        try {
            JdbcUtils.excuteUpdate(query, cpu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
