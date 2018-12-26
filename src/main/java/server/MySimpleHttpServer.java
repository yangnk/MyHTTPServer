package main.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 上午10:24
 **/
public class MySimpleHttpServer {
    static final int THREADPOOL_SIZE = 10;

    public static void main(String[] args) throws IOException {
        //创建serversocket
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(Constant.PORT));
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(THREADPOOL_SIZE);
        //serversocket一直在监听
        System.out.println("server is running...");
        while (true) {
            try {
                executorService.submit(new ServerHandler(serverSocket.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
