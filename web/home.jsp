
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.List" %>
<%@ page import="models.dto.Mobile"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
        <!-- Header -->
        <header class="header">
            <div class="inner-wrap">
                <nav class="navbar navbar-default navbar-static-top" style="padding: 15px 20px;">
                    <div class="container-fluid">
                        <!-- Logo -->
                        <div class="navbar-header" style="margin-right: 20px;">
                            <a style="font-weight: 400; font-size: 26px;" class="navbar-brand" href="./home">Mobile Shop</a>
                        </div>

                        <!-- Menu trái -->
                        <ul class="nav navbar-nav" style="font-size: 16px;">
                            <li><a href="./home">Home</a></li>
                        </ul>

                        <!-- Menu phải -->
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="#" class="navbar-btn"
                                   style="padding: 6px 10px; margin-top: 8px; color: white; background-color: #5cb85c; border-radius: 4px;">
                                    <span class="glyphicon glyphicon-log-out"></span> Logout
                                </a>
                            </li>
                            <li>
                                <a href="cart.html" class="navbar-btn"
                                   style="padding: 6px 10px; margin-top: 8px; margin-left: 10px;">
                                    <span class="glyphicon glyphicon-shopping-cart"></span> Cart <span class="badge"
                                                                                                       id="cart-count">0</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
        <!-- End Header -->
        
        <%
            String msg = (String)request.getAttribute("message");
            String alertType = (String)request.getAttribute("type");
            if(msg == null && alertType == null){
                msg = (String)request.getParameter("message");
                alertType = (String)request.getParameter("type");
            }
            if (msg != null && alertType != null) {
        %>
            <div class="alert alert-<%= alertType %> alert-dismissible text-center" role="alert" style="padding: 8px 15px; margin: 10px auto; max-width: 500px; font-size: 13px; position: relative;">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close" style="position: absolute; top: 5px; right: 10px;">&times;</button>
                <strong><%= msg %></strong>
            </div>

        <%
            }
        %>
        
        <!-- Main Section -->
        <div class="container">
            <h2>Search Devices by Price</h2>
            <form class="form-inline" id="search-form">
                <div class="form-group">
                    <label for="minPrice">Min Price:</label>
                    <input type="number" class="form-control" id="minPrice" placeholder="Min">
                </div>
                <div class="form-group">
                    <label for="maxPrice">Max Price:</label>
                    <input type="number" class="form-control" id="maxPrice" placeholder="Max">
                </div>
                <button type="button" class="btn btn-primary" onclick="searchDevices()">Search</button>
            </form>

            <hr>
            <div id="device-list">
                <!-- Data Grid -->
                <table class="table table-bordered table-hover" id="mobileTable">
                    <thead>
                        <tr class="info">
                            <th>Name</th>
                            <th>Year Of Production</th>
                            <th>Price ($)</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  
                            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
                            formatter.setMinimumFractionDigits(2);
                            formatter.setMaximumFractionDigits(2);
                            
                            List<Mobile> mobiles = (List<Mobile>)request.getAttribute("mobiles");
                            for(Mobile item : mobiles){
                        %>
                                <tr>
                                    <td><%=item.getMobileName()%></td>
                                    <td><%= item.getYearOfProduction() %></td>
                                    <td><%= formatter.format(item.getPrice()) %></td>
                                    <td><%= item.getDescription() %></td>
                                    <td>
                                        <button class="btn btn-success btn-sm" onclick="updateRow(this)">Add to cart</button>
                                    </td>
                                </tr>
                        <%}%>
                        
                    </tbody>
                </table>
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
