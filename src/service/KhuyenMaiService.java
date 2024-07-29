
package service;

import java.util.List;
import model.KhuyenMai;
import repository.KhuyenMaiRepository;

public class KhuyenMaiService {
    
    KhuyenMaiRepository kmRepo=new KhuyenMaiRepository();
    
     public List<KhuyenMai> getAllKhuyenMai() {
         return kmRepo.getAllKhuyenMai();
     }
    
}
