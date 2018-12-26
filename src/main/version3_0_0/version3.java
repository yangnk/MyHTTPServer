package main.version3_0_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Socket服务器端开启线程测试类
 * @author zzj
 * @date Jul 14, 2016 11:35:32 AM
 */
class ServerSocketPoolTest  {
    public static void main(String[] args) {
        testCommon();
    }

    /**
     * 1.测试普通的server
     * @author zzj
     */
    public static void testCommon(){
        ServerSocket serverSocket=null;
        //定义一个容量为50的线程
        ExecutorService service = Executors.newFixedThreadPool(50);
        try {
            serverSocket = new ServerSocket(220);
            while(true){
                System.out.println("wait receive message from client...");
                //接收客户端连接的socket对象
                Socket connection =null;
                //接收客户端传过来的数据，会阻塞
                connection=serverSocket.accept();
                service.submit(new SubPolThread(connection));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (serverSocket!=null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class SubPolThread implements Callable<Boolean>{
    private Socket connection;
    public SubPolThread(Socket conSocket){
        this.connection=conSocket;
    }

    /**
     * 读取客户端信息
     * @param inputStream
     */
    private static void readMessageFromClient(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader br=new BufferedReader(reader);
        String a = null;
        while((a=br.readLine())!=null){
            System.out.println(a);
        }
    }

    /**
     * 响应客户端信息
     * @param outputStream
     * @param string
     */
    private static void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        writer.append("I am server message!!!");
        writer.flush();
        writer.close();
    }

    /* (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Boolean call() throws Exception {
        try {

            System.out.println("****received message from client******");

            //读取客户端传过来的数据
            readMessageFromClient(connection.getInputStream());

            System.out.println("****received message from client end******");
            System.out.println();

            //向客户端写入数据
            writeMsgToClient(connection.getOutputStream(),"I am server message!!!");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            if (connection!=null) {
                try {
                    connection .close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
