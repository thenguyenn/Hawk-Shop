
package service;

import java.util.List;
import model.NhanVien;
import repository.NhanVienRepository;

public class NhanVienService {
    
    NhanVienRepository nvRepo=new NhanVienRepository();
    
    public List<NhanVien> getAllNhanVien() {
        return nvRepo.getAllNhanVien();
    }
}
