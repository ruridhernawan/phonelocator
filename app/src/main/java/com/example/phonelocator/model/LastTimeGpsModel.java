package com.example.phonelocator.model;

import com.google.gson.annotations.SerializedName;

public class LastTimeGpsModel {
    @SerializedName("deviceId")
    public String deviceId;

    @SerializedName("token")
    private String token;

    @SerializedName("body")
    public String body;

    @SerializedName("duration")
    private int duration;

    public LastTimeGpsModel(String deviceId, String token, int duration) {
        this.deviceId = deviceId;
        this.token = token;
        this.duration = duration;
    }

    @SerializedName("message")
    public String message;

    @SerializedName("code")
    private String code;

    @SerializedName("devices")
    private DeviceModel[] device;

    @SerializedName("Status")
    private int Status;

    @SerializedName("Success")
    private boolean Success;

    @SerializedName("Status_Message")
    private String Status_Message;

    @SerializedName("locations")
    public Location[] locations;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
