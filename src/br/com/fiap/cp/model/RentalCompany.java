package br.com.fiap.cp.model;

import br.com.fiap.cp.helper.Cnh;
import br.com.fiap.cp.helper.Color;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class RentalCompany {
    Scanner scan = new Scanner(System.in);
    ArrayList<Client> registeredClients = new ArrayList<>();
    ArrayList<Vehicle> registeredVehicles = new ArrayList<>();
    public void showOptions(){

        System.out.printf("-------------------------------%n[1] - Register a new Client%n[2] - Register a new Vehicle%n[3] - Rent a Vehicle%n[4] - Unrent a Vehicle%n[5] - Exit%nType the option number: ");
        String option = scan.nextLine();

        switch (option){
            case "1":
                registerClient();
                break;
            case "2":
                registerVehicle();
                break;
            case "3":
                rentCar();
                break;
            case "4":
                unrentCar();
                break;
            case "5":
                System.out.printf("%nThank you for working with us! Goodbye!");
                break;
            default:
                showOptions();
                break;
        }
    }

    private void registerClient(){

        System.out.printf("%n------------------------------------------------------------------%n-------------------- Registering a new client! -------------------%n------------------------------------------------------------------%n");

        System.out.printf("%nCould you please tell us your name: ");
        String name = scan.nextLine();

        System.out.printf("%nCould you please tell us your CNH's registration number: ");
        String cnhRegistrationNum = scan.nextLine();

        Cnh cnh = new Cnh(cnhRegistrationNum);

        System.out.printf("%nCould you please insert your CNH's emission date (dd-mm-yyyy): ");
        String cnhEmissionDate = scan.nextLine();

        while (!cnh.checkDatePattern(cnhEmissionDate)){
            System.out.printf("%nCould you please insert your CNH's emission date (dd-mm-yyyy): ");
            cnhEmissionDate = scan.nextLine();
        }

        System.out.printf("%nCould you please insert your CNH's expiration date (dd-mm-yyyy): ");
        String cnhExpiryDate = scan.nextLine();

        while (!cnh.checkDatePattern(cnhExpiryDate)){
            System.out.printf("%nCould you please insert your CNH's expiration date (dd-mm-yyyy): ");
            cnhExpiryDate = scan.nextLine();
        }

        cnh.setEmissionDate(cnhEmissionDate);
        cnh.setExpiryDate(cnhExpiryDate);

        System.out.printf("%nCould you please tell us your CPF: ");
        String cpf = scan.nextLine();

        System.out.printf("%nThanks for registering with us, " + name + "%n");

        registeredClients.add(new Client(name, cnh, cpf));

        showOptions();
    }

    private void registerVehicle(){

        System.out.printf("%n------------------------------------------------------------------%n------------------- Registering a new vehicle! -------------------%n------------------------------------------------------------------%n");

        System.out.printf("%nCould you please insert the name of the car's color: ");
        String colorName = scan.nextLine();
        Color color = new Color(colorName);

        System.out.printf("%nCould you please insert the values of the car's color (r,g,b) [255,0,0]: ");
        String rgb = scan.nextLine();

        while (!color.checkColorPattern(rgb)) {
            System.out.printf("%nCould you please insert the values of the car's color (r,g,b) [255,0,0]: ");
            rgb = scan.nextLine();
        }

        color.setRgb(rgb);

        System.out.printf("%nCould you please tell us the car's model: ");
        String model = scan.nextLine();

        System.out.printf("%nCould you please inform your plate: ");
        String plate = scan.nextLine();

        System.out.printf("%nCould you please tell us the engine number: ");
        String engine = scan.nextLine();

        System.out.printf("%nWould you please inform the price per day of this vehicle: ");
        float pricePerDay = Float.parseFloat(scan.nextLine());

        registeredVehicles.add(new Vehicle(color, model, plate, engine, pricePerDay));

        showOptions();
    }

    private Client findClient(String givenCpf) {

        Client findedClient = null;

        for (Client client : registeredClients) {
            if (Objects.equals(client.getCpf(), givenCpf)) findedClient = client;
        }

        return findedClient;
    }

    private Vehicle findVehicle(String givenCarPlate) {

        Vehicle findedVehicle = null;

        for (Vehicle vehicle : registeredVehicles) {
            if (Objects.equals(vehicle.getPlate(), givenCarPlate)) findedVehicle = vehicle;
        }

        return findedVehicle;
    }
    private void rentCar(){

        System.out.printf("%n------------------------------------------------------------------%n-------------------------- Renting a car! ------------------------%n------------------------------------------------------------------%n");

        System.out.printf("%nTell me the client cpf: ");
        Client renter = findClient(scan.nextLine());
        if (renter != null) System.out.println(renter.getData());
        else {
            System.out.printf("%nClient couldn't be found!");
            showOptions();
            return;
        }

        System.out.printf("%nTell me the plate of the car you want to rent: ");
        Vehicle car = findVehicle(scan.nextLine());
        if (car != null) System.out.println(car.getData());
        else {
            System.out.printf("%nVehicle couldn't be found!");
            showOptions();
            return;
        }

        if (!car.isRented() && renter.getCnh().validateCnh() && !renter.isRenting()) {
            System.out.printf("%nFor how long do you want to rent this car (days): ");
            car.setRentedDays(Integer.parseInt(scan.nextLine()));

            car.setRenter(renter);
            car.setRented(true);

            renter.setRenting(true);
            System.out.printf("%nThank you for renting our car, it'll be only R$ " + car.getPricePerDay() * car.getRentedDays() + "! (" + car.getModel() + " is ready for use, " + renter.getName() + ")%n");
        } else {
            System.out.printf("%nSorry, the car (" + car.getModel() + ") could not be rented for one of these 3 reasons:%nThe car is already rented;%nThe renter CNH has expired;%nThe renter is already renting another car.%n");
        }

        showOptions();
    }

    private void unrentCar(){

        System.out.printf("%n------------------------------------------------------------------%n------------------------- Unrenting a car! -----------------------%n------------------------------------------------------------------%n");

        System.out.printf("%nTell me the plate of the car you want to unrent: ");
        Vehicle car = findVehicle(scan.nextLine());
        if (car != null) car.getData();
        else {
            System.out.printf("%nVehicle couldn't be found!%n");
            showOptions();
            return;
        }

        if (car.isRented()){
            car.getRenter().setRenting(false);
            car.setRenter(null);
            car.setRentedDays(0);
            car.setRented(false);
            System.out.printf("%nThe car (" + car.getModel() + ") was unrented!%n");
        } else {
            System.out.printf("%nThe car(" + car.getModel() + ") has not even been rented yet!%n");
        }

        showOptions();
    }
}
