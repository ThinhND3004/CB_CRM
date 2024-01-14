/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.HotelList;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface I_Hotel {
    void add(String url);
    void check(String url);
    void updateHotelInfor(String url);
    void deleteHotel(String url);
    void searchByNam(String url);
    void seachByID(String url);
    void sort();
    void display(String url);
    void showAllFromFile(String url);
}
