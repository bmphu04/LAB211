/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_object;

import java.util.List;
import product_object.BN_CN;
import product_object.Product;
import product_object.BI_CI;
/**
 *
 * @author HT
 */
public interface IProductDao {
    void addProduct(BI_CI product);
    List<Product> searchByName(String name);
    void updateProduct(BI_CI product);
    void deletateProduct(int id);
    List <BN_CN> getAllProduct();
    void saveToFile();
    void LoadFromFile();
}
