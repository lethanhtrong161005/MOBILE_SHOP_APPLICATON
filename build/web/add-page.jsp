<%-- 
    Document   : create.jsp
    Created on : Jun 14, 2025, 2:24:21 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.List" %>
<%@ page import="java.util.HashMap" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Mobile</title>
        
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
        <!-- Insert new mobile -->
        <!-- Confirm Modal -->
        <div id="confirmModal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%;
            background-color:rgba(0,0,0,0.5); z-index:9999; justify-content:center; align-items:center;">
            <div style="background:white; padding:30px; border-radius:10px; max-width:500px; text-align:center;">
                <h4>Confirm New Mobile Info</h4>
                <div id="confirmContent" style="text-align:left; margin-top:10px; margin-bottom:20px;"></div>
                <button class="btn btn-success" onclick="submitConfirmed()">Confirm</button>
                <button class="btn btn-danger" onclick="closeConfirm()">Cancel</button>
            </div>
        </div>

        <div class="container" style="margin-top: 30px;">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <h4 class="text-center">Add New Mobile</h4>
                    <form action="staff-add" method="post" class="form-horizontal" id="insertForm" onsubmit="showConfirmForm(event)">
                        <%
                            Map<String, List<String>> errors = (Map<String, List<String>>) request.getAttribute("errors");
                            Map<String, String[]> old = (Map<String, String[]>) request.getAttribute("old");
                        %>

                        <!-- ID -->
                        <div class="form-group">
                            <label for="newId">ID</label>
                            <input name="txtId" type="text" class="form-control" placeholder="ID (ex:MB....)" id="newId"
                                   value="<%= old != null ? old.get("txtId")[0] : "" %>">
                            <%
                                if (errors != null && errors.get("txtId") != null)
                                    for (String msg : errors.get("txtId"))
                                        out.println("<small style='color:red'>" + msg + "</small><br/>");
                            %>
                        </div>

                        <!-- Name -->
                        <div class="form-group">
                            <label for="newName">Name</label>
                            <input name="txtName" type="text" class="form-control" placeholder="Name" id="newName"
                                   value="<%= old != null ? old.get("txtName")[0] : "" %>">
                            <%
                                if (errors != null && errors.get("txtName") != null)
                                    for (String msg : errors.get("txtName"))
                                        out.println("<small style='color:red'>" + msg + "</small><br/>");
                            %>
                        </div>

                        <!-- Year Of Production -->
                        <div class="form-group">
                            <label for="newYear">Year Of Production</label>
                            <input name="txtYearOfProduction" type="number" class="form-control" placeholder="Year Of Production" id="newYear"
                                   value="<%= old != null ? old.get("txtYearOfProduction")[0] : "" %>">
                            <%
                                if (errors != null && errors.get("txtYearOfProduction") != null)
                                    for (String msg : errors.get("txtYearOfProduction"))
                                        out.println("<small style='color:red'>" + msg + "</small><br/>");
                            %>
                        </div>

                        <!-- Price -->
                        <div class="form-group">
                            <label for="newPrice">Price</label>
                            <input name="txtPrice" type="number" step="0.01" class="form-control" placeholder="Price" id="newPrice"
                                   value="<%= old != null ? old.get("txtPrice")[0] : "" %>">
                            <%
                                if (errors != null && errors.get("txtPrice") != null)
                                    for (String msg : errors.get("txtPrice"))
                                        out.println("<small style='color:red'>" + msg + "</small><br/>");
                            %>
                        </div>

                        <!-- Description -->
                        <div class="form-group">
                            <label for="newDesc">Description</label>
                            <input name="txtDescription" type="text" class="form-control" placeholder="Description" id="newDesc"
                                   value="<%= old != null ? old.get("txtDescription")[0] : "" %>">
                            <%
                                if (errors != null && errors.get("txtDescription") != null)
                                    for (String msg : errors.get("txtDescription"))
                                        out.println("<small style='color:red'>" + msg + "</small><br/>");
                            %>
                        </div>

                        <!-- Quantity -->
                        <div class="form-group">
                            <label for="newQty">Quantity</label>
                            <input name="txtQuantity" type="number" class="form-control" placeholder="Quantity" id="newQty"
                                   value="<%= old != null ? old.get("txtQuantity")[0] : "" %>">
                            <%
                                if (errors != null && errors.get("txtQuantity") != null)
                                    for (String msg : errors.get("txtQuantity"))
                                        out.println("<small style='color:red'>" + msg + "</small><br/>");
                            %>
                        </div>

                        <!-- NotSale -->
                        <div class="form-group">
                            <label for="newNotSale">Sale Status</label>
                            <select class="form-control" name="txtNotSale" id="newNotSale">
                                <option value="false" <%= old != null && "false".equals(old.get("txtNotSale")[0]) ? "selected" : "" %>>For Sale</option>
                                <option value="true" <%= old != null && "true".equals(old.get("txtNotSale")[0]) ? "selected" : "" %>>Not For Sale</option>
                            </select>
                            <%
                                if (errors != null && errors.get("txtNotSale") != null)
                                    for (String msg : errors.get("txtNotSale"))
                                        out.println("<small style='color:red'>" + msg + "</small><br/>");
                            %>
                        </div>

                        <!-- Buttons -->
                        <div class="form-group">
                            <div class="col-sm-12" style="padding-left: 0;">
                                <a class="btn btn-default" href="<%=request.getContextPath()%>/staff">Back</a>
                                <button type="submit" class="btn btn-primary">Insert</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>


        <!-- jQuery -->
        <script src="bootstrap/dist/js/bootstrap.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="js/script.js" type="text/javascript"></script>
    </body>
</html>
