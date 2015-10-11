<%-- 
    Document   : index
    Created on : Jul 8, 2015, 2:20:01 AM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body onload="return showAlert()">
        <h1>Welcome to GNB Sales Database Management Application</h1>
        <form name="loginInfo" action="${pageContext.request.contextPath}/LoginVerificationServlet" onsubmit="return validateForm()" method="POST">
            <table border="0">
                <thead>
                    <tr>
                        <th>Login</th>
                        <th></th>
                    </tr>
                </thead>
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
        <a href="registration.jsp">Registration</a>
        <a href="retrievePwd.jsp">Forget Password</a>
        <script>
            function validateForm()
            {
                var username = document.forms["loginInfo"]["username"].value;
                var password = document.forms["loginInfo"]["password"].value;
                if (username == null || username == "")
                {
                    alert("Username cannot be empty");
                    return false;
                }
                else if (password == null || password == "")
                {
                    alert("Password cannot be empty");
                    return false;
                }
                else
                {
                    return true;
                }
            }
        </script>
        <script>
            function showAlert()
            {
                var message = '${Message}';
                if (message)
                {
                    alert(message);
                }
                else
                {
                    return true;
                }
            }
        </script>
    </body>
</html>
