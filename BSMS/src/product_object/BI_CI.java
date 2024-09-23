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
public class BI_CI extends Product{
    public String  brandId;
    protected String categoryId;
    public BI_CI(){
        super();
    }
     public BI_CI(int id, String name, int modelYear, double listPrice,String BrandId, String CategoryId) {
        super(id, name, modelYear, listPrice);
        this.brandId= BrandId;
        this.categoryId = CategoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
 return String.format("| %-5d | %-10s | %-6s | %-6s | %-6d | %-15.2f |",id,name,brandId,categoryId,modelYear,listPrice );    }
    
}
