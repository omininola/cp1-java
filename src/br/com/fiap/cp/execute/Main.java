package br.com.fiap.cp.execute;

import br.com.fiap.cp.model.Client;
import br.com.fiap.cp.model.RentalCompany;
import br.com.fiap.cp.model.Vehicle;

public class Main {

    public static void main(String[] args) {

        RentalCompany rentalCompany = new RentalCompany();
        Client client1 = rentalCompany.registerClient();
        Vehicle car1 = rentalCompany.registerVehicle();

        rentalCompany.rentCar(client1, car1);
        rentalCompany.rentCar(client1, car1);

        rentalCompany.unrentCar(car1);

        rentalCompany.rentCar(client1, car1);
    }
}