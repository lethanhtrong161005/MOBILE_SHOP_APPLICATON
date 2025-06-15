/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author Le Thanh Trong
 */
public interface Const {
    

//    Page URL
    public final String DEFAULT_URL = "/";
    public final String LOGIN_URL = "/login";
    
    public final String STAFF_URL ="/staff";
    public final String STAFF_SEARCH_URL = "/staff/search";
    public final String STAFF_ADD_URL = "/staff-add";
    
    public final String HOME_URL = "/home";
    public final String LOGOUT_URL = "/logout";
    
//    End Page URL
    

//    Page Name
    public final String LOGIN_PAGE = "login.jsp";
    public final String STAFF_PAGE = "staff-page.jsp";
    public final String HOME_PAGE = "home-page.jsp";
    public final String STAFF_ADD_PAGE = "add-page.jsp";
//    End Page Name
    
    
//  Pattern
    public final String MOBILE_ID_PATTERN = "^(?i)mb\\w*$";
    
    
}
