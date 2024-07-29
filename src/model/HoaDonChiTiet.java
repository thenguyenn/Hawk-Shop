package model;

public class HoaDonChiTiet {

    private int MaHoaDon;
    private int MaSPCT;
    private String TenSP;
    private String TenImei;
    private float Gia;
    private float ThanhTien;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int MaHoaDon, int MaSPCT, String TenSP, String TenImei, float Gia, float ThanhTien) {
        this.MaHoaDon = MaHoaDon;
        this.MaSPCT = MaSPCT;
        this.TenSP = TenSP;
        this.TenImei = TenImei;
        this.Gia = Gia;
        this.ThanhTien = ThanhTien;
    }

    
    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(int MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getTenImei() {
        return TenImei;
    }

    public void setTenImei(String TenImei) {
        this.TenImei = TenImei;
    }

    

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
    
}
