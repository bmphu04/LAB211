/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product_object;

/**
 *
 * @author HT
 */
public class Product {
    protected static int currentId = 0;
    protected int id;
    protected String name;
    protected int modelYear;
    protected double listPrice;

    public Product() {
    }

    public Product(int id,String name, int modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
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

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int ModelYear) {
        this.modelYear = modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public String toString() {
    return id + " , " + name + " , " + modelYear + " , " + listPrice;}

    
            
}
