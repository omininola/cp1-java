package br.com.fiap.cp.helper;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cnh {

    private LocalDate emissionDate;
    private LocalDate expiryDate;
    private String registrationNum;

    public Cnh(String registrationNum) {
        this.emissionDate = emissionDate;
        this.expiryDate = expiryDate;
        this.registrationNum = registrationNum;
    }

    public boolean validateCnh(){
        LocalDate today = LocalDate.now();
        return this.expiryDate.isAfter(today);
    }

    private LocalDate parseDate(String dateUnparsed){
        String[] dateSplited = dateUnparsed.split("-");
        int year = Integer.parseInt(dateSplited[2]);
        int month = Integer.parseInt(dateSplited[1]);
        int day = Integer.parseInt(dateSplited[0]);

        return LocalDate.of(year, month, day);
    }

    public boolean checkDatePattern(String date){
        Pattern pattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }

    public LocalDate getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(String emissionDate) {
        this.emissionDate = parseDate(emissionDate);
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = parseDate(expiryDate);
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }
}
