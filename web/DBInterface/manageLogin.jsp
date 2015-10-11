<%-- 
    Document   : loginManagement
    Created on : Jul 25, 2015, 1:41:38 PM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales App Account Management Page</title>
    </head>
    <body onload="return showAlert()">
        <h1>Please enter a email below for an invitation to register:</h1>
        <form name="CreateInvitation" action="${pageContext.request.contextPath}/CreateInvitationServlet" method="POST">
            <input type="text" name="InviteTarget" value="" required/>
            <input type="submit" value="Submit" name="Submit" />
        </form>
        <h1>Please fill the information below to enable or disable a sales app account:</h1>
        <form name="LoginAccountSwitch" action="${pageContext.request.contextPath}/LoginAccountSwitchServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Username of the account to be managed:</td>
                        <td><input type="text" name="SwitchTarget" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Action to be taken:</td>
                        <td>
                            <input type="radio" name="Action" value="0" required/>Disable
                            <br>
                            <input type="radio" name="Action" value="1" />Enable
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" name="Submit" /></td>
                        <td><input type="reset" value="Clear" name="Clear" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <br><a href="${pageContext.request.contextPath}/DBInterface/dbiHome.jsp">Back to Database Management Home</a>
    </body>
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
</html>
