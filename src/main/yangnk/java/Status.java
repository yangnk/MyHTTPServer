package main.yangnk.java;

public enum Status {
    _200("200 OK"),
    _404("404 Not Found");

    private final String status;
    Status(String status) {
        this.status = status;
    }
}
