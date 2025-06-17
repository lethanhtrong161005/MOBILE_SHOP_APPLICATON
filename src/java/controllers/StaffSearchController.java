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
import java.util.List;
import models.dto.Mobile;
import services.MobileService;
import utils.Const;


@WebServlet(name="StaffSearchController", urlPatterns={Const.STAFF_SEARCH_URL})
public class StaffSearchController extends HttpServlet {
   
    private MobileService mobileService = new MobileService();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String path = req.getServletPath();
        if(Const.STAFF_SEARCH_URL.endsWith(path)){
            if("GET".equalsIgnoreCase(req.getMethod())){
                searchMobile(req, resp);
            }
        }
    }
    
    private void searchMobile(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException{
        String txtSearchId = req.getParameter("txtSearchId");
        String txtSearchName = req.getParameter("txtSearchName");
        List<Mobile> mobiles = mobileService.getMobileByIdOrName(txtSearchId, txtSearchName);
        if(mobiles == null){
            req.setAttribute("message", "Mobile is not found");
            req.setAttribute("type", "danger");
        }else{
            req.setAttribute("mobiles", mobiles);
        }
        req.getRequestDispatcher(Const.STAFF_PAGE).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
