<%-- 
    Document   : regConfirm
    Created on : Jul 11, 2015, 3:35:43 PM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Confirmation Page</title>
    </head>
    <body>
    <center>
        <h3><%=request.getAttribute("Message")%></h3>
    </center>
    <br>
    <a href="registration.jsp">Back to registration page</a>
    <br>
    <a href="index.jsp">Back to login page</a>
    <br>
    <a href="retrievePwd.jsp">Forget Password</a>
    <br>
    </body>
</html>
