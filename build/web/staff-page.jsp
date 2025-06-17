
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.List" %>
<%@ page import="models.dto.Mobile"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff</title>

    <link rel="stylesheet" href="bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
    
    <%
    String status = (String)request.getParameter("status");
    if(status != null){
    %>
    <div class="alert alert-success alert-dismissible fade in text-center d-flex align-items-center justify-content-center" 
         role="alert" 
         id="autoDismissAlert" 
         style="
            position: fixed;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 100%;
            max-width: 600px;
            z-index: 9999;
            margin: 0 auto;
            border-radius: 0;
            height: 60px;
            display: flex;
            align-items: center;
            justify-content: center;
         ">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close" 
                style="position: absolute; right: 10px; top: 10px;">&times;</button>
        <strong style="width: 100%; text-align: center;">Login Success!</strong> 
    </div>
    <%
        }
    %>


    <!-- Header -->
    <header class="header">
        <div class="inner-wrap">
            <nav class="navbar navbar-default navbar-static-top" style="padding: 15px 20px;">
                <div class="container-fluid">
                    <!-- Logo -->
                    <div class="navbar-header" style="margin-right: 20px;">
                        <a style="font-weight: 400; font-size: 26px;" class="navbar-brand" href="#">Mobile Shop</a>
                    </div>

                    <!-- Menu trái -->
                    <ul class="nav navbar-nav" style="font-size: 16px;">
                        <li><a href="home.html">Home</a></li>
                        <li><a href="#">Blog</a></li>
                        <li><a href="./staff">Staff Page</a></li>
                    </ul>

                    <!-- Menu phải -->
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <h4 class="navbar-text" style="margin-right: 30px;">Welcome To Staff</h4>
                        </li>
                        <li>
                            <a href="./logout" class="navbar-btn"
                                style="padding: 6px 10px; margin-top: 8px; color: white; background-color: #5cb85c; border-radius: 4px;">
                                <span class="glyphicon glyphicon-log-out"></span> Logout
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

    <!-- Title Page -->
    <div class="title-des" style="margin-bottom: 25px">
        <div class="container">
            <div class="inner-wrap">
                <h1 style="margin-bottom: 15px;">Welcome To Staff</h1>
                <a href="<%= request.getContextPath() %>/staff-add" class="btn btn-primary w-100">Create New</a>
            </div>
        </div>
    </div>
        
    <!-- End Title Page -->


    <!-- Section One -->
    <div class="container">
        <!-- Search Form -->
        <form action="staff-search" method="get" class="row align-items-center" style="margin-bottom: 20px;" onsubmit="searchMobile(); return false;">
            <div class="col-sm-3">
                <input type="text" id="searchId" class="form-control" placeholder="Search by ID" name="txtSearchId">
            </div>
            <div class="col-sm-3">
                <input type="text" id="searchName" class="form-control" placeholder="Search by Name" name="txtSearchName">
            </div>
            <div class="col-sm-2">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </form>


        <!-- Data Grid -->
        <table class="table table-bordered table-hover" id="mobileTable">
            <thead>
                <tr class="info">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Year Of Production</th>
                    <th>Price ($)</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Not Sale</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Mobile> mobiles = (List<Mobile>) request.getAttribute("mobiles");
                        if (mobiles != null) {
                            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
                            formatter.setMinimumFractionDigits(2);
                            formatter.setMaximumFractionDigits(2);
                        
                            for (Mobile item : mobiles) {
                %>
                <tr>
                    <td><%= item.getMobileId() %></td>
                    <td><%= item.getMobileName() %></td>
                    <td><%= item.getYearOfProduction() %></td>
                    <td><%= formatter.format(item.getPrice()) %></td>
                    <td><%= item.getDescription() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.isNotSale() %></td>
                    <td>
                        <form action="<%= request.getContextPath() %>/staff-delete" method="GET" style="display:inline;">
                                    <input type="hidden" name="txtId" value="<%= item.getMobileId() %>" />
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                        <form action="<%= request.getContextPath() %>/staff-update" method="GET" style="display:inline;">
                                    <input type="hidden" name="txtId" value="<%= item.getMobileId() %>" />
                                    <button type="submit" class="btn btn-success btn-sm">Update</button>
                        </form>
                        
                    </td>
                </tr>
                <%
                            }
                        }
                %>
                
            </tbody>
        </table>
    </div>
    <!-- End Section One -->

    <!-- jQuery -->
    <script src="bootstrap/dist/js/bootstrap.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="js/script.js" type="text/javascript"></script>
</body>

</html>
