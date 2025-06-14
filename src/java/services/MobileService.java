/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.List;
import models.dao.MobileDAO;
import models.dto.Mobile;

/**
 *
 * @author admin
 */
public class MobileService implements I_MobileService{
    
    private final MobileDAO mobileDAO;

    public MobileService() {
        this.mobileDAO = new MobileDAO();
    }
    
    @Override
    public List<Mobile> getAllMobile() {
        return mobileDAO.findAll();
    }
    
}
