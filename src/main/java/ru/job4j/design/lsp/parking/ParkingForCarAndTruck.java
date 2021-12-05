package ru.job4j.design.lsp.parking;


public class ParkingForCarAndTruck implements Parking {
    private final String[] parkingForCarAndTruck;
    private final int spacesForCar;
    private final int spacesForTruck;



    public ParkingForCarAndTruck(int spacesForCar, int spacesForTruck) {
        this.parkingForCarAndTruck = new String[spacesForCar + spacesForTruck];
        this.spacesForCar = spacesForCar;
        this.spacesForTruck = spacesForTruck;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            if (getNumberOfFreeSpacesForCar() > 0) {
                for (int i = 0; i < spacesForCar; i++) {
                    if (parkingForCarAndTruck[i] == null) {
                        parkingForCarAndTruck[i] = vehicle.getNumber();
                        return true;
                    }
                }
            }
            return false;
        }
        if (getNumberOfFreeSpacesForTruck() > 0) {
            for (int i = spacesForCar; i < parkingForCarAndTruck.length; i++) {
                if (parkingForCarAndTruck[i] == null) {
                    parkingForCarAndTruck[i] = vehicle.getNumber();
                    return true;
                }
            }
        } else if (getNumberOfFreeSpacesForCar() >= vehicle.getSize()) {
            int countFreeSpace = 0;
            for (int i = 0; i < spacesForCar; i++) {
                if (parkingForCarAndTruck[i] == null) {
                    countFreeSpace++;
                    if (countFreeSpace == vehicle.getSize()) {
                        for (int j = i; j > i - vehicle.getSize(); j--) {
                            parkingForCarAndTruck[j] = vehicle.getNumber();
                        }
                    }
                } else {
                    countFreeSpace = 0;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeVehicle(Vehicle vehicle) {
        boolean result = false;
        for (int i = 0; i < parkingForCarAndTruck.length; i++) {
            if (vehicle.getNumber().equals(parkingForCarAndTruck[i])) {
                parkingForCarAndTruck[i] = null;
                result = true;
            }
        }
        return result;
    }

    @Override
    public int getNumberOfFreeSpacesForTruck() {
        int result = 0;
        for (int i = spacesForCar; i < parkingForCarAndTruck.length; i++) {
            if (parkingForCarAndTruck[i] == null) {
                result++;
            }
        }
        return result;
    }

    @Override
    public int getNumberOfFreeSpacesForCar() {
        int result = 0;
        for (int i = 0; i < spacesForCar; i++) {
            if (parkingForCarAndTruck[i] == null) {
                result++;
            }
        }
        return result;
    }

    @Override
    public String getNumberOfVehicle(int spaceNumber) {
        return parkingForCarAndTruck[spaceNumber];
    }

    @Override
    public int getSize() {
        return parkingForCarAndTruck.length;
    }
}
