
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="models.dto.Mobile"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete</title>
        
        <link rel="stylesheet" href="bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>  
        <div class="container" style="height: 100%;">
            <div class="row" style="display: table; height: 100%; width: 100%;">
                <div class="col-md-6 col-md-offset-3" style="display: table-cell; vertical-align: middle;">
                    <%
                        Mobile mobile = (Mobile) request.getAttribute("mobile");
                        if (mobile != null) {
                            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
                            formatter.setMinimumFractionDigits(2);
                            formatter.setMaximumFractionDigits(2);
                    %>
                    <div class="panel panel-danger">
                        <div class="panel-heading text-center">
                            <h3>Confirm Delete</h3>
                        </div>
                        <div class="panel-body">
                            <p><strong>ID:</strong> <%= mobile.getMobileId() %></p>
                            <p><strong>Name:</strong> <%= mobile.getMobileName() %></p>
                            <p><strong>Year:</strong> <%= mobile.getYearOfProduction() %></p>
                            <p><strong>Price:</strong> <%= formatter.format(mobile.getPrice()) %></p>
                            <p><strong>Description:</strong> <%= mobile.getDescription() %></p>
                            <p><strong>Quantity:</strong> <%= mobile.getQuantity() %></p>
                            <p><strong>Status:</strong> <%= mobile.isNotSale() ? "Not For Sale" : "For Sale" %></p>

                            <form action="staff-delete" method="post" class="text-center">
                                <input type="hidden" name="txtId" value="<%= mobile.getMobileId() %>">
                                <button type="submit" class="btn btn-danger">Confirm Delete</button>
                                <a href="./staff" class="btn btn-default">Cancel</a>
                            </form>
                        </div>
                    </div>
                    <%
                        }else{
                            String msg = (String)request.getAttribute("message");
                            String alertType = (String)request.getAttribute("type");
                            if (msg != null && alertType != null){
                    %>
                    <div class="alert alert-<%= alertType %> alert-dismissible text-center" role="alert" style="padding: 8px 15px; margin: 10px auto; max-width: 500px; font-size: 13px; position: relative;">
                        <a href="./staff" class="close" aria-label="Close" title="Back to Staff Page" style="position: absolute; top: 5px; right: 10px;">&times;</a>
                        <strong><%= msg %></strong>
                    </div>
                    <%
                            }
                        }
                    %>
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
