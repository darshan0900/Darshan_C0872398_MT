package com.darshan09200.carrentingcenter;

import java.util.ArrayList;

enum AgeGroup {
    UNDER_20("Under 20"),
    BETWEEN_21_AND_60("Between 21 and 60"),
    ABOVE_60("Above 60");

    private final String label;

    AgeGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

public class Database {
    private static Database databaseInstance = new Database();

    public static int ABOVE_60_DISCOUNT = 10;

    public static int UNDER_20_CHARGES = 5;
    public static int GPS_CHARGES = 5;
    public static int CHILD_SEAT_CHARGES = 7;
    public static int UNLIMITED_MILEAGE_CHARGES = 10;

    private final ArrayList<Car> carNamesData;
    private boolean clearDataFlag = false;

    private Database() {
        carNamesData = new ArrayList<>();
        carNamesData.add(new Car("Please Choose a Car", -1));
        carNamesData.add(new Car("BMW", 50));
        carNamesData.add(new Car("Audi", 65));
        carNamesData.add(new Car("Cadillac", 30));
        carNamesData.add(new Car("Volks Wagon", 45));
        carNamesData.add(new Car("Mercedes", 65));
        carNamesData.add(new Car("Peugeot", 40));
    }

    public static Database getInstance() {
        return databaseInstance;
    }

    public ArrayList<Car> getCarNamesData() {
        return carNamesData;
    }

    public ArrayList<String> getCarNames() {
        ArrayList<String> carNames = new ArrayList<>();
        getCarNamesData().forEach(car -> {
            carNames.add(car.getName());
        });

        return carNames;
    }

    public Car getCar(int position) {
        if (position > 0 && position < carNamesData.size()) {
            Car currentCar = carNamesData.get(position);
            return currentCar;
        }
        return null;
    }

    public void setClearDataFlag() {
        clearDataFlag = true;
    }

    public boolean shouldClearData() {
        return clearDataFlag;
    }

    public void resetClearDataFlag() {
        clearDataFlag = false;
    }
}
