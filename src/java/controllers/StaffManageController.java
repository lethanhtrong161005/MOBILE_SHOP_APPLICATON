/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.dto.Mobile;
import services.MobileService;
import utils.Const;
import utils.RedirectUtils;

/**
 *
 * @author Le Thanh Trong
 */
@WebServlet(name="StaffManageController", urlPatterns={Const.STAFF_URL, 
                                                })
public class StaffManageController extends HttpServlet {
    
    private MobileService mobileService = new MobileService();
    
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(
            HttpServletRequest req, 
            HttpServletResponse resp
    )
    throws ServletException, IOException 
    {
       String path = req.getServletPath();
       if(Const.STAFF_URL.endsWith(path)){
           loadMobileList(req, resp);
       }else if(Const.STAFF_DELETE_URL.endsWith(path)){
           if("GET".equalsIgnoreCase(req.getMethod())){
               resp.sendRedirect(req.getContextPath() + Const.STAFF_DELETE_URL);
           }
       }
           
    }
    
    
    //Load Mobile List
    private void loadMobileList(
            HttpServletRequest req, 
            HttpServletResponse resp
    )throws ServletException, IOException
    {
        List<Mobile> mobiles = mobileService.getAllMobile();
        
        if (mobiles != null && !mobiles.isEmpty()) {
            req.setAttribute("mobiles", mobiles);
        } else {
            req.setAttribute("message", "Mobile list is empty");
            req.setAttribute("type", "warning");
        }
        req.getRequestDispatcher(Const.STAFF_PAGE).forward(req, resp);
    }
 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
