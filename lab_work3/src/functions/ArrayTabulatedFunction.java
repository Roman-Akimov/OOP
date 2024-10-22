package functions;

public class ArrayTabulatedFunction implements TabulatedFunction{

    private FunctionPoint[] points;
    private int count;

    // конструктор с границами и количеством точек
    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (pointsCount < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Invalid bounds or point count.");
        }
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }
        count = pointsCount; // устанавливаем кол-во точек
    }

    // конструктор с границами и массивом значений
    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) {
        if (values.length < 2 || leftX >= rightX) {
            throw new IllegalArgumentException("Invalid bounds or point count.");
        }
        count = values.length;
        points = new FunctionPoint[count];
        double step = (rightX - leftX) / (count - 1);
        for (int i = 0; i < count; i++) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }

    // получение левой границы области определения
    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    // получение правой границы области определения
    public double getRightDomainBorder() {
        return points[count - 1].getX();
    }

    // получение значения функции в заданной точке x
    public double getFunctionValue(double x) {
        // проверка, находится ли x в пределах области определения
        if (Double.compare(x, getLeftDomainBorder()) < 0 || Double.compare(x, getRightDomainBorder()) > 0) {
            return Double.NaN;
        }
        // поиск интервала, в котором находится x
        for (int i = 0; i < count - 1; i++) {
            if (Double.compare(x, points[i].getX()) >= 0 && Double.compare(x, points[i + 1].getX()) <= 0) {
                double x1 = points[i].getX();
                double y1 = points[i].getY();
                double x2 = points[i + 1].getX();
                double y2 = points[i + 1].getY();

                // формула линейной интерполяции
                return y1 + (y2 - y1) * (x - x1) / (x2 - x1);
            }
        }

        return Double.NaN; // Для безопасности
    }

    // получение количества точек
    public int getPointsCount() {
        return count;
    }

    // получение точки по индексу
    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= count) {
            throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        return points[index];
    }

    // установка новой точки по индексу
    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException{
        if (index < 0 || index >= count) {
            throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        if (index > 0 && index < count) {
            if (Double.compare(point.getX(), points[index - 1].getX()) > 0 &&
                    Double.compare(point.getX(), points[index + 1].getX()) < 0) {
                points[index] = point;
            }
        }
    }

    // получение координаты x по индексу
    public double getPointX(int index) {
        if (index < 0 || index >= count) {
            throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        return points[index].getX();
    }

    // установка нового значения x для точки по индексу
    public void setPointX(int index, double x) throws InappropriateFunctionPointException{
        if (index < 0 || index >= count) {
                throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        if (index > 0 && index < count) {
            // проверка, что новое значение x находится в пределах соседних точек
            if (Double.compare(x, points[index - 1].getX()) > 0 &&
                    Double.compare(x, points[index + 1].getX()) < 0) {
                points[index].setX(x); // установка нового x
            }
        }
    }

    // получение координаты y точки по индексуЫА
    public double getPointY(int index) {
        if (index < 0 || index >= count) {
            throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        return points[index].getY();
    }

    // установка нового значения y для точки по индексу
    public void setPointY(int index, double y) {
        if (index < 0 || index >= count) {
            throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        points[index].setY(y);
    }

    // Удаление точки по индексу
    public void deletePoint(int index) {
        if (count < 3) {
            throw new IllegalStateException("Cannot delete point; fewer than three points.");
        }
        if (index < 0 || index >= count) {
            throw new FunctionPointIndexOutOfBoundsException("Index out of bounds.");
        }
        FunctionPoint[] newPoints = new FunctionPoint[count - 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index + 1, newPoints, index, count - index - 1);
        points = newPoints;
        count--;
    }

    // Добавление новой точки
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        for (int i = 0; i < count; i++) {
            if (Double.compare(points[i].getX(), point.getX()) == 0) {
                throw new InappropriateFunctionPointException("Duplicate x value.");
            }
        }
        FunctionPoint[] newPoints = new FunctionPoint[count + 1];
        int i = 0;
        while (i < count && Double.compare(points[i].getX(), point.getX()) < 0) {
            newPoints[i] = points[i];
            i++;
        }
        newPoints[i] = point;
        System.arraycopy(points, i, newPoints, i + 1, count - i);
        points = newPoints;
        count++;
    }
}