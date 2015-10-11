/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBInterfaceServelet;

import BasicUtility.EmailUtility;
import BasicUtility.InvitationManager;
import BasicUtility.LoginAccountManager;
import BasicUtility.RandomPasswordGenerator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
@WebServlet(name = "LoginAccountSwitchServlet", urlPatterns = {"/LoginAccountSwitchServlet"})
public class LoginAccountSwitchServlet extends HttpServlet {

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
        String email = request.getParameter("SwitchTarget");
        String resultMessage = "";
        String redirect = "/DBInterface/manageLogin.jsp";

        try {
            ResultSet result = LoginAccountManager.RetrieveLoginAccount(email);
            if (result == null) {
                resultMessage = "This username does not exist.";
            } else {
                String action = "";
                if (Integer.parseInt(request.getParameter("Action")) == 0) {
                    if (LoginAccountManager.DisableLoginAccount(email) == 0) {
                        resultMessage = "This account is already disabled.";
                    } else {
                        action = "disabled. This may happen due to suspicious activities on your account, or you have submitted any request to have your account locked. ";
                    }
                } else {
                    if (LoginAccountManager.EnableLoginAccount(email) == 0) {
                        resultMessage = "This account is already enabled.";
                    } else {
                        action = "enabled. Please follow the following link to login to your account:\nhttp://localhost:8080/salesapp/LoginSystem/loginHome.jsp\n";
                    }
                }
                String subject = "Your GNB Sales Database Application Account";
                String content = "Dear Sir/Madam:\n" + "This email is written to inform you that your GNB Sales database Application Account has been " + action
                        + "To know more about this operation, please contact any administrator.\n"
                        + "Sincerely,\n" + "GNB Sales Database Application Mantainance Team";
                EmailUtility.sendEmail(host, port, user, pass, email, subject,
                        content);
                resultMessage = "The requested action has been completed.";
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
