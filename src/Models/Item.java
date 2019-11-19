/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Mahesh
 */
public class Item {
    protected int id;
    protected String name;
    protected String description;
    protected double price;
    protected String expiry_date;
    protected int sku;

    public Item(int id, String name, double price, String description, String expiry_date, int sku) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiry_date = expiry_date;
        this.sku = sku;
        this.description = description;
        //this.category_id = category.getId();
    }

    public Item() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpiryDate() {
        return getExpiry_date();
    }

    public void setExpiryDate(String expiry_date) {
        this.setExpiry_date(expiry_date);
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    
}
