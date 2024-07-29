/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import repository.Dbconnect;
import java.sql.*;

/**
 *
 * @author tuantq148
 */
public class JdbcUtils {
    Dbconnect dbconnect;
    //sử dụng cho câu truy vấn select
    public static ResultSet executeQuery(String sql, Object... args) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Dbconnect.getDBConect();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);

            }
            rs = ps.executeQuery();
        } catch (Exception e) {
        }
        return rs;
    }
    
    //sử dụng cho câu truy vấn thêm, sửa ,xóa....
    public static Integer excuteUpdate(String sql, Object... args) {
        Connection con = null;
        Integer row = 0;
        PreparedStatement ps = null;
        try {
            con = Dbconnect.getDBConect();//tạo kết nối
            ps = con.prepareStatement(sql);//tạo ps
            //gán các giá trị cho các ? trong các câu lệnh sql
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
