
package model;

import java.util.Date;

public class HoaDon {
    private int MaHoaDon;
    private int MaNhanVien;
    private int MaKhachHang;
    private String MaKhuyenMai;
    private int MaHTTT;
    private float tongTien;
    private Date ngayTao;
    private String TrangThai;
    private float GiamGia;
    private float ThanhTien;

    public HoaDon() {
    }

    public HoaDon(int MaHoaDon, int MaNhanVien, int MaKhachHang, String MaKhuyenMai, int MaHTTT, float tongTien, Date ngayTao, String TrangThai, float GiamGia, float ThanhTien) {
        this.MaHoaDon = MaHoaDon;
        this.MaNhanVien = MaNhanVien;
        this.MaKhachHang = MaKhachHang;
        this.MaKhuyenMai = MaKhuyenMai;
        this.MaHTTT = MaHTTT;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.TrangThai = TrangThai;
        this.GiamGia = GiamGia;
        this.ThanhTien = ThanhTien;
    }

    

    public float getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(float GiamGia) {
        this.GiamGia = GiamGia;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getMaKhuyenMai() {
        return MaKhuyenMai;
    }

    public void setMaKhuyenMai(String MaKhuyenMai) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public int getMaHTTT() {
        return MaHTTT;
    }

    public void setMaHTTT(int MaHTTT) {
        this.MaHTTT = MaHTTT;
    }

   
    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
