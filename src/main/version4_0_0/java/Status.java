package main.version4_0_0.java;

public enum Status {
    _200("200 OK"),
    _404("404 Not Found");

    private final String status;
    Status(String status) {
        this.status = status;
    }
}
