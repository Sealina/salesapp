package LoginServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BasicUtility.LoginAccountManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sealina
 */
@WebServlet(urlPatterns = {"/RetrieveCompletionServlet"})
public class RetrieveCompletionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String resultMessage = "";
        String redirect = "/LoginSystem/resetPwd.jsp";
        
        try {
            int result = LoginAccountManager.VerifyLogin(username, password);
            if (result == 0)
            {
                resultMessage = "This username does not exist!";
            }
            else if (result == 1)
            {
                resultMessage = "This account has not finish registration!";
            }
            else if (result == 2)
            {
                resultMessage = "Incorrect verification code!";
            }
            else if (LoginAccountManager.ChangePassword(username, newPassword) != 1)
            {
                resultMessage = "Database error! Unable to reset password. Please contact database administrator.";
            }
            else
            {
                resultMessage = "Password successfully reset, please proceed to login.";
                redirect = "/LoginSystem/loginHome.jsp";
            }
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
