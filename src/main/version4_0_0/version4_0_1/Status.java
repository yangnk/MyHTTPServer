package main.version4_0_0.version4_0_1;

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
}
