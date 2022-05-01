import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        var server = new StreamSearchServer(9999, "pdfs");
        server.start();

//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            var socket = new Socket("localhost", 9999);
//            var out = new PrintWriter(socket.getOutputStream(), true);
//            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.print("Введите слово для поиска:");
//            out.println(scanner.nextLine());
//            System.out.println(in.readLine());
//            socket.close();
//            out.close();
//            in.close();
//        }
    }
}
