package LoginServlet;

import BasicUtility.CustomerManager;
import BasicUtility.DatabaseConnection;
import BasicUtility.InvitationManager;
import BasicUtility.LoginAccountManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
/**
 * Application Lifecycle Listener implementation class myServletListener
 *
 */
public class DBConnectionListener implements ServletContextListener {
 
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
 
    	ServletContext sc = event.getServletContext();
 
    	String url = sc.getInitParameter("dbUrl");
    	String user_name = sc.getInitParameter("dbUsername");
    	String password = sc.getInitParameter("dbPassword");
    	String database = sc.getInitParameter("dbName");
    	DatabaseConnection db = new DatabaseConnection(url + database, user_name, password);
        LoginAccountManager.init(db.conn);
        InvitationManager.init(db.conn);
        CustomerManager.init(db.conn);
    	//sc.setAttribute("db", db);
 
    }
 
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
 
}