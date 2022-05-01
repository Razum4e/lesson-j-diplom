import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

//сервер поисковой системы запускается поток поисковой системы
//методом "run()", который в главном классе запускается методом "start()"

public class StreamSearchServer extends Thread {
    private static BooleanSearchEngine engine;
    private final int port;

    public StreamSearchServer(int port, String pdfsDir) throws IOException {
        this.port = port;
        engine = new BooleanSearchEngine(pdfsDir);
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String word;
                try {
                    word = in.readLine();
                } catch (SocketException socketException) {
                    continue;
                }
                var wordToJson = searchAndOutToJson(word);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(wordToJson);
                socket.close();
                out.close();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String searchAndOutToJson(String word) {
        var pageEntryList = engine.search(word);
        var gson = new GsonBuilder().create();
        if (pageEntryList.isEmpty()) {
            return "Слово не найдено.";
        }
        return gson.toJson(pageEntryList);
    }
}