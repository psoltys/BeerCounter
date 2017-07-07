package com.example.piotr.beercounter;

import java.io.Serializable;

/**
 * Created by Piotr on 2017-07-07.
 */

public class Food implements Serializable {
    private double price;
    private String name;

    Food(){

    }
    Food(String name, double price){
        this.name = name;
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    @Override
    public String toString(){
        return name + " cena: " + price;
    }
}
