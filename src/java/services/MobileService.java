/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import models.dao.MobileDAO;
import models.dto.Mobile;
import utils.Const;
import utils.ErrorUtils;
import utils.ValidatorUtils;

/**
 *
 * @author admin
 */
public class MobileService implements I_MobileService{
    
    private final MobileDAO mobileDAO = new MobileDAO();

    
    @Override
    public List<Mobile> getAllMobile() {
        return mobileDAO.findAll();
    }

    @Override
    public HashMap<String, List<String>> insertMobile(
            String txtId, 
            String txtName, 
            String txtYearOfProduction, 
            String txtPrice, 
            String txtDescription, 
            String txtQuantity, 
            String txtNotSale) {
        
        HashMap<String, List<String>> errors = new HashMap<>();
        ValidatorUtils valid  = new ValidatorUtils();
        
        // Validate txtId
        if (txtId == null || txtId.trim().isEmpty()) {
            ErrorUtils.addError(errors, "txtId", "Mobile id is not empty");
        } else if (!Pattern.matches(Const.MOBILE_ID_PATTERN, txtId)) {
            ErrorUtils.addError(errors, "txtId", "Mobile id must start with 'mb' (case-insensitive)");
        } else if (mobileDAO.findById(txtId.trim()) != null) {
            ErrorUtils.addError(errors, "txtId", "Mobile id already exists");
        }


        
        
        //Vali txtName
        if(txtName == null || txtName.trim().isEmpty()){
            ErrorUtils.addError(errors, "txtName", "Mobile name is not empty");
        }
        
        //Valid txtYearOfProduction
        if (txtYearOfProduction == null || txtYearOfProduction.trim().isEmpty()) {
            ErrorUtils.addError(errors, "txtYearOfProduction", "Year of production is required");
        } else if (!valid.isInteger(txtYearOfProduction)) {
            ErrorUtils.addError(errors, "txtYearOfProduction", "Year must be an integer");
        } else {
            int year = Integer.parseInt(txtYearOfProduction.trim());
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            if (year < 2000 || year > currentYear) {
                ErrorUtils.addError(errors, "txtYearOfProduction", "Year must be between 2000 and " + currentYear);
            }
        }
        
        //Valid txtPrice
        if(txtPrice == null || txtPrice.trim().isEmpty()){
            ErrorUtils.addError(errors, "txtPrice", "Price is required");
        }else if(!valid.isDouble(txtPrice)){
            ErrorUtils.addError(errors, "txtPrice", "Price must be a valid number");
        }else{
            double price = Double.parseDouble(txtPrice.trim());
            if(price <= 0){
                ErrorUtils.addError(errors, "txtPrice", "Price must be greater than 0");
            }
        }
        
        //Valid txtQuantity
        if(txtQuantity == null || txtQuantity.trim().isEmpty()){
            ErrorUtils.addError(errors, "txtQuantity", "Quantity is required");
        }else if(!valid.isInteger(txtQuantity)){
            ErrorUtils.addError(errors, "txtQuantity", "Quantity must be an integer");
        }else{
            int quantity = Integer.parseInt(txtQuantity.trim());
            if(quantity < 0){
                ErrorUtils.addError(errors, "txtQuantity", "Quantity must be a positive number");
            }
        }
        
        //Valid txtNotSale
        if (txtNotSale == null || txtNotSale.trim().isEmpty()) {
            ErrorUtils.addError(errors, "txtNotSale", "NotSale is required");
        } else if (!valid.isBoolean(txtNotSale)) {
            ErrorUtils.addError(errors, "txtNotSale", "NotSale must be true or false");
        }
        
        txtDescription = (txtDescription != null) ? txtDescription.trim() : "";

        
        if(errors.isEmpty()){
            Mobile mobile = new Mobile(
                    txtId.trim().toUpperCase(),
                    txtName.trim(),
                    txtDescription,
                    Double.parseDouble(txtPrice.trim()),
                    Integer.parseInt(txtYearOfProduction.trim()),
                    Integer.parseInt(txtQuantity.trim()),
                    Boolean.parseBoolean(txtNotSale.trim())  
            );
            mobileDAO.add(mobile);
        }
        
        return errors;
    }

    @Override
    public Mobile searchByID(String id) {
        return mobileDAO.findById(id.trim());
    }
    
    

    
    
}
