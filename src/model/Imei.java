
package model;

public class Imei {
    private int maImei;
    private String tenImei;
    private int MaSPCT;
    private int TrangThai;

    public Imei() {
    }

    public Imei(int maImei, String tenImei, int MaSPCT, int TrangThai) {
        this.maImei = maImei;
        this.tenImei = tenImei;
        this.MaSPCT = MaSPCT;
        this.TrangThai = TrangThai;
    }

    public int getMaImei() {
        return maImei;
    }

    public void setMaImei(int maImei) {
        this.maImei = maImei;
    }

    public String getTenImei() {
        return tenImei;
    }

    public void setTenImei(String tenImei) {
        this.tenImei = tenImei;
    }

    public int getMaSPCT() {
        return MaSPCT;
    }

    public void setMaSPCT(int MaSPCT) {
        this.MaSPCT = MaSPCT;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
