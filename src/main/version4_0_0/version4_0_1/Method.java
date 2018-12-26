package main.version4_0_0.version4_0_1;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 下午6:04
 **/
public enum  Method {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String METHOD;
    Method(String method) {
        this.METHOD = method;
    }
}
