/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import model.HoaDon;

public class HoaDonService1 {

    Connection cn = null;
    PreparedStatement ptm = null;
    String sql = null;
    ResultSet rs = null;
    private Iterable<HoaDon> listHoaDon;

    public List<HoaDon> getAllHoaDonCho() {
        List<HoaDon> listHD = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HoaDon\n"
                    +"ORDER BY id DESC";
            conn = DBConnection.getDBConect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt(1));
                hd.setMaNhanVien(rs.getInt(2));
                hd.setMaKhachHang(rs.getInt(3));
//                hd.setMaKhuyenMai(rs.getInt(4));
//                hd.setMaHTTT(rs.getInt(5));
                hd.setTongTien(rs.getFloat(7));
                hd.setTrangThai(rs.getString(13));
                hd.setNgayTao(rs.getDate(14));
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

//    public ArrayList<HoaDon> listHoaDonView() {
//        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
//        try {
//            Connection conn = DBconnection.getConnection();
//            String sql = "Select HoaDon.id,NhanVien.id,KhachHang.id,HoaDon.soLuong,HoaDon.tongTien,HoaDon.sdt,HoaDon.diaChi,HoaDon.ngayXacNhan,HoaDon.ngayShip,HoaDon.ngayNhan,Hoadon.trangThai,\n"
//                    + "KhachHang.ten,NhanVien.ten,HinhThucTT.ten\n"
//                    + "from HoaDon join KhachHang on HoaDon.id_KhachHang = KhachHang.id            \n"
//                    + "join NhanVien on HoaDon.id_NhanVien = NhanVien.id\n"
//                    + "join HinhThucTT on HoaDon.id_HinhThucTT = HinhThucTT.id";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                
//                String NgayTao = rs.getString(8);
//                String NgayThanhToan = rs.getString(8);
//                String maNV = rs.getString(2);
//                String maKH = rs.getString(3);
//                int donGia = rs.getInt(5);
//                int soLuong = rs.getInt(4);
//                int giaTriVoucher = rs.getInt(5);
//                int tongTien = rs.getInt(5);
//                String tenNv = rs.getString(13);
//                String tenKH = rs.getString(12);
//                String trangThai = rs.getString(11);
//                String hinhThucThanhToan = rs.getString(14);
//                String SDT = rs.getString(6);
//                String DiaChi = rs.getString(7);
//                
//                HoaDon hd = new HoaDon(id, id, maNV, maKH, tenNv, tenKH, NgayTao, NgayThanhToan, tongTien, donGia, soLuong, donGia, trangThai, SDT, DiaChi, hinhThucThanhToan);
//                listHoaDon.add(hd);
//            }
//            return listHoaDon;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Loi select");
//        }
//        return null;
//    }
    public Boolean delete(String id) {
        String sql = "delete from [dbo].[HoaDon] where[id] = " + id;
        try (Connection conn = DBConnection.getDBConect();
                PreparedStatement pst = conn.prepareStatement(sql)) {
//            pst.setObject(1, id);
            int kq = pst.executeUpdate();
            return kq > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public ArrayList<HoaDon> searchById(String id) {
        ArrayList<HoaDon> kq = new ArrayList<>();
        for (HoaDon hd : listHoaDon) {
            if (id.equals(hd.getMaHoaDon())) {
                kq.add(hd);
            }
        }
        return kq;
    }
}
