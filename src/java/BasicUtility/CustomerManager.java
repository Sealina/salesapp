/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sealina
 */
public class CustomerManager {
    
    static Connection conn = null;
    static PreparedStatement stmt = null;
    static ResultSet result = null;

    public static void init(Connection dbConn) {
        conn = dbConn;
    }

    public static int AddCustomer(
            String createDate,
            String customer,
            String city,
            String state,
            String country,
            String phoneNum,
            String type,
            String subtype,
            String status,
            String customerManager,
            String owner
    ) throws SQLException {

        stmt = conn.prepareStatement(
                "INSERT INTO account VALUES " + 
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );

        stmt.setString(1, createDate);
        stmt.setString(2, customer);
        stmt.setString(3, city);
        stmt.setString(4, state);
        stmt.setString(5, country);
        stmt.setString(6, phoneNum);
        stmt.setString(7, type);
        stmt.setString(8, subtype);
        stmt.setString(9, status);
        stmt.setString(10, customerManager);
        stmt.setString(11, owner);
        return stmt.executeUpdate();
    }

    public static ResultSet ExecuteRetrieving(
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
     3: success
     */
    public static int VerifyPassword(String email, String password) throws SQLException {
        result = ExecuteRetrieving(email);

        if (result == null) {
            return 0;
        } else {
            result.next();
            String correctPassword = result.getString("password");
            int isRegistered = result.getInt("isRegistered");
            if (isRegistered == 0) {
                return 1;
            } else if (!password.equals(correctPassword)) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}
