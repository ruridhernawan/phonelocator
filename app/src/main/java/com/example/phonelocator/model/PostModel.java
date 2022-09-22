package com.example.phonelocator.model;

import com.google.gson.annotations.SerializedName;

public class PostModel {
    @SerializedName("userName")
    private String userName;

    @SerializedName("body")
    public String body;

    @SerializedName("message")
    public String message;

    @SerializedName("userPassword")
    private String userPassword;

    public PostModel(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;

    }

    @SerializedName("code")
    private String code;

    @SerializedName("token")
    private String token;

    @SerializedName("devices")
    private DeviceModel[] device;

    @SerializedName("Status")
    private int Status;

    @SerializedName("Success")
    private boolean Success;

    @SerializedName("Status_Message")
    private String Status_Message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DeviceModel[] getDevice() {
        return device;
    }

    public void setDevice(DeviceModel[] device) {
        this.device = device;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getStatus_Message() {
        return Status_Message;
    }

    public void setStatus_Message(String status_Message) {
        Status_Message = status_Message;
    }
}
