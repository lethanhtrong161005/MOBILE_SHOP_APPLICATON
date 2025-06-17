
package utils;


public interface Query {
    
    
    public final String FIND_BY_USER_ID = "SELECT * FROM Users WHERE userId = ?";
    
    
    public final String FIND_ALL_MOBILE = "SELECT * FROM Mobiles";
    
    public final String FIND_MOBILE_BY_ID = "SELECT * FROM Mobiles WHERE mobileId = ?";
    
    public final String FIND_MOBILE_BY_ID_OR_NAME = 
    "SELECT * FROM Mobiles WHERE mobileId COLLATE Latin1_General_CI_AI = ? OR mobileName COLLATE Latin1_General_CI_AI = ?";

    public final String ADD_MOBILE = "INSERT INTO Mobiles (mobileId, mobileName, description, price, yearOfProduction, quantity, notSale)VALUES(?,?,?,?,?,?,?)";
    
    public final String REMOVE_MOBILE_BY_ID = "DELETE FROM Mobiles WHERE mobileId = ?";
    
    public final String UPDATE_MOBILE_BY_ID = "UPDATE Mobiles SET price=?, description=?, quantity=?, notSale=? WHERE mobileId=?";
    
    
   
}
