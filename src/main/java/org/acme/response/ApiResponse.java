package org.acme.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ApiResponse<T> {
    public static final int OK_CODE = 200;
    public static final String SUCCESS = "success";

    private int status;
    private List<T> data;
    private String message;
    private Date time = new Date();
    private boolean success;

    public ApiResponse() {
    }

    public ApiResponse(int status, List<T> data, String message, boolean success) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public static <T> ApiResponse<T> ok(List<T> data) {
        return new ApiResponse<>(OK_CODE, data, SUCCESS, Boolean.TRUE);
    }

    public int getStatus() {
        return status;
    }

    public List<T> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}