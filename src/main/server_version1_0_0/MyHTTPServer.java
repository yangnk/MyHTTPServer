package main.server_version1_0_0;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHTTPServer {
    public static void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // 接收GET 方法
            String s = null;
            while ((s=bufferedReader.readLine())!=null&&!s.isEmpty()) {
                if (s.startsWith("GET")) {
                    System.out.println("this is GET method.");
                }else if (s.startsWith("POST")) {
                    System.out.println("this is POST method.");
                }
            }
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-type:text/html");
            printWriter.println();
            printWriter.println("<h1>myhhtpserver</h1>");
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        start();
    }
}
