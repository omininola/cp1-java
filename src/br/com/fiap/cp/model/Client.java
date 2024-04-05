package br.com.fiap.cp.model;
import br.com.fiap.cp.helper.Cnh;

public class Client {

    private String name;
    private Cnh cnh;
    private String cpf;
    private Vehicle rentedCar = null;
    private boolean isRenting = false;

    public Client(String name, Cnh cnh, String cpf) {
        this.name = name;
        this.cnh = cnh;
        this.cpf = cpf;
    }

    public String getData(){
        return "\nName: " + this.name +
                "\nCPF: " + this.cpf +
                "\nCNH: " + this.cnh.getRegistrationNum();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cnh getCnh() {
        return cnh;
    }

    public void setCnh(Cnh cnh) {
        this.cnh = cnh;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Vehicle getRentedCar(){
        return this.rentedCar;
    }

    public void setRentedCar(Vehicle rentedCar){
        this.rentedCar = rentedCar;
    }

    public boolean isRenting() {
        return isRenting;
    }

    protected void setRenting(boolean renting) {
        isRenting = renting;
    }
}
