import functions.*;

public class Main {
    public static void main(String[] args) {
        // Объявляем ссылочную переменную типа TabulatedFunction
        TabulatedFunction function = null;

        // Проверка работы ArrayTabulatedFunction
        try {
            function = new ArrayTabulatedFunction(1, 5, 3);
            System.out.println("ArrayTabulatedFunction создан успешно.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании ArrayTabulatedFunction: " + e.getMessage());
        }

        // Проверка выброса исключений в ArrayTabulatedFunction
        try {
            function.setPoint(5, new FunctionPoint(3, 4)); // Должно выбросить FunctionPointIndexOutOfBoundsException
        } catch (FunctionPointIndexOutOfBoundsException | InappropriateFunctionPointException e) {
            System.out.println("Ошибка в setPoint: " + e.getMessage());
        }

        try {
            function.setPoint(0, new FunctionPoint(0, 4)); // Должно выбросить InappropriateFunctionPointException
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Ошибка в setPoint: " + e.getMessage());
        }

        try {
            function.deletePoint(1); // Должно пройти успешно
            System.out.println("Точка удалена успешно.");
        } catch (IllegalStateException e) {
            System.out.println("Ошибка в deletePoint: " + e.getMessage());
        }

        // Проверка работы LinkedListTabulatedFunction
        try {
            function = new LinkedListTabulatedFunction(1, 5, 3);
            System.out.println("LinkedListTabulatedFunction создан успешно.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании LinkedListTabulatedFunction: " + e.getMessage());
        }

        // Проверка выброса исключений в LinkedListTabulatedFunction
        try {
            function.addPoint(new FunctionPoint(2, 3)); // Добавление точки
            function.setPoint(0, new FunctionPoint(2, 4)); // Должно пройти успешно
            System.out.println("Точка установлена успешно.");
        } catch (InappropriateFunctionPointException e) {
            System.out.println("Ошибка в setPoint: " + e.getMessage());
        }

        try {
            function.deletePoint(0); // Должно пройти успешно
            System.out.println("Точка удалена успешно.");
        } catch (IllegalStateException e) {
            System.out.println("Ошибка в deletePoint: " + e.getMessage());
        }
    }
}
