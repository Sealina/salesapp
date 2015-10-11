<%-- 
    Document   : registration
    Created on : Jul 11, 2015, 3:18:00 PM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Registration</h1>
        <form name="registration" action="${pageContext.request.contextPath}/RegConfirmationServlet" onsubmit="return validateReg()" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>First Name:</td>
                        <td><input type="text" name="firstName" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Last Name:</td>
                        <td><input type="text" name="lastName" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Username (Please use a valid email address):</td>
                        <td><input type="text" name="username" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Re-enter Password:</td>
                        <td><input type="password" name="passwordCheck" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Invitation Code:</td>
                        <td><input type="password" name="invitationCode" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Are you an internal employee or external employee?</td>
                        <td>
                            <input type="radio" name="accountType" value="1" required>internal
                            <br>
                            <input type="radio" name="accountType" value="2">external
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" name="regSubmit" /></td>
                        <td><input type="reset" value="Clear" name="regClear" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <script>
            function validateReg()
            {
                var pwd = document.forms["registration"]["password"].value;
                var pwdCheck = document.forms["registration"]["passwordCheck"].value;
                if (pwd !== pwdCheck) {
                    alert("Please enter the same password!");
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </body>
</html>
