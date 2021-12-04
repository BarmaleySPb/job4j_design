package ru.job4j.design.lsp.parking;

import java.util.Objects;

public abstract class Vehicle {
    private int size;
    private String number;

    public Vehicle(int size, String number) {
        this.size = size;
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return size == vehicle.size && number.equals(vehicle.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, number);
    }
}
