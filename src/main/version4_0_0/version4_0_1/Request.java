package main.version4_0_0.version4_0_1;

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
    String pathName = null;
    String fileName = null;

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
            if (s.startsWith("GET")) {
                System.out.println("this is GET method.");
            }else if (s.startsWith("POST")) {
                System.out.println("this is POST method.");
            }else if (s.startsWith("PUT")) {
                System.out.println("this is PUT method.");
            }else if (s.startsWith("DELETE")) {
                System.out.println("this is DELETE method.");
            }
            requestInfo.append(s + "\n");
        }
        System.out.printf("接受到的内容：\n%s", requestInfo);
        pathName = parseUrl(requestInfo.toString());
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

    public String getPathName() {
        return pathName;
    }
}
