import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int clientCount = 0; // добавляем счетчик
        String town=null;
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    clientCount++; // увеличиваем счетчик каждый раз, когда происходит подключение нового клиента
                    System.out.println("Новое соединение подключено");
                    // Проверяем, является ли это первым клиентом
                    if (clientCount == 1) {
                        out.println("Вы первый клиент. Введите первое слово?!");
                        town=in.readLine();
                        System.out.println("первый город: "+town);
                    } else {
                        char lastChar = (town.length() > 0) ? town.charAt(town.length() - 1) : '\0';
                        out.println("Приветствую, введите город, до этого был "+town+" вам на последнюю букву, т.е. на "+lastChar);
                        town=in.readLine();
                        System.out.println("следующий город "+ town);
                    }
                }
            }
        }
    }
}