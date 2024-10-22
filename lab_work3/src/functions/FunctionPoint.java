package functions;

public class FunctionPoint {
    private double x, y;

    // конструктор с параметрами
    public FunctionPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    // конструктор копирования
    public FunctionPoint(FunctionPoint point) {
        x = point.x;
        y = point.y;
    }

    // конструктор по умолчанию
    public FunctionPoint() {
        x = y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}