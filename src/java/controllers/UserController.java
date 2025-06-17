/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.dto.Mobile;
import services.MobileService;
import utils.Const;

@WebServlet(name = "UserController", urlPatterns = {Const.HOME_URL})
public class UserController extends HttpServlet {
    
    private MobileService mobileService = new MobileService();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Mobile> mobiles = mobileService.getAllMobile();
        System.out.println(mobiles.size());
        if (mobiles != null && !mobiles.isEmpty()) {
            req.setAttribute("mobiles", mobiles);
        } else {
            req.setAttribute("message", "Mobile list is empty");
            req.setAttribute("type", "warning");
        }
        req.getRequestDispatcher(Const.HOME_PAGE).forward(req, resp);
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
