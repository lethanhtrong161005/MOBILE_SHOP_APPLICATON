
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


@WebServlet(name="LoginController", urlPatterns={Const.LOGIN_URL})
public class LoginController extends HttpServlet {
    
    private LoginService loginService = new LoginService();

    
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
