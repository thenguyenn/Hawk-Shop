
package service;

import java.util.List;
import model.SanPhamHD;
import repository.SanPhamHDRepository;

public class SanPhamHDService {
    
    SanPhamHDRepository sanPhamHDRepo=new SanPhamHDRepository();
    
    public List<SanPhamHD> getAllSanPhamHD() {
        return sanPhamHDRepo.getAllSanPhamHD();
    }
    
    public void UpdateSoLuongSP(int MaSPHD, int soLuong) {
        int x= sanPhamHDRepo.UpdateSoLuongSP(MaSPHD, soLuong);
    }
}
