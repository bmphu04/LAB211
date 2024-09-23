/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import action_service.Service;
import data_object.ProductDao;
import java.util.Scanner;

/**
 *
 * @author HT
 */
// LỚP BIỂU DIỄN ĐỐI TƯỢNG SẢN PHẨM
public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDao productDao = new ProductDao();
        Service service = new Service(productDao);
        boolean exist = false;
        int opt;
        String[] opts = {"1. Add a product.\n"
            + "2. Search product by product name, return a list of all products that same name.\n"
            + "3. Update product.\n"
            + "4. Delete product.\n"
            + "5. Save products to file.\n"
            + "6. Print list products from the file.\n"
            + "Orther. Quit (press any number > 6"};

        do {
            opt = service.menu(opts);
            if (opt > 6) {
                exist = true;
                System.out.println("Program has finished!");
            }
            // sử dụng thêm vòng lặp while để tránh 
            //trường hợp bị ghi đè biến opt khi chọn option 2 ở dưới
            while (!exist) {
                switch (opt) {
                    case 1:
                        service.addAProduct();
                        break;
                    case 2:
                        service.SearchByName();
                        break;
                    case 3:
                        service.updateProduct();
                        break;
                    case 4:
                        service.deleteProduct();
                        break;
                    case 5:
                        service.saveProduct();
                        break;
                    case 6:
                        service.printProduct();
                        break;
                    default:
                        System.out.println("Option invalid try again!");
                        break;
                }
                if (!exist) {
                    //Ask to go back to menu
                    boolean flag = false;
                    while (!flag) {
                        System.out.println("Do you want to go back Menu ? (Enter 1 or 2)");
                        System.out.println("1.Yes\n2.No");
                        String enter = sc.nextLine();
                        if (enter.equals("2")) {
                            System.out.println("Continuing with the current operation.");
                            flag = true;
                        } else if (enter.equals("1")) {
                            opt = service.menu(opts);
                            if (opt > 6) {
                                exist = true;
                                System.out.println("Program has finished!");
                                flag = true;
                            }
                            flag = true;
                        } else {
                            System.out.println("Character Error !");
                            flag = false;
                        }
                    }
                }
            }
        } while (exist == false);// thoát chương trình khi flag là true
    }
}
