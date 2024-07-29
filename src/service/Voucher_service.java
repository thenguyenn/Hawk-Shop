/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;



import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.voucher;
import repository.DBConnection;

/**
 *
 * @author khuat
 */
public class Voucher_service {
    Connection cn = null;
    PreparedStatement ptm = null;
    String sql = null;
    ResultSet rs = null;
    ArrayList<voucher> list = new ArrayList<>();
    public ArrayList<voucher> getVoucherAll() {
        sql = "SELECT * FROM KhuyenMai";
        ArrayList<voucher> list = new ArrayList<>();
        list.removeAll(list);
        try {

            cn = DBConnection.getDBConect();
            ptm = cn.prepareStatement(sql);
            rs = ptm.executeQuery();
            while (rs.next()) {
                    Date ngay_bat_dau=rs.getDate(5);
                    Date ngay_ket_thuc=rs.getDate(6);
                    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                    String ns=sdf.format(ngay_bat_dau);
                    String ndk=sdf.format(ngay_ket_thuc);
                voucher nv = new voucher(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), ns, ndk, (int) rs.getFloat(7));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int deteleUpdate(String id){
        sql = "delete dbo.Voucher Where id = ?";
        try {
            cn = DBConnection.getDBConect();
            ptm = cn.prepareStatement(sql);
            
            ptm.setObject(1, id);           
            return ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int UpdateVoucher(voucher s) {       
//        sql = "update voucher\n"
//                + "set ten_voucher=?,soluong=?,giatri=?,ngay_bat_dau=?,ngay_ket_thuc=?,trangthai=?\n"
//                + "where id=?";
        sql = "UPDATE dbo.Voucher SET ten_voucher=?,soluong=?,giatri=?,ngay_bat_dau=?,ngay_ket_thuc=?,trangthai=? WHERE [id] = ?";
        try {
            cn = DBConnection.getDBConect();
            ptm = cn.prepareStatement(sql);
            
            ptm.setObject(7, s.getMa());
            ptm.setObject(1, s.getTen());
            ptm.setObject(2, s.getSoLuong());
            ptm.setObject(3, s.getGiaTri());
            ptm.setObject(4, s.getNgayBatDau());
            ptm.setObject(5, s.getNgayKetThuc());
            ptm.setObject(6, s.getTrangThai());
            return ptm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }  

    public ArrayList<voucher> findAllVoucherByName(String ten) throws SQLException{
        sql = "select * from voucher where ten_voucher like ='"+ten+"'";
        
            Statement srm = cn.createStatement();
            rs = srm.executeQuery(sql);
            ArrayList<voucher> list = new ArrayList<>();
            while (rs.next()) {            
            voucher vou = new voucher();
            vou.setMa(rs.getString("id"));
            vou.setTen(rs.getString("ten_voucher"));
            vou.setSoLuong(rs.getInt("soLuong"));
            vou.setGiaTri(rs.getInt("giaTri"));
            vou.setNgayBatDau(rs.getString("ngay_bat_dau"));
            vou.setNgayKetThuc(rs.getString("ngay_ket_thuc"));
            vou.setTrangThai(rs.getInt("trangThai"));
        }
        return list;     
    }
      public ArrayList<voucher> TimKiem(String ten) {
        sql = "select * from voucher where ten_voucher like '"+ten+"'";
        ArrayList<voucher> list = new ArrayList<>();
        
        try {

            cn = DBConnection.getDBConect();
            ptm = cn.prepareStatement(sql);
            rs = ptm.executeQuery();
            while (rs.next()) {
                    Date ngay_bat_dau=rs.getDate(5);
                    Date ngay_ket_thuc=rs.getDate(6);
                    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                    String ns=sdf.format(ngay_bat_dau);
                    String ndk=sdf.format(ngay_ket_thuc);
                voucher nv = new voucher(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), ns, ndk, rs.getInt(7));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        return list;
    }
}
