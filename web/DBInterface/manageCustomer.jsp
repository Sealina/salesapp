<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : manageCustomer
    Created on : Jul 25, 2015, 4:09:11 PM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Management Page</title>
    </head>
    <body onload="return showAlert()">
        <h1>Please fill in one of the following table to retrieve or add a customer.</h1>
        <form name="addCustomer" action="${pageContext.request.contextPath}/CreateCustomerServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Customer:</td>
                        <td><input type="text" name="customer" value="" required/></td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input type="text" name="city" value="" /></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td><input type="text" name="state" value="" /></td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td><input type="text" name="country" value="" /></td>
                    </tr>
                    <tr>
                        <td>Main Phone:</td>
                        <td><input type="text" name="phone" value="" /></td>
                    </tr>
                    <tr>
                        <td>Type:</td>
                        <td><select name="type">
                                <option disabled selected> -- select an option -- </option>
                                <option></option>
                                <option>Competitor</option>
                                <option>Customer</option>
                                <option>Influencer</option>
                                <option>Lead</option>
                                <option>Prospect</option>
                                <option>Vendor</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Sub-Type:</td>
                        <td><input type="text" name="subtype" value="" /></td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td><select name="status">
                                <option disabled selected> -- select an option -- </option>
                                <option></option>
                                <option>Active</option>
                                <option>Inactive</option>
                                <option>New</option>
                            </select>
                        </td>
                    </tr>
                    <sql:query var="AccountManager" dataSource="jdbc/salesapp">
                        SELECT firstName, lastName FROM loginaccount WHERE accountType = 0
                    </sql:query>
                    <tr>
                        <td>Account Manager:</td>
                        <td><select name="accountmanager">
                                <option disabled selected> -- select an option -- </option>
                                <option></option>
                                <c:forEach var="row" items="${AccountManager.rows}">
                                    <option>${row.lastName}, ${row.firstName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Owner:</td>
                        <td><select name="owner">
                                <option disabled selected> -- select an option -- </option>
                                <option></option>
                                <option>Everyone</option>
                                <c:forEach var="row" items="${AccountManager.rows}">
                                    <option>${row.lastName}, ${row.firstName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" name="regSubmit" /></td>
                        <td><input type="reset" value="Clear" name="regClear" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
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
