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

    @Override
    public boolean add(Mobile mobile) {
        int rowAdded = 0;
        try(Connection connection = DbUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(Query.ADD_MOBILE)){
            
            statement.setString(1, mobile.getMobileId());
            statement.setString(2, mobile.getMobileName());
            statement.setString(3, mobile.getDescription());
            statement.setFloat(4, (float) mobile.getPrice());
            statement.setInt(5, mobile.getYearOfProduction());
            statement.setInt(6, mobile.getQuantity());
            statement.setBoolean(7, mobile.isNotSale());
            
            rowAdded = statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println("add: " + e.getMessage());
        }
        return rowAdded > 0;
    }

    @Override
    public Mobile findById(String id) {
        try ( Connection connection = DbUtils.getConnection();  
            PreparedStatement statement = connection.prepareStatement(Query.FIND_MOBILE_BY_ID)) 
        {

            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Mobile mobile = new Mobile();
                mobile.setMobileId(rs.getString("mobileId"));
                mobile.setMobileName(rs.getString("mobileName"));
                mobile.setDescription(rs.getString("description"));
                mobile.setPrice(rs.getFloat("price"));
                mobile.setQuantity(rs.getInt("quantity"));
                mobile.setYearOfProduction(rs.getInt("yearOfProduction"));
                mobile.setNotSale(rs.getBoolean("notSale"));
                return mobile;
            }
        } catch (Exception e) {
            System.out.println("findById: " + e.getMessage());
        }
        return null; 
    }


    
    
}
