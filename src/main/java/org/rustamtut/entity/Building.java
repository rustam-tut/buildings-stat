package org.rustamtut.entity;

import java.util.Objects;

public class Building {

    private String city;

    private String street;

    private int house;

    private int floor;

    public Building(String settlement, String address, int number, int floors) {
        this.city = settlement;
        this.street = address;
        this.house = number;
        this.floor = floors;
    }

    public Building() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return house == building.house
                && floor == building.floor
                && city.equals(building.city)
                && street.equals(building.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floor);
    }

    @Override
    public String toString() {
        return city + ", " + street + ", " + "#" + house + ", " + floor + " floors;";
    }
}
