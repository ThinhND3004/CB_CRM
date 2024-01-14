/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.I_Menu;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class Menu extends ArrayList<String> implements I_Menu{

    public Menu() {
        super();
    }

    @Override
    public void addItem(String str) {
        this.add(str);
    }

    @Override
    public int getChoice() {
        return Utils.getNumber("Input your choice: ", 0, this.size() - 2);
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < this.size(); i++)
        {
            System.out.println(this.get(i));
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        result = Utils.getBoolean(welcome);
        return result;
    }
    
    
}