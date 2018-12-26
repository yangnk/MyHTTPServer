package main.version4_0_0.java;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer{
    final static int PORT = 8080;
    final static int FIXED_THREAD_NUM = 10;

    public void start() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("web server is running...");
        ExecutorService exec = Executors.newFixedThreadPool(FIXED_THREAD_NUM);
        while (true){
            try {
                exec.submit(new HttpHandler(ss.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new WebServer().start();
    }
}
