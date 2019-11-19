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
public class Category {
    protected int cat_id;
    protected String name;

    public Category(int cat_id, String name) {
        this.cat_id = cat_id;
        this.name = name;
    }

    public int getId() {
        return cat_id;
    }

    public void setId(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
