package com.example.piotr.beercounter;

/**
 * Created by Piotr on 2017-06-15.
 */

public class Beer {
    private long id;
    private double price;
    private int quantity;
    private String finalPrice;

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

    @Override
    public String toString(){
        return getFinalPrice();
    }
}
