<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : modifyTicket
    Created on : Aug 10, 2015, 10:05:45 AM
    Author     : HZ
--%>

<sql:query var="ticket" dataSource="jdbc/salesapp">
    SELECT * FROM opportunities WHERE `Quote No` = ? <sql:param value="${param.quote}" />
</sql:query>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Retrieve&Modify Page</title>
    </head>
    <body>
        <h1>Ticket Detail</h1>
        <table border="1">
            <!-- column headers -->
            <tr>
                <c:forEach var="columnName" items="${ticket.columnNames}">
                    <th><c:out value="${columnName}"/></th>
                    </c:forEach>
            </tr>
            <!-- column data -->
            <c:forEach var="row" items="${ticket.rowsByIndex}">
                <tr>
                    <c:forEach var="column" items="${row}">
                        <td><c:out value="${column}"/></td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="Modify this ticket" name="modifyTicket" onclick = "return loadModifyTicket()"/>
        <script>
            function loadModifyTicket()
            {
                document.write('<h1>Please make changes below to modify the ticket</h1>');
                document.write('<form name = "modifyTicket" action="${pageContext.request.contextPath}/CreateContactServlet" method="POST">');
            }
        </script>
        <br><a href="${pageContext.request.contextPath}/DBInterface/dbiHome.jsp">Back to database interface home</a>
        <br><a href="${pageContext.request.contextPath}/DBInterface/manageTicket.jsp">Back to manage ticket</a>
    </body>
</html>
