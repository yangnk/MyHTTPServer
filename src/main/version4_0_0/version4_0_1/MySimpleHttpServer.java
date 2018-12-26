package main.version4_0_0.version4_0_1;

import com.sun.javafx.binding.IntegerConstant;
import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 上午10:24
 **/
public class MySimpleHttpServer {
    public static void main(String[] args) throws IOException {
        //创建serversocket
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(Constant.PORT));
        //serversocket一直在监听
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            //创建request，并解析
            Request request = new Request(inputStream);
            request.parse();
            //创建response，并返回
            Response response = new Response(outputStream);
            response.setRequest(request);
            response.sentResources();
        }
    }
}
