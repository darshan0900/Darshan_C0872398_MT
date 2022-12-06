package com.darshan09200.carrentingcenter;

public class Car {
    private final String name;
    private final double dailyRent;

    public Car(String name, double dailyRent) {
        this.name = name;
        this.dailyRent = dailyRent;
    }

    public String getName() {
        return name;
    }

    public double getDailyRent() {
        return dailyRent;
    }
}
