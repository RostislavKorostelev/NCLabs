package buildings.net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BinaryServer {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Parallel server starts");

        ServerSocket serverSocket = new ServerSocket(1993);

        while(!serverSocket.isClosed()) {
            Socket client = serverSocket.accept();
            executorService.execute(new NewConnection(client));
            System.out.println("Client connected");
        }
        executorService.shutdown();
    }

}
