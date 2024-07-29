package repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;

public class HoaDonRepository {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<HoaDon> getAllHoaDonCho() {
        List<HoaDon> listHD = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HoaDon\n"
                    + "WHERE trangThai='ChuaThanhToan'\n"
                    + "ORDER BY id DESC";
            conn = DBConnection.getDBConect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt(1));
                hd.setMaNhanVien(rs.getInt(2));
                hd.setMaKhachHang(rs.getInt(3));
                hd.setMaKhuyenMai(rs.getString(19));
                hd.setMaHTTT(rs.getInt(5));
                hd.setTongTien(rs.getFloat(7));
                hd.setTrangThai(rs.getString(13));
                hd.setNgayTao(rs.getDate(14));
                hd.setGiamGia(rs.getFloat(20));
                hd.setThanhTien(rs.getFloat(21));
                listHD.add(hd);
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
        return listHD;
    }

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> listHD = new ArrayList<>();
        Connection conn = null;
        Statement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HoaDon\n"
                    + "ORDER BY id DESC";
            conn = DBConnection.getDBConect();
            sttm = conn.createStatement();
            rs = sttm.executeQuery(sSQL);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt(1));
                hd.setMaNhanVien(rs.getInt(2));
                hd.setMaKhachHang(rs.getInt(3));
                hd.setMaKhuyenMai(rs.getString(19));
                hd.setMaHTTT(rs.getInt(5));
                hd.setTongTien(rs.getFloat(7));
                hd.setTrangThai(rs.getString(13));
                hd.setNgayTao(rs.getDate(14));
                hd.setGiamGia(rs.getFloat(20));
                hd.setThanhTien(rs.getFloat(21));
                listHD.add(hd);
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
        return listHD;
    }

    public int insertHoaDon(int maNV, int maKH) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "INSERT HoaDon(id_NhanVien,id_KhachHang,created_at,trangThai)\n"
                    + "VALUES (?,?,GETDATE(),'ChuaThanhToan')";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, maNV);
            sttm.setInt(2, maKH);
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

    public int updateTien(float tongTien, float giamGia, float thanhTien, int maHD) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE HoaDon SET tongTien=?, GiamGia =? ,ThanhTien=?\n"
                    + "WHERE id=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setFloat(1, tongTien);
            sttm.setFloat(2, giamGia);
            sttm.setFloat(3, thanhTien);
            sttm.setInt(4, maHD);
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

    public int updateTrangThaiTT(int MaHD, String trangThai) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE HoaDon SET trangThai=?\n"
                    + "WHERE id=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, trangThai);
            sttm.setInt(2, MaHD);
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

    public int updateKhuyenMaiHTTT(String maKhuyenMai, int maHTTT, int maHD) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE HoaDon SET maKhuyenMai=?, id_HinhThucTT=?\n"
                    + "WHERE id=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, maKhuyenMai);
            sttm.setInt(2, maHTTT);
            sttm.setInt(3, maHD);
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

    public List<HoaDon> getAllHoaDonTheoTrangThai(String trangThai) {
        List<HoaDon> listHD = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HoaDon\n"
                    + "WHERE trangThai=?\n"
                    + "ORDER BY id DESC";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, trangThai);
            rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt(1));
                hd.setMaNhanVien(rs.getInt(2));
                hd.setMaKhachHang(rs.getInt(3));
                hd.setMaKhuyenMai(rs.getString(19));
                hd.setMaHTTT(rs.getInt(5));
                hd.setTongTien(rs.getFloat(7));
                hd.setTrangThai(rs.getString(13));
                hd.setNgayTao(rs.getDate(14));
                hd.setGiamGia(rs.getFloat(20));
                hd.setThanhTien(rs.getFloat(21));
                listHD.add(hd);
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
        return listHD;
    }

    public List<HoaDon> getAllHoaDonTheoHTTT(int httt) {
        List<HoaDon> listHD = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT * FROM HoaDon\n"
                    + "WHERE id_HinhThucTT=?\n"
                    + "ORDER BY id DESC";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, httt);
            rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt(1));
                hd.setMaNhanVien(rs.getInt(2));
                hd.setMaKhachHang(rs.getInt(3));
                hd.setMaKhuyenMai(rs.getString(19));
                hd.setMaHTTT(rs.getInt(5));
                hd.setTongTien(rs.getFloat(7));
                hd.setTrangThai(rs.getString(13));
                hd.setNgayTao(rs.getDate(14));
                hd.setGiamGia(rs.getFloat(20));
                hd.setThanhTien(rs.getFloat(21));
                listHD.add(hd);
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
        return listHD;
    }

    public float getDoanhThuTheoNgay(int ngay, int thang) {
        float doanhThu = 0;
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT SUM(ThanhTien)\n"
                    + "FROM HoaDon\n"
                    + "WHERE TrangThai='DaThanhToan' "
                    + "AND DATEPART(MONTH, HoaDon.created_at)=? "
                    + "AND DAY(HoaDon.created_at)=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, thang);
            sttm.setInt(2, ngay);
            rs = sttm.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getFloat(1);
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
        return doanhThu;
    }

    public float getDoanhThuTheoThang(int thang) {
        float doanhThu = 0;
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = "SELECT SUM(ThanhTien)\n"
                    + "FROM HoaDon\n"
                    + "WHERE TrangThai='DaThanhToan' AND DATEPART(MONTH, HoaDon.created_at)=?";
            conn = DBConnection.getDBConect();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, thang);
            rs = sttm.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getFloat(1);
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
        return doanhThu;
    }
}
