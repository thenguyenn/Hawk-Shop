
package service;

import java.sql.Date;
import java.util.List;
import model.HoaDon;
import repository.HoaDonRepository;

public class HoaDonService {
    
    HoaDonRepository hdRepo=new HoaDonRepository();
    
    public List<HoaDon> getAllHoaDonCho() {
        return hdRepo.getAllHoaDonCho();
    }
    
    public String insertHoaDon(int maNV,int maKH) {
        int x=hdRepo.insertHoaDon(maNV, maKH);
        if(x>=0){
            return "Thêm hóa đơn thành công";
        }
        return "Thêm hóa đơn thất bại";
    }
    
    public void updateTien(float tongTien,float giamGia,float thanhTien,int maHD) {
        int x=hdRepo.updateTien(tongTien, giamGia, thanhTien, maHD);
    }
    
    public void updateTrangThaiTT(int MaHD,String trangThai) {
        int x=hdRepo.updateTrangThaiTT(MaHD, trangThai);
    }
    
    public void updateKhuyenMaiHTTT(String maKhuyenMai,int maHTTT,int maHD) {
        int x=hdRepo.updateKhuyenMaiHTTT(maKhuyenMai, maHTTT, maHD);
    }
    
    public List<HoaDon> getAllHoaDon() {
        return hdRepo.getAllHoaDon();
    }
    
    public List<HoaDon> getAllHoaDonTheoTrangThai(String trangThai) {
        return hdRepo.getAllHoaDonTheoTrangThai(trangThai);
    }
    
    public List<HoaDon> getAllHoaDonTheoHTTT(int httt) {
        return hdRepo.getAllHoaDonTheoHTTT(httt);
    }
    
    public float getDoanhThuTheoNgay(int ngay, int thang) {
        return hdRepo.getDoanhThuTheoNgay(ngay, thang);
    }
    
    public float getDoanhThuTheoThang(int thang) {
        return hdRepo.getDoanhThuTheoThang(thang);
    }
}
