package ru.job4j.design.lsp.parking;

import org.junit.Assert;
import org.junit.Test;


public class ParkingTest {

    @Test
    public void whenParkingCar() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(2, 2);
        Car car = new Car("asd");
        parking.addVehicle(car);
        Assert.assertEquals(car.getNumber(), parking.getNumberOfVehicle(0));
    }

    @Test
    public void whenRemoveCarFromParking() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(2, 2);
        Car car = new Car("asd");
        parking.addVehicle(car);
        Assert.assertEquals(car.getNumber(), parking.getNumberOfVehicle(0));
        parking.removeVehicle(car);
        Assert.assertNull(parking.getNumberOfVehicle(0));
    }

    @Test
    public void whenParkingTruck() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(2, 2);
        Truck truck = new Truck(3, "asd");
        parking.addVehicle(truck);
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(2));
    }

    @Test
    public void whenParkingTruckAtCarPlace() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(3, 0);
        Truck truck = new Truck(3, "asd");
        parking.addVehicle(truck);
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(0));
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(1));
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(2));
    }

    @Test
    public void whenParkingTruckAtCarPlaceAndParkingOneCar() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(4, 0);
        Truck truck = new Truck(3, "asd");
        Car car = new Car("dsa");
        parking.addVehicle(truck);
        parking.addVehicle(car);
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(0));
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(1));
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(2));
        Assert.assertEquals(car.getNumber(), parking.getNumberOfVehicle(3));
    }

    @Test
    public void whenParkingTruckAtCarPlaceAndParkingTwoCars() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(5, 0);
        Truck truck = new Truck(3, "asd");
        Car car = new Car("dsa");
        Car secondCar = new Car("qwe");
        parking.addVehicle(secondCar);
        parking.addVehicle(truck);
        parking.addVehicle(car);
        Assert.assertEquals(secondCar.getNumber(), parking.getNumberOfVehicle(0));
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(1));
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(2));
        Assert.assertEquals(truck.getNumber(), parking.getNumberOfVehicle(3));
        Assert.assertEquals(car.getNumber(), parking.getNumberOfVehicle(4));
    }

    @Test
    public void whenParkingTruckAtCarPlaceAndParkingTwoCars2() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(2, 0);
        Truck truck = new Truck(3, "asd");
        Car car = new Car("dsa");
        Car secondCar = new Car("qwe");
        Car thirdCar = new Car("zxc");
        Car fourthCar = new Car("cxz");
        parking.addVehicle(thirdCar);
        parking.addVehicle(fourthCar);
        Assert.assertFalse(parking.addVehicle(secondCar));
        Assert.assertFalse(parking.addVehicle(truck));
        Assert.assertFalse(parking.addVehicle(car));
        Assert.assertEquals(thirdCar.getNumber(), parking.getNumberOfVehicle(0));
        Assert.assertEquals(fourthCar.getNumber(), parking.getNumberOfVehicle(1));
    }

    @Test
    public void whenRemoveCarButCarIsNull() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(2, 2);
        Car car = new Car("asd");
        Car secondCar = new Car("qwe");
        parking.addVehicle(car);
        Assert.assertFalse(parking.removeVehicle(secondCar));
    }

    @Test
    public void whenParkingTruckAtCarPlaceAndNotEnoughPlaces() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(5, 0);
        Truck truck = new Truck(3, "asd");
        Car car = new Car("dsa");
        Car secondCar = new Car("qwe");
        Car thirdCar = new Car("zxc");
        parking.addVehicle(secondCar);
        parking.addVehicle(thirdCar);
        parking.addVehicle(car);
        parking.removeVehicle(thirdCar);
        Assert.assertFalse(parking.addVehicle(truck));
    }

    @Test
    public void whenOneFreeSpacePlacesForTruckAndFreeSpacesForCarButNotNearby() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(5, 1);
        Truck truck = new Truck(3, "asd");
        Truck secondTruck = new Truck(3, "as");
        Car car = new Car("dsa");
        Car secondCar = new Car("qwe");
        Car thirdCar = new Car("zxc");
        parking.addVehicle(secondCar);
        parking.addVehicle(thirdCar);
        parking.addVehicle(car);
        parking.removeVehicle(thirdCar);
        parking.addVehicle(truck);
        Assert.assertFalse(parking.addVehicle(secondTruck));
        Assert.assertEquals(3, parking.getNumberOfFreeSpacesForCar());
        Assert.assertEquals(0, parking.getNumberOfFreeSpacesForTruck());
    }

    @Test
    public void whenRemoveTruckFromPlaceForCar() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(5, 0);
        Truck truck = new Truck(3, "asd");
        parking.addVehicle(truck);
        Assert.assertEquals(2, parking.getNumberOfFreeSpacesForCar());
        Assert.assertTrue(parking.removeVehicle(truck));
        Assert.assertEquals(5, parking.getNumberOfFreeSpacesForCar());
    }

    @Test
    public void whenRemoveTruckFromPlaceForTruck() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(5, 3);
        Truck truck = new Truck(3, "asd");
        parking.addVehicle(truck);
        Assert.assertTrue(parking.removeVehicle(truck));
        Assert.assertEquals(3, parking.getNumberOfFreeSpacesForTruck());
    }

    @Test
    public void whenGetFreeSpacesForTruck() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(5, 3);
        Truck truck = new Truck(3, "asd");
        parking.addVehicle(truck);
        Assert.assertEquals(2, parking.getNumberOfFreeSpacesForTruck());
    }

    @Test
    public void whenGetFreeSpacesForCar() {
        ParkingForCarAndTruck parking = new ParkingForCarAndTruck(5, 0);
        Truck truck = new Truck(3, "asd");
        Car car = new Car("asdf");
        parking.addVehicle(truck);
        parking.addVehicle(car);
        Assert.assertEquals(1, parking.getNumberOfFreeSpacesForCar());
    }
}