package com.example.netease.architecturecomponentsdemo.demo.model.dto;

/**
 * Created by netease on 17/11/14.
 */

public class State {
    public static final int SUCCESS = 0;
    public static final int LOADING = 1;
    public static final int FAILED = 2;

    public State() {
    }

    public State(int status, String message) {
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
