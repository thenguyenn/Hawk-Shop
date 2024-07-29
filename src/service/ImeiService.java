
package service;

import java.util.List;
import model.Imei;
import repository.ImeiRepository;


public class ImeiService {
    ImeiRepository imeiRepo=new ImeiRepository();
    
    public List<Imei> getAllImeiTheoMaSPCT(int MaSPCT) {
        return imeiRepo.getAllImeiTheoMaSPCT(MaSPCT);
    }
    
    public void UpdateTrangThaiIMEI(int MaIMEI, int trangThai) {
        int x=imeiRepo.UpdateTrangThaiIMEI(MaIMEI, trangThai);
    }
    
    public int getMaImeiTheoTen(String TenImei) {
        return imeiRepo.getMaImeiTheoTen(TenImei);
    }
}
