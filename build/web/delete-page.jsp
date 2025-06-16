<%-- 
    Document   : delete-page
    Created on : Jun 15, 2025, 2:16:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete</title>
        
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
        
        <div class="container">
            <h2>Confirm Delete</h2>
            <p><strong>ID:</strong> ${mobile.mobileId}</p>
            <p><strong>Name:</strong> ${mobile.mobileName}</p>
            <p><strong>Year:</strong> ${mobile.yearOfProduction}</p>
            <p><strong>Price:</strong> ${mobile.price}</p>
            <p><strong>Description:</strong> ${mobile.description}</p>
            <p><strong>Quantity:</strong> ${mobile.quantity}</p>
            <p><strong>Status:</strong> ${mobile.notSale ? "Not For Sale" : "For Sale"}</p>

            <form action="staff-delete" method="post">
                <input type="hidden" name="txtId" value="${mobile.mobileId}">
                <button type="submit" class="btn btn-danger">Confirm Delete</button>
                <a href="./staff" class="btn btn-secondary">Cancel</a>
            </form>
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
