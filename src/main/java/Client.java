import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean st = true;
        try (Socket socket = new Socket("127.0.0.1", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (st){
                String town = in.readLine();
                System.out.println(town);
                String sTown = scanner.nextLine();
                if (town.charAt(town.length() - 1) == '!') {
                    out.println(sTown);
                    st = false;
                } else if (sTown.charAt(0) == town.charAt(town.length() - 1)) {
                    out.println(sTown);
                    st = false;
                } else {
                    System.out.println("Неверный город, попробуйте еще раз.");
                    String[] words = town.split("\\s+"); // Разделение строки на слова по пробелам
                    String townWord = words[6];
                    out.println(townWord);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
