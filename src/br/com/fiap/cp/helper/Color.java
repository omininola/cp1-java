package br.com.fiap.cp.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    private int r;
    private int g;
    private int b;
    private String name;

    public Color(String name) {
        this.name = name;
    }

    public boolean checkColorPattern(String rbgValues){
        Pattern pattern = Pattern.compile("^\\d{1,3},\\d{1,3},\\d{1,3}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(rbgValues);
        return matcher.find();
    }

    public void setRgb(String rgbValues){
        String[] splitedInfos = rgbValues.split(",");
        this.r = Integer.parseInt(splitedInfos[0]);
        this.g = Integer.parseInt(splitedInfos[1]);
        this.b = Integer.parseInt(splitedInfos[2]);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
