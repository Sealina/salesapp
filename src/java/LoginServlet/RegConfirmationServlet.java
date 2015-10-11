package LoginServlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BasicUtility.EmailUtility;
import BasicUtility.InvitationManager;
import BasicUtility.LoginAccountManager;
import java.io.IOException;
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
@WebServlet(urlPatterns = {"/RegConfirmationServlet"})
public class RegConfirmationServlet extends HttpServlet {

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
        String invitationCode = request.getParameter("invitationCode");
        String subject = "GNB Sales App Registration Confirmation";
        String content = "Please click the following link to complete the registration:\nhttp://localhost:8080/salesapp/LoginSystem/regCompletion.jsp";
        String resultMessage = "";
        int isConfirmed = 0;
        int isEnabled = 1;

        try {
            if (LoginAccountManager.RetrieveLoginAccount(email) != null) {
                resultMessage = "This email address is already in use! Please use another one for registration.";
            } else {
                switch (InvitationManager.VerifyInvitation(email, invitationCode)) {
                    case 0:
                        resultMessage = "This email is not in the invite list. Please contact the administrator for more information.";
                        break;
                    case 1:
                        resultMessage = "Wrong invitation code.";
                        break;
                    default:
                        EmailUtility.sendEmail(host, port, user, pass, email, subject,
                                content);
                        LoginAccountManager.AddLoginAccount(
                                request.getParameter("firstName"),
                                request.getParameter("lastName"),
                                email,
                                request.getParameter("password"),
                                Integer.parseInt(request.getParameter("accountType")),
                                isConfirmed,
                                isEnabled
                        );
                        resultMessage = "The e-mail was sent successfully. Please check your mail box to finish the registration.";
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "There was an error: " + e.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/LoginSystem/regConfirm.jsp").forward(
                    request, response);
        }
    }
}
