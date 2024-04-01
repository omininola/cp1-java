package br.com.fiap.cp.model;
import br.com.fiap.cp.helper.Color;

import java.util.Scanner;

public class Vehicle {

    private Color color;
    private String model;
    private String plate;
    private String engine;
    private double pricePerDay;
    private boolean isRented = false;
    private int rentedDays = 0;
    private Client renter = null;

    public Vehicle(Color color, String model, String plate, String engine, double pricePerDay) {
        this.color = color;
        this.model = model;
        this.plate = plate;
        this.engine = engine;
        this.pricePerDay = pricePerDay;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }

    protected void setRented(boolean rented) {
        isRented = rented;
    }

    public int getRentedDays() {
        return rentedDays;
    }

    protected void setRentedDays(int rentedDays) {
        this.rentedDays = rentedDays;
    }

    public Client getRenter() {
        return renter;
    }

    protected void setRenter(Client renter) {
        this.renter = renter;
    }
}
