import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        var server = new StreamSearchServer(9999, "pdfs"); // запуск поискового сервера
        server.start();                                          // запуск параллельно работающего серверного потока

//        var socket = new Socket("localhost", 9999);     //открываем локальный локет
//
//        var out = new PrintWriter(socket.getOutputStream(), true); // поток отправки в сокет
//        out.println("бизнес");                                              // отправляем строку в поток out
//
//        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// поток приема из сокета
//        System.out.println(in.readLine());                        //печать того, что приняли с потока
//
//        out.close(); //закрываем поток выхода
//        in.close(); // закрываем поток ввода
//
//        server.interrupt(); //закрываем поток сервера

    }
}
