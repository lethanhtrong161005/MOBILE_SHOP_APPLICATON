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
import jakarta.servlet.http.HttpSession;
import models.dto.User;
import services.LoginService;
import services.UserService;
import utils.Const;

/**
 *
 * @author Le Thanh Trong
 */
@WebServlet(name="LoginController", urlPatterns={Const.LOGIN_URL})
public class LoginController extends HttpServlet {
    
    private LoginService loginService = new LoginService();

    
    
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
        if(Const.LOGIN_URL.endsWith(path)){
            if("GET".equalsIgnoreCase(req.getMethod())){
                loadFromLogin(req, resp);
            }else if("POST".equalsIgnoreCase(req.getMethod())){
                login(req, resp);
            }
        }else if(Const.LOGOUT_URL.endsWith(path)){
            
        }
    }
    
    //Load Form Login GET
    private void loadFromLogin(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        req.getRequestDispatcher(Const.LOGIN_PAGE).forward(req, resp);
    }
    
    //Login Post
    private void login(
            HttpServletRequest req, 
            HttpServletResponse resp
    )throws ServletException, IOException 
    {
    
        String txtUseId = req.getParameter("txtUseId");
        String txtPassword = req.getParameter("txtPassword");
        User user = loginService.loginWithUserIdAndPassword(txtUseId, txtPassword);
        String url = Const.LOGIN_URL;
        
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", user);
            int roleId = user.getRole();
            if (roleId == 1) {
                resp.sendRedirect(req.getContextPath() 
                                    + Const.STAFF_URL
                                    + "?status=success");
            } else if (roleId == 0) {
                resp.sendRedirect(req.getContextPath() + Const.HOME_URL);
            } else {
                req.setAttribute("message", "Your role is not supported!");
                req.setAttribute("type", "danger");
                req.getRequestDispatcher(Const.LOGIN_PAGE).forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Invalid user id or password!");
            req.setAttribute("type", "danger");
            req.getRequestDispatcher(Const.LOGIN_PAGE).forward(req, resp);
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
