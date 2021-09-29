/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №2: Передача звукового файла через UDP сокет
 * Дата выполнения: 29.09.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Клиент отправляет на сервер по протоколу UDP
 * файл с названием "file.mp3". Чтобы это сделать, 
 * открывается поток чтения из файла и по 1024 байта
 * отправляется на сервер
 */

import java.io.*;
import java.net.*;

public class UDP_Client {
    public static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress addr = InetAddress.getByName("localhost");
        byte[] data = new byte[1024];
        FileInputStream fr = new FileInputStream(new File("file.mp3"));
        DatagramPacket pac;
        while (fr.read(data) != -1) {
            pac = new DatagramPacket(data, data.length, addr, PORT);
            clientSocket.send(pac);
        }
        System.out.println("Выход");
        clientSocket.close();
    }
}