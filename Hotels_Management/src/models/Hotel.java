/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class Hotel implements Serializable, Comparable<Hotel> {

    private String id;
    private String name;
    private int roomAvailable;
    private String address;
    private String phone;
    private int rate;

    public Hotel(String id, String name, int roomAvailable, String address, String phone, int rate) {
        this.id = id;
        this.name = name;
        this.roomAvailable = roomAvailable;
        this.address = address;
        this.phone = phone;
        this.rate = rate;
    }

    public Hotel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(int roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void display() {
        System.out.println("\nHotel ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Phone: " + phone + "\n"
                + "Address: " + address + "\n"
                + "Room: " + roomAvailable + "\n"
                + "Rate: " + rate);
    }

    @Override
    public int compareTo(Hotel other) {
        return other.getName().compareTo(this.getName());
    }

}
