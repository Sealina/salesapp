<%-- 
    Document   : Home
    Created on : Jul 11, 2015, 10:22:40 AM
    Author     : Sealina
--%>


<%@ page import="java.sql.*" %>
<%@ page import="BasicUtility.LoginAccountManager" %>
<%
    ResultSet currentAccount = LoginAccountManager.RetrieveLoginAccount((String) request.getSession().getAttribute("Username"));
    currentAccount.next();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database Interface</title>
    </head>
    <body>
        <h1>Database Interface Home</h1>
        <h2>Welcome, <%=currentAccount.getString("firstName")%> <%=currentAccount.getString("lastName")%></h2>
        <script>
            switch (<%=currentAccount.getString("accountType")%>) {
                case 0:
                    document.write('<br><a href="${pageContext.request.contextPath}/DBInterface/manageLogin.jsp">Manage Account</a>');
                case 1:
                    document.write('<br><a href="${pageContext.request.contextPath}/DBInterface/manageCustomer.jsp">Manage Customer</a>');
                    document.write('<br><a href="${pageContext.request.contextPath}/DBInterface/manageContact.jsp">Manage Contact</a>');
                case 2:
                    document.write('<br><a href="${pageContext.request.contextPath}/DBInterface/manageTicket.jsp">Manage Ticket</a>');
                default:
                    break;
            }
        </script>
    </body>
</html>
