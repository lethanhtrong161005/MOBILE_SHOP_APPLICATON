/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.dto.User;
import utils.DbUtils;
import utils.Query;

/**
 *
 * @author Le Thanh Trong
 */
public class UserDAO {
    
    public User findByUserId(String userId){       
        User user = new User();
        try(Connection connection = DbUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(Query.FIND_BY_USER_ID)){
            
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                user.setUserId(rs.getString("userId"));
                user.setPassword(rs.getInt("password"));
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getInt("role"));
            }
        }catch(Exception e){
            System.out.println("findByUserId: " + e.getMessage());
        }
        return user;
    }
    
}
