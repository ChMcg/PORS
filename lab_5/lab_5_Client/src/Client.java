/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №5: Удалённый вызов метода
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Client {
    public static final String OBJECT_NAME = "figureFactory";
    public static final String HOST = "localhost";
    public static final int PORT = 8081;
    public static final String URL = "rmi://" + HOST + ":" + PORT + "/" + OBJECT_NAME;

    private static FigureFactoryInterface figureFactoryInterface;

    public static void main(String[] args) throws IOException {

        try {
            figureFactoryInterface = (FigureFactoryInterface) Naming.lookup(URL);
        }
        catch (RemoteException|NotBoundException|MalformedURLException e) {
            System.out.println("Не удалось подключиться к серверу: " + URL);
            System.out.println("Проверьте, что сервер работает");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Подключение к серверу '" + HOST + ":" + PORT + "' установлено");

        processCircle();
        processTriangle();
        processRectangle();
        
        System.out.println("Работа завершена");
        System.exit(0);
    }
            
    private static void processCircle() throws RemoteException {

        CircleInterface circle = (CircleInterface) figureFactoryInterface.getObject(
                FigureFactoryInterface.FigureType.Circle, "circle");

        int radius = 20;
        circle.setRadius(radius);
        circle.setName("окружность");

        System.out.println("Площадь объекта " + circle.getName() 
                            + " с радиусом " + String.valueOf(radius) 
                            + " = " + String.valueOf(circle.calculateArea()));
    }

    private static void processTriangle() throws RemoteException {

        TriangleInterface triangle = (TriangleInterface) figureFactoryInterface.getObject(
                FigureFactoryInterface.FigureType.Triangle, "triangle");

        triangle.setSides(3, 4, 5);
        triangle.setName("треугольник");

        System.out.println("Площадь объекта " + triangle.getName() 
                            + " = " + String.valueOf(triangle.calculateArea()));
    }

    private static void processRectangle() throws RemoteException {

        RectangleInterface rectangle = (RectangleInterface) figureFactoryInterface.getObject(
            FigureFactoryInterface.FigureType.Rectangle, "rectangle");

        rectangle.setSides(20, 30);
        rectangle.setName("прямоугольник");

        System.out.println("Площадь объекта " + rectangle.getName() 
                            + " = " + String.valueOf(rectangle.calculateArea()));
    }

}
