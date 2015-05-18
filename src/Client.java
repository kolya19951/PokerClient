import java.net.*;
import java.io.*;

public class Client {
    int serverPort = 1488;
    String address = "127.0.0.1";
    InputStream sin;
    OutputStream sout;
    DataInputStream in;
    DataOutputStream out;


    Client() {
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}