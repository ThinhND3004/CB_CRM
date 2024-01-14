/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controllers.HotelList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import models.Hotel;

/**
 *
 * @author ACER
 */
public class Utils {

    private static Scanner sc;

    public static String getID() {
        String id = null;
        do {
            id = Utils.getString("Input Hotel's ID (start with 'H'): ");
        } while (!id.startsWith("H"));

        return id;
    }

    public static String getString(String welcome) {
        String result = "";
        sc = new Scanner(System.in);
        boolean check = true;
        do {
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.length() > 0) {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getNumber(String welcome, int min, int max) {
        int result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                result = sc.nextInt();
                if (result >= min || result <= max) {
                    check = false;
                }
            } catch (Exception e) {
            }
        } while (check);
        return result;
    }

    public static double getNumber(String welcome, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double result = 0.0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.println(welcome);
                result = scanner.nextDouble();
                check = true;
            } catch (Exception e) {
                scanner.nextLine();
            }
        } while (check);
        return result;
    }

    public static String updateString(String welcome, String oldValue) {
        String result = "";
        System.out.print(welcome);
        sc = new Scanner(System.in);
        result = sc.nextLine();
        if (result.length() == 0) {
            result = oldValue;
        }

        return result;
    }

    
    public static String updateID(String mes, String oldID)
    {
        String result = null;
        boolean check = false;
        do {            
            result = Utils.updateString(mes, oldID);
            if(!result.startsWith("H"))
            {
                System.out.println("ID must be start with \"H\": ");
            } else
            {
                check = true;
            }
        } while (!check);
        
        return result;
    }
    
    
    public static int updateInt(String mess, int oldValue)
    {
        int result = -1;
        boolean check = false;
        do {    
            System.out.println(mess);
            String input = sc.nextLine();
            if(input.length() == 0)
            {
                result = oldValue;
                check = true;
            } else
            {
                result = Integer.parseInt(input);
                check = true;
            }
        } while (!check);
        return result;
    }
    
    
    public static String updatePhone(String mes, String oldPhone)
    {
        String result = "";
        boolean check = false;
        do {
            result = Utils.updateString(mes, oldPhone);
            if (result.matches("\\d{10}")) {
                check = true;
            } else {
                System.out.println("Phone numbers can only contain 10 numbers and cannot contain special characters\n\n");
            }
        } while (!check);
        return result;
    }
    
    
    public static int updateNumber(String welcome, int min, int max, int oldValue) {
        int result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                String str = sc.nextLine();
                if (str.length() == 0) {
                    result = oldValue;
                    check = false;
                } else {
                    result = Integer.parseInt(str);
                    if (result > min && result < max) {
                        check = false;
                    }
                }

            } catch (Exception e) {
            }
        } while (check);
        return result;
    }

    public static double updateNumber(String welcome, double min, double max, double oldValue) {
        double result = 0;
        boolean check = true;
        sc = new Scanner(System.in);
        do {
            try {
                System.out.print(welcome);
                String str = sc.nextLine();
                if (str.length() == 0) {
                    result = oldValue;
                    check = false;
                } else {
                    result = Integer.parseInt(str);
                    if (result > min && result < max) {
                        check = false;
                    }
                }

            } catch (Exception e) {
            }
        } while (check);
        return result;
    }

    public static boolean getBoolean(String welcome) {
        boolean check = false;
        System.out.print(welcome);
        sc = new Scanner(System.in);
        String str = sc.nextLine();
        if ("y".equalsIgnoreCase(str)) {
            check = true;
        }
        return check;
    }

    public static boolean writeToFile(String url, HotelList hotelList) {
        boolean result = false;

        try (FileOutputStream file = new FileOutputStream(url);
                ObjectOutputStream oos = new ObjectOutputStream(file)) {

            for (Hotel hotel : hotelList) {
                oos.writeObject(hotel);
            }

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static HotelList readFromFile(String url) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(url);
        ObjectInputStream ois = new ObjectInputStream(fis);
        HotelList readList = null;
        try {
            Hotel hotel = null;
            readList = new HotelList();
            while (fis.available() > 0) {
                hotel = (Hotel) ois.readObject();
                readList.add(hotel);
            }
        } catch (Exception e) {
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return readList;
    }

    public static String getPhone() {
        String phone = null;
        boolean check = false;
        do {
            phone = Utils.getString("Input Hotel's Phone: ");
            if (phone.matches("\\d{10}")) {
                check = true;
            } else {
                System.out.println("Phone numbers can only contain 10 numbers and cannot contain special characters\n\n");
            }
        } while (check == false);

        return phone;
    }

    public static int getInt(String mes) {
        int result = 0;
        boolean check = false;

        do {
            System.out.println(mes);
            try {
                result = sc.nextInt();
                check = true; 
            } catch (Exception e) {
                System.out.println("Your input is invalid! Please enter an integer.");
                sc.nextLine(); 
            }
        } while (!check);

        return result;
    }

    public static int getRate(String mes) {
        int rate = 0;
        boolean check = false;
        do {
            rate = Utils.getInt(mes);
            if (rate < 1 || rate > 5) {
                System.out.println("Rate must be from 1 to 5!!!");
            } else {
                check = true;
            }
        } while (!check);

        return rate;
    }
}
