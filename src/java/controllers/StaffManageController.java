
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


@WebServlet(name="StaffManageController", urlPatterns={Const.STAFF_URL, 
                                                })
public class StaffManageController extends HttpServlet {
    
    private MobileService mobileService = new MobileService();
    
   
 
    protected void processRequest(
            HttpServletRequest req, 
            HttpServletResponse resp
    )
    throws ServletException, IOException 
    {
       String path = req.getServletPath();
       if(Const.STAFF_URL.endsWith(path)){
           loadMobileList(req, resp);
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
            // forward để giữ list
            req.setAttribute("mobiles", mobiles);
        } else {
            // redirect để gửi message bằng query param
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
