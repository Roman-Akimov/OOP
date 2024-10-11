package other_main;

import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
    	
    	// создаем табулированную функцию с границами от -5 до 5 и заданными значениями
        TabulatedFunction func = new TabulatedFunction(-5, 5, new double[]{25, 16, 9, 4, 1, 0, 1, 4, 9, 16, 25});
        
    	// проверяем границы области определения
    	System.out.println("Левая граница: " + func.getLeftDomainBorder());
    	System.out.println("Правая граница: " + func.getRightDomainBorder());
        
        func.deletePoint(0);
        System.out.println("Ставим новое значение для левой границы");
    	// добавляем новую точку
    	func.addPoint(new FunctionPoint(-6, 13));
        
    	// проверяем границы области определения
    	System.out.println("Левая граница: " + func.getLeftDomainBorder());
    	System.out.println("Правая граница: " + func.getRightDomainBorder());
        
        // выводим все оставшиеся точки функции
        for(int i = 0; i < func.getPointsCount(); i++)
        {
        	// получаем и выводим значение x текущей точки
            System.out.print(func.getPointX(i));
            System.out.print(' ');
            // получаем и выводим значение y текущей точки
            System.out.print(func.getPointY(i));
            
            System.out.println();
        }
        
    	System.out.println("Вычисляем и выводим значение функции для x = 1.5");
        System.out.println(func.getFunctionValue(1.5));
    	System.out.println("Вычисляем и выводим значение функции для x = 6");
        System.out.println(func.getFunctionValue(6));
    }
}
