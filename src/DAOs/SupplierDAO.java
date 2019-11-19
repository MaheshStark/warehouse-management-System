/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DBHandlers.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mahesh
 */

public class SupplierDAO {
    
    public static ResultSet getAllSuppliers() throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM users WHERE role=?";
        ps = DBConnection.connect().prepareStatement(query);
        ps.setString(1, "Supplier");
        ResultSet suppliers = ps.executeQuery();
        return suppliers;
    }
    
    public static ResultSet getSuppliersByItemId(int item_id) throws SQLException{
        
        PreparedStatement ps;
        String query = "SELECT * FROM items INNER JOIN suppliers ON items.supplier_id = suppliers.id AND item_id=?";
        ps = DBConnection.connect().prepareStatement(query);
        ps.setInt(1, item_id);
        ResultSet suppliers = ps.executeQuery();
        return suppliers;
    }
}
