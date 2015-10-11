<%-- 
    Document   : regComplete
    Created on : Jul 16, 2015, 10:54:35 AM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Complete Page</title>
    </head>
    <body>
        <center>
            Please enter fill the form to confirm your information and begin your usage of the database interface!
        </center>
        <form name="regComplete" action="${pageContext.request.contextPath}/RegCompletionServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username(Email):</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Login" name="Login" /></td>
                        <td><input type="reset" value="Clear" name="Clear" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
