package LoginServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BasicUtility.LoginAccountManager;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sealina
 */
@WebServlet(urlPatterns = {"/RegCompletionServlet"})
public class RegCompletionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String resultMessage = "";
        String redirect = "/LoginSystem/regCompletion.jsp";

        try {
            ResultSet result = LoginAccountManager.RetrieveLoginAccount(username);
            if (result == null)
            {
                resultMessage = "Invalid username! Please use a registered email address for login.";
            }
            else
            {
                result.next();
                String correctPassword = result.getString("password");
                int isRegistered = result.getInt("isRegistered");
                
                if (isRegistered == 1)
                {
                    resultMessage = "This account is already registered, please use the login form on welcome page to login.";
                }
                else if (!password.equals(correctPassword))
                {
                    resultMessage = "Incorrect combination of username and password.";
                }
                else
                {
                    
                    LoginAccountManager.CompleteRegistration(username);
                    redirect = "/DBInterface/dbiHome.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "There was an error: " + e.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            request.setAttribute("Username", username);
            getServletContext().getRequestDispatcher(redirect).forward(
                    request, response);
        }
    }
}
