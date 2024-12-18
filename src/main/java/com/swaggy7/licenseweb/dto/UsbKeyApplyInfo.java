package com.swaggy7.licenseweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

public class UsbKeyApplyInfo {

    private Integer usbId;

    private String userName;

    private String userDept;

    private String userAccount;

    private String usbName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime usbTime;

    public Integer getUsbId() {
        return usbId;
    }

    public void setUsbId(Integer usbId) {
        this.usbId = usbId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUsbName() {
        return usbName;
    }

    public void setUsbName(String usbName) {
        this.usbName = usbName;
    }

    public LocalDateTime getUsbTime() {
        return usbTime;
    }

    public void setUsbTime(LocalDateTime usbTime) {
        this.usbTime = usbTime;
    }

    @Override
    public String toString() {
        return "UsbKeyApplyInfo{" +
                "usbId=" + usbId +
                ", userName='" + userName + '\'' +
                ", userDept='" + userDept + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", usbName='" + usbName + '\'' +
                ", usbTime=" + usbTime +
                '}';
    }
}
