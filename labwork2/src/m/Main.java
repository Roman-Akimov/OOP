package m;

import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
    	TabulatedFunction func = new TabulatedFunction(-5, 5, new double[]{25, 16, 9, 4, 1, 0, 1, 4, 9, 16, 25});

    	// Удаляем первую точку
    	func.deletePoint(0);

    	// Изменяем значение точки
    	func.setPoint(2, new FunctionPoint(2, 10));

    	// Изменяем координаты точки
    	func.setPointX(1, -3);
    	func.setPointY(3, 5);

    	// Проверяем границы области определения
    	System.out.println("Левая граница: " + func.getLeftDomainBorder());
    	System.out.println("Правая граница: " + func.getRightDomainBorder());

    	// Выводим все оставшиеся точки
    	for (int i = 0; i < func.getPointsCount(); i++) {
    	    System.out.print(func.getPointX(i));
    	    System.out.print(' ');
    	    System.out.print(func.getPointY(i));
    	    System.out.println();
    	}

    	// Проверяем значение функции в точке
    	System.out.println(func.getFunctionValue(1.5));

    	// Добавляем новую точку
    	func.addPoint(new FunctionPoint(-3, 8));

    	// Выводим все точки после добавления
    	System.out.println("\nПосле добавления новой точки:");
    	for (int i = 0; i < func.getPointsCount(); i++) {
    	    System.out.print(func.getPointX(i));
    	    System.out.print(' ');
    	    System.out.print(func.getPointY(i));
    	    System.out.println();
    	}
    }
}
