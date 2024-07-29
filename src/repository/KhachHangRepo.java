/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.KhachHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Phan_Triu
 */
public class KhachHangRepo {
    Dbconnect dbConnection;
    
   public ArrayList<KhachHang> getAll(){
        String sql = "SELECT TOP (1000) [id] ,[ten] ,[sdt] ,[email] ,[diaChi] ,[created_at] ,[updated_at] ,[updated_by] ,[deleted]\n" +
"  FROM [KhachHang]";
        ArrayList<KhachHang> listKH = new ArrayList<>();
        try (Connection conn = dbConnection.getDBConect();
                PreparedStatement pst = conn.prepareCall(sql)
                ){
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                   KhachHang kh  = new KhachHang();
                kh.setId(rs.getInt(1));
                kh.setTen(rs.getString(2));
                kh.setSdt(rs.getString(3));
                kh.setEmai(rs.getString(4));
                kh.setDiaChi(rs.getString(5));             
                listKH.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }
   
   public Boolean addNew(KhachHang khachHang){
        String sql = "USE [master] INSERT INTO [KhachHang] ([ten] ,[sdt] ,[email] ,[diaChi]) VALUES (?,?,?,?)";
        
        try(Connection conn = dbConnection.getDBConect();
            PreparedStatement pst = conn.prepareStatement(sql)){
            
            pst.setString(1, khachHang.getTen());
            pst.setString(2, khachHang.getSdt());
            pst.setString(3, khachHang.getEmai());
            pst.setString(4, khachHang.getDiaChi());               
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int deleteKH(String maKH) {
        String sql = "USE [master] DELETE FROM KhachHang \n" +
"      WHERE id = ?";
        try (Connection conn = dbConnection.getDBConect();
            PreparedStatement pst = conn.prepareStatement(sql)){
            

            pst.setObject(1, maKH);
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    
   public Integer update(KhachHang khachHang){
       Integer row = null;
       String sql = "USE [master] UPDATE KhachHang SET ten = ?,sdt =?  ,email = ?, diaChi = ? WHERE id = ?";
        
        try(Connection conn = dbConnection.getDBConect();
            PreparedStatement pst = conn.prepareStatement(sql)){
            
            pst.setString(1, khachHang.getTen());
            pst.setString(2, khachHang.getSdt());
            pst.setString(3, khachHang.getEmai());
            pst.setString(4, khachHang.getDiaChi());
            pst.setInt(5, khachHang.getId()); 
            
            int kq = pst.executeUpdate();
            return kq;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    
    
}
