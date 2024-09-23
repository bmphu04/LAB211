/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_object;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import product_object.BI_CI;
import product_object.BN_CN;
import product_object.Product;

/**
 *
 * @author HT
 */
// ĐÂY LÀ LỚP PHỤ TRÁCH VIỆC LƯU TRỮ VÀ TRUY XUẤT DỮ LIỆU SẢN PHẨM
public class ProductDao implements IProductDao {

    private List<BI_CI> products = new ArrayList<>();
    
    private int currentID = 0;//ID khởi tạo =0 sẽ tự tăng dần

    private Map<String, String> brands = new HashMap<>();
    private Map<String, String> category = new HashMap<>();

    public ProductDao() {
        LoadFromFile();
        loadBrandIdFromFile();
        loadCategoryIdFromFile();
    }

    // hàm sinh ID tự động      
    private int generateId() {
        return ++currentID;

    }

    // FUNCTION 1: Create product
    @Override
    public void addProduct(BI_CI product) {
        if (!isBrandId(product.getBrandId())) {
            System.out.println("Brand ID invalid");
            System.out.println("Create product failure");
            return;
        }
        if (!isCategory(product.getCategoryId())) {
            System.out.println("Category ID invalid");
            System.out.println("Create product failure");
            return;
        }
        product.setId(generateId());
        products.add(product);
        System.out.println("Create product successfully");
        System.out.println("Back to menu choice option 5 to save to file!");
    }
    //FUNCTION 2: Search Product information by name.
    @Override
    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().startsWith(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
    //FUNCTION 3 Update product information

    @Override
    public void updateProduct(BI_CI product) {
        for (BI_CI p : products) {
            if (p.getId() == product.getId()) {
                p.setName(product.getName());
                p.setBrandId(product.getBrandId());
                p.setCategoryId(product.getCategoryId());
                p.setModelYear(product.getModelYear());
                p.setListPrice(product.getListPrice());
                return;
            }
        }
        System.out.println("Error ! ID not exit");
    }

    //FUNCTION 4: Delete Product information
    @Override
    public void deletateProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    //FUNCTION 5: Save to file
    @Override
    public void saveToFile() {
        try {
            File f = new File("Product.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Product p : products) {
                bw.write(p.toString() + "\n");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //FUNCTION 6: Load from file and print

    @Override
    public void LoadFromFile() {
        try {
            File f = new File("Product.txt");
            if (!f.exists()) {
                System.out.println("File not exit !");
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 6) {
                    int id = Integer.parseInt(tokens[0].trim());
                    String name = tokens[1].trim();
                    String brandId = tokens[2].trim();
                    String categoryId = tokens[3].trim();
                    int modelYear = Integer.parseInt(tokens[4].trim());
                    double listPrice = Double.parseDouble(tokens[5].trim());
                    BI_CI product = new BI_CI(id, name, modelYear, listPrice, brandId, categoryId);
                    product.setId(id);
                    products.add(product);
                    if (id > currentID) {
                        currentID = id;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBrandIdFromFile() {
        try {
            File f = new File("brandId.txt");
            if (!f.exists()) {
                System.out.println("File not exit");
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 2) {
                    String id = tokens[0].trim();
                    String name = tokens[1].trim();
                    brands.put(id, name);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCategoryIdFromFile() {
        try {
            File f = new File("categoryId.txt");
            if (!f.exists()) {
                System.out.println("File not exit!");
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 2) {
                    String id = tokens[0].trim();
                    String name = tokens[1].trim();
                    category.put(id, name);
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isBrandId(String id) {
        return brands.containsKey(id);
    }

    public boolean isCategory(String id) {
        return category.containsKey(id);
    }

   

//get List Product chưa BrandId/CategoryId
    
    public List<BI_CI> getAll() {
        return products;
    }
  @Override
    public List<BN_CN> getAllProduct() {
        List<BN_CN> InforPro = new ArrayList<>();
        for (BI_CI i : products) {
            String brandName = brands.get(i.getBrandId());
            String categoryName = category.get(i.getCategoryId());
            BN_CN inforProduct = new BN_CN(i.getId(), i.getName(), i.getModelYear(), i.getListPrice(), brandName, categoryName);        
            InforPro.add(inforProduct);
        }
        return InforPro;
    }
}
