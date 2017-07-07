package com.example.piotr.beercounter;

import java.io.Serializable;

/**
 * Created by Piotr on 2017-07-07.
 */

public class Borrow implements Serializable {
    private String name;
    private double price;

    Borrow(){

    }
    Borrow(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public Double getPrice(){
        return price;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString(){
        return name + " ile: " + price;
    }
}
