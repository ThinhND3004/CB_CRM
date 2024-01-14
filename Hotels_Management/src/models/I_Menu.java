/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ACER
 */
public interface I_Menu {
    void addItem(String str);
    int getChoice();
    void showMenu();
    boolean confirmYesNo(String welcome);
}
