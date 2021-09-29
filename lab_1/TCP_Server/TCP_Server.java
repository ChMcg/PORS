/*
 * Выполнил: Бахир Андрей, группа 7361
 * Задание №1: Передача сообщений TCP через сокет
 * Дата выполнения: 29.09.2021 
 * Версия: 0.1
 */

// ------------------------------------------- // 

/*
 * Сервер ожидает подключений на порту 8080.
 * При подключении, начинает циклично получать
 * сообщения от подключившегося клиента и подтверждать
 * получение обратным сообщением в формате 
 * `# [номер пакета] получен` до тех пор, пока 
 * содержимое пакета не будет соответствовать "END"
 */

import java.io.*;
import java.net.*;

public class TCP_Server {
	public static int PORT = 8080;
	private static ServerSocket server;
	private static Socket socket;

	public static void main(String[] args) throws IOException {
		try {
			server = new ServerSocket(PORT);
			System.out.println("Started: " + server);

			socket = server.accept();
			System.out.println("Соединение подтверждено: " + socket.toString());

			PrintWriter out = getWriter();
			BufferedReader in = getReader();

			while (true) {
				try {
					var tmp = in.readLine();
					Message message = Message.fromString(tmp);
					out.println("#" + message.header + " received");
					if (message.data.equals("END"))
						break;
					System.out.println("Получено сообщение: " + message);
				} catch (MessageParseException e) {
					System.out.println("Ошибка разбора пакета");
					System.out.println(e.getStackTrace());
				}
			}
		} catch (UnknownHostException e) {
			System.out.println(e.getStackTrace());
		} finally {
			System.out.println("Очищаем ресурсы ....");
			if (socket != null) socket.close();
			if (server != null) server.close();
		}
	}

	private static PrintWriter getWriter() throws IOException {
		return new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}

	private static BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
}
