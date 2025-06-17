

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
import utils.RedirectUtils;

@WebServlet(name="StaffDeleteController", urlPatterns={Const.STAFF_DELETE_URL})
public class StaffDeleteController extends HttpServlet {
    
    private MobileService mobileService = new MobileService();

    protected void processRequest(
            HttpServletRequest req, 
            HttpServletResponse resp
    )throws ServletException, IOException 
    {   
        String path = req.getServletPath();
        if(Const.STAFF_DELETE_URL.endsWith(path)){
            if("GET".equalsIgnoreCase(req.getMethod())){
                loadFormDeleteGet(req, resp);
            }else if("POST".equalsIgnoreCase(req.getMethod())){
                deleteMobilePost(req, resp);
            }
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
        } else {
            req.setAttribute("mobile", mobile);
        }

        req.getRequestDispatcher(Const.STAFF_DELETE_PAGE).forward(req, resp);
    }
    
    private void deleteMobilePost(
            HttpServletRequest req, 
            HttpServletResponse resp
    )throws ServletException, IOException 
    {   
        String txtId = req.getParameter("txtId");
        String message = "";
        String type = "";
        System.out.println(txtId);

        if (txtId != null && !txtId.trim().isEmpty()) {
            boolean deleted = mobileService.removeMobileById(txtId);
            if (deleted) {
                message = "Deleted mobile with ID: " + txtId + " successfully.";
                type = "success";
            } else {
                message = "Failed to delete mobile with ID: " + txtId + ".";
                type = "danger";
            }
        } else {
            message = "Invalid mobile ID.";
            type = "danger";
        }

        String redirectUrl = RedirectUtils.buildMessageRedirectUrl(Const.STAFF_URL, message, type);
        resp.sendRedirect(req.getContextPath() + redirectUrl);
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
