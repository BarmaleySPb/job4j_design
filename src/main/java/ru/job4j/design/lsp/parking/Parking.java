package ru.job4j.design.lsp.parking;

public interface Parking {

    boolean addVehicle(Vehicle vehicle);
    boolean removeVehicle(Vehicle vehicle);
    String getNumberOfVehicle(int spaceNumber);
    int getSize();
    int getNumberOfFreeSpacesForTruck();
    int getNumberOfFreeSpacesForCar();
}
