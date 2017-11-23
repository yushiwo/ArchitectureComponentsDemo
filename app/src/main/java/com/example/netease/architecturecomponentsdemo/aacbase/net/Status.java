package com.example.netease.architecturecomponentsdemo.aacbase.net;

/**
 * Created by netease on 17/11/14.
 */

public class Status {
    public static final int SUCCESS = 0;
    public static final int LOADING = 1;
    public static final int ERROR = 2;

    public Status() {
    }

    public Status(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
