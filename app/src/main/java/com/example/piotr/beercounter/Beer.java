package com.example.piotr.beercounter;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Piotr on 2017-06-15.
 */

public class Beer implements Serializable {
    private long id;
    private double price =0 ;
    private int quantity;
    private double finalPrice;
    private String date;
    private ArrayList<Food> food= new ArrayList<Food>();
    private ArrayList<Borrow> borrowed = new ArrayList<Borrow>();
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

    public void setBorrowed(String borrowed, double price){
        this.finalPrice +=price;
        this.borrowed.add(new Borrow(borrowed,price));
    }
    public void setFood(String food, double price){
        this.finalPrice +=price;
        this.food.add(new Food(food, price));
    }

    public ArrayList getFood(){
        return food;
    }
    public ArrayList getBorrowed() {
        return borrowed;
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
