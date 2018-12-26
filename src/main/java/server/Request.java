package main.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 上午10:24
 **/
public class Request {
    InputStream inputStream = null;
    String url = null;
    String fileName = null;
    String method = null;
    String header = null;

    public Request(InputStream input) {
        this.inputStream = input;
    }
    public void parse() throws IOException {
        //解析
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        // 接收不同的方法
        String s = null;
        StringBuffer requestInfo = new StringBuffer();
        while ((s=bufferedReader.readLine())!=null && !s.isEmpty()) {
            requestInfo.append(s + "\n");
        }
        System.out.printf("接受到的内容：\n%s", requestInfo);
        header = requestInfo.toString();
        method = parseMethod(requestInfo.toString());
        url = parseUrl(requestInfo.toString());
    }

    /**
     * 解析method方法
     * @param requestInfo
     * @return
     */
    private String parseMethod(String requestInfo) {
        int index1 = requestInfo.indexOf(" ");
        if(index1 != -1){
            return requestInfo.substring(0,index1);
        }
        return null;
    }

    /**
     * 解析header获取url
     * @param requestInfo
     * @return
     */
    private String parseUrl(String requestInfo) {
        int index1,index2;
        index1=requestInfo.indexOf(" ");
        if(index1!=-1){
            index2=requestInfo.indexOf(" ",index1+1);
            if(index2>index1){
                fileName = requestInfo.substring(index1 + 1, index2);
                return requestInfo.substring(index1+1,index2);
            }
        }
        return null;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUrl() {
        return url;
    }
}
