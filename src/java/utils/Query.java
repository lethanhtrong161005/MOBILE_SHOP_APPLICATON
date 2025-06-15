/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author admin
 */
public interface Query {
    
    
    public final String FIND_BY_USER_ID = "SELECT * FROM Users WHERE userId = ?";
    
    
    public final String FIND_ALL_MOBILE = "SELECT * FROM Mobiles";
    
    public final String FIND_MOBILE_BY_ID = "SELECT * FROM Mobiles WHERE mobileId = ?";
    
    public final String ADD_MOBILE = "INSERT INTO Mobiles (mobileId, mobileName, description, price, yearOfProduction, quantity, notSale)VALUES(?,?,?,?,?,?,?)";
   
}
