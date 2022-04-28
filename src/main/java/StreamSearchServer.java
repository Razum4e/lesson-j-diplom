import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//сервер поисковой системы запускается поток поисковой системы
//методом "run()", который в главном классе запускается методом "start()"
public class StreamSearchServer extends Thread {
    private final int port;
    private static BooleanSearchEngine engine;

    public StreamSearchServer(int port, String pdfsDir) throws IOException {
        this.port = port;
        engine = new BooleanSearchEngine(pdfsDir);
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var word = in.readLine();
                var wordToJson = searchAndOutToJson(word);

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(wordToJson);

                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String searchAndOutToJson (String word) {
        var pageEntryList = engine.search(word);
        var gson = new GsonBuilder().create();
        return gson.toJson(pageEntryList);
    }
}