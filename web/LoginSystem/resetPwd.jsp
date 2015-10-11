<%-- 
    Document   : resetPwd
    Created on : Jul 22, 2015, 1:48:20 AM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body onload="return showAlert()">
        <h1>Please enter the information below to reset your password:</h1>
        <form name="resetPassword" action="${pageContext.request.contextPath}/RetrieveCompletionServlet" onsubmit="return validateForm()" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username(Email):</td>
                        <td><input type="text" name="username" value="" /></td>
                    </tr>
                    <tr>
                        <td>Verification Code(Please check your mail box):</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td>New password:</td>
                        <td><input type="password" name="newPassword" value="" /></td>
                    </tr>
                    <tr>
                        <td>Re-enter new password:</td>
                        <td><input type="password" name="newPasswordCheck" value="" /></td>
                    </tr>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Clear" name="Clear" /></td>
                        <td><input type="submit" value="Submit" name="Submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <script>
            function validateForm()
            {
                var username = document.forms["resetPassword"]["username"].value;
                var password = document.forms["resetPassword"]["password"].value;
                var pwd = document.forms["resetPassword"]["newPassword"].value;
                var pwdCheck = document.forms["resetPassword"]["newPasswordCheck"].value;
                if (username == null || username == "")
                {
                    alert("Username cannot be empty");
                    return false;
                }
                else if (password == null || password == "")
                {
                    alert("Verification code cannot be empty");
                    return false;
                }
                else if (pwd != pwdCheck) {
                    alert("Please enter the same password!");
                    return false;
                } else {
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
