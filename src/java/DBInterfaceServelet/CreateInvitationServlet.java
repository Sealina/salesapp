/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBInterfaceServelet;

import BasicUtility.EmailUtility;
import BasicUtility.InvitationManager;
import BasicUtility.RandomPasswordGenerator;
import BasicUtility.DatabaseConnection;
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
@WebServlet(name = "CreateInvitationServlet", urlPatterns = {"/CreateInvitationServlet"})
public class CreateInvitationServlet extends HttpServlet {
    
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
        String email = request.getParameter("InviteTarget");
        String resultMessage = "";
        String redirect = "/DBInterface/manageLogin.jsp";

        try {
            ResultSet result = InvitationManager.RetrieveInvitation(email);
            if (result != null) {
                resultMessage = "This email address already has a valid invitation!";
            } else {
                String invitationCode = RandomPasswordGenerator.generatePassword(6, 20);
                String subject = "Invitation to register at GNB Sales Database Application";
                String content = "Dear Sir/Madam:\n" + "You are invited to register an account with GNB Sales Database Application. "
                        + "Please follow the link below to complete your registration:\n" + "http://localhost:8080/salesapp/LoginSystem/registration.jsp\n"
                        + "Please use the following invitation code with your registration:\n" + invitationCode + "\n"
                        + "Sincerely,\n" + "GNB Sales Database Application Mantainance Team";
                InvitationManager.CreateInvitation(email, invitationCode);
                EmailUtility.sendEmail(host, port, user, pass, email, subject,
                            content);
                resultMessage = "An invitation has been sent.";
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
