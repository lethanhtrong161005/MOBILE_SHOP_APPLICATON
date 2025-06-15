/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author admin
 */
public class ValidatorUtils {
    
    public boolean isInteger(String value) {
        if (value == null || value.trim().isEmpty()) return false;
        try {
            Integer.parseInt(value.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(value.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isBoolean(String value) {
        if (value == null) return false;
        String lower = value.trim().toLowerCase();
        return lower.equals("true") || lower.equals("false");
    }
    
    
}
