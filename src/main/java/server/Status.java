package main.java.server;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2018-12-26 下午5:45
 **/
public enum Status {
    _200("200 ok"),
    _404("404 Not Found");

    public final String status;
    Status(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
