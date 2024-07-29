
package model;

public class SanPhamHD {
    private int MaSPCT;
    private int MaSP;
    private String TenSP;
    private int soLuong;
    private float gia;
    private String noiDung;
    private String HeDieuHanh;
    private String ThuongHieu;
    private String ChatLieu;
    private String MauSac;
    private String RAM;
    private String ROM;
    private String Pin;

    public SanPhamHD() {
    }

    public SanPhamHD(int MaSPCT, int MaSP, String TenSP, int soLuong, float gia, String noiDung, String HeDieuHanh, String ThuongHieu, String ChatLieu, String MauSac, String RAM, String ROM, String Pin) {
        this.MaSPCT = MaSPCT;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.soLuong = soLuong;
        this.gia = gia;
        this.noiDung = noiDung;
        this.HeDieuHanh = HeDieuHanh;
        this.ThuongHieu = ThuongHieu;
        this.ChatLieu = ChatLieu;
        this.MauSac = MauSac;
        this.RAM = RAM;
        this.ROM = ROM;
        this.Pin = Pin;
    }

    
    
    public SanPhamHD(int MaSPCT, int MaSP, String TenSP, int soLuong, float gia, String noiDung, String HeDieuHanh, String ThuongHieu, String ChatLieu, String MauSac) {
        this.MaSPCT = MaSPCT;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.soLuong = soLuong;
        this.gia = gia;
        this.noiDung = noiDung;
        this.HeDieuHanh = HeDieuHanh;
        this.ThuongHieu = ThuongHieu;
        this.ChatLieu = ChatLieu;
        this.MauSac = MauSac;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getROM() {
        return ROM;
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String Pin) {
        this.Pin = Pin;
    }
    
    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }


    public int getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(int MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getHeDieuHanh() {
        return HeDieuHanh;
    }

    public void setHeDieuHanh(String HeDieuHanh) {
        this.HeDieuHanh = HeDieuHanh;
    }

    public String getThuongHieu() {
        return ThuongHieu;
    }

    public void setThuongHieu(String ThuongHieu) {
        this.ThuongHieu = ThuongHieu;
    }

    public String getChatLieu() {
        return ChatLieu;
    }

    public void setChatLieu(String ChatLieu) {
        this.ChatLieu = ChatLieu;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }
    
}
