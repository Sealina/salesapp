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
import javax.servlet.http.*;

/**
 *
 * @author Sealina
 */
@WebServlet(urlPatterns = {"/LoginVerificationServlet"})
public class LoginVerificationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String resultMessage = "";
        String redirect = "/LoginSystem/loginHome.jsp";
        HttpSession session = request.getSession();

        try {
            int result = LoginAccountManager.VerifyLogin(username, password);
            switch (result) {
                case 0:
                    resultMessage = "Invalid username! Please use a registered email address for login.";
                    break;
                case 1:
                    resultMessage = "This account have not yet complete registration process!";
                    break;
                case 2:
                    resultMessage = "Incorrect combination of username and password.";
                    break;
                case 3:
                    resultMessage = "Account is locked. Login is not permitted at this point. Please check your mail box or contact administrator.";
                    break;
                case 4:
                    redirect = "/DBInterface/dbiHome.jsp";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = "There was an error: " + e.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            session.setAttribute("Username", username);
            getServletContext().getRequestDispatcher(redirect).forward(
                    request, response);
        }
    }
}
