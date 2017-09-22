package main.yangnk.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyHttpRequest {
    Method myMethod;
    String url;
    String version;
    List<String> header = new ArrayList<>();

    public MyHttpRequest(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = br.readLine();
        parseRequestLine(str);

        while (!str.equals("")) {
            str = br.readLine();
            getHeader(str);
        }
    }
    private void parseRequest(String str){
        try {
            String[] splitArr = str.split("\\s");
            myMethod = Method.valueOf(splitArr[0]);
            url = splitArr[1];
            version = splitArr[2];
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    private void getHeader(String str){
        this.header.add(str);
    }
    private void parseRequestLine(String str) {
//        log.info(str);
        String[] split = str.split("\\s+");
        try {
            myMethod = Method.valueOf(split[0]);
        } catch (Exception e) {
//            myMethod = Method.UNRECOGNIZED;
        }
        url = split[1];
        version = split[2];
    }
}
