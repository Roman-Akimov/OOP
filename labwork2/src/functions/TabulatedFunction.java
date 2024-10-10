package functions;

public class TabulatedFunction {
	
	private FunctionPoint[] points;
    private int count;

    // Конструктор с границами и количеством точек
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }
        count = pointsCount;
    }

    // Конструктор с границами и массивом значений
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        count = values.length;
        points = new FunctionPoint[count];
        double step = (rightX - leftX) / (count - 1);
        for (int i = 0; i < count; i++) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }

    public double getLeftDomainBorder() {
        return points[0].getX();
    }

    public double getRightDomainBorder() {
        return points[count - 1].getX();
    }

    public double getFunctionValue(double x) {
        if (Double.compare(x, getLeftDomainBorder()) < 0 || Double.compare(x, getRightDomainBorder()) > 0) {
            return Double.NaN;
        }
        
        for (int i = 0; i < count - 1; i++) {
            if (Double.compare(x, points[i].getX()) >= 0 && Double.compare(x, points[i + 1].getX()) <= 0) {
                double x0 = points[i].getX();
                double y0 = points[i].getY();
                double x1 = points[i + 1].getX();
                double y1 = points[i + 1].getY();
                
                // Линейная интерполяция
                return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
            }
        }
        
        return Double.NaN; // Для безопасности
    }

    public int getPointsCount() {
        return count;
    }

    public FunctionPoint getPoint(int index) {
        return points[index];
    }

    public void setPoint(int index, FunctionPoint point) {
        if (index > 0 && index < count) {
            if (Double.compare(point.getX(), points[index - 1].getX()) > 0 && 
                Double.compare(point.getX(), points[index + 1].getX()) < 0) {
                points[index] = point;
            }
        }
    }

    public double getPointX(int index) {
        return points[index].getX();
    }

    public void setPointX(int index, double x) {
        if (index > 0 && index < count) {
            if (Double.compare(x, points[index - 1].getX()) > 0 && 
                Double.compare(x, points[index + 1].getX()) < 0) {
                points[index].setX(x);
            }
        }
    }

    public double getPointY(int index) {
        return points[index].getY();
    }

    public void setPointY(int index, double y) {
        points[index].setY(y);
    }

    public void deletePoint(int index) {
        if (index < 0 || index >= count) return;

        FunctionPoint[] newPoints = new FunctionPoint[count - 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index + 1, newPoints, index, count - index - 1);
        points = newPoints;
        count--;
    }
    
    public void addPoint(FunctionPoint point) {
    	FunctionPoint[] newPoints = new FunctionPoint[count + 1];
    	int i = 0;
    	while (i < count && Double.compare(points[i].getX(), point.getX()) < 0) {
    		newPoints[i] = points[i];
    		i++;
    	}
    	newPoints[i] = point;
    	System.arraycopy(points, i, newPoints, i + 1, count -  i);
    	points = newPoints;
    	count++;
    }
}

