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
import java.util.HashMap;
import java.util.List;
import services.MobileService;
import utils.Const;
import utils.RedirectUtils;

/**
 *
 * @author admin
 */
@WebServlet(name="StaffAddController", urlPatterns={Const.STAFF_ADD_URL})
public class StaffAddController extends HttpServlet {
    
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
    )throws ServletException, IOException 
    {
        String path = req.getServletPath();
        if(Const.STAFF_ADD_URL.endsWith(path)){
            if("GET".equalsIgnoreCase(req.getMethod())){
                req.getRequestDispatcher(Const.STAFF_ADD_PAGE).forward(req, resp);
            }else if("POST".equalsIgnoreCase(req.getMethod())){
                addMobile(req, resp);
            }
        }
    }
    
    
    private void addMobile(
            HttpServletRequest req, 
            HttpServletResponse resp
    )throws ServletException, IOException{
        
        String txtId = req.getParameter("txtId");
        String txtName = req.getParameter("txtName");
        String txtYearOfProduction = req.getParameter("txtYearOfProduction");
        String txtPrice = req.getParameter("txtPrice");
        String txtDescription = req.getParameter("txtDescription");
        String txtQuantity = req.getParameter("txtQuantity");
        String txtNotSale = req.getParameter("txtNotSale");
        
        HashMap<String, List<String>> errors = mobileService.insertMobile(txtId, txtName, txtYearOfProduction, txtPrice, txtDescription, txtQuantity, txtNotSale);
        if(errors != null){
            req.setAttribute("errors", errors);
            req.getRequestDispatcher(Const.STAFF_ADD_PAGE).forward(req, resp);
        }else{
            resp.sendRedirect(req.getContextPath() + RedirectUtils.buildMessageRedirectUrl(Const.STAFF_URL, "Add Mobile Success", "success"));
        }        
        
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
