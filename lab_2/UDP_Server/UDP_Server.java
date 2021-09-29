/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №2: Передача звукового файла через UDP сокет
 * Дата выполнения: 29.09.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Сервер ожидает датаграммы на порту 8080 в 
 * течении десяти секунд. Полученные датаграммы
 * отправляются на запись в файл с названием "file.mp3"
 * Подразумевается что после того, как файл получен 
 * полностью, сокет больше не получает датаграмм и 
 * завершает работу программы по таймеру.
 */

import java.io.*;
import java.net.*;

public class UDP_Server {
    public final static int PORT = 8080;
    private final static String file = "file.mp3";
    public final static int pacSize = 1024;

    public static void main(String[] args) throws IOException {
        try {
            byte data[] = new byte[pacSize];
            DatagramPacket pac = new DatagramPacket(data, data.length);
            DatagramSocket s = new DatagramSocket(PORT);
            FileOutputStream os = new FileOutputStream(file);
            try {
                s.setSoTimeout(10000);
                while (true) {
                    s.receive(pac);
                    os.write(data);
                    os.flush();
                }
            } catch (SocketTimeoutException e) {
                System.out.println("Прием данных закончен");
            } finally {
                s.close();
                os.close();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Выход");
        }
    }
}