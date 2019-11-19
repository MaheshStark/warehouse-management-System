/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DBHandlers.DBConnection;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahesh
 */
public class UserDAO {
    public static void insertUser(User user) {
        
        try {
            Connection conn = DBConnection.connect();
            PreparedStatement ps;
            
            String query = "INSERT INTO users (name, email, phone, username, password, role) values(?,?,?,?,?,?)";
            
            System.out.println(query);
            
            ps = conn.prepareStatement(query);
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getPhone());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully inserted the user: " + user.getUsername(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static ResultSet getUserRoles() throws SQLException{
        PreparedStatement ps;
        String query = "SELECT * FROM users";
        ps = DBConnection.connect().prepareStatement(query);
        ResultSet roles = ps.executeQuery();
        return roles;
    }
    
    public static ResultSet getAllUsers() throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM users";
        ps = DBConnection.connect().prepareStatement(query);
        ResultSet users = ps.executeQuery();
        return users;
    }
    
    public static void updateUser(User user){
        try {
            Connection conn = DBConnection.connect();
            
            PreparedStatement ps = null;
            String query = "UPDATE users SET name=?, email=?, password=? WHERE username = ?";
            ps = conn.prepareStatement(query);
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUsername());
            System.out.println(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated the user: " + user.getUsername(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void deleteUser(User user){
        try {
            Connection conn = DBConnection.connect();
            
            PreparedStatement ps = null;
            String query = "DELETE FROM users WHERE username=?";
            ps = conn.prepareStatement(query);
            
            ps.setString(1, user.getUsername());
            System.out.println(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully deleted the user: " + user.getUsername(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static ResultSet getAllLoggedInUser(User user) throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM users WHERE username=?";
        ps = DBConnection.connect().prepareStatement(query);
        ps.setString(1, user.getUsername());
        ResultSet users = ps.executeQuery();
        return users;
    }
}
