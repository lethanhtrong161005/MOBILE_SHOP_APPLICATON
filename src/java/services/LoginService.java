
package services;

import models.dao.UserDAO;
import models.dto.User;

public class LoginService {
    
    private final UserDAO userDAO = new UserDAO();
 
    public User loginWithUserIdAndPassword(String userId, String password) {
        if (userId == null || password == null) {
            return null;
        }
        try {
            User user = userDAO.findByUserId(userId);
            if (user == null) {
                return null;
            }
            int inputPassword;
            try {
                inputPassword = Integer.parseInt(password);
            } catch (NumberFormatException e) {
                System.out.println("password: " + e.getMessage());
                return null; 
            }

            return (inputPassword == user.getPassword()) ? user : null;

        } catch (Exception e) {
            System.err.println("loginWithUserIdAndPassword: " + e.getMessage());
            return null;
        }
        
    }
    
}
