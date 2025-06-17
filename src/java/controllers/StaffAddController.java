
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


@WebServlet(name="StaffAddController", urlPatterns={Const.STAFF_ADD_URL})
public class StaffAddController extends HttpServlet {
    
    private MobileService mobileService = new MobileService();
   

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
        if (errors.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + RedirectUtils.buildMessageRedirectUrl(Const.STAFF_URL, "Add Mobile Success", "success"));
        } else {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher(Const.STAFF_ADD_PAGE).forward(req, resp);
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
