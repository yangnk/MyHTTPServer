package main.java.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 下午7:03
 **/
public class ServerHandler implements Runnable {
    Socket socket = new Socket();
    InputStream inputStream = null;

    public ServerHandler(Socket accept) {
        this.socket = accept;
    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            //创建request，并解析
            Request request = new Request(inputStream);
            request.parse();
            //创建response，并返回
            Response response = new Response(outputStream);
            response.setRequest(request);
            response.sent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
