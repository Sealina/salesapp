<%-- 
    Document   : manageTicket
    Created on : Aug 1, 2015, 5:52:37 AM
    Author     : Sealina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Management Page</title>
    </head>
    <body>
        <h1>Please enter the information below to add a ticket.</h1>
        <form name="addTicket" action="${pageContext.request.contextPath}/CreateContactServlet" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Name of the Sales Rep.:</td>
                        <td><input type="text" name="salesrepname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Quote No. :</td>
                        <td><input type="text" name="quote" value="" /></td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td><select name="status">
                                <option disabled selected> -- select an option -- </option>
                                <option></option>
                                <option>Open</option>
                                <option>Close</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><input type="text" name="description" value="" /></td>
                    </tr>
                    <tr>
                        <td>Customer:</td>
                        <td><input type="text" name="customer" value="" /></td>
                    </tr>
                    <tr>
                        <td>Company of the Sales Rep.:</td>
                        <td><input type="text" name="salesrepcompany" value="" /></td>
                    </tr>
                    <tr>
                        <td>Subtype:</td>
                        <td><input type="text" name="subtype" value="" /></td>
                    </tr>
                    <tr>
                        <td>Estimated close date:</td>
                        <td><input type="text" name="estclose" value="" /></td>
                    </tr>
                    <tr>
                        <td>Potential:</td>
                        <td><input type="text" name="potential" value="" /></td>
                    </tr>
                    <tr>
                        <td>Probability:</td>
                        <td><input type="text" name="prob" value="" /></td>
                    </tr>
                    <tr>
                        <td>Summary:</td>
                        <td><input type="text" name="summary" value="" style="height:200px;font-size:14pt;"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" name="regSubmit" /></td>
                        <td><input type="reset" value="Clear" name="regClear" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <br>
        <h2>Please enter a quote no. below to retrieve(/modify) a ticket</h2>
        <form name = "retrieveTicket" action = "${pageContext.request.contextPath}/DBInterface/modifyTicket.jsp">
            Quote No.: <input type="text" name="quote" value="" />
            <input type="submit" value="Submit" name="Submit" />
        </form>
    </body>
</html>
