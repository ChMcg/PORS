/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №1: Передача сообщений TCP через сокет
 * Дата выполнения: 29.09.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Клиент подключается к серверу 
 * и передаёт 10 сообщений с текстом 
 * "Hello". Затем отправляет сообщение
 * о завершении передачи (текст "END")
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_Client {
	public static String SERVER_HOST = "localhost";
	public static int SERVER_PORT = 8080;
	private static Socket socket;

	public static void main(String[] args) throws IOException {
		try {
			socket = new Socket(SERVER_HOST, SERVER_PORT);

			PrintWriter out = getWriter();
			BufferedReader in = getReader();

			for (int i = 0; i < 10; i++) {
				out.println(new Message(i, "Hello").toString());
				System.out.println(in.readLine());
			}

			out.println(new Message(10, "END").toString());

		} catch (UnknownHostException e) {
			System.out.println(e.getStackTrace());
		} finally {
			if (socket != null)
				socket.close();
		}
	}

	private static PrintWriter getWriter() throws IOException {
		return new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}

	private static BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
}
