import java.io.*;

public class Rectangle implements Serializable {
    private double a;
    private double b;
    private double area = -1;
    public static final long serialVersionUID = 2L;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double calculateArea() {
        area = a * b;
        return area;
    }

    public double getArea() {
        return area;
    }
}