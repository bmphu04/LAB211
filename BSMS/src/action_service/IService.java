/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import java.util.List;
import product_object.Product;

/**
 *
 * @author HT
 */
public interface IService {
    public void addAProduct();
    public void SearchByName();
    public void updateProduct();
    public void deleteProduct();
    public void saveProduct();
    public void printProduct();
   
}
