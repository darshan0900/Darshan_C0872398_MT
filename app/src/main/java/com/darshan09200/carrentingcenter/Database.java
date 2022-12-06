package com.darshan09200.carrentingcenter;

import java.util.ArrayList;

enum AgeGroup {
    UNDER_20,
    BETWEEN_21_AND_60,
    ABOVE_60
}

public class Database {
    private static Database databaseInstance = new Database();

    public static int ABOVE_60_DISCOUNT = 10;

    public static int UNDER_20_CHARGES = 5;
    public static int GPS_CHARGES = 5;
    public static int CHILD_SEAT_CHARGES = 7;
    public static int UNLIMITED_MILEAGE_CHARGES = 10;

    private final ArrayList<Car> carNamesData;

    private Database() {
        carNamesData = new ArrayList<>();
        carNamesData.add(new Car("Please Choose a Car", -1));
        carNamesData.add(new Car("BMW", 10));
        carNamesData.add(new Car("Audi", 10));
        carNamesData.add(new Car("Cadillac", 10));
        carNamesData.add(new Car("Volks Wagon", 10));
        carNamesData.add(new Car("Mercedes", 10));
        carNamesData.add(new Car("Peugeot", 10));
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
}
