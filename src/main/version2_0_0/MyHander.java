package main.version2_0_0;

import java.net.Socket;

public class MyHander implements Runnable{
    Socket socket = null;
    public MyHander(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("新连接:"+socket.getInetAddress()+":"+socket.getPort());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
