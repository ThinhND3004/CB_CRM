/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.HotelList;
import controllers.Menu;
import java.io.File;
import models.I_Hotel;
import models.I_Menu;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class Hotels_Management {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "HotelStorage.txt";
        File file = new File(url);

        I_Menu subMenu = new Menu();
        subMenu.addItem("\n Which type of search do you wanna choose?");
        subMenu.addItem("|             1. Search by Name           |");
        subMenu.addItem("|             2. Search by ID             |");
        subMenu.addItem("|=========================================|");
        I_Menu menu = new Menu();
        menu.addItem("\n\n-------------------MENU--------------------");
        menu.addItem("|             1. Add Hotel                |");
        menu.addItem("|             2. Update Hotel             |");
        menu.addItem("|             3. Delete Hotel             |");
        menu.addItem("|             4. Search Hotel             |");
        menu.addItem("|             5. Check To Exist Hotel     |");
        menu.addItem("|             6. Display                  |");
        menu.addItem("|             0. Quit                     |");
        menu.addItem("-------------------------------------------");
        int choice;
        boolean cont = true;
        I_Hotel hotel = new HotelList();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 0:
                    cont = Utils.getBoolean("Countinue? (Y/N)");
                    break;
                case 1:
                    hotel.add(url);
                    break;
                case 4:
                    boolean outSearch = false;
                    do {
                        subMenu.showMenu();
                        int subChoice = subMenu.getChoice();
                        switch (subChoice)
                        {
                            case 1:
                                hotel.searchByNam(url);
                                break;
                            case 2:
                                hotel.seachByID(url);
                                break;
                        }
                        outSearch = Utils.getBoolean("Do you wanna go back to main menu?");
                    } while (!outSearch);
                    break;
                case 3:
                    hotel.deleteHotel(url);
                    break;
                case 2:
                    boolean outUpdate = false;
                    
                    do {                        
                        hotel.updateHotelInfor(url);
                        
                        outUpdate = Utils.getBoolean("Do you wanna go back to main menu?");
                    } while (outUpdate == false);
                    
                    break;
                case 5:
                    boolean outConfirm = false;
                    do {
                        hotel.check(url);
                        outConfirm = Utils.getBoolean("Do you wanna go back main menu (Y/N): ");
                    } while (!outConfirm);

                    break;
                    
                case 6:
                    hotel.display(url);
                    break;
                    
                case 7:
                    hotel.showAllFromFile(url);
                    break;
            }
        } while (cont);
    }

}
