/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №3: Сериализация
 * Дата выполнения: 08.10.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Сервер ожидает подключений на порту 8080.
 * При подключении, десериализует объект Rectangle
 * и рассчитывает площадь. После этого результат 
 * вычислений сохраняется в объекте Rectangle
 * (это была самая очевидная реализация обратной передачи)
 * и передаёт его обратно клиенту
 */

import java.io.*;
import java.net.*;

public class Server {
    public static int PORT = 8080;
    private static ServerSocket server;
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Сервер запущен: " + server);

            socket = server.accept();
            System.out.println("Соединение подтверждено: " + socket.toString());

            ObjectInputStream objectInputStream = getObjectInputStream();
            ObjectOutputStream objectOutputStream = getObjectOutputStream();

            Rectangle rectangle = (Rectangle) objectInputStream.readObject();

            double area = rectangle.calculateArea();
            System.out.println("Вычислили площадь: " + area + ". " + "Отправляем обратно");

            objectOutputStream.writeObject(rectangle);

        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс" + e.getClass());
            System.out.println(e.getStackTrace());
        } catch (UnknownHostException e) {
            System.out.println("Не удалось определить IP адрес");
            System.out.println(e.getStackTrace());
        } finally {
            System.out.println("Очищаем ресурсы");
            if (socket != null) socket.close();
            if (server != null) server.close();
        }
    }

    private static ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(socket.getOutputStream());
    }

    private static ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(socket.getInputStream());
    }

}
