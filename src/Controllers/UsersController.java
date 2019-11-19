/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBHandlers.DBConnection;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mahesh
 */
public class UsersController {
    
    private static String logged_in_user;
    private static boolean login_status = false;
    private static String logged_in_user_role;
    
    // Begin Getters and Setters
    public static String getLogged_in_user() {
        return logged_in_user;
    }

    public static void setLogged_in_user(String logged_in_user) {
        UsersController.logged_in_user = logged_in_user;
    }

    public static boolean getLogin_status() {
        return login_status;
    }

    public static void setLogin_status(boolean login_status) {
        UsersController.login_status = login_status;
    }

    

    public static String getLogged_in_user_role() {
        return logged_in_user_role;
    }

    public static void setLogged_in_user_role(String logged_in_user_role) {
        UsersController.logged_in_user_role = logged_in_user_role;
    }
    // End Getters and Setters
    
    public static boolean authenticateUser(User user) throws SQLException{
        PreparedStatement ps;
        ResultSet rs;
        
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        ps = DBConnection.connect().prepareStatement(query);
        
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
       
        rs = ps.executeQuery();
        
        if (rs.first()){
            setLogin_status(true);
            user.setRole(rs.getString("role"));
            user.setUsername(rs.getString("username"));
            return true;
        } else {
            return false;
        }
    }
}
