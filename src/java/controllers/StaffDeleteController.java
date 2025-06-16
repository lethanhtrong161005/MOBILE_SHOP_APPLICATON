/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dto.Mobile;
import services.MobileService;
import utils.Const;

/**
 *
 * @author admin
 */
@WebServlet(name="StaffDeleteController", urlPatterns={Const.STAFF_DELETE_URL})
public class StaffDeleteController extends HttpServlet {
    
    private MobileService mobileService = new MobileService();
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, 
            HttpServletResponse resp
    )throws ServletException, IOException 
    {
        String path = req.getServletPath();
        if(Const.STAFF_DELETE_URL.endsWith(path)){
            
        }
    }
    
    private void loadFormDeleteGet(
            HttpServletRequest req, 
            HttpServletResponse resp
    )throws ServletException, IOException
    {
        String txtId = req.getParameter("txtId");
        Mobile mobile = mobileService.searchByID(txtId);
        if (mobile == null || mobile.getMobileId() == null) {
            req.setAttribute("message", "Mobile not found");
            req.setAttribute("type", "danger");
            req.getRequestDispatcher(Const.STAFF_PAGE).forward(req, resp);
            return;
        }
        
        req.setAttribute("mobile", mobile);
        req.getRequestDispatcher(Const.STAFF_DELETE_PAGE).forward(req, resp); 
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
