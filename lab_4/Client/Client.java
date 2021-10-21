/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №4: Удалённый вызов метода
 * Дата выполнения: 21.10.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Клиент делает разбор строки пользователя, 
 * затем обращается к заглушке объекта 
 * Calculator и вызывает нужные методы.
 * С помощью RMI вызывается метод и 
 * возвращается результат вычислений. 
 * Результат показывается пользователю.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

enum Action {
    Addition,
    Subtraction,
    Multiplication,
    Division
};

public class Client {
    public static final String OBJECT_NAME = "calculatorImpl";
    public static final String HOST = "localhost";
    public static final int PORT = 8081;
    public static final String URL = "rmi://" + HOST + ":" + PORT + "/" + OBJECT_NAME;
    // public static final String URL = "rmi://localhost:8081/calculatorImpl";

    private static CalculatorInterface calculator;

    public static void main(String[] args) throws IOException {

        try {
            calculator = (CalculatorInterface) Naming.lookup(URL);
        }
        catch (RemoteException|NotBoundException|MalformedURLException e) {
            System.out.println("Не удалось подключиться к серверу: " + URL);
            System.out.println("Проверьте, что сервер работает");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Подключение к серверу '" + HOST + ":" + PORT + "' установлено");
        System.out.println("Ожидание выражения для вычилсения.");
        System.out.println("Для выхода введите 'exit'");

        processRepl();

        System.out.println("\nРабота завершена");
    }

    private static void processRepl() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(">>> ");
            String input = reader.readLine();

            if (input == null) break;
            if (input.equals("exit")) break;

            try {
                String result = evaluateStringWithAction(input);
                System.out.println("Результат: " + result);
            }
            catch (ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            catch (ParseException e) {
                System.out.println("Не удалось распознать выражение:\n" + e.getMessage());
            }
            catch (RemoteException e) {
                System.out.println("Ошибка сети: " + e.getMessage());
            }
        }
    }

    private static String evaluateStringWithAction(String data) 
            throws ParseException, RemoteException, ArithmeticException {
        data.replace(" ", "");
        if (data.contains("+")) {
            return processSplittedStringWithAction(data.split("[+]{1}"), Action.Addition);
        }
        else if (data.contains("-")) {
            return processSplittedStringWithAction(data.split("[-]{1}"), Action.Subtraction);
        }
        else if (data.contains("*")) {
            return processSplittedStringWithAction(data.split("[*]{1}"), Action.Multiplication);
        }
        else if (data.contains("/")) {
            return processSplittedStringWithAction(data.split("[/]{1}"), Action.Division);
        }
        else {
            throw new ParseException("Выражение должно содержать действие", 0);
        }
    }

    private static String processSplittedStringWithAction(String[] data, Action action) 
            throws ParseException, RemoteException, ArithmeticException {

        if (data.length != 2) {
            throw new ParseException(
                "Выражение должно содержать ровно два элемента", 0);
        }

        Integer a, b;

        try {
            a = Integer.parseInt(data[0].strip());
        }
        catch (NumberFormatException e) {
            throw new ParseException("Ошибка разбора элемента: '" + data[0] + "'", 25);
        }

        try {
            b = Integer.parseInt(data[1].strip());
        }
        catch (NumberFormatException e) {
            throw new ParseException("Ошибка разбора элемента: '" + data[1] + "'", 25);
        }

        if (action == Action.Division && b == 0) {
            throw new ArithmeticException("деление на ноль");
        }

        switch (action) {
            case Addition:       return String.valueOf(calculator.add(a, b));
            case Subtraction:    return String.valueOf(calculator.subtract(a, b));
            case Multiplication: return String.valueOf(calculator.multiply(a, b));
            case Division:       return String.valueOf(calculator.divide(a, b));
            default: break;
        }

        return "";
        
    }
}
