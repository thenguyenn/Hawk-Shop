package repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.SanPhamHD;

public class SanPhamHDRepository {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<SanPhamHD> getAllSanPhamHD() {
        List<SanPhamHD> listSPHD = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT SanPhamChiTiet.id,SanPhamChiTiet.id_SanPham,SanPham.ten,SanPhamChiTiet.soLuong,\n"
                    + "SanPhamChiTiet.gia,Mota.noiDung,RAM.name,ROM.name,Pin.name,HeDieuHanh.ten,ThuongHieu.ten,\n"
                    + "ChatLieu.ten,MauSac.tenMau\n"
                    + "FROM SanPhamChiTiet\n"
                    + "JOIN SanPham ON SanPhamChiTiet.id_SanPham=SanPham.id\n"
                    + "JOIN ThuongHieu ON SanPhamChiTiet.id_ThuongHieu=ThuongHieu.id\n"
                    + "JOIN Mota ON SanPhamChiTiet.id_MoTa=Mota.id\n"
                    + "JOIN HeDieuHanh ON SanPhamChiTiet.id_HeDieuHanh=HeDieuHanh.id\n"
                    + "JOIN ChatLieu ON SanPhamChiTiet.id_ChatLieu=ChatLieu.id\n"
                    + "JOIN MauSac ON SanPhamChiTiet.id_Mau=MauSac.id\n"
                    + "JOIN RAM ON SanPhamChiTiet.id_RAM=RAM.id\n"
                    + "JOIN ROM ON SanPhamChiTiet.id_ROM=ROM.id\n"
                    + "JOIN Pin ON SanPhamChiTiet.id_Pin=Pin.id\n"
                    + "WHERE SanPhamChiTiet.soLuong>0";
            conn = DBConnection.getDBConect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                SanPhamHD sphd = new SanPhamHD();
                sphd.setMaSPCT(rs.getInt(1));
                sphd.setMaSP(rs.getInt(2));
                sphd.setTenSP(rs.getString(3));
                sphd.setSoLuong(rs.getInt(4));
                sphd.setGia(rs.getFloat(5));
                sphd.setNoiDung(rs.getNString(6));
                sphd.setRAM(rs.getString(7));
                sphd.setROM(rs.getString(8));
                sphd.setPin(rs.getString(9));
                sphd.setHeDieuHanh(rs.getNString(10));
                sphd.setThuongHieu(rs.getNString(11));
                sphd.setChatLieu(rs.getNString(12));
                sphd.setMauSac(rs.getNString(13));
                listSPHD.add(sphd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.close();
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSPHD;
    }

    public int UpdateSoLuongSP(int MaSPHD, int soLuong) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE SanPhamChiTiet SET soLuong=soLuong-?\n"
                    + "WHERE id =?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, soLuong);
            sttm.setInt(2, MaSPHD);
            return sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
//    public int deteleUpdate(String id){
//        Connection conn = null;
//        PreparedStatement sttm = null;
//        String sSQL = "delete dbo.Voucher Where id = ?";
//        try {
//            cn = DBConnection.getDBConect();
//            ptm = cn.prepareStatement(sql);
//            
//            ptm.setObject(1, id);           
//            return ptm.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
}
