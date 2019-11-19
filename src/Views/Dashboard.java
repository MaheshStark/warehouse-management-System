/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DAOs.ItemDAO;
import static DAOs.ItemDAO.deleteItem;
import static DAOs.ItemDAO.getAllCategories;
import static DAOs.ItemDAO.getExpiredtems;
import static DAOs.ItemDAO.getItemById;
import static DAOs.ItemDAO.getRecentItems;
import static DAOs.ItemDAO.searchItemsByName;
import static DAOs.ItemDAO.updateItem;
import static DAOs.OrderDAO.approveSalesOrder;
import static DAOs.OrderDAO.getPurchasedOrders;
import static DAOs.OrderDAO.getSalesOrders;
import static DAOs.SupplierDAO.getAllSuppliers;
import DAOs.UserDAO;
import static DAOs.UserDAO.getAllLoggedInUser;
import static DAOs.UserDAO.getAllUsers;
import static DAOs.UserDAO.updateUser;
import DBHandlers.DBConnection;
import Models.Category;
import Models.Item;
import Models.PurchaseOrder;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mahesh
 */
public final class Dashboard extends javax.swing.JFrame {
    
    int selected_item_id;
    int selected_expired_item;
    int selected_sales_order_id;

    /**
     * Creates new form Dashboard
     * @param role
     * @param username
     * @throws java.sql.SQLException
     */
    public Dashboard(String role, String username) throws SQLException {
        
        initComponents();
        refreshUsers();
        refreshItems();
        fetchSuppliers();
        fetchExpiredItems();
        displayRecentItems();
        displayPurchasedOrders();
        displaySalesOrders();
        
        
        lblUsername.setText("Welcome! " + username);
        lblLoginRole.setText("You logged in as a: " + role);
        
        User user = new User();
        user.setUsername(username);
        
        ResultSet rs;
        rs = getAllLoggedInUser(user);
        while(rs.next()){
            txtLoggedInUserName.setText(rs.getString("name"));
            txtLoggedInUserEmail.setText(rs.getString("email"));
            txtLoggedInUserUsername.setText(rs.getString("username"));
            txtLoggedInUserPassword.setText(rs.getString("password"));
        }
    }
    
    public void fetchExpiredItems(){
        try {
            DefaultTableModel mdl = (DefaultTableModel) tblExpiredItems.getModel();
            
            mdl.setRowCount(0);
            ResultSet rs = getExpiredtems();
            while(rs.next()){
                mdl.addRow(new Object[]{rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_expiry_date")});
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayRecentItems(){
        try {
            DefaultTableModel mdl = (DefaultTableModel) tblRecentItems.getModel();
            
            mdl.setRowCount(0);
            ResultSet rs = getRecentItems();
            while(rs.next()){
                mdl.addRow(new Object[]{rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_price"), rs.getString("item_description"), rs.getString("item_expiry_date"), rs.getString("sku")});
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    //Get all users from database and bind to users table.
    public void refreshUsers(){
        DefaultListModel dlm = new DefaultListModel();
        try {
            ResultSet users;
            users = getAllUsers();
            while(users.next()){
                String name = users.getString("username");
                dlm.addElement(name);
            }
            listUsers.setModel(dlm);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    public void fetchSuppliers() throws SQLException{
        
        ResultSet suppliers;
        suppliers = getAllSuppliers();
        
        while(suppliers.next()){
              cmbItemSupplier.addItem(suppliers.getString("username"));
        }
    }
    

    
    public void refreshItems(){
        try {
            DefaultTableModel mdl = (DefaultTableModel) tblItems.getModel();
            
            mdl.setRowCount(0);
            
            for (Item item : ItemDAO.getAllItems()) {
                mdl.addRow(new Object[]{item.getId(), item.getName(), item.getPrice(), item.getExpiryDate(), item.getSku()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayPurchasedOrders(){
        try {
            DefaultTableModel mdl = (DefaultTableModel) tblPurchasedOrders.getModel();
            
            mdl.setRowCount(0);
            ResultSet rs = getPurchasedOrders();
            while(rs.next()){
                mdl.addRow(new Object[]{rs.getInt("purchase_order_id"), rs.getString("required_item"), rs.getInt("required_quantity"), rs.getDate("created_date"), rs.getString("status")});
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displaySalesOrders(){
        try {
            DefaultTableModel mdl = (DefaultTableModel) tblSalesOrders.getModel();
            
            mdl.setRowCount(0);
            ResultSet rs = getSalesOrders();
            while(rs.next()){
                mdl.addRow(new Object[]{rs.getInt("sales_order_id"), rs.getString("required_item"), rs.getInt("required_quantity"), rs.getDate("created_date"), rs.getString("status")});
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTab = new javax.swing.JTabbedPane();
        dashboard = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblExpiredItems = new javax.swing.JTable();
        btnDiscardItem = new javax.swing.JButton();
        btnRefreshExpItems = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblRecentItems = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtLoggedInUserName = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtLoggedInUserEmail = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtLoggedInUserUsername = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtLoggedInUserPassword = new javax.swing.JPasswordField();
        btnUpdateLoggedInUser = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        txtItemQuantity = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        txtProductPrice = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtItemDescription = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        txtItemExpiryDate = new javax.swing.JFormattedTextField();
        btnUpdateProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        cmbItemSupplier = new javax.swing.JComboBox<String>();
        lblTotalAmount = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnNewItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        btnRefreshItems = new javax.swing.JButton();
        txtSearchKeyword = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        btnSearchItem = new javax.swing.JButton();
        cmbSearchBy = new javax.swing.JComboBox<String>();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtUserEmail = new javax.swing.JTextField();
        btnUpdateUserAccount = new javax.swing.JButton();
        btnDeleteUserAccount = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUserPassword = new javax.swing.JPasswordField();
        jPanel8 = new javax.swing.JPanel();
        btnUsersRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listUsers = new javax.swing.JList<String>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNewUserName = new javax.swing.JTextField();
        txtNewUserEmail = new javax.swing.JTextField();
        txtNewUsername = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cmbUserRole = new javax.swing.JComboBox<String>();
        btnCreateUserAccount = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtNewUserPhone = new javax.swing.JTextField();
        txtNewUserPassword = new javax.swing.JPasswordField();
        jPanel20 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        btnNewPurchaseOrder = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblPurchasedOrders = new javax.swing.JTable();
        btnRefreshPurchaseOrders = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSalesOrders = new javax.swing.JTable();
        btnRefreshSalesOrders = new javax.swing.JButton();
        btnApproveSalesOrder = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        lblLoginRole = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Dashboard - Maga");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Expired Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 12))); // NOI18N

        tblExpiredItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Item ID", "Item Name", "Expired On"
            }
        ));
        tblExpiredItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExpiredItemsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblExpiredItems);

        btnDiscardItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Icons/icon-delete.png"))); // NOI18N
        btnDiscardItem.setText("Delete Product");
        btnDiscardItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscardItemActionPerformed(evt);
            }
        });

        btnRefreshExpItems.setText("Refresh");
        btnRefreshExpItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshExpItemsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnDiscardItem)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefreshExpItems)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDiscardItem)
                    .addComponent(btnRefreshExpItems))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recently Added Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 12))); // NOI18N

        tblRecentItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Price", "Description", "Expiry Date", "SKU"
            }
        ));
        jScrollPane7.setViewportView(tblRecentItems);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Your Account", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 12))); // NOI18N

        jLabel31.setText("Name");

        jLabel32.setText("Email");

        jLabel33.setText("Username");

        txtLoggedInUserUsername.setEditable(false);

        jLabel34.setText("Password");

        btnUpdateLoggedInUser.setText("Update Profile");
        btnUpdateLoggedInUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateLoggedInUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLoggedInUserName)
                    .addComponent(txtLoggedInUserEmail)
                    .addComponent(txtLoggedInUserUsername)
                    .addComponent(txtLoggedInUserPassword)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(btnUpdateLoggedInUser))
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoggedInUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoggedInUserEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoggedInUserUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoggedInUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateLoggedInUser)
                .addContainerGap())
        );

        javax.swing.GroupLayout dashboardLayout = new javax.swing.GroupLayout(dashboard);
        dashboard.setLayout(dashboardLayout);
        dashboardLayout.setHorizontalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dashboardLayout.setVerticalGroup(
            dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainTab.addTab("Dashboard", dashboard);

        jPanel18.setBackground(new java.awt.Color(231, 231, 231));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Item Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 14))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel26.setText("ID");

        jLabel27.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel27.setText("Quantity");

        jLabel28.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel28.setText("Supplier");

        txtProductId.setEditable(false);
        txtProductId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductIdActionPerformed(evt);
            }
        });

        jLabel10.setText("Price");

        jLabel14.setText("Name");

        txtProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductNameActionPerformed(evt);
            }
        });

        jLabel18.setText("Description");

        txtItemDescription.setColumns(20);
        txtItemDescription.setRows(5);
        jScrollPane6.setViewportView(txtItemDescription);

        jLabel19.setText("Expiry Date");

        btnUpdateProduct.setText("Update Product");
        btnUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductActionPerformed(evt);
            }
        });

        btnDeleteProduct.setText("Delete Product");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });

        cmbItemSupplier.setToolTipText("");
        cmbItemSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbItemSupplierActionPerformed(evt);
            }
        });

        lblTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTotalAmount.setText("Total Amount: ");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(btnUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDeleteProduct))
                                    .addComponent(jLabel19))
                                .addGap(0, 96, Short.MAX_VALUE))
                            .addComponent(txtItemQuantity)
                            .addComponent(txtProductPrice)
                            .addComponent(txtProductId, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtItemExpiryDate)
                            .addComponent(txtProductName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbItemSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(lblTotalAmount))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(3, 3, 3)
                .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtItemQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtItemExpiryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbItemSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lblTotalAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateProduct)
                    .addComponent(btnDeleteProduct))
                .addContainerGap())
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Item List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 14))); // NOI18N

        btnNewItem.setText("Insert an Item");
        btnNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewItemActionPerformed(evt);
            }
        });

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Item Name", "Item Price", "Expiry Date", "SKU"
            }
        ));
        tblItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblItems);

        btnRefreshItems.setText("Refresh");
        btnRefreshItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshItemsActionPerformed(evt);
            }
        });

        jLabel30.setText("Search:");

        btnSearchItem.setText("Search");
        btnSearchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchItemActionPerformed(evt);
            }
        });

        cmbSearchBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Name" }));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnNewItem, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRefreshItems, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchKeyword)
                        .addGap(18, 18, 18)
                        .addComponent(cmbSearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchItem)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(btnSearchItem)
                    .addComponent(cmbSearchBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewItem)
                    .addComponent(btnRefreshItems))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        mainTab.addTab("Inventory", jPanel17);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "User Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel5.setText("Name");

        jLabel6.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel6.setText("Email");

        btnUpdateUserAccount.setText("Update Account");
        btnUpdateUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUserAccountActionPerformed(evt);
            }
        });

        btnDeleteUserAccount.setText("Delete Account");
        btnDeleteUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserAccountActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel7.setText("Username");

        txtUsername.setEditable(false);
        txtUsername.setAutoscrolls(false);

        jLabel8.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel8.setText("Password");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnUpdateUserAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteUserAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(182, 182, 182))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUserPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(txtUsername)
                            .addComponent(txtUserEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserName)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserEmail)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserPassword)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateUserAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteUserAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Users List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 14))); // NOI18N

        btnUsersRefresh.setText("Refresh");
        btnUsersRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersRefreshActionPerformed(evt);
            }
        });

        listUsers.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        listUsers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listUsersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listUsers);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btnUsersRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsersRefresh)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Create an User Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel3.setText("Username");

        jLabel4.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel4.setText("Password");

        jLabel9.setFont(new java.awt.Font("Lato", 0, 12)); // NOI18N
        jLabel9.setText("Role");

        cmbUserRole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manager", "Supplier", "Retailer" }));

        btnCreateUserAccount.setText("Create Account");
        btnCreateUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateUserAccountActionPerformed(evt);
            }
        });

        btnClear.setText("Clear Fields");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel11.setText("Phone");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNewUserEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                            .addComponent(txtNewUsername)
                            .addComponent(txtNewUserName)
                            .addComponent(txtNewUserPassword)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCreateUserAccount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNewUserPhone))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNewUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNewUserEmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNewUsername))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtNewUserPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNewUserPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(cmbUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateUserAccount)
                    .addComponent(btnClear))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTab.addTab("Users", jPanel1);

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Purchase Orders", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 14))); // NOI18N

        btnNewPurchaseOrder.setText("New Purchase Order");
        btnNewPurchaseOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPurchaseOrderActionPerformed(evt);
            }
        });

        tblPurchasedOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Required Item", "Quantity", "Created Date", "Status"
            }
        ));
        tblPurchasedOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPurchasedOrdersMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblPurchasedOrders);

        btnRefreshPurchaseOrders.setText("Refresh");
        btnRefreshPurchaseOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPurchaseOrdersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(btnNewPurchaseOrder)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefreshPurchaseOrders)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefreshPurchaseOrders)
                    .addComponent(btnNewPurchaseOrder))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sales Orders", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 0, 14))); // NOI18N

        tblSalesOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Requested Item", "Requested Quantity", "Date", "Status"
            }
        ));
        tblSalesOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalesOrdersMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblSalesOrders);

        btnRefreshSalesOrders.setText("Refresh");
        btnRefreshSalesOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshSalesOrdersActionPerformed(evt);
            }
        });

        btnApproveSalesOrder.setText("Approve");
        btnApproveSalesOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveSalesOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnApproveSalesOrder)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefreshSalesOrders)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApproveSalesOrder)
                    .addComponent(btnRefreshSalesOrders))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        mainTab.addTab("Orders", jPanel20);

        lblUsername.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        lblUsername.setText("Welcome");

        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Icons/icon-logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblLoginRole.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        lblLoginRole.setText("You logged in as");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTab, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblLoginRole)
                        .addGap(18, 18, 18)
                        .addComponent(btnLogout)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(btnLogout)
                    .addComponent(lblLoginRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainTab, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateUserAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserAccountActionPerformed
        String selected = listUsers.getSelectedValue();
        String name = txtUserName.getText();
        String email = txtUserEmail.getText();
        String username = txtUsername.getText();
        String password = String.valueOf(txtUserPassword.getPassword());

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        UserDAO.updateUser(user);
    }//GEN-LAST:event_btnUpdateUserAccountActionPerformed

    private void btnDeleteUserAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserAccountActionPerformed
        String selected = listUsers.getSelectedValue();
        User user = new User();
        user.setUsername(selected);
        UserDAO.deleteUser(user);
        
        txtUserName.setText("");
        txtUserEmail.setText("");
        txtUsername.setText("");
        txtUserPassword.setText("");
    }//GEN-LAST:event_btnDeleteUserAccountActionPerformed

    private void btnUsersRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersRefreshActionPerformed
        // TODO add your handling code here:
        refreshUsers();
    }//GEN-LAST:event_btnUsersRefreshActionPerformed

    private void listUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUsersValueChanged
        String selected = listUsers.getSelectedValue();

        try {
            String query = "SELECT * FROM users WHERE username = '" + selected + "'";
            PreparedStatement ps;
            ResultSet rs;
            ps = DBConnection.connect().prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            txtUserName.setText(rs.getString("name"));
            txtUserEmail.setText(rs.getString("email"));
            txtUsername.setText(rs.getString("username"));
            txtUserPassword.setText(rs.getString("password"));
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listUsersValueChanged

    private void btnCreateUserAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateUserAccountActionPerformed
        // TODO add your handling code here:
        String role = (String) cmbUserRole.getSelectedItem();
        String name = txtNewUserName.getText();
        String email = txtNewUserEmail.getText();
        String username = txtNewUsername.getText();
        int phone = Integer.parseInt(txtNewUserPhone.getText());
        String password = String.valueOf(txtNewUserPassword.getPassword());
        System.out.println(password);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        UserDAO.insertUser(user);
    }//GEN-LAST:event_btnCreateUserAccountActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        txtNewUserName.setText("");
        txtNewUserEmail.setText("");
        txtNewUsername.setText("");
        txtNewUserPassword.setText("");
        txtNewUserPhone.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewItemActionPerformed
        try {
            new NewItem().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNewItemActionPerformed

    private void txtProductIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductIdActionPerformed

    private void btnRefreshItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshItemsActionPerformed
        refreshItems();
    }//GEN-LAST:event_btnRefreshItemsActionPerformed

    private void tblItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsMouseClicked
        try {
            DefaultTableModel mdl = (DefaultTableModel) tblItems.getModel();
            int selectRowIndex = tblItems.getSelectedRow();
            
            selected_item_id = (int) mdl.getValueAt(selectRowIndex, 0);
            
            
            String query = "SELECT * FROM items INNER JOIN users ON items.supplier = users.username WHERE item_id = '" + selected_item_id + "'";
            PreparedStatement ps;
            ResultSet rs;
            ps = DBConnection.connect().prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.first()){
                txtProductId.setText(rs.getString("item_id"));
                txtProductName.setText(rs.getString("item_name"));
                txtProductPrice.setText(rs.getString("item_price"));
                txtItemQuantity.setValue(rs.getInt("sku"));
                txtItemDescription.setText(rs.getString("item_description"));
                txtItemExpiryDate.setText(rs.getString("item_expiry_date"));
                cmbItemSupplier.setSelectedItem(rs.getString("supplier"));
                
                int itemValue = Integer.parseInt(rs.getString("item_price"));
                int quantity = rs.getInt("sku");
                int total = itemValue * quantity;
                
                lblTotalAmount.setText("Total Amount: Rs. " + String.valueOf(total));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblItemsMouseClicked

    private void cmbItemSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbItemSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbItemSupplierActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        Item item = new Item();
        item.setId(selected_item_id);
        
        try {
            if(deleteItem(item)){
                JOptionPane.showMessageDialog(null, "Sucessfully deleted the item.");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnDiscardItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscardItemActionPerformed
            
        if (tblExpiredItems.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Please select an item first.");
            return;
        }
        
        Item item = new Item();
        item.setId(selected_expired_item);
        
        try {
            if(deleteItem(item)){
                JOptionPane.showMessageDialog(null, "Sucessfully deleted the item.");
                fetchExpiredItems();
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDiscardItemActionPerformed

    private void tblExpiredItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExpiredItemsMouseClicked
        DefaultTableModel mdl = (DefaultTableModel) tblExpiredItems.getModel();
            int selectRowIndex = tblExpiredItems.getSelectedRow();
            
            selected_expired_item = (int) mdl.getValueAt(selectRowIndex, 0);
    }//GEN-LAST:event_tblExpiredItemsMouseClicked

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnUpdateLoggedInUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateLoggedInUserActionPerformed
        User user = new User();
        user.setName(txtLoggedInUserName.getText());
        user.setEmail(txtLoggedInUserEmail.getText());
        user.setPassword(txtLoggedInUserPassword.getText());
        user.setUsername(txtLoggedInUserUsername.getText());
        
        updateUser(user);
        
    }//GEN-LAST:event_btnUpdateLoggedInUserActionPerformed

    private void btnSearchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchItemActionPerformed
        //Remove current rows
        DefaultTableModel mdl = (DefaultTableModel) tblItems.getModel();

        
        try {
            String searchBy = (String) cmbSearchBy.getSelectedItem();
            System.out.println(searchBy);
            mdl.setRowCount(0);
            ResultSet rs;
            
            switch(searchBy){
                case "ID" :
                    int id = Integer.parseInt(txtSearchKeyword.getText());
                    rs = getItemById(id);
                    while(rs.next()){
                        mdl.addRow(new Object[]{rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_price"), rs.getString("item_expiry_date"), rs.getString("sku")});
                    }
                    break;
                
                case "Name" :
                    String name = txtSearchKeyword.getText();
                    rs = searchItemsByName(name);
                    while(rs.next()){
                        mdl.addRow(new Object[]{rs.getInt("item_id"), rs.getString("item_name"), rs.getString("item_price"), rs.getString("item_expiry_date"), rs.getString("sku")});
                    }
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchItemActionPerformed

    private void btnNewPurchaseOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPurchaseOrderActionPerformed
        try {
            new NewPurchaseOrder().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNewPurchaseOrderActionPerformed

    private void tblPurchasedOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPurchasedOrdersMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPurchasedOrdersMouseClicked

    private void btnRefreshPurchaseOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPurchaseOrdersActionPerformed
        displayPurchasedOrders();
    }//GEN-LAST:event_btnRefreshPurchaseOrdersActionPerformed

    private void tblSalesOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalesOrdersMouseClicked
        DefaultTableModel mdl = (DefaultTableModel) tblSalesOrders.getModel();
        int selectRowIndex = tblSalesOrders.getSelectedRow();
            
            selected_sales_order_id = (int) mdl.getValueAt(selectRowIndex, 0);
    }//GEN-LAST:event_tblSalesOrdersMouseClicked

    private void btnUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductActionPerformed
        Item item = new Item();
        
        item.setId(Integer.parseInt(txtProductId.getText()));
        item.setName(txtProductName.getText());
        item.setDescription(txtItemDescription.getText());
        item.setPrice(Integer.parseInt(txtProductPrice.getText()));
        item.setSku((int) txtItemQuantity.getValue());
        item.setExpiryDate(txtItemExpiryDate.getText());
        
        User supplier = new User();
        supplier.setUsername((String) cmbItemSupplier.getSelectedItem());
        
        updateItem(item, supplier);
    }//GEN-LAST:event_btnUpdateProductActionPerformed

    private void btnApproveSalesOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveSalesOrderActionPerformed
        if(tblSalesOrders.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Please select an item first!");
            return;
        }
        
        PurchaseOrder po = new PurchaseOrder();
        po.setId(selected_sales_order_id);
        po.setStatus("Approved");
            
        approveSalesOrder(po);
    }//GEN-LAST:event_btnApproveSalesOrderActionPerformed

    private void btnRefreshSalesOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshSalesOrdersActionPerformed
        displaySalesOrders();
    }//GEN-LAST:event_btnRefreshSalesOrdersActionPerformed

    private void txtProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductNameActionPerformed

    private void btnRefreshExpItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshExpItemsActionPerformed
        fetchExpiredItems();
    }//GEN-LAST:event_btnRefreshExpItemsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApproveSalesOrder;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreateUserAccount;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnDeleteUserAccount;
    private javax.swing.JButton btnDiscardItem;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNewItem;
    private javax.swing.JButton btnNewPurchaseOrder;
    private javax.swing.JButton btnRefreshExpItems;
    private javax.swing.JButton btnRefreshItems;
    private javax.swing.JButton btnRefreshPurchaseOrders;
    private javax.swing.JButton btnRefreshSalesOrders;
    private javax.swing.JButton btnSearchItem;
    private javax.swing.JButton btnUpdateLoggedInUser;
    private javax.swing.JButton btnUpdateProduct;
    private javax.swing.JButton btnUpdateUserAccount;
    private javax.swing.JButton btnUsersRefresh;
    private javax.swing.JComboBox<String> cmbItemSupplier;
    private javax.swing.JComboBox<String> cmbSearchBy;
    private javax.swing.JComboBox<String> cmbUserRole;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblLoginRole;
    private javax.swing.JLabel lblTotalAmount;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JList<String> listUsers;
    private javax.swing.JTabbedPane mainTab;
    private javax.swing.JTable tblExpiredItems;
    private javax.swing.JTable tblItems;
    private javax.swing.JTable tblPurchasedOrders;
    private javax.swing.JTable tblRecentItems;
    private javax.swing.JTable tblSalesOrders;
    private javax.swing.JTextArea txtItemDescription;
    private javax.swing.JFormattedTextField txtItemExpiryDate;
    private javax.swing.JSpinner txtItemQuantity;
    private javax.swing.JTextField txtLoggedInUserEmail;
    private javax.swing.JTextField txtLoggedInUserName;
    private javax.swing.JPasswordField txtLoggedInUserPassword;
    private javax.swing.JTextField txtLoggedInUserUsername;
    private javax.swing.JTextField txtNewUserEmail;
    private javax.swing.JTextField txtNewUserName;
    private javax.swing.JPasswordField txtNewUserPassword;
    private javax.swing.JTextField txtNewUserPhone;
    private javax.swing.JTextField txtNewUsername;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductPrice;
    private javax.swing.JTextField txtSearchKeyword;
    private javax.swing.JTextField txtUserEmail;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPasswordField txtUserPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
