/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №4: Удалённый вызов метода
 * Дата выполнения: 21.10.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Сервер создаёт объект CalculatorImpl и 
 * регистрирует его в журнале RMI. Затем 
 * сервер ожидает подключения.
 */

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server { 
    public static final String OBJECT_NAME = "figureFactory";
    public static final int PORT = 8081;

    public static void main(String[] args) 
            throws RemoteException, AlreadyBoundException, InterruptedException {
        final Registry registry = LocateRegistry.createRegistry(PORT);
        FigureFactoryInterface service = (FigureFactoryInterface) new FigureFactoryImpl(registry);
        registry.bind(OBJECT_NAME, service);
        System.out.println("Сервер запущен на порту " + PORT);
    }
}
