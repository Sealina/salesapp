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
public class InvitationManager {

    static Connection conn = null;
    static PreparedStatement stmt = null;
    static ResultSet result = null;

    public static void init(Connection dbConn) {
        conn = dbConn;
    }

    public static int CreateInvitation(
            String email,
            String verificationCode
    ) throws SQLException {

        stmt = conn.prepareStatement(
                "INSERT INTO invitelist VALUES (?, ?)"
        );
        stmt.setString(1, email);
        stmt.setString(2, verificationCode);
        return stmt.executeUpdate();
    }

    public static ResultSet RetrieveInvitation(
            String email
    ) throws SQLException {

        stmt = conn.prepareStatement(
                "SELECT * FROM invitelist where email = ?"
        );

        stmt.setString(1, email);
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

    /*
     return value:
     0: invitation does not exist
     1: wrong password
     2: success
     */
    public static int VerifyInvitation(String email, String invitationCode) throws SQLException {
        result = RetrieveInvitation(email);

        if (result == null) {
            return 0;
        } else {
            result.next();
            String correctInvitationCode = result.getString("invitationCode");
            if (!invitationCode.equals(correctInvitationCode)) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
