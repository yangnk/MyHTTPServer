package main.java.server;

import java.io.*;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 上午10:24
 **/
public class Response {
    private Request request;
    private OutputStream outputStream;
    private Status status;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sent()throws IOException{
        //get 方法
        if (request.method.equals(Method.GET.toString())) {
            byte[] bytes=new byte[Constant.BUFFER_SIZE];
            FileInputStream fis=null;
            try {
                File file=new File(System.getProperty("user.dir") + "/webapp/" + request.getFileName());
                if(file.exists()){
                    //需要加入header
                    String header = fillHeader(Status._200);
                    outputStream.write(header.getBytes());
                    bytes = getBytes(file);
                    outputStream.write(bytes);
                }else{
                    //404未发现
                    status = Status._404;
                    String header = fillHeader(Status._404);
                    outputStream.write(header.getBytes());
//                    String body = "<h1>File Not Found</h1>";
                    String body = fillBody(status);
                    outputStream.write(body.getBytes());
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }finally{
                if(fis!=null){
                    fis.close();
                }
            }
        }

    }

    /**
     * 塞response的body
     * @param status
     * @return
     */
    private String fillBody(Status status) {
        String body = null;
        if (status.toString().equals(Status._404.toString())) {
            body = "<h1>File Not Found</h1>";
        }
        return body;
    }

    /**
     * 塞response的header
     * @param status http方法
     * @return
     */
    private String fillHeader(Status status) {
        String header = Constant.VERSION + status.toString() + "\n"+
                "Content-Type:text/html\r\n"+
                "\r\n";
        return header;
    }

    /**
     * 读取文件file中内容
     * @param file 文件file
     * @return
     * @throws IOException
     */
    private byte[] getBytes(File file) throws IOException {
        int length = (int) file.length();
        byte[] array = new byte[length];
        InputStream in = new FileInputStream(file);
        int offset = 0;
        while (offset < length) {
            int count = in.read(array, offset, (length - offset));
            offset += count;
        }
        in.close();
        return array;
    }
}
