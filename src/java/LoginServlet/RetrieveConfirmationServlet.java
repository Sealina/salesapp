package LoginServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BasicUtility.RandomPasswordGenerator;
import BasicUtility.EmailUtility;
import BasicUtility.LoginAccountManager;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/RetrieveConfirmationServlet"})
public class RetrieveConfirmationServlet extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    @Override
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String email = request.getParameter("username");
        String subject = "GNB Sales App Reset Password";
        String content = "Please click the following link to reset your password:\n" +
                "http://localhost:8080/salesapp/LoginSystem/resetPwd.jsp\n" +
                "Please use the following verification code for your reference:\n";
        String resultMessage = "";
        int isConfirmed = 0;

        try {
            if (LoginAccountManager.RetrieveLoginAccount(email) == null) {
                resultMessage = "This account name does not exist. To register, please follow the registration link.";
            } else {
                //generate random string as password
                String tmpPassword = RandomPasswordGenerator.generatePassword(6, 20);
                LoginAccountManager.ChangePassword(email, tmpPassword);
                EmailUtility.sendEmail(host, port, user, pass, email, subject,
                        content + tmpPassword);
                resultMessage = "The e-mail was sent successfully. Please check your mail box to reset your password.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "There was an error: " + e.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/LoginSystem/retrieveConfirm.jsp").forward(
                    request, response);
        }
    }
}
