<%-- 
    Document   : staff-page
    Created on : Jun 11, 2025, 1:44:23 PM
    Author     : Le Thanh Trong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        String msg = request.getParameter("message");
        String alertType = request.getParameter("type");

        if (msg == null || alertType == null) {
            msg = (String) request.getAttribute("message");
            alertType = (String) request.getAttribute("type");
        }

        if (msg != null && alertType != null) {
    %>
        <div class="alert alert-<%= alertType %> alert-dismissible text-center">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">&times;</button>
            <strong><%= msg %></strong>
        </div>
    <%
        }
    %>



    <!-- Title Page -->
    <div class="title-des">
        <div class="container">
            <div class="inner-wrap">
                <h1 style="margin-bottom: 20px;">Welcome To Staff</h1>
            </div>
        </div>s
    </div>
    <!-- End Title Page -->


    <!-- Section One -->
    <div class="container">
        <!-- Search Form -->
        <form class="row align-items-center" style="margin-bottom: 20px;" onsubmit="searchMobile(); return false;">
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
                <!-- Dữ liệu mẫu -->
                <c:forEach items="${mobiles}" var="item">
                    <tr>
                        <td>${item.mobileId}</td>
                        <td>${item.mobileName}</td>
                        <td>${item.yearOfProduction}</td>
                        <td contenteditable="true">${item.price}</td>
                        <td contenteditable="true">${item.description}</td>
                        <td contenteditable="true">${item.quantity}</td>
                        <td contenteditable="true">${item.notSale}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="deleteRow(this)">Delete</button>
                            <button class="btn btn-success btn-sm" onclick="updateRow(this)">Update</button>
                        </td>
                    </tr>
                    <!-- More rows will be added dynamically -->
                </c:forEach>
                
            </tbody>
        </table>

        <!-- Insert new mobile -->
        <h4>Add New Mobile</h4>
        <form class="form-inline" onsubmit="insertMobile(event)">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="ID" id="newId" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Name" id="newName" required>
            </div>
            <div class="form-group">
                <input type="number" class="form-control" placeholder="Price" id="newPrice" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Description" id="newDesc">
            </div>
            <div class="form-group">
                <input type="number" class="form-control" placeholder="Quantity" id="newQty">
            </div>
            <div class="form-group">
                <select class="form-control" id="newNotSale">
                    <option value="false">For Sale</option>
                    <option value="true">Not For Sale</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Insert</button>
        </form>
    </div>
    <!-- End Section One -->












    <!-- jQuery -->
    <script src="bootstrap/dist/js/bootstrap.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="js/script.js"></script>
</body>

</html>
