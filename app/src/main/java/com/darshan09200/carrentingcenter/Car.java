package com.darshan09200.carrentingcenter;

public class Car {
    private final String name;
    private final double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
