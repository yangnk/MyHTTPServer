package main.version4_0_0.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyHttpResponse {
//    private static final String version = "HTTP/1.1";
    private static final String VERSION = "HTTP/1.1";
    List<String > headers = new ArrayList<>();
    byte[] body;
    // TODO: 2017/9/20 http response 继续完善

    public MyHttpResponse(MyHttpRequest req) throws IOException {
        if (req.myMethod == Method.GET) {					
            fillHeaders(Status._200);
            // TODO fix dir bug http://localhost:8080/src/test
            File file = new File("." + req.url);
            if (file.isDirectory()) {
                headers.add(ContentType.HTML.toString());
                StringBuilder result = new StringBuilder("<html><head><title>Index of ");
                result.append(req.url);
                result.append("</title></head><body><h1>Index of ");
                result.append(req.url);
                result.append("</h1><hr><pre>");

                // TODO add Parent Directory
                File[] files = file.listFiles();
                for (File subfile : files) {
                    result.append(" <a href=\"" + subfile.getPath() + "\">" + subfile.getPath() + "</a>\n");
                }
                result.append("<hr></pre></body></html>");
                fillResponse(result.toString());
            } else if (file.exists()) {
                setContentType(req.url, headers);
                fillResponse(getBytes(file));
            } else {
//                log.info("File not found:" + req.url);
                fillHeaders(Status._404);
                fillResponse(Status._404.toString());
            }}
    }

    private void setContentType(String url, List<String> headers) {
        try {
            String ext = url.substring(url.indexOf(".") + 1);
            headers.add(ContentType.valueOf(ext.toUpperCase()).toString());
        } catch (Exception e) {
//            log.error("ContentType not found: " + e, e);
        }
    }

    private void fillHeaders(Status status) {
        headers.add(MyHttpResponse.VERSION + " " + status.toString());
        headers.add("Connection: close");
        headers.add("Server: SimpleWebServer");
    }

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

    private void fillResponse(String response) {
        body = response.getBytes();
    }

    private void fillResponse(byte[] response) {
        body = response;
    }

    //http header
    private void setHeader(Status status) {
        headers.add(MyHttpResponse.VERSION + " " + status.toString());
        headers.add("Connection: close");
        headers.add("Server: mySimpleHTTPServer");
    }

    public void write(OutputStream os) throws IOException {
        DataOutputStream output = new DataOutputStream(os);
        for (String header : headers) {
            output.writeBytes(header + "\r\n");
        }
        output.writeBytes("\r\n");
        if (body != null) {
            output.write(body);
        }
        output.writeBytes("\r\n");
        output.flush();
    }
}
