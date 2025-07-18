
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body>
        
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
        
        <div style="height: 100vh; display: flex; justify-content: center; align-items: center;">
            <form
                action="login" method="post" style="background-color: white; padding: 40px 30px; border-radius: 20px; box-shadow: 0 0 20px rgba(0,0,0,0.1); width: 100%; max-width: 400px;">
                <h2 style="text-align: center; font-weight: bold; margin-bottom: 30px;">LOGIN</h2>
                <div class="form-group">
                    <input name="txtUseId" type="text" class="form-control" placeholder="Enter User ID"
                           style="background-color: #eee; border: none; border-radius: 25px; padding: 12px 20px; margin-bottom: 20px;">
                </div>
                <div class="form-group">
                    <input name="txtPassword" type="password" class="form-control" placeholder="Password"
                           style="background-color: #eee; border: none; border-radius: 25px; padding: 12px 20px; margin-bottom: 30px;">
                </div>
                <button type="submit"
                        style="width: 100%; background-color: #28a745; border: none; color: white; padding: 14px 0; font-weight: bold; border-radius: 30px;">
                    SIGN IN
                </button>
            </form>
        </div>
        
        <!-- jQuery -->
        <script src="bootstrap/dist/js/bootstrap.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
