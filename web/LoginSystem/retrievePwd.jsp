<%-- 
    Document   : retrievePwd
    Created on : Jul 11, 2015, 3:20:01 PM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forget Password</title>
    </head>
    <body>
        <h1>Please enter your email address below to retrieve your password:</h1>
        <form name="retrievePwd" action="../RetrieveConfirmationServlet" method="POST">
            <input type="text" name="username" value="" />
            <input type="submit" value="Submit" name="Submit" />
        </form>
    </body>
</html>
