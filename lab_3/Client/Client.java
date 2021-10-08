/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №3: Сериализация
 * Дата выполнения: 08.10.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Клиент подключается к серверу, передаёт ему 
 * сериализованный объект класса Rectangle, и ожидает 
 * обратно новый объект, в котором будет уже 
 * рассчитанное значение площади. Десериализует его,
 * и выводит полученно значение площади в консоль 
 */

import java.io.*;
import java.net.*;

public class Client {
    public static String SERVER_HOST = "localhost";
    public static int SERVER_PORT = 8080;
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);

            Rectangle localObject = new Rectangle(10, 20);

            ObjectOutputStream objectOutputStream = getObjectOutputStream();
            ObjectInputStream objectInputStream = getObjectInputStream();

            objectOutputStream.writeObject(localObject);

            System.out.println("Отправили объект, ожидаем когда сервер посчитает площадь");

            Rectangle returnedObject = (Rectangle) objectInputStream.readObject();

            System.out.println("Сервер вернул объект.");
            System.out.println("Площадь: " + returnedObject.getArea());

        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс" + e.getClass());
            System.out.println(e.getStackTrace());
        } catch (UnknownHostException e) {
            System.out.println("Не удалось определить IP адрес");
            System.out.println(e.getStackTrace());
        } finally {
            System.out.println("Очищаем ресурсы");
            if (socket != null) socket.close();
        }
    }

    private static ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(socket.getOutputStream());
    }

    private static ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(socket.getInputStream());
    }

}
