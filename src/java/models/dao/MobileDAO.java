/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.dto.Mobile;
import utils.DbUtils;
import utils.Query;

/**
 *
 * @author admin
 */
public class MobileDAO implements I_MobileDAO{
    
    @Override
    public List<Mobile> findAll(){
        List<Mobile> mobiles = new ArrayList<>();
        try(Connection connection = DbUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(Query.FIND_ALL_MOBILE)){
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Mobile m = new Mobile();
                m.setMobileId(rs.getString("mobileId"));
                m.setMobileName(rs.getString("mobileName"));
                m.setDescription(rs.getString("description"));
                m.setPrice(rs.getFloat("price"));
                m.setYearOfProduction(rs.getInt("yearOfProduction"));
                m.setQuantity(rs.getInt("quantity"));
                m.setNotSale(rs.getBoolean("notSale"));
                mobiles.add(m);
            }
            
        }catch(Exception e){
            System.out.println("findAll: " + e.getMessage());
            
        }
        return mobiles;
    }
    
}
