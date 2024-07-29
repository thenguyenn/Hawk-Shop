
package service;

import java.util.List;
import model.HoaDonChiTiet;
import repository.HoaDonChiTietRepo;

public class HoaDonChiTietService {
    HoaDonChiTietRepo hdctRepo=new HoaDonChiTietRepo();
    
    public List<HoaDonChiTiet> getAllHoaDonChiTiet(int MaHoaDon) {
        return hdctRepo.getAllHoaDonChiTiet(MaHoaDon);
    }
    
    public String insertHoaDonChiTiet(HoaDonChiTiet hdct) {
        int x=hdctRepo.insertHoaDonChiTiet(hdct);
        if(x>=0){
            return "Thêm sản phẩm vào hóa đơn thành công";
        }
        return "Thêm sản phẩm vào hóa đơn thất bại";
    }

}
