
package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import models.dto.Mobile;
import services.MobileService;
import utils.Const;
import utils.RedirectUtils;

@WebServlet(name="StaffUpdateController", urlPatterns={Const.STAFF_UPDATE_URL})
public class StaffUpdateController extends HttpServlet {
    
    private MobileService mobileService = new MobileService();
     
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String path = req.getServletPath();
        if(Const.STAFF_UPDATE_URL.endsWith(path)){
            if("GET".equalsIgnoreCase(req.getMethod())){
                loadOldDetail(req, resp);
            }else if("POST".equalsIgnoreCase(req.getMethod())){
                updateMobile(req, resp);
            }
        }
    }
    
    private void loadOldDetail(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException{
        String txtId = req.getParameter("txtId");
        Mobile mobile = mobileService.searchByID(txtId.trim());
        req.setAttribute("mobile", mobile);
        req.getRequestDispatcher(Const.STAFF_UPDATE_PAGE).forward(req, resp);
    }
    
    private void updateMobile(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    String txtId = req.getParameter("txtId");
    String txtPrice = req.getParameter("txtPrice");
    String txtDescription = req.getParameter("txtDescription");
    String txtQuantity = req.getParameter("txtQuantity");
    String txtNotSale = req.getParameter("txtNotSale");

    // Gọi update
    HashMap<String, List<String>> errors = mobileService.updateMobile(txtId, txtPrice, txtDescription, txtQuantity, txtNotSale);

    // Lưu dữ liệu cũ nhập lại nếu có lỗi
    req.setAttribute("errors", errors);
    req.setAttribute("old", req.getParameterMap()); // lưu input người dùng

    // Lấy lại mobile để hiển thị form
    Mobile mobile = mobileService.searchByID(txtId.trim());
    req.setAttribute("mobile", mobile);

    if (errors.isEmpty()) {
        resp.sendRedirect(req.getContextPath() + RedirectUtils.buildMessageRedirectUrl(
                Const.STAFF_URL,
                "Update Success",
                "success"));
    } else {
        req.getRequestDispatcher(Const.STAFF_UPDATE_PAGE).forward(req, resp);
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
