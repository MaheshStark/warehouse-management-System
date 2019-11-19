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
public class Order {
    private int id;
    private String required_item;
    private int required_qunatity;
    private String created_date;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequired_item() {
        return required_item;
    }

    public void setRequired_item(String required_item) {
        this.required_item = required_item;
    }

    public int getRequired_qunatity() {
        return required_qunatity;
    }

    public void setRequired_qunatity(int required_qunatity) {
        this.required_qunatity = required_qunatity;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
