package com.example.shubhamkumar.code_for_good;

/**
 * Created by Shubham Kumar on 7/8/2018.
 */

public class Equipment {

    private String name;
    private int quantity;

    public Equipment(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;

    }
}
