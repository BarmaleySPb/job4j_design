package ru.job4j.design.lsp.parking;

public class Car extends Vehicle {

    public static final int SIZE_OF_CAR = 1;


    public Car(String number) {
        super(SIZE_OF_CAR, number);
    }
}
