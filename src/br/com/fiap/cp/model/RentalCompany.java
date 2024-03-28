package br.com.fiap.cp.model;

import br.com.fiap.cp.helper.Cnh;
import br.com.fiap.cp.helper.Color;

import java.util.Scanner;

public class RentalCompany {
    Scanner scan = new Scanner(System.in);
    public Client registerClient(){

        System.out.println("------------------------------------------------------------------");
        System.out.println("-------------------- Registering a new client! -------------------");
        System.out.println("------------------------------------------------------------------");


        System.out.println("Could you please tell us your name: ");
        String name = scan.nextLine();

        System.out.println("Could you please insert your CNH's emission date (dd-mm-yyyy): ");
        String cnhEmissionDate = scan.nextLine();

        System.out.println("Could you please insert your CNH's expiration date (dd-mm-yyyy): ");
        String cnhExpiryDate = scan.nextLine();

        System.out.println("Could you please tell us your CNH's registration number: ");
        String cnhRegistrationNum = scan.nextLine();

        Cnh cnh = new Cnh(cnhRegistrationNum);
        cnh.setEmissionDate(cnhEmissionDate);
        cnh.setExpiryDate(cnhExpiryDate);

        System.out.println("Could you please tell us your CPF: ");
        String cpf = scan.nextLine();

        System.out.println("Thanks for registering with us, " + name);

        System.out.flush();

        return new Client(name, cnh, cpf);
    }

    public Vehicle registerVehicle(){

        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------- Registering a new vehicle! -------------------");
        System.out.println("------------------------------------------------------------------");

        System.out.println("Could you please insert the values of the car's color (r,g,b) [255,0,0]: ");
        String rgb = scan.nextLine();

        System.out.println("Could you please insert the name of the color: ");
        String colorName = scan.nextLine();
        Color color = new Color(rgb, colorName);

        System.out.println("Could you please tell us the car's model: ");
        String model = scan.nextLine();

        System.out.println("Could you please inform your plate: ");
        String plate = scan.nextLine();

        System.out.println("Could you please tell us the engine number: ");
        String engine = scan.nextLine();

        System.out.println("Would you please inform the price per day of this vehicle: ");
        double pricePerDay = scan.nextDouble();

        System.out.flush();

        return new Vehicle(color, model, plate, engine, pricePerDay);
    }

    public void rentCar(Client renter, Vehicle car){

        System.out.println("------------------------------------------------------------------");
        System.out.println("-------------------------- Renting a car! ------------------------");
        System.out.println("------------------------------------------------------------------");

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
    }

    public void unrentCar(Vehicle car){

        System.out.println("------------------------------------------------------------------");
        System.out.println("------------------------- Unrenting a car! -----------------------");
        System.out.println("------------------------------------------------------------------");

        if (car.isRented()){
            car.getRenter().setRenting(false);
            car.setRenter(null);
            car.setRentedDays(0);
            car.setRented(false);
            System.out.println("The car (" + car.getModel() + ") was unrented!");
        } else {
            System.out.println("The car(" + car.getModel() + ") has not even been rented yet!");
        }
    }
}
