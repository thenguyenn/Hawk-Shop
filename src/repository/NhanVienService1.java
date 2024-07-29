/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.NhanVien1;

/**
 *
 * @author MSI
 */
public class NhanVienService1 {
   Connection cn = null;
    PreparedStatement ptm = null;
    String sql = null;
    ResultSet rs = null;
    
    public ArrayList<NhanVien1> getAll() {
        sql = "SELECT * FROM nhanVien";
        ArrayList<NhanVien1> list = new ArrayList<>();
        try {

            cn = DBConnection.getDBConect();
            ptm = cn.prepareStatement(sql);
            rs = ptm.executeQuery();
            while (rs.next()) {
                NhanVien1 nv = new NhanVien1(rs.getInt(1), rs.getString(2), rs.getInt(3)
                        , rs.getString(4), rs.getString(5),rs.getString(6));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean addNhanVien(NhanVien1 nhanVien) {
            sql = "INSERT INTO nhanvien (ten, id_role, email, SDT, diaChi) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DBConnection.getDBConect();
                 PreparedStatement pst = conn.prepareStatement(sql)
                    ){
            pst.setObject(1, nhanVien.getTen());
            pst.setObject(2, nhanVien.getId_role());
            pst.setObject(3, nhanVien.getEmail());
            pst.setObject(4, nhanVien.getSDT());
            pst.setObject(5, nhanVien.getDiaChi());
            
            
            int kq = pst.executeUpdate();
            return  kq>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return false;
    }
    public int deteleSach(String ma){
        sql = "delete from nhanVien Where id = ?";
        try {
            cn = DBConnection.getDBConect();
            ptm = cn.prepareStatement(sql);
            
            ptm.setObject(1, ma);           
            return ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            
            return 0;
        }
    }
    
    public int UpdateNV(NhanVien1 s) {
         sql = "UPDATE nhanvien SET ten = ?, id_role = ?, email = ?, sdt = ?, diaChi = ? WHERE id = ?";

        try (Connection conn = DBConnection.getDBConect();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, s.getTen());
            pst.setInt(2, s.getId_role());
            pst.setString(3, s.getEmail());
            pst.setString(4, s.getSDT());
            pst.setString(5, s.getDiaChi());
            pst.setInt(6, s.getId());

            // Sử dụng executeUpdate thay vì ptm.executeUpdate()
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return 0;
    }
}

