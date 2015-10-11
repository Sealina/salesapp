<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : manageContact
    Created on : Aug 1, 2015, 5:49:35 AM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Management Page</title>
    </head>
    <body>
        <h1>Please enter the information below to add a contact.</h1>
        <form name="addContact" action="${pageContext.request.contextPath}/CreateContactServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>First Name of the Contact:</td>
                        <td><input type="text" name="firstname" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Last Name of the Contact:</td>
                        <td><input type="text" name="lastname" value="" required/></td>
                    </tr>
                    <tr>
                        <td>Company:</td>
                        <td><input type="text" name="company" value="" required/></td>
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
                        <td>Work Phone:</td>
                        <td><input type="text" name="phone" value="" /></td>
                    </tr>
                    <tr>
                        <td>Mobile:</td>
                        <td><input type="text" name="mobile" value="" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" value="" /></td>
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
                        <td>Ways to send newsletter</td>
                        <td><select name="newsletter">
                                <option disabled selected> -- select an option -- </option>
                                <option></option>
                                <option>Email</option>
                                <option>Work Phone</option>
                                <option>Mobile</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Do not solicit:</td>
                        <td><select name="donotsolicit">
                                <option disabled selected> -- select an option -- </option>
                                <option>No</option>
                                <option>Yes</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Create User:</td>
                        <td><select name="createuser">
                                <option disabled selected> -- select an option -- </option>
                                <option>Current User</option>
                                <option>Administrator</option>
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
</html>
