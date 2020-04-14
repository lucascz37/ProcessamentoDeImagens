package br.com.edu.main;

public class PixelYIQ {
    private double y;
    private double i;
    private double q;

    public PixelYIQ(double y, double i, double q){
            this.y = y;
            this.i = i;
            this.q = q;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getQ() {
        return q;
    }

    public void setQ(double q) {
        this.q = q;
    }
}
