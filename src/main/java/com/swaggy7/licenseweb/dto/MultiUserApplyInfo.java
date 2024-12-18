package com.swaggy7.licenseweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//多选 使用信息和申请信息时的DTO
public class MultiUserApplyInfo {
    private ArrayList<Integer> userWebIds;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime webTime;

    public ArrayList<Integer> getUserWebIds() {
        return userWebIds;
    }

    public void setUserWebIds(ArrayList<Integer> userWebIds) {
        this.userWebIds = userWebIds;
    }

    public LocalDateTime getWebTime() {
        return webTime;
    }

    public void setWebTime(LocalDateTime webTime) {
        this.webTime = webTime;
    }

    @Override
    public String toString() {
        return "MultiUserApplyInfo{" +
                "userWebIds=" + userWebIds +
                ", webTime=" + webTime +
                '}';
    }
}
