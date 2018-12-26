package main.version4_0_0.version4_0_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 上午10:24
 **/
public class Response {
    private Request request;
    private OutputStream outputStream;

    private static final String VERSION = "HTTP/1.1";
    List<String > headers = new ArrayList<>();

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sentResources()throws IOException{
        byte[] bytes=new byte[Constant.BUFFER_SIZE];
        FileInputStream fis=null;
        try {
            File file=new File(System.getProperty("user.dir") + "/webapp/" + request.getFileName());
            if(file.exists()){
                //需要加入header
                String header = fillHeader(Status._200);
                outputStream.write(header.getBytes());
                //读取file文件中内容
                fis=new FileInputStream(file);
                int ch=fis.read(bytes,0,Constant.BUFFER_SIZE);
                while(ch!=-1){
                    outputStream.write(bytes, 0, Constant.BUFFER_SIZE);
                    ch=fis.read(bytes, 0, Constant.BUFFER_SIZE);
                }
            }else{
                //404未发现
                String header = fillHeader(Status._404);
                String context = "<h1>File Not Found</h1>";
                outputStream.write(header.getBytes());
                outputStream.write(context.getBytes());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally{
            if(fis!=null){
                fis.close();
            }
        }
    }

    private String fillHeader(Status status) {
        return VERSION + status.toString() + "\n"+
                "Content-Type:text/html\r\n"+
                "\r\n";
    }
}
