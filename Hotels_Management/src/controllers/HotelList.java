/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import models.Hotel;
import models.I_Hotel;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class HotelList extends ArrayList<Hotel> implements I_Hotel {
    
    @Override
    public void add(String url) {
        HotelList hotelList = null;
        boolean checkOut = true;
        boolean checkID = false;
        try {
            hotelList = Utils.readFromFile(url);
            
            do {
                String id = null;
                do {            
                    boolean dup = false;
                    id = Utils.getID();
                    for (Hotel hotel : hotelList) {
                        if (id.equals(hotel.getId())) {
                            System.out.println("Exist a hotel with this id!");
                            dup = true;
                            break;
                        }
                    }
                    if(dup != true)
                    {
                        checkID = true;
                    }
                } while (checkID == false);
                
                String name = Utils.getString("Input Hotel's Name: ");
                String address = Utils.getString("Input Hotel's Address: ");
                int room = Utils.getInt("Input Hotel's Available Room: ");
                int rate = Utils.getRate("Input Hotel's Rate: ");
                String phone = Utils.getPhone();
                
                Hotel newHotel = new Hotel(id, name, room, address, phone, rate);
                hotelList.add(newHotel);
                checkOut = Utils.getBoolean("\n\nDo you wanna continue to add hotel? (Y/N)");
            } while (checkOut);
            
        } catch (Exception e) {
        }
        
        File file = new File(url);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Utils.writeToFile(url, hotelList);
        } catch (Exception e) {
        }
        System.out.println("\n====Now, there are " + hotelList.size() + " hotel(s) in your list!!====");
        for (int i = 0; i < hotelList.size(); i++) {
            Hotel hotelResult = hotelList.get(i);
            System.out.print("\n" + (i + 1) + ". ");
            hotelResult.display();
            System.out.println("------------");
        }
        System.out.println("======================================================================");
    }
    
    public void showAllFromFile(String url) {
        try {
            HotelList hotels = Utils.readFromFile(url);
            
            if (hotels != null) {
                for (Hotel hotel : hotels) {
                    hotel.display();
                }
            } else {
                System.out.println("Do not have any Hotel in you file!");
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public void check(String url) {
        try {
            HotelList hotels = Utils.readFromFile(url);
            
            String findHotelID = Utils.getID();
            
            Hotel hotelCheck = null;
            
            for (Hotel hotel : hotels) {
                if (findHotelID.equalsIgnoreCase(hotel.getId())) {
                    hotelCheck = hotel;
                    System.out.println("Exist Hotel!");
                }
            }
            
            if (hotelCheck == null) {
                System.out.println("No Hotel Found!");
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public void updateHotelInfor(String url) {
        try {
            HotelList list = Utils.readFromFile(url);
            
            String hotelID = Utils.getID();
            
            Hotel existHotel = null;
            
            for (Hotel hotel : list) {
                if (hotel.getId().equals(hotelID)) {
                    existHotel = hotel;
                }
            }
            
            if (existHotel == null) {
                System.out.println("Hotel does not exist!");
            } else {
                String newID = existHotel.getId();
                String newName = Utils.updateString("Input new hotel's name: ", existHotel.getName());
                String phone = Utils.updatePhone("Input new phonenumber: ", existHotel.getPhone());
                String address = Utils.updateString("Input new address: ", existHotel.getAddress());
                int room = Utils.updateInt("Input new number of room available: ", existHotel.getRoomAvailable());
                int rate = Utils.updateNumber("Input new hotel's rate: ", 1, 5, existHotel.getRate());
                
                Hotel newHotel = new Hotel(newID, newName, room, address, phone, rate);
                
                System.out.println("\n------------New update-------------");
                newHotel.display();
                System.out.println("-----------------------------------");
                int index = list.indexOf(existHotel);
                list.set(index, newHotel);
            }
            
            Utils.writeToFile(url, list);
            
           
        } catch (Exception e) {
        }
    }
    
    @Override
    public void deleteHotel(String url) {
        try {
            HotelList hotels = Utils.readFromFile(url);
            
            String hotelID = Utils.getID();
            
            Hotel delHotel = null;
            
            for (Hotel hotel : hotels) {
                if (hotelID.equals(hotel.getId())) {
                    delHotel = hotel;
                }
            }
            
            if (delHotel != null) {
                boolean confirm = Utils.getBoolean("You wanna delete hotel with id " + delHotel.getId() + "?");
                if (confirm) {
                    hotels.remove(delHotel);
                    System.out.println("This hotel has been deleted!");
                }
            } else {
                System.out.println("This hotel is not exist!");
            }
            
            Utils.writeToFile(url, hotels);
        } catch (Exception e) {
        }
    }
    
    @Override
    public void searchByNam(String url) {
        try {
            HotelList hotels = Utils.readFromFile(url);
            
            String hotelName = Utils.getString("Input Hotel's Name: ");
            
            HotelList result = null;
            
            for (Hotel hotel : hotels) {
                if (hotelName.equals(hotel.getName())) {
                    result.add(hotel);                    
                }
            }
            if (result != null) {
                for(Hotel hotel : result)
                {
                    hotel.display();
                    System.out.println("\n");
                }
            } else {
                System.out.println("This hotel is not exist!");
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public void seachByID(String url) {
        try {
            HotelList hotels = Utils.readFromFile(url);
            
            HotelList resultList = new HotelList();
            
            String hotelID = Utils.getString("Input Hotel's ID: ");
            
            Hotel result = null;
            
            for (Hotel hotel : hotels) {
                if (hotel.getId().toLowerCase().contains(hotelID.toLowerCase()) || hotelID.toLowerCase().contains(hotel.getId().toLowerCase())) {
                    resultList.add(hotel);
                }
            }
            if (resultList != null) {
                resultList.sort();
                System.out.println("Hotel(s) have an ID like " + hotelID + ": ");
                for (Hotel hotel : resultList) {
                    hotel.display();
                }
            } else {
                System.out.println("Do not have any hotel with id: " + hotelID + "! ");
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public void sort() {
        Collections.sort(this);
    }
    
    @Override
    public void display(String url) {
        try {
            HotelList hotels = Utils.readFromFile(url);
            
            if (hotels != null) {
                hotels.sort();
                for (Hotel hotel : hotels) {
                    hotel.display();
                }
            } else {
                System.out.println("Do not have any Hotel in you file!");
            }
        } catch (Exception e) {
        }
        
          
    }
    
}
