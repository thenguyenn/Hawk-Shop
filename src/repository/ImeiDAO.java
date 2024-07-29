/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import java.sql.*;
import model.Imei1;

/**
 *
 * @author lenovo
 */
public class ImeiDAO {

    public boolean insert(Imei1 im) {
        String query = "INSERT INTO [QuanLyBanHang].[dbo].[IMEI] \n"
                + "            ([ten]\n"
                + "            ,[created_at]\n"
                + "            ,[created_by]\n"
                + "            ,[updated_at]\n"
                + "            ,[updated_by]\n"
                + "            ,[deleted])\n"
                + "VALUES      ( ?, ?, ?, ?, ?, ?);";
        try {
            JdbcUtils.excuteUpdate(query, im.getTen(), im.getCreatedAt(), im.getCreatedBy(),
                    im.getUpdatedAt(), im.getUpdatedBy(), im.getDeleted());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Imei1 getByName(String name) {
        String query = "SELECT Top 1 * FROM IMEI WHERE [ten] = ?";
        Imei1 imei = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                imei = new Imei1(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getBoolean(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    public boolean delete(Integer id) {
        String query = "DELETE FROM IMEI WHERE id = ?";
        try {
            JdbcUtils.excuteUpdate(query, id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
