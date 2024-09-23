/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

/**
 *
 * @author HT
 */
public class Utilities {
     public boolean isPositive(double price) {
        return price >= 0;
    }

    public boolean isYear(int year) {
        return year >= 1817 && year <= 2024;
    }

    public boolean checkEmpty(String s) {
        return s.trim().isEmpty();
    }
//xu li khoang trang du
    public String SolveWhiteSpace(String str) {
        String string = str.trim();
        String clearStr = string.replaceAll("\\s+", " ");
        return clearStr;
    }
}
