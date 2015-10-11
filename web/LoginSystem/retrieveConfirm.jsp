<%-- 
    Document   : retrieveConfirm
    Created on : Jul 21, 2015, 3:09:24 PM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retrieve Password Confirmation Page</title>
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
