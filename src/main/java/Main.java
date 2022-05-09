import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        var server = new StreamSearchServer(9999, "pdfs");
        server.start();//для завершения работы сервера введите "end" в консоль

        //только запуск/закрытие сервера
        while (true) {
            System.out.print("Ввод:");
            if (scanner.nextLine().equals("end")){
                server.interrupt();
                break;
            }
        }

        //запуск/закрытие сервера и работа с ним
//        while (true) {
//            System.out.print("Введите слово для поиска:");
//            var line = scanner.nextLine();
//            if (line.equals("end")) {
//                server.interrupt();
//                break;
//            }
//            var socket = new Socket("localhost", 9999);
//            var out = new PrintWriter(socket.getOutputStream(), true);
//            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out.println(line);
//            System.out.println(in.readLine());
//            socket.close();
//            out.close();
//            in.close();
//        }
    }
}
