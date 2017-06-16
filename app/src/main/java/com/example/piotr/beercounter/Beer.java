package com.example.piotr.beercounter;

import java.io.Serializable;

/**
 * Created by Piotr on 2017-06-15.
 */

public class Beer implements Serializable {
    private long id;
    private double price;
    private int quantity;
    private String finalPrice;
    private String date;

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

    public String getFinalPrice(){
        finalPrice = Double.toString(price* quantity);
        return finalPrice;
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
