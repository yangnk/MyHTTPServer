package main.version2_0_0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    public static void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new MyHander(socket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
