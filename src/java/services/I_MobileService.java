
package services;

import java.util.HashMap;
import java.util.List;
import models.dto.Mobile;

public interface I_MobileService {
    
    List<Mobile> getAllMobile();
    
    HashMap<String, List<String>> insertMobile(
            String txtId, 
            String txtName, 
            String txtYearOfProduction,
            String txtPrice,
            String txtDescription,
            String txtQuantity,
            String txtNotSale);
    
    Mobile searchByID(String id);
    
    boolean removeMobileById(String id);
    
    List<Mobile> getMobileByIdOrName(String id, String name);
    
    HashMap<String, List<String>> updateMobile(
            String txtId, 
            String txtPrice,
            String txtDescription,
            String txtQuantity,
            String txtNotSale);
    
}
