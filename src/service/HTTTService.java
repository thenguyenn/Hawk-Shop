
package service;

import java.util.List;
import model.HTTT;
import repository.HTTTRepository;

public class HTTTService {
    
    HTTTRepository htttRepo=new HTTTRepository();
    
    public List<HTTT> getAllHTTT() {
        return htttRepo.getAllHTTT();
    }
    
}
