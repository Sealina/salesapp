/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBInterfaceServelet;

import BasicUtility.CustomerManager;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HZ
 */
@WebServlet(name = "CreateCustomerServlet", urlPatterns = {"/CreateCustomerServlet"})
public class CreateCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateobj = new Date();
        String createDate =df.format(dateobj);
        String resultMessage = "";
        String redirect = "/DBInterface/manageCustomer.jsp";

        try {
            CustomerManager.AddCustomer(createDate,
                    request.getParameter("customer"),
                    request.getParameter("city"),
                    request.getParameter("state"),
                    request.getParameter("country"),
                    request.getParameter("phone"),
                    request.getParameter("type"),
                    request.getParameter("subtype"),
                    request.getParameter("status"),
                    request.getParameter("accountmanager"),
                    request.getParameter("owner"));
            resultMessage = "Entry added!";
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "There was an error: " + e.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher(redirect).forward(
                    request, response);
        }
    }
}
