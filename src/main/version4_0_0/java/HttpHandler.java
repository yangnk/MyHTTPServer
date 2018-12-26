package main.version4_0_0.java;

import java.io.IOException;
import java.net.Socket;

public class HttpHandler implements Runnable {
    Socket socket;
    public HttpHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            MyHttpRequest req = new MyHttpRequest(socket.getInputStream());
            MyHttpResponse res = new MyHttpResponse(req);
            res.write(socket.getOutputStream());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
