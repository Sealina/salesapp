package BasicUtility;

import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sealina
 */
public class LoginAccountManager {

    static Connection conn = null;
    static PreparedStatement stmt = null;
    static ResultSet result = null;

    public static void init(Connection dbConn) {
        conn = dbConn;
    }

    public static int AddLoginAccount(
            String firstName,
            String lastName,
            String email,
            String password,
            int accountType,
            int isConfirmed,
            int isEnabled
    ) throws SQLException {

        stmt = conn.prepareStatement(
                "INSERT INTO loginaccount VALUES (?, ?, ?, ?, ?, ?, ?)"
        );

        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, email);
        stmt.setString(4, password);
        stmt.setInt(5, accountType);
        stmt.setInt(6, isConfirmed);
        stmt.setInt(7, isEnabled);
        return stmt.executeUpdate();
    }

    public static ResultSet RetrieveLoginAccount(
            String username
    ) throws SQLException {

        stmt = conn.prepareStatement(
                "SELECT * FROM loginaccount where email = ?"
        );

        stmt.setString(1, username);
        result = stmt.executeQuery();

        result.last();
        int size = result.getRow();
        result.beforeFirst();

        if (size == 1) {
            return result;
        } else {
            return null;
        }
    }

    public static int CompleteRegistration(String email) throws SQLException {

        stmt = conn.prepareStatement(
                "UPDATE loginaccount SET isRegistered = 1 WHERE email = ?"
        );

        stmt.setString(1, email);
        return stmt.executeUpdate();
    }

    public static int ChangePassword(String email, String newPassword) throws SQLException {
        stmt = conn.prepareStatement(
                "UPDATE loginaccount SET password = ? WHERE email = ?"
        );

        stmt.setString(1, newPassword);
        stmt.setString(2, email);
        return stmt.executeUpdate();
    }

    /*
     return value:
     0: username does not exist
     1: not registered
     2: wrong password
     3: account disabled
     4: success
     */
    public static int VerifyLogin(String email, String password) throws SQLException {
        result = RetrieveLoginAccount(email);

        if (result == null) {
            return 0;
        } else {
            result.next();
            String correctPassword = result.getString("password");
            int isRegistered = result.getInt("isRegistered");
            int isEnabled = result.getInt("isEnabled");
            if (isEnabled == 0) {
                return 3;
            } else if (isRegistered == 0) {
                return 1;
            } else if (!password.equals(correctPassword)) {
                return 2;
            } else {
                return 4;
            }
        }
    }

    public static int EnableLoginAccount(String email) throws SQLException {
        RetrieveLoginAccount(email);

        if (result.next() && result.getInt("isEnabled") == 1) {
            return 0;
        }

        stmt = conn.prepareStatement(
                "UPDATE loginaccount SET isEnabled = 1 WHERE email = ?"
        );

        stmt.setString(1, email);
        return stmt.executeUpdate();
    }

    public static int DisableLoginAccount(String email) throws SQLException {
        RetrieveLoginAccount(email);

        if (result.next() && result.getInt("isEnabled") == 0) {
            return 0;
        }

        stmt = conn.prepareStatement(
                "UPDATE loginaccount SET isEnabled = 0 WHERE email = ?"
        );

        stmt.setString(1, email);
        return stmt.executeUpdate();
    }
}
