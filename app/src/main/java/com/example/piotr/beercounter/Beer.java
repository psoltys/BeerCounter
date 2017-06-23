package com.example.piotr.beercounter;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
/**
 * Created by Piotr on 2017-06-15.
 */

public class Beer implements Serializable {
    private long id;
    private double price;
    private int quantity;
    private double finalPrice;
    private String date;
    @Exclude
    private String key;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public double getFinalPrice(){
        return finalPrice;
    }
    public void setFinalPrice(double finalPrice){
        this.finalPrice = finalPrice;
    }

    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return key;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }
    @Override
    public String toString(){
        return "Cena: "+ getFinalPrice() + " Data: " + getDate() +  " Ilosc: " + getQuantity();
    }
}
