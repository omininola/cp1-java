package br.com.fiap.cp.helper;

public class Color {

    private int r;
    private int g;
    private int b;
    private String name;

    public Color(String rgb, String name) {
        String[] splitedInfos = rgb.split(",");
        this.r = Integer.parseInt(splitedInfos[0]);
        this.g = Integer.parseInt(splitedInfos[1]);
        this.b = Integer.parseInt(splitedInfos[2]);
        this.name = name;
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
