/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DBHandlers.DBConnection;
import Models.Item;
import Models.Category;
import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahesh
 */
public class ItemDAO {
    
    /**
     *
     * @param item
     * @param category
     * @return
     * @throws SQLException
     */
    public static boolean insert(Item item, User supplier) throws SQLException {
        
        Connection conn = DBConnection.connect();
        PreparedStatement ps;
        
        String query = "INSERT INTO items (item_name, item_price, item_description, item_expiry_date, sku, supplier) values(?,?,?,?,?,?)";
        
        System.out.println(query);
        
        ps = conn.prepareStatement(query);
       
        ps.setString(1, item.getName());
        ps.setDouble(2, item.getPrice());
        ps.setString(3, item.getDescription());
        ps.setString(4, item.getExpiryDate());
        ps.setInt(5, item.getSku());
        ps.setString(6, supplier.getUsername());
        System.out.println(ps);
        return ps.executeUpdate() > 0;
    }
    
    public static void updateItem(Item item, User supplier){
        try {
            Connection conn = DBConnection.connect();
            
            PreparedStatement ps = null;
            String query = "UPDATE items SET item_name=?, item_price=?, item_description=?, item_expiry_date=?, sku=?, supplier=? WHERE item_id = ?";
            ps = conn.prepareStatement(query);
            
            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getExpiryDate());
            ps.setInt(5, item.getSku());
            ps.setString(6, supplier.getUsername());
            ps.setInt(7, item.getId());
            System.out.println(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully updated the Item: " + item.getId(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static ArrayList<Item> getAllItems() throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM items";
        ps = DBConnection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<Item> items = new ArrayList();
        
        while (rs.next()) {
            // should ideally be handled in the controller
            Item item = new Item(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            items.add(item);
        }
        
        return items;
    }
    
    public static ResultSet getItemById(int id) throws SQLException{
        DBConnection dbc = new DBConnection();
        Connection conn = DBConnection.connect();
        
        ResultSet rs = null;
        
        PreparedStatement ps = null;
        
        String query = "SELECT * from items WHERE item_id=?";
        
        ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        
        rs = ps.executeQuery();
        
        return rs;
    }
    
    public static ResultSet searchItemsByName(String name) throws SQLException{
        DBConnection dbc = new DBConnection();
        Connection conn = DBConnection.connect();
        
        ResultSet rs = null;
        
        PreparedStatement ps = null;
        
        String query = "SELECT * from items WHERE item_name LIKE ?";
        
        ps = conn.prepareStatement(query);
        ps.setString(1, name + "%");
        
        rs = ps.executeQuery();
        
        return rs;
    }
    
    public static ResultSet getAllCategories() throws SQLException{
        PreparedStatement ps;
        String query = "SELECT * FROM categories";
        ps = DBConnection.connect().prepareStatement(query);
        ResultSet results = ps.executeQuery();
        
        ArrayList<Item> items = new ArrayList();
        
        return results;
    }
    
    public static ResultSet getExpiredtems() throws SQLException{
        
        String currentDate = java.time.LocalDate.now().toString();
        
        PreparedStatement ps;
        String query = "SELECT * FROM items WHERE item_expiry_date < ?";
        ps = DBConnection.connect().prepareStatement(query);
        ps.setString(1, currentDate);
        ResultSet results = ps.executeQuery();
        return results;
    }
    
    public static boolean deleteItem(Item item) throws SQLException{
        PreparedStatement ps;
        String query = "DELETE FROM items WHERE item_id = ?";
        ps = DBConnection.connect().prepareStatement(query);
        ps.setInt(1, item.getId());
        
        if(ps.executeUpdate()>0){
            return true;
        } else {
            return false;
        }
    }
    
    public static ResultSet getRecentItems() throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM items ORDER BY item_id DESC LIMIT 10";
        ps = DBConnection.connect().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        return rs;
    }
}
