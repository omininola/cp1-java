package br.com.fiap.cp.model;

import br.com.fiap.cp.helper.Cnh;
import br.com.fiap.cp.helper.Color;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class RentalCompany {
    Scanner scan = new Scanner(System.in);
    ArrayList<Client> registeredClients = new ArrayList<Client>();
    ArrayList<Vehicle> registeredVehicles = new ArrayList<Vehicle>();
    public void showOptions(){

        String[] options = {"Register Client", "Register Vehicle", "Rent Vehicle", "Unrent Vehicle", "Exit"};
        int option = JOptionPane.showOptionDialog(null, "Choose what you want to do:", "Actions you can do!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//        System.out.println("-------------------------------");
//        System.out.printf("[1] - Register a new Client%n[2] - Register a new Vehicle%n[3] - Rent a Vehicle%n[4] - Unrent a Vehicle%n[5] - Exit%nType the option number: ");
//        String option = scan.nextLine();

        switch (option){
            case 0:
                registerClient();
                break;
            case 1:
                registerVehicle();
                break;
            case 2:
                rentCar();
                break;
            case 3:
                unrentCar();
                break;
            case 4:
                System.out.println("Thank you for working with us! Goodbye!");
                break;
//            default:
//                showOptions();
//                break;
        }
    }

    private Client registerClient(){

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("-------------------- Registering a new client! -------------------");
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("Could you please tell us your name: ");
        String name = scan.nextLine();

        System.out.println("Could you please tell us your CNH's registration number: ");
        String cnhRegistrationNum = scan.nextLine();

        Cnh cnh = new Cnh(cnhRegistrationNum);

        System.out.println("Could you please insert your CNH's emission date (dd-mm-yyyy): ");
        String cnhEmissionDate = scan.nextLine();

        while (!cnh.checkDatePattern(cnhEmissionDate)){
            System.out.println("Could you please insert your CNH's emission date (dd-mm-yyyy): ");
            cnhEmissionDate = scan.nextLine();
        }

        System.out.println("Could you please insert your CNH's expiration date (dd-mm-yyyy): ");
        String cnhExpiryDate = scan.nextLine();

        while (!cnh.checkDatePattern(cnhExpiryDate)){
            System.out.println("Could you please insert your CNH's expiration date (dd-mm-yyyy): ");
            cnhExpiryDate = scan.nextLine();
        }

        cnh.setEmissionDate(cnhEmissionDate);
        cnh.setExpiryDate(cnhExpiryDate);

        System.out.println("Could you please tell us your CPF: ");
        String cpf = scan.nextLine();

        System.out.println("Thanks for registering with us, " + name);

        Client client = new Client(name, cnh, cpf);

        registeredClients.add(client);

        showOptions();

        return client;
    }

    private Vehicle registerVehicle(){

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("------------------- Registering a new vehicle! -------------------");
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("Could you please insert the name of the car's color: ");
        String colorName = scan.nextLine();
        Color color = new Color(colorName);

        System.out.println("Could you please insert the values of the car's color (r,g,b) [255,0,0]: ");
        String rgb = scan.nextLine();

        while (!color.checkColorPatter(rgb)) {
            System.out.println("Could you please insert the values of the car's color (r,g,b) [255,0,0]: ");
            rgb = scan.nextLine();
        }

        color.setRgb(rgb);

        System.out.println("Could you please tell us the car's model: ");
        String model = scan.nextLine();

        System.out.println("Could you please inform your plate: ");
        String plate = scan.nextLine();

        System.out.println("Could you please tell us the engine number: ");
        String engine = scan.nextLine();

        System.out.println("Would you please inform the price per day of this vehicle: ");
        float pricePerDay = scan.nextFloat();

        Vehicle vehicle = new Vehicle(color, model, plate, engine, pricePerDay);

        registeredVehicles.add(vehicle);

        showOptions();

        return vehicle;
    }

    private Client findClient(String infomedCpf) {

        Client findedClient = null;

        for (Client client : registeredClients) {
            if (Objects.equals(client.getCpf(), infomedCpf)) findedClient = client;
        }

        return findedClient;
    }

    private Vehicle findVehicle(String carPlate) {

        Vehicle findedVehicle = null;

        for (Vehicle vehicle : registeredVehicles) {
            if (Objects.equals(vehicle.getPlate(), carPlate)) findedVehicle = vehicle;
        }

        return findedVehicle;
    }
    private void rentCar(){

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("-------------------------- Renting a car! ------------------------");
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("Tell me the client cpf: ");
        Client renter = findClient(scan.nextLine());
        if (renter != null) System.out.println(renter.getData());
        else {
            System.out.println("Client couldn't be found!");
            showOptions();
            return;
        }

        System.out.println("Tell me the plate of the car you want to rent: ");
        Vehicle car = findVehicle(scan.nextLine());
        if (car != null) System.out.println(car.getData());
        else {
            System.out.println("Vehicle couldn't be found!");
            showOptions();
            return;
        }

        if (!car.isRented() && renter.getCnh().validateCnh() && !renter.isRenting()) {
            System.out.println("For how long do you want to rent this car (days): ");
            car.setRentedDays(scan.nextInt());

            car.setRenter(renter);
            car.setRented(true);

            renter.setRenting(true);
            System.out.println("Thank you for renting our car, it'll be only R$ " + car.getPricePerDay() * car.getRentedDays() + "! (" + car.getModel() + " is ready for use, " + renter.getName() + ")");
        } else {
            System.out.printf("Sorry, the car (" + car.getModel() + ") could not be rented for one of these 3 reasons:%nThe car is already rented;%nThe renter CNH has expired;%nThe renter is already renting another car.%n");
        }

        showOptions();
    }

    private void unrentCar(){

        System.out.println("\n------------------------------------------------------------------");
        System.out.println("------------------------- Unrenting a car! -----------------------");
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("Tell me the plate of the car you want to unrent: ");
        Vehicle car = findVehicle(scan.nextLine());
        if (car != null) car.getData();
        else {
            System.out.println("Vehicle couldn't be found!");
            showOptions();
            return;
        }

        if (car.isRented()){
            car.getRenter().setRenting(false);
            car.setRenter(null);
            car.setRentedDays(0);
            car.setRented(false);
            System.out.println("The car (" + car.getModel() + ") was unrented!");
        } else {
            System.out.println("The car(" + car.getModel() + ") has not even been rented yet!");
        }

        showOptions();
    }
}
