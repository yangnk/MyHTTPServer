package main.yangnk.java;

public enum Method {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String METHOD;
    Method(String method) {
        this.METHOD = method;
    }
}
