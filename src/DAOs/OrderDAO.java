/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DBHandlers.DBConnection;
import Models.PurchaseOrder;
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
public class OrderDAO {
    public static boolean createNewPurchaseOrder(PurchaseOrder order) throws SQLException {
        
        Connection conn = DBConnection.connect();
        PreparedStatement ps;
        
        String query = "INSERT INTO purchase_orders (required_item, required_quantity, created_date, status, supplier) VALUES (?,?,?,?,?)";
        
        System.out.println(query);
        
        ps = conn.prepareStatement(query);
       
        ps.setString(1, order.getRequired_item());
        ps.setDouble(2, order.getRequired_qunatity());
        ps.setString(3, order.getCreated_date());
        ps.setString(4, order.getStatus());
        ps.setString(5, order.getSupplier());
        System.out.println(ps);
        return ps.executeUpdate() > 0;
    }
    
    public static boolean createNewSalesOrder(PurchaseOrder order) throws SQLException {
        
        Connection conn = DBConnection.connect();
        PreparedStatement ps;
        
        String query = "INSERT INTO sales_orders (required_item, required_quantity, created_date, status) VALUES (?,?,?,?)";
        
        System.out.println(query);
        
        ps = conn.prepareStatement(query);
       
        ps.setString(1, order.getRequired_item());
        ps.setDouble(2, order.getRequired_qunatity());
        ps.setString(3, order.getCreated_date());
        ps.setString(4, order.getStatus());
        System.out.println(ps);
        return ps.executeUpdate() > 0;
    }
    
    public static ResultSet getPurchasedOrders(User supplier) throws SQLException{
        
        System.out.println("method getPurchasedOrders called");
        
        PreparedStatement ps;
        String query = "SELECT * FROM purchase_orders WHERE supplier=?";
        ps = DBConnection.connect().prepareStatement(query);
        ps.setString(1, supplier.getUsername());
        System.out.println(ps);
        ResultSet results = ps.executeQuery();
        return results;
    }
    
    public static ResultSet getPurchasedOrders() throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM purchase_orders";
        ps = DBConnection.connect().prepareStatement(query);
        ResultSet results = ps.executeQuery();
        return results;
    }
    
    public static ResultSet getSalesOrders() throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM sales_orders";
        ps = DBConnection.connect().prepareStatement(query);
        ResultSet results = ps.executeQuery();
        return results;
    }
    
    public static void approveOrder(PurchaseOrder po){
        try {
            Connection conn = DBConnection.connect();
            
            PreparedStatement ps = null;
            String query = "UPDATE purchase_orders SET status=? WHERE purchase_order_id = ?";
            ps = conn.prepareStatement(query);
            
            ps.setString(1, po.getStatus());
            ps.setInt(2, po.getId());
            System.out.println(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Approved and message sent to the manager", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void approveSalesOrder(PurchaseOrder po){
        try {
            Connection conn = DBConnection.connect();
            
            PreparedStatement ps = null;
            String query = "UPDATE sales_orders SET status=? WHERE sales_order_id = ?";
            ps = conn.prepareStatement(query);
            
            ps.setString(1, po.getStatus());
            ps.setInt(2, po.getId());
            System.out.println(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Approved and message sent to the retailer", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
