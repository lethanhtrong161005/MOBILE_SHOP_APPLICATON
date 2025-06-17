<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.dto.Mobile" %>
<%@ page import="java.util.Map, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Mobile</title>
    <link rel="stylesheet" href="bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
   
    <div class="container" style="margin-top: 30px;">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <h4 class="text-center">Update Mobile Info</h4>
                <%
                    Map<String, List<String>> errors = (Map<String, List<String>>) request.getAttribute("errors");
                    Map<String, String[]> old = (Map<String, String[]>) request.getAttribute("old");
                    Mobile mobile = (Mobile) request.getAttribute("mobile");

                    if (mobile == null) {
                %>
                <div class="alert alert-danger text-center">Mobile not found.</div>
                <a href="./staff" class="btn btn-default">Back</a>
                <%
                    } else {
                %>
                <form action="staff-update" method="post" class="form-horizontal">
                    
                    <div class="form-group">
                        <label for="txtId">ID</label>
                        <input type="text" name="txtId" class="form-control" id="txtId"
                               value="<%= mobile.getMobileId() %>" readonly>
                    </div>

                    <!-- Price -->
                    <div class="form-group">
                        <label for="txtPrice">Price</label>
                        <input name="txtPrice" type="number" step="0.01" class="form-control"
                               value="<%= old != null ? old.get("txtPrice")[0] : mobile.getPrice() %>">
                        <%
                            if (errors != null && errors.get("txtPrice") != null)
                                for (String msg : errors.get("txtPrice"))
                                    out.println("<small style='color:red'>" + msg + "</small><br/>");
                        %>
                    </div>

                    <!-- Description -->
                    <div class="form-group">
                        <label for="txtDescription">Description</label>
                        <input name="txtDescription" type="text" class="form-control"
                               value="<%= old != null ? old.get("txtDescription")[0] : mobile.getDescription() %>">
                        <%
                            if (errors != null && errors.get("txtDescription") != null)
                                for (String msg : errors.get("txtDescription"))
                                    out.println("<small style='color:red'>" + msg + "</small><br/>");
                        %>
                    </div>

                    <!-- Quantity -->
                    <div class="form-group">
                        <label for="txtQuantity">Quantity</label>
                        <input name="txtQuantity" type="number" class="form-control"
                               value="<%= old != null ? old.get("txtQuantity")[0] : mobile.getQuantity() %>">
                        <%
                            if (errors != null && errors.get("txtQuantity") != null)
                                for (String msg : errors.get("txtQuantity"))
                                    out.println("<small style='color:red'>" + msg + "</small><br/>");
                        %>
                    </div>

                    <!-- NotSale -->
                    <div class="form-group">
                        <label for="txtNotSale">Sale Status</label>
                        <select class="form-control" name="txtNotSale">
                            <option value="false" <%= (old != null && "false".equals(old.get("txtNotSale")[0])) || (!mobile.isNotSale()) ? "selected" : "" %>>For Sale</option>
                            <option value="true" <%= (old != null && "true".equals(old.get("txtNotSale")[0])) || mobile.isNotSale() ? "selected" : "" %>>Not For Sale</option>
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
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </div>
                </form>
                <% } %>
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
