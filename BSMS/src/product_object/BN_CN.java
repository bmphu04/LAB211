/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product_object;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 *
 * @author HT
 */
public class BN_CN extends Product{
    protected String BrandName;
    protected String CategoryName;

    public BN_CN() {
        super();
    }

    public BN_CN(int id, String name, int modelYear, double listPrice,String BrandName, String CategoryName) {
        super(id, name, modelYear, listPrice);
        this.BrandName = BrandName;
        this.CategoryName = CategoryName;
    }
    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    @Override
    public String toString() {
        
        return String.format("| %-5d | %-10s | %-14s | %-20s | %-10d | %-15.2f |",id,name,BrandName,CategoryName,modelYear,listPrice );
        
    }
  
    
}
