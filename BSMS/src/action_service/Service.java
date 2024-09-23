/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import data_object.ProductDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import product_object.BI_CI;
import product_object.BN_CN;
import product_object.Product;
import action_service.Utilities;
/**
 *
 * @author HT
 */
// LỚP Service NÀY CHỨA CÁC HÀNH ĐỘNG NGHIỆP VỤ NHƯ: TÌM KIỂM, THÊM, SỬA XÓA...
public class Service implements IService {
    private ProductDao productDao;
    public Utilities Utility = new Utilities();
    public Service(ProductDao productDao) {
        this.productDao = productDao;
    }
    Scanner sc = new Scanner(System.in);

    //phương thức Menu 
    public int menu(String[] opts) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        int choice = 0;
        for (int i = 0; i < opts.length; i++) {
            System.out.println(opts[i]);
        }
        while (Utility.checkEmpty(input)) {
            System.out.println("Please, Enter your choice");
            input = sc.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice < 0) {
                    System.out.println("Don't enter negative number !");
                    input = "";
                }
            } catch (Exception e) {
                System.out.println("Character Error! try again");
                input = "";
            }
        }
        return choice;
    }
   

    // FUNCTION 1: Create product
    @Override
    public void addAProduct() {
        Scanner sc = new Scanner(System.in);
        String inputName = "";
        String name = "";

        while (Utility.checkEmpty(inputName)) {
            System.out.println("Input Name: ");
            inputName = sc.nextLine();
            name = Utility.SolveWhiteSpace(inputName);
            if (Utility.checkEmpty(name)) {
                System.out.println("Name not empty.Try again!");
            }
        }
        //add BrandId
        String brandId = "";
        while (!productDao.isBrandId(brandId)) {
            System.out.println("Enter BrandId: ");
            brandId = sc.nextLine();
            if (!productDao.isBrandId(brandId)) {
                System.out.println("BrandId invalid: ");
            }

        }
        //add CategoryId
        String categoryId = "";
        while (!productDao.isCategory(categoryId)) {
            System.out.println("Input CategoryId: ");
            categoryId = sc.nextLine();
            if (!productDao.isCategory(categoryId)) {
                System.out.println("categoryId invalid: ");
            }
        }

        //add model year
        boolean flagYear = false;
        String input = "";
        int modelYear = 0;
        while (flagYear == false) {
            System.out.println("Input Model year: ");
            input = sc.nextLine();
            try {
                modelYear = Integer.parseInt(input);
                if (!Utility.isYear(modelYear)) {
                    System.out.println("Year is invalid, try again");
                } else {
                    flagYear = true;
                }
            } catch (Exception e) {
                System.out.println("Year is invalid, try again");
            }
        }
        //add Listprice
        boolean flagPrice = false;
        String input2 = "";
        double listPrice = 0;
        while (flagPrice == false) {
            System.out.println("Enter Price: ");
            input2 = sc.nextLine();
            try {
                listPrice = Double.parseDouble(input2);
                if (!Utility.isPositive(listPrice)) {
                    System.out.println("Invalid price!");
                } else {
                    flagPrice = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid Price!");
            }
        }
        BI_CI product = new BI_CI(0, name, modelYear, listPrice, brandId, categoryId);
        productDao.addProduct(product);

    }

    //FUNCTION 2: Search Product information by name
    @Override
    public void SearchByName() {
        System.out.println("Enter product name to search: ");
        String name = sc.nextLine();
        List<Product> products = productDao.searchByName(name);
        if (products == null || products.isEmpty()) {
            System.out.println("Have no any product");
        } else {
            Collections.sort(products, Comparator.comparing(Product::getModelYear));
            print();
            for (Product product : products) {
                System.out.println(product);
               
            }
            print();
          }

    }

    //FUNCTION 3 Update product information
    @Override
    public void updateProduct() {
        String input = "";
        int id = 0;
        while (Utility.checkEmpty(input)) {
            System.out.println("Enter product ID to update: ");
            input = sc.nextLine();

            try {
                id = Integer.parseInt(input);
                if (id < 0) {
                    System.out.println("ID cannot be negative! Try again");
                    input = "";
                }
            } catch (Exception e) {
                System.out.println("Character Erorr! Try again");
                input = "";
            }
        }
        //kiem tra xem ID có tồn tại không
        BI_CI product = null;
        for (BI_CI p : productDao.getAll()) {
            if (p.getId() == id) {
                product = p;
                break;
            }
        }
        if (product == null) {
            System.out.println("ID invalid ");
            return;
        }
        //sau khi kiểm tra xong tiến hành update
        //nhap new name
        System.out.println("Enter new Name (Leave Blank to keep old): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            product.setName(newName);
        }

        //nhap new BrandID
        boolean flag1 = false;
        while (flag1 == false) {
            System.out.println("Enter new Brand ID(Leave blank to keep old): ");
            String newBrandID = sc.nextLine();
            if (!newBrandID.trim().isEmpty()) {

                //kiem tra brandid co ton tai ko
                if (productDao.isBrandId(newBrandID)) {
                    product.setBrandId(newBrandID);
                    flag1 = true;
                } else {
                    System.out.println("BrandId not exit(Leave Blank or Try again)");
                }
            } else {
                flag1 = true;
            }

        }
        //Nhap new category ID
        boolean flag2 = false;
        while (flag2 == false) {
            System.out.println("Enter new category(Leave blank to keep old):");
            String newCategoryId = sc.nextLine();
            //nếu người dùng ko bỏ trống thì tiến hành check để cap nhat
            if (!newCategoryId.isEmpty()) {
                if (productDao.isCategory(newCategoryId)) {
                    product.setCategoryId(newCategoryId);
                    flag2 = true;
                } else {
                    System.out.println("Category Id not exit(Leave Blank or Try again)");
                }
            } else {
                flag2 = true;
            }

        }
        // nhap model year
        boolean flag3 = false;
        while (flag3 == false) {
            System.out.println("Enter model year (Leave blank to keep old): ");
            String newModelyear = sc.nextLine();
            int modelyear = 0;
            if (Utility.checkEmpty(newModelyear)) {
                flag3 = true;
            } else {
                try {
                    modelyear = Integer.parseInt(newModelyear);
                    if (Utility.isYear(modelyear)) {
                        product.setModelYear(modelyear);
                        flag3 = true;
                    } else {
                        System.out.println("modelYear invalid");
                    }

                } catch (Exception e) {
                    System.out.println("modelYear invalid");
                }
            }

        }
        // nhap list price
        boolean flag4 = false;
        while (flag4 == false) {
            System.out.println("Enter new list price");
            String newPrice = sc.nextLine();
            if (!newPrice.isEmpty()) {
                if (newPrice.matches("\\d+")) {
                    double price = Double.parseDouble(newPrice);
                    product.setListPrice(price);
                    flag4 = true;
                } else {
                    System.out.println("List price invalid(Leave Blank or Try again)");
                }
            }// nếu empty thì gán true để kết thúc vòng lặp và giữ value cũ
            else if (newPrice.trim().isEmpty()) {
                flag4 = true;
            }
        }
        // sau khi cập nhật xong thì nhắc user lưu sản phẩm
        productDao.updateProduct(product);
        System.out.println("Product update successfully");
        System.out.println("Back to menu and choice option 5 to save to file !");
    }

    //FUNCTION 4: Delete Product information
    @Override
    public void deleteProduct() {
        String input = "";
        int id = 0;
        while (Utility.checkEmpty(input)) {
            System.out.println("Input ID to delete: ");
            input = sc.nextLine();
            try {
                id = Integer.parseInt(input);

            } catch (Exception e) {
                System.out.println("Character error !");
                input = "";
            }
            if (id < 0) {
                System.out.println("ID cannot be negative number !");
                input = "";
            }

        }
        Product product = null;
        for (Product p : productDao.getAll()) {
            if (p.getId() == id) {
                product = p;
            }
        }
        if (product == null) {
            System.out.println("Product does not exist !");
            return;
        }
        System.out.println("Are you sure to Delete:");
        System.out.println("1.Yes\n2.No");
        String enter = sc.nextLine();
        if (enter.equals("1")) {
            productDao.deletateProduct(id);
            System.out.println("Delete successfully");
        } else if (enter.equals("2")) {
            System.out.println("Oder Canceled");
        } else {
            System.out.println("Character Error, Delete failure");
        }

    }

    //FUNCTION 5: Save to file
    @Override
    public void saveProduct() {
        productDao.saveToFile();
        System.out.println("Saved to file Successful");
    }

    //FUNCTION 6: Print all list from file
    @Override
    public void printProduct() {
        List<BN_CN> products = productDao.getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getListPrice).reversed().thenComparing(Product::getName));
        System.out.println("+-------------------------------------------------------------------------------------------+");
        for (BN_CN i : products) {
            System.out.println(i);
        }
                System.out.println("+-------------------------------------------------------------------------------------------+");

    }
    void print(){
        System.out.println("+-----------------------------------------------------------------+");
    }
}
